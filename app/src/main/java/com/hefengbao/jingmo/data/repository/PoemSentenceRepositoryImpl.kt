package com.hefengbao.jingmo.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hefengbao.jingmo.data.database.dao.PoemSentenceDao
import com.hefengbao.jingmo.data.database.entity.PoemSentenceEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PoemSentenceRepositoryImpl @Inject constructor(
    private val poemSentenceDao: PoemSentenceDao
) : PoemSentenceRepository {
    override suspend fun getNextId(id: Int): Int = poemSentenceDao.getNextId(id)
    override suspend fun getPrevId(id: Int): Int = poemSentenceDao.getPrevId(id)
    override fun searchSentencesList(query: String): Flow<PagingData<PoemSentenceEntity>> = Pager(
        config = PagingConfig(pageSize = 30),
        pagingSourceFactory = { poemSentenceDao.searchSentencesList("%$query%") }
    ).flow


    override fun getSentence(id: Int): Flow<PoemSentenceEntity> =
        poemSentenceDao.getSentence(id)

    override suspend fun getSearchNextId(id: Int, query: String): Int =
        poemSentenceDao.getSearchNextId(id, "%$query%")

    override suspend fun getSearchPrevId(id: Int, query: String): Int =
        poemSentenceDao.getSearchPrevId(id, "%$query%")
}