package template.com.example.kan_p.prokedex.model

import com.google.gson.annotations.SerializedName

data class BaseStats(
    @SerializedName("HP") var hp: Int,
    @SerializedName("Attack") var atk: Int,
    @SerializedName("Defense") var def: Int,
    @SerializedName("Sp. Attack") var spAtk: Int,
    @SerializedName("Sp. Defense") var spDef: Int,
    @SerializedName("Speed") var speed: Int
)