/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.network.retrofit

import com.hefengbao.jingmo.data.model.Dataset
import com.hefengbao.jingmo.data.model.china.WorldCulturalHeritage
import com.hefengbao.jingmo.data.model.chinese.AntitheticalCouplet
import com.hefengbao.jingmo.data.model.chinese.ChineseKnowledge
import com.hefengbao.jingmo.data.model.chinese.ChineseWisecrack
import com.hefengbao.jingmo.data.model.chinese.DictionaryWrapper
import com.hefengbao.jingmo.data.model.chinese.ExpressionWrapper
import com.hefengbao.jingmo.data.model.chinese.IdiomWrapper
import com.hefengbao.jingmo.data.model.chinese.Lyric
import com.hefengbao.jingmo.data.model.chinese.Proverb
import com.hefengbao.jingmo.data.model.chinese.Quote
import com.hefengbao.jingmo.data.model.chinese.Riddle
import com.hefengbao.jingmo.data.model.chinese.TongueTwister
import com.hefengbao.jingmo.data.model.classicalliterature.ClassicPoem
import com.hefengbao.jingmo.data.model.classicalliterature.PeopleWrapper
import com.hefengbao.jingmo.data.model.classicalliterature.PoemSentence
import com.hefengbao.jingmo.data.model.classicalliterature.WritingWrapper
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("dataset.json")
    suspend fun dataset(): List<Dataset>

    @GET("china_world_cultural_heritage.json")
    suspend fun chinaWorldCultureHeritage(): List<WorldCulturalHeritage>

    @GET("expressions_{page}.json")
    suspend fun chineseExpressions(
        @Path("page") page: Int
    ): ExpressionWrapper

    @GET("chinese_antithetical_couplet.json")
    suspend fun chineseAntitheticalCouplet(): List<AntitheticalCouplet>

    @GET("chinese_knowledge.json")
    suspend fun chineseKnowledge(): List<ChineseKnowledge>

    @GET("chinese_wisecracks.json")
    suspend fun chineseWisecracks(): List<ChineseWisecrack>

    @GET("chinese_quotes.json")
    suspend fun chineseQuotes(): List<Quote>

    @GET("classic_poems.json")
    suspend fun classicPoems(): List<ClassicPoem>

    @GET("dict_{page}.json")
    suspend fun dictionary(
        @Path("page") page: Int
    ): DictionaryWrapper

    @GET("idioms_v2_{page}.json")
    suspend fun idioms(
        @Path("page") page: Int
    ): IdiomWrapper

    @GET("lyrics.json")
    suspend fun lyrics(): List<Lyric>

    @GET("people_{page}.json")
    suspend fun people(
        @Path("page") page: Int
    ): PeopleWrapper

    @GET("proverbs.json")
    suspend fun chineseProverb(): List<Proverb>

    @GET("poem_sentences.json")
    suspend fun poemSentences(): List<PoemSentence>

    @GET("riddles.json")
    suspend fun riddles(): List<Riddle>

    @GET("tongue_twisters.json")
    suspend fun tongueTwisters(): List<TongueTwister>

    @GET("writings_{page}.json")
    suspend fun writings(
        @Path("page") page: Int
    ): WritingWrapper
}