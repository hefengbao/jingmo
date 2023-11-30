package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.data.database.dao.IdiomDao
import com.hefengbao.jingmo.data.database.entity.IdiomEntity
import com.hefengbao.jingmo.data.database.model.SimpleIdiomInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IdiomRepositoryImpl @Inject constructor(
    private val idiomDao: IdiomDao
) : IdiomRepository {
    override suspend fun getIdiom(id: Long): IdiomEntity = idiomDao.getIdiom(id)
    override suspend fun getNextId(id: Long): Long = idiomDao.getNextId(id)
    override suspend fun getPrevId(id: Long): Long = idiomDao.getPrevId(id)
    override fun getSimpleIdiomInfoList(): Flow<List<SimpleIdiomInfo>> =
        idiomDao.getSimpleIdiomInfoList()

    override fun searchSimpleIdiomInfoList(query: String): Flow<List<SimpleIdiomInfo>> =
        idiomDao.searchSimpleIdiomInfoList("%$query%")

    override suspend fun getSearchNextId(id: Long, query: String): Long =
        idiomDao.getSearchNextId(id, "%$query%")

    override suspend fun getSearchPrevId(id: Long, query: String): Long =
        idiomDao.getSearchPrevId(id, "%$query%")
}