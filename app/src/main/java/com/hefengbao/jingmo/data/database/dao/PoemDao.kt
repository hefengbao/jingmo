package com.hefengbao.jingmo.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.hefengbao.jingmo.data.database.entity.PoemEntity
import com.hefengbao.jingmo.data.database.entity.PoemWithWriterAndTags

@Dao
interface PoemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(poemEntity: PoemEntity)

    @Transaction
    @Query("select * from poems where id = :id")
    suspend fun getPoem(id: Long): PoemWithWriterAndTags

    @Query("select id from poems where id > :id order by id asc limit 1")
    suspend fun getNextId(id: Long): Long

    @Query("select id from poems where id < :id order by id desc limit 1")
    suspend fun getPrevId(id: Long): Long
}