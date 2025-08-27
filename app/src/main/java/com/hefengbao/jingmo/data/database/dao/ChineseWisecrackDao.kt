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
import com.hefengbao.jingmo.data.database.entity.chinese.WisecrackEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChineseWisecrackDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: WisecrackEntity)

    @Query("select w.* from chinese_wisecrack w  where w.id = :id limit 1")
    fun get(id: Int): Flow<WisecrackEntity?>

    @Query("select w.* from chinese_wisecrack w  where w.id in (:ids)")
    fun get(ids: List<Int>): Flow<List<WisecrackEntity>>

    @Query("select w.* from chinese_wisecrack w where w.id = (select id from chinese_wisecrack order by random() limit 1)")
    fun random(): Flow<WisecrackEntity?>

    @Query("select id from chinese_wisecrack where id > :id order by id asc limit 1")
    fun getNextId(id: Int): Flow<Int?>

    @Query("select id from chinese_wisecrack where id < :id order by id desc limit 1")
    fun getPrevId(id: Int): Flow<Int?>

    @Query("select * from chinese_wisecrack where riddle like :query or answer like :query")
    fun search(query: String): Flow<List<WisecrackEntity>>

    @Query("select w.* from bookmarks b join chinese_wisecrack w on b.bookmarkable_id = w.id and b.bookmarkable_model = 'chinese_wisecrack' order by b.id desc")
    fun bookmarks(): PagingSource<Int, WisecrackEntity>

    @Query("select count(*) from chinese_wisecrack")
    fun total(): Flow<Int>

    @Query("delete from chinese_wisecrack")
    suspend fun clear()
}