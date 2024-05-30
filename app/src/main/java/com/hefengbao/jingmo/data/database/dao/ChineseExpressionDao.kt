package com.hefengbao.jingmo.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hefengbao.jingmo.data.database.entity.ChineseExpressionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChineseExpressionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ChineseExpressionEntity)

    @Query("select * from chinese_expressions where id = (select e.id from chinese_expressions e order by random() limit 1) limit 1")
    fun random(): Flow<ChineseExpressionEntity>

    @Query("select * from chinese_expressions where id = :id")
    fun get(id: Int): Flow<ChineseExpressionEntity>

    @Query("select * from chinese_expressions where word like :query")
    fun search(query: String): PagingSource<Int, ChineseExpressionEntity>

    @Query("select count(*) from chinese_expressions")
    fun total(): Flow<Int>
}