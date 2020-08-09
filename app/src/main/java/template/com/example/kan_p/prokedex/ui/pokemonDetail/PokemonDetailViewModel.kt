package template.com.example.kan_p.prokedex.ui.pokemonDetail

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import template.com.example.kan_p.prokedex.repository.PokedexRepository
import template.com.example.kan_p.prokedex.util.pokeAPI.model.PokemonDetailResponse

class PokemonDetailViewModel(context: Context): ViewModel() {

    private var pokemonDetail = MutableLiveData<PokemonDetailResponse>()
    private var pokedexRepository = PokedexRepository(context)

    fun fetchPokemonDetail(id: Int): MutableLiveData<PokemonDetailResponse> {
        pokemonDetail = pokedexRepository.getPokemonDetail(id)
        return pokemonDetail
    }

    fun getPokemonDetail(): MutableLiveData<PokemonDetailResponse> {
        return pokemonDetail
    }

    fun getMaxBaseStat(): Int {
        var maxStat: Int = pokemonDetail.value!!.baseStats[0].baseStat
        for (stat in pokemonDetail.value!!.baseStats) {
            if (stat.baseStat > maxStat) {
                maxStat = stat.baseStat
            }
        }
        return maxStat
    }

}