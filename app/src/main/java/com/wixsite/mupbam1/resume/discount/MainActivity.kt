package com.wixsite.mupbam1.resume.discount
// https://www.youtube.com/watch?v=DvJDnw3HECg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.wixsite.mupbam1.resume.discount.databinding.ActivityMainBinding
import java.io.*


val priceData= mutableListOf<Int>()
val resultData= mutableListOf<Int>()
var offset=0
var readLength=0


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

           init()
    }

    fun init(){
        binding.apply {
            btRun.setOnClickListener {
                if (edOffset.text.isNotEmpty()&&edReadLength.text.isNotEmpty()) {
                  when(edDiscount.text.toString().toInt()){
                      in 1..99-> visib()
                      else -> Toast.makeText(this@MainActivity, "% скидки должен быть в диапазоне 1..99", Toast.LENGTH_SHORT).show()
                  }
                }
            }
        }
    }

    private fun visib() {
        binding.apply {
            offset = edOffset.text.toString().toInt()
            readLength = edReadLength.text.toString().toInt()
            edPrice.visibility = View.VISIBLE
            tvArray.visibility = View.VISIBLE
            tvResult.visibility = View.VISIBLE
            btEnter.visibility = View.VISIBLE
            btRun.visibility = View.GONE
            edDiscount.visibility = View.GONE
            edOffset.visibility = View.GONE
            edReadLength.visibility = View.GONE
        }

        discountPriceData()

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
                            val discount=edDiscount.text.toString().toInt()
                            Log.d("MyLog","discount $discount")

                            val discountPrice = priceData[priceData.size-1] *(100 -discount) / 100
                            resultData.add(discountPrice)

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