package br.com.bicmsystems.pokemonrx

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import br.com.bicmsystems.pokemonrx.adapter.PokemonAdapter
import br.com.bicmsystems.pokemonrx.api.PokemonService
import br.com.bicmsystems.pokemonrx.model.Pokemon
import kotlinx.android.synthetic.main.activity_main.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    lateinit var pokemons: ArrayList<Pokemon>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pokemons = arrayListOf()

        val adapter = PokemonAdapter(this, pokemons)
        rvPokemons.adapter = adapter
        rvPokemons.layoutManager = LinearLayoutManager(this)

        PokemonService.listAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            // Log.i("POKEMON", "${it.name} - ${it.sprites.frontDefault}")
                            pokemons.add(it)
                        }, {
                            Log.e("POKEMON", it.message)
                        }, {
                            // Log.i("POKEMON", "Todos pokemons percorridos")
                            adapter.notifyDataSetChanged()
                        })

    }
}
