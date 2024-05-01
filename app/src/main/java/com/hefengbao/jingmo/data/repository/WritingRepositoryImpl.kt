package com.hefengbao.jingmo.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hefengbao.jingmo.data.database.dao.WritingDao
import com.hefengbao.jingmo.data.database.entity.WritingCollectionEntity
import com.hefengbao.jingmo.data.database.entity.WritingEntity
import com.hefengbao.jingmo.data.database.model.SimpleWritingInfo
import com.hefengbao.jingmo.data.database.model.WritingBookmarkSimpleInfo
import com.hefengbao.jingmo.data.database.model.WritingWithBookmark
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WritingRepositoryImpl @Inject constructor(
    private val writingDao: WritingDao
) : WritingRepository {
    override fun get(id: Int): Flow<WritingWithBookmark> = writingDao.get(id)

    override fun random(): Flow<WritingEntity> = writingDao.random()

    override fun collected(id: Int): Flow<WritingCollectionEntity?> = writingDao.collected(id)

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

    override fun getNextId(id: Int, author: String): Flow<Int?> =
        writingDao.getNextId(id, author)

    override fun getPrevId(id: Int): Flow<Int?> = writingDao.getPrevId(id)

    override fun getPrevId(id: Int, author: String): Flow<Int?> =
        writingDao.getPrevId(id, author)

    override fun getSearchNextId(id: Int, query: String): Flow<Int?> =
        writingDao.getSearchNextId(id, "*$query*")

    override fun getSearchPrevId(id: Int, query: String): Flow<Int?> =
        writingDao.getSearchPrevId(id, "*$query*")

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

    override fun getCollectionNextId(collectedAt: Long): Flow<Int?> =
        writingDao.getCollectionNextId(collectedAt)

    override fun getCollectionPrevId(collectedAt: Long): Flow<Int?> =
        writingDao.getCollectionPrevId(collectedAt)
}