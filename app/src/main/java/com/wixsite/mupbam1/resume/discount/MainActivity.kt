package com.wixsite.mupbam1.resume.discount

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

val priceData= listOf<Int>(5,100,20,66,16)
val resultData= mutableListOf<Int>()
val discount=50
val offset=1
val readLength=3

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        discountPriceData()
        Log.d("MyLog","resultData-$resultData")
    }

    private fun discountPriceData() {
        val position=offset+ readLength-1

        if (priceData.size>=position){
            for (index in offset..position){
                var discountPrice=priceData[index]* discount/100

                resultData.add(discountPrice)

            }
        }

    }

}