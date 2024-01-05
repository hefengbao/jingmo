package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.common.network.SafeApiCall
import com.hefengbao.jingmo.data.database.AppDatabase
import com.hefengbao.jingmo.data.network.Network
import com.hefengbao.jingmo.common.network.Result
import com.hefengbao.jingmo.data.database.entity.RiddleEntity
import com.hefengbao.jingmo.data.model.asPeopleEntity
import com.hefengbao.jingmo.data.model.asRiddleEntity
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

class NetworkDatasourceRepositoryImpl @Inject constructor(
    private val network: Network,
    private val database: AppDatabase
) : NetworkDatasourceRepository, SafeApiCall {
    override suspend fun syncRiddle(): Result<Any>{
        return try {
            val riddles = network.riddles()

            database.riddleDao().insertAll(riddles.map {
                it.asRiddleEntity()
            })

            Result.Success(Any())
        }catch (exception: CancellationException){
            throw exception
        }catch (throwable: Throwable){
            Result.Error(throwable)
        }
    }

    override suspend fun insertRiddleEntities(entities: List<RiddleEntity>) {
        database.riddleDao().insertAll(entities)
    }

    override suspend fun syncPeople(): Result<Any> {
        return try {
            val people = network.people()

            database.peopleDao().insert(people.map {
                it.asPeopleEntity()
            })

            Result.Success(Any())
        }catch (exception: CancellationException){
            throw exception
        }catch (throwable: Throwable){
            Result.Error(throwable)
        }
    }
}