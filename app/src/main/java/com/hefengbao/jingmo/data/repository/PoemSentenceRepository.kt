package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.data.database.entity.PoemSentenceEntity
import com.hefengbao.jingmo.data.database.entity.SentenceWithPoem
import kotlinx.coroutines.flow.Flow

interface PoemSentenceRepository {
    suspend fun getSentenceWithPoem(id: Int): SentenceWithPoem
    suspend fun getNextId(id: Int): Int
    suspend fun getPrevId(id: Int): Int
    fun searchSentencesList(query: String): Flow<List<PoemSentenceEntity>>
    suspend fun getSentence(id: Int): Flow<PoemSentenceEntity>
    suspend fun getSearchNextId(id: Int, query: String): Int
    suspend fun getSearchPrevId(id: Int, query: String): Int
}