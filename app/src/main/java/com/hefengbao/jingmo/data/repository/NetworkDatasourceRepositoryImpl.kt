package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.common.network.Result
import com.hefengbao.jingmo.common.network.SafeApiCall
import com.hefengbao.jingmo.data.database.AppDatabase
import com.hefengbao.jingmo.data.database.entity.ChineseKnowledgeEntity
import com.hefengbao.jingmo.data.database.entity.ChineseWisecrackEntity
import com.hefengbao.jingmo.data.database.entity.ClassicPoemEntity
import com.hefengbao.jingmo.data.database.entity.IdiomEntity
import com.hefengbao.jingmo.data.database.entity.PeopleEntity
import com.hefengbao.jingmo.data.database.entity.PoemSentenceEntity
import com.hefengbao.jingmo.data.database.entity.RiddleEntity
import com.hefengbao.jingmo.data.database.entity.TongueTwisterEntity
import com.hefengbao.jingmo.data.database.entity.WritingEntity
import com.hefengbao.jingmo.data.model.ChineseKnowledge
import com.hefengbao.jingmo.data.model.ChineseWisecrack
import com.hefengbao.jingmo.data.model.ClassicPoem
import com.hefengbao.jingmo.data.model.Dataset
import com.hefengbao.jingmo.data.model.Idiom
import com.hefengbao.jingmo.data.model.PeopleWrapper
import com.hefengbao.jingmo.data.model.PoemSentence
import com.hefengbao.jingmo.data.model.Riddle
import com.hefengbao.jingmo.data.model.TongueTwister
import com.hefengbao.jingmo.data.model.WritingWrapper
import com.hefengbao.jingmo.data.network.Network
import javax.inject.Inject

class NetworkDatasourceRepositoryImpl @Inject constructor(
    private val network: Network,
    private val database: AppDatabase
) : NetworkDatasourceRepository, SafeApiCall {
    override suspend fun dataset(): Result<List<Dataset>> = safeApiCall {
        network.dataset()
    }

    override suspend fun syncChineseKnowledge(): Result<List<ChineseKnowledge>> = safeApiCall {
        network.chineseKnowledge()
    }

    override suspend fun insertChinesKnowledge(entity: ChineseKnowledgeEntity) {
        database.chineseKnowledgeDao().insert(entity)
    }

    override suspend fun syncChineseWisecracks(): Result<List<ChineseWisecrack>> = safeApiCall {
        network.chineseWisecracks()
    }

    override suspend fun insertChineseWisecrack(entity: ChineseWisecrackEntity) {
        database.chineseWisecrackDao().insert(entity)
    }

    override suspend fun syncClassicPoems(): Result<List<ClassicPoem>> = safeApiCall {
        network.classicPoems()
    }

    override suspend fun insertClassicPoems(entity: ClassicPoemEntity) =
        database.classicPoemDao().insert(entity)

    override suspend fun syncIdioms(): Result<List<Idiom>> = safeApiCall {
        network.idioms()
    }

    override suspend fun insertIdiom(entity: IdiomEntity) {
        database.idiomDao().insert(entity)
    }

    override suspend fun syncPeople(page: Int): Result<PeopleWrapper> = safeApiCall {
        network.people(page)
    }

    override suspend fun insertPeople(entity: PeopleEntity) {
        database.peopleDao().insert(entity)
    }

    override suspend fun syncPoemSentences(): Result<List<PoemSentence>> = safeApiCall {
        network.poemSentences()
    }

    override suspend fun insertPoemSentence(entity: PoemSentenceEntity) {
        database.poemSentenceDao().insert(entity)
    }

    override suspend fun syncRiddles(): Result<List<Riddle>> = safeApiCall {
        network.riddles()
    }

    override suspend fun insertRiddle(entity: RiddleEntity) {
        database.riddleDao().insert(entity)
    }

    override suspend fun syncTongueTwisters(): Result<List<TongueTwister>> = safeApiCall {
        network.tongueTwisters()
    }

    override suspend fun insertTongueTwister(entity: TongueTwisterEntity) {
        database.tongueTwisterDao().insert(entity)
    }

    override suspend fun syncWritings(page: Int): Result<WritingWrapper> = safeApiCall {
        network.writings(page)
    }

    override suspend fun insertWriting(entity: WritingEntity) {
        database.writingDao().insert(entity)
    }

}