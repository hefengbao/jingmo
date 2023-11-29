package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.common.network.AppDispatchers
import com.hefengbao.jingmo.common.network.Dispatcher
import com.hefengbao.jingmo.data.database.dao.ChineseWisecrackDao
import com.hefengbao.jingmo.data.database.dao.IdiomDao
import com.hefengbao.jingmo.data.database.dao.PoemDao
import com.hefengbao.jingmo.data.database.dao.PoemSentenceDao
import com.hefengbao.jingmo.data.database.dao.PoemTagDao
import com.hefengbao.jingmo.data.database.dao.TagDao
import com.hefengbao.jingmo.data.database.dao.TongueTwisterDao
import com.hefengbao.jingmo.data.database.dao.WriterDao
import com.hefengbao.jingmo.data.database.entity.ChineseWisecrackEntity
import com.hefengbao.jingmo.data.database.entity.IdiomEntity
import com.hefengbao.jingmo.data.database.entity.PoemEntity
import com.hefengbao.jingmo.data.database.entity.PoemSentenceEntity
import com.hefengbao.jingmo.data.database.entity.PoemTagCrossRef
import com.hefengbao.jingmo.data.database.entity.TagEntity
import com.hefengbao.jingmo.data.database.entity.TongueTwisterEntity
import com.hefengbao.jingmo.data.database.entity.WriterEntity
import com.hefengbao.jingmo.data.model.ChineseWisecrack
import com.hefengbao.jingmo.data.model.Idiom
import com.hefengbao.jingmo.data.model.Poem
import com.hefengbao.jingmo.data.model.PoemSentence
import com.hefengbao.jingmo.data.model.PoemTag
import com.hefengbao.jingmo.data.model.Tag
import com.hefengbao.jingmo.data.model.TongueTwister
import com.hefengbao.jingmo.data.model.Writer
import com.hefengbao.jingmo.data.network.fake.FakeNetworkDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SyncRepositoryImpl @Inject constructor(
    private val networkDataSource: FakeNetworkDataSource,
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val poemDao: PoemDao,
    private val tagDao: TagDao,
    private val poemTagDao: PoemTagDao,
    private val writerDao: WriterDao,
    private val poemSentenceDao: PoemSentenceDao,
    private val chineseWisecrackDao: ChineseWisecrackDao,
    private val idiomDao: IdiomDao,
    private val tongueTwisterDao: TongueTwisterDao
) : SyncRepository {
    override fun syncPoems(): Flow<List<Poem>> = flow {
        emit(
            networkDataSource.getPoems()
        )
    }.flowOn(ioDispatcher)

    override fun syncWriters(): Flow<List<Writer>> = flow {
        emit(
            networkDataSource.getWriters()
        )
    }.flowOn(ioDispatcher)

    override fun syncTags(): Flow<List<Tag>> = flow {
        emit(
            networkDataSource.getTags()
        )
    }.flowOn(ioDispatcher)

    override fun syncPoemTagList(): Flow<List<PoemTag>> = flow {
        emit(
            networkDataSource.getPoemTagList()
        )
    }.flowOn(ioDispatcher)

    override fun syncPoemSentences(): Flow<List<PoemSentence>> = flow {
        emit(
            networkDataSource.getPoemSentences()
        )
    }.flowOn(ioDispatcher)

    override fun syncChineseWisecracks(): Flow<List<ChineseWisecrack>> = flow {
        emit(
            networkDataSource.getChineseWisecracks()
        )
    }.flowOn(ioDispatcher)

    override fun syncIdioms(): Flow<List<Idiom>> = flow {
        emit(
            networkDataSource.getIdioms()
        )
    }.flowOn(ioDispatcher)

    override fun syncTongueTwisters(): Flow<List<TongueTwister>> = flow {
        emit(
            networkDataSource.getTongueTwisters()
        )
    }.flowOn(ioDispatcher)

    override suspend fun insertPoem(entity: PoemEntity) {
        poemDao.insert(entity)
    }

    override suspend fun insertTag(entity: TagEntity) {
        tagDao.insert(entity)
    }

    override suspend fun insertPoemTag(entity: PoemTagCrossRef) {
        poemTagDao.insert(entity)
    }

    override suspend fun insertWriter(entity: WriterEntity) {
        writerDao.insert(entity)
    }

    override suspend fun insertPoemSentence(entity: PoemSentenceEntity) {
        poemSentenceDao.insert(entity)
    }

    override suspend fun insertChineseWisecrack(entity: ChineseWisecrackEntity) {
        chineseWisecrackDao.insert(entity)
    }

    override suspend fun insertIdiom(entity: IdiomEntity) {
        idiomDao.insert(entity)
    }

    override suspend fun insertTongueTwister(entity: TongueTwisterEntity) {
        tongueTwisterDao.insert(entity)
    }
}