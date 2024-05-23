package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.data.database.dao.ChineseKnowledgeDao
import com.hefengbao.jingmo.data.database.dao.ChineseWisecrackDao
import com.hefengbao.jingmo.data.database.dao.ClassicPoemDao
import com.hefengbao.jingmo.data.database.dao.DictionaryDao
import com.hefengbao.jingmo.data.database.dao.IdiomDao
import com.hefengbao.jingmo.data.database.dao.PeopleDao
import com.hefengbao.jingmo.data.database.dao.PoemSentenceDao
import com.hefengbao.jingmo.data.database.dao.TongueTwisterDao
import com.hefengbao.jingmo.data.database.dao.WritingDao
import com.hefengbao.jingmo.data.database.entity.ChineseKnowledgeEntity
import com.hefengbao.jingmo.data.database.entity.ChineseWisecrackEntity
import com.hefengbao.jingmo.data.database.entity.ClassicPoemEntity
import com.hefengbao.jingmo.data.database.entity.DictionaryEntity
import com.hefengbao.jingmo.data.database.entity.DictionaryPinyinEntity
import com.hefengbao.jingmo.data.database.entity.IdiomEntity
import com.hefengbao.jingmo.data.database.entity.PeopleEntity
import com.hefengbao.jingmo.data.database.entity.PoemSentenceEntity
import com.hefengbao.jingmo.data.database.entity.TongueTwisterEntity
import com.hefengbao.jingmo.data.database.entity.WritingEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImportRepositoryImpl @Inject constructor(
    private val chineseWisecrackDao: ChineseWisecrackDao,
    private val chineseKnowledgeDao: ChineseKnowledgeDao,
    private val dictionaryDao: DictionaryDao,
    private val idiomDao: IdiomDao,
    private val peopleDao: PeopleDao,
    private val classicPoemDao: ClassicPoemDao,
    private val poemSentenceDao: PoemSentenceDao,
    private val tongueTwisterDao: TongueTwisterDao,
    private val writingDao: WritingDao
) : ImportRepository {
    override suspend fun insertChineseWisecrack(entity: ChineseWisecrackEntity) =
        chineseWisecrackDao.insert(entity)

    override suspend fun insertChineseKnowledge(entity: ChineseKnowledgeEntity) =
        chineseKnowledgeDao.insert(entity)

    override suspend fun insertClassicPoem(entity: ClassicPoemEntity) =
        classicPoemDao.insert(entity)

    override suspend fun insertDictionary(entity: DictionaryEntity) = dictionaryDao.insert(entity)

    override suspend fun insertDictionaryPinyin(entity: DictionaryPinyinEntity) =
        dictionaryDao.insertDictionaryPinyin(entity)

    override suspend fun insertIdiom(entity: IdiomEntity) =
        idiomDao.insert(entity)

    override suspend fun insertPeople(entity: PeopleEntity) =
        peopleDao.insert(entity)

    override suspend fun insertPoemSentence(entity: PoemSentenceEntity) =
        poemSentenceDao.insert(entity)

    override suspend fun insertTongueTwister(entity: TongueTwisterEntity) =
        tongueTwisterDao.insert(entity)

    override suspend fun insertWriting(entity: WritingEntity) =
        writingDao.insert(entity)

    override fun chineseWisecrackTotal(): Flow<Int> = chineseWisecrackDao.total()

    override fun chineseKnowledgeTotal(): Flow<Int> = chineseKnowledgeDao.total()

    override fun classicPoemTotal(): Flow<Int> = classicPoemDao.total()

    override fun dictionaryTotal(): Flow<Int> = dictionaryDao.total()

    override fun idiomsTotal(): Flow<Int> = idiomDao.total()

    override fun peopleTotal(): Flow<Int> = peopleDao.total()

    override fun poemSentencesTotal(): Flow<Int> = poemSentenceDao.total()

    override fun tongueTwistersTotal(): Flow<Int> = tongueTwisterDao.total()

    override fun writingsTotal(): Flow<Int> = writingDao.total()
}