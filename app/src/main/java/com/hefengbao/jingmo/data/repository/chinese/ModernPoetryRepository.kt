package com.hefengbao.jingmo.data.repository.chinese

import androidx.paging.PagingData
import com.hefengbao.jingmo.data.database.entity.chinese.ModernPoetryEntity
import kotlinx.coroutines.flow.Flow

interface ModernPoetryRepository {
    suspend fun insert(entity: ModernPoetryEntity)
    fun get(id: Int): Flow<ModernPoetryEntity?>
    fun getRandom(): Flow<ModernPoetryEntity?>
    fun search(query: String): Flow<List<ModernPoetryEntity>>
    fun total(): Flow<Int>
    fun prevId(id: Int): Flow<Int?>
    fun nextId(id: Int): Flow<Int?>
    fun bookmarks(): Flow<PagingData<ModernPoetryEntity>>
}