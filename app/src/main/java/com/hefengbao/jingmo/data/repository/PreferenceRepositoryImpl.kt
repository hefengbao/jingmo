package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.data.datastore.AppPreference
import com.hefengbao.jingmo.data.datastore.DatasetPreference
import com.hefengbao.jingmo.data.datastore.ReadStatusPreference
import com.hefengbao.jingmo.data.model.AppStatus
import com.hefengbao.jingmo.data.model.DatasetVersion
import com.hefengbao.jingmo.data.model.ReadStatus
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PreferenceRepositoryImpl @Inject constructor(
    private val app: AppPreference,
    private val dataset: DatasetPreference,
    private val readStatus: ReadStatusPreference,
) : PreferenceRepository {
    override fun getDatasetStatus(): Flow<DatasetVersion> = dataset.datasetVersion

    override suspend fun setChineseKnowledgeVersion(version: Int) =
        dataset.setChineseKnowledgeVersion(version)

    override suspend fun setChineseWisecracksVersion(version: Int) =
        dataset.setChineseWisecracksVersion(version)

    override suspend fun setClassicPoemsVersion(version: Int) =
        dataset.setClassicPoemsVersion(version)

    override suspend fun setIdiomsVersion(version: Int) = dataset.setIdiomsVersion(version)

    override suspend fun setPeopleVersion(version: Int) = dataset.setPeopleVersion(version)

    override suspend fun setPoemSentencesVersion(version: Int) =
        dataset.setPoemSentencesVersion(version)

    override suspend fun setRiddlesVersion(version: Int) = dataset.setRiddlesVersion(version)

    override suspend fun setTongueTwistersVersion(version: Int) =
        dataset.setTongueTwistersVersion(version)

    override suspend fun setWritingsVersion(version: Int) = dataset.setWritingsVersion(version)

    override suspend fun setWritingsCurrentPage(page: Int) = dataset.setWritingsCurrentPage(page)

    override suspend fun setWritingsCurrentCount(count: Int) =
        dataset.setWritingsCurrentCount(count)

    override fun getReadStatus(): Flow<ReadStatus> = readStatus.readStatus

    override suspend fun setChineseKnowledgeLastReadId(id: Int) =
        readStatus.setChineseKnowledgeLastReadId(id)

    override suspend fun setChineseWisecracksLastReadId(id: Int) =
        readStatus.setChineseWisecracksLastReadId(id)

    override suspend fun setClassicPoemLastReadId(id: Int) =
        readStatus.setClassicPoemsLastReadId(id)

    override suspend fun setIdiomsLastReadId(id: Int) = readStatus.setIdiomsLastReadId(id)

    override suspend fun setPeopleLastReadId(id: Int) = readStatus.setPeopleLastReadId(id)

    override suspend fun setPoemSentencesLastReadId(id: Int) =
        readStatus.setPoemSentencesLastReadId(id)

    override suspend fun setRiddlesLastReadId(id: Int) = readStatus.setRiddlesLastReadId(id)

    override suspend fun setTongueTwistersLastReadId(id: Int) =
        readStatus.setTongueTwistersLastReadId(id)

    override suspend fun setWritingsLastReadId(id: Int) = readStatus.setWritingsLastReadId(id)

    override fun getAppStatus(): Flow<AppStatus> = app.appStatus

    override suspend fun setCaptureTextColor(color: String) = app.setCaptureTextColor(color)

    override suspend fun setCaptureBackgroundColor(color: String) =
        app.setCaptureBackgroundColor(color)
}