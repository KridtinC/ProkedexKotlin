package template.com.example.kan_p.prokedex.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import template.com.example.kan_p.prokedex.model.Pokemon
import template.com.example.kan_p.prokedex.repository.room.AppDatabase
import template.com.example.kan_p.prokedex.repository.room.ImagePathEntity
import template.com.example.kan_p.prokedex.util.pokeAPI.PokeAPI
import template.com.example.kan_p.prokedex.util.pokeAPI.PokeAPIService
import template.com.example.kan_p.prokedex.util.pokeAPI.model.PokemonDetailResponse
import template.com.example.kan_p.prokedex.util.pokeAPI.model.PokemonListResponse


class PokedexRepository(context: Context) {

    private val mContext = context
    private val appDatabase: AppDatabase = AppDatabase.getAppDatabase(mContext)

    fun getPokemonDetail(id: Int): MutableLiveData<PokemonDetailResponse> {
        val pokemonDetail = MutableLiveData<PokemonDetailResponse>()
        Log.i("PokedexRepo", id.toString())
        var req = PokeAPI.retrofit.create(PokeAPIService::class.java).getPokemonDetail(id)
        req.enqueue(object : Callback<PokemonDetailResponse?> {
            override fun onFailure(call: Call<PokemonDetailResponse?>, t: Throwable) {
                pokemonDetail.value = null
                Log.e("PokedexRepo", "onFailure: " + t.message)
            }

            override fun onResponse(
                call: Call<PokemonDetailResponse?>,
                response: Response<PokemonDetailResponse?>
            ) {
                if (response.isSuccessful) {
                    pokemonDetail.value = response.body()
                } else {
                    pokemonDetail.value = null
                    Log.e("PokedexRepo", "onResponse: " +response.errorBody())
                }
            }

        })

        return pokemonDetail
    }

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
                    for (i in 0 until allPokemons.value!!.size){
                        allPokemons.value!![i].id = i+1
                    }
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

    fun getImageURL(id: Int): LiveData<String> {
        return appDatabase.imagePathDao().getPath(id)
    }

    fun saveImageURL(id: Int, path: String) {
        val pokemonImagePath = ImagePathEntity(id, path)
        appDatabase.imagePathDao().insert(pokemonImagePath)
    }
}