package com.hefengbao.jingmo.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hefengbao.jingmo.data.database.AppDatabase
import com.hefengbao.jingmo.data.database.entity.WritingEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WritingRepositoryImpl @Inject constructor(
    private val database: AppDatabase
) : WritingRepository {
    override fun get(id: Int): Flow<WritingEntity> = database.writingDao().get(id)
    override fun list(): Flow<PagingData<WritingEntity>> = Pager(
        config = PagingConfig(pageSize = 30),
        pagingSourceFactory = {  database.writingDao().list() }
    ).flow

    override fun search(query: String): Flow<PagingData<WritingEntity>> = Pager(
        config = PagingConfig(pageSize = 30),
        pagingSourceFactory = { database.writingDao().search("%$query%") }
    ).flow

    override fun searchByAuthor(author: String): Flow<PagingData<WritingEntity>> = Pager(
        config = PagingConfig(pageSize = 30),
        pagingSourceFactory = {
            database.writingDao().searchByAuthor(author)
        }
    ).flow

    override suspend fun getNextId(id: Int): Int = database.writingDao().getNextId(id)

    override suspend fun getNextId(id: Int, author: String): Int = database.writingDao().getNextId(id, author)

    override suspend fun getPrevId(id: Int): Int = database.writingDao().getPrevId(id)

    override suspend fun getPrevId(id: Int, author: String): Int = database.writingDao().getPrevId(id, author)

    override suspend fun getSearchNextId(id: Int, query: String): Int = database.writingDao().getSearchNextId(id, "%$query%")

    override suspend fun getSearchPrevId(id: Int, query: String): Int = database.writingDao().getSearchPrevId(id, "%$query%")
}