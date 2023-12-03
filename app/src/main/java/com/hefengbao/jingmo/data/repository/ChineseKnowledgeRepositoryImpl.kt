package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.data.database.dao.ChineseKnowledgeDao
import com.hefengbao.jingmo.data.database.entity.ChineseKnowledgeEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChineseKnowledgeRepositoryImpl @Inject constructor(
    private val chineseKnowledgeDao: ChineseKnowledgeDao
) : ChineseKnowledgeRepository {
    override fun getChineseKnowledge(id: Int): Flow<ChineseKnowledgeEntity> =
        chineseKnowledgeDao.getChineseKnowledge(id)

    override suspend fun getPrevId(id: Int): Int =
        chineseKnowledgeDao.getPrevId(id)

    override suspend fun getNextId(id: Int): Int =
        chineseKnowledgeDao.getNestId(id)

    override fun getSearchChineseKnowledgeList(query: String): Flow<List<ChineseKnowledgeEntity>> =
        chineseKnowledgeDao.getSearchChineseKnowledgeList("%$query%")
}