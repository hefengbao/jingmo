package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.data.model.DataStatus
import kotlinx.coroutines.flow.Flow

interface PreferenceRepository {
    fun getDataStatus(): Flow<DataStatus>
    suspend fun setPoemVersion(version: Int)
    suspend fun setPoemLastReadId(id: Long)
    suspend fun setTagVersion(version: Int)
    suspend fun setPoemTagVersion(version: Int)
    suspend fun setWriterVersion(version: Int)
    suspend fun setPoemSentenceVersion(version: Int)
    suspend fun setPoemSentenceLastReadId(id: Long)
    suspend fun setIdiomVersion(version: Int)
    suspend fun setIdiomLastReadId(id: Long)
    suspend fun setChineseWisecrackVersion(version: Int)
    suspend fun setChineseWisecrackLastReadId(id: Long)
    suspend fun setCaptureColor(color: String)
    suspend fun setCaptureBackgroundColor(color: String)
    suspend fun setTongueTwisterVersion(version: Int)
    suspend fun setTongueTwisterLastReadId(id: Int)
    suspend fun setChineseKnowledgeVersion(version: Int)
    suspend fun setChineseKnowledgeLastReadId(id: Int)
}