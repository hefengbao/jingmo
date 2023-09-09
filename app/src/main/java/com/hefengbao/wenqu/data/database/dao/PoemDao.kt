package com.hefengbao.wenqu.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.hefengbao.wenqu.data.database.entity.PoemEntity
import com.hefengbao.wenqu.data.database.entity.PoemWithWriterAndTags

@Dao
interface PoemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(poemEntity: PoemEntity)

    @Transaction
    @Query("SELECT * FROM poems WHERE id = :id")
    suspend fun getPoem(id: Long): PoemWithWriterAndTags

    @Query("select id from poems where id > :id limit 1")
    suspend fun getNextId(id: Long): Long

    @Query("select id from poems where id < :id limit 1")
    suspend fun getPrevId(id: Long): Long
}