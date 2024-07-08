/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.data.model.AppStatus
import com.hefengbao.jingmo.data.model.DatasetVersion
import com.hefengbao.jingmo.data.model.ReadStatus
import kotlinx.coroutines.flow.Flow

interface PreferenceRepository {
    fun getDatasetStatus(): Flow<DatasetVersion>
    suspend fun setChineseExpressionVersion(version: Int)
    suspend fun setChineseKnowledgeVersion(version: Int)
    suspend fun setChineseWisecracksVersion(version: Int)
    suspend fun setClassicPoemsVersion(version: Int)
    suspend fun setDictionaryVersion(version: Int)
    suspend fun setIdiomsVersion(version: Int)
    suspend fun setPeopleVersion(version: Int)
    suspend fun setPoemSentencesVersion(version: Int)
    suspend fun setRiddlesVersion(version: Int)
    suspend fun setTongueTwistersVersion(version: Int)
    suspend fun setWritingsVersion(version: Int)
    suspend fun setWritingsCurrentPage(page: Int)
    suspend fun setWritingsCurrentCount(count: Int)

    fun getReadStatus(): Flow<ReadStatus>
    suspend fun setChineseKnowledgeLastReadId(id: Int)
    suspend fun setChineseWisecracksLastReadId(id: Int)
    suspend fun setClassicPoemLastReadId(id: Int)
    suspend fun setIdiomsLastReadId(id: Int)
    suspend fun setPeopleLastReadId(id: Int)
    suspend fun setPoemSentencesLastReadId(id: Int)
    suspend fun setRiddlesLastReadId(id: Int)
    suspend fun setTongueTwistersLastReadId(id: Int)
    suspend fun setWritingsLastReadId(id: Int)

    fun getAppStatus(): Flow<AppStatus>
    suspend fun setCaptureTextColor(color: String)
    suspend fun setCaptureBackgroundColor(color: String)
}