package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.data.database.dao.RiddleDao
import com.hefengbao.jingmo.data.database.entity.RiddleEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RiddleRepositoryImpl @Inject constructor(
    private val dao: RiddleDao
) : RiddleRepository {
    override fun getRiddle(id: Int): Flow<RiddleEntity> = dao.getRiddle(id)

    override suspend fun getNextId(id: Int): Int = dao.getNextId(id)

    override suspend fun getPrevId(id: Int): Int = dao.getPrevId(id)

    override fun searchResultList(query: String): Flow<List<RiddleEntity>> =
        dao.searchResult("%$query%")
}