package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.data.database.dao.PoemSentenceDao
import com.hefengbao.jingmo.data.database.entity.PoemSentenceEntity
import com.hefengbao.jingmo.data.database.entity.SentenceWithPoem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PoemSentenceRepositoryImpl @Inject constructor(
    private val poemSentenceDao: PoemSentenceDao
) : PoemSentenceRepository {
    override suspend fun getSentenceWithPoem(id: Int): SentenceWithPoem =
        poemSentenceDao.getSentenceWithPoem(id)

    override suspend fun getNextId(id: Int): Int = poemSentenceDao.getNextId(id)
    override suspend fun getPrevId(id: Int): Int = poemSentenceDao.getPrevId(id)
    override fun searchSentencesList(query: String): Flow<List<PoemSentenceEntity>> =
        poemSentenceDao.searchSentencesList("%$query%")

    override suspend fun getSentence(id: Int): Flow<PoemSentenceEntity> =
        poemSentenceDao.getSentence(id)

    override suspend fun getSearchNextId(id: Int, query: String): Int =
        poemSentenceDao.getSearchNextId(id, "%$query%")

    override suspend fun getSearchPrevId(id: Int, query: String): Int =
        poemSentenceDao.getSearchPrevId(id, "%$query%")
}