package com.hefengbao.jingmo.data.repository

import androidx.paging.PagingData
import com.hefengbao.jingmo.data.database.entity.WritingEntity
import kotlinx.coroutines.flow.Flow

interface WritingRepository {
    fun get(id: Int): Flow<WritingEntity>
    fun list(): Flow<PagingData<WritingEntity>>
    fun search(query: String): Flow<PagingData<WritingEntity>>
    fun searchByAuthor(author: String): Flow<PagingData<WritingEntity>>
    suspend fun getNextId(id: Int): Int
    suspend fun getPrevId(id: Int): Int
    suspend fun getNextId(id: Int, author: String): Int
    suspend fun getPrevId(id: Int, author: String): Int
    suspend fun getSearchNextId(id: Int, query: String): Int
    suspend fun getSearchPrevId(id: Int, query: String): Int
}