package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.data.database.dao.PoemDao
import com.hefengbao.jingmo.data.database.entity.PoemWithWriterAndTags
import com.hefengbao.jingmo.data.database.model.PoemSimpleInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PoemRepositoryImpl @Inject constructor(
    private val poemDao: PoemDao
) : PoemRepository {
    override suspend fun getPoem(id: Long): PoemWithWriterAndTags = poemDao.getPoem(id)
    override suspend fun getNextId(id: Long): Long = poemDao.getNextId(id)
    override suspend fun getPrevId(id: Long): Long = poemDao.getPrevId(id)
    override fun getPoemSimpleList(): Flow<List<PoemSimpleInfo>> = poemDao.getPoemSimpleInfoList()

    override fun searchPoemSimpleList(query: String): Flow<List<PoemSimpleInfo>> =
        poemDao.searchPoemSimpleInfoList(query)
}