/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.repository.settings

import com.hefengbao.jingmo.data.database.entity.chinese.DictionaryEntity
import com.hefengbao.jingmo.data.database.entity.chinese.DictionaryPinyinEntity
import com.hefengbao.jingmo.data.database.entity.chinese.ExpressionEntity
import com.hefengbao.jingmo.data.database.entity.chinese.IdiomEntity
import com.hefengbao.jingmo.data.database.entity.chinese.KnowledgeEntity
import com.hefengbao.jingmo.data.database.entity.chinese.LyricEntity
import com.hefengbao.jingmo.data.database.entity.chinese.TongueTwisterEntity
import com.hefengbao.jingmo.data.database.entity.chinese.WisecrackEntity
import com.hefengbao.jingmo.data.database.entity.classicalliterature.ClassicPoemEntity
import com.hefengbao.jingmo.data.database.entity.classicalliterature.PeopleEntity
import com.hefengbao.jingmo.data.database.entity.classicalliterature.SentenceEntity
import com.hefengbao.jingmo.data.database.entity.classicalliterature.WritingEntity
import kotlinx.coroutines.flow.Flow

interface ImportRepository {
    suspend fun insertChineseExpression(entity: ExpressionEntity)
    suspend fun insertChineseWisecrack(entity: WisecrackEntity)
    suspend fun insertChineseKnowledge(entity: KnowledgeEntity)
    suspend fun insertClassicPoem(entity: ClassicPoemEntity)
    suspend fun insertDictionary(entity: DictionaryEntity)
    suspend fun insertDictionaryPinyin(entity: DictionaryPinyinEntity)
    suspend fun insertIdiom(entity: IdiomEntity)
    suspend fun insertLyric(entity: LyricEntity)
    suspend fun insertPeople(entity: PeopleEntity)
    suspend fun insertPoemSentence(entity: SentenceEntity)
    suspend fun insertTongueTwister(entity: TongueTwisterEntity)
    suspend fun insertWriting(entity: WritingEntity)
    fun chineseExpressionTotal(): Flow<Int>
    fun chineseWisecrackTotal(): Flow<Int>
    fun chineseKnowledgeTotal(): Flow<Int>
    fun classicPoemTotal(): Flow<Int>
    fun dictionaryTotal(): Flow<Int>
    fun idiomsTotal(): Flow<Int>
    fun lyricTotal(): Flow<Int>
    fun peopleTotal(): Flow<Int>
    fun poemSentencesTotal(): Flow<Int>
    fun tongueTwistersTotal(): Flow<Int>
    fun writingsTotal(): Flow<Int>
}