package template.com.example.kan_p.prokedex.util.pokeAPI

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import template.com.example.kan_p.prokedex.util.pokeAPI.model.PokemonDetailResponse
import template.com.example.kan_p.prokedex.util.pokeAPI.model.PokemonListResponse

interface PokeAPIService {
    @GET("pokemon/{index}")
    fun getPokemonDetail(@Path("index") index: Int): Call<PokemonDetailResponse>

    @GET("pokemon")
    fun getPokemonList(@Query("limit") limit: Int, @Query("offset") offset: Int): Call<PokemonListResponse>
}