package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.common.network.SafeApiCall
import com.hefengbao.jingmo.data.database.AppDatabase
import com.hefengbao.jingmo.data.model.Riddle
import com.hefengbao.jingmo.data.network.Network
import com.hefengbao.jingmo.common.network.Result
import com.hefengbao.jingmo.data.database.entity.RiddleEntity
import javax.inject.Inject

class NetworkDatasourceRepositoryImpl @Inject constructor(
    private val network: Network,
    private val database: AppDatabase
) : NetworkDatasourceRepository, SafeApiCall {
    override suspend fun syncRiddle(): Result<List<Riddle>> = safeApiCall {
        network.riddles()
    }

    override suspend fun insertRiddleEntities(entities: List<RiddleEntity>) {
        database.riddleDao().insertAll(entities)
    }
}