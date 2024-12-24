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
import com.hefengbao.jingmo.data.database.dao.ClassicalLiteratureWritingDao
import com.hefengbao.jingmo.data.database.entity.classicalliterature.WritingCollectionEntity
import com.hefengbao.jingmo.data.database.entity.classicalliterature.WritingEntity
import com.hefengbao.jingmo.data.model.IdTitle
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WritingRepositoryImpl @Inject constructor(
    private val classicalLiteratureWritingDao: ClassicalLiteratureWritingDao
) : WritingRepository {
    override fun get(id: Int): Flow<WritingEntity> = classicalLiteratureWritingDao.get(id)

    override fun random(): Flow<WritingEntity> = classicalLiteratureWritingDao.random()

    override fun list(): Flow<PagingData<WritingEntity>> = Pager(
        config = PagingConfig(pageSize = 30),
        pagingSourceFactory = { classicalLiteratureWritingDao.list() }
    ).flow

    override fun search(query: String): Flow<PagingData<WritingEntity>> = Pager(
        config = PagingConfig(pageSize = 30),
        pagingSourceFactory = { classicalLiteratureWritingDao.search("*$query*") }
    ).flow

    override fun searchByAuthor(author: String): Flow<PagingData<WritingEntity>> = Pager(
        config = PagingConfig(pageSize = 30),
        pagingSourceFactory = {
            classicalLiteratureWritingDao.searchByAuthor(author)
        }
    ).flow

    override fun getNextId(id: Int): Flow<Int?> = classicalLiteratureWritingDao.getNextId(id)

    override fun getPrevId(id: Int): Flow<Int?> = classicalLiteratureWritingDao.getPrevId(id)

    override fun collections(): Flow<PagingData<WritingEntity>> = Pager(
        config = PagingConfig(pageSize = 30),
        pagingSourceFactory = {
            classicalLiteratureWritingDao.collections()
        }
    ).flow

    override suspend fun collect(entity: WritingCollectionEntity) =
        classicalLiteratureWritingDao.collect(entity)

    override suspend fun uncollect(writingId: Int) =
        classicalLiteratureWritingDao.uncollect(writingId)

    override fun isCollect(id: Int): Flow<WritingCollectionEntity?> =
        classicalLiteratureWritingDao.isCollect(id)

    override fun getIdTitle(query: String): Flow<PagingData<IdTitle>> = Pager(
        config = PagingConfig(pageSize = 30),
        pagingSourceFactory = { classicalLiteratureWritingDao.getIdTitle("%${query}%") }
    ).flow
}