package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.data.database.entity.IdiomEntity
import com.hefengbao.jingmo.data.database.model.SimpleIdiomInfo
import kotlinx.coroutines.flow.Flow

interface IdiomRepository {
    suspend fun getIdiom(id: Int): IdiomEntity
    suspend fun getNextId(id: Int): Int
    suspend fun getPrevId(id: Int): Int
    fun getSimpleIdiomInfoList(): Flow<List<SimpleIdiomInfo>>
    fun searchSimpleIdiomInfoList(query: String): Flow<List<SimpleIdiomInfo>>
    suspend fun getSearchNextId(id: Int, query: String): Int
    suspend fun getSearchPrevId(id: Int, query: String): Int
}