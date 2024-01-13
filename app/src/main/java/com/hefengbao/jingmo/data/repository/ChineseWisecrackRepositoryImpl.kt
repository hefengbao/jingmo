package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.data.database.dao.ChineseWisecrackDao
import com.hefengbao.jingmo.data.database.entity.ChineseWisecrackEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChineseWisecrackRepositoryImpl @Inject constructor(
    private val chineseWisecrackDao: ChineseWisecrackDao
) : ChineseWisecrackRepository {
    override suspend fun getChineseCrack(id: Int): ChineseWisecrackEntity =
        chineseWisecrackDao.getChineseWisecrack(id)

    override suspend fun getNextId(id: Int): Int = chineseWisecrackDao.getNextId(id)
    override suspend fun getPrevId(id: Int): Int = chineseWisecrackDao.getPrevId(id)
    override fun searchWisecrackList(query: String): Flow<List<ChineseWisecrackEntity>> =
        chineseWisecrackDao.searchWisecrackList("%$query%")

    override suspend fun getSearchNextId(id: Int, query: String): Int =
        chineseWisecrackDao.getSearchNextId(id, "%$query%")

    override suspend fun getSearchPrevId(id: Int, query: String): Int =
        chineseWisecrackDao.getSearchPrevId(id, "%$query%")
}