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
import com.hefengbao.jingmo.data.database.entity.chinese.WisecrackEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WisecrackRepositoryImpl @Inject constructor(
    private val dao: ChineseWisecrackDao
) : WisecrackRepository {
    override fun get(id: Int): Flow<WisecrackEntity?> = dao.get(id)

    override fun get(ids: List<Int>): Flow<List<WisecrackEntity>> = dao.get(ids)

    override fun getRandom(): Flow<WisecrackEntity?> = dao.random()

    override fun getNextId(id: Int): Flow<Int?> = dao.getNextId(id)

    override fun getPrevId(id: Int): Flow<Int?> = dao.getPrevId(id)

    override fun search(query: String): Flow<List<WisecrackEntity>> =
        dao.search("%$query%")

    override fun bookmarks(): Flow<PagingData<WisecrackEntity>> = Pager(
        config = PagingConfig(pageSize = 30),
        pagingSourceFactory = { dao.bookmarks() }
    ).flow
}