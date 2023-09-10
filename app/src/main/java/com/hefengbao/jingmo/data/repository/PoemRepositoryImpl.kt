package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.data.database.dao.PoemDao
import com.hefengbao.jingmo.data.database.entity.PoemWithWriterAndTags
import javax.inject.Inject

class PoemRepositoryImpl @Inject constructor(
    private val poemDao: PoemDao
) : PoemRepository {
    override suspend fun getPoem(id: Long): PoemWithWriterAndTags = poemDao.getPoem(id)
    override suspend fun getNextId(id: Long): Long = poemDao.getNextId(id)
    override suspend fun getPrevId(id: Long): Long = poemDao.getPrevId(id)
}