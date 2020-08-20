package com.example.roomdatabaseview.repository

import androidx.lifecycle.LiveData
import com.example.roomdatabaseview.Dao.BookDao
import com.example.roomdatabaseview.model.Book

class BookRepository(private val bookDao: BookDao){

    val allWords : LiveData<List<Book>> = bookDao.getAllBook()

    suspend fun insert(book: Book){
        bookDao.insert(book)
    }
    suspend fun delete(){
        bookDao.deleteAll()
    }

   suspend fun deleteItem(name : String){
       bookDao.deleteItem(name)
   }
    suspend fun updateItem(updateName : String , name : String){
        bookDao.update(updateName,name)
    }
}