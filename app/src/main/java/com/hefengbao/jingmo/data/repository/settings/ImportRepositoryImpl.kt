/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.repository.settings

import com.hefengbao.jingmo.data.database.dao.ChineseDictionaryDao
import com.hefengbao.jingmo.data.database.dao.ChineseExpressionDao
import com.hefengbao.jingmo.data.database.dao.ChineseIdiomDao
import com.hefengbao.jingmo.data.database.dao.ChineseKnowledgeDao
import com.hefengbao.jingmo.data.database.dao.ChineseLyricDao
import com.hefengbao.jingmo.data.database.dao.ChineseProverbDao
import com.hefengbao.jingmo.data.database.dao.ChineseTongueTwisterDao
import com.hefengbao.jingmo.data.database.dao.ChineseWisecrackDao
import com.hefengbao.jingmo.data.database.dao.ClassicalLiteratureClassicPoemDao
import com.hefengbao.jingmo.data.database.dao.ClassicalLiteraturePeopleDao
import com.hefengbao.jingmo.data.database.dao.ClassicalLiteratureSentenceDao
import com.hefengbao.jingmo.data.database.dao.ClassicalLiteratureWritingDao
import com.hefengbao.jingmo.data.database.entity.chinese.DictionaryEntity
import com.hefengbao.jingmo.data.database.entity.chinese.DictionaryPinyinEntity
import com.hefengbao.jingmo.data.database.entity.chinese.ExpressionEntity
import com.hefengbao.jingmo.data.database.entity.chinese.IdiomEntity
import com.hefengbao.jingmo.data.database.entity.chinese.KnowledgeEntity
import com.hefengbao.jingmo.data.database.entity.chinese.LyricEntity
import com.hefengbao.jingmo.data.database.entity.chinese.ProverbEntity
import com.hefengbao.jingmo.data.database.entity.chinese.TongueTwisterEntity
import com.hefengbao.jingmo.data.database.entity.chinese.WisecrackEntity
import com.hefengbao.jingmo.data.database.entity.classicalliterature.ClassicPoemEntity
import com.hefengbao.jingmo.data.database.entity.classicalliterature.PeopleEntity
import com.hefengbao.jingmo.data.database.entity.classicalliterature.SentenceEntity
import com.hefengbao.jingmo.data.database.entity.classicalliterature.WritingEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImportRepositoryImpl @Inject constructor(
    private val chineseExpressionDao: ChineseExpressionDao,
    private val chineseWisecrackDao: ChineseWisecrackDao,
    private val chineseKnowledgeDao: ChineseKnowledgeDao,
    private val chineseDictionaryDao: ChineseDictionaryDao,
    private val chineseIdiomDao: ChineseIdiomDao,
    private val chineseLyricDao: ChineseLyricDao,
    private val chineseProverbDao: ChineseProverbDao,
    private val classicalLiteraturePeopleDao: ClassicalLiteraturePeopleDao,
    private val classicalLiteratureClassicPoemDao: ClassicalLiteratureClassicPoemDao,
    private val classicalLiteratureSentenceDao: ClassicalLiteratureSentenceDao,
    private val chineseTongueTwisterDao: ChineseTongueTwisterDao,
    private val classicalLiteratureWritingDao: ClassicalLiteratureWritingDao
) : ImportRepository {
    override suspend fun insertChineseExpression(entity: ExpressionEntity) =
        chineseExpressionDao.insert(entity)

    override suspend fun insertChineseWisecrack(entity: WisecrackEntity) =
        chineseWisecrackDao.insert(entity)

    override suspend fun insertChineseKnowledge(entity: KnowledgeEntity) =
        chineseKnowledgeDao.insert(entity)

    override suspend fun insertChineseProverb(entity: ProverbEntity) =
        chineseProverbDao.insert(entity)

    override suspend fun insertClassicPoem(entity: ClassicPoemEntity) =
        classicalLiteratureClassicPoemDao.insert(entity)

    override suspend fun insertDictionary(entity: DictionaryEntity) =
        chineseDictionaryDao.insert(entity)

    override suspend fun insertDictionaryPinyin(entity: DictionaryPinyinEntity) =
        chineseDictionaryDao.insertDictionaryPinyin(entity)

    override suspend fun insertIdiom(entity: IdiomEntity) =
        chineseIdiomDao.insert(entity)

    override suspend fun insertLyric(entity: LyricEntity) = chineseLyricDao.insert(entity)

    override suspend fun insertPeople(entity: PeopleEntity) =
        classicalLiteraturePeopleDao.insert(entity)

    override suspend fun insertPoemSentence(entity: SentenceEntity) =
        classicalLiteratureSentenceDao.insert(entity)

    override suspend fun insertTongueTwister(entity: TongueTwisterEntity) =
        chineseTongueTwisterDao.insert(entity)

    override suspend fun insertWriting(entity: WritingEntity) =
        classicalLiteratureWritingDao.insert(entity)

    override fun chineseExpressionTotal(): Flow<Int> = chineseExpressionDao.total()

    override fun chineseWisecrackTotal(): Flow<Int> = chineseWisecrackDao.total()

    override fun chineseKnowledgeTotal(): Flow<Int> = chineseKnowledgeDao.total()

    override fun chineseProverbTotal(): Flow<Int> = chineseProverbDao.total()

    override fun classicPoemTotal(): Flow<Int> = classicalLiteratureClassicPoemDao.total()

    override fun dictionaryTotal(): Flow<Int> = chineseDictionaryDao.total()

    override fun idiomsTotal(): Flow<Int> = chineseIdiomDao.total()

    override fun lyricTotal(): Flow<Int> = chineseLyricDao.total()

    override fun peopleTotal(): Flow<Int> = classicalLiteraturePeopleDao.total()

    override fun poemSentencesTotal(): Flow<Int> = classicalLiteratureSentenceDao.total()

    override fun tongueTwistersTotal(): Flow<Int> = chineseTongueTwisterDao.total()

    override fun writingsTotal(): Flow<Int> = classicalLiteratureWritingDao.total()
}