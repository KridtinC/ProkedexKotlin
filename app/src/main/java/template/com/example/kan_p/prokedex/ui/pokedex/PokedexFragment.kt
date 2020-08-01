package template.com.example.kan_p.prokedex.ui.pokedex

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_pokedex.*
import template.com.example.kan_p.prokedex.R
import template.com.example.kan_p.prokedex.model.Pokemon
import template.com.example.kan_p.prokedex.ui.adapter.PokedexAdapter

class PokedexFragment : Fragment() {
    private lateinit var pokedexViewModel: PokedexViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pokedexViewModel =
            ViewModelProviders.of(this).get(PokedexViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_pokedex, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var allPokemons: ArrayList<Pokemon>
        pokedexViewModel.getAllPokemons().observe(viewLifecycleOwner, Observer {
            if (it == null){
                allPokemons = ArrayList()
            } else {
                allPokemons = it
            }
            val pokedexAdapter = PokedexAdapter(allPokemons)

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
}