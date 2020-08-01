package template.com.example.kan_p.prokedex.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_pokedex.view.*
import template.com.example.kan_p.prokedex.R
import template.com.example.kan_p.prokedex.model.Pokemon

class PokedexAdapter(private val items: ArrayList<Pokemon>): RecyclerView.Adapter<PokedexAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_pokedex, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class ViewHolder(itemsView: View): RecyclerView.ViewHolder(itemsView) {
        fun bind(pokemon: Pokemon) {
            itemView.apply {
                pokemon_name.text = pokemon.name
                pokemon_id.text = pokemon.id.toString()
            }

//            Picasso.get().load(pokemon.avatarImage).into(itemView.imgMember)

        }
    }

}