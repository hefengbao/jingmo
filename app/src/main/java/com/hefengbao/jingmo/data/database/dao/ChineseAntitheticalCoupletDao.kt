/*
 *  This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 *  For the full copyright and license information, please view the LICENSE
 *  file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hefengbao.jingmo.data.database.entity.chinese.AntitheticalCoupletCollectionEntity
import com.hefengbao.jingmo.data.database.entity.chinese.AntitheticalCoupletEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChineseAntitheticalCoupletDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: AntitheticalCoupletEntity)

    @Query("select * from chinese_antithetical_couplets where id = :id")
    fun get(id: Int): Flow<AntitheticalCoupletEntity>

    @Query("select a.* from chinese_antithetical_couplets a where a.id = (select id from chinese_antithetical_couplets order by random() limit 1)")
    fun random(): Flow<AntitheticalCoupletEntity>

    @Query("select id from chinese_antithetical_couplets where id > :id order by id asc limit 1")
    fun getNextId(id: Int): Flow<Int?>

    @Query("select id from chinese_antithetical_couplets where id < :id order by id desc limit 1")
    fun getPrevId(id: Int): Flow<Int?>

    @Query("select * from chinese_antithetical_couplets order by id asc")
    fun list(): PagingSource<Int, AntitheticalCoupletEntity>

    @Query("select * from chinese_antithetical_couplets where body like :query order by id asc")
    fun search(query: String): PagingSource<Int, AntitheticalCoupletEntity>

    @Query("select i.* from chinese_antithetical_couplet_collections c join chinese_antithetical_couplets i on c.id = i.id order by collected_at desc")
    fun collections(): PagingSource<Int, AntitheticalCoupletEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun collect(entity: AntitheticalCoupletCollectionEntity)

    @Query("delete from chinese_antithetical_couplet_collections where id = :id")
    suspend fun uncollect(id: Int)

    @Query("select * from chinese_antithetical_couplet_collections where id = :id")
    fun isCollect(id: Int): Flow<AntitheticalCoupletCollectionEntity?>

    @Query("select count(*) from chinese_antithetical_couplets")
    fun total(): Flow<Int>

    @Query("delete from chinese_antithetical_couplets")
    suspend fun clear()
}