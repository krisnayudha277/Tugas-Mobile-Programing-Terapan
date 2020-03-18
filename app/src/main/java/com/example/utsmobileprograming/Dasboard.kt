package com.example.utsmobileprograming
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.*
import org.json.JSONArray
import org.json.JSONObject
class Dasboard : AppCompatActivity() {
    val context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        Tambah1.setOnClickListener() {
            val Intent = Intent(context, Tambah::class.java)
            startActivity(Intent)
            finish()
        }

        Berita.setOnClickListener() {
            val Intent = Intent(context, Menu::class.java)
            startActivity(Intent)
            finish()
        }
        button.setOnClickListener {
            val sharedPreferences = getSharedPreferences("CEKLOGIN", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()

            editor.putString("STATUS", "0")
            editor.apply()

            startActivity(Intent(this@Dasboard, MainActivity::class.java))
            finish()
        }
    }
}