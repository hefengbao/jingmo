package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.data.database.entity.BookmarkEntity
import kotlinx.coroutines.flow.Flow

interface BookmarkRepository {
    suspend fun add(entity: BookmarkEntity)
    suspend fun cancel(id: Int, model: String)
    fun bookmarked(id: Int, model: String): Flow<BookmarkEntity?>
}