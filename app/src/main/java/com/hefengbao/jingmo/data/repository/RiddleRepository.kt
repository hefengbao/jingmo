package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.data.database.entity.RiddleEntity
import kotlinx.coroutines.flow.Flow

interface RiddleRepository {
    fun getRiddle(id: Int): Flow<RiddleEntity>
    suspend fun getNextId(id: Int): Int
    suspend fun getPrevId(id: Int): Int
    fun searchResultList(query: String): Flow<List<RiddleEntity>>
}