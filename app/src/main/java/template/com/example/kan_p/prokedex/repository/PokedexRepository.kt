package template.com.example.kan_p.prokedex.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import template.com.example.kan_p.prokedex.model.Pokemon
import template.com.example.kan_p.prokedex.util.pokeAPI.PokeAPI
import template.com.example.kan_p.prokedex.util.pokeAPI.PokeAPIService
import template.com.example.kan_p.prokedex.util.pokeAPI.model.PokemonListResponse


class PokedexRepository {

    fun getAllPokemons(): MutableLiveData<ArrayList<Pokemon>> {
        val allPokemons = MutableLiveData<ArrayList<Pokemon>>()

        var req = PokeAPI.retrofit.create(PokeAPIService::class.java).getPokemonList(807, 0)
        req.enqueue(object : Callback<PokemonListResponse?> {
            override fun onResponse(
                call: Call<PokemonListResponse?>,
                response: Response<PokemonListResponse?>
            ) {
                if (response.isSuccessful) {
                    allPokemons.value = response.body()?.results
                    Log.i("PokedexRepo", response.body()?.results.toString())
                } else{
                    allPokemons.value = null
                    Log.e("PokedexRepo", "onResponse: " +response.errorBody())
                }
            }

            override fun onFailure(call: Call<PokemonListResponse?>, t: Throwable) {
                allPokemons.value = null
                Log.e("PokedexRepo", "onFailure: " + t.message)
            }
        })
        return allPokemons
    }
}