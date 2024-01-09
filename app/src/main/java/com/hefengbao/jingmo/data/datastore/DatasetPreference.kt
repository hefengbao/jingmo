package com.hefengbao.jingmo.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.hefengbao.jingmo.common.Constant
import com.hefengbao.jingmo.data.model.DatasetVersion
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataset: DataStore<Preferences> by preferencesDataStore(name = Constant.DATASTORE_DATASET_NAME)

private suspend fun setInt(context: Context, key: Preferences.Key<Int>, value: Int) {
    context.dataset.edit { it[key] = value }
}

class DatasetPreference(
    private val context: Context
) {
    val datasetVersion: Flow<DatasetVersion> = context.dataset.data.map {
        DatasetVersion(
            chineseKnowledgeVersion = it[PREF_CHINESE_KNOWLEDGE_VERSION] ?: 0,
            chineseWisecracksVersion = it[PREF_CHINESE_WISECRACKS_VERSION] ?: 0,
            idiomsVersion = it[PREF_IDIOMS_VERSION] ?: 0,
            peopleVersion = it[PREF_PEOPLE_VERSION] ?: 0,
            poemSentencesVersion = it[PREF_POEM_SENTENCES_VERSION] ?: 0,
            riddlesVersion = it[PREF_RIDDLES_VERSION] ?: 0,
            tongueTwistersVersion = it[PREF_TONGUE_TWISTERS_VERSION] ?: 0,
            writingsVersion = it[PREF_WRITINGS_VERSION] ?: 0
        )
    }

    suspend fun setChineseKnowledgeVersion(version: Int) = setInt(context, PREF_CHINESE_KNOWLEDGE_VERSION, version)
    suspend fun setChineseWisecracksVersion(version: Int) = setInt(context, PREF_CHINESE_WISECRACKS_VERSION, version)
    suspend fun setIdiomsVersion(version: Int) = setInt(context, PREF_IDIOMS_VERSION, version)
    suspend fun setPeopleVersion(version: Int) = setInt(context, PREF_PEOPLE_VERSION, version)
    suspend fun setPoemSentencesVersion(version: Int) = setInt(context, PREF_POEM_SENTENCES_VERSION, version)
    suspend fun setRiddlesVersion(version: Int) = setInt(context, PREF_RIDDLES_VERSION, version)
    suspend fun setTongueTwistersVersion(version: Int) = setInt(context, PREF_TONGUE_TWISTERS_VERSION, version)
    suspend fun setWritingsVersion(version: Int) = setInt(context, PREF_WRITINGS_VERSION, version)

    companion object {
        private val PREF_CHINESE_KNOWLEDGE_VERSION = intPreferencesKey("key_chinese_knowledge_version")
        private val PREF_CHINESE_WISECRACKS_VERSION = intPreferencesKey("key_chinese_wisecracks_version")
        private val PREF_IDIOMS_VERSION = intPreferencesKey("key_idioms_version")
        private val PREF_PEOPLE_VERSION = intPreferencesKey("key_people_version")
        private val PREF_POEM_SENTENCES_VERSION = intPreferencesKey("key_poem_sentences_version")
        private val PREF_RIDDLES_VERSION = intPreferencesKey("key_riddles_version")
        private val PREF_TONGUE_TWISTERS_VERSION = intPreferencesKey("key_tongue_twisters_version")
        private val PREF_WRITINGS_VERSION = intPreferencesKey("key_writings_version")
    }
}