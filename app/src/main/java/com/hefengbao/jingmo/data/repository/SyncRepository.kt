package com.hefengbao.jingmo.data.repository

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
import kotlinx.coroutines.flow.Flow

interface SyncRepository {
    fun syncPoems(): Flow<List<Poem>>
    fun syncWriters(): Flow<List<Writer>>
    fun syncTags(): Flow<List<Tag>>
    fun syncPoemTagList(): Flow<List<PoemTag>>
    fun syncPoemSentences(): Flow<List<PoemSentence>>
    fun syncChineseWisecracks(): Flow<List<ChineseWisecrack>>
    fun syncIdioms(): Flow<List<Idiom>>
    fun syncTongueTwisters(): Flow<List<TongueTwister>>

    suspend fun insertPoem(entity: PoemEntity)
    suspend fun insertTag(entity: TagEntity)
    suspend fun insertPoemTag(entity: PoemTagCrossRef)
    suspend fun insertWriter(entity: WriterEntity)
    suspend fun insertPoemSentence(entity: PoemSentenceEntity)
    suspend fun insertChineseWisecrack(entity: ChineseWisecrackEntity)
    suspend fun insertIdiom(entity: IdiomEntity)
    suspend fun insertTongueTwister(entity: TongueTwisterEntity)
}