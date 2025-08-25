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

interface NetworkDatasourceRepository {
    suspend fun dataset(): Result<List<Dataset>>
    suspend fun insertChinaWorldCultureHeritage(entity: WorldCulturalHeritageEntity)
    suspend fun syncChinaWorldCultureHeritage(
        version: Int,
        page: Int
    ): Result<DataWrapper<WorldCulturalHeritage>>

    suspend fun insertChineseAntitheticalCouplet(entity: AntitheticalCoupletEntity)
    suspend fun syncChineseAntitheticalCouplet(
        version: Int,
        page: Int
    ): Result<DataWrapper<AntitheticalCouplet>>

    suspend fun insertChineseCharacter(entity: CharacterEntity)
    suspend fun syncChineseCharacter(version: Int, page: Int): Result<DataWrapper<Character>>
    suspend fun insertChineseExpression(entity: ExpressionEntity)
    suspend fun syncChineseExpression(version: Int, page: Int): Result<DataWrapper<Expression>>
    suspend fun insertChineseIdiom(entity: IdiomEntity)
    suspend fun syncChineseIdiom(version: Int, page: Int): Result<DataWrapper<Idiom>>
    suspend fun insertChinesKnowledge(entity: KnowledgeEntity)
    suspend fun syncChineseKnowledge(version: Int, page: Int): Result<DataWrapper<ChineseKnowledge>>
    suspend fun insertChineseLyric(entity: LyricEntity)
    suspend fun syncChineseLyric(version: Int, page: Int): Result<DataWrapper<Lyric>>
    suspend fun insertChineseModernPoetry(entity: ModernPoetryEntity)
    suspend fun syncChineseModernPoetry(version: Int, page: Int): Result<DataWrapper<ModernPoetry>>
    suspend fun insertChineseProverb(entity: ProverbEntity)
    suspend fun syncChineseProverb(version: Int, page: Int): Result<DataWrapper<Proverb>>
    suspend fun insertChineseQuote(entity: QuoteEntity)
    suspend fun syncChineseQuote(version: Int, page: Int): Result<DataWrapper<Quote>>
    suspend fun insertChineseRiddle(entity: RiddleEntity)
    suspend fun syncChineseRiddle(version: Int, page: Int): Result<DataWrapper<Riddle>>
    suspend fun insertChineseTongueTwister(entity: TongueTwisterEntity)
    suspend fun syncChineseTongueTwister(
        version: Int,
        page: Int
    ): Result<DataWrapper<TongueTwister>>

    suspend fun insertChineseWisecrack(entity: WisecrackEntity)
    suspend fun syncChineseWisecrack(version: Int, page: Int): Result<DataWrapper<Wisecrack>>
    suspend fun insertClassicalLiteratureClassicPoem(entity: ClassicPoemEntity)
    suspend fun syncClassicalLiteratureClassicPoem(
        version: Int,
        page: Int
    ): Result<DataWrapper<ClassicPoem>>

    suspend fun insertClassicalLiteraturePeople(entity: PeopleEntity)
    suspend fun syncClassicalLiteraturePeople(version: Int, page: Int): Result<DataWrapper<People>>
    suspend fun insertClassicalLiteratureSentence(entity: SentenceEntity)
    suspend fun syncClassicalLiteratureSentence(
        version: Int,
        page: Int
    ): Result<DataWrapper<Sentence>>

    suspend fun insertClassicalLiteratureWriting(entity: WritingEntity)
    suspend fun insertClassicalLiteratureWriting(entities: List<WritingEntity>)
    suspend fun syncClassicalLiteratureWriting(
        version: Int,
        page: Int
    ): Result<DataWrapper<Writing>>
}