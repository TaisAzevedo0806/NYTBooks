package com.example.nytimesbooks.ui.activity.book

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nytimesbooks.R
import com.example.nytimesbooks.data.APIService
import com.example.nytimesbooks.data.models.Book
import com.example.nytimesbooks.data.response.BookResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * O ViewModel é responsável por gerenciar os dados da Activity (UI) e fazer a ligação com as demais camadas
 * do aplicativo (network, acesso de dados, etc). Fazemos a ligação utilizando o LiveData.
 *
 * Dentro do ViewModel não deve ser criada nenhuma referência à componentes de interface (como activities ou fragments).
 * O ViewModel deve ser independente desses componentes.
 * Por exemplo, quando rotacionamos o dispositivo, a activity na tela será destruída e construída novamente na nova
 * orientação. O ViewModel deve sobreviver a este processo sem ligações com a activity. Do contrário, a activity será
 * destruída mas continuará na memória devido à ligação com o ViewModel, que não é destruído, resultando em um
 * vazamento de memória no aplicativo.
 *
 * Ao manter o ViewModel separado da interface, também é possível realizar testes unitários no ViewModel, sem a necessidade
 * de realizar os testes do framework do Android em um emulador.
 *
 * */

class BooksViewModel : ViewModel() {

    val booksLiveData: MutableLiveData<List<Book>> = MutableLiveData()

    // A classe Pair permite passar dois valores no mesmo parâmetro.
    // O ponto de interrogação no segundo valor indica que o mesmo pode receber valores nulos.
    val viewFlipperLiveData: MutableLiveData<Pair<Int, Int?>> = MutableLiveData()

    fun getBooks() {
        // Exemplo para criar a lista manualmente antes de buscar os dados
        //booksLiveData.value = createListBooks()

        // A função enqueue é assíncrona, não irá travar o aplicativo enquanto aguarda a resposta da api.
        // Enquanto que a função execute é síncrona, e só irá permitir a utilização do app após receber o retorno da api.

        APIService.service.getBooks().enqueue(object: Callback<BookResponse> {

            override fun onResponse(call: Call<BookResponse>, response: Response<BookResponse>) {

                // Verifica se a chamada retornou um código entre 200 e 300
                when {
                    response.isSuccessful -> {
                        val books: MutableList<Book> = mutableListOf() // Cria uma lista vazia

                        response.body()?.let {
                            for (result in it.results) books.add(result.details[0].getBookModel())
                        }

                        booksLiveData.value = books
                        viewFlipperLiveData.value = Pair(VIEW_FLIPPER_BOOKS, null)
                    }
                    response.code() == 401 -> viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERROR, R.string.error_401)
                    else -> viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERROR, R.string.unhandled_error)
                }
            }

            override fun onFailure(call: Call<BookResponse>, t: Throwable) {
                // Tratamento do retorno em caso de falha, por exemplo: gravar logs ou enviar log para o Crashlytics
                viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERROR, R.string.error_500)
            }
        })
    }

    companion object {
        private const val VIEW_FLIPPER_BOOKS = 1
        private const val VIEW_FLIPPER_ERROR = 2
    }
/*
    fun createListBooks(): List<Book> {
        return listOf(
                Book("Title 1", "Author 1"),
                Book("Title 2", "Author 2"),
                Book("Title 3", "Author 3"),
                Book("Title 4", "Author 4"),
                Book("Title 5", "Author 5")
        )
    }
 */
}