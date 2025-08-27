package com.hefengbao.jingmo.data.repository.chinese

import androidx.paging.PagingData
import com.hefengbao.jingmo.data.database.entity.chinese.QuoteEntity
import kotlinx.coroutines.flow.Flow

interface QuoteRepository {
    suspend fun insert(entity: QuoteEntity)
    fun get(id: Int): Flow<QuoteEntity?>
    fun get(ids: List<Int>): Flow<List<QuoteEntity>>
    fun getRandom(): Flow<QuoteEntity?>
    fun search(query: String): Flow<List<QuoteEntity>>
    fun total(): Flow<Int>
    fun prevId(id: Int): Flow<Int?>
    fun nextId(id: Int): Flow<Int?>
    fun bookmarks(): Flow<PagingData<QuoteEntity>>
}