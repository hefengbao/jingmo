package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.data.database.entity.SentenceWithPoem

interface PoemSentenceRepository {
    suspend fun getSentence(id: Long): SentenceWithPoem
    suspend fun getNextId(id: Long): Long
    suspend fun getPrevId(id: Long): Long
}