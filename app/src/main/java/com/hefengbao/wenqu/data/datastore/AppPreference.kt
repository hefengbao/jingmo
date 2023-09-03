package com.hefengbao.wenqu.data.pref

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
    /*val dataStats: Flow<DataStatus> = context.dataStore.data.map {
        DataStatus(
            poemSynced = it[PREF_POEM_SYNCED] ?: false,
            poemCount = it[PREF_POEM_COUNT] ?: 0L,
            poemLastReadId = it[]
        )
    }*/

    val poemSynced = getBoolean(context, PREF_POEM_SYNCED)

    suspend fun setPoemSynced(synced: Boolean) = setBoolean(context, PREF_POEM_SYNCED, synced)

    val poemCount = getLong(context, PREF_POEM_COUNT)

    suspend fun setPoemCount(count: Long) = setLong(context, PREF_POEM_COUNT, count)

    val poemLastReadId = getLong(context, PREF_POEM_LAST_READ_ID, 1)

    suspend fun setPoemLastReadId(id: Long) = setLong(context, PREF_POEM_LAST_READ_ID, id)

    val tagSynced = getBoolean(context, PREF_TAG_SYNCED)

    suspend fun setTagSynced(synced: Boolean) = setBoolean(context, PREF_TAG_SYNCED, synced)

    val tagCount = getLong(context, PREF_TAG_COUNT)

    suspend fun setTagCount(count: Long) = setLong(context, PREF_TAG_COUNT, count)

    val writerSynced = getBoolean(context, PREF_WRITER_SYNCED)

    suspend fun setWriterSynced(synced: Boolean) = setBoolean(context, PREF_WRITER_SYNCED, synced)

    val writerCount = getLong(context, PREF_WRITER_COUNT)

    suspend fun setWriterCount(count: Long) = setLong(context, PREF_WRITER_COUNT, count)

    val poemTagSynced = getBoolean(context, PREF_POEM_TAG_SYNCED)

    suspend fun setPoemTagSynced(synced: Boolean) =
        setBoolean(context, PREF_POEM_TAG_SYNCED, synced)

    val poemTagCount = getLong(context, PREF_POEM_TAG_COUNT)

    suspend fun setPoemTagCount(count: Long) = setLong(context, PREF_POEM_TAG_COUNT, count)

    val poemSentenceSynced = getBoolean(context, PREF_POEM_SENTENCE_SYNCED)

    suspend fun setPoemSentenceSynced(synced: Boolean) = setBoolean(context, PREF_POEM_SENTENCE_SYNCED, synced)

    val poemSentenceCount = getLong(context, PREF_POEM_SENTENCE_COUNT)

    suspend fun setPoemSentenceCount(count: Long) = setLong(context, PREF_POEM_SENTENCE_COUNT, count)

    val poemSentenceLastReadId = getLong(context, PREF_POEM_SENTENCE_LAST_READ_ID)

    suspend fun setPoemSentenceLastReadId(id: Long) = setLong(context, PREF_POEM_SENTENCE_LAST_READ_ID, id)



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
        private val PREF_POEM_SENTENCE_LAST_READ_ID = longPreferencesKey("key_poem_sentence_last_read_id")
        private val PREF_CHINESE_WISECRACK_SYNCED = booleanPreferencesKey("key_chinese_wisecrack_synced")
        private val PREF_CHINESE_WISECRACK_COUNT = longPreferencesKey("pref_chinese_wisecrack_count")
        private val PREF_CHINESE_WISECRACK_LAST_READ_ID = longPreferencesKey("key_chinese_wisecrack_last_read_id")
    }
}