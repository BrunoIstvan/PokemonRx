package br.com.bicmsystems.pokemonrx.api

import br.com.bicmsystems.pokemonrx.model.Pokemon
import br.com.bicmsystems.pokemonrx.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

interface PokemonAPI {

    @GET("/api/v2/pokemon")
    fun listAll() : Observable<PokemonResponse>

    @GET("/api/v2/pokemon/{name}")
    fun getByName(@Path("name") name: String) : Observable<Pokemon>

}