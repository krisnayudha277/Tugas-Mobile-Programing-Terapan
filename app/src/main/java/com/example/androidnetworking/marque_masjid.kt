package com.example.androidnetworking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_identitas_masjid.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_marque_masjid.*
import org.json.JSONArray
import org.json.JSONObject

class marque_masjid : AppCompatActivity() {
val context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marque_masjid)

        getdariserver()
        getkembali()
        simpan7.setOnClickListener(){
            var data_isimarque= isi_marquee2.text.toString()
//            var data_alamatmasjid= alamat_masjid.text.toString()
            postkeserver(data_isimarque)

            val intent = Intent(context,Home::class.java)
            startActivity(intent)
        }
    }
    fun getdariserver(){
        AndroidNetworking.get("https://masjidkrisna.000webhostapp.com/marquee-json.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.e("_kotlinResponse",response.toString())

                    val jsonArray : JSONArray = response.getJSONArray("result")
                    for (i  in 0 until jsonArray.length()){
                        val jsonObject : JSONObject = jsonArray.getJSONObject(i)
                        Log.e("_kotlineTittle",jsonObject.optString("isi_marquee"))


                        isi_marquee.setText(jsonObject.optString("isi_marquee"))
                    }
                }

                override fun onError(anError: ANError) {
                    // handle error
                    Log.i("_err",anError.toString())
                }
            })
    }
    fun getkembali(){
        kembali3.setOnClickListener(){
            val home = Intent(context,Home::class.java)
            startActivity(home)
            finish()
        }
    }
    fun postkeserver(data:String){
        AndroidNetworking.post("https://masjidkrisna.000webhostapp.com/proses-marquee.php")
            .addBodyParameter("isi_marquee",data)
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
