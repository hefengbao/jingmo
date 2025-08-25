/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.repository.settings

import com.hefengbao.jingmo.data.database.dao.ChinaWorldCulturalHeritageDao
import com.hefengbao.jingmo.data.database.dao.ChineseAntitheticalCoupletDao
import com.hefengbao.jingmo.data.database.dao.ChineseCharacterDao
import com.hefengbao.jingmo.data.database.dao.ChineseExpressionDao
import com.hefengbao.jingmo.data.database.dao.ChineseIdiomDao
import com.hefengbao.jingmo.data.database.dao.ChineseKnowledgeDao
import com.hefengbao.jingmo.data.database.dao.ChineseLyricDao
import com.hefengbao.jingmo.data.database.dao.ChineseModernPoetryDao
import com.hefengbao.jingmo.data.database.dao.ChineseProverbDao
import com.hefengbao.jingmo.data.database.dao.ChineseQuoteDao
import com.hefengbao.jingmo.data.database.dao.ChineseRiddleDao
import com.hefengbao.jingmo.data.database.dao.ChineseTongueTwisterDao
import com.hefengbao.jingmo.data.database.dao.ChineseWisecrackDao
import com.hefengbao.jingmo.data.database.dao.ClassicalLiteratureClassicPoemDao
import com.hefengbao.jingmo.data.database.dao.ClassicalLiteraturePeopleDao
import com.hefengbao.jingmo.data.database.dao.ClassicalLiteratureSentenceDao
import com.hefengbao.jingmo.data.database.dao.ClassicalLiteratureWritingDao
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
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImportRepositoryImpl @Inject constructor(
    private val chinaWorldCulturalHeritageDao: ChinaWorldCulturalHeritageDao,
    private val chineseAntitheticalCoupletDao: ChineseAntitheticalCoupletDao,
    private val chineseExpressionDao: ChineseExpressionDao,
    private val chineseWisecrackDao: ChineseWisecrackDao,
    private val chineseKnowledgeDao: ChineseKnowledgeDao,
    private val chineseCharacterDao: ChineseCharacterDao,
    private val chineseIdiomDao: ChineseIdiomDao,
    private val chineseLyricDao: ChineseLyricDao,
    private val chineseModernPoetryDao: ChineseModernPoetryDao,
    private val chineseProverbDao: ChineseProverbDao,
    private val chineseQuoteDao: ChineseQuoteDao,
    private val chineseRiddleDao: ChineseRiddleDao,
    private val classicalLiteraturePeopleDao: ClassicalLiteraturePeopleDao,
    private val classicalLiteratureClassicPoemDao: ClassicalLiteratureClassicPoemDao,
    private val classicalLiteratureSentenceDao: ClassicalLiteratureSentenceDao,
    private val chineseTongueTwisterDao: ChineseTongueTwisterDao,
    private val classicalLiteratureWritingDao: ClassicalLiteratureWritingDao
) : ImportRepository {
    override suspend fun insertChinaWorldCultureHeritage(entity: WorldCulturalHeritageEntity) =
        chinaWorldCulturalHeritageDao.insert(entity)

    override suspend fun clearChinaWorldCultureHeritage() =
        chinaWorldCulturalHeritageDao.clear()

    override suspend fun insertChineseAntitheticalCouplet(entity: AntitheticalCoupletEntity) =
        chineseAntitheticalCoupletDao.insert(entity)

    override suspend fun clearChineseAntitheticalCouplet() =
        chineseAntitheticalCoupletDao.clear()

    override suspend fun insertChineseExpression(entity: ExpressionEntity) =
        chineseExpressionDao.insert(entity)

    override suspend fun clearChineseExpression() =
        chineseExpressionDao.clear()

    override suspend fun insertChineseWisecrack(entity: WisecrackEntity) =
        chineseWisecrackDao.insert(entity)

    override suspend fun clearChineseWisecrack() =
        chineseWisecrackDao.clear()

    override suspend fun insertChineseKnowledge(entity: KnowledgeEntity) =
        chineseKnowledgeDao.insert(entity)

    override suspend fun clearChineseKnowledge() =
        chineseKnowledgeDao.clear()

    override suspend fun insertChineseProverb(entity: ProverbEntity) =
        chineseProverbDao.insert(entity)

    override suspend fun clearChineseProverb() =
        chineseProverbDao.clear()

    override suspend fun insertChineseQuote(entity: QuoteEntity) =
        chineseQuoteDao.insert(entity)

    override suspend fun clearChineseQuote() = chineseQuoteDao.clear()

    override suspend fun insertChineseCharacter(entity: CharacterEntity) =
        chineseCharacterDao.insert(entity)

    override suspend fun clearChineseCharacter() =
        chineseCharacterDao.clear()

    override suspend fun insertChineseIdiom(entity: IdiomEntity) =
        chineseIdiomDao.insert(entity)

    override suspend fun clearChineseIdioms() =
        chineseIdiomDao.clear()

    override suspend fun insertChineseLyric(entity: LyricEntity) =
        chineseLyricDao.insert(entity)

    override suspend fun clearChineseLyric() =
        chineseLyricDao.clear()

    override suspend fun insertChineseModernPoetry(entity: ModernPoetryEntity) =
        chineseModernPoetryDao.insert(entity)

    override suspend fun clearChineseModernPoetry() =
        chineseModernPoetryDao.clear()

    override suspend fun insertChineseTongueTwister(entity: TongueTwisterEntity) =
        chineseTongueTwisterDao.insert(entity)

    override suspend fun clearChineseTongueTwister() =
        chineseTongueTwisterDao.clear()

    override suspend fun insertChineseRiddle(entity: RiddleEntity) =
        chineseRiddleDao.insert(entity)

    override suspend fun clearChineseRiddle() =
        chineseRiddleDao.clear()

    override suspend fun insertClassicalLiteraturePeople(entity: PeopleEntity) =
        classicalLiteraturePeopleDao.insert(entity)

    override suspend fun clearClassicalLiteraturePeople() =
        classicalLiteraturePeopleDao.clear()

    override suspend fun insertClassicalLiteratureClassicPoems(entity: ClassicPoemEntity) =
        classicalLiteratureClassicPoemDao.insert(entity)

    override suspend fun clearClassicalLiteratureClassicPoem() =
        classicalLiteratureClassicPoemDao.clear()

    override suspend fun insertClassicalLiteratureSentence(entity: SentenceEntity) =
        classicalLiteratureSentenceDao.insert(entity)

    override suspend fun clearClassicalLiteratureSentence() =
        classicalLiteratureSentenceDao.clear()

    override suspend fun insertClassicalLiteratureWriting(entity: WritingEntity) =
        classicalLiteratureWritingDao.insert(entity)

    override suspend fun insertClassicalLiteratureWriting(entities: List<WritingEntity>) =
        classicalLiteratureWritingDao.insert(entities)

    override suspend fun clearClassicalLiteratureWriting() =
        classicalLiteratureWritingDao.clear()

    override fun chinaChinaWorldCultureHeritageTotal(): Flow<Int> =
        chinaWorldCulturalHeritageDao.total()

    override fun chineseAntitheticalCoupletTotal(): Flow<Int> =
        chineseAntitheticalCoupletDao.total()

    override fun chineseExpressionTotal(): Flow<Int> = chineseExpressionDao.total()

    override fun chineseWisecrackTotal(): Flow<Int> = chineseWisecrackDao.total()

    override fun chineseKnowledgeTotal(): Flow<Int> = chineseKnowledgeDao.total()

    override fun chineseProverbTotal(): Flow<Int> = chineseProverbDao.total()

    override fun chineseQuoteTotal(): Flow<Int> = chineseQuoteDao.total()

    override fun classicalLiteratureClassicPoemTotal(): Flow<Int> =
        classicalLiteratureClassicPoemDao.total()

    override fun chineseCharacterTotal(): Flow<Int> = chineseCharacterDao.total()

    override fun chineseIdiomTotal(): Flow<Int> = chineseIdiomDao.total()

    override fun chineseLyricTotal(): Flow<Int> = chineseLyricDao.total()

    override fun chineseModernPoetryTotal(): Flow<Int> = chineseModernPoetryDao.total()

    override fun classicalLiteraturePeopleTotal(): Flow<Int> = classicalLiteraturePeopleDao.total()

    override fun classicalLiteratureSentenceTotal(): Flow<Int> =
        classicalLiteratureSentenceDao.total()

    override fun chineseTongueTwisterTotal(): Flow<Int> = chineseTongueTwisterDao.total()

    override fun chineseRiddleTotal(): Flow<Int> = chineseRiddleDao.total()

    override fun classicalLiteratureWritingTotal(): Flow<Int> =
        classicalLiteratureWritingDao.total()
}