package com.hefengbao.wenqu.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.hefengbao.wenqu.data.model.DataStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "wenqu")

private fun getBoolean(
    context: Context,
    key: Preferences.Key<Boolean>,
    default: Boolean = false
): Flow<Boolean> =
    context.dataStore.data.map { it[key] ?: default }

private suspend fun setBoolean(context: Context, key: Preferences.Key<Boolean>, value: Boolean) {
    context.dataStore.edit { it[key] = value }
}

private fun getLong(context: Context, key: Preferences.Key<Long>, default: Long = 0L): Flow<Long> =
    context.dataStore.data.map { it[key] ?: default }

private suspend fun setLong(context: Context, key: Preferences.Key<Long>, value: Long) {
    context.dataStore.edit { it[key] = value }
}

class AppPreference(
    private val context: Context
) {
    val dataStats: Flow<DataStatus> = context.dataStore.data.map {
        DataStatus(
            poemSynced = it[PREF_POEM_SYNCED] ?: false,
            poemCount = it[PREF_POEM_COUNT] ?: 0L,
            poemLastReadId = it[PREF_POEM_LAST_READ_ID] ?: 1L,
            tagSynced = it[PREF_TAG_SYNCED] ?: false,
            tagCount = it[PREF_TAG_COUNT] ?: 0L,
            poemTagSynced = it[PREF_POEM_TAG_SYNCED] ?: false,
            poemTagCount = it[PREF_POEM_TAG_COUNT] ?: 0L,
            writerSynced = it[PREF_WRITER_SYNCED] ?: false,
            writerCount = it[PREF_WRITER_COUNT] ?: 0L,
            poemSentenceSynced = it[PREF_POEM_SENTENCE_SYNCED] ?: false,
            poemSentenceCount = it[PREF_POEM_SENTENCE_COUNT] ?: 0L,
            poemSentenceLastReadId = it[PREF_POEM_SENTENCE_LAST_READ_ID] ?: 1L,
            chineseWisecrackSynced = it[PREF_CHINESE_WISECRACK_SYNCED] ?: false,
            chineseWisecrackCount = it[PREF_CHINESE_WISECRACK_COUNT] ?: 0L,
            chineseWisecrackLastReadId = it[PREF_CHINESE_WISECRACK_LAST_READ_ID] ?: 1L,
            idiomSynced = it[PREF_IDIOM_SYNCED] ?: false,
            idiomCount = it[PREF_IDIOM_COUNT] ?: 0L,
            idiomLastReadId = it[PREF_IDIOM_LAST_READ_ID] ?: 1L
        )
    }

    suspend fun setPoemSyncedAndCount(synced: Boolean, count: Long) {
        context.dataStore.edit {
            it[PREF_POEM_SYNCED] = synced
            it[PREF_POEM_COUNT] = count
        }
    }

    suspend fun setPoemLastReadId(id: Long) = setLong(context, PREF_POEM_LAST_READ_ID, id)

    suspend fun setTagSyncedAndCount(synced: Boolean, count: Long) {
        context.dataStore.edit {
            it[PREF_TAG_SYNCED] = synced
            it[PREF_TAG_COUNT] = count
        }
    }

    suspend fun setPoemTagSyncedAndCount(synced: Boolean, count: Long) {
        context.dataStore.edit {
            it[PREF_POEM_TAG_SYNCED] = synced
            it[PREF_POEM_TAG_COUNT] = count
        }
    }

    suspend fun setWriterSyncedAndCount(synced: Boolean, count: Long) {
        context.dataStore.edit {
            it[PREF_WRITER_SYNCED] = synced
            it[PREF_WRITER_COUNT] = count
        }
    }

    suspend fun setPoemSentenceSyncedAndCount(synced: Boolean, count: Long) {
        context.dataStore.edit {
            it[PREF_POEM_SENTENCE_SYNCED] = synced
            it[PREF_POEM_SENTENCE_COUNT] = count
        }
    }

    suspend fun setPoemSentenceLastReadId(id: Long) =
        setLong(context, PREF_POEM_SENTENCE_LAST_READ_ID, id)

    suspend fun setChineseWisecrackSyncedAndCount(synced: Boolean, count: Long) {
        context.dataStore.edit {
            it[PREF_CHINESE_WISECRACK_SYNCED] = synced
            it[PREF_CHINESE_WISECRACK_COUNT] = count
        }
    }

    suspend fun setChineseWisecrackLastReadId(id: Long) =
        setLong(context, PREF_CHINESE_WISECRACK_LAST_READ_ID, id)

    suspend fun setIdiomSyncedAndCount(synced: Boolean, count: Long) {
        context.dataStore.edit {
            it[PREF_IDIOM_SYNCED] = synced
            it[PREF_IDIOM_COUNT] = count
        }
    }

    suspend fun setIdiomLastReadId(id: Long) = setLong(context, PREF_IDIOM_LAST_READ_ID, id)

    companion object {
        private val PREF_POEM_SYNCED = booleanPreferencesKey("key_poem_synced")
        private val PREF_POEM_COUNT = longPreferencesKey("key_poem_count")
        private val PREF_POEM_LAST_READ_ID = longPreferencesKey("key_poem_last_read_id")
        private val PREF_TAG_SYNCED = booleanPreferencesKey("key_tag_synced")
        private val PREF_TAG_COUNT = longPreferencesKey("key_tag_count")
        private val PREF_WRITER_SYNCED = booleanPreferencesKey("key_writer_synced")
        private val PREF_WRITER_COUNT = longPreferencesKey("key_writer_count")
        private val PREF_POEM_TAG_SYNCED = booleanPreferencesKey("key_poem_tag_synced")
        private val PREF_POEM_TAG_COUNT = longPreferencesKey("key_poem_tag_count")
        private val PREF_POEM_SENTENCE_SYNCED = booleanPreferencesKey("key_poem_sentence_synced")
        private val PREF_POEM_SENTENCE_COUNT = longPreferencesKey("pref_poem_sentence_count")
        private val PREF_POEM_SENTENCE_LAST_READ_ID =
            longPreferencesKey("key_poem_sentence_last_read_id")
        private val PREF_CHINESE_WISECRACK_SYNCED =
            booleanPreferencesKey("key_chinese_wisecrack_synced")
        private val PREF_CHINESE_WISECRACK_COUNT =
            longPreferencesKey("pref_chinese_wisecrack_count")
        private val PREF_CHINESE_WISECRACK_LAST_READ_ID =
            longPreferencesKey("key_chinese_wisecrack_last_read_id")
        private val PREF_IDIOM_SYNCED = booleanPreferencesKey("key_idiom_synced")
        private val PREF_IDIOM_COUNT = longPreferencesKey("pref_idiom_count")
        private val PREF_IDIOM_LAST_READ_ID = longPreferencesKey("key_idiom_last_read_id")
    }
}