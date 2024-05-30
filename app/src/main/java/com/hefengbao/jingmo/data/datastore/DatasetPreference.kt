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
            chineseExpressionVersion = it[PREF_CHINESE_EXPRESSION] ?: 0,
            chineseKnowledgeVersion = it[PREF_CHINESE_KNOWLEDGE] ?: 0,
            chineseWisecracksVersion = it[PREF_CHINESE_WISECRACKS] ?: 0,
            classicPoemsVersion = it[PREF_CLASSIC_POEMS] ?: 0,
            dictionaryVersion = it[PREF_DICTIONARY] ?: 0,
            idiomsVersion = it[PREF_IDIOMS] ?: 0,
            peopleVersion = it[PREF_PEOPLE] ?: 0,
            poemSentencesVersion = it[PREF_POEM_SENTENCES] ?: 0,
            riddlesVersion = it[PREF_RIDDLES] ?: 0,
            tongueTwistersVersion = it[PREF_TONGUE_TWISTERS] ?: 0,
            writingsVersion = it[PREF_WRITINGS] ?: 0,
            writingsCurrentPage = it[PREF_WRITINGS_CURRENT_PAGE] ?: 1,
            writingsCurrentCount = it[PREF_WRITINGS_CURRENT_COUNT] ?: 0
        )
    }

    suspend fun setChineseExpressionVersion(version: Int) =
        setInt(context, PREF_CHINESE_EXPRESSION, version)

    suspend fun setChineseKnowledgeVersion(version: Int) =
        setInt(context, PREF_CHINESE_KNOWLEDGE, version)

    suspend fun setChineseWisecracksVersion(version: Int) =
        setInt(context, PREF_CHINESE_WISECRACKS, version)

    suspend fun setClassicPoemsVersion(version: Int) =
        setInt(context, PREF_CLASSIC_POEMS, version)

    suspend fun setDictionaryVersion(version: Int) = setInt(context, PREF_DICTIONARY, version)

    suspend fun setIdiomsVersion(version: Int) =
        setInt(context, PREF_IDIOMS, version)

    suspend fun setPeopleVersion(version: Int) =
        setInt(context, PREF_PEOPLE, version)

    suspend fun setPoemSentencesVersion(version: Int) =
        setInt(context, PREF_POEM_SENTENCES, version)

    suspend fun setRiddlesVersion(version: Int) =
        setInt(context, PREF_RIDDLES, version)

    suspend fun setTongueTwistersVersion(version: Int) =
        setInt(context, PREF_TONGUE_TWISTERS, version)

    suspend fun setWritingsVersion(version: Int) =
        setInt(context, PREF_WRITINGS, version)

    suspend fun setWritingsCurrentPage(page: Int) =
        setInt(context, PREF_WRITINGS_CURRENT_PAGE, page)

    suspend fun setWritingsCurrentCount(count: Int) =
        setInt(context, PREF_WRITINGS_CURRENT_COUNT, count)

    companion object {
        private val PREF_CHINESE_EXPRESSION = intPreferencesKey("key_chinese_expression")
        private val PREF_CHINESE_KNOWLEDGE = intPreferencesKey("key_chinese_knowledge")
        private val PREF_CHINESE_WISECRACKS = intPreferencesKey("key_chinese_wisecracks")
        private val PREF_CLASSIC_POEMS = intPreferencesKey("key_classic_poems")
        private val PREF_DICTIONARY = intPreferencesKey("key_dictionary")
        private val PREF_IDIOMS = intPreferencesKey("key_idioms")
        private val PREF_PEOPLE = intPreferencesKey("key_people")
        private val PREF_POEM_SENTENCES = intPreferencesKey("key_poem_sentences")
        private val PREF_RIDDLES = intPreferencesKey("key_riddles")
        private val PREF_TONGUE_TWISTERS = intPreferencesKey("key_tongue_twisters")
        private val PREF_WRITINGS = intPreferencesKey("key_writings")

        // 用于实现诗文同步 续传
        private val PREF_WRITINGS_CURRENT_PAGE = intPreferencesKey("key_writings_current_page")
        private val PREF_WRITINGS_CURRENT_COUNT = intPreferencesKey("key_writings_current_count")
    }
}