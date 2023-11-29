package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.data.database.dao.TongueTwisterDao
import com.hefengbao.jingmo.data.database.entity.TongueTwisterEntity
import com.hefengbao.jingmo.data.database.model.SimpleTongueTwister
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TongueTwisterRepositoryImpl @Inject constructor(
    private val dao: TongueTwisterDao
) : TongueTwisterRepository {
    override fun getTongueTwister(id: Int): Flow<TongueTwisterEntity> = dao.getTongueTwister(id)

    override suspend fun getNextId(id: Int): Int = dao.getNextId(id)

    override suspend fun getPrevId(id: Int): Int = dao.getPrevId(id)

    override fun getTongueTwisterList(): Flow<List<SimpleTongueTwister>> =
        dao.getTongueTwisterList()
}