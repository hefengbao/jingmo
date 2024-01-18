package com.hefengbao.jingmo.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hefengbao.jingmo.data.database.dao.IdiomDao
import com.hefengbao.jingmo.data.database.entity.IdiomEntity
import com.hefengbao.jingmo.data.database.model.SimpleIdiomInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IdiomRepositoryImpl @Inject constructor(
    private val idiomDao: IdiomDao
) : IdiomRepository {
    override fun getIdiom(id: Int): Flow<IdiomEntity> = idiomDao.getIdiom(id)
    override suspend fun getNextId(id: Int): Int = idiomDao.getNextId(id)
    override suspend fun getPrevId(id: Int): Int = idiomDao.getPrevId(id)
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
}