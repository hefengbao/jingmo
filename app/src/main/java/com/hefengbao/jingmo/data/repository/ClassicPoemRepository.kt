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
import com.hefengbao.jingmo.data.database.entity.ClassicPoemCollectionEntity
import com.hefengbao.jingmo.data.database.entity.ClassicPoemEntity
import kotlinx.coroutines.flow.Flow

interface ClassicPoemRepository {
    suspend fun insert(entity: ClassicPoemEntity)
    fun random(): Flow<ClassicPoemEntity>
    fun get(id: Int): Flow<ClassicPoemEntity>
    fun total(): Flow<Int>
    fun getNextId(id: Int): Flow<Int?>
    fun getPrevId(id: Int): Flow<Int?>
    suspend fun collect(entity: ClassicPoemCollectionEntity)
    suspend fun uncollect(id: Int)
    fun isCollect(id: Int): Flow<ClassicPoemCollectionEntity?>
    fun collections(): Flow<PagingData<ClassicPoemEntity>>
    fun search(query: String): Flow<List<ClassicPoemEntity>>
}