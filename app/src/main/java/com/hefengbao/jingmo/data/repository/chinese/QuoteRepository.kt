package com.hefengbao.jingmo.data.repository.chinese

import androidx.paging.PagingData
import com.hefengbao.jingmo.data.database.entity.chinese.QuoteCollectionEntity
import com.hefengbao.jingmo.data.database.entity.chinese.QuoteEntity
import kotlinx.coroutines.flow.Flow

interface QuoteRepository {
    suspend fun insert(entity: QuoteEntity)
    fun get(id: Int): Flow<QuoteEntity>
    fun random(): Flow<QuoteEntity>
    fun search(query: String): Flow<List<QuoteEntity>>
    fun collections(): Flow<PagingData<QuoteEntity>>
    fun total(): Flow<Int>
    fun prevId(id: Int): Flow<Int?>
    fun nextId(id: Int): Flow<Int?>
    fun isCollect(id: Int): Flow<QuoteCollectionEntity?>
    suspend fun collect(entity: QuoteCollectionEntity)
    suspend fun uncollect(id: Int)
}