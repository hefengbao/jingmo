/*
 *  This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 *  For the full copyright and license information, please view the LICENSE
 *  file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.repository.chinese

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hefengbao.jingmo.data.database.dao.ChineseModernPoetryDao
import com.hefengbao.jingmo.data.database.entity.chinese.ModernPoetryCollectionEntity
import com.hefengbao.jingmo.data.database.entity.chinese.ModernPoetryEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ModernPoetryRepositoryImpl @Inject constructor(
    private val dao: ChineseModernPoetryDao
) : ModernPoetryRepository {
    override suspend fun insert(entity: ModernPoetryEntity) = dao.insert(entity)

    override fun get(id: Int): Flow<ModernPoetryEntity> = dao.get(id)

    override fun random(): Flow<ModernPoetryEntity> = dao.random()

    override fun search(query: String): Flow<List<ModernPoetryEntity>> = dao.search(query)

    override fun collections(): Flow<PagingData<ModernPoetryEntity>> = Pager(
        pagingSourceFactory = { dao.collections() },
        config = PagingConfig(pageSize = 30)
    ).flow

    override fun total(): Flow<Int> = dao.total()

    override fun prevId(id: Int): Flow<Int?> = dao.getPrevId(id)

    override fun nextId(id: Int): Flow<Int?> = dao.getNextId(id)

    override fun isCollect(id: Int): Flow<ModernPoetryCollectionEntity?> = dao.isCollect(id)

    override suspend fun collect(entity: ModernPoetryCollectionEntity) = dao.collect(entity)

    override suspend fun uncollect(id: Int) = dao.uncollect(id)
}