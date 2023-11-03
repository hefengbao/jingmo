package com.hefengbao.jingmo.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.hefengbao.jingmo.common.Constant
import com.hefengbao.jingmo.data.model.DataStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = Constant.DATASTORE_NAME)

private fun getLong(context: Context, key: Preferences.Key<Long>, default: Long = 0L): Flow<Long> =
    context.dataStore.data.map { it[key] ?: default }

private suspend fun setLong(context: Context, key: Preferences.Key<Long>, value: Long) {
    context.dataStore.edit { it[key] = value }
}

private fun getInt(context: Context, key: Preferences.Key<Int>, default: Int = 0): Flow<Int> =
    context.dataStore.data.map { it[key] ?: default }

private suspend fun setInt(context: Context, key: Preferences.Key<Int>, value: Int) {
    context.dataStore.edit { it[key] = value }
}

private suspend fun setString(context: Context, key: Preferences.Key<String>, value: String) {
    context.dataStore.edit { it[key] = value }
}

class AppPreference(
    private val context: Context
) {
    val dataStats: Flow<DataStatus> = context.dataStore.data.map {
        DataStatus(
            poemVersion = it[PREF_POEM_VERSION] ?: 0,
            poemLastReadId = it[PREF_POEM_LAST_READ_ID] ?: 1L,
            tagVersion = it[PREF_TAG_VERSION] ?: 0,
            poemTagVersion = it[PREF_POEM_TAG_VERSION] ?: 0,
            writerVersion = it[PREF_WRITER_VERSION] ?: 0,
            poemSentenceVersion = it[PREF_POEM_SENTENCE_VERSION] ?: 0,
            poemSentenceLastReadId = it[PREF_POEM_SENTENCE_LAST_READ_ID] ?: 1L,
            chineseWisecrackVersion = it[PREF_CHINESE_WISECRACK_VERSION] ?: 0,
            chineseWisecrackLastReadId = it[PREF_CHINESE_WISECRACK_LAST_READ_ID] ?: 1L,
            idiomVersion = it[PREF_IDIOM_VERSION] ?: 0,
            idiomLastReadId = it[PREF_IDIOM_LAST_READ_ID] ?: 1L,
            captureColor = it[PREF_CAPTURE_COLOR] ?: "white",
            captureBackgroundColor = it[PREF_CAPTURE_BACKGROUND_COLOR] ?: "#065279"
        )
    }

    suspend fun setPoemVersion(version: Int) = setInt(context, PREF_POEM_VERSION, version)

    suspend fun setPoemLastReadId(id: Long) = setLong(context, PREF_POEM_LAST_READ_ID, id)

    suspend fun setTagVersion(version: Int) = setInt(context, PREF_TAG_VERSION, version)

    suspend fun setPoemTagVersion(version: Int) = setInt(context, PREF_POEM_TAG_VERSION, version)

    suspend fun setWriterVersion(version: Int) = setInt(context, PREF_WRITER_VERSION, version)

    suspend fun setPoemSentenceVersion(version: Int) =
        setInt(context, PREF_POEM_SENTENCE_VERSION, version)

    suspend fun setPoemSentenceLastReadId(id: Long) =
        setLong(context, PREF_POEM_SENTENCE_LAST_READ_ID, id)

    suspend fun setChineseWisecrackVersion(version: Int) =
        setInt(context, PREF_CHINESE_WISECRACK_VERSION, version)

    suspend fun setChineseWisecrackLastReadId(id: Long) =
        setLong(context, PREF_CHINESE_WISECRACK_LAST_READ_ID, id)

    suspend fun setIdiomVersion(version: Int) = setInt(context, PREF_IDIOM_VERSION, version)

    suspend fun setIdiomLastReadId(id: Long) = setLong(context, PREF_IDIOM_LAST_READ_ID, id)

    suspend fun setCaptureColor(color: String) = setString(context, PREF_CAPTURE_COLOR, color)

    suspend fun setCaptureBackgroundColor(color: String) =
        setString(context, PREF_CAPTURE_BACKGROUND_COLOR, color)

    companion object {
        private val PREF_POEM_VERSION = intPreferencesKey("key_poem_version")
        private val PREF_POEM_LAST_READ_ID = longPreferencesKey("key_poem_last_read_id")
        private val PREF_TAG_VERSION = intPreferencesKey("key_tag_version")
        private val PREF_WRITER_VERSION = intPreferencesKey("key_writer_version")
        private val PREF_POEM_TAG_VERSION = intPreferencesKey("key_poem_tag_version")
        private val PREF_POEM_SENTENCE_VERSION = intPreferencesKey("key_poem_sentence_version")
        private val PREF_POEM_SENTENCE_LAST_READ_ID =
            longPreferencesKey("key_poem_sentence_last_read_id")
        private val PREF_CHINESE_WISECRACK_VERSION =
            intPreferencesKey("key_chinese_wisecrack_version")
        private val PREF_CHINESE_WISECRACK_LAST_READ_ID =
            longPreferencesKey("key_chinese_wisecrack_last_read_id")
        private val PREF_IDIOM_VERSION = intPreferencesKey("pref_idiom_version")
        private val PREF_IDIOM_LAST_READ_ID = longPreferencesKey("key_idiom_last_read_id")

        private val PREF_CAPTURE_COLOR = stringPreferencesKey("pref_capture_color")
        private val PREF_CAPTURE_BACKGROUND_COLOR =
            stringPreferencesKey("pref_capture_background_color")
    }
}