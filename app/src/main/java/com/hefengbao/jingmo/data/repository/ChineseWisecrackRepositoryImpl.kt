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
import com.hefengbao.jingmo.data.database.dao.ChineseWisecrackDao
import com.hefengbao.jingmo.data.database.entity.ChineseWisecrackCollectionEntity
import com.hefengbao.jingmo.data.database.entity.ChineseWisecrackEntity
import com.hefengbao.jingmo.data.database.model.ChineseWisecrackWithBookmark
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChineseWisecrackRepositoryImpl @Inject constructor(
    private val chineseWisecrackDao: ChineseWisecrackDao
) : ChineseWisecrackRepository {
    override fun getChineseCrack(id: Int): Flow<ChineseWisecrackWithBookmark> =
        chineseWisecrackDao.getChineseWisecrack(id)

    override fun getNextId(id: Int): Flow<Int?> = chineseWisecrackDao.getNextId(id)

    override fun getPrevId(id: Int): Flow<Int?> = chineseWisecrackDao.getPrevId(id)

    override fun searchWisecrackList(query: String): Flow<List<ChineseWisecrackEntity>> =
        chineseWisecrackDao.searchWisecrackList("%$query%")

    override fun collections(): Flow<PagingData<ChineseWisecrackEntity>> = Pager(
        config = PagingConfig(pageSize = 30),
        pagingSourceFactory = { chineseWisecrackDao.collections() }
    ).flow

    override suspend fun collect(entity: ChineseWisecrackCollectionEntity) =
        chineseWisecrackDao.collect(entity)

    override suspend fun uncollect(id: Int) =
        chineseWisecrackDao.uncollect(id)

    override fun isCollect(id: Int): Flow<ChineseWisecrackCollectionEntity?> =
        chineseWisecrackDao.isCollect(id)
}