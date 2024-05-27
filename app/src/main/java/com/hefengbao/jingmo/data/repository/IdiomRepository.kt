package com.hefengbao.jingmo.data.repository

import androidx.paging.PagingData
import com.hefengbao.jingmo.data.database.entity.IdiomCollectionEntity
import com.hefengbao.jingmo.data.database.entity.IdiomEntity
import com.hefengbao.jingmo.data.database.model.SimpleIdiomInfo
import kotlinx.coroutines.flow.Flow

interface IdiomRepository {
    fun getIdiom(id: Int): Flow<IdiomEntity>
    fun random(): Flow<IdiomEntity>
    fun getNextId(id: Int): Flow<Int?>
    fun getPrevId(id: Int): Flow<Int?>
    fun getSimpleIdiomInfoList(): Flow<PagingData<SimpleIdiomInfo>>
    fun searchSimpleIdiomInfoList(query: String): Flow<PagingData<SimpleIdiomInfo>>
    fun collections(): Flow<PagingData<IdiomEntity>>
    suspend fun collect(entity: IdiomCollectionEntity)
    suspend fun uncollect(id: Int)
    fun isCollect(id: Int): Flow<IdiomCollectionEntity?>
}