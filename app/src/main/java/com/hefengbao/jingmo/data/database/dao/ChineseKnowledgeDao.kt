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
import androidx.room.Transaction
import com.hefengbao.jingmo.data.database.entity.chinese.KnowledgeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChineseKnowledgeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: KnowledgeEntity)

    @Query("select * from chinese_knowledge where id = :id limit 1")
    fun get(id: Int): Flow<KnowledgeEntity?>

    @Query("select k.*from chinese_knowledge k where k.id = (select id from chinese_knowledge order by random() limit 1) limit 1")
    fun random(): Flow<KnowledgeEntity?>

    @Query("select id from chinese_knowledge where id > :id order by id asc limit 1")
    fun getNextId(id: Int): Flow<Int?>

    @Query("select id from chinese_knowledge where id < :id order by id desc limit 1")
    fun getPrevId(id: Int): Flow<Int?>

    @Transaction
    @Query("select * from chinese_knowledge join chinese_knowledge_fts on chinese_knowledge_fts.rowid = chinese_knowledge.id where chinese_knowledge_fts match :query")
    fun search(query: String): Flow<List<KnowledgeEntity>>

    @Query("select k.* from bookmarks b join chinese_knowledge k on b.bookmarkable_id = k.id and b.bookmarkable_model = 'chinese_knowledge' order by b.id desc")
    fun bookmarks(): PagingSource<Int, KnowledgeEntity>

    @Query("select count(*) from chinese_knowledge")
    fun total(): Flow<Int>

    @Query("delete from chinese_knowledge")
    suspend fun clear()
}