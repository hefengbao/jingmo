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
import com.hefengbao.jingmo.data.database.dao.LyricDao
import com.hefengbao.jingmo.data.database.entity.LyricCollectionEntity
import com.hefengbao.jingmo.data.database.entity.LyricEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LyricRepositoryImpl @Inject constructor(
    private val lyricDao: LyricDao
) : LyricRepository {
    override fun get(id: Int): Flow<LyricEntity> = lyricDao.get(id)

    override fun random(): Flow<LyricEntity> = lyricDao.random()

    override fun search(query: String): Flow<List<LyricEntity>> = lyricDao.search(query)

    override fun collections(): Flow<PagingData<LyricEntity>> = Pager(
        config = PagingConfig(pageSize = 30),
        pagingSourceFactory = { lyricDao.collections() }
    ).flow

    override fun total(): Flow<Int> = lyricDao.total()

    override fun isCollect(id: Int): Flow<LyricCollectionEntity?> = lyricDao.isCollect(id)

    override suspend fun collect(entity: LyricCollectionEntity) = lyricDao.collect(entity)

    override suspend fun uncollect(id: Int) = lyricDao.uncollect(id)
}