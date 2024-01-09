package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.data.datastore.AppPreference
import com.hefengbao.jingmo.data.datastore.DatasetPreference
import com.hefengbao.jingmo.data.model.DataStatus
import com.hefengbao.jingmo.data.model.DatasetVersion
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PreferenceRepositoryImpl @Inject constructor(
    private val app: AppPreference,
    private val dataset: DatasetPreference,
) : PreferenceRepository {
    override fun getDatasetStatus(): Flow<DatasetVersion> = dataset.datasetVersion
    override suspend fun setChineseKnowledgeVersion(version: Int) = dataset.setChineseKnowledgeVersion(version)
    override suspend fun setChineseWisecracksVersion(version: Int) = dataset.setChineseWisecracksVersion(version)
    override suspend fun setIdiomsVersion(version: Int) = dataset.setIdiomsVersion(version)
    override suspend fun setPeopleVersion(version: Int) = dataset.setPeopleVersion(version)
    override suspend fun setPoemSentencesVersion(version: Int) = dataset.setPoemSentencesVersion(version)
    override suspend fun setRiddlesVersion(version: Int) = dataset.setRiddlesVersion(version)
    override suspend fun setTongueTwistersVersion(version: Int) = dataset.setTongueTwistersVersion(version)
    override suspend fun setWritingsVersion(version: Int) = dataset.setWritingsVersion(version)

    override fun getDataStatus(): Flow<DataStatus> = app.dataStats

    override suspend fun setPoemVersion(version: Int) = app.setPoemVersion(version)
    override suspend fun setPoemLastReadId(id: Long) = app.setPoemLastReadId(id)
    override suspend fun setTagVersion(version: Int) = app.setTagVersion(version)
    override suspend fun setPoemTagVersion(version: Int) = app.setPoemTagVersion(version)
    override suspend fun setWriterVersion(version: Int) = app.setWriterVersion(version)

    override suspend fun setPoemSentenceLastReadId(id: Long) =
        app.setPoemSentenceLastReadId(id)

    override suspend fun setIdiomLastReadId(id: Long) = app.setIdiomLastReadId(id)

    override suspend fun setChineseWisecrackLastReadId(id: Long) =
        app.setChineseWisecrackLastReadId(id)

    override suspend fun setCaptureColor(color: String) = app.setCaptureColor(color)
    override suspend fun setCaptureBackgroundColor(color: String) =
        app.setCaptureBackgroundColor(color)

    override suspend fun setTongueTwisterLastReadId(id: Int) =
        app.setTongueTwisterLastReadId(id)

    override suspend fun setChineseKnowledgeLastReadId(id: Int) =
        app.setChineseKnowledgeLastReadId(id)

    override suspend fun setRiddleVersion(version: Int) = app.setRiddleVersion(version)

    override suspend fun setRiddleLastReadId(id: Int) = app.setRiddleLastReadId(id)
}