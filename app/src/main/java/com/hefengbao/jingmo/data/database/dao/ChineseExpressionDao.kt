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
import com.hefengbao.jingmo.data.database.entity.chinese.ExpressionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChineseExpressionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ExpressionEntity)

    @Query("select * from chinese_expression where id = (select e.id from chinese_expression e order by random() limit 1) limit 1")
    fun random(): Flow<ExpressionEntity?>

    @Query("select * from chinese_expression where id = :id limit 1")
    fun get(id: Int): Flow<ExpressionEntity?>

    @Query("select * from chinese_expression where id in (:ids)")
    fun get(ids: List<Int>): Flow<List<ExpressionEntity>>

    @Query("select e.* from bookmarks b join chinese_expression e on b.bookmarkable_id = e.id and b.bookmarkable_model = 'chinese_expression' order by b.id desc")
    fun bookmarks(): PagingSource<Int, ExpressionEntity>

    @Query("select * from chinese_expression where word like :query")
    fun search(query: String): PagingSource<Int, ExpressionEntity>

    @Query("select count(*) from chinese_expression")
    fun total(): Flow<Int>

    @Query("delete from chinese_expression")
    suspend fun clear()
}