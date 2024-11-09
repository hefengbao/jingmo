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
import com.hefengbao.jingmo.data.database.entity.chinese.ModernPoetryCollectionEntity
import com.hefengbao.jingmo.data.database.entity.chinese.ModernPoetryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChineseModernPoetryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ModernPoetryEntity)

    @Query("select * from chinese_modern_poetry where id = :id limit 1")
    fun get(id: Int): Flow<ModernPoetryEntity>

    @Query("select p.*from chinese_modern_poetry p where p.id = (select id from chinese_modern_poetry order by random() limit 1) limit 1")
    fun random(): Flow<ModernPoetryEntity>

    @Query("select id from chinese_modern_poetry where id > :id order by id asc limit 1")
    fun getNextId(id: Int): Flow<Int?>

    @Query("select id from chinese_modern_poetry where id < :id order by id desc limit 1")
    fun getPrevId(id: Int): Flow<Int?>

    @Transaction
    @Query("select * from chinese_modern_poetry join chinese_modern_poetry_fts on chinese_modern_poetry_fts.rowid = chinese_modern_poetry.id where chinese_modern_poetry_fts match :query")
    fun search(query: String): Flow<List<ModernPoetryEntity>>

    @Query("select p.* from chinese_modern_poetry_collections c join chinese_modern_poetry p on c.id = p.id order by collected_at desc")
    fun collections(): PagingSource<Int, ModernPoetryEntity>

    @Query("select count(*) from chinese_modern_poetry")
    fun total(): Flow<Int>

    @Query("select * from chinese_modern_poetry_collections where id = :id")
    fun isCollect(id: Int): Flow<ModernPoetryCollectionEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun collect(entity: ModernPoetryCollectionEntity)

    @Query("delete from chinese_modern_poetry_collections where id = :id")
    suspend fun uncollect(id: Int)

    @Query("delete from chinese_modern_poetry")
    suspend fun clear()
}