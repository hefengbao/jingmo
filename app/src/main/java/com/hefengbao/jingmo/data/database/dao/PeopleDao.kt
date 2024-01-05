package com.hefengbao.jingmo.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hefengbao.jingmo.data.database.entity.PeopleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PeopleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entities: List<PeopleEntity>)

    @Query("select * from people where id = :id limit 1")
    fun getPeople(id: Int): Flow<PeopleEntity>
}