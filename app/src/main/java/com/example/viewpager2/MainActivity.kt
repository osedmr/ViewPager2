package com.example.viewpager2

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.viewpager2.data.ImageData
import com.example.viewpager2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dotsIndicator = binding.dotsIndicator
        adapter=ViewPagerAdapter(ImageData.getImages())
        binding.viewPager2.adapter=adapter
        dotsIndicator.attachTo(binding.viewPager2)

        binding.button.setOnClickListener {
            val intent = Intent(this,AutoSwiping::class.java)
            startActivity(intent)
        }


    }
}