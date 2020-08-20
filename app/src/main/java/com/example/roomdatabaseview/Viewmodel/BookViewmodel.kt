package com.example.roomdatabaseview.Viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.roomdatabaseview.database.BookDatabase
import com.example.roomdatabaseview.model.Book
import com.example.roomdatabaseview.repository.BookRepository
import kotlinx.coroutines.launch

class BookViewmodel(application: Application) : AndroidViewModel(application) {


    private val repository: BookRepository
    val allBook: LiveData<List<Book>>

    init {
        val bookDao = BookDatabase.getDatabase(
            application
        ).bookDao()

        repository = BookRepository(bookDao)
        allBook = repository.allWords
    }

    fun insert(book: Book) = viewModelScope.launch {
        repository.insert(book)
    }

    fun delete() = viewModelScope.launch {
        repository.delete()
    }

    fun deleteItem(name : String) = viewModelScope.launch {
        repository.deleteItem(name)
    }
    fun updateItem(updateName : String, name : String) = viewModelScope.launch {
        repository.updateItem(updateName,name)
    }
}