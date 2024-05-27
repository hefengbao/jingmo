package com.hefengbao.jingmo.data.repository

import androidx.paging.PagingData
import com.hefengbao.jingmo.data.database.entity.WritingCollectionEntity
import com.hefengbao.jingmo.data.database.entity.WritingEntity
import com.hefengbao.jingmo.data.database.model.SimpleWritingInfo
import com.hefengbao.jingmo.data.database.model.WritingBookmarkSimpleInfo
import kotlinx.coroutines.flow.Flow

interface WritingRepository {
    fun get(id: Int): Flow<WritingEntity>
    fun random(): Flow<WritingEntity>
    fun list(): Flow<PagingData<WritingEntity>>
    fun search(query: String): Flow<PagingData<SimpleWritingInfo>>
    fun searchByAuthor(author: String): Flow<PagingData<SimpleWritingInfo>>
    fun getNextId(id: Int): Flow<Int?>
    fun getPrevId(id: Int): Flow<Int?>
    fun collections(): Flow<PagingData<WritingBookmarkSimpleInfo>>
    suspend fun collect(entity: WritingCollectionEntity)
    suspend fun uncollect(writingId: Int)
    fun isCollect(id: Int): Flow<WritingCollectionEntity?>
}