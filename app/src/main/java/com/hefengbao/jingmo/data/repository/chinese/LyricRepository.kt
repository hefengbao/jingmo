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
import com.hefengbao.jingmo.data.database.entity.chinese.LyricEntity
import kotlinx.coroutines.flow.Flow

interface LyricRepository {
    fun get(id: Int): Flow<LyricEntity?>
    fun get(ids: List<Int>): Flow<List<LyricEntity>>
    fun getRandom(): Flow<LyricEntity?>
    fun search(query: String): Flow<List<LyricEntity>>
    fun total(): Flow<Int>
    fun prevId(id: Int): Flow<Int?>
    fun nextId(id: Int): Flow<Int?>
    fun bookmarks(): Flow<PagingData<LyricEntity>>
}