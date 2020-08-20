package com.example.roomdatabaseview

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import kotlinx.android.synthetic.main.activity_add_book.*

class AddBookActivity : AppCompatActivity() {

    lateinit var bookNam : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book)


        btnSave.setOnClickListener {

            val replyIntent = Intent()
//            bookNam = edtBookName.text.toString()

            if(TextUtils.isEmpty(edtBookName.text)){
                setResult(Activity.RESULT_CANCELED,replyIntent)
            }else{
                val bookName = edtBookName.text.toString()
                replyIntent.putExtra(EXTRA_REPLY,bookName)
                setResult(Activity.RESULT_OK,replyIntent)

            }
            finish()
        }
    }
    companion object{
        const val EXTRA_REPLY = "REPLY"

    }
}