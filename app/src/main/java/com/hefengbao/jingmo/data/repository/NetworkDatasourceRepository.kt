package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.common.network.Result
import com.hefengbao.jingmo.data.database.entity.ChineseKnowledgeEntity
import com.hefengbao.jingmo.data.database.entity.ChineseWisecrackEntity
import com.hefengbao.jingmo.data.database.entity.IdiomEntity
import com.hefengbao.jingmo.data.database.entity.PeopleEntity
import com.hefengbao.jingmo.data.database.entity.PoemSentenceEntity
import com.hefengbao.jingmo.data.database.entity.RiddleEntity
import com.hefengbao.jingmo.data.database.entity.TongueTwisterEntity
import com.hefengbao.jingmo.data.database.entity.WritingEntity
import com.hefengbao.jingmo.data.model.ChineseKnowledge
import com.hefengbao.jingmo.data.model.ChineseWisecrack
import com.hefengbao.jingmo.data.model.Dataset
import com.hefengbao.jingmo.data.model.Idiom
import com.hefengbao.jingmo.data.model.People
import com.hefengbao.jingmo.data.model.PeopleWrapper
import com.hefengbao.jingmo.data.model.PoemSentence
import com.hefengbao.jingmo.data.model.Riddle
import com.hefengbao.jingmo.data.model.TongueTwister
import com.hefengbao.jingmo.data.model.WritingWrapper


interface NetworkDatasourceRepository {
    suspend fun dataset(): Result<List<Dataset>>
    suspend fun syncChineseKnowledge(): Result<List<ChineseKnowledge>>
    suspend fun insertChinesKnowledge(entity: ChineseKnowledgeEntity)
    suspend fun syncChineseWisecracks(): Result<List<ChineseWisecrack>>
    suspend fun insertChineseWisecrack(entity: ChineseWisecrackEntity)
    suspend fun syncIdioms(): Result<List<Idiom>>
    suspend fun insertIdiom(entity: IdiomEntity)
    suspend fun syncPeople(page: Int): Result<PeopleWrapper>
    suspend fun insertPeople(entity: PeopleEntity)
    suspend fun syncPoemSentences(): Result<List<PoemSentence>>
    suspend fun insertPoemSentence(entity: PoemSentenceEntity)
    suspend fun syncRiddles(): Result<List<Riddle>>
    suspend fun insertRiddle(entity: RiddleEntity)
    suspend fun syncTongueTwisters(): Result<List<TongueTwister>>
    suspend fun insertTongueTwister(entity: TongueTwisterEntity)
    suspend fun syncWritings(page: Int): Result<WritingWrapper>
    suspend fun insertWriting(entity: WritingEntity)
}