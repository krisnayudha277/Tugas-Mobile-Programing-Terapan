package com.example.androidnetworking

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
import kotlinx.android.synthetic.main.activity_identitas_masjid.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_slideshow_masjid.*
import org.json.JSONArray
import org.json.JSONObject

class slideshow_masjid : AppCompatActivity() {
val context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slideshow_masjid)

//        getdariserver()
        getkembali()
        simpan4.setOnClickListener() {
            var data_judul = slideshow2.text.toString()
            var data_url = urlslideshow2.text.toString()
            postkeserver(data_judul, data_url)

            val intent = Intent(context, Home::class.java)
            startActivity(intent)
        }


        val recyclerView = findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val users = ArrayList<User>()


        AndroidNetworking.get("https://masjidkrisna.000webhostapp.com/slideshow-json.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.e("_kotlinResponse", response.toString())

                    val jsonArray = response.getJSONArray("result")
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        Log.e("_kotlinTitle", jsonObject.optString("judul_slideshow"))

                        // txt1.setText(jsonObject.optString("shubuh"))
                        var isi1 = jsonObject.optString("judul_slideshow").toString()
                        var isi2 = jsonObject.optString("url_slideshow").toString()

                        users.add(User("$isi1", "$isi2"))


                    }

                    val adapter = CustomAdapter(users)
                    recyclerView.adapter = adapter


                }

                override fun onError(anError: ANError) {
                    Log.i("_err", anError.toString())
                }
            })
    }

    //    fun getdariserver(){
//        AndroidNetworking.get("https://masjidkrisna.000webhostapp.com/slideshow-json.php")
//            .setPriority(Priority.MEDIUM)
//            .build()
//            .getAsJSONObject(object : JSONObjectRequestListener {
//                override fun onResponse(response: JSONObject) {
//                    Log.e("_kotlinResponse",response.toString())
//
//                    val jsonArray : JSONArray = response.getJSONArray("result")
//                    for (i  in 0 until jsonArray.length()){
//                        val jsonObject : JSONObject = jsonArray.getJSONObject(i)
//                        Log.e("_kotlineTittle",jsonObject.optString("judul_slideshow"))
//                        Log.e("_kotlineTittle",jsonObject.optString("url_slideshow"))
//
//
//                        judulslideshow.setText(jsonObject.optString("judul_slideshow"))
//                        urlslideshow.setText(jsonObject.optString("url_slideshow"))
//                    }
//                }
//
//                override fun onError(anError: ANError) {
//                    // handle error
//                    Log.i("_err",anError.toString())
//                }
//            })
//    }
    fun getkembali(){
        kembali5.setOnClickListener(){
            val home = Intent(context,Home::class.java)
            startActivity(home)
            finish()
        }
    }
    fun postkeserver(data:String,data2:String){
        AndroidNetworking.post("https://masjidkrisna.000webhostapp.com/proses-sldieshow.php")
            .addBodyParameter("judul_slideshow",data)
            .addBodyParameter("url_slideshow",data2)
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
