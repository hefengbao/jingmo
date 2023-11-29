package com.hefengbao.jingmo.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hefengbao.jingmo.data.database.entity.TongueTwisterEntity
import com.hefengbao.jingmo.data.database.model.SimpleTongueTwister
import kotlinx.coroutines.flow.Flow

@Dao
interface TongueTwisterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: TongueTwisterEntity)

    @Query("select * from tongue_twisters where id = :id")
    fun getTongueTwister(id: Int): Flow<TongueTwisterEntity>

    @Query("select id from tongue_twisters where id > :id order by id asc limit 1")
    suspend fun getNextId(id: Int): Int

    @Query("select id from tongue_twisters where id < :id order by id desc limit 1")
    suspend fun getPrevId(id: Int): Int

    @Query("select id,title from tongue_twisters order by id asc")
    fun getTongueTwisterList(): Flow<List<SimpleTongueTwister>>
}