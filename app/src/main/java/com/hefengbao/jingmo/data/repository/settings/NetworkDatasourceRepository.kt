/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.repository.settings

import com.hefengbao.jingmo.common.network.Result
import com.hefengbao.jingmo.data.database.entity.chinese.DictionaryEntity
import com.hefengbao.jingmo.data.database.entity.chinese.DictionaryPinyinEntity
import com.hefengbao.jingmo.data.database.entity.chinese.ExpressionEntity
import com.hefengbao.jingmo.data.database.entity.chinese.IdiomEntity
import com.hefengbao.jingmo.data.database.entity.chinese.KnowledgeEntity
import com.hefengbao.jingmo.data.database.entity.chinese.LyricEntity
import com.hefengbao.jingmo.data.database.entity.chinese.ProverbEntity
import com.hefengbao.jingmo.data.database.entity.chinese.RiddleEntity
import com.hefengbao.jingmo.data.database.entity.chinese.TongueTwisterEntity
import com.hefengbao.jingmo.data.database.entity.chinese.WisecrackEntity
import com.hefengbao.jingmo.data.database.entity.classicalliterature.ClassicPoemEntity
import com.hefengbao.jingmo.data.database.entity.classicalliterature.PeopleEntity
import com.hefengbao.jingmo.data.database.entity.classicalliterature.SentenceEntity
import com.hefengbao.jingmo.data.database.entity.classicalliterature.WritingEntity
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

interface NetworkDatasourceRepository {
    suspend fun dataset(): Result<List<Dataset>>
    suspend fun syncChineseExpression(page: Int): Result<ExpressionWrapper>
    suspend fun insertChineseExpression(entity: ExpressionEntity)
    suspend fun syncChineseKnowledge(): Result<List<ChineseKnowledge>>
    suspend fun insertChinesKnowledge(entity: KnowledgeEntity)
    suspend fun syncChineseProverbs(): Result<List<Proverb>>
    suspend fun insertChineseProverb(entity: ProverbEntity)
    suspend fun syncChineseWisecracks(): Result<List<ChineseWisecrack>>
    suspend fun insertChineseWisecrack(entity: WisecrackEntity)
    suspend fun syncClassicPoems(): Result<List<ClassicPoem>>
    suspend fun insertClassicPoems(entity: ClassicPoemEntity)
    suspend fun syncDictionary(page: Int): Result<DictionaryWrapper>
    suspend fun insertDictionary(entity: DictionaryEntity)
    suspend fun insertDictionaryPinyin(entity: DictionaryPinyinEntity)
    suspend fun clearDictionaryPinyin()
    suspend fun syncIdioms(page: Int): Result<IdiomWrapper>
    suspend fun insertIdiom(entity: IdiomEntity)
    suspend fun syncLyrics(): Result<List<Lyric>>
    suspend fun insertLyric(entity: LyricEntity)
    suspend fun syncPeople(page: Int): Result<PeopleWrapper>
    suspend fun insertPeople(entity: PeopleEntity)
    suspend fun syncPoemSentences(): Result<List<PoemSentence>>
    suspend fun insertPoemSentence(entity: SentenceEntity)
    suspend fun syncRiddles(): Result<List<Riddle>>
    suspend fun insertRiddle(entity: RiddleEntity)
    suspend fun syncTongueTwisters(): Result<List<TongueTwister>>
    suspend fun insertTongueTwister(entity: TongueTwisterEntity)
    suspend fun syncWritings(page: Int): Result<WritingWrapper>
    suspend fun insertWriting(entity: WritingEntity)
}