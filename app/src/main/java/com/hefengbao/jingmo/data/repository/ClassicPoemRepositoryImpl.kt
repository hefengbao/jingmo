package com.hefengbao.jingmo.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hefengbao.jingmo.data.database.dao.ClassicPoemDao
import com.hefengbao.jingmo.data.database.entity.ClassicPoemCollectionEntity
import com.hefengbao.jingmo.data.database.entity.ClassicPoemEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ClassicPoemRepositoryImpl @Inject constructor(
    private val classicPoemDao: ClassicPoemDao
) : ClassicPoemRepository {
    override suspend fun insert(entity: ClassicPoemEntity) = classicPoemDao.insert(entity)

    override fun random(): Flow<ClassicPoemEntity> = classicPoemDao.random()

    override fun get(id: Int): Flow<ClassicPoemEntity> = classicPoemDao.get(id)

    override fun total(): Flow<Int> = classicPoemDao.total()

    override fun getNextId(id: Int): Flow<Int?> = classicPoemDao.getNextId(id)

    override fun getPrevId(id: Int): Flow<Int?> = classicPoemDao.getPrevId(id)

    override suspend fun collect(entity: ClassicPoemCollectionEntity) =
        classicPoemDao.collect(entity)

    override suspend fun uncollect(id: Int) = classicPoemDao.uncollect(id)

    override fun isCollect(id: Int): Flow<ClassicPoemCollectionEntity?> =
        classicPoemDao.isCollect(id)

    override fun collections(): Flow<PagingData<ClassicPoemEntity>> = Pager(
        config = PagingConfig(15),
        pagingSourceFactory = { classicPoemDao.collections() }
    ).flow

    override fun search(query: String): Flow<List<ClassicPoemEntity>> = classicPoemDao.search(query)
}