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
import com.hefengbao.jingmo.common.network.SafeApiCall
import com.hefengbao.jingmo.data.database.AppDatabase
import com.hefengbao.jingmo.data.database.entity.chinese.DictionaryEntity
import com.hefengbao.jingmo.data.database.entity.chinese.DictionaryPinyinEntity
import com.hefengbao.jingmo.data.database.entity.chinese.ExpressionEntity
import com.hefengbao.jingmo.data.database.entity.chinese.IdiomEntity
import com.hefengbao.jingmo.data.database.entity.chinese.KnowledgeEntity
import com.hefengbao.jingmo.data.database.entity.chinese.LyricEntity
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
import com.hefengbao.jingmo.data.model.chinese.Riddle
import com.hefengbao.jingmo.data.model.chinese.TongueTwister
import com.hefengbao.jingmo.data.model.classicalliterature.ClassicPoem
import com.hefengbao.jingmo.data.model.classicalliterature.PeopleWrapper
import com.hefengbao.jingmo.data.model.classicalliterature.PoemSentence
import com.hefengbao.jingmo.data.model.classicalliterature.WritingWrapper
import com.hefengbao.jingmo.data.network.Network
import javax.inject.Inject

class NetworkDatasourceRepositoryImpl @Inject constructor(
    private val network: Network,
    private val database: AppDatabase
) : NetworkDatasourceRepository, SafeApiCall {
    override suspend fun dataset(): Result<List<Dataset>> = safeApiCall {
        network.dataset()
    }

    override suspend fun syncChineseExpression(page: Int): Result<ExpressionWrapper> =
        safeApiCall {
            network.chineseExpressions(page)
        }

    override suspend fun insertChineseExpression(entity: ExpressionEntity) =
        database.expressionDao().insert(entity)

    override suspend fun syncChineseKnowledge(): Result<List<ChineseKnowledge>> = safeApiCall {
        network.chineseKnowledge()
    }

    override suspend fun insertChinesKnowledge(entity: KnowledgeEntity) {
        database.knowledgeDao().insert(entity)
    }

    override suspend fun syncChineseWisecracks(): Result<List<ChineseWisecrack>> = safeApiCall {
        network.chineseWisecracks()
    }

    override suspend fun insertChineseWisecrack(entity: WisecrackEntity) {
        database.wisecrackDao().insert(entity)
    }

    override suspend fun syncClassicPoems(): Result<List<ClassicPoem>> = safeApiCall {
        network.classicPoems()
    }

    override suspend fun insertClassicPoems(entity: ClassicPoemEntity) =
        database.classicPoemDao().insert(entity)

    override suspend fun syncDictionary(page: Int): Result<DictionaryWrapper> = safeApiCall {
        network.dictionary(page)
    }

    override suspend fun insertDictionary(entity: DictionaryEntity) =
        database.dictionaryDao().insert(entity)

    override suspend fun insertDictionaryPinyin(entity: DictionaryPinyinEntity) =
        database.dictionaryDao().insertDictionaryPinyin(entity)

    override suspend fun clearDictionaryPinyin() = database.dictionaryDao().clearDictionaryPinyin()

    override suspend fun syncIdioms(page: Int): Result<IdiomWrapper> = safeApiCall {
        network.idioms(page)
    }

    override suspend fun insertIdiom(entity: IdiomEntity) {
        database.idiomDao().insert(entity)
    }

    override suspend fun syncLyrics(): Result<List<Lyric>> = safeApiCall {
        network.lyrics()
    }

    override suspend fun insertLyric(entity: LyricEntity) = database.lyricDao().insert(entity)

    override suspend fun syncPeople(page: Int): Result<PeopleWrapper> = safeApiCall {
        network.people(page)
    }

    override suspend fun insertPeople(entity: PeopleEntity) {
        database.peopleDao().insert(entity)
    }

    override suspend fun syncPoemSentences(): Result<List<PoemSentence>> = safeApiCall {
        network.poemSentences()
    }

    override suspend fun insertPoemSentence(entity: SentenceEntity) {
        database.sentenceDao().insert(entity)
    }

    override suspend fun syncRiddles(): Result<List<Riddle>> = safeApiCall {
        network.riddles()
    }

    override suspend fun insertRiddle(entity: RiddleEntity) {
        database.riddleDao().insert(entity)
    }

    override suspend fun syncTongueTwisters(): Result<List<TongueTwister>> = safeApiCall {
        network.tongueTwisters()
    }

    override suspend fun insertTongueTwister(entity: TongueTwisterEntity) {
        database.tongueTwisterDao().insert(entity)
    }

    override suspend fun syncWritings(page: Int): Result<WritingWrapper> = safeApiCall {
        network.writings(page)
    }

    override suspend fun insertWriting(entity: WritingEntity) {
        database.writingDao().insert(entity)
    }

}