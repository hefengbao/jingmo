package com.hefengbao.jingmo.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hefengbao.jingmo.data.database.dao.PoemSentenceDao
import com.hefengbao.jingmo.data.database.entity.PoemSentenceCollectionEntity
import com.hefengbao.jingmo.data.database.entity.PoemSentenceEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PoemSentenceRepositoryImpl @Inject constructor(
    private val poemSentenceDao: PoemSentenceDao
) : PoemSentenceRepository {
    override fun getNextId(id: Int): Flow<Int?> = poemSentenceDao.getNextId(id)

    override fun getPrevId(id: Int): Flow<Int?> = poemSentenceDao.getPrevId(id)

    override fun searchSentencesList(query: String): Flow<PagingData<PoemSentenceEntity>> = Pager(
        config = PagingConfig(pageSize = 30),
        pagingSourceFactory = { poemSentenceDao.searchSentencesList("%$query%") }
    ).flow

    override fun getSentence(id: Int): Flow<PoemSentenceEntity> =
        poemSentenceDao.getSentence(id)

    override fun random(): Flow<PoemSentenceEntity> = poemSentenceDao.random()

    override fun collections(): Flow<PagingData<PoemSentenceEntity>> = Pager(
        config = PagingConfig(pageSize = 30),
        pagingSourceFactory = {
            poemSentenceDao.collections()
        }
    ).flow

    override suspend fun collect(entity: PoemSentenceCollectionEntity) =
        poemSentenceDao.collect(entity)

    override suspend fun uncollect(id: Int) = poemSentenceDao.uncollect(id)

    override fun isCollect(id: Int): Flow<PoemSentenceCollectionEntity?> =
        poemSentenceDao.isCollect(id)
}