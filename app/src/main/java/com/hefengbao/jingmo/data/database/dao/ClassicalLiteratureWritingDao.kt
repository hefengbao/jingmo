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
import com.hefengbao.jingmo.data.database.entity.classicalliterature.WritingEntity
import com.hefengbao.jingmo.data.model.IdTitle
import kotlinx.coroutines.flow.Flow

@Dao
interface ClassicalLiteratureWritingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: WritingEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entities: List<WritingEntity>)

    @Query("select * from classicalliterature_writing where id = :id limit 1")
    fun get(id: Int): Flow<WritingEntity?>

    @Query("select * from classicalliterature_writing where id in (:ids)")
    fun get(ids: List<Int>): Flow<List<WritingEntity>>

    @Query("select * from classicalliterature_writing where id = (select id from classicalliterature_writing order by random() limit 1) limit 1")
    fun random(): Flow<WritingEntity?>

    @Query("select * from classicalliterature_writing w order by id asc")
    fun list(): PagingSource<Int, WritingEntity>

    @Query("select * from classicalliterature_writing w join classicalliterature_writing_fts fts on fts.rowid = w.id where classicalliterature_writing_fts match :query")
    fun search(query: String): PagingSource<Int, WritingEntity>

    @Query("select * from classicalliterature_writing where author like '%'|| :author || '%' order by id asc")
    fun searchByAuthor(author: String): PagingSource<Int, WritingEntity>

    @Query("select id from classicalliterature_writing where rowid > :id order by id asc limit 1")
    fun getNextId(id: Int): Flow<Int?>

    @Query("select id from classicalliterature_writing where rowid < :id order by id desc limit 1")
    fun getPrevId(id: Int): Flow<Int?>

    @Query("select w.* from bookmarks b join classicalliterature_writing w on b.bookmarkable_id = w.id and b.bookmarkable_model = 'classicalliterature_writing' order by b.id desc")
    fun bookmarks(): PagingSource<Int, WritingEntity>

    @Query("select count(id) from classicalliterature_writing w")
    fun total(): Flow<Int>

    @Query("delete from classicalliterature_writing")
    suspend fun clear()

    @Query("select Id as id, title2 as title from classicalliterature_writing where title2 like :query order by id asc")
    fun getIdTitle(query: String): PagingSource<Int, IdTitle>

    @Query("select max(id) from classicalliterature_writing")
    fun getMaxId(): Flow<Int>
}