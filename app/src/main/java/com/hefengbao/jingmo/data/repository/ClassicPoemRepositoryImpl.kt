package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.data.database.dao.ClassicPoemDao
import com.hefengbao.jingmo.data.database.entity.ClassicPoemEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ClassicPoemRepositoryImpl @Inject constructor(
    private val classicPoemDao: ClassicPoemDao
) : ClassicPoemRepository {
    override suspend fun insert(entity: ClassicPoemEntity) = classicPoemDao.insert(entity)

    override fun random(): Flow<ClassicPoemEntity> = classicPoemDao.random()

    override fun total(): Flow<Int> = classicPoemDao.total()
}