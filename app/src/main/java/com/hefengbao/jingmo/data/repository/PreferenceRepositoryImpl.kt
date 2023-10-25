package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.data.datastore.AppPreference
import com.hefengbao.jingmo.data.model.DataStatus
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PreferenceRepositoryImpl @Inject constructor(
    private val preference: AppPreference
) : PreferenceRepository {
    override fun getDataStatus(): Flow<DataStatus> = preference.dataStats
    override suspend fun setPoemSyncedAndCount(synced: Boolean, count: Long) {
        preference.setPoemSyncedAndCount(synced, count)
    }

    override suspend fun setPoemLastReadId(id: Long) {
        preference.setPoemLastReadId(id)
    }

    override suspend fun setTagSyncedAndCount(synced: Boolean, count: Long) {
        preference.setTagSyncedAndCount(synced, count)
    }

    override suspend fun setPoemTagSyncedAndCount(synced: Boolean, count: Long) {
        preference.setPoemTagSyncedAndCount(synced, count)
    }

    override suspend fun setWriterSyncedAndCount(synced: Boolean, count: Long) {
        preference.setWriterSyncedAndCount(synced, count)
    }

    override suspend fun setPoemSentenceSyncedAndCount(synced: Boolean, count: Long) {
        preference.setPoemSentenceSyncedAndCount(synced, count)
    }

    override suspend fun setPoemSentenceLastReadId(id: Long) {
        preference.setPoemSentenceLastReadId(id)
    }

    override suspend fun setIdiomSyncedAndCount(synced: Boolean, count: Long) {
        preference.setIdiomSyncedAndCount(synced, count)
    }

    override suspend fun setIdiomLastReadId(id: Long) {
        preference.setIdiomLastReadId(id)
    }

    override suspend fun setChineseWisecrackSyncedAndCount(synced: Boolean, count: Long) {
        preference.setChineseWisecrackSyncedAndCount(synced, count)
    }

    override suspend fun setChineseWisecrackLastReadId(id: Long) {
        preference.setChineseWisecrackLastReadId(id)
    }

    override suspend fun setCaptureColor(color: String) {
        preference.setCaptureColor(color)
    }

    override suspend fun setCaptureBackgroundColor(color: String) {
        preference.setCaptureBackgroundColor(color)
    }
}