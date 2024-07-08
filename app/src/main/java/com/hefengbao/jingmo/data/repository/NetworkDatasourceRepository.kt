/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.common.network.Result
import com.hefengbao.jingmo.data.database.entity.ChineseExpressionEntity
import com.hefengbao.jingmo.data.database.entity.ChineseKnowledgeEntity
import com.hefengbao.jingmo.data.database.entity.ChineseWisecrackEntity
import com.hefengbao.jingmo.data.database.entity.ClassicPoemEntity
import com.hefengbao.jingmo.data.database.entity.DictionaryEntity
import com.hefengbao.jingmo.data.database.entity.DictionaryPinyinEntity
import com.hefengbao.jingmo.data.database.entity.IdiomEntity
import com.hefengbao.jingmo.data.database.entity.PeopleEntity
import com.hefengbao.jingmo.data.database.entity.PoemSentenceEntity
import com.hefengbao.jingmo.data.database.entity.RiddleEntity
import com.hefengbao.jingmo.data.database.entity.TongueTwisterEntity
import com.hefengbao.jingmo.data.database.entity.WritingEntity
import com.hefengbao.jingmo.data.model.ChineseExpressionWrapper
import com.hefengbao.jingmo.data.model.ChineseKnowledge
import com.hefengbao.jingmo.data.model.ChineseWisecrack
import com.hefengbao.jingmo.data.model.ClassicPoem
import com.hefengbao.jingmo.data.model.Dataset
import com.hefengbao.jingmo.data.model.DictionaryWrapper
import com.hefengbao.jingmo.data.model.IdiomWrapper
import com.hefengbao.jingmo.data.model.PeopleWrapper
import com.hefengbao.jingmo.data.model.PoemSentence
import com.hefengbao.jingmo.data.model.Riddle
import com.hefengbao.jingmo.data.model.TongueTwister
import com.hefengbao.jingmo.data.model.WritingWrapper

interface NetworkDatasourceRepository {
    suspend fun dataset(): Result<List<Dataset>>
    suspend fun syncChineseExpression(page: Int): Result<ChineseExpressionWrapper>
    suspend fun insertChineseExpression(entity: ChineseExpressionEntity)
    suspend fun syncChineseKnowledge(): Result<List<ChineseKnowledge>>
    suspend fun insertChinesKnowledge(entity: ChineseKnowledgeEntity)
    suspend fun syncChineseWisecracks(): Result<List<ChineseWisecrack>>
    suspend fun insertChineseWisecrack(entity: ChineseWisecrackEntity)
    suspend fun syncClassicPoems(): Result<List<ClassicPoem>>
    suspend fun insertClassicPoems(entity: ClassicPoemEntity)
    suspend fun syncDictionary(page: Int): Result<DictionaryWrapper>
    suspend fun insertDictionary(entity: DictionaryEntity)
    suspend fun insertDictionaryPinyin(entity: DictionaryPinyinEntity)
    suspend fun clearDictionaryPinyin()
    suspend fun syncIdioms(page: Int): Result<IdiomWrapper>
    suspend fun insertIdiom(entity: IdiomEntity)
    suspend fun syncPeople(page: Int): Result<PeopleWrapper>
    suspend fun insertPeople(entity: PeopleEntity)
    suspend fun syncPoemSentences(): Result<List<PoemSentence>>
    suspend fun insertPoemSentence(entity: PoemSentenceEntity)
    suspend fun syncRiddles(): Result<List<Riddle>>
    suspend fun insertRiddle(entity: RiddleEntity)
    suspend fun syncTongueTwisters(): Result<List<TongueTwister>>
    suspend fun insertTongueTwister(entity: TongueTwisterEntity)
    suspend fun syncWritings(page: Int): Result<WritingWrapper>
    suspend fun insertWriting(entity: WritingEntity)
}