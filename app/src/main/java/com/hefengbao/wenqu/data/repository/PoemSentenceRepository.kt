package com.hefengbao.wenqu.data.repository

import com.hefengbao.wenqu.data.database.entity.SentenceWithPoem

interface PoemSentenceRepository {
    suspend fun getSentence(id: Long): SentenceWithPoem
    suspend fun getNextId(id: Long): Long
    suspend fun getPrevId(id: Long): Long
}