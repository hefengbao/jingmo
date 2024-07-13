/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.repository.chinese

import com.hefengbao.jingmo.data.database.dao.ChineseTongueTwisterDao
import com.hefengbao.jingmo.data.database.entity.chinese.TongueTwisterEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TongueTwisterRepositoryImpl @Inject constructor(
    private val dao: ChineseTongueTwisterDao
) : TongueTwisterRepository {
    override fun get(id: Int): Flow<TongueTwisterEntity> = dao.getTongueTwister(id)

    override suspend fun getNextId(id: Int): Int = dao.getNextId(id)

    override suspend fun getPrevId(id: Int): Int = dao.getPrevId(id)

    override fun search(): Flow<List<TongueTwisterEntity>> =
        dao.getTongueTwisterList()
}