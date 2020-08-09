package template.com.example.kan_p.prokedex.ui.pokemonDetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_pokemon_detail_base_info.*
import template.com.example.kan_p.prokedex.R

class BaseInfoFragment : Fragment() {

    private lateinit var pokemonDetailViewModel: PokemonDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_pokemon_detail_base_info, container, false)

        pokemonDetailViewModel =
            ViewModelProvider(requireActivity()).get(PokemonDetailViewModel::class.java)
        (activity as AppCompatActivity).supportActionBar?.apply {
            title = (activity as AppCompatActivity).intent.getStringExtra("POKEMON_NAME")
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pokemonDetailViewModel.getPokemonDetail().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                Log.i("BaseInfoFragmentPKMD", it.types.toString())

                //Set Type Image
                Glide.with(requireContext())
                    .load(it.types[0].type.name.iconWithTextID)
                    .into(base_info_type1)
                if (it.types.size > 1) {
                    Glide.with(requireContext())
                        .load(it.types[1].type.name.iconWithTextID)
                        .into(base_info_type2)
                }

                //Set Base Stats
                val maxStat = pokemonDetailViewModel.getMaxBaseStat()
                base_info_base_stat_hp_value.apply {
                    max = maxStat
                    progress = it.baseStats[0].baseStat
                }
                base_info_base_stat_attack_value.apply {
                    max = maxStat
                    progress = it.baseStats[1].baseStat
                }
                base_info_base_stat_defense_value.apply {
                    max = maxStat
                    progress = it.baseStats[2].baseStat
                }
                base_info_base_stat_spattack_value.apply {
                    max = maxStat
                    progress = it.baseStats[3].baseStat
                }
                base_info_base_stat_spdefense_value.apply {
                    max = maxStat
                    progress = it.baseStats[4].baseStat
                }
                base_info_base_stat_speed_value.apply {
                    max = maxStat
                    progress = it.baseStats[5].baseStat
                }

            }
        })
    }
}