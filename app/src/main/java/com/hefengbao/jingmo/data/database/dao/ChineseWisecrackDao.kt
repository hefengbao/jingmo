package com.hefengbao.jingmo.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hefengbao.jingmo.data.database.entity.ChineseWisecrackEntity

@Dao
interface ChineseWisecrackDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ChineseWisecrackEntity)

    @Query("select * from chinese_wisecracks where id = :id")
    suspend fun getChineseWisecrack(id: Long): ChineseWisecrackEntity

    @Query("select id from chinese_wisecracks where id > :id order by id asc limit 1")
    suspend fun getNextId(id: Long): Long

    @Query("select id from chinese_wisecracks where id < :id order by id desc limit 1")
    suspend fun getPrevId(id: Long): Long
}