package template.com.example.kan_p.prokedex.ui.pokedex

import android.content.res.Configuration
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
import kotlinx.android.synthetic.main.fragment_pokedex.*
import template.com.example.kan_p.prokedex.R
import template.com.example.kan_p.prokedex.model.Pokemon
import template.com.example.kan_p.prokedex.ui.adapter.PokedexAdapter
import java.util.*
import kotlin.collections.ArrayList

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
            ViewModelProviders.of(this).get(PokedexViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_pokedex, container, false)
        setHasOptionsMenu(true)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pokedexViewModel.getAllPokemons().observe(viewLifecycleOwner, Observer {
            if (it == null){
                allPokemons = ArrayList()
            } else {
                allPokemons = it
            }
            pokedexAdapter = PokedexAdapter(allPokemons)

            val spanCount = if(activity!!.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) 2 else 4

            recyclerView.apply {
                layoutManager = GridLayoutManager(context, spanCount, GridLayoutManager.VERTICAL, false)
                isNestedScrollingEnabled = false
                adapter = pokedexAdapter
                onFlingListener = null
            }

            pokedexAdapter.notifyDataSetChanged()
        })
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
            Toast.makeText(context,"Change grid size", Toast.LENGTH_LONG).show()
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