package com.example.nytimesbooks.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nytimesbooks.R
import com.example.nytimesbooks.data.models.Book
import kotlinx.android.synthetic.main.item_book.view.*

/**
 * onItemClickListener: Solicitamos que a activity passe este método por parâmetro para que a
 *      responsabilidade de criar outras telas seja da própria activity, e não do adapter, pois este
 *      não deve conter referências de views (activities e fragments).
 *
 *      Utilizando a expressão com lambda ((book: Book) -> Unit) estamos dizendo que o método receberá por
 *      parâmetro um objeto do tipo Book e retornará uma Unit.
 * */

class BookAdapter(
        private val books: List<Book>,
        val onItemClickListener: ((book: Book) -> Unit)
) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)

        // Passa o método onItemClickListener no construtor da subclasse para que esta possa chamar o método
        return BookViewHolder(view, onItemClickListener)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bindView(books[position])
    }

    override fun getItemCount() = books.count()

    class BookViewHolder(
        view: View,
        private val onItemClickListener: ((book: Book) -> Unit)
    ) : RecyclerView.ViewHolder(view) {

        private val title = view.tvTitle
        private val author = view.tvAuthor

        fun bindView(book: Book) {
            title.text = book.title
            author.text = book.author

            itemView.setOnClickListener { onItemClickListener.invoke(book) }
        }
    }
}
