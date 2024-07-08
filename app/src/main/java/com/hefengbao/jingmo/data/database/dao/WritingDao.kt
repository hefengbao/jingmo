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
import com.hefengbao.jingmo.data.database.entity.WritingCollectionEntity
import com.hefengbao.jingmo.data.database.entity.WritingEntity
import com.hefengbao.jingmo.data.database.model.SimpleWritingInfo
import com.hefengbao.jingmo.data.database.model.WritingBookmarkSimpleInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface WritingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: WritingEntity)

    @Query("select w.rowid, w.* from writings w where w.rowid = :id limit 1")
    fun get(id: Int): Flow<WritingEntity>

    @Query("select w.rowid, w.* from writings w where w.rowid = (select rowid from writings where type match  '词' or type match '律诗' or type match  '绝句' order by random() limit 1) limit 1")
    fun random(): Flow<WritingEntity>

    @Query("select rowid, w.* from writings w order by rowid asc")
    fun list(): PagingSource<Int, WritingEntity>

    @Query("select rowid,w.dynasty,w.author,w.type,w.title_content as title from writings w where w.author match :query or w.title_content match :query or w.content match :query")
    fun search(query: String): PagingSource<Int, SimpleWritingInfo>

    @Query("select rowid,w.dynasty,w.author,w.type,w.title_content as title from writings w where w.author match :author order by rowid asc")
    fun searchByAuthor(author: String): PagingSource<Int, SimpleWritingInfo>

    @Query("select rowid from writings where rowid > :id order by rowid asc limit 1")
    fun getNextId(id: Int): Flow<Int?>

    @Query("select rowid from writings where rowid < :id order by rowid desc limit 1")
    fun getPrevId(id: Int): Flow<Int?>

    @Query("select c.id as id,c.collected_at as collected_at,w.author as author,w.dynasty as dynasty,w.type as type,w.title_content as title from writing_collections c join writings w on c.id = w.rowid order by c.collected_at desc")
    fun collections(): PagingSource<Int, WritingBookmarkSimpleInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun collect(entity: WritingCollectionEntity)

    @Query("delete from writing_collections where id = :id")
    suspend fun uncollect(id: Int)

    @Query("select * from writing_collections where id = :id")
    fun isCollect(id: Int): Flow<WritingCollectionEntity?>

    @Query("select count(w.rowid) from writings w")
    fun total(): Flow<Int>
}