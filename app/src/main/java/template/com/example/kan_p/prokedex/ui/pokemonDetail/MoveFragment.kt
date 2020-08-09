package template.com.example.kan_p.prokedex.ui.pokemonDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import template.com.example.kan_p.prokedex.R

class MoveFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_pokemon_detail_move, container, false)
        return root
    }
}