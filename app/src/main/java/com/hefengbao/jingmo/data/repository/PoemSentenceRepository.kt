package com.hefengbao.jingmo.data.repository

import androidx.paging.PagingData
import com.hefengbao.jingmo.data.database.entity.PoemSentenceCollectionEntity
import com.hefengbao.jingmo.data.database.entity.PoemSentenceEntity
import com.hefengbao.jingmo.data.database.model.PoemSentenceWithBookmark
import kotlinx.coroutines.flow.Flow

interface PoemSentenceRepository {
    fun getNextId(id: Int): Flow<Int?>
    fun getPrevId(id: Int): Flow<Int?>
    fun searchSentencesList(query: String): Flow<PagingData<PoemSentenceEntity>>
    fun getSentence(id: Int): Flow<PoemSentenceWithBookmark>
    suspend fun getSearchNextId(id: Int, query: String): Int
    suspend fun getSearchPrevId(id: Int, query: String): Int
    fun collections(): Flow<PagingData<PoemSentenceWithBookmark>>
    suspend fun collect(entity: PoemSentenceCollectionEntity)
    suspend fun uncollect(id: Int)
    fun isCollect(id: Int): Flow<PoemSentenceCollectionEntity?>
    fun getCollectionNextId(collectedAt: Long): Flow<Int?>
    fun getCollectionPrevId(collectedAt: Long): Flow<Int?>
}