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
import com.hefengbao.jingmo.data.model.chinese.ModernPoetry
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

    @GET("china_world_cultural_heritage_v{version}.json")
    suspend fun chinaWorldCultureHeritage(
        @Path("version") version: Int,
    ): List<WorldCulturalHeritage>

    @GET("chinese_antithetical_couplet_v{version}.json")
    suspend fun chineseAntitheticalCouplet(
        @Path("version") version: Int,
    ): List<AntitheticalCouplet>

    @GET("chinese_dict_v{version}_{page}.json")
    suspend fun chineseDictionary(
        @Path("version") version: Int,
        @Path("page") page: Int
    ): DictionaryWrapper

    @GET("chinese_expressions_v{version}_{page}.json")
    suspend fun chineseExpressions(
        @Path("version") version: Int,
        @Path("page") page: Int
    ): ExpressionWrapper

    @GET("chinese_idioms_v{version}_{page}.json")
    suspend fun chineseIdioms(
        @Path("version") version: Int,
        @Path("page") page: Int
    ): IdiomWrapper

    @GET("chinese_knowledge_v{version}.json")
    suspend fun chineseKnowledge(
        @Path("version") version: Int,
    ): List<ChineseKnowledge>

    @GET("chinese_lyrics_v{version}.json")
    suspend fun chineseLyrics(
        @Path("version") version: Int,
    ): List<Lyric>

    @GET("chinese_modern_poetry_v{version}.json")
    suspend fun chineseModernPoetry(
        @Path("version") version: Int,
    ): List<ModernPoetry>

    @GET("classical_literature_classic_poems_v{version}.json")
    suspend fun classicalLiteratureClassicPoems(
        @Path("version") version: Int,
    ): List<ClassicPoem>

    @GET("chinese_proverbs_v{version}.json")
    suspend fun chineseProverbs(
        @Path("version") version: Int,
    ): List<Proverb>

    @GET("chinese_quotes_v{version}.json")
    suspend fun chineseQuotes(
        @Path("version") version: Int,
    ): List<Quote>

    @GET("chinese_riddles_v{version}.json")
    suspend fun chineseRiddles(
        @Path("version") version: Int,
    ): List<Riddle>

    @GET("chinese_tongue_twisters_v{version}.json")
    suspend fun chineseTongueTwisters(
        @Path("version") version: Int,
    ): List<TongueTwister>

    @GET("chinese_wisecracks_v{version}.json")
    suspend fun chineseWisecracks(
        @Path("version") version: Int,
    ): List<ChineseWisecrack>

    @GET("classical_literature_people_v{version}_{page}.json")
    suspend fun classicalLiteraturePeople(
        @Path("version") version: Int,
        @Path("page") page: Int
    ): PeopleWrapper

    @GET("classical_literature_sentences_v{version}.json")
    suspend fun classicalLiteratureSentences(
        @Path("version") version: Int,
    ): List<PoemSentence>

    @GET("classical_literature_writings_v{version}_{page}.json")
    suspend fun classicalLiteratureWritings(
        @Path("version") version: Int,
        @Path("page") page: Int
    ): WritingWrapper
}