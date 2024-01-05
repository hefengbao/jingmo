package com.hefengbao.jingmo.data.network.retrofit

import com.hefengbao.jingmo.data.model.Riddle
import retrofit2.http.GET

interface Api {
    @GET("riddles.json")
    suspend fun riddles(): List<Riddle>
}