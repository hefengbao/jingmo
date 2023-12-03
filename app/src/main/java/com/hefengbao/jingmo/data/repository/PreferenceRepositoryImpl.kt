package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.data.datastore.AppPreference
import com.hefengbao.jingmo.data.model.DataStatus
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PreferenceRepositoryImpl @Inject constructor(
    private val preference: AppPreference
) : PreferenceRepository {
    override fun getDataStatus(): Flow<DataStatus> = preference.dataStats
    override suspend fun setPoemVersion(version: Int) = preference.setPoemVersion(version)
    override suspend fun setPoemLastReadId(id: Long) = preference.setPoemLastReadId(id)
    override suspend fun setTagVersion(version: Int) = preference.setTagVersion(version)
    override suspend fun setPoemTagVersion(version: Int) = preference.setPoemTagVersion(version)
    override suspend fun setWriterVersion(version: Int) = preference.setWriterVersion(version)
    override suspend fun setPoemSentenceVersion(version: Int) =
        preference.setPoemSentenceVersion(version)

    override suspend fun setPoemSentenceLastReadId(id: Long) =
        preference.setPoemSentenceLastReadId(id)

    override suspend fun setIdiomVersion(version: Int) = preference.setIdiomVersion(version)
    override suspend fun setIdiomLastReadId(id: Long) = preference.setIdiomLastReadId(id)
    override suspend fun setChineseWisecrackVersion(version: Int) =
        preference.setChineseWisecrackVersion(version)

    override suspend fun setChineseWisecrackLastReadId(id: Long) =
        preference.setChineseWisecrackLastReadId(id)

    override suspend fun setCaptureColor(color: String) = preference.setCaptureColor(color)
    override suspend fun setCaptureBackgroundColor(color: String) =
        preference.setCaptureBackgroundColor(color)

    override suspend fun setTongueTwisterVersion(version: Int) =
        preference.setTongueTwisterVersion(version)

    override suspend fun setTongueTwisterLastReadId(id: Int) =
        preference.setTongueTwisterLastReadId(id)

    override suspend fun setChineseKnowledgeVersion(version: Int) =
        preference.setChineseKnowledgeVersion(version)

    override suspend fun setChineseKnowledgeLastReadId(id: Int) =
        preference.setChineseKnowledgeLastReadId(id)
}