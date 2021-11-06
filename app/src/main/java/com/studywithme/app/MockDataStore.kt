package com.studywithme.app

import com.studywithme.app.models.MockAPI
import com.studywithme.app.models.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MockDataStore {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://6161de9737492500176314c6.mockapi.io/api/develop/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val mockApi = retrofit.create(MockAPI::class.java)

    suspend fun postRoom(room: Room): Room = withContext(Dispatchers.IO) {
        return@withContext mockApi.postRoom(room).execute().body() ?: error("error")
    }
}
