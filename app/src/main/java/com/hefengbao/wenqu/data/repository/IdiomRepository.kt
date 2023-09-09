package com.hefengbao.wenqu.data.repository

import com.hefengbao.wenqu.data.database.entity.IdiomEntity

interface IdiomRepository {
    suspend fun getIdiom(id: Long): IdiomEntity
    suspend fun getNextId(id: Long): Long
    suspend fun getPrevId(id: Long): Long
}