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
import androidx.room.Transaction
import com.hefengbao.jingmo.data.database.entity.chinese.ModernPoetryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChineseModernPoetryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ModernPoetryEntity)

    @Query("select * from chinese_modernpoetry where id = :id limit 1")
    fun get(id: Int): Flow<ModernPoetryEntity?>

    @Query("select p.*from chinese_modernpoetry p where p.id = (select id from chinese_modernpoetry order by random() limit 1) limit 1")
    fun random(): Flow<ModernPoetryEntity?>

    @Query("select id from chinese_modernpoetry where id > :id order by id asc limit 1")
    fun getNextId(id: Int): Flow<Int?>

    @Query("select id from chinese_modernpoetry where id < :id order by id desc limit 1")
    fun getPrevId(id: Int): Flow<Int?>

    @Transaction
    @Query("select * from chinese_modernpoetry join chinese_modernpoetry_fts on chinese_modernpoetry_fts.rowid = chinese_modernpoetry.id where chinese_modernpoetry_fts match :query")
    fun search(query: String): Flow<List<ModernPoetryEntity>>

    @Query("select p.* from bookmarks b join chinese_modernpoetry p on b.bookmarkable_id = p.id and b.bookmarkable_model = 'chinese_modernpoetry' order by b.id desc")
    fun bookmarks(): PagingSource<Int, ModernPoetryEntity>

    @Query("select count(*) from chinese_modernpoetry")
    fun total(): Flow<Int>

    @Query("delete from chinese_modernpoetry")
    suspend fun clear()
}