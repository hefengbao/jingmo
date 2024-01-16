package com.hefengbao.jingmo.data.repository

import androidx.paging.PagingData
import com.hefengbao.jingmo.data.database.entity.WritingEntity
import com.hefengbao.jingmo.data.database.model.SimpleWritingInfo
import kotlinx.coroutines.flow.Flow

interface WritingRepository {
    fun get(id: Int): Flow<WritingEntity>
    fun list(): Flow<PagingData<WritingEntity>>
    fun search(query: String): Flow<PagingData<SimpleWritingInfo>>
    fun searchByAuthor(author: String): Flow<PagingData<SimpleWritingInfo>>
    suspend fun getNextId(id: Int): Int
    suspend fun getPrevId(id: Int): Int
    suspend fun getNextId(id: Int, author: String): Int
    suspend fun getPrevId(id: Int, author: String): Int
    suspend fun getSearchNextId(id: Int, query: String): Int
    suspend fun getSearchPrevId(id: Int, query: String): Int
}