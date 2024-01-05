package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.data.model.Riddle
import com.hefengbao.jingmo.common.network.Result
import com.hefengbao.jingmo.data.database.entity.RiddleEntity


interface NetworkDatasourceRepository {
    suspend fun syncRiddle(): Result<List<Riddle>>
    suspend fun insertRiddleEntities(entities: List<RiddleEntity>)
}