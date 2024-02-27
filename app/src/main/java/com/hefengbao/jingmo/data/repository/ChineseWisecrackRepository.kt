package com.hefengbao.jingmo.data.repository

import androidx.paging.PagingData
import com.hefengbao.jingmo.data.database.entity.ChineseWisecrackCollectionEntity
import com.hefengbao.jingmo.data.database.entity.ChineseWisecrackEntity
import com.hefengbao.jingmo.data.database.model.ChineseWisecrackWithBookmark
import kotlinx.coroutines.flow.Flow

interface ChineseWisecrackRepository {
    fun getChineseCrack(id: Int): Flow<ChineseWisecrackWithBookmark>
    fun getNextId(id: Int): Flow<Int?>
    fun getPrevId(id: Int): Flow<Int?>
    fun searchWisecrackList(query: String): Flow<List<ChineseWisecrackEntity>>
    fun getSearchNextId(id: Int, query: String): Flow<Int?>
    fun getSearchPrevId(id: Int, query: String): Flow<Int?>
    fun collections(): Flow<PagingData<ChineseWisecrackWithBookmark>>
    suspend fun collect(entity: ChineseWisecrackCollectionEntity)
    suspend fun uncollect(id: Int)
    fun isCollect(id: Int): Flow<ChineseWisecrackCollectionEntity?>
}