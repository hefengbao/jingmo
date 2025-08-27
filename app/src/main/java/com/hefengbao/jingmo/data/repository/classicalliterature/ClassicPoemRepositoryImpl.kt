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
import com.hefengbao.jingmo.data.database.entity.classicalliterature.ClassicPoemEntity
import com.hefengbao.jingmo.data.model.IdTitle
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ClassicPoemRepositoryImpl @Inject constructor(
    private val dao: ClassicalLiteratureClassicPoemDao
) : ClassicPoemRepository {
    override suspend fun insert(entity: ClassicPoemEntity) = dao.insert(entity)

    override fun getRandom(): Flow<ClassicPoemEntity?> = dao.random()

    override fun get(id: Int): Flow<ClassicPoemEntity?> = dao.get(id)

    override fun get(ids: List<Int>): Flow<List<ClassicPoemEntity>> = dao.get(ids)

    override fun total(): Flow<Int> = dao.total()

    override fun getNextId(id: Int): Flow<Int?> = dao.getNextId(id)

    override fun getPrevId(id: Int): Flow<Int?> = dao.getPrevId(id)

    override fun bookmarks(): Flow<PagingData<ClassicPoemEntity>> = Pager(
        config = PagingConfig(15),
        pagingSourceFactory = { dao.bookmarks() }
    ).flow

    override fun search(query: String): Flow<List<ClassicPoemEntity>> = dao.search(query)

    override fun getIdTitle(query: String): Flow<PagingData<IdTitle>> = Pager(
        config = PagingConfig(30),
        pagingSourceFactory = { dao.getIdTitle("%${query}%") }
    ).flow
}