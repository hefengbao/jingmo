package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.data.database.entity.ChineseWisecrackEntity

interface ChineseWisecrackRepository {
    suspend fun getChineseCrack(id: Long): ChineseWisecrackEntity
    suspend fun getNextId(id: Long): Long
    suspend fun getPrevId(id: Long): Long
}