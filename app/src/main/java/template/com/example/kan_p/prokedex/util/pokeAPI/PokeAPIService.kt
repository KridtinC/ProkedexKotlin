package template.com.example.kan_p.prokedex.util.pokeAPI

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import template.com.example.kan_p.prokedex.util.pokeAPI.model.PokemonListResponse

interface PokeAPIService {
//    @GET("pokemon")
//    fun getPokemon(@Path("index") index: String?): Call<PokemonRequest>

    @GET("pokemon")
    fun getPokemonList(@Query("limit") limit: Int, @Query("offset") offset: Int): Call<PokemonListResponse>

//    @GET("pokemon")
//    fun obtainListPokemonDesc(@Query("") pokemonNumber: Int): Call<PokemonDescRequest?>?
}