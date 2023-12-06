package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.data.database.entity.ChineseKnowledgeEntity
import kotlinx.coroutines.flow.Flow

interface ChineseKnowledgeRepository {
    fun getChineseKnowledge(id: Int): Flow<ChineseKnowledgeEntity>
    suspend fun getPrevId(id: Int): Int
    suspend fun getNextId(id: Int): Int
    fun getSearchChineseKnowledgeList(query: String): Flow<List<ChineseKnowledgeEntity>>
}