package com.hefengbao.wenqu.data.repository

import com.hefengbao.wenqu.data.model.Poem
import com.hefengbao.wenqu.data.model.PoemTag
import com.hefengbao.wenqu.data.model.Tag
import com.hefengbao.wenqu.data.model.Writer
import kotlinx.coroutines.flow.Flow

interface SyncRepository {
    fun syncPoems(): Flow<List<Poem>>
    fun syncWriters(): Flow<List<Writer>>
    fun syncTags(): Flow<List<Tag>>
    fun syncPoemTagList(): Flow<List<PoemTag>>
}