package com.hefengbao.jingmo.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hefengbao.jingmo.data.database.entity.IdiomEntity
import com.hefengbao.jingmo.data.database.model.SimpleIdiomInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface IdiomDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: IdiomEntity)

    @Query("select * from idioms where id = :id")
    suspend fun getIdiom(id: Int): IdiomEntity

    @Query("select id from idioms where id > :id order by id asc limit 1")
    suspend fun getNextId(id: Int): Int

    @Query("select id from idioms where id < :id order by id desc limit 1")
    suspend fun getPrevId(id: Int): Int

    @Query("select id, word from idioms order by id asc")
    fun getSimpleIdiomInfoList(): Flow<List<SimpleIdiomInfo>>

    @Query("select id, word from idioms where word like :query order by id asc")
    fun searchSimpleIdiomInfoList(query: String): Flow<List<SimpleIdiomInfo>>

    @Query("select id from idioms where id > :id and word like :query order by id asc limit 1")
    suspend fun getSearchNextId(id: Int, query: String): Int

    @Query("select id from idioms where id < :id and word like :query order by id desc limit 1")
    suspend fun getSearchPrevId(id: Int, query: String): Int
}