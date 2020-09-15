package com.example.nytimesbooks.data.response

import com.example.nytimesbooks.data.models.Book
import com.squareup.moshi.JsonClass

/**
 *  Ao utilizar uma data class, o Kotlin se encarregará de gerar o arquivo bytecode com todas as funções
 *  que normalmente seriam implementadas manualmente no Java, como: Getters e Setters, Constructors, Equals, ToString.
 *
 *  Será possível utilizar estas funções normalmente, sem que elas estejam visíveis dentro da classe.
 *
 *  As anotações @Json são injeções da biblioteca Moshi que irá converter o json recebido para a classe automaticamente.
 *
 *  Quando o atributo possui o mesmo nome do atributo no json recebido não é necessário adicionar a anotação @Json
 *  acima do atributo para fazer o mapeamento. O Moshi identificará os atributos com o mesmo nome  fará o mapeamento
 *  automaticamente. Neste caso os atributos possuem o mesmo nome, portanto não será adicionada a anotação.
 *
 * */

@JsonClass(generateAdapter = true)
data class BookDetailResponse (
    val title: String,
    val author: String,
    val description: String
) {
    /*
     * Delega a função de transformar o BookDetailResponse em Book para a própria classe Response.
     * Do contrário esta responsabilidade seria do BookViewModel.
     * Desta forma mantemos o ViewModel o mais limpo possível.
     */

    fun getBookModel() = Book(
            title = this.title,
            author = this.author,
            description = this.description
    )
}