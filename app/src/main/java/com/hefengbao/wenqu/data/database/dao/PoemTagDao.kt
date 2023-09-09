package com.hefengbao.wenqu.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.hefengbao.wenqu.data.database.entity.PoemTagCrossRef

@Dao
interface PoemTagDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entity: PoemTagCrossRef)
}