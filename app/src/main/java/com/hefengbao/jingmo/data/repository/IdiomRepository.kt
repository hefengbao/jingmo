package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.data.database.entity.IdiomEntity
import com.hefengbao.jingmo.data.database.model.SimpleIdiomInfo
import kotlinx.coroutines.flow.Flow

interface IdiomRepository {
    suspend fun getIdiom(id: Long): IdiomEntity
    suspend fun getNextId(id: Long): Long
    suspend fun getPrevId(id: Long): Long
    fun getSimpleIdiomInfoList(): Flow<List<SimpleIdiomInfo>>
    fun searchSimpleIdiomInfoList(query: String): Flow<List<SimpleIdiomInfo>>
    suspend fun getSearchNextId(id: Long, query: String): Long
    suspend fun getSearchPrevId(id: Long, query: String): Long
}