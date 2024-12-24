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
import com.hefengbao.jingmo.data.database.entity.china.WorldCulturalHeritageEntity
import com.hefengbao.jingmo.data.database.entity.chinese.AntitheticalCoupletEntity
import com.hefengbao.jingmo.data.database.entity.chinese.DictionaryEntity
import com.hefengbao.jingmo.data.database.entity.chinese.DictionaryPinyinEntity
import com.hefengbao.jingmo.data.database.entity.chinese.ExpressionEntity
import com.hefengbao.jingmo.data.database.entity.chinese.IdiomEntity
import com.hefengbao.jingmo.data.database.entity.chinese.KnowledgeEntity
import com.hefengbao.jingmo.data.database.entity.chinese.LyricEntity
import com.hefengbao.jingmo.data.database.entity.chinese.ModernPoetryEntity
import com.hefengbao.jingmo.data.database.entity.chinese.ProverbEntity
import com.hefengbao.jingmo.data.database.entity.chinese.QuoteEntity
import com.hefengbao.jingmo.data.database.entity.chinese.RiddleEntity
import com.hefengbao.jingmo.data.database.entity.chinese.TongueTwisterEntity
import com.hefengbao.jingmo.data.database.entity.chinese.WisecrackEntity
import com.hefengbao.jingmo.data.database.entity.classicalliterature.ClassicPoemEntity
import com.hefengbao.jingmo.data.database.entity.classicalliterature.PeopleEntity
import com.hefengbao.jingmo.data.database.entity.classicalliterature.SentenceEntity
import com.hefengbao.jingmo.data.database.entity.classicalliterature.WritingEntity
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
import com.hefengbao.jingmo.data.network.Network
import javax.inject.Inject

class NetworkDatasourceRepositoryImpl @Inject constructor(
    private val network: Network,
    private val database: AppDatabase
) : NetworkDatasourceRepository, SafeApiCall {
    override suspend fun dataset(): Result<List<Dataset>> = safeApiCall {
        network.dataset()
    }

    override suspend fun insertChinaWorldCultureHeritage(entity: WorldCulturalHeritageEntity) =
        database.worldCulturalHeritageDao().insert(entity)

    override suspend fun syncChinaWorldCultureHeritage(version: Int): Result<List<WorldCulturalHeritage>> =
        safeApiCall {
            network.chinaWorldCultureHeritage(version)
        }

    override suspend fun insertChineseAntitheticalCouplet(entity: AntitheticalCoupletEntity) =
        database.antitheticalCoupletDao().insert(entity)

    override suspend fun syncChineseAntitheticalCouplets(version: Int): Result<List<AntitheticalCouplet>> =
        safeApiCall {
            network.chineseAntitheticalCouplets(version)
        }

    override suspend fun clearChineseDictionaryPinyin() =
        database.dictionaryDao().clearDictionaryPinyin()

    override suspend fun insertChineseDictionary(entity: DictionaryEntity) =
        database.dictionaryDao().insert(entity)

    override suspend fun insertChineseDictionaryPinyin(entity: DictionaryPinyinEntity) =
        database.dictionaryDao().insertDictionaryPinyin(entity)

    override suspend fun syncChineseDictionary(version: Int, page: Int): Result<DictionaryWrapper> =
        safeApiCall {
            network.chineseDictionary(version, page)
        }

    override suspend fun insertChineseExpression(entity: ExpressionEntity) =
        database.expressionDao().insert(entity)

    override suspend fun syncChineseExpressions(
        version: Int,
        page: Int
    ): Result<ExpressionWrapper> =
        safeApiCall {
            network.chineseExpressions(version, page)
        }

    override suspend fun syncChineseIdioms(version: Int, page: Int): Result<IdiomWrapper> =
        safeApiCall {
            network.chineseIdioms(version, page)
        }

    override suspend fun insertChineseIdiom(entity: IdiomEntity) {
        database.idiomDao().insert(entity)
    }

    override suspend fun insertChinesKnowledge(entity: KnowledgeEntity) {
        database.knowledgeDao().insert(entity)
    }

    override suspend fun syncChineseKnowledge(version: Int): Result<List<ChineseKnowledge>> =
        safeApiCall {
            network.chineseKnowledge(version)
        }

    override suspend fun insertChineseLyric(entity: LyricEntity) =
        database.lyricDao().insert(entity)

    override suspend fun syncChineseLyrics(version: Int): Result<List<Lyric>> = safeApiCall {
        network.chineseLyrics(version)
    }

    override suspend fun insertChineseModernPoetry(entity: ModernPoetryEntity) =
        database.chineseModernPoetryDao().insert(entity)

    override suspend fun syncChineseModernPoetry(version: Int): Result<List<ModernPoetry>> =
        safeApiCall {
            network.chineseModernPoetry(version)
        }

    override suspend fun insertChineseProverb(entity: ProverbEntity) =
        database.proverbDao().insert(entity)

    override suspend fun syncChineseProverbs(version: Int): Result<List<Proverb>> = safeApiCall {
        network.chineseProverbs(version)
    }

    override suspend fun insertChineseQuote(entity: QuoteEntity) =
        database.chineseQuoteDao().insert(entity)

    override suspend fun syncChineseQuotes(version: Int): Result<List<Quote>> = safeApiCall {
        network.chineseQuotes(version)
    }

    override suspend fun insertChineseRiddle(entity: RiddleEntity) {
        database.riddleDao().insert(entity)
    }

    override suspend fun syncChineseRiddles(version: Int): Result<List<Riddle>> = safeApiCall {
        network.chineseRiddles(version)
    }

    override suspend fun insertChineseTongueTwister(entity: TongueTwisterEntity) {
        database.tongueTwisterDao().insert(entity)
    }

    override suspend fun syncChineseTongueTwisters(version: Int): Result<List<TongueTwister>> =
        safeApiCall {
            network.chineseTongueTwisters(version)
        }

    override suspend fun syncChineseWisecracks(version: Int): Result<List<ChineseWisecrack>> =
        safeApiCall {
            network.chineseWisecracks(version)
        }

    override suspend fun insertChineseWisecrack(entity: WisecrackEntity) {
        database.wisecrackDao().insert(entity)
    }

    override suspend fun insertClassicalLiteratureClassicPoem(entity: ClassicPoemEntity) =
        database.classicPoemDao().insert(entity)

    override suspend fun syncClassicalLiteratureClassicPoems(version: Int): Result<List<ClassicPoem>> =
        safeApiCall {
            network.classicalLiteratureClassicPoems(version)
        }

    override suspend fun insertClassicalLiteraturePeople(entity: PeopleEntity) {
        database.peopleDao().insert(entity)
    }

    override suspend fun syncClassicalLiteraturePeople(
        version: Int,
        page: Int
    ): Result<PeopleWrapper> =
        safeApiCall {
            network.classicalLiteraturePeople(version, page)
        }

    override suspend fun insertClassicalLiteratureSentence(entity: SentenceEntity) {
        database.sentenceDao().insert(entity)
    }

    override suspend fun syncClassicalLiteratureSentence(version: Int): Result<List<PoemSentence>> =
        safeApiCall {
            network.classicalLiteratureSentences(version)
        }

    override suspend fun insertClassicalLiteratureWriting(entity: WritingEntity) {
        database.writingDao().insert(entity)
    }

    override suspend fun insertClassicalLiteratureWriting(entities: List<WritingEntity>) =
        database.writingDao().insert(entities)

    override suspend fun syncClassicalLiteratureWritings(
        version: Int,
        page: Int
    ): Result<WritingWrapper> =
        safeApiCall {
            network.classicalLiteratureWritings(version, page)
        }
}