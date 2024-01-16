package com.hefengbao.jingmo.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hefengbao.jingmo.data.database.entity.WritingEntity
import com.hefengbao.jingmo.data.database.model.SimpleWritingInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface WritingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: WritingEntity)

    @Query("select rowid, w.* from writings w where rowid = :id limit 1")
    fun get(id: Int): Flow<WritingEntity>

    @Query("select rowid, w.* from writings w order by rowid asc")
    fun list(): PagingSource<Int, WritingEntity>

    @Query("select rowid,w.dynasty,w.author,w.type,w.title_content as title,w.content from writings w where w.author like :query or w.title_content like :query or w.content like :query order by rowid asc")
    fun search(query: String): PagingSource<Int, SimpleWritingInfo>

    @Query("select rowid,w.dynasty,w.author,w.type,w.title_content as title,w.content from writings w where w.author = :author order by rowid asc")
    fun searchByAuthor(author: String): PagingSource<Int, SimpleWritingInfo>

    @Query("select rowid from writings where author = :author and rowid > :id order by rowid asc limit 1")
    suspend fun getNextId(id: Int, author: String): Int

    @Query("select rowid from writings where author = :author and rowid < :id order by rowid desc limit 1")
    suspend fun getPrevId(id: Int, author: String): Int

    @Query("select rowid from writings where rowid > :id order by rowid asc limit 1")
    suspend fun getNextId(id: Int): Int

    @Query("select rowid from writings where rowid < :id order by rowid desc limit 1")
    suspend fun getPrevId(id: Int): Int


    @Query("select rowid from writings where (author like :query or title_content like :query or content like :query) and rowid > :id order by rowid asc limit 1")
    suspend fun getSearchNextId(id: Int, query: String): Int

    @Query("select rowid from writings where (author like :query or title_content like :query or content like :query) and rowid < :id order by rowid desc limit 1")
    suspend fun getSearchPrevId(id: Int, query: String): Int
}