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
import com.hefengbao.jingmo.data.database.entity.chinese.TongueTwisterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChineseTongueTwisterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: TongueTwisterEntity)

    @Query("select * from tongue_twisters where id = :id")
    fun getTongueTwister(id: Int): Flow<TongueTwisterEntity>

    @Query("select id from tongue_twisters where id > :id order by id asc limit 1")
    suspend fun getNextId(id: Int): Int

    @Query("select id from tongue_twisters where id < :id order by id desc limit 1")
    suspend fun getPrevId(id: Int): Int

    @Query("select * from tongue_twisters order by id asc")
    fun getTongueTwisterList(): Flow<List<TongueTwisterEntity>>

    @Query("select count(*) from tongue_twisters")
    fun total(): Flow<Int>
}