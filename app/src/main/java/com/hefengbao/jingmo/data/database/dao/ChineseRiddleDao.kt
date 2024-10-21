/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hefengbao.jingmo.data.database.entity.chinese.RiddleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChineseRiddleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: RiddleEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entities: List<RiddleEntity>)

    @Query("select * from riddles where id = :id limit 1")
    fun get(id: Int): Flow<RiddleEntity>

    @Query("select r.* from riddles r where r.id = (select id from riddles order by random() limit 1)")
    fun random(): Flow<RiddleEntity>

    @Query("select id from riddles where id > :id order by id asc limit 1")
    fun getNextId(id: Int): Flow<Int?>

    @Query("select id from riddles where id < :id order by id desc limit 1")
    fun getPrevId(id: Int): Flow<Int?>

    @Query("select * from riddles where puzzle like :query order by id asc")
    fun search(query: String): Flow<List<RiddleEntity>>

    @Query("delete from riddles")
    suspend fun clear()

    @Query("select count(*) from riddles")
    fun total(): Flow<Int>
}