/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.repository.chinese

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hefengbao.jingmo.data.database.dao.ChineseExpressionDao
import com.hefengbao.jingmo.data.database.entity.chinese.ExpressionEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExpressionRepositoryImpl @Inject constructor(
    private val dao: ChineseExpressionDao
) : ExpressionRepository {
    override fun get(id: Int): Flow<ExpressionEntity> = dao.get(id)

    override fun random(): Flow<ExpressionEntity> = dao.random()

    override fun search(query: String): Flow<PagingData<ExpressionEntity>> = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = { dao.search("%$query%") }
    ).flow
}