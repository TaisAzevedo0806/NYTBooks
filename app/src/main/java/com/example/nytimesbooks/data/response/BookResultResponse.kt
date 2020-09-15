package com.example.nytimesbooks.data.response

import com.squareup.moshi.Json
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
 *  automaticamente. Neste caso foi criado o atributo com nome diferente e utilizada a anotação.
 *
 * */

@JsonClass(generateAdapter = true)
data class BookResultResponse (
    @Json(name = "book_details")
    val details: List<BookDetailResponse>
)