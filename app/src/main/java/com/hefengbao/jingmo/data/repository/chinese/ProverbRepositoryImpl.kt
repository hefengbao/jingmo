package com.hefengbao.jingmo.data.repository.chinese

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hefengbao.jingmo.data.database.dao.ChineseProverbDao
import com.hefengbao.jingmo.data.database.entity.chinese.ProverbEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProverbRepositoryImpl @Inject constructor(
    private val dao: ChineseProverbDao
) : ProverbRepository {
    override suspend fun insert(entity: ProverbEntity) = dao.insert(entity)

    override fun get(id: Int): Flow<ProverbEntity?> = dao.get(id)

    override fun get(ids: List<Int>): Flow<List<ProverbEntity>> = dao.get(ids)

    override fun getRandom(): Flow<ProverbEntity?> = dao.random()

    override fun search(query: String): Flow<List<ProverbEntity>> = dao.search(query)

    override fun total(): Flow<Int> = dao.total()

    override fun prevId(id: Int): Flow<Int?> = dao.getPrevId(id)

    override fun nextId(id: Int): Flow<Int?> = dao.getNextId(id)

    override fun bookmarks(): Flow<PagingData<ProverbEntity>> = Pager(
        config = PagingConfig(pageSize = 15),
        pagingSourceFactory = { dao.bookmarks() }
    ).flow
}