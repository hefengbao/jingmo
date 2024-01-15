package com.hefengbao.jingmo.data.repository

import androidx.paging.PagingData
import com.hefengbao.jingmo.data.database.entity.PoemSentenceEntity
import kotlinx.coroutines.flow.Flow

interface PoemSentenceRepository {
    suspend fun getNextId(id: Int): Int
    suspend fun getPrevId(id: Int): Int
    fun searchSentencesList(query: String): Flow<PagingData<PoemSentenceEntity>>
    fun getSentence(id: Int): Flow<PoemSentenceEntity>
    suspend fun getSearchNextId(id: Int, query: String): Int
    suspend fun getSearchPrevId(id: Int, query: String): Int
}