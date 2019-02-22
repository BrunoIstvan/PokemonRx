package br.com.bicmsystems.pokemonrx.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.bicmsystems.pokemonrx.R
import br.com.bicmsystems.pokemonrx.model.Pokemon
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_pokemon.view.*

class PokemonAdapter(
        val context: Context,
        val pokemons: List<Pokemon>) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {
    override fun onBindViewHolder(holder: PokemonViewHolder?, position: Int) {
        val pokemon = pokemons[position]
        holder?.let {
            holder.bindView(pokemon)
        }
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PokemonViewHolder {
        val itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_pokemon, parent, false)
        return PokemonViewHolder(itemView)
    }

    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(pokemon: Pokemon) = with(itemView) {
            tvPokemonName.text = pokemon.name

            Glide.with(context).load(pokemon.sprites.frontDefault).into(ivPokemon)
        }
    }
}