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
    private val appStatus: AppPreference,
    private val datasetStatus: DatasetPreference,
    private val readStatus: ReadStatusPreference,
) : PreferenceRepository {
    override fun getDatasetStatus(): Flow<DatasetVersion> = datasetStatus.datasetVersion

    override suspend fun setChinaWorldCultureHeritageVersion(version: Int) =
        datasetStatus.setChinsWorldCultureHeritageVersion(version)

    override suspend fun setChineseAntitheticalVersion(version: Int) =
        datasetStatus.setChineseAntitheticalCoupletVersion(version)

    override suspend fun setChineseCharacterVersion(version: Int) =
        datasetStatus.setChineseDictionaryVersion(version)

    override suspend fun setChineseExpressionVersion(version: Int) =
        datasetStatus.setChineseExpressionVersion(version)

    override suspend fun setChineseIdiomVersion(version: Int) =
        datasetStatus.setChineseIdiomVersion(version)

    override suspend fun setChineseKnowledgeVersion(version: Int) =
        datasetStatus.setChineseKnowledgeVersion(version)

    override suspend fun setChineseLyricVersion(version: Int) =
        datasetStatus.setChineseLyricVersion(version)

    override suspend fun setChineseModernPoetryVersion(version: Int) =
        datasetStatus.setChineseModernPoetryVersion(version)

    override suspend fun setChineseProverbVersion(version: Int) =
        datasetStatus.setChineseProverbVersion(version)

    override suspend fun setChineseQuoteVersion(version: Int) =
        datasetStatus.setChineseQuoteVersion(version)

    override suspend fun setChineseRiddleVersion(version: Int) =
        datasetStatus.setChineseRiddleVersion(version)

    override suspend fun setChineseTongueTwisterVersion(version: Int) =
        datasetStatus.setChineseTongueTwisterVersion(version)

    override suspend fun setChineseWisecracksVersion(version: Int) =
        datasetStatus.setChineseWisecrackVersion(version)

    override suspend fun setClassicalLiteratureClassicPoemsVersion(version: Int) =
        datasetStatus.setClassicalLiteratureClassicPoemVersion(version)

    override suspend fun setClassicalLiteraturePeopleVersion(version: Int) =
        datasetStatus.setClassicalLiteraturePeopleVersion(version)

    override suspend fun setClassicalLiteratureSentenceVersion(version: Int) =
        datasetStatus.setClassicalLiteratureSentenceVersion(version)

    override suspend fun setClassicalLiteratureWritingVersion(version: Int) =
        datasetStatus.setClassicalLiteratureWritingVersion(version)

    override suspend fun setClassicalLiteratureWritingCurrentPage(page: Int) =
        datasetStatus.setClassicalLiteratureWritingCurrentPage(page)

    override suspend fun setClassicalLiteratureWritingCurrentCount(count: Int) =
        datasetStatus.setClassicalLiteratureWritingCurrentCount(count)

    override fun getReadStatus(): Flow<ReadStatus> = readStatus.readStatus

    override suspend fun setChineseAntitheticalCoupletLastReadId(id: Int) =
        readStatus.setChineseAntitheticalCoupletLastReadId(id)

    override suspend fun setChineseIdiomLastReadId(id: Int) =
        readStatus.setChineseIdiomsLastReadId(id)

    override suspend fun setChineseModernPoetryLastReadId(id: Int) =
        readStatus.setChineseModernPoetryLastReadId(id)

    override suspend fun setChineseQuoteLastReadId(id: Int) =
        readStatus.setChineseQuoteLastReadId(id)

    override suspend fun setChineseKnowledgeLastReadId(id: Int) =
        readStatus.setChineseKnowledgeLastReadId(id)

    override suspend fun setChineseRiddleLastReadId(id: Int) =
        readStatus.setChineseRiddlesLastReadId(id)

    override suspend fun setChineseTongueTwisterLastReadId(id: Int) =
        readStatus.setChineseTongueTwistersLastReadId(id)

    override suspend fun setChineseWisecrackLastReadId(id: Int) =
        readStatus.setChineseWisecracksLastReadId(id)

    override suspend fun setClassicalLiteratureClassicPoemLastReadId(id: Int) =
        readStatus.setClassicLiteratureClassicPoemsLastReadId(id)

    override suspend fun setClassicalLiteraturePeopleLastReadId(id: Int) =
        readStatus.setClassicLiteraturePeopleLastReadId(id)

    override suspend fun setClassicalLiteratureSentenceLastReadId(id: Int) =
        readStatus.setClassicLiteratureSentencesLastReadId(id)

    override suspend fun setClassicalLiteratureWritingLastReadId(id: Int) =
        readStatus.setClassicLiteratureWritingsLastReadId(id)

    override fun getAppStatus(): Flow<AppStatus> = appStatus.appStatus

    override suspend fun setCaptureTextColor(color: String) = appStatus.setCaptureTextColor(color)

    override suspend fun setCaptureBackgroundColor(color: String) =
        appStatus.setCaptureBackgroundColor(color)

    override suspend fun setShowSyncDataTip(show: Boolean) = appStatus.setShowSyncDataTip(show)

    override suspend fun setUserAgreementVersion(version: Int) =
        appStatus.setUserAgreementVersion(version)
}