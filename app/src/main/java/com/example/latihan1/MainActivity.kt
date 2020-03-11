package com.example.latihan1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {


                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_main)
        //val value konstanta
        //var value
        //         val kota : String="Surabaya"
        //            kota="Malang"


        //if else
//      var jam =12
//
//      if (jam <= 12){
//          Log.i("Hasil","Selamat Pagi")
//      }else{
//          Log.i("Hasil", "Selamat Malam")
//      }
//

        //if else nilai n tampil
       /* var nilai = 76
        if(nilai >=90 && nilai <=100){
            Log.i("Hasil","Nilai Anak A")
            tampil.text="Nilai Anda A"
        }else if(nilai >=80 && nilai <=89){
            Log.i("Hasil","Nilai Anak B")
            tampil.text="Nilai Anda B"
        }else if(nilai >=70 && nilai <=89){
            Log.i("Hasil","Nilai Anak C")
            tampil.text="Nilai Anda C"
        }else if(nilai >=60 && nilai <=79) {
            Log.i("Hasil", "Nilai Anak D")
            tampil.text="Nilai Anda D"
        }else{
            Log.i("Hasil","Nilai Anak E")
            tampil.text="Nilai Anda D"
        }
        */
        //looping for menampilkan 0-10
        /*for (x in 0..10)
            Log.i("Hasil","$x")*/

        //looping while 1-5
        /*var i =1
        while (i <= 5){
            Log.i("Hasil","$i")
            i++
        }*/

        //looping do while
        /* var num = 2
        var i = 1

        do {
            Log.i("Hasil","2 * $i = "+num * i)
            i++
        }while(i < 11)*/

        //tampil memanggil
        //tampil()

       /* var a = 5
        var b = 7

        penjumlahan(a,b)*/

        var a = 8
        var b = 3
        var c = 5

        pembagian(a,b,c)
    }

   /* fun tampil(){
        Log.i("Hasil","A")*/

   /* fun penjumlahan (a:Int, b:Int){
        var  c = a+b
        Log.i("Hasil","$c")
    }*/

    fun pembagian(a:Int,b:Int,c:Int) {

        var d = (a * b - 10)/c
        Log.i("Hasil","$d")
    }
}
