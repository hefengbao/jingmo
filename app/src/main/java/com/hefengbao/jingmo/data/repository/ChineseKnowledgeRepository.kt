package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.data.database.entity.ChineseKnowledgeEntity
import kotlinx.coroutines.flow.Flow

interface ChineseKnowledgeRepository {
    fun getChineseKnowledge(id: Int): Flow<ChineseKnowledgeEntity>
    fun getPrevId(id: Int): Flow<Int?>
    fun getNextId(id: Int): Flow<Int?>
    fun getSearchChineseKnowledgeList(query: String): Flow<List<ChineseKnowledgeEntity>>
}