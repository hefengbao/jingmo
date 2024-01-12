package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.data.model.AppStatus
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

    fun getDataStatus(): Flow<AppStatus>
    suspend fun setChineseKnowledgeLastReadId(id: Int)
    suspend fun setChineseWisecracksLastReadId(id: Int)
    suspend fun setIdiomsLastReadId(id: Int)
    suspend fun setPeopleLastReadId(id: Int)
    suspend fun setPoemSentencesLastReadId(id: Int)
    suspend fun setRiddlesLastReadId(id: Int)
    suspend fun setTongueTwistersLastReadId(id: Int)
    suspend fun setWritingsLastReadId(id: Int)


    suspend fun setCaptureTextColor(color: String)
    suspend fun setCaptureBackgroundColor(color: String)
}