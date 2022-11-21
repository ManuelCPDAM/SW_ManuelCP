package com.example.sw_manuelcp

import com.example.sw_manuelcp.ModeloDatos.StarWarResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getStarWar(@Url url:String): Response<StarWarResponse>
}