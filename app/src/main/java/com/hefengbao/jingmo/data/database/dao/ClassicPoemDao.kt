package com.hefengbao.jingmo.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hefengbao.jingmo.data.database.entity.ClassicPoemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ClassicPoemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ClassicPoemEntity)

    @Query("select * from classic_poems where id = (select p.id from classic_poems p order by random() limit 1) limit 1")
    fun random(): Flow<ClassicPoemEntity>

    @Query("select count(p.id) from classic_poems p")
    fun total(): Flow<Int>
}