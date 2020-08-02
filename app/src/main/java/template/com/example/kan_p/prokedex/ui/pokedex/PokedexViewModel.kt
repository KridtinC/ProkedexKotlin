package template.com.example.kan_p.prokedex.ui.pokedex

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import template.com.example.kan_p.prokedex.model.Pokemon
import template.com.example.kan_p.prokedex.repository.PokedexRepository

class PokedexViewModel(context: Context) : ViewModel() {

    private var allPokemons = MutableLiveData<ArrayList<Pokemon>>()
    private var pokedexRepository = PokedexRepository(context)

    init {
        allPokemons = pokedexRepository.getAllPokemons()
    }

    fun getAllPokemons(): MutableLiveData<ArrayList<Pokemon>>{
        return allPokemons
    }

    fun getImageURL(id: Int): LiveData<String> {
        return pokedexRepository.getImageURL(id)
    }

    fun saveImageURL(id: Int, path: String) {
        pokedexRepository.saveImageURL(id, path)
    }
}