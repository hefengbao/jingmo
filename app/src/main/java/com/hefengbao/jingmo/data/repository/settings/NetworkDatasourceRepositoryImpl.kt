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
import com.hefengbao.jingmo.data.database.entity.chinese.CharacterEntity
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
        database.chinaWorldCulturalHeritageDao().insert(entity)

    override suspend fun syncChinaWorldCultureHeritage(
        version: Int,
        page: Int
    ): Result<DataWrapper<WorldCulturalHeritage>> =
        safeApiCall {
            network.chinaWorldCultureHeritages(version, page)
        }

    override suspend fun insertChineseAntitheticalCouplet(entity: AntitheticalCoupletEntity) =
        database.chineseAntitheticalCoupletDao().insert(entity)

    override suspend fun syncChineseAntitheticalCouplet(
        version: Int,
        page: Int
    ): Result<DataWrapper<AntitheticalCouplet>> =
        safeApiCall {
            network.chineseAntitheticalCouplets(version, page)
        }

    override suspend fun insertChineseCharacter(entity: CharacterEntity) =
        database.chineseCharacterDao().insert(entity)


    override suspend fun syncChineseCharacter(
        version: Int,
        page: Int
    ): Result<DataWrapper<Character>> =
        safeApiCall {
            network.chineseCharacters(version, page)
        }

    override suspend fun insertChineseExpression(entity: ExpressionEntity) =
        database.chineseExpressionDao().insert(entity)

    override suspend fun syncChineseExpression(
        version: Int,
        page: Int
    ): Result<DataWrapper<Expression>> =
        safeApiCall {
            network.chineseExpressions(version, page)
        }

    override suspend fun syncChineseIdiom(version: Int, page: Int): Result<DataWrapper<Idiom>> =
        safeApiCall {
            network.chineseIdioms(version, page)
        }

    override suspend fun insertChineseIdiom(entity: IdiomEntity) {
        database.chineseIdiomDao().insert(entity)
    }

    override suspend fun insertChinesKnowledge(entity: KnowledgeEntity) {
        database.chineseKnowledgeDao().insert(entity)
    }

    override suspend fun syncChineseKnowledge(
        version: Int,
        page: Int
    ): Result<DataWrapper<ChineseKnowledge>> =
        safeApiCall {
            network.chineseKnowledge(version, page)
        }

    override suspend fun insertChineseLyric(entity: LyricEntity) =
        database.chineseLyricDao().insert(entity)

    override suspend fun syncChineseLyric(version: Int, page: Int): Result<DataWrapper<Lyric>> =
        safeApiCall {
            network.chineseLyrics(version, page)
        }

    override suspend fun insertChineseModernPoetry(entity: ModernPoetryEntity) =
        database.chineseModernPoetryDao().insert(entity)

    override suspend fun syncChineseModernPoetry(
        version: Int,
        page: Int
    ): Result<DataWrapper<ModernPoetry>> =
        safeApiCall {
            network.chineseModernPoetry(version, page)
        }

    override suspend fun insertChineseProverb(entity: ProverbEntity) =
        database.chineseProverbDao().insert(entity)

    override suspend fun syncChineseProverb(version: Int, page: Int): Result<DataWrapper<Proverb>> =
        safeApiCall {
            network.chineseProverbs(version, page)
        }

    override suspend fun insertChineseQuote(entity: QuoteEntity) =
        database.chineseQuoteDao().insert(entity)

    override suspend fun syncChineseQuote(version: Int, page: Int): Result<DataWrapper<Quote>> =
        safeApiCall {
            network.chineseQuotes(version, page)
        }

    override suspend fun insertChineseRiddle(entity: RiddleEntity) {
        database.chineseRiddleDao().insert(entity)
    }

    override suspend fun syncChineseRiddle(version: Int, page: Int): Result<DataWrapper<Riddle>> =
        safeApiCall {
            network.chineseRiddles(version, page)
        }

    override suspend fun insertChineseTongueTwister(entity: TongueTwisterEntity) {
        database.chineseTongueTwisterDao().insert(entity)
    }

    override suspend fun syncChineseTongueTwister(
        version: Int,
        page: Int
    ): Result<DataWrapper<TongueTwister>> =
        safeApiCall {
            network.chineseTongueTwisters(version, page)
        }

    override suspend fun syncChineseWisecrack(
        version: Int,
        page: Int
    ): Result<DataWrapper<Wisecrack>> =
        safeApiCall {
            network.chineseWisecracks(version, page)
        }

    override suspend fun insertChineseWisecrack(entity: WisecrackEntity) {
        database.chineseWisecrackDao().insert(entity)
    }

    override suspend fun insertClassicalLiteratureClassicPoem(entity: ClassicPoemEntity) =
        database.classicalLiteratureClassicPoemDao().insert(entity)

    override suspend fun syncClassicalLiteratureClassicPoem(
        version: Int,
        page: Int
    ): Result<DataWrapper<ClassicPoem>> =
        safeApiCall {
            network.classicalLiteratureClassicPoems(version, page)
        }

    override suspend fun insertClassicalLiteraturePeople(entity: PeopleEntity) {
        database.classicalLiteraturePeopleDao().insert(entity)
    }

    override suspend fun syncClassicalLiteraturePeople(
        version: Int,
        page: Int
    ): Result<DataWrapper<People>> =
        safeApiCall {
            network.classicalLiteraturePeople(version, page)
        }

    override suspend fun insertClassicalLiteratureSentence(entity: SentenceEntity) {
        database.classicalLiteratureSentenceDao().insert(entity)
    }

    override suspend fun syncClassicalLiteratureSentence(
        version: Int,
        page: Int
    ): Result<DataWrapper<Sentence>> =
        safeApiCall {
            network.classicalLiteratureSentences(version, page)
        }

    override suspend fun insertClassicalLiteratureWriting(entity: WritingEntity) {
        database.classicalLiteratureWritingDao().insert(entity)
    }

    override suspend fun insertClassicalLiteratureWriting(entities: List<WritingEntity>) =
        database.classicalLiteratureWritingDao().insert(entities)

    override suspend fun syncClassicalLiteratureWriting(
        version: Int,
        page: Int
    ): Result<DataWrapper<Writing>> =
        safeApiCall {
            network.classicalLiteratureWritings(version, page)
        }
}