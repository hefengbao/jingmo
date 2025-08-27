/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.repository.classicalliterature

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hefengbao.jingmo.data.database.dao.ClassicalLiteratureSentenceDao
import com.hefengbao.jingmo.data.database.entity.classicalliterature.SentenceEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SentenceRepositoryImpl @Inject constructor(
    private val dao: ClassicalLiteratureSentenceDao
) : SentenceRepository {
    override fun get(id: Int): Flow<SentenceEntity?> = dao.get(id)

    override fun get(ids: List<Int>): Flow<List<SentenceEntity>> = dao.get(ids)

    override fun getRandom(): Flow<SentenceEntity?> = dao.random()

    override fun getNextId(id: Int): Flow<Int?> = dao.getNextId(id)

    override fun getPrevId(id: Int): Flow<Int?> = dao.getPrevId(id)

    override fun search(query: String): Flow<PagingData<SentenceEntity>> = Pager(
        config = PagingConfig(pageSize = 30),
        pagingSourceFactory = { dao.search("%$query%") }
    ).flow

    override fun bookmarks(): Flow<PagingData<SentenceEntity>> = Pager(
        config = PagingConfig(pageSize = 30),
        pagingSourceFactory = {
            dao.bookmarks()
        }
    ).flow
}