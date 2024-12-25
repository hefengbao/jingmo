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
import com.hefengbao.jingmo.data.database.entity.classicalliterature.WritingCollectionEntity
import com.hefengbao.jingmo.data.database.entity.classicalliterature.WritingEntity
import com.hefengbao.jingmo.data.model.IdTitle
import kotlinx.coroutines.flow.Flow

@Dao
interface ClassicalLiteratureWritingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: WritingEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entities: List<WritingEntity>)

    @Query("select * from writings where id = :id limit 1")
    fun get(id: Int): Flow<WritingEntity>

    @Query("select * from writings where id = (select id from writings order by random() limit 1) limit 1")
    fun random(): Flow<WritingEntity>

    @Query("select * from writings w order by id asc")
    fun list(): PagingSource<Int, WritingEntity>

    @Query("select * from writings join writing_fts on writing_fts.rowid = writings.id where writing_fts match :query")
    fun search(query: String): PagingSource<Int, WritingEntity>

    @Query("select * from writings where author like '%'|| :author || '%' order by id asc")
    fun searchByAuthor(author: String): PagingSource<Int, WritingEntity>

    @Query("select id from writings where rowid > :id order by id asc limit 1")
    fun getNextId(id: Int): Flow<Int?>

    @Query("select id from writings where rowid < :id order by id desc limit 1")
    fun getPrevId(id: Int): Flow<Int?>

    @Query("select * from writing_collections c join writings w on c.id = w.rowid order by c.collected_at desc")
    fun collections(): PagingSource<Int, WritingEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun collect(entity: WritingCollectionEntity)

    @Query("delete from writing_collections where id = :id")
    suspend fun uncollect(id: Int)

    @Query("select * from writing_collections where id = :id")
    fun isCollect(id: Int): Flow<WritingCollectionEntity?>

    @Query("select count(id) from writings w")
    fun total(): Flow<Int>

    @Query("delete from writings")
    suspend fun clear()

    @Query("select Id as id, title2 as title from writings where title2 like :query order by id asc")
    fun getIdTitle(query: String): PagingSource<Int, IdTitle>

    @Query("select max(id) from writings")
    fun getMaxId(): Flow<Int>
}