package org.school.demoapp.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.school.demoapp.data.WallPapers
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MyApi
{

    @GET("api/?key=18517190-acf0803de102698e40c86bacb")
    suspend fun getWallpapersList() : Response<WallPapers>

    @GET("api/?key=18517190-acf0803de102698e40c86bacb")
    suspend fun getCategoryWallpapersList( @Query("category") category : String ) : Response<WallPapers>

    companion object
    {
        var loggingInterceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpInterceptor = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

        operator fun invoke(): MyApi {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://pixabay.com/")
                .client(okHttpInterceptor)
                .build()
                .create(MyApi::class.java)
        }
    }
}