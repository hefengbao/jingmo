/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.network.retrofit

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
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("dataset_v2.json")
    suspend fun dataset(): List<Dataset>

    @GET("china_worldcultureheritage_v{version}_{page}.json")
    suspend fun chinaWorldCultureHeritage(
        @Path("version") version: Int,
        @Path("page") page: Int
    ): DataWrapper<WorldCulturalHeritage>

    @GET("chinese_antitheticalcouplet_v{version}_{page}.json")
    suspend fun chineseAntitheticalCouplet(
        @Path("version") version: Int,
        @Path("page") page: Int
    ): DataWrapper<AntitheticalCouplet>

    @GET("chinese_character_v{version}_{page}.json")
    suspend fun chineseCharacter(
        @Path("version") version: Int,
        @Path("page") page: Int
    ): DataWrapper<Character>

    @GET("chinese_expression_v{version}_{page}.json")
    suspend fun chineseExpressions(
        @Path("version") version: Int,
        @Path("page") page: Int
    ): DataWrapper<Expression>

    @GET("chinese_idiom_v{version}_{page}.json")
    suspend fun chineseIdioms(
        @Path("version") version: Int,
        @Path("page") page: Int
    ): DataWrapper<Idiom>

    @GET("chinese_knowledge_v{version}_{page}.json")
    suspend fun chineseKnowledge(
        @Path("version") version: Int,
        @Path("page") page: Int
    ): DataWrapper<ChineseKnowledge>

    @GET("chinese_lyric_v{version}_{page}.json")
    suspend fun chineseLyrics(
        @Path("version") version: Int,
        @Path("page") page: Int
    ): DataWrapper<Lyric>

    @GET("chinese_modernpoetry_v{version}_{page}.json")
    suspend fun chineseModernPoetry(
        @Path("version") version: Int,
        @Path("page") page: Int
    ): DataWrapper<ModernPoetry>

    @GET("chinese_proverb_v{version}_{page}.json")
    suspend fun chineseProverbs(
        @Path("version") version: Int,
        @Path("page") page: Int
    ): DataWrapper<Proverb>

    @GET("chinese_quote_v{version}_{page}.json")
    suspend fun chineseQuotes(
        @Path("version") version: Int,
        @Path("page") page: Int
    ): DataWrapper<Quote>

    @GET("chinese_riddle_v{version}_{page}.json")
    suspend fun chineseRiddles(
        @Path("version") version: Int,
        @Path("page") page: Int
    ): DataWrapper<Riddle>

    @GET("chinese_tonguetwister_v{version}_{page}.json")
    suspend fun chineseTongueTwisters(
        @Path("version") version: Int,
        @Path("page") page: Int
    ): DataWrapper<TongueTwister>

    @GET("chinese_wisecrack_v{version}_{page}.json")
    suspend fun chineseWisecracks(
        @Path("version") version: Int,
        @Path("page") page: Int
    ): DataWrapper<Wisecrack>

    @GET("classicalliterature_classicpoem_v{version}_{page}.json")
    suspend fun classicalLiteratureClassicPoems(
        @Path("version") version: Int,
        @Path("page") page: Int
    ): DataWrapper<ClassicPoem>

    @GET("classicalliterature_people_v{version}_{page}.json")
    suspend fun classicalLiteraturePeople(
        @Path("version") version: Int,
        @Path("page") page: Int
    ): DataWrapper<People>

    @GET("classicalliterature_sentence_v{version}_{page}.json")
    suspend fun classicalLiteratureSentences(
        @Path("version") version: Int,
        @Path("page") page: Int
    ): DataWrapper<Sentence>

    @GET("classicalliterature_writing_v{version}_{page}.json")
    suspend fun classicalLiteratureWritings(
        @Path("version") version: Int,
        @Path("page") page: Int
    ): DataWrapper<Writing>
}