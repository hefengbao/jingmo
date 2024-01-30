package com.hefengbao.jingmo.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hefengbao.jingmo.data.database.entity.PoemSentenceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PoemSentenceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: PoemSentenceEntity)

    @Query("select * from poem_sentences where id = :id")
    fun getSentence(id: Int): Flow<PoemSentenceEntity>

    @Query("select id from poem_sentences where id > :id order by id asc limit 1")
    fun getNextId(id: Int): Flow<Int?>

    @Query("select id from poem_sentences where id < :id order by id desc limit 1")
    fun getPrevId(id: Int): Flow<Int?>

    @Query("select * from poem_sentences where content like :query")
    fun searchSentencesList(query: String): PagingSource<Int, PoemSentenceEntity>

    @Query("select id from poem_sentences where id > :id and content like :query order by id asc limit 1")
    suspend fun getSearchNextId(id: Int, query: String): Int

    @Query("select id from poem_sentences where id < :id and content like :query order by id desc limit 1")
    suspend fun getSearchPrevId(id: Int, query: String): Int
}