/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.repository

import androidx.paging.PagingData
import com.hefengbao.jingmo.data.database.entity.LyricCollectionEntity
import com.hefengbao.jingmo.data.database.entity.LyricEntity
import kotlinx.coroutines.flow.Flow

interface LyricRepository {
    fun get(id: Int): Flow<LyricEntity>
    fun random(): Flow<LyricEntity>
    fun search(query: String): Flow<List<LyricEntity>>
    fun collections(): Flow<PagingData<LyricEntity>>
    fun total(): Flow<Int>
    fun isCollect(id: Int): Flow<LyricCollectionEntity?>
    suspend fun collect(entity: LyricCollectionEntity)
    suspend fun uncollect(id: Int)
}