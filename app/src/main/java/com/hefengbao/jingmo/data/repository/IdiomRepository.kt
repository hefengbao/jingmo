package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.data.database.entity.IdiomEntity

interface IdiomRepository {
    suspend fun getIdiom(id: Long): IdiomEntity
    suspend fun getNextId(id: Long): Long
    suspend fun getPrevId(id: Long): Long
}