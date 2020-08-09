package template.com.example.kan_p.prokedex.util.pokeAPI.model

import com.google.gson.annotations.SerializedName
import template.com.example.kan_p.prokedex.model.*

data class PokemonListResponse(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String,
    @SerializedName("previous") val previous: String,
    @SerializedName("results") val results: ArrayList<Pokemon>
)

data class PokemonDetailResponse(
    @SerializedName("abilities") val abilities: ArrayList<Ability>,
    @SerializedName("base_experience") val baseExperience: Int,
    @SerializedName("forms") val forms: ArrayList<Form>,
    @SerializedName("height") val height: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("stats") val baseStats: ArrayList<BaseStats>,
    @SerializedName("types") val types: ArrayList<Type>,
    @SerializedName("weight") val weight: Int
)