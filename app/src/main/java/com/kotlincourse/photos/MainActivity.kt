package com.kotlincourse.photos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.kotlincourse.photos.Adapter.PhotosAdapter
import com.kotlincourse.photos.Models.PhotoContainer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

class MainActivity : AppCompatActivity() {

    private lateinit var client: OkHttpClient
    private lateinit var request: Request
    private lateinit var gson: Gson

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Creating Instances
        client = OkHttpClient()
        gson = Gson()

        recycler1.layoutManager = GridLayoutManager(this, 2)
      //  recycler1.layoutManager = LinearLayoutManager(this)

        runBlocking { getPhotos() }
    }

        //Suspend function because we're using coroutines
    // this function is for handling this service code
    private suspend fun getPhotos(){
        request = Request.Builder().url("https://2f56fd02-93f1-4033-a3e9-224277044942.mock.pstmn.io/getphotos").build()

        //Dispatcher IO is better for doing network request
        withContext(Dispatchers.IO){
            try {
                val response = client.newCall(request).execute()
                val result = response.body()?.string()
                val pasedResult = gson.fromJson(result, PhotoContainer::class.java)

                //setting the list to the Adapter - PhotoAdapter takes an Array list as a parameter and the arrayList is in the PhotoContainer that's why we take photoArray as the main parameter
                val rvAdaptor = PhotosAdapter(pasedResult.photos)
                //Set the adapter to Recycler View
                recycler1.adapter = rvAdaptor
            }
            catch (e: Exception){
                e.printStackTrace()

            }
        }

    }
}
