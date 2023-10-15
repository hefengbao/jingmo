package com.hefengbao.jingmo.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.hefengbao.jingmo.data.database.entity.PoemSentenceEntity
import com.hefengbao.jingmo.data.database.entity.SentenceWithPoem
import kotlinx.coroutines.flow.Flow

@Dao
interface PoemSentenceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: PoemSentenceEntity)

    @Transaction
    @Query("select * from poem_sentences where id = :id")
    suspend fun getSentence(id: Long): SentenceWithPoem

    @Query("select id from poem_sentences where id > :id order by id asc limit 1")
    suspend fun getNextId(id: Long): Long

    @Query("select id from poem_sentences where id < :id order by id desc limit 1")
    suspend fun getPrevId(id: Long): Long

    @Query("select * from poem_sentences where content like :query")
    fun searchSentencesList(query: String): Flow<List<PoemSentenceEntity>>
}