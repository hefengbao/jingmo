package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.data.model.DataStatus
import com.hefengbao.jingmo.data.model.DatasetVersion
import kotlinx.coroutines.flow.Flow

interface PreferenceRepository {
    fun getDatasetStatus(): Flow<DatasetVersion>
    suspend fun setChineseKnowledgeVersion(version: Int)
    suspend fun setChineseWisecracksVersion(version: Int)
    suspend fun setIdiomsVersion(version: Int)
    suspend fun setPeopleVersion(version: Int)
    suspend fun setPoemSentencesVersion(version: Int)
    suspend fun setRiddlesVersion(version: Int)
    suspend fun setTongueTwistersVersion(version: Int)
    suspend fun setWritingsVersion(version: Int)
    fun getDataStatus(): Flow<DataStatus>
    suspend fun setPoemVersion(version: Int)
    suspend fun setPoemLastReadId(id: Long)
    suspend fun setTagVersion(version: Int)
    suspend fun setPoemTagVersion(version: Int)
    suspend fun setWriterVersion(version: Int)

    suspend fun setPoemSentenceLastReadId(id: Long)

    suspend fun setIdiomLastReadId(id: Long)

    suspend fun setChineseWisecrackLastReadId(id: Long)
    suspend fun setCaptureColor(color: String)
    suspend fun setCaptureBackgroundColor(color: String)

    suspend fun setTongueTwisterLastReadId(id: Int)

    suspend fun setChineseKnowledgeLastReadId(id: Int)
    suspend fun setRiddleVersion(version: Int)
    suspend fun setRiddleLastReadId(id: Int)
}