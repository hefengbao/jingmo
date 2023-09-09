package com.hefengbao.wenqu.data.repository

import com.hefengbao.wenqu.data.database.dao.ChineseWisecrackDao
import com.hefengbao.wenqu.data.database.entity.ChineseWisecrackEntity
import javax.inject.Inject

class ChineseCrackRepositoryImpl @Inject constructor(
    private val chineseWisecrackDao: ChineseWisecrackDao
) : ChineseWisecrackRepository {
    override suspend fun getChineseCrack(id: Long): ChineseWisecrackEntity =
        chineseWisecrackDao.getChineseWisecrack(id)

    override suspend fun getNextId(id: Long): Long = chineseWisecrackDao.getNextId(id)
    override suspend fun getPrevId(id: Long): Long = chineseWisecrackDao.getPrevId(id)
}