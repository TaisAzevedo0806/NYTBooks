package com.example.nytimesbooks.ui.activity.detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nytimesbooks.R
import com.example.nytimesbooks.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.include_toolbar.*
import java.security.AccessControlContext

class DetailActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setupToolbar(toolBarMain, R.string.title_book_details)

        tvDetailTitle.text = intent.getStringExtra(EXTRA_TITLE)
        tvDetailDescription.text = intent.getStringExtra(EXTRA_DESCRIPTION)
    }

    /**
     * As funções declaradas dentro do Companion Object são semelhantes a classes estáticas, podem
     * ser chamadas sem que a classe seja instanciada.
     *
     * Criamos o método getStartIntent que irá retornar a intent pronta desta activity para outras
     * activities que desejem chamá-la. Então ao invés de cada activity que precisar chamar esta tela
     * precisar criar o Intent, já retornamos ele pronto.
     *
     * Recebemos por parâmetro nesta função o contexto (activity origem) e os objetos/valores que será utilizados
     * na nova tela (DetailActivity).
     * */

    companion object {
        private const val EXTRA_TITLE = "title"
        private const val EXTRA_DESCRIPTION = "description"

        fun getStartIntent(context: Context, title: String, description: String): Intent {
            // Primeiro parâmetro: activity origem (contexto). Segundo parâmetro: activity destino (classe)
            return Intent(context, DetailActivity::class.java).apply {
                // Dentro do contexto da Intent
                putExtra(EXTRA_TITLE, title)
                putExtra(EXTRA_DESCRIPTION, description)
            }
        }
    }
}