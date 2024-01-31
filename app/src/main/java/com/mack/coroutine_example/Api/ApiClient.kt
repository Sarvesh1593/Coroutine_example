package com.mack.coroutine_example.Api

import retrofit2.Response
import retrofit2.http.GET

interface ApiClient {
    @GET("/api/breeds/image/random")
    suspend fun getRandomDogImage(): Response<DogImageModel>
}