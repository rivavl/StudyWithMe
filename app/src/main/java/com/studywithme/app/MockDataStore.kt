package com.studywithme.app

import com.google.gson.GsonBuilder
import com.studywithme.app.objects.room.RoomDto
import com.studywithme.app.present.models.MockAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MockDataStore {
    private val gson = GsonBuilder()
        .create()

    private val gsonConverter = GsonConverterFactory.create(gson)

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://6161de9737492500176314c6.mockapi.io/api/develop/v1/")
        .addConverterFactory(gsonConverter)
        .build()

    private val mockApi = retrofit.create(MockAPI::class.java)

    suspend fun postRoom(room: RoomDto): RoomDto = withContext(Dispatchers.IO) {
        return@withContext mockApi.postRoom(room).execute().body() ?: error("error")
    }
}
