package com.hefengbao.wenqu.data.repository

import com.hefengbao.wenqu.data.database.entity.ChineseWisecrackEntity

interface ChineseWisecrackRepository {
    suspend fun getChineseCrack(id: Long): ChineseWisecrackEntity
    suspend fun getNextId(id: Long): Long
    suspend fun getPrevId(id: Long): Long
}