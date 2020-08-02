package template.com.example.kan_p.prokedex.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_pokedex.view.*
import template.com.example.kan_p.prokedex.R
import template.com.example.kan_p.prokedex.model.Pokemon
import kotlin.collections.ArrayList

class PokedexAdapter(private val items: ArrayList<Pokemon>): RecyclerView.Adapter<PokedexAdapter.ViewHolder>(), Filterable {

    private var filteredData = ArrayList<Pokemon>()

    init {
        filteredData.addAll(items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_pokedex, parent, false))
    }

    override fun getItemCount() = filteredData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(filteredData[position])
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            private val filterResults = FilterResults()
            override fun performFiltering(p0: CharSequence?): FilterResults {
                filteredData.clear()
                if (p0.isNullOrBlank()) {
                    filteredData.addAll(items)
                } else {
                    val queryTxt = p0.toString().toLowerCase().trim()
                    for (item in items) {
                        if (item.name.toLowerCase().contains(queryTxt)) {
                            filteredData.add(item)
                        }
                    }
                }
                return filterResults.also {
                    it.values = filteredData
                }
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                notifyDataSetChanged()
            }

        }
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