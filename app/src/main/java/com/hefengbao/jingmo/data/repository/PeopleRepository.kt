package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.data.database.entity.PeopleEntity
import com.hefengbao.jingmo.data.database.model.SimplePeopleInfo
import kotlinx.coroutines.flow.Flow

interface PeopleRepository {
    fun getById(id: Int): Flow<PeopleEntity>
    fun getByName(name: String): Flow<PeopleEntity>
    fun searchList(query: String): Flow<List<SimplePeopleInfo>>
    fun getRecommendList(): List<String>
}