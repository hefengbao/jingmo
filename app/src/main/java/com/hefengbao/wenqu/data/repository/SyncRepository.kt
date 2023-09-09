package com.hefengbao.wenqu.data.repository

import com.hefengbao.wenqu.data.database.entity.ChineseWisecrackEntity
import com.hefengbao.wenqu.data.database.entity.IdiomEntity
import com.hefengbao.wenqu.data.database.entity.PoemEntity
import com.hefengbao.wenqu.data.database.entity.PoemSentenceEntity
import com.hefengbao.wenqu.data.database.entity.PoemTagCrossRef
import com.hefengbao.wenqu.data.database.entity.TagEntity
import com.hefengbao.wenqu.data.database.entity.WriterEntity
import com.hefengbao.wenqu.data.model.ChineseWisecrack
import com.hefengbao.wenqu.data.model.Idiom
import com.hefengbao.wenqu.data.model.Poem
import com.hefengbao.wenqu.data.model.PoemSentence
import com.hefengbao.wenqu.data.model.PoemTag
import com.hefengbao.wenqu.data.model.Tag
import com.hefengbao.wenqu.data.model.Writer
import kotlinx.coroutines.flow.Flow

interface SyncRepository {
    fun syncPoems(): Flow<List<Poem>>
    fun syncWriters(): Flow<List<Writer>>
    fun syncTags(): Flow<List<Tag>>
    fun syncPoemTagList(): Flow<List<PoemTag>>
    fun syncPoemSentences(): Flow<List<PoemSentence>>
    fun syncChineseWisecracks(): Flow<List<ChineseWisecrack>>
    fun syncIdioms(): Flow<List<Idiom>>

    suspend fun insertPoem(entity: PoemEntity)
    suspend fun insertTag(entity: TagEntity)
    suspend fun insertPoemTag(entity: PoemTagCrossRef)
    suspend fun insertWriter(entity: WriterEntity)
    suspend fun insertPoemSentence(entity: PoemSentenceEntity)
    suspend fun insertChineseWisecrack(entity: ChineseWisecrackEntity)
    suspend fun insertIdiom(entity: IdiomEntity)
}