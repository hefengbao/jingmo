package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.data.database.entity.PoemEntity
import com.hefengbao.jingmo.data.database.entity.PoemWithWriterAndTags
import com.hefengbao.jingmo.data.database.model.PoemSimpleInfo
import kotlinx.coroutines.flow.Flow

interface PoemRepository {
    suspend fun getPoemWithWriterAndTags(id: Long): PoemWithWriterAndTags
    suspend fun getNextId(id: Long): Long
    suspend fun getPrevId(id: Long): Long
    suspend fun getPoem(id: Long): Flow<PoemEntity>
    fun getPoemSimpleList(): Flow<List<PoemSimpleInfo>>
    fun searchPoemSimpleList(query: String): Flow<List<PoemSimpleInfo>>
}