package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.data.database.entity.ChineseWisecrackEntity
import kotlinx.coroutines.flow.Flow

interface ChineseWisecrackRepository {
    suspend fun getChineseCrack(id: Int): ChineseWisecrackEntity
    suspend fun getNextId(id: Int): Int
    suspend fun getPrevId(id: Int): Int
    fun searchWisecrackList(query: String): Flow<List<ChineseWisecrackEntity>>
    suspend fun getSearchNextId(id: Int, query: String): Int
    suspend fun getSearchPrevId(id: Int, query: String): Int
}