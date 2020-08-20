package com.example.roomdatabaseview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabaseview.R
import com.example.roomdatabaseview.model.Book
import kotlinx.android.synthetic.main.item_book.view.*

class BookAdapter : RecyclerView.Adapter<BookAdapter.BookViewHolder>(){

    private  var mClickListener: ClickListener? = null

    private  var books = emptyList<Book>()

    fun setOnClickListener(clickListener: ClickListener) {
        this.mClickListener = clickListener
    }

  inner  class BookViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView),
  View.OnClickListener{

      init {
          itemView.setOnClickListener(this)

      }
        lateinit var book: Book

        fun bind(book: Book){
            this.book = book
            itemView.txtBookName.text = book.bookName

        }

      override fun onClick(view: View?) {
          mClickListener?.onClick(book)
      }
  }
    fun addBookList(bookList : List<Book>){
        this.books = bookList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        var view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_book,parent,false)
        return BookViewHolder(view)
    }

    override fun getItemCount(): Int =
        books.size

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(books[position])
    }

    interface ClickListener{
        fun onClick(book: Book)
    }
}


