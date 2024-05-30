package com.hefengbao.jingmo.data.repository

import androidx.paging.PagingData
import com.hefengbao.jingmo.data.database.entity.ChineseExpressionEntity
import kotlinx.coroutines.flow.Flow

interface ChineseExpressionRepository {
    fun get(id: Int): Flow<ChineseExpressionEntity>
    fun random(): Flow<ChineseExpressionEntity>
    fun search(query: String): Flow<PagingData<ChineseExpressionEntity>>
}