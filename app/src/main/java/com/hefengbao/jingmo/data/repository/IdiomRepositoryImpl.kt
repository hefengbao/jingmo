/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hefengbao.jingmo.data.database.dao.IdiomDao
import com.hefengbao.jingmo.data.database.entity.IdiomCollectionEntity
import com.hefengbao.jingmo.data.database.entity.IdiomEntity
import com.hefengbao.jingmo.data.database.model.SimpleIdiomInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IdiomRepositoryImpl @Inject constructor(
    private val idiomDao: IdiomDao
) : IdiomRepository {
    override fun getIdiom(id: Int): Flow<IdiomEntity> = idiomDao.getIdiom(id)

    override fun random(): Flow<IdiomEntity> = idiomDao.random()

    override fun getNextId(id: Int): Flow<Int?> = idiomDao.getNextId(id)

    override fun getPrevId(id: Int): Flow<Int?> = idiomDao.getPrevId(id)

    override fun getSimpleIdiomInfoList(): Flow<PagingData<SimpleIdiomInfo>> = Pager(
        config = PagingConfig(pageSize = 30),
        pagingSourceFactory = {
            idiomDao.getSimpleIdiomInfoList()
        }
    ).flow

    override fun searchSimpleIdiomInfoList(query: String): Flow<PagingData<SimpleIdiomInfo>> =
        Pager(
            config = PagingConfig(pageSize = 30),
            pagingSourceFactory = {
                idiomDao.searchSimpleIdiomInfoList("%$query%")
            }
        ).flow

    override fun collections(): Flow<PagingData<IdiomEntity>> = Pager(
        config = PagingConfig(pageSize = 30),
        pagingSourceFactory = { idiomDao.collections() }
    ).flow

    override suspend fun collect(entity: IdiomCollectionEntity) =
        idiomDao.collect(entity)

    override suspend fun uncollect(id: Int) = idiomDao.uncollect(id)

    override fun isCollect(id: Int): Flow<IdiomCollectionEntity?> = idiomDao.isCollect(id)
}