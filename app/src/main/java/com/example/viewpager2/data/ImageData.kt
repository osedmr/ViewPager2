package com.example.viewpager2.data

import com.example.viewpager2.R

object ImageData {
    fun getImages(): List<ImagesModel> =
        listOf(
            ImagesModel(R.drawable.popeyesslider),
            ImagesModel(R.drawable.arbys),
            ImagesModel(R.drawable.dominos),
            ImagesModel(R.drawable.tavukdoner),
            ImagesModel(R.drawable.doner),
            ImagesModel(R.drawable.evyemekleri)
        )
}