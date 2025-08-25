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

    @Query("select * from chinese_tonguetwister where id = :id")
    fun get(id: Int): Flow<TongueTwisterEntity?>

    @Query("select t.* from chinese_tonguetwister t where t.id = (select id from chinese_tonguetwister order by random() limit 1)")
    fun random(): Flow<TongueTwisterEntity?>

    @Query("select id from chinese_tonguetwister where id > :id order by id asc limit 1")
    fun getNextId(id: Int): Flow<Int?>

    @Query("select id from chinese_tonguetwister where id < :id order by id desc limit 1")
    fun getPrevId(id: Int): Flow<Int?>

    @Query("select * from chinese_tonguetwister order by id asc")
    fun list(): Flow<List<TongueTwisterEntity>>

    @Query("select count(*) from chinese_tonguetwister")
    fun total(): Flow<Int>

    @Query("delete from chinese_tonguetwister")
    suspend fun clear()
}