package com.hefengbao.jingmo.data.repository.chinese

import androidx.paging.PagingData
import com.hefengbao.jingmo.data.database.entity.chinese.ProverbEntity
import kotlinx.coroutines.flow.Flow

interface ProverbRepository {
    suspend fun insert(entity: ProverbEntity)
    fun get(id: Int): Flow<ProverbEntity?>
    fun get(ids: List<Int>): Flow<List<ProverbEntity>>
    fun getRandom(): Flow<ProverbEntity?>
    fun search(query: String): Flow<List<ProverbEntity>>
    fun total(): Flow<Int>
    fun prevId(id: Int): Flow<Int?>
    fun nextId(id: Int): Flow<Int?>
    fun bookmarks(): Flow<PagingData<ProverbEntity>>
}