package com.hefengbao.wenqu.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.hefengbao.wenqu.data.database.entity.PoemSentenceEntity
import com.hefengbao.wenqu.data.database.entity.SentenceWithPoem

@Dao
interface PoemSentenceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: PoemSentenceEntity)

    @Transaction
    @Query("SELECT * FROM poem_sentences WHERE id = :id")
    suspend fun getSentence(id: Long): SentenceWithPoem

    @Query("select id from poem_sentences where id > :id limit 1")
    suspend fun getNextId(id: Long): Long

    @Query("select id from poem_sentences where id < :id limit 1")
    suspend fun getPrevId(id: Long): Long
}