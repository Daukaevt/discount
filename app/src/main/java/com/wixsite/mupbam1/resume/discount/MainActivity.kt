package com.wixsite.mupbam1.resume.discount

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.wixsite.mupbam1.resume.discount.databinding.ActivityMainBinding

//val priceData= listOf<Int>(5,100,20,66,16)
val priceData= mutableListOf<Int>()
val resultData= mutableListOf<Int>()
val discount=50
var offset=0
var readLength=0

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.apply {
            btRun.setOnClickListener {
                if (edOffset.text.isNotEmpty()&&edReadLength.text.isNotEmpty()) {
                    offset = edOffset.text.toString().toInt()
                    readLength = edReadLength.text.toString().toInt()
                    edPrice.visibility = View.VISIBLE
                    tvArray.visibility = View.VISIBLE
                    tvResult.visibility = View.VISIBLE
                    btEnter.visibility = View.VISIBLE
                    btRun.visibility=View.GONE
                    edOffset.visibility=View.GONE
                    edReadLength.visibility=View.GONE

                    discountPriceData()

                }
            }
        }
    }

    private fun discountPriceData() {
        binding.apply {

            btEnter.setOnClickListener {
                if (binding.edPrice.text.isNotEmpty()){
                    priceData.add(edPrice.text.toString().toInt())
                    tvArray.text = priceData.toString()

                    val position = offset + readLength - 1

                    when (priceData.size)
                    {   in 0..offset-> Log.d("MyLog","more")
                        in offset+1..position+1-> {

                            var discountPrice = priceData[priceData.size-1] * discount / 100
                            resultData.add(discountPrice)
                            Log.d("MyLog", " resultData    $resultData")

                        }
                        else->    Log.d("MyLog","no more")
                    }

                    binding.tvResult.text = resultData.toString()
                    edPrice.getText().clear()
                }


            }
        }
    }

}