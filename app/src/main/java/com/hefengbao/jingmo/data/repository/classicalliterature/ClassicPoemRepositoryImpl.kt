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
import com.hefengbao.jingmo.data.database.dao.ClassicalLiteratureClassicPoemDao
import com.hefengbao.jingmo.data.database.entity.classicalliterature.ClassicPoemCollectionEntity
import com.hefengbao.jingmo.data.database.entity.classicalliterature.ClassicPoemEntity
import com.hefengbao.jingmo.data.model.IdTitle
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ClassicPoemRepositoryImpl @Inject constructor(
    private val classicalLiteratureClassicPoemDao: ClassicalLiteratureClassicPoemDao
) : ClassicPoemRepository {
    override suspend fun insert(entity: ClassicPoemEntity) =
        classicalLiteratureClassicPoemDao.insert(entity)

    override fun random(): Flow<ClassicPoemEntity> = classicalLiteratureClassicPoemDao.random()

    override fun get(id: Int): Flow<ClassicPoemEntity> = classicalLiteratureClassicPoemDao.get(id)

    override fun total(): Flow<Int> = classicalLiteratureClassicPoemDao.total()

    override fun getNextId(id: Int): Flow<Int?> = classicalLiteratureClassicPoemDao.getNextId(id)

    override fun getPrevId(id: Int): Flow<Int?> = classicalLiteratureClassicPoemDao.getPrevId(id)

    override suspend fun collect(entity: ClassicPoemCollectionEntity) =
        classicalLiteratureClassicPoemDao.collect(entity)

    override suspend fun uncollect(id: Int) = classicalLiteratureClassicPoemDao.uncollect(id)

    override fun isCollect(id: Int): Flow<ClassicPoemCollectionEntity?> =
        classicalLiteratureClassicPoemDao.isCollect(id)

    override fun collections(): Flow<PagingData<ClassicPoemEntity>> = Pager(
        config = PagingConfig(15),
        pagingSourceFactory = { classicalLiteratureClassicPoemDao.collections() }
    ).flow

    override fun search(query: String): Flow<List<ClassicPoemEntity>> =
        classicalLiteratureClassicPoemDao.search(query)

    override fun getIdTitle(query: String): Flow<PagingData<IdTitle>> = Pager(
        config = PagingConfig(30),
        pagingSourceFactory = { classicalLiteratureClassicPoemDao.getIdTitle("%${query}%") }
    ).flow
}