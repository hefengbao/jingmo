package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.data.database.dao.PoemSentenceDao
import com.hefengbao.jingmo.data.database.entity.PoemSentenceEntity
import com.hefengbao.jingmo.data.database.entity.SentenceWithPoem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PoemSentenceRepositoryImpl @Inject constructor(
    private val poemSentenceDao: PoemSentenceDao
) : PoemSentenceRepository {
    override suspend fun getSentenceWithPoem(id: Long): SentenceWithPoem =
        poemSentenceDao.getSentenceWithPoem(id)

    override suspend fun getNextId(id: Long): Long = poemSentenceDao.getNextId(id)
    override suspend fun getPrevId(id: Long): Long = poemSentenceDao.getPrevId(id)
    override fun searchSentencesList(query: String): Flow<List<PoemSentenceEntity>> =
        poemSentenceDao.searchSentencesList(query)

    override suspend fun getSentence(id: Long): Flow<PoemSentenceEntity> =
        poemSentenceDao.getSentence(id)
}