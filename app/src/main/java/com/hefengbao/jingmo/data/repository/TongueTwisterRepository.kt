package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.data.database.entity.TongueTwisterEntity
import com.hefengbao.jingmo.data.database.model.SimpleTongueTwister
import kotlinx.coroutines.flow.Flow

interface TongueTwisterRepository {
    fun getTongueTwister(id: Int): Flow<TongueTwisterEntity>
    suspend fun getNextId(id: Int): Int
    suspend fun getPrevId(id: Int): Int
    fun getTongueTwisterList(): Flow<List<SimpleTongueTwister>>
}