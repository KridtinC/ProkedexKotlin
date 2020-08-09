package template.com.example.kan_p.prokedex.model

import com.google.gson.annotations.SerializedName

data class BaseStats(
    @SerializedName("base_stat") var baseStat: Int,
    @SerializedName("effort") var effoftValue: Int,
    @SerializedName("stat") var stat: StatInfo
)

data class StatInfo(
    var name: String,
    var url: String
)