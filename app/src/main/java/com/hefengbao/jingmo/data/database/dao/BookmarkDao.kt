package com.hefengbao.jingmo.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hefengbao.jingmo.data.database.entity.BookmarkEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(entity: BookmarkEntity)

    @Query("delete from bookmarks where bookmarkable_id = :id and bookmarkable_model = :model")
    suspend fun cancel(id: Int, model: String)

    @Query("select * from bookmarks where bookmarkable_id = :id and bookmarkable_model = :model limit 1")
    fun bookmarked(id: Int, model: String): Flow<BookmarkEntity?>

    @Query("select * from bookmarks order by id desc")
    fun bookmarks(): PagingSource<Int, BookmarkEntity>
}