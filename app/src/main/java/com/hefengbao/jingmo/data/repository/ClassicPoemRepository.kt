package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.data.database.entity.ClassicPoemEntity
import kotlinx.coroutines.flow.Flow

interface ClassicPoemRepository {
    suspend fun insert(entity: ClassicPoemEntity)
    fun random(): Flow<ClassicPoemEntity>
    fun total(): Flow<Int>
}