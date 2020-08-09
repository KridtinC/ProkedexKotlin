package template.com.example.kan_p.prokedex.ui.pokemonDetail

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import template.com.example.kan_p.prokedex.R
import template.com.example.kan_p.prokedex.ui.ViewModelFactory

class PokemonDetailActivity: AppCompatActivity() {

    private lateinit var pokemonDetailViewModel: PokemonDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detail)

        val navView: BottomNavigationView = findViewById(R.id.nav_view_pokemon_detail)
        val navController = findNavController(R.id.nav_pokemon_detail_fragment)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_pokemon_detail_base, R.id.navigation_pokemon_detail_move, R.id.navigation_pokemon_detail_egg_group, R.id.navigation_pokemon_detail_more
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        pokemonDetailViewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(PokemonDetailViewModel::class.java)

        pokemonDetailViewModel.fetchPokemonDetail(intent.getIntExtra("POKEMON_ID", 0)).observe(this, Observer {
            if (it != null) {
                Log.i("PokemonDetailAcPKMD", it.abilities.toString())
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}