package com.hefengbao.wenqu.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.hefengbao.wenqu.data.database.entity.WriterEntity

@Dao
interface WriterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(writerEntity: WriterEntity)
}