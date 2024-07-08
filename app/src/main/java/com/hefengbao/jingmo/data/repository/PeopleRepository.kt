/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.data.database.entity.PeopleEntity
import com.hefengbao.jingmo.data.database.model.SimplePeopleInfo
import kotlinx.coroutines.flow.Flow

interface PeopleRepository {
    fun getRandom(): Flow<PeopleEntity>
    fun getById(id: Int): Flow<PeopleEntity>
    fun getByName(name: String): Flow<PeopleEntity>
    fun searchList(query: String): Flow<List<SimplePeopleInfo>>
    fun getRecommendList(): List<String>
}