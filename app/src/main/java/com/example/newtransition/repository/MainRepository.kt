package com.example.newtransition.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.newtransition.model.LiveNewsList
import com.example.newtransition.model.Poster
import com.example.newtransition.network.APIService
import com.example.newtransition.network.ApiNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainRepository(context: Context) {
    var totalOrderValuesResponse = MutableLiveData<LiveNewsList>()
    var posterlist = MutableLiveData<Poster>()

/*/*q:bitcoin
from:2020-07-28
sortBy:publishedAt
apiKey:b0af0c0f85d54286a55e78d7f21cc9ca*/*/
    fun getnewslist(){
        val apiService =ApiNetwork.client.create(APIService::class.java)

        val requestCall = apiService.getNews("bitcoin","2020-07-29","publishedAt","b0af0c0f85d54286a55e78d7f21cc9ca")

        requestCall?.enqueue(object : Callback<LiveNewsList?> {
            override fun onResponse(
                call: Call<LiveNewsList?>, response: Response<LiveNewsList?>
            ) {
                val responsebody = response.body()
                if (response.isSuccessful) {
                    if (response.code() == 200 && response.body() != null) {
                        if (responsebody != null) {
                            if (responsebody.getStatus() == "ok") {

                                totalOrderValuesResponse.value = responsebody

                                Log.d("TAG", "onResponse: otp::" + responsebody)

                            } else {
                                print(responsebody.getStatus())


                            }

                        }


                    }
                }

            }


            override fun onFailure(call: Call<LiveNewsList?>, t: Throwable) {
                print(t.message.toString())
            }


        })
    }

    fun discountstructureone() {


        val apiService =ApiNetwork.client.create(APIService::class.java)
        val requestCall = apiService.poster()
        requestCall?.enqueue(object : Callback<Poster?> {

            override fun onResponse(
                call: Call<Poster?>, response: Response<Poster?>
            ) {
                val responsebody = response.body()

                if (response.isSuccessful) {
                    if (response.code() == 200 && response.body() != null) {
                        if (responsebody != null) {
                            posterlist.value = responsebody


                        }


                    }
                }

            }

            override fun onFailure(call: Call<Poster?>, t: Throwable) {
                Log.d("TAG", "onFailure: " + t.message)

            }

        })

    }
}