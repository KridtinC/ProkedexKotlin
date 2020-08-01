package template.com.example.kan_p.prokedex.util.pokeAPI.model

import com.google.gson.annotations.SerializedName
import template.com.example.kan_p.prokedex.model.Pokemon

data class PokemonListResponse(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String,
    @SerializedName("previous") val previous: String,
    @SerializedName("results") val results: ArrayList<Pokemon>
)