package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.data.database.dao.PoemDao
import com.hefengbao.jingmo.data.database.entity.PoemEntity
import com.hefengbao.jingmo.data.database.entity.PoemWithWriterAndTags
import com.hefengbao.jingmo.data.database.model.PoemSimpleInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PoemRepositoryImpl @Inject constructor(
    private val poemDao: PoemDao
) : PoemRepository {
    override suspend fun getPoemWithWriterAndTags(id: Long): PoemWithWriterAndTags =
        poemDao.getPoemWithWriterAndTags(id)

    override suspend fun getNextId(id: Long): Long = poemDao.getNextId(id)
    override suspend fun getPrevId(id: Long): Long = poemDao.getPrevId(id)
    override suspend fun getPoem(id: Long): Flow<PoemEntity> = poemDao.getPoem(id)
    override fun getPoemSimpleList(): Flow<List<PoemSimpleInfo>> = poemDao.getPoemSimpleInfoList()
    override fun searchPoemSimpleList(query: String): Flow<List<PoemSimpleInfo>> =
        poemDao.searchPoemSimpleInfoList("%$query%")

    override suspend fun getSearchNextId(id: Long, query: String): Long =
        poemDao.getSearchNextId(id, "%$query%")

    override suspend fun getSearchPrevId(id: Long, query: String): Long =
        poemDao.getSearchPrevId(id, "%$query%")
}