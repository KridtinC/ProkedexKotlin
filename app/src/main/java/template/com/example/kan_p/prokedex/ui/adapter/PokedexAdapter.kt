package template.com.example.kan_p.prokedex.ui.adapter

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.Toast
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.item_pokedex.view.*
import template.com.example.kan_p.prokedex.R
import template.com.example.kan_p.prokedex.model.Pokemon
import template.com.example.kan_p.prokedex.ui.pokemonDetail.PokemonDetailActivity


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
        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, PokemonDetailActivity::class.java).apply {
                putExtra("POKEMON_ID", filteredData[position].id)
                putExtra("POKEMON_NAME", filteredData[position].name)
                putExtra("POKEMON_URL", filteredData[position].url)

            }
            Toast.makeText(it.context, filteredData[position].url, Toast.LENGTH_SHORT).show()
            it.context.startActivity(intent)

        }
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
                        if (item.name.toLowerCase().contains(queryTxt) or item.id.toString().contains(queryTxt)) {
                            filteredData.add(item)
                        }
                    }
                }
                Log.i("Adapter", filteredData.toString())
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
                Glide.with(itemView.context)
                    .load("${context.getDir("pokemonImage", Context.MODE_PRIVATE).absolutePath}/Pokemon_${pokemon.id}.png")
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                            Log.e("Adapter", e.toString())
                            return false
                        }

                        override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                            Log.i("Adapter", "ready")
                            Palette.from((resource as BitmapDrawable).bitmap).maximumColorCount(24).generate { palette ->
                                if (palette != null) {
                                    palette.lightMutedSwatch?.rgb?.let {
                                        itemView.item_pokemon_cardview.setCardBackgroundColor(
                                            it
                                        )
                                    }
                                }
                            }


                            return false
                        }

                    })
                    .fitCenter()
                    .placeholder(R.drawable.ic_pokedex_bottomnav)
                    .into(pokemon_img)

            }



        }
    }

}