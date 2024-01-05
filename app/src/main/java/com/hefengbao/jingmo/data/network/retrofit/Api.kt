package com.hefengbao.jingmo.data.network.retrofit

import com.hefengbao.jingmo.data.model.People
import com.hefengbao.jingmo.data.model.Riddle
import com.hefengbao.jingmo.data.model.WritingWrapper
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("riddles.json")
    suspend fun riddles(): List<Riddle>
    @GET("people.json")
    suspend fun people(): List<People>
    @GET("writings_{page}.json")
    suspend fun writings(
        @Path("page") page: Int
    ): WritingWrapper
}