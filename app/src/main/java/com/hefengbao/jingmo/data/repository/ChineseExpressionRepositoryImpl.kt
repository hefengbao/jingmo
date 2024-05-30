package com.hefengbao.jingmo.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hefengbao.jingmo.data.database.dao.ChineseExpressionDao
import com.hefengbao.jingmo.data.database.entity.ChineseExpressionEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChineseExpressionRepositoryImpl @Inject constructor(
    private val dao: ChineseExpressionDao
) : ChineseExpressionRepository {
    override fun get(id: Int): Flow<ChineseExpressionEntity> = dao.get(id)

    override fun random(): Flow<ChineseExpressionEntity> = dao.random()

    override fun search(query: String): Flow<PagingData<ChineseExpressionEntity>> = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = { dao.search("%$query%") }
    ).flow
}