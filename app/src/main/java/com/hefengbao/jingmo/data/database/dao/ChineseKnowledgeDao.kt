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
import com.hefengbao.jingmo.data.database.entity.chinese.KnowledgeCollectionEntity
import com.hefengbao.jingmo.data.database.entity.chinese.KnowledgeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChineseKnowledgeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: KnowledgeEntity)

    @Query("select * from chinese_knowledge where id = :id limit 1")
    fun get(id: Int): Flow<KnowledgeEntity>

    @Query("select k.*from chinese_knowledge k where k.id = (select id from chinese_knowledge order by random() limit 1) limit 1")
    fun random(): Flow<KnowledgeEntity>

    @Transaction
    @Query("select * from chinese_knowledge join chinese_knowledge_fts on chinese_knowledge_fts.rowid = chinese_knowledge.id where chinese_knowledge_fts match :query")
    fun search(query: String): Flow<List<KnowledgeEntity>>

    @Query("select k.* from chinese_knowledge_collections c join chinese_knowledge k on c.id = k.id order by collected_at desc")
    fun collections(): PagingSource<Int, KnowledgeEntity>

    @Query("select count(*) from chinese_knowledge")
    fun total(): Flow<Int>

    @Query("select * from chinese_knowledge_collections where id = :id")
    fun isCollect(id: Int): Flow<KnowledgeCollectionEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun collect(entity: KnowledgeCollectionEntity)

    @Query("delete from chinese_knowledge_collections where id = :id")
    suspend fun uncollect(id: Int)
}