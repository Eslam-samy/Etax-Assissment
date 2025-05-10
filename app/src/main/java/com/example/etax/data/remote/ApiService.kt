package com.example.etax.data.remote

import com.example.etax.data.model.NowPlayingDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {



    //https://api.themoviedb.org/3/movie/now_playing?api_key=YOUR_API_KEY

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): NowPlayingDto

}