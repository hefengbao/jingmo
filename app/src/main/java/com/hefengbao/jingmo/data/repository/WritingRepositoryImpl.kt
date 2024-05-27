package com.hefengbao.jingmo.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hefengbao.jingmo.data.database.dao.WritingDao
import com.hefengbao.jingmo.data.database.entity.WritingCollectionEntity
import com.hefengbao.jingmo.data.database.entity.WritingEntity
import com.hefengbao.jingmo.data.database.model.SimpleWritingInfo
import com.hefengbao.jingmo.data.database.model.WritingBookmarkSimpleInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WritingRepositoryImpl @Inject constructor(
    private val writingDao: WritingDao
) : WritingRepository {
    override fun get(id: Int): Flow<WritingEntity> = writingDao.get(id)

    override fun random(): Flow<WritingEntity> = writingDao.random()

    override fun list(): Flow<PagingData<WritingEntity>> = Pager(
        config = PagingConfig(pageSize = 30),
        pagingSourceFactory = { writingDao.list() }
    ).flow

    override fun search(query: String): Flow<PagingData<SimpleWritingInfo>> = Pager(
        config = PagingConfig(pageSize = 30),
        pagingSourceFactory = { writingDao.search("*$query*") }
    ).flow

    override fun searchByAuthor(author: String): Flow<PagingData<SimpleWritingInfo>> = Pager(
        config = PagingConfig(pageSize = 30),
        pagingSourceFactory = {
            writingDao.searchByAuthor(author)
        }
    ).flow

    override fun getNextId(id: Int): Flow<Int?> = writingDao.getNextId(id)

    override fun getPrevId(id: Int): Flow<Int?> = writingDao.getPrevId(id)

    override fun collections(): Flow<PagingData<WritingBookmarkSimpleInfo>> = Pager(
        config = PagingConfig(pageSize = 30),
        pagingSourceFactory = {
            writingDao.collections()
        }
    ).flow

    override suspend fun collect(entity: WritingCollectionEntity) =
        writingDao.collect(entity)

    override suspend fun uncollect(writingId: Int) = writingDao.uncollect(writingId)

    override fun isCollect(id: Int): Flow<WritingCollectionEntity?> =
        writingDao.isCollect(id)
}