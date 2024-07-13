/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hefengbao.jingmo.data.database.entity.chinese.WisecrackCollectionEntity
import com.hefengbao.jingmo.data.database.entity.chinese.WisecrackEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChineseWisecrackDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: WisecrackEntity)

    @Query("select w.* from chinese_wisecracks w  where w.id = :id")
    fun getChineseWisecrack(id: Int): Flow<WisecrackEntity>

    @Query("select id from chinese_wisecracks where id > :id order by id asc limit 1")
    fun getNextId(id: Int): Flow<Int?>

    @Query("select id from chinese_wisecracks where id < :id order by id desc limit 1")
    fun getPrevId(id: Int): Flow<Int?>

    @Query("select * from chinese_wisecracks where riddle like :query or answer like :query")
    fun searchWisecrackList(query: String): Flow<List<WisecrackEntity>>

    @Query("select w.* from chinese_wisecrack_collections c join chinese_wisecracks w on c.id = w.id order by c.collected_at desc")
    fun collections(): PagingSource<Int, WisecrackEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun collect(entity: WisecrackCollectionEntity)

    @Query("delete from chinese_wisecrack_collections where id = :id")
    suspend fun uncollect(id: Int)

    @Query("select * from chinese_wisecrack_collections where id = :id")
    fun isCollect(id: Int): Flow<WisecrackCollectionEntity?>

    @Query("select count(*) from chinese_wisecracks")
    fun total(): Flow<Int>
}