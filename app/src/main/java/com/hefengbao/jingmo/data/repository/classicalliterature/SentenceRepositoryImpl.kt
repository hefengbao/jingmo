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
import com.hefengbao.jingmo.data.database.entity.classicalliterature.SentenceCollectionEntity
import com.hefengbao.jingmo.data.database.entity.classicalliterature.SentenceEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SentenceRepositoryImpl @Inject constructor(
    private val classicalLiteratureSentenceDao: ClassicalLiteratureSentenceDao
) : SentenceRepository {
    override fun get(id: Int): Flow<SentenceEntity> =
        classicalLiteratureSentenceDao.get(id)

    override fun random(): Flow<SentenceEntity> = classicalLiteratureSentenceDao.random()

    override fun getNextId(id: Int): Flow<Int?> = classicalLiteratureSentenceDao.getNextId(id)

    override fun getPrevId(id: Int): Flow<Int?> = classicalLiteratureSentenceDao.getPrevId(id)

    override fun search(query: String): Flow<PagingData<SentenceEntity>> = Pager(
        config = PagingConfig(pageSize = 30),
        pagingSourceFactory = { classicalLiteratureSentenceDao.search("%$query%") }
    ).flow

    override fun collections(): Flow<PagingData<SentenceEntity>> = Pager(
        config = PagingConfig(pageSize = 30),
        pagingSourceFactory = {
            classicalLiteratureSentenceDao.collections()
        }
    ).flow

    override suspend fun collect(entity: SentenceCollectionEntity) =
        classicalLiteratureSentenceDao.collect(entity)

    override suspend fun uncollect(id: Int) = classicalLiteratureSentenceDao.uncollect(id)

    override fun isCollect(id: Int): Flow<SentenceCollectionEntity?> =
        classicalLiteratureSentenceDao.isCollect(id)
}