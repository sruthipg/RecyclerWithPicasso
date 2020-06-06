package com.example.accubitsapp.network

import com.example.accubitsapp.db.model.movie.MovieResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MyApi {


    @GET("upcoming?api_key=9c0523bff54071c4fb4b716a950231b9&language=en-US&page=1&region=IN|US&with_release_type=2|3")
    suspend fun getMovie() : Response<MovieResponse>

    companion object{
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ) : MyApi{

            val okkHttpclient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okkHttpclient)
                .baseUrl("https://api.themoviedb.org/3/movie/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory()) // <- Check this line
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }

}

