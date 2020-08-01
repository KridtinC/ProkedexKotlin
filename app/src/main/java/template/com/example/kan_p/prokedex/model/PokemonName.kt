package template.com.example.kan_p.prokedex.model

import com.google.gson.annotations.SerializedName

data class PokemonName(
    @SerializedName("english") val en: String,
    @SerializedName("japanese") val jp: String,
    @SerializedName("chinese") val ch: String,
    @SerializedName("french") val fr: String)