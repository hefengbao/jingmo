/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hefengbao.jingmo.data.database.entity.ChineseKnowledgeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChineseKnowledgeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ChineseKnowledgeEntity)

    @Query("select k.rowid, k.* from chinese_knowledge k where k.rowid = :id limit 1")
    fun getChineseKnowledge(id: Int): Flow<ChineseKnowledgeEntity>

    @Query("select rowid from chinese_knowledge where rowid <:id order by rowid desc limit 1")
    fun getPrevId(id: Int): Flow<Int?>

    @Query("select rowid from chinese_knowledge where rowid > :id order by rowid asc limit 1")
    fun getNextId(id: Int): Flow<Int?>

    @Query("select k.rowid, k.* from chinese_knowledge k where k.content match :query")
    fun getSearchChineseKnowledgeList(query: String): Flow<List<ChineseKnowledgeEntity>>

    @Query("delete from chinese_knowledge")
    suspend fun clear()

    @Query("select count(*) from chinese_knowledge")
    fun total(): Flow<Int>
}