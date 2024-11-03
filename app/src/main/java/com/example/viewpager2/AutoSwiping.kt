package com.example.viewpager2

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.viewpager2.data.ImageData
import com.example.viewpager2.databinding.ActivityAutoSwipingBinding

class AutoSwiping : AppCompatActivity() {
    private lateinit var binding: ActivityAutoSwipingBinding
    private lateinit var adapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityAutoSwipingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dotsIndicator = binding.dotsIndicator
        adapter = ViewPagerAdapter(ImageData.getImages())
        binding.viewPager2AutoSwiping.adapter = adapter
        dotsIndicator.attachTo(binding.viewPager2AutoSwiping)

        startAutoSwipe()
        binding.viewPager2AutoSwiping.registerOnPageChangeCallback(onPageChangeCallBack)

    }

    private fun startAutoSwipe() {
        stopAutoSwipe()
        autoSwipeHandler.postDelayed(autoSwipeRunnable, 3000)
    }

    private var autoSwipeHandler = Handler(Looper.getMainLooper())
    private var autoSwipeRunnable = object :Runnable {
        override fun run() {
            val  currentItem = binding.viewPager2AutoSwiping.currentItem
            if (binding.viewPager2AutoSwiping.adapter !=null){
                val nextItem = (currentItem  + 1 ) % binding.viewPager2AutoSwiping.adapter!!.itemCount
                binding.viewPager2AutoSwiping.setCurrentItem(nextItem,true)
                autoSwipeHandler.postDelayed(this,3000)
            }
            startAutoSwipe()
        }
    }

    private fun stopAutoSwipe() {
        autoSwipeHandler.removeCallbacks(autoSwipeRunnable)
    }


    private val onPageChangeCallBack = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrollStateChanged(state: Int) {
            super.onPageScrollStateChanged(state)
            if (state == ViewPager2.SCROLL_STATE_DRAGGING) {
                stopAutoSwipe()
            } else if (state == ViewPager2.SCROLL_STATE_IDLE) {
                startAutoSwipe()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopAutoSwipe()
        binding.viewPager2AutoSwiping.unregisterOnPageChangeCallback(onPageChangeCallBack)
    }

    override fun onResume() {
        super.onResume()
        startAutoSwipe()
    }
    override fun onPause() {
        super.onPause()
        stopAutoSwipe()

    }
}