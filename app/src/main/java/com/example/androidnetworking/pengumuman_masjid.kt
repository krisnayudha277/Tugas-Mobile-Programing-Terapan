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
import kotlinx.android.synthetic.main.activity_pengumuman_masjid.*
import org.json.JSONArray
import org.json.JSONObject

class pengumuman_masjid : AppCompatActivity() {
val context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengumuman_masjid)

        getdariserver()
        getkembali()
        simpan3.setOnClickListener(){
            var data_namamasjid= nama_masjid.text.toString()
            var data_alamatmasjid= alamat_masjid.text.toString()
            postkeserver(data_namamasjid,data_alamatmasjid)

            val intent = Intent(context,Home::class.java)
            startActivity(intent)
        }
    }
    fun getdariserver() {
        AndroidNetworking.get("https://masjidkrisna.000webhostapp.com/pengumuman-json.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.e("_kotlinResponse",response.toString())

                    val jsonArray : JSONArray = response.getJSONArray("result")
                    for (i  in 0 until jsonArray.length()){
                        val jsonObject : JSONObject = jsonArray.getJSONObject(i)
                        Log.e("_kotlineTittle",jsonObject.optString("judul_pengumuman"))
                        Log.e("_kotlineTittle",jsonObject.optString("isi_pengumuman"))


                        judulpengumuman.setText(jsonObject.optString("judul_pengumuman"))
                        isipengumuman.setText(jsonObject.optString("isi_pengumuman"))
                    }
                }

                override fun onError(anError: ANError) {
                    // handle error
                    Log.i("_err",anError.toString())
                }
            })
    }
    fun getkembali(){
        kembali4.setOnClickListener(){
            val home = Intent(context,Home::class.java)
            startActivity(home)
            finish()
        }
    }
    fun postkeserver(data:String,data2:String){
        AndroidNetworking.post("https://masjidkrisna.000webhostapp.com/proses-pengumuman.php")
            .addBodyParameter("nama_masjid",data)
            .addBodyParameter("alamat_masjid",data2)
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
