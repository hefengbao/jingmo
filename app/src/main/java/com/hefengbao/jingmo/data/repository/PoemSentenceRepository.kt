package com.hefengbao.jingmo.data.repository

import androidx.paging.PagingData
import com.hefengbao.jingmo.data.database.entity.PoemSentenceCollectionEntity
import com.hefengbao.jingmo.data.database.entity.PoemSentenceEntity
import kotlinx.coroutines.flow.Flow

interface PoemSentenceRepository {
    fun getNextId(id: Int): Flow<Int?>
    fun getPrevId(id: Int): Flow<Int?>
    fun searchSentencesList(query: String): Flow<PagingData<PoemSentenceEntity>>
    fun getSentence(id: Int): Flow<PoemSentenceEntity>
    fun random(): Flow<PoemSentenceEntity>
    suspend fun getSearchNextId(id: Int, query: String): Int
    suspend fun getSearchPrevId(id: Int, query: String): Int
    fun collections(): Flow<PagingData<PoemSentenceEntity>>
    suspend fun collect(entity: PoemSentenceCollectionEntity)
    suspend fun uncollect(id: Int)
    fun isCollect(id: Int): Flow<PoemSentenceCollectionEntity?>
    fun getCollectionNextId(collectedAt: Long): Flow<Int?>
    fun getCollectionPrevId(collectedAt: Long): Flow<Int?>
}