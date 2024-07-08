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
import com.hefengbao.jingmo.data.database.entity.IdiomCollectionEntity
import com.hefengbao.jingmo.data.database.entity.IdiomEntity
import com.hefengbao.jingmo.data.database.model.SimpleIdiomInfo
import kotlinx.coroutines.flow.Flow

interface IdiomRepository {
    fun getIdiom(id: Int): Flow<IdiomEntity>
    fun random(): Flow<IdiomEntity>
    fun getNextId(id: Int): Flow<Int?>
    fun getPrevId(id: Int): Flow<Int?>
    fun getSimpleIdiomInfoList(): Flow<PagingData<SimpleIdiomInfo>>
    fun searchSimpleIdiomInfoList(query: String): Flow<PagingData<SimpleIdiomInfo>>
    fun collections(): Flow<PagingData<IdiomEntity>>
    suspend fun collect(entity: IdiomCollectionEntity)
    suspend fun uncollect(id: Int)
    fun isCollect(id: Int): Flow<IdiomCollectionEntity?>
}