package com.hefengbao.jingmo.data.repository.chinese

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hefengbao.jingmo.data.database.dao.ChineseProverbDao
import com.hefengbao.jingmo.data.database.entity.chinese.ProverbCollectionEntity
import com.hefengbao.jingmo.data.database.entity.chinese.ProverbEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProverbRepositoryImpl @Inject constructor(
    private val proverbDao: ChineseProverbDao
) : ProverbRepository {
    override suspend fun insert(entity: ProverbEntity) = proverbDao.insert(entity)

    override fun get(id: Int): Flow<ProverbEntity> = proverbDao.get(id)

    override fun random(): Flow<ProverbEntity> = proverbDao.random()

    override fun search(query: String): Flow<List<ProverbEntity>> = proverbDao.search(query)

    override fun collections(): Flow<PagingData<ProverbEntity>> = Pager(
        config = PagingConfig(pageSize = 15),
        pagingSourceFactory = { proverbDao.collections() }
    ).flow

    override fun total(): Flow<Int> = proverbDao.total()

    override fun isCollect(id: Int): Flow<ProverbCollectionEntity?> = proverbDao.isCollect(id)

    override suspend fun collect(entity: ProverbCollectionEntity) = proverbDao.collect(entity)

    override suspend fun uncollect(id: Int) = proverbDao.uncollect(id)
}