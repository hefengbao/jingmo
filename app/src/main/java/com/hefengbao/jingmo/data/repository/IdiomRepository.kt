package com.hefengbao.jingmo.data.repository

import androidx.paging.PagingData
import com.hefengbao.jingmo.data.database.entity.IdiomEntity
import com.hefengbao.jingmo.data.database.model.SimpleIdiomInfo
import kotlinx.coroutines.flow.Flow

interface IdiomRepository {
    fun getIdiom(id: Int): Flow<IdiomEntity>
    fun getNextId(id: Int): Flow<Int?>
    fun getPrevId(id: Int): Flow<Int?>
    fun getSimpleIdiomInfoList(): Flow<PagingData<SimpleIdiomInfo>>
    fun searchSimpleIdiomInfoList(query: String): Flow<PagingData<SimpleIdiomInfo>>
    suspend fun getSearchNextId(id: Int, query: String): Int
    suspend fun getSearchPrevId(id: Int, query: String): Int
}