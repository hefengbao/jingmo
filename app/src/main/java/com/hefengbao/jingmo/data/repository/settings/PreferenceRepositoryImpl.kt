/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.repository.settings

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

    override suspend fun setChineseDictionaryVersion(version: Int) =
        dataset.setChineseDictionaryVersion(version)

    override suspend fun setChineseExpressionVersion(version: Int) =
        dataset.setChineseExpressionVersion(version)

    override suspend fun setChineseIdiomVersion(version: Int) =
        dataset.setChineseIdiomVersion(version)

    override suspend fun setChineseKnowledgeVersion(version: Int) =
        dataset.setChineseKnowledgeVersion(version)

    override suspend fun setChineseLyricVersion(version: Int) =
        dataset.setChineseLyricVersion(version)

    override suspend fun setChineseProverbVersion(version: Int) =
        dataset.setChineseProverbVersion(version)

    override suspend fun setChineseRiddleVersion(version: Int) =
        dataset.setChineseRiddleVersion(version)

    override suspend fun setChineseTongueTwisterVersion(version: Int) =
        dataset.setChineseTongueTwisterVersion(version)

    override suspend fun setChineseWisecracksVersion(version: Int) =
        dataset.setChineseWisecrackVersion(version)

    override suspend fun setClassicalLiteratureClassicPoemsVersion(version: Int) =
        dataset.setClassicalLiteratureClassicPoemVersion(version)

    override suspend fun setClassicalLiteraturePeopleVersion(version: Int) =
        dataset.setClassicalLiteraturePeopleVersion(version)

    override suspend fun setClassicalLiteratureSentenceVersion(version: Int) =
        dataset.setClassicalLiteratureSentenceVersion(version)

    override suspend fun setClassicalLiteratureWritingVersion(version: Int) =
        dataset.setClassicalLiteratureWritingVersion(version)

    override suspend fun setClassicalLiteratureWritingCurrentPage(page: Int) =
        dataset.setClassicalLiteratureWritingCurrentPage(page)

    override suspend fun setClassicalLiteratureWritingCurrentCount(count: Int) =
        dataset.setClassicalLiteratureWritingCurrentCount(count)

    override fun getReadStatus(): Flow<ReadStatus> = readStatus.readStatus

    override suspend fun setChineseIdiomLastReadId(id: Int) = readStatus.setIdiomsLastReadId(id)

    override suspend fun setChineseKnowledgeLastReadId(id: Int) =
        readStatus.setChineseKnowledgeLastReadId(id)

    override suspend fun setChineseRiddleLastReadId(id: Int) = readStatus.setRiddlesLastReadId(id)

    override suspend fun setChineseTongueTwisterLastReadId(id: Int) =
        readStatus.setTongueTwistersLastReadId(id)

    override suspend fun setChineseWisecrackLastReadId(id: Int) =
        readStatus.setChineseWisecracksLastReadId(id)

    override suspend fun setClassicalLiteratureClassicPoemLastReadId(id: Int) =
        readStatus.setClassicPoemsLastReadId(id)

    override suspend fun setClassicalLiteraturePeopleLastReadId(id: Int) =
        readStatus.setPeopleLastReadId(id)

    override suspend fun setClassicalLiteratureSentenceLastReadId(id: Int) =
        readStatus.setPoemSentencesLastReadId(id)

    override suspend fun setClassicalLiteratureWritingLastReadId(id: Int) =
        readStatus.setWritingsLastReadId(id)

    override fun getAppStatus(): Flow<AppStatus> = app.appStatus

    override suspend fun setCaptureTextColor(color: String) = app.setCaptureTextColor(color)

    override suspend fun setCaptureBackgroundColor(color: String) =
        app.setCaptureBackgroundColor(color)
}