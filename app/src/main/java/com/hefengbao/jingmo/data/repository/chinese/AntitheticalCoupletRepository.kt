/*
 *  This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 *  For the full copyright and license information, please view the LICENSE
 *  file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.repository.chinese

import androidx.paging.PagingData
import com.hefengbao.jingmo.data.database.entity.chinese.AntitheticalCoupletEntity
import kotlinx.coroutines.flow.Flow

interface AntitheticalCoupletRepository {
    fun get(id: Int): Flow<AntitheticalCoupletEntity?>
    fun getRandom(): Flow<AntitheticalCoupletEntity?>
    fun getNextId(id: Int): Flow<Int?>
    fun getPrevId(id: Int): Flow<Int?>
    fun list(): Flow<PagingData<AntitheticalCoupletEntity>>
    fun search(query: String): Flow<PagingData<AntitheticalCoupletEntity>>
    fun bookmarks(): Flow<PagingData<AntitheticalCoupletEntity>>
}