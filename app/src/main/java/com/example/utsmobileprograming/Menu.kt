package com.example.utsmobileprograming

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
class Menu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val recyclerView = findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val users = ArrayList<User>()
        
        
         AndroidNetworking.get("http://192.168.43.210/cobarepo/penduduk-json.php")
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
                        var isi1 = jsonObject.optString("nama_penduduk").toString()
                        var isi2 = jsonObject.optString("ttl_penduduk").toString()
                        var isi3 = jsonObject.optString("hp_penduduk").toString()
                        var isi4 = jsonObject.optString("alamat_penduduk").toString()

                        users.add(User("$isi1", "$isi2", "$isi3", "$isi4"))


                    }

                    val adapter = CustomAdapter(users)
                    recyclerView.adapter = adapter


                }

                override fun onError(anError: ANError) {
                    Log.i("_err", anError.toString())
                }
            })
    }
}
