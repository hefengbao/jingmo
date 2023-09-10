package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.data.database.entity.PoemWithWriterAndTags

interface PoemRepository {
    suspend fun getPoem(id: Long): PoemWithWriterAndTags
    suspend fun getNextId(id: Long): Long
    suspend fun getPrevId(id: Long): Long
}