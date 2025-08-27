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
import com.hefengbao.jingmo.data.database.dao.ChineseKnowledgeDao
import com.hefengbao.jingmo.data.database.entity.chinese.KnowledgeEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class KnowledgeRepositoryImpl @Inject constructor(
    private val dao: ChineseKnowledgeDao
) : KnowledgeRepository {
    override suspend fun insert(entity: KnowledgeEntity) = dao.insert(entity)

    override fun get(id: Int): Flow<KnowledgeEntity?> = dao.get(id)

    override fun get(ids: List<Int>): Flow<List<KnowledgeEntity>> = dao.get(ids)

    override fun getRandom(): Flow<KnowledgeEntity?> = dao.random()

    override fun search(query: String): Flow<List<KnowledgeEntity>> = dao.search(query)

    override fun list(): Flow<PagingData<KnowledgeEntity>> = Pager(
        config = PagingConfig(pageSize = 15),
        pagingSourceFactory = { dao.bookmarks() }
    ).flow

    override fun total(): Flow<Int> = dao.total()

    override fun getNextId(id: Int): Flow<Int?> = dao.getNextId(id)

    override fun getPrevId(id: Int): Flow<Int?> = dao.getPrevId(id)
}