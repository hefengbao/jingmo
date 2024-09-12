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
import com.hefengbao.jingmo.data.database.entity.classicalliterature.SentenceCollectionEntity
import com.hefengbao.jingmo.data.database.entity.classicalliterature.SentenceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ClassicalLiteratureSentenceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: SentenceEntity)

    @Query("select p.* from poem_sentences p where p.id = :id")
    fun get(id: Int): Flow<SentenceEntity>

    @Query("select p.* from poem_sentences p where p.id = (select id from poem_sentences order by random() limit 1)")
    fun random(): Flow<SentenceEntity>

    @Query("select id from poem_sentences where id > :id order by id asc limit 1")
    fun getNextId(id: Int): Flow<Int?>

    @Query("select id from poem_sentences where id < :id order by id desc limit 1")
    fun getPrevId(id: Int): Flow<Int?>

    @Query("select * from poem_sentences where content like :query")
    fun search(query: String): PagingSource<Int, SentenceEntity>

    @Query("select p.* from poem_sentence_collections c join poem_sentences p on c.id = p.id  order by c.collected_at desc")
    fun collections(): PagingSource<Int, SentenceEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun collect(entity: SentenceCollectionEntity)

    @Query("delete from poem_sentence_collections where id = :id")
    suspend fun uncollect(id: Int)

    @Query("select * from poem_sentence_collections where id = :id")
    fun isCollect(id: Int): Flow<SentenceCollectionEntity?>


    @Query("select count(*) from poem_sentences")
    fun total(): Flow<Int>
}