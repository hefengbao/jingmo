/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.network

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

interface Network {
    suspend fun dataset(): List<Dataset>
    suspend fun chinaWorldCultureHeritages(
        version: Int,
        page: Int
    ): DataWrapper<WorldCulturalHeritage>

    suspend fun chineseAntitheticalCouplets(
        version: Int,
        page: Int
    ): DataWrapper<AntitheticalCouplet>

    suspend fun chineseExpressions(version: Int, page: Int): DataWrapper<Expression>
    suspend fun chineseKnowledge(version: Int, page: Int): DataWrapper<ChineseKnowledge>
    suspend fun chineseModernPoetry(version: Int, page: Int): DataWrapper<ModernPoetry>
    suspend fun chineseQuotes(version: Int, page: Int): DataWrapper<Quote>
    suspend fun chineseWisecracks(version: Int, page: Int): DataWrapper<Wisecrack>
    suspend fun chineseCharacters(version: Int, page: Int): DataWrapper<Character>
    suspend fun chineseLyrics(version: Int, page: Int): DataWrapper<Lyric>
    suspend fun chineseIdioms(version: Int, page: Int): DataWrapper<Idiom>
    suspend fun chineseProverbs(version: Int, page: Int): DataWrapper<Proverb>
    suspend fun chineseRiddles(version: Int, page: Int): DataWrapper<Riddle>
    suspend fun chineseTongueTwisters(version: Int, page: Int): DataWrapper<TongueTwister>
    suspend fun classicalLiteratureClassicPoems(version: Int, page: Int): DataWrapper<ClassicPoem>
    suspend fun classicalLiteraturePeople(version: Int, page: Int): DataWrapper<People>
    suspend fun classicalLiteratureSentences(version: Int, page: Int): DataWrapper<Sentence>
    suspend fun classicalLiteratureWritings(version: Int, page: Int): DataWrapper<Writing>
}