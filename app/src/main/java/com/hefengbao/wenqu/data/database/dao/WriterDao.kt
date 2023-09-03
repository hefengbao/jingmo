package com.hefengbao.wenqu.data.database.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.hefengbao.wenqu.data.database.entity.PoemEntity
import com.hefengbao.wenqu.data.database.entity.TagEntity

interface TagDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tagEntity: TagEntity)
}