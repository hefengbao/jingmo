package com.hefengbao.wenqu.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hefengbao.wenqu.data.database.entity.IdiomEntity

@Dao
interface IdiomDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: IdiomEntity)

    @Query("SELECT * FROM idioms WHERE id = :id")
    suspend fun getIdiom(id: Long): IdiomEntity

    @Query("select id from idioms where id > :id limit 1")
    suspend fun getNextId(id: Long): Long

    @Query("select id from idioms where id < :id limit 1")
    suspend fun getPrevId(id: Long): Long
}