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
import com.hefengbao.jingmo.data.database.dao.ChineseWisecrackDao
import com.hefengbao.jingmo.data.database.entity.chinese.WisecrackCollectionEntity
import com.hefengbao.jingmo.data.database.entity.chinese.WisecrackEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WisecrackRepositoryImpl @Inject constructor(
    private val chineseWisecrackDao: ChineseWisecrackDao
) : WisecrackRepository {
    override fun get(id: Int): Flow<WisecrackEntity> =
        chineseWisecrackDao.getChineseWisecrack(id)

    override fun getNextId(id: Int): Flow<Int?> = chineseWisecrackDao.getNextId(id)

    override fun getPrevId(id: Int): Flow<Int?> = chineseWisecrackDao.getPrevId(id)

    override fun search(query: String): Flow<List<WisecrackEntity>> =
        chineseWisecrackDao.searchWisecrackList("%$query%")

    override fun collections(): Flow<PagingData<WisecrackEntity>> = Pager(
        config = PagingConfig(pageSize = 30),
        pagingSourceFactory = { chineseWisecrackDao.collections() }
    ).flow

    override suspend fun collect(entity: WisecrackCollectionEntity) =
        chineseWisecrackDao.collect(entity)

    override suspend fun uncollect(id: Int) =
        chineseWisecrackDao.uncollect(id)

    override fun isCollect(id: Int): Flow<WisecrackCollectionEntity?> =
        chineseWisecrackDao.isCollect(id)
}