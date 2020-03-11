package com.example.intent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_1.*
import kotlinx.android.synthetic.main.activity_2.*
import kotlinx.android.synthetic.main.activity_main.*

class Activity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        val intentObject:Intent=intent
        val name=intentObject.getStringExtra("name")
        textView.text = "Hey $name"
        val context=this

        btnIntentUtama2.setOnClickListener(){
            val Intent = Intent(context,MainActivity::class.java)
            startActivity(Intent)
            finish()
        }
    }
}
