package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.data.model.DataStatus
import kotlinx.coroutines.flow.Flow

interface PreferenceRepository {
    fun getDataStatus(): Flow<DataStatus>
    suspend fun setPoemSyncedAndCount(synced: Boolean, count: Long)
    suspend fun setPoemLastReadId(id: Long)
    suspend fun setTagSyncedAndCount(synced: Boolean, count: Long)
    suspend fun setPoemTagSyncedAndCount(synced: Boolean, count: Long)
    suspend fun setWriterSyncedAndCount(synced: Boolean, count: Long)
    suspend fun setPoemSentenceSyncedAndCount(synced: Boolean, count: Long)
    suspend fun setPoemSentenceLastReadId(id: Long)
    suspend fun setIdiomSyncedAndCount(synced: Boolean, count: Long)
    suspend fun setIdiomLastReadId(id: Long)
    suspend fun setChineseWisecrackSyncedAndCount(synced: Boolean, count: Long)
    suspend fun setChineseWisecrackLastReadId(id: Long)
}