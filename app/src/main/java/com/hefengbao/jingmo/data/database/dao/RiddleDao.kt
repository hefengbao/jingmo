package com.hefengbao.jingmo.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hefengbao.jingmo.data.database.entity.RiddleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RiddleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: RiddleEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entities: List<RiddleEntity>)
    @Query("select * from riddles where id = :id limit 1")
    fun getRiddle(id: Int): Flow<RiddleEntity>
    @Query("select id from riddles where id > :id order by id asc limit 1")
    suspend fun getNextId(id: Int): Int
    @Query("select id from riddles where id < :id order by id desc limit 1")
    suspend fun getPrevId(id: Int): Int
    @Query("select * from riddles where puzzle like :query order by id asc")
    fun searchResult(query: String): Flow<List<RiddleEntity>>
    @Query("select id from riddles where id > :id and puzzle like :query order by id asc limit 1")
    suspend fun getSearchNextId(id: Int, query: String): Int
    @Query("select id from riddles where id < :id and puzzle like :query order by id desc limit 1")
    suspend fun getSearchPrevId(id: Int, query: String): Int

}