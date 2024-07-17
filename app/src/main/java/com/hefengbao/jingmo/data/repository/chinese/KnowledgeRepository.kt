/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.repository.chinese

import androidx.paging.PagingData
import com.hefengbao.jingmo.data.database.entity.chinese.KnowledgeCollectionEntity
import com.hefengbao.jingmo.data.database.entity.chinese.KnowledgeEntity
import kotlinx.coroutines.flow.Flow

interface KnowledgeRepository {
    suspend fun insert(entity: KnowledgeEntity)
    fun get(id: Int): Flow<KnowledgeEntity>
    fun random(): Flow<KnowledgeEntity>
    fun search(query: String): Flow<List<KnowledgeEntity>>
    fun collections(): Flow<PagingData<KnowledgeEntity>>
    fun total(): Flow<Int>
    fun isCollect(id: Int): Flow<KnowledgeCollectionEntity?>
    suspend fun collect(entity: KnowledgeCollectionEntity)
    suspend fun uncollect(id: Int)
}