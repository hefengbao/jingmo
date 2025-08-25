/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.network.retrofit

import com.hefengbao.jingmo.BuildConfig
import com.hefengbao.jingmo.data.model.DataWrapper
import com.hefengbao.jingmo.data.model.Dataset
import com.hefengbao.jingmo.data.model.china.WorldCulturalHeritage
import com.hefengbao.jingmo.data.model.chinese.AntitheticalCouplet
import com.hefengbao.jingmo.data.model.chinese.Character
import com.hefengbao.jingmo.data.model.chinese.ChineseKnowledge
import com.hefengbao.jingmo.data.model.chinese.Expression
import com.hefengbao.jingmo.data.model.chinese.Idiom
import com.hefengbao.jingmo.data.model.chinese.Lyric
import com.hefengbao.jingmo.data.model.chinese.ModernPoetry
import com.hefengbao.jingmo.data.model.chinese.Proverb
import com.hefengbao.jingmo.data.model.chinese.Quote
import com.hefengbao.jingmo.data.model.chinese.Riddle
import com.hefengbao.jingmo.data.model.chinese.TongueTwister
import com.hefengbao.jingmo.data.model.chinese.Wisecrack
import com.hefengbao.jingmo.data.model.classicalliterature.ClassicPoem
import com.hefengbao.jingmo.data.model.classicalliterature.People
import com.hefengbao.jingmo.data.model.classicalliterature.Sentence
import com.hefengbao.jingmo.data.model.classicalliterature.Writing
import com.hefengbao.jingmo.data.network.Network
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkImpl @Inject constructor(
    networkJson: Json,
    okhttpCallFactory: Call.Factory
) : Network {

    private val networkApi1 = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL_1)
        .callFactory(okhttpCallFactory)
        .addConverterFactory(
            networkJson.asConverterFactory("application/json".toMediaType())
        ).build()
        .create(Api::class.java)

    private val networkApi2 = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL_2)
        .callFactory(okhttpCallFactory)
        .addConverterFactory(
            networkJson.asConverterFactory("application/json".toMediaType())
        ).build()
        .create(Api::class.java)

    private val networkApi3 = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL_3)
        .callFactory(okhttpCallFactory)
        .addConverterFactory(
            networkJson.asConverterFactory("application/json".toMediaType())
        ).build()
        .create(Api::class.java)

    override suspend fun dataset(): List<Dataset> = networkApi1.dataset()

    override suspend fun chinaWorldCultureHeritages(
        version: Int,
        page: Int
    ): DataWrapper<WorldCulturalHeritage> =
        networkApi1.chinaWorldCultureHeritage(version, page)

    override suspend fun chineseAntitheticalCouplets(
        version: Int,
        page: Int
    ): DataWrapper<AntitheticalCouplet> =
        networkApi1.chineseAntitheticalCouplet(version, page)

    override suspend fun chineseExpressions(version: Int, page: Int): DataWrapper<Expression> =
        networkApi1.chineseExpressions(version, page)

    override suspend fun chineseKnowledge(version: Int, page: Int): DataWrapper<ChineseKnowledge> =
        networkApi1.chineseKnowledge(version, page)

    override suspend fun chineseModernPoetry(version: Int, page: Int): DataWrapper<ModernPoetry> =
        networkApi1.chineseModernPoetry(version, page)

    override suspend fun chineseQuotes(version: Int, page: Int): DataWrapper<Quote> =
        networkApi1.chineseQuotes(version, page)

    override suspend fun chineseWisecracks(version: Int, page: Int): DataWrapper<Wisecrack> =
        networkApi1.chineseWisecracks(version, page)

    override suspend fun chineseCharacters(version: Int, page: Int): DataWrapper<Character> =
        networkApi1.chineseCharacter(version, page)

    override suspend fun chineseLyrics(version: Int, page: Int): DataWrapper<Lyric> =
        networkApi1.chineseLyrics(version, page)

    override suspend fun chineseIdioms(version: Int, page: Int): DataWrapper<Idiom> =
        networkApi1.chineseIdioms(version, page)

    override suspend fun chineseProverbs(version: Int, page: Int): DataWrapper<Proverb> =
        networkApi1.chineseProverbs(version, page)

    override suspend fun chineseRiddles(version: Int, page: Int): DataWrapper<Riddle> =
        networkApi1.chineseRiddles(version, page)

    override suspend fun chineseTongueTwisters(
        version: Int,
        page: Int
    ): DataWrapper<TongueTwister> =
        networkApi1.chineseTongueTwisters(version, page)

    override suspend fun classicalLiteratureClassicPoems(
        version: Int,
        page: Int
    ): DataWrapper<ClassicPoem> =
        networkApi1.classicalLiteratureClassicPoems(version, page)

    override suspend fun classicalLiteraturePeople(version: Int, page: Int): DataWrapper<People> =
        networkApi1.classicalLiteraturePeople(version, page)

    override suspend fun classicalLiteratureSentences(
        version: Int,
        page: Int
    ): DataWrapper<Sentence> =
        networkApi1.classicalLiteratureSentences(version, page)

    override suspend fun classicalLiteratureWritings(
        version: Int,
        page: Int
    ): DataWrapper<Writing> =
        networkApi3.classicalLiteratureWritings(version, page)
}