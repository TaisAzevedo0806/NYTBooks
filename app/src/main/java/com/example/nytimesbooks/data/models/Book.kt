package com.example.nytimesbooks.data.models

/**
 *  Ao utilizar uma data class, o Kotlin se encarregará de gerar o arquivo bytecode com todas as funções
 *  que normalmente seriam implementadas manualmente no Java, como: Getters e Setters, Constructors, Equals, ToString.
 *
 *  Será possível utilizar estas funções normalmente, sem que elas estejam visíveis dentro da classe.
 * */

data class Book (
        val title: String,
        val author: String,
        val description: String
)