package template.com.example.kan_p.prokedex.repository.room

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Query
import template.com.example.kan_p.prokedex.model.Pokemon

interface PokemonDao {
    @Insert
    fun insert(pokemonEntity: PokemonEntity)

    @Query("SELECT * FROM pokemon WHERE pokemon.id = :id")
    fun getPokemonByID(id: Int): LiveData<Pokemon>
}