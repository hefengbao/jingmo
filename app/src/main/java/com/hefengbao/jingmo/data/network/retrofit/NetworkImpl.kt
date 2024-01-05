package com.hefengbao.jingmo.data.network.retrofit

import com.hefengbao.jingmo.data.model.People
import com.hefengbao.jingmo.data.model.Riddle
import com.hefengbao.jingmo.data.network.Network
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

private val baseUrl = "https://hefengbao.github.io/jingmo/api/"
@Singleton
class NetworkImpl @Inject constructor(
    networkJson: Json,
    okhttpCallFactory: Call.Factory
) : Network{

    private val networkApi = Retrofit.Builder()
        .baseUrl(baseUrl)
        .callFactory(okhttpCallFactory)
        .addConverterFactory(
            networkJson.asConverterFactory("application/json".toMediaType())
        ).build()
        .create(Api::class.java)

    override suspend fun riddles(): List<Riddle> = networkApi.riddles()
    override suspend fun people(): List<People> = networkApi.people()
}