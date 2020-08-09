package template.com.example.kan_p.prokedex.model

import android.graphics.drawable.Drawable
import com.google.gson.annotations.SerializedName
import template.com.example.kan_p.prokedex.R

enum class TypeEnum(val value: String, val iconID: Int, val iconWithTextID: Int) {
    @SerializedName("normal")
    NORMAL("Normal", R.drawable.type_normal, R.drawable.type_text_normal),
    @SerializedName("fire")
    FIRE("Fire", R.drawable.type_fire, R.drawable.type_text_fire),
    @SerializedName("fighting")
    FIGHTING("Fighting", R.drawable.type_fighting, R.drawable.type_text_fighting),
    @SerializedName("water")
    WATER("Water", R.drawable.type_water, R.drawable.type_text_water),
    @SerializedName("flying")
    FLYING("Flying", R.drawable.type_flying, R.drawable.type_text_flying),
    @SerializedName("grass")
    GRASS("Grass", R.drawable.type_grass, R.drawable.type_text_grass),
    @SerializedName("poison")
    POISON("Poison", R.drawable.type_poison, R.drawable.type_text_poison),
    @SerializedName("electric")
    ELECTRIC("Electric", R.drawable.type_electric, R.drawable.type_text_electric),
    @SerializedName("ground")
    GROUND("Ground", R.drawable.type_ground, R.drawable.type_text_ground),
    @SerializedName("psychic")
    PSYCHIC("Psychic", R.drawable.type_psychic, R.drawable.type_text_psychic),
    @SerializedName("rock")
    ROCK("Rock", R.drawable.type_rock, R.drawable.type_text_rock),
    @SerializedName("ice")
    ICE("Ice", R.drawable.type_ice, R.drawable.type_text_ice),
    @SerializedName("bug")
    BUG("Bug", R.drawable.type_bug, R.drawable.type_text_bug),
    @SerializedName("dragon")
    DRAGON("Dragon", R.drawable.type_dragon, R.drawable.type_text_dragon),
    @SerializedName("ghost")
    GHOST("Ghost", R.drawable.type_ghost, R.drawable.type_text_ghost),
    @SerializedName("dark")
    DARK("Dark", R.drawable.type_dark, R.drawable.type_text_dark),
    @SerializedName("steel")
    STEEL("Steel", R.drawable.type_steel, R.drawable.type_text_steel),
    @SerializedName("fairy")
    FAIRY("Fairy", R.drawable.type_fairy, R.drawable.type_text_fairy)
}