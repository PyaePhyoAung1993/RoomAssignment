package com.example.roomdatabaseview

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdatabaseview.Viewmodel.BookViewmodel
import com.example.roomdatabaseview.adapter.BookAdapter
import com.example.roomdatabaseview.model.Book
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_update.view.*

class MainActivity : AppCompatActivity() , BookAdapter.ClickListener {

    private lateinit var bookViewmodel: BookViewmodel
    private lateinit var bookAdapter: BookAdapter

    private val addBookActivityRequestCode = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bookAdapter = BookAdapter()

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = bookAdapter
        }
        bookAdapter.setOnClickListener(this)

        bookViewmodel = ViewModelProviders.of(this)
            .get(BookViewmodel::class.java)

        bookViewmodel.allBook.observe(
            this, Observer { books ->
                books.let {
                    bookAdapter.addBookList(books)
                }
            }
        )
        btnDelete.setOnClickListener {
            bookViewmodel.delete()
        }

        btnAdd.setOnClickListener {
            val intent = Intent(
                this,
                AddBookActivity::class.java
            )
            startActivityForResult(intent,addBookActivityRequestCode)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == addBookActivityRequestCode && resultCode == Activity.RESULT_OK){
            data?.getStringExtra(AddBookActivity.EXTRA_REPLY)?.let {
                val book = Book(it)
                bookViewmodel.insert(book)
            }
        }
    }

    override fun onClick(book: Book) {
        val builder = AlertDialog.Builder(this)
        builder.apply {
            setTitle("Delete Item")
            setMessage("Are you sure to delete")
            setIcon(android.R.drawable.ic_dialog_alert)
            setPositiveButton("Yes"){
                dialogInterface, i ->
                bookViewmodel.deleteItem(book.bookName)
            }
            setNegativeButton("No"){
                dialogInterface, i ->
                Toast.makeText(applicationContext,"Delete cancel", Toast.LENGTH_LONG).show()
            }
            setNeutralButton("Update"){
                dialogInterface, i ->
                val updateBuilder = AlertDialog.Builder(context)
                val dialogLayout = layoutInflater.inflate(
                    R.layout.dialog_update,null
                )
                updateBuilder.apply {
                    setTitle("Update Book")
                    setView(dialogLayout)
                    setPositiveButton("OK") {
                        dialogInterface, i ->
                        val updateText = dialogLayout.edtUpdateName.text.toString()
                        bookViewmodel.updateItem(
                            updateText ,book.bookName

                        )
                    }
                }
                val updateDialog : AlertDialog = updateBuilder.create()
                updateDialog.show()
            }
        }

        val alertDialog : AlertDialog = builder.create()
        alertDialog.show()



    }
}