package template.com.example.kan_p.prokedex.ui.pokedex

import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_pokedex.*
import template.com.example.kan_p.prokedex.R
import template.com.example.kan_p.prokedex.model.Pokemon
import template.com.example.kan_p.prokedex.ui.ViewModelFactory
import template.com.example.kan_p.prokedex.ui.adapter.PokedexAdapter
import java.io.File
import java.io.FileOutputStream


class PokedexFragment : Fragment() {
    private lateinit var pokedexViewModel: PokedexViewModel
    lateinit var allPokemons: ArrayList<Pokemon>
    private lateinit var pokedexAdapter: PokedexAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pokedexViewModel =
            ViewModelProviders.of(this, ViewModelFactory(requireContext())).get(PokedexViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_pokedex, container, false)
        setHasOptionsMenu(true)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        if (sharedPref != null) {

            if (!sharedPref.contains("isImagesDownloaded")) {
                //Downlaod images
                Log.i("Fragment", "Not downloaded yet")
                for (i in 0 until 807){
                    Glide.with(requireContext())
                        .asBitmap()
                        .load("https://cdn.traction.one/pokedex/pokemon/${i+1}.png")
                        .into(object : CustomTarget<Bitmap?>(300, 300) {
                            override fun onResourceReady(
                                resource: Bitmap,
                                transition: Transition<in Bitmap?>?
                            ) {
                                val path = saveImages(resource, i+1)
//                                Flowable.fromCallable { pokedexViewModel.saveImageURL(i+1 , path) }
//                                    .subscribeOn(Schedulers.io())
//                                    .observeOn(AndroidSchedulers.mainThread())
//                                    .subscribe { Log.d("FragmentRxJava", "insert complete") }
                            }

                            override fun onLoadCleared(placeholder: Drawable?) {

                            }
                        })
                    Log.i("FragmentLoop", i.toString())
                }

                with(sharedPref.edit()) {
                    putBoolean("isImagesDownloaded", true)
                    commit()
                }
            } else {
                //Get images from Room
                pokedexViewModel.getImageURL(59).observe(viewLifecycleOwner, Observer {
                    if (it != null) {
                        Log.i("FragmentGetImageURL", it)
                    }
                })

                Log.i("Fragment", "Downloaded")

            }

        }

        pokedexViewModel.getAllPokemons().observe(viewLifecycleOwner, Observer {
            if (it == null) {
                allPokemons = ArrayList()
            } else {
                allPokemons = it
            }
            pokedexAdapter = PokedexAdapter(allPokemons)

            val spanCount =
                if (requireActivity().resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) 1 else 2

            recyclerView.apply {
                layoutManager =
                    GridLayoutManager(context, spanCount, GridLayoutManager.VERTICAL, false)
                isNestedScrollingEnabled = false
                adapter = pokedexAdapter
                onFlingListener = null
            }

            pokedexAdapter.notifyDataSetChanged()
        })
    }

    private fun saveImages(resource: Bitmap, id: Int): String {
        val cw = ContextWrapper(context)
        val directory: File = cw.getDir("pokemonImage", Context.MODE_PRIVATE)
        val path = File(directory, "Pokemon_$id.png")
        val fos: FileOutputStream?
        try {
            fos = FileOutputStream(path)
            resource.compress(Bitmap.CompressFormat.PNG, 100, fos)
            fos.close()
            return path.absolutePath
        } catch (e: java.lang.Exception) {
            Log.e("SAVE_IMAGE", e.message, e)
        }
        return ""
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.pokedex_toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.pokedex_search -> {
            val searchView = MenuItemCompat.getActionView(item) as SearchView
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    pokedexAdapter.filter.filter(query)
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    Log.i("PokedexFragment", newText)
                    if (newText != null) {
                        pokedexAdapter.filter.filter(newText)
                    }
                    return false
                }
            })
            true
        }
        R.id.change_grid_view -> {
            Toast.makeText(context, "Change grid size", Toast.LENGTH_LONG).show()
            recyclerView.layoutManager
            recyclerView.apply {
                layoutManager = GridLayoutManager(context, 1, GridLayoutManager.VERTICAL, false)
            }
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}