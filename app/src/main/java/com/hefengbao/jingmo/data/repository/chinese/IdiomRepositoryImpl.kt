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
import com.hefengbao.jingmo.data.database.dao.ChineseIdiomDao
import com.hefengbao.jingmo.data.database.entity.chinese.IdiomEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IdiomRepositoryImpl @Inject constructor(
    private val dao: ChineseIdiomDao
) : IdiomRepository {
    override fun get(id: Int): Flow<IdiomEntity?> = dao.get(id)

    override fun get(ids: List<Int>): Flow<List<IdiomEntity>> = dao.get(ids)

    override fun getRandom(): Flow<IdiomEntity?> = dao.random()

    override fun getNextId(id: Int): Flow<Int?> = dao.getNextId(id)

    override fun getPrevId(id: Int): Flow<Int?> = dao.getPrevId(id)

    override fun list(): Flow<PagingData<IdiomEntity>> = Pager(
        config = PagingConfig(pageSize = 30),
        pagingSourceFactory = {
            dao.list()
        }
    ).flow

    override fun search(query: String): Flow<PagingData<IdiomEntity>> =
        Pager(
            config = PagingConfig(pageSize = 30),
            pagingSourceFactory = {
                dao.search("%$query%")
            }
        ).flow

    override fun bookmarks(): Flow<PagingData<IdiomEntity>> = Pager(
        config = PagingConfig(pageSize = 30),
        pagingSourceFactory = { dao.bookmarks() }
    ).flow
}