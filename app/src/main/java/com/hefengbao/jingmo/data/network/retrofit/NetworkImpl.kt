package com.hefengbao.jingmo.data.network.retrofit

import com.hefengbao.jingmo.data.model.ChineseKnowledge
import com.hefengbao.jingmo.data.model.ChineseWisecrack
import com.hefengbao.jingmo.data.model.ClassicPoem
import com.hefengbao.jingmo.data.model.Dataset
import com.hefengbao.jingmo.data.model.Idiom
import com.hefengbao.jingmo.data.model.PeopleWrapper
import com.hefengbao.jingmo.data.model.PoemSentence
import com.hefengbao.jingmo.data.model.Riddle
import com.hefengbao.jingmo.data.model.TongueTwister
import com.hefengbao.jingmo.data.model.WritingWrapper
import com.hefengbao.jingmo.data.network.Network
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

private const val baseUrl = "https://hefengbao.github.io/jingmo-data/api/"
private const val baseUrl2 = "https://hefengbao.github.io/jingmo-data2/api/"
private const val baseUrl3 = "https://hefengbao.github.io/jingmo-data3/api/"

@Singleton
class NetworkImpl @Inject constructor(
    networkJson: Json,
    okhttpCallFactory: Call.Factory
) : Network {

    private val networkApi = Retrofit.Builder()
        .baseUrl(baseUrl)
        .callFactory(okhttpCallFactory)
        .addConverterFactory(
            networkJson.asConverterFactory("application/json".toMediaType())
        ).build()
        .create(Api::class.java)

    private val networkApi2 = Retrofit.Builder()
        .baseUrl(baseUrl2)
        .callFactory(okhttpCallFactory)
        .addConverterFactory(
            networkJson.asConverterFactory("application/json".toMediaType())
        ).build()
        .create(Api::class.java)

    private val networkApi3 = Retrofit.Builder()
        .baseUrl(baseUrl3)
        .callFactory(okhttpCallFactory)
        .addConverterFactory(
            networkJson.asConverterFactory("application/json".toMediaType())
        ).build()
        .create(Api::class.java)

    override suspend fun dataset(): List<Dataset> = networkApi.dataset()
    override suspend fun chineseKnowledge(): List<ChineseKnowledge> = networkApi.chineseKnowledge()
    override suspend fun chineseWisecracks(): List<ChineseWisecrack> =
        networkApi.chineseWisecracks()

    override suspend fun classicPoems(): List<ClassicPoem> = networkApi.classicPoems()
    override suspend fun idioms(): List<Idiom> = networkApi.idioms()
    override suspend fun people(page: Int): PeopleWrapper = networkApi.people(page)
    override suspend fun poemSentences(): List<PoemSentence> = networkApi.poemSentences()
    override suspend fun riddles(): List<Riddle> = networkApi.riddles()
    override suspend fun tongueTwisters(): List<TongueTwister> = networkApi.tongueTwisters()
    override suspend fun writings(page: Int): WritingWrapper = networkApi3.writings(page)
}