package com.example.androidnetworking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.androidnetworking.error.ANError
import org.json.JSONArray
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
val context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        getdariserver()
        getkembali()
        simpan2.setOnClickListener(){
            var data_shubuh= shubuh2.text.toString()
            var data_dhuhur= dhuhur2.text.toString()
            var data_ashar= ashar2.text.toString()
            var data_magrib= magrib2.text.toString()
            var data_isha= isha2.text.toString()
            var data_dhuha= dhuha2.text.toString()
            postkeserver(data_shubuh,data_dhuhur,data_ashar,data_magrib,data_isha,data_dhuha)

            val intent = Intent(context,Home::class.java)
            startActivity(intent)
        }
    }

    fun getdariserver(){
        AndroidNetworking.get("https://masjidkrisna.000webhostapp.com/jadwal_json.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.e("_kotlinResponse",response.toString())

                    val jsonArray : JSONArray = response.getJSONArray("result")
                    for (i  in 0 until jsonArray.length()){
                        val jsonObject : JSONObject = jsonArray.getJSONObject(i)
                        Log.e("_kotlineTittle",jsonObject.optString("shubuh"))
                        Log.e("_kotlineTittle",jsonObject.optString("dhuhur"))
                        Log.e("_kotlineTittle",jsonObject.optString("ashar"))
                        Log.e("_kotlineTittle",jsonObject.optString("maghrib"))
                        Log.e("_kotlineTittle",jsonObject.optString("isha"))
                        Log.e("_kotlineTittle",jsonObject.optString("dhuha"))


                        txt1.setText(jsonObject.optString("shubuh"))
                        txt2.setText(jsonObject.optString("dhuhur"))
                        txt3.setText(jsonObject.optString("ashar"))
                        txt4.setText(jsonObject.optString("maghrib"))
                        txt5.setText(jsonObject.optString("isha"))
                        txt6.setText(jsonObject.optString("dhuha"))
                    }
                }

                override fun onError(anError: ANError) {
                    // handle error
                    Log.i("_err",anError.toString())
                }
            })
    }
    fun getkembali(){
        kembali.setOnClickListener(){
            val home = Intent(context,Home::class.java)
            startActivity(home)
            finish()
        }
    }
    fun postkeserver(data:String,data2:String,data3:String,data4:String,data5:String,data6:String){
        AndroidNetworking.post("https://masjidkrisna.000webhostapp.com/proses-jadwal.php")
            .addBodyParameter("shubuh",data)
            .addBodyParameter("dhuhur",data2)
            .addBodyParameter("ashar",data3)
            .addBodyParameter("maghrib",data4)
            .addBodyParameter("isha",data5)
            .addBodyParameter("dhuha",data6)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener{
                override fun onResponse(response: JSONArray?) {

                }

                override fun onError(anError: ANError?) {

                }
            })
    }
}
