package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.data.database.dao.BookmarkDao
import com.hefengbao.jingmo.data.database.entity.BookmarkEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookmarkRepositoryImpl @Inject constructor(
    val dao: BookmarkDao
) : BookmarkRepository {
    override suspend fun add(entity: BookmarkEntity) = dao.add(entity)

    override suspend fun cancel(id: Int, model: String) = dao.cancel(id, model)

    override fun bookmarked(
        id: Int,
        model: String
    ): Flow<BookmarkEntity?> = dao.bookmarked(id, model)
}