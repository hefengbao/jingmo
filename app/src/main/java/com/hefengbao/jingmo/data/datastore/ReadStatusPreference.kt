package com.hefengbao.jingmo.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.hefengbao.jingmo.common.Constant
import com.hefengbao.jingmo.data.model.ReadStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.readStatus: DataStore<Preferences> by preferencesDataStore(name = Constant.DATASTORE_READ_STATUS_NAME)

private suspend fun setInt(context: Context, key: Preferences.Key<Int>, value: Int) {
    context.readStatus.edit { it[key] = value }
}

class ReadStatusPreference(
    private val context: Context
) {
    val readStatus: Flow<ReadStatus> = context.readStatus.data.map {
        ReadStatus(
            chineseKnowledgeLastReadId = it[PREF_CHINESE_KNOWLEDGE] ?: 1,
            chineseWisecracksLastReadId = it[PREF_CHINESE_WISECRACKS] ?: 1,
            classicPoemsLastReadId = it[PREF_CLASSIC_POEMS] ?: 1,
            idiomsLastReadId = it[PREF_IDIOMS] ?: 1,
            peopleLastReadId = it[PREF_PEOPLE] ?: 1,
            poemSentencesLastReadId = it[PREF_POEM_SENTENCES] ?: 1,
            riddlesLastReadId = it[PREF_RIDDLES] ?: 1,
            tongueTwistersLastReadId = it[PREF_TONGUE_TWISTERS] ?: 1,
            writingsLastReadId = it[PREF_WRITINGS] ?: 1,
        )
    }

    suspend fun setChineseKnowledgeLastReadId(version: Int) =
        setInt(context, PREF_CHINESE_KNOWLEDGE, version)

    suspend fun setChineseWisecracksLastReadId(version: Int) =
        setInt(context, PREF_CHINESE_WISECRACKS, version)

    suspend fun setClassicPoemsLastReadId(version: Int) =
        setInt(context, PREF_CLASSIC_POEMS, version)

    suspend fun setIdiomsLastReadId(version: Int) = setInt(context, PREF_IDIOMS, version)

    suspend fun setPeopleLastReadId(version: Int) = setInt(context, PREF_PEOPLE, version)

    suspend fun setPoemSentencesLastReadId(version: Int) =
        setInt(context, PREF_POEM_SENTENCES, version)

    suspend fun setRiddlesLastReadId(version: Int) = setInt(context, PREF_RIDDLES, version)

    suspend fun setTongueTwistersLastReadId(version: Int) =
        setInt(context, PREF_TONGUE_TWISTERS, version)

    suspend fun setWritingsLastReadId(version: Int) = setInt(context, PREF_WRITINGS, version)

    companion object {
        private val PREF_CHINESE_KNOWLEDGE = intPreferencesKey("key_chinese_knowledge")
        private val PREF_CHINESE_WISECRACKS = intPreferencesKey("key_chinese_wisecracks")
        private val PREF_CLASSIC_POEMS = intPreferencesKey("key_classic_poems")
        private val PREF_IDIOMS = intPreferencesKey("key_idioms")
        private val PREF_PEOPLE = intPreferencesKey("key_people")
        private val PREF_POEM_SENTENCES = intPreferencesKey("key_poem_sentences")
        private val PREF_RIDDLES = intPreferencesKey("key_riddles")
        private val PREF_TONGUE_TWISTERS = intPreferencesKey("key_tongue_twisters")
        private val PREF_WRITINGS = intPreferencesKey("key_writings")
    }
}