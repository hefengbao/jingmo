/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.network

import com.hefengbao.jingmo.data.model.Dataset
import com.hefengbao.jingmo.data.model.chinese.ChineseKnowledge
import com.hefengbao.jingmo.data.model.chinese.ChineseWisecrack
import com.hefengbao.jingmo.data.model.chinese.DictionaryWrapper
import com.hefengbao.jingmo.data.model.chinese.ExpressionWrapper
import com.hefengbao.jingmo.data.model.chinese.IdiomWrapper
import com.hefengbao.jingmo.data.model.chinese.Lyric
import com.hefengbao.jingmo.data.model.chinese.Proverb
import com.hefengbao.jingmo.data.model.chinese.Riddle
import com.hefengbao.jingmo.data.model.chinese.TongueTwister
import com.hefengbao.jingmo.data.model.classicalliterature.ClassicPoem
import com.hefengbao.jingmo.data.model.classicalliterature.PeopleWrapper
import com.hefengbao.jingmo.data.model.classicalliterature.PoemSentence
import com.hefengbao.jingmo.data.model.classicalliterature.WritingWrapper

interface Network {
    suspend fun dataset(): List<Dataset>
    suspend fun chineseExpressions(page: Int): ExpressionWrapper
    suspend fun chineseKnowledge(): List<ChineseKnowledge>
    suspend fun chineseWisecracks(): List<ChineseWisecrack>
    suspend fun classicPoems(): List<ClassicPoem>
    suspend fun dictionary(page: Int): DictionaryWrapper
    suspend fun lyrics(): List<Lyric>
    suspend fun idioms(page: Int): IdiomWrapper
    suspend fun people(page: Int): PeopleWrapper
    suspend fun poemSentences(): List<PoemSentence>
    suspend fun chineseProverbs(): List<Proverb>
    suspend fun riddles(): List<Riddle>
    suspend fun tongueTwisters(): List<TongueTwister>
    suspend fun writings(page: Int): WritingWrapper
}