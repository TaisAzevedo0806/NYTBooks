package com.example.nytimesbooks.data

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Utilizando a palavra object no lugar de class, transformamos esta classe em um Singleton (objeto que
 * é instanciado apenas uma vez durante toda a execução do aplicativo).
 * */

object APIService {

    // Inicializa o Retrofit passando por parâmetro a classe que representa os endpoints
    val service: NYTServices = initRetrofit().create(NYTServices::class.java)

    private fun initRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/books/v3/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

}