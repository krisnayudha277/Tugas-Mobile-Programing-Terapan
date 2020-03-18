package com.example.utsmobileprograming

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.RenderScript
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_tambah.*
import org.json.JSONArray

class Tambah : AppCompatActivity() {
    val context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah)

        insert.setOnClickListener() {
            var nama = nama_penduduk.text.toString()
            var ttl = ttl_penduduk.text.toString()
            var hp = hp_penduduk.text.toString()
            var alamat = alamat_penduduk.text.toString()
            postkeserver(nama, ttl,hp, alamat)

            val intent = Intent(context, Dasboard::class.java)
            startActivity(intent)
        }
    }
    fun postkeserver(data: String, data2: String, data3: String, data4: String) {
        AndroidNetworking.post("http://192.168.43.210/cobarepo/proses_mahasiswa.php")
            .addBodyParameter("nama_penduduk", data)
            .addBodyParameter("nim_penduduk", data2)
            .addBodyParameter("alamat_penduduk", data3)
            .addBodyParameter("alamat_penduduk", data4)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener {
                override fun onResponse(response: JSONArray?) {

                }

                override fun onError(anError: ANError?) {

                }
            })

    }
}

