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
import com.hefengbao.jingmo.data.database.entity.PoemSentenceCollectionEntity
import com.hefengbao.jingmo.data.database.entity.PoemSentenceEntity
import kotlinx.coroutines.flow.Flow

interface PoemSentenceRepository {
    fun getNextId(id: Int): Flow<Int?>
    fun getPrevId(id: Int): Flow<Int?>
    fun searchSentencesList(query: String): Flow<PagingData<PoemSentenceEntity>>
    fun getSentence(id: Int): Flow<PoemSentenceEntity>
    fun random(): Flow<PoemSentenceEntity>
    fun collections(): Flow<PagingData<PoemSentenceEntity>>
    suspend fun collect(entity: PoemSentenceCollectionEntity)
    suspend fun uncollect(id: Int)
    fun isCollect(id: Int): Flow<PoemSentenceCollectionEntity?>
}