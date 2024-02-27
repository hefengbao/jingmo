package com.hefengbao.jingmo.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hefengbao.jingmo.data.database.dao.IdiomDao
import com.hefengbao.jingmo.data.database.entity.IdiomCollectionEntity
import com.hefengbao.jingmo.data.database.model.IdiomWithBookmark
import com.hefengbao.jingmo.data.database.model.SimpleIdiomInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IdiomRepositoryImpl @Inject constructor(
    private val idiomDao: IdiomDao
) : IdiomRepository {
    override fun getIdiom(id: Int): Flow<IdiomWithBookmark> = idiomDao.getIdiom(id)
    override fun getNextId(id: Int): Flow<Int?> = idiomDao.getNextId(id)
    override fun getPrevId(id: Int): Flow<Int?> = idiomDao.getPrevId(id)
    override fun getSimpleIdiomInfoList(): Flow<PagingData<SimpleIdiomInfo>> = Pager(
        config = PagingConfig(pageSize = 30),
        pagingSourceFactory = {
            idiomDao.getSimpleIdiomInfoList()
        }
    ).flow

    override fun searchSimpleIdiomInfoList(query: String): Flow<PagingData<SimpleIdiomInfo>> =
        Pager(
            config = PagingConfig(pageSize = 30),
            pagingSourceFactory = {
                idiomDao.searchSimpleIdiomInfoList("%$query%")
            }
        ).flow

    override suspend fun getSearchNextId(id: Int, query: String): Int =
        idiomDao.getSearchNextId(id, "%$query%")

    override suspend fun getSearchPrevId(id: Int, query: String): Int =
        idiomDao.getSearchPrevId(id, "%$query%")

    override fun collections(): Flow<PagingData<IdiomWithBookmark>> = Pager(
        config = PagingConfig(pageSize = 30),
        pagingSourceFactory = { idiomDao.collections() }
    ).flow

    override suspend fun collect(entity: IdiomCollectionEntity) =
        idiomDao.collect(entity)

    override suspend fun uncollect(id: Int) = idiomDao.uncollect(id)

    override fun isCollect(id: Int): Flow<IdiomCollectionEntity?> = idiomDao.isCollect(id)

    override fun getCollectionNextId(collectedAt: Long): Flow<Int?> =
        idiomDao.getCollectionNextId(collectedAt)

    override fun getCollectionPrevId(collectedAt: Long): Flow<Int?> =
        idiomDao.getCollectionPrevId(collectedAt)
}