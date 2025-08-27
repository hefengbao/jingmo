/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.repository.classicalliterature

import androidx.paging.PagingData
import com.hefengbao.jingmo.data.database.entity.classicalliterature.SentenceEntity
import kotlinx.coroutines.flow.Flow

interface SentenceRepository {
    fun get(id: Int): Flow<SentenceEntity?>
    fun get(ids: List<Int>): Flow<List<SentenceEntity>>
    fun getRandom(): Flow<SentenceEntity?>
    fun getNextId(id: Int): Flow<Int?>
    fun getPrevId(id: Int): Flow<Int?>
    fun search(query: String): Flow<PagingData<SentenceEntity>>
    fun bookmarks(): Flow<PagingData<SentenceEntity>>
}