package com.example.datamahasiswa

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_dashboard.*
import org.json.JSONArray
import org.json.JSONObject

class Dashboard : AppCompatActivity() {
    val context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        insert.setOnClickListener() {
            var nama = nama_mahasiswa.text.toString()
            var nim = nim_mahasiswa.text.toString()
            var alamat = alamat_mahasiswa.text.toString()
            postkeserver(nama, nim, alamat)

            val intent = Intent(context, Dashboard::class.java)
            startActivity(intent)
        }

        button.setOnClickListener {
            val sharedPreferences = getSharedPreferences("CEKLOGIN", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()

            editor.putString("STATUS", "0")
            editor.apply()

            startActivity(Intent(this@Dashboard, MainActivity::class.java))
            finish()
        }


        val recyclerView = findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val users = ArrayList<User>()


        AndroidNetworking.get("https://siamahasiswa.000webhostapp.com/mahasiswa-json.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.e("_kotlinResponse", response.toString())

                    val jsonArray = response.getJSONArray("result")
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        Log.e("_kotlinTitle", jsonObject.optString("id_data"))

                        // txt1.setText(jsonObject.optString("shubuh"))
                        var isi1 = jsonObject.optString("nama_mahasiswa").toString()
                        var isi2 = jsonObject.optString("nim_mahasiswa").toString()
                        var isi3 = jsonObject.optString("alamat_mahasiswa").toString()

                        users.add(User("$isi1", "$isi2", "$isi3"))


                    }

                    val adapter = CustomAdapter(users)
                    recyclerView.adapter = adapter


                }

                override fun onError(anError: ANError) {
                    Log.i("_err", anError.toString())
                }
            })
    }
        fun postkeserver(data: String, data2: String, data3: String) {
            AndroidNetworking.post("https://siamahasiswa.000webhostapp.com/proses_mahasiswa.php")
                .addBodyParameter("nama_mahasiswa", data)
                .addBodyParameter("nim_mahasiswa", data2)
                .addBodyParameter("alamat_mahasiswa", data3)
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

