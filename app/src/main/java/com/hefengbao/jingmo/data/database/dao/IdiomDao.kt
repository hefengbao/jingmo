package com.hefengbao.jingmo.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hefengbao.jingmo.data.database.entity.IdiomEntity

@Dao
interface IdiomDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: IdiomEntity)

    @Query("select * from idioms where id = :id")
    suspend fun getIdiom(id: Long): IdiomEntity

    @Query("select id from idioms where id > :id order by id asc limit 1")
    suspend fun getNextId(id: Long): Long

    @Query("select id from idioms where id < :id order by id desc limit 1")
    suspend fun getPrevId(id: Long): Long
}