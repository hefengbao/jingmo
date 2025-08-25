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
import com.hefengbao.jingmo.data.database.dao.ChineseQuoteDao
import com.hefengbao.jingmo.data.database.entity.chinese.QuoteEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QuoteRepositoryImpl @Inject constructor(
    private val dao: ChineseQuoteDao,
) : QuoteRepository {
    override suspend fun insert(entity: QuoteEntity) = dao.insert(entity)

    override fun get(id: Int): Flow<QuoteEntity?> = dao.get(id)

    override fun getRandom(): Flow<QuoteEntity?> = dao.random()

    override fun search(query: String): Flow<List<QuoteEntity>> = dao.search(query)

    override fun total(): Flow<Int> = dao.total()

    override fun prevId(id: Int): Flow<Int?> = dao.getPrevId(id)

    override fun nextId(id: Int): Flow<Int?> = dao.getNextId(id)

    override fun bookmarks(): Flow<PagingData<QuoteEntity>> = Pager(
        config = PagingConfig(pageSize = 30),
        pagingSourceFactory = { dao.bookmarks() }
    ).flow
}