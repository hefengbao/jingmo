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
import com.hefengbao.jingmo.data.database.entity.chinese.AntitheticalCoupletEntity
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
import com.hefengbao.jingmo.data.model.chinese.AntitheticalCouplet
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
    suspend fun insertChineseAntitheticalCouplet(entity: AntitheticalCoupletEntity)
    suspend fun syncChineseAntitheticalCouplets(): Result<List<AntitheticalCouplet>>
    suspend fun clearChineseDictionaryPinyin()
    suspend fun insertChineseDictionary(entity: DictionaryEntity)
    suspend fun insertChineseDictionaryPinyin(entity: DictionaryPinyinEntity)
    suspend fun syncChineseDictionary(page: Int): Result<DictionaryWrapper>
    suspend fun insertChineseExpression(entity: ExpressionEntity)
    suspend fun syncChineseExpressions(page: Int): Result<ExpressionWrapper>
    suspend fun insertChineseIdiom(entity: IdiomEntity)
    suspend fun syncChineseIdioms(page: Int): Result<IdiomWrapper>
    suspend fun insertChinesKnowledge(entity: KnowledgeEntity)
    suspend fun syncChineseKnowledge(): Result<List<ChineseKnowledge>>
    suspend fun insertChineseLyric(entity: LyricEntity)
    suspend fun syncChineseLyrics(): Result<List<Lyric>>
    suspend fun insertChineseProverb(entity: ProverbEntity)
    suspend fun syncChineseProverbs(): Result<List<Proverb>>
    suspend fun insertChineseRiddle(entity: RiddleEntity)
    suspend fun syncChineseRiddles(): Result<List<Riddle>>
    suspend fun insertChineseTongueTwister(entity: TongueTwisterEntity)
    suspend fun syncChineseTongueTwisters(): Result<List<TongueTwister>>
    suspend fun insertChineseWisecrack(entity: WisecrackEntity)
    suspend fun syncChineseWisecracks(): Result<List<ChineseWisecrack>>
    suspend fun insertClassicalLiteratureClassicPoem(entity: ClassicPoemEntity)
    suspend fun syncClassicalLiteratureClassicPoems(): Result<List<ClassicPoem>>
    suspend fun insertClassicalLiteraturePeople(entity: PeopleEntity)
    suspend fun syncClassicalLiteraturePeople(page: Int): Result<PeopleWrapper>
    suspend fun insertClassicalLiteratureSentence(entity: SentenceEntity)
    suspend fun syncClassicalLiteratureSentence(): Result<List<PoemSentence>>
    suspend fun insertClassicalLiteratureWriting(entity: WritingEntity)
    suspend fun syncClassicalLiteratureWritings(page: Int): Result<WritingWrapper>
}