package br.com.bicmsystems.pokemonrx.api

import br.com.bicmsystems.pokemonrx.model.Pokemon
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable

object PokemonService {

    private val BASE_URL: String get() = "https://pokeapi.co/"

    fun buildRetrofit() : PokemonAPI {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY


        val okhttp = OkHttpClient.Builder()
                //.addNetworkInterceptor(StethoInterceptor())
                .build()

        return Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okhttp)
                .build()
                .create(PokemonAPI::class.java)
    }

    fun listAll() : Observable<Pokemon>  {
        return buildRetrofit().listAll()
                .flatMap { Observable.from(it.results) }
                .flatMap { buildRetrofit().getByName( it.name ) }

    }
    //fun getByName(name: String) : Observable<Pokemon> = buildRetrofit().getByName(name)

}