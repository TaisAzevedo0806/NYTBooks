package com.example.nytimesbooks.ui.activity.book

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nytimesbooks.R
import com.example.nytimesbooks.ui.activity.detail.DetailActivity
import com.example.nytimesbooks.ui.adapter.BookAdapter
import com.example.nytimesbooks.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_books.*
import kotlinx.android.synthetic.main.include_toolbar.*

class BooksActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

        // Configurar toolbar personalizada
        setupToolbar(toolBarMain, R.string.title_books, false)

        setObservers()
    }

    private fun setObservers() {
        val booksViewModel: BooksViewModel = ViewModelProvider(this).get(BooksViewModel::class.java)

        // Books observer
        // Cria o observer, e cada vez que o objeto booksLiveData tiver alguma alteração, esta ação será executada automaticamente
        booksViewModel.booksLiveData.observe(this, {
            /**
             * Utilizando a extensão .let entramos no contexto do objeto, e com o ponto de interrogação é realizada a validação
             * automática de valor nulo. Portanto temos a garantia de que dentro do .let podemos acessar o objeto tranquilamente
             * sem realizar nenhuma validação manual de valor nulo.
             *
             * Ainda dentro da extensão .let, acessariamos o objeto do contexto utilizando o nome it.
             * Para alterar o nome do objeto dentro da extensão podemos utilizar a síntaxe books ->
             * (nome personalizado seguido de lambda).
             *
             */
            it?.let { books ->
                with(recycler) {
                    // Como estamos dentro do contexto do recycler view, utilizamos o this@BooksActivity para acessar
                    // um contexto de fora, neste caso é o contexto da BooksActivity
                    layoutManager = LinearLayoutManager(this@BooksActivity, RecyclerView.VERTICAL, false)
                    setHasFixedSize(true)

                    /*
                     * Quando uma expressão lambda é o último parâmetro de uma função, podemos fechar os parênteses
                     * após o penúltimo parâmetro (neste caso a lista books) e implementar a função com chaves após os
                     * parênteses. Neste caso a função implementada é o onItemClickListener.
                     */
                    adapter = BookAdapter(books) { book ->
                        /*
                        * Passa por parâmetro o método onClickListener, para que a chamada da activity destino
                        * seja feita aqui na activity origem, e não dentro do adapter.
                        * */
                        val intent = DetailActivity.getStartIntent(this@BooksActivity, book.title, book.description)
                        this@BooksActivity.startActivity(intent)
                    }
                }
            }
        })
        booksViewModel.getBooks()

        // ViewFlipper observer
        booksViewModel.viewFlipperLiveData.observe(this, Observer {
            it?.let { flipper ->
                viewFlipperBooks.displayedChild = flipper.first // .first acessa  primeira posição do parãmetro que utiliza a classe Pair

                // Só executa este bloco se o parâmetro for diferente de null
                flipper.second?.let{ resErroId ->
                    tvError.text = getString(resErroId)
                }
            }
        })
    }
}