package com.hefengbao.jingmo.data.repository

import android.util.Log
import com.hefengbao.jingmo.common.network.SafeApiCall
import com.hefengbao.jingmo.data.database.AppDatabase
import com.hefengbao.jingmo.data.network.Network
import com.hefengbao.jingmo.common.network.Result
import com.hefengbao.jingmo.data.database.entity.RiddleEntity
import com.hefengbao.jingmo.data.model.asPeopleEntity
import com.hefengbao.jingmo.data.model.asRiddleEntity
import com.hefengbao.jingmo.data.model.toWritingEntity
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

    override suspend fun syncWritings(): Result<Any> {
        return try {
            var page: Int? = 1

            while (page != null){

                Log.i("NetworkDatasourceRepositoryImpl", "page = $page")
                val response = network.writings(page)
                Log.i("NetworkDatasourceRepositoryImpl", "count = ${response.data.size}")
                if (response.nextPage != null){
                    page ++
                }else{
                    page = null
                }

                response.data.map {
                    database.writingDao().insert(it.toWritingEntity())
                    Log.i("NetworkDatasourceRepositoryImpl", "id = ${it.id}")
                }
            }

            Result.Success(Any())
        }catch (exception: CancellationException){
            throw exception
        }catch (throwable: Throwable){
            Log.e("NetworkDatasourceRepositoryImpl", throwable.message.toString())
            Result.Error(throwable)
        }
    }
}