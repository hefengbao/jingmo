package com.hefengbao.jingmo.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hefengbao.jingmo.data.database.entity.PeopleEntity
import com.hefengbao.jingmo.data.database.model.SimplePeopleInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface PeopleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: PeopleEntity)

    @Query("select * from people where id = (select p.id from people p order by random() limit 1) limit 1")
    fun random(): Flow<PeopleEntity>

    @Query("select * from people where id = :id limit 1")
    fun getPeopleById(id: Int): Flow<PeopleEntity>

    @Query("select * from people where name = :name limit 1")
    fun getPeopleByName(name: String): Flow<PeopleEntity>

    @Query("select id,name,dynasty from people where name like :query")
    fun search(query: String): Flow<List<SimplePeopleInfo>>

    @Query("select count(*) from people")
    fun total(): Flow<Int>
}