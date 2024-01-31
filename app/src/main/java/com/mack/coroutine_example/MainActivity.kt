package com.mack.coroutine_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import coil.api.load
import com.mack.coroutine_example.Api.ApiAdapter
import com.mack.coroutine_example.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(),CoroutineScope by MainScope() {
    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        binding!!.btnGetImage.setOnClickListener {
            launch(Dispatchers.Main) {
                // Try catch block to handle the error exception
                try {
                    val response = ApiAdapter.apiClient.getRandomDogImage()
                    // check if response is successful
                    if(response.isSuccessful && response.body() != null){
                        val data = response.body()
                        data!!.message?.let{imageUrl ->
                            // Load URL into the ImageView using Coil
                            binding!!.ivDogImage.load(imageUrl)
                        }
                    }else{
                        Toast.makeText(this@MainActivity,"Error Occurred : ${response.message()}",Toast.LENGTH_SHORT).show()
                    }
                }catch (e:Exception){
                    Toast.makeText(this@MainActivity,"Error Occurred : ${e.message}",Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}