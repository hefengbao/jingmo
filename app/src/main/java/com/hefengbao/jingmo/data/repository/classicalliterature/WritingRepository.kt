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
import com.hefengbao.jingmo.data.database.entity.classicalliterature.WritingEntity
import com.hefengbao.jingmo.data.model.IdTitle
import kotlinx.coroutines.flow.Flow

interface WritingRepository {
    fun get(id: Int): Flow<WritingEntity?>
    fun get(ids: List<Int>): Flow<List<WritingEntity>>
    fun getRandom(): Flow<WritingEntity?>
    fun list(): Flow<PagingData<WritingEntity>>
    fun search(query: String): Flow<PagingData<WritingEntity>>
    fun searchByAuthor(author: String): Flow<PagingData<WritingEntity>>
    fun getNextId(id: Int): Flow<Int?>
    fun getPrevId(id: Int): Flow<Int?>
    fun bookmarks(): Flow<PagingData<WritingEntity>>
    fun getIdTitle(query: String): Flow<PagingData<IdTitle>>
    fun getMaxId(): Flow<Int>
}