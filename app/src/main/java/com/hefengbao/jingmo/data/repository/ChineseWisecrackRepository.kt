package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.data.database.entity.ChineseWisecrackEntity
import kotlinx.coroutines.flow.Flow

interface ChineseWisecrackRepository {
    suspend fun getChineseCrack(id: Long): ChineseWisecrackEntity
    suspend fun getNextId(id: Long): Long
    suspend fun getPrevId(id: Long): Long
    fun searchWisecrackList(query: String): Flow<List<ChineseWisecrackEntity>>
    suspend fun getSearchNextId(id: Long, query: String): Long
    suspend fun getSearchPrevId(id: Long, query: String): Long
}