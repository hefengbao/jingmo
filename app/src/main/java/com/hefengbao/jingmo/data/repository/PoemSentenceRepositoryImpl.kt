package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.data.database.dao.PoemSentenceDao
import com.hefengbao.jingmo.data.database.entity.SentenceWithPoem
import javax.inject.Inject

class PoemSentenceRepositoryImpl @Inject constructor(
    private val poemSentenceDao: PoemSentenceDao
) : PoemSentenceRepository {
    override suspend fun getSentence(id: Long): SentenceWithPoem = poemSentenceDao.getSentence(id)
    override suspend fun getNextId(id: Long): Long = poemSentenceDao.getNextId(id)
    override suspend fun getPrevId(id: Long): Long = poemSentenceDao.getPrevId(id)
}