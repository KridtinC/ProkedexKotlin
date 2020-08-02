package template.com.example.kan_p.prokedex.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import template.com.example.kan_p.prokedex.ui.pokedex.PokedexViewModel

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PokedexViewModel::class.java)) {
            return PokedexViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}