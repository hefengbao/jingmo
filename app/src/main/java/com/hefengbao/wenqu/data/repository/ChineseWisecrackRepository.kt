package com.hefengbao.wenqu.data.repository

import com.hefengbao.wenqu.data.database.entity.PoemWithWriterAndTags

interface PoemRepository {
    suspend fun getPoem(id: Long): PoemWithWriterAndTags
    suspend fun getNextId(id: Long): Long
    suspend fun getPrevId(id: Long): Long
}