package com.hefengbao.jingmo.data.repository.chinese

import androidx.paging.PagingData
import com.hefengbao.jingmo.data.database.entity.chinese.ProverbCollectionEntity
import com.hefengbao.jingmo.data.database.entity.chinese.ProverbEntity
import kotlinx.coroutines.flow.Flow

interface ProverbRepository {
    suspend fun insert(entity: ProverbEntity)
    fun get(id: Int): Flow<ProverbEntity>
    fun random(): Flow<ProverbEntity>
    fun search(query: String): Flow<List<ProverbEntity>>
    fun collections(): Flow<PagingData<ProverbEntity>>
    fun total(): Flow<Int>
    fun prevId(id: Int): Flow<Int?>
    fun nextId(id: Int): Flow<Int?>
    fun isCollect(id: Int): Flow<ProverbCollectionEntity?>
    suspend fun collect(entity: ProverbCollectionEntity)
    suspend fun uncollect(id: Int)
}