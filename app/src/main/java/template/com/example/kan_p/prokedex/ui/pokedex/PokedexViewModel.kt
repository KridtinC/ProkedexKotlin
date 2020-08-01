package template.com.example.kan_p.prokedex.ui.pokedex

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import template.com.example.kan_p.prokedex.model.Pokemon
import template.com.example.kan_p.prokedex.repository.PokedexRepository

class PokedexViewModel : ViewModel() {

    private var allPokemons = MutableLiveData<ArrayList<Pokemon>>()
    private var pokedexRepository = PokedexRepository()

    init {
        allPokemons = pokedexRepository.getAllPokemons()
    }

    fun getAllPokemons(): MutableLiveData<ArrayList<Pokemon>>{
        return allPokemons
    }
}