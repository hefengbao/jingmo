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
import com.hefengbao.jingmo.data.database.entity.ChineseWisecrackCollectionEntity
import com.hefengbao.jingmo.data.database.entity.ChineseWisecrackEntity
import com.hefengbao.jingmo.data.database.model.ChineseWisecrackWithBookmark
import kotlinx.coroutines.flow.Flow

interface ChineseWisecrackRepository {
    fun getChineseCrack(id: Int): Flow<ChineseWisecrackWithBookmark>
    fun getNextId(id: Int): Flow<Int?>
    fun getPrevId(id: Int): Flow<Int?>
    fun searchWisecrackList(query: String): Flow<List<ChineseWisecrackEntity>>
    fun collections(): Flow<PagingData<ChineseWisecrackEntity>>
    suspend fun collect(entity: ChineseWisecrackCollectionEntity)
    suspend fun uncollect(id: Int)
    fun isCollect(id: Int): Flow<ChineseWisecrackCollectionEntity?>
}