package com.example.newtransition.network

import com.example.newtransition.model.LiveNewsList
import com.example.newtransition.model.Poster
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface APIService {

    /*http://newsapi.org/v2/everything?q=bitcoin&from=2020-07-28&sortBy=publishedAt&apiKey=b0af0c0f85d54286a55e78d7f21cc9ca*/
    /** shopOwnerMaps **/
  /*  @GET("everything")
     fun shopOwnerMaps(
        @Query("q") bitcoin: String, @Query("from")from:String,@Query("sortBy")sortBy:String,@Query("apiKey")apiKey:String
    ): Response<LiveNewsList>*/
    @GET("DisneyPosters.json")
    fun poster(): Call<Poster?>?

/*q:bitcoin
from:2020-07-28
sortBy:publishedAt
apiKey:b0af0c0f85d54286a55e78d7f21cc9ca*/
    @GET("v2/everything")
    fun getNews(
        @Query("q") bitcoin: String?,
        @Query("from") date: String?,
        @Query("sortBy") publishedAt: String?,
        @Query("apiKey") apikey: String?
    ): Call<LiveNewsList?>?

}