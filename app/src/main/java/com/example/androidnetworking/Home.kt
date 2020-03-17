package com.example.androidnetworking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.synnapps.carouselview.ImageListener
import kotlinx.android.synthetic.main.activity_home.*

class Home : AppCompatActivity() {
    private val images = intArrayOf(R.drawable.images, R.drawable.images2, R.drawable.images3, R.drawable.images4)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val carousel1 = carousel
        carousel1.setPageCount(images.size)
        carousel1.setImageListener(ImageListener { position, imageView -> imageView.setImageResource(images[position]) })

        val context = this
        jadwalsholat.setOnClickListener() {
            val Intent = Intent(context, MainActivity::class.java)
            startActivity(Intent)
            finish()
        }

        alamat.setOnClickListener() {
            val Intent = Intent(context, identitas_masjid::class.java)
            startActivity(Intent)
            finish()
        }

        marquesholat.setOnClickListener() {
            val Intent = Intent(context, marque_masjid::class.java)
            startActivity(Intent)
            finish()
        }

        pengumuman.setOnClickListener() {
            val Intent = Intent(context, pengumuman_masjid::class.java)
            startActivity(Intent)
            finish()
        }

        tagline.setOnClickListener() {
            val Intent = Intent(context, tagline_masjid::class.java)
            startActivity(Intent)
            finish()
        }

        slideshow.setOnClickListener() {
            val Intent = Intent(context, slideshow_masjid::class.java)
            startActivity(Intent)
            finish()
        }
    }
}
