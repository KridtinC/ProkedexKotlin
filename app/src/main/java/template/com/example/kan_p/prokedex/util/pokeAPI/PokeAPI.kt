package template.com.example.kan_p.prokedex.util.pokeAPI

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokeAPI {
    companion object {
        private const val BASE_URL = "https://pokeapi.co/api/v2/"
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}