package com.hefengbao.jingmo.data.repository

import androidx.paging.PagingData
import com.hefengbao.jingmo.data.database.entity.WritingCollectionEntity
import com.hefengbao.jingmo.data.database.entity.WritingEntity
import com.hefengbao.jingmo.data.database.model.SimpleWritingInfo
import com.hefengbao.jingmo.data.database.model.WritingBookmarkSimpleInfo
import com.hefengbao.jingmo.data.database.model.WritingWithBookmark
import kotlinx.coroutines.flow.Flow

interface WritingRepository {
    fun get(id: Int): Flow<WritingWithBookmark>
    fun random(): Flow<WritingWithBookmark>
    fun list(): Flow<PagingData<WritingEntity>>
    fun search(query: String): Flow<PagingData<SimpleWritingInfo>>
    fun searchByAuthor(author: String): Flow<PagingData<SimpleWritingInfo>>
    fun getNextId(id: Int): Flow<Int?>
    fun getPrevId(id: Int): Flow<Int?>
    fun getNextId(id: Int, author: String): Flow<Int?>
    fun getPrevId(id: Int, author: String): Flow<Int?>
    fun getSearchNextId(id: Int, query: String): Flow<Int?>
    fun getSearchPrevId(id: Int, query: String): Flow<Int?>
    fun collections(): Flow<PagingData<WritingBookmarkSimpleInfo>>
    suspend fun collect(entity: WritingCollectionEntity)
    suspend fun uncollect(writingId: Int)
    fun isCollect(id: Int): Flow<WritingCollectionEntity?>
    fun getCollectionNextId(collectedAt: Long): Flow<Int?>
    fun getCollectionPrevId(collectedAt: Long): Flow<Int?>
}