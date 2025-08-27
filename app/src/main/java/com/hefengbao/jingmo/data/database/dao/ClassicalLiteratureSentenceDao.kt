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
import com.hefengbao.jingmo.data.database.entity.classicalliterature.SentenceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ClassicalLiteratureSentenceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: SentenceEntity)

    @Query("select * from classicalliterature_sentence where id = :id limit 1")
    fun get(id: Int): Flow<SentenceEntity?>

    @Query("select * from classicalliterature_sentence where id in (:ids)")
    fun get(ids: List<Int>): Flow<List<SentenceEntity>>

    @Query("select * from classicalliterature_sentence where id = (select id from classicalliterature_sentence order by random() limit 1)")
    fun random(): Flow<SentenceEntity?>

    @Query("select id from classicalliterature_sentence where id > :id order by id asc limit 1")
    fun getNextId(id: Int): Flow<Int?>

    @Query("select id from classicalliterature_sentence where id < :id order by id desc limit 1")
    fun getPrevId(id: Int): Flow<Int?>

    @Query("select * from classicalliterature_sentence where content like :query")
    fun search(query: String): PagingSource<Int, SentenceEntity>

    @Query("select s.* from bookmarks b join classicalliterature_sentence s on b.bookmarkable_id = s.id and b.bookmarkable_model = 'classicalliterature_sentence' order by b.id desc")
    fun bookmarks(): PagingSource<Int, SentenceEntity>

    @Query("select count(*) from classicalliterature_sentence")
    fun total(): Flow<Int>

    @Query("delete from classicalliterature_sentence")
    suspend fun clear()
}