package com.hefengbao.jingmo.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.hefengbao.jingmo.common.Constant
import com.hefengbao.jingmo.data.model.HomeItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.home: DataStore<Preferences> by preferencesDataStore(name = Constant.DATASTORE_HOME)

private fun getBoolean(
    context: Context,
    key: Preferences.Key<Boolean>,
    default: Boolean = true
): Flow<Boolean> =
    context.home.data.map { it[key] ?: default }

private suspend fun setBoolean(context: Context, key: Preferences.Key<Boolean>, value: Boolean) {
    context.home.edit { it[key] = value }
}

class HomePreference(
    private val context: Context
) {
    val homeItem: Flow<HomeItem> = context.home.data.map {
        HomeItem(
            classicPoem = it[PREF_CLASSIC_POEM] ?: true,
            writing = it[PREF_WRITING] ?: true,
            poemSentence = it[PREF_POEM_SENTENCE] ?: true,
            idiom = it[PREF_IDIOM] ?: true,
            chineseWisecrack = it[PREF_CHINESE_WISECRACK] ?: true,
            tongueTwister = it[PREF_TONGUE_TWISTER] ?: true,
            festival = it[PREF_FESTIVAL] ?: true,
            solarTerm = it[PREF_SOLAR_TERM] ?: true,
            chineseKnowledge = it[PREF_CHINESE_KNOWLEDGE] ?: true,
            people = it[PREF_PEOPLE] ?: true,
            chineseColor = it[PREF_CHINESE_COLOR] ?: true,
            character = it[PREF_CHARACTER] ?: true,
        )
    }

    suspend fun setClassicPoem(checked: Boolean) = setBoolean(context, PREF_CLASSIC_POEM, checked)

    suspend fun setWriting(checked: Boolean) = setBoolean(context, PREF_WRITING, checked)

    suspend fun setPoemSentence(checked: Boolean) = setBoolean(context, PREF_POEM_SENTENCE, checked)

    suspend fun setIdiom(checked: Boolean) = setBoolean(context, PREF_IDIOM, checked)

    suspend fun setChineseWisecrack(checked: Boolean) =
        setBoolean(context, PREF_CHINESE_WISECRACK, checked)

    suspend fun setTongueTwister(checked: Boolean) =
        setBoolean(context, PREF_TONGUE_TWISTER, checked)

    suspend fun setFestival(checked: Boolean) = setBoolean(context, PREF_FESTIVAL, checked)

    suspend fun setSolarTerm(checked: Boolean) = setBoolean(context, PREF_SOLAR_TERM, checked)

    suspend fun setChineseKnowledge(checked: Boolean) =
        setBoolean(context, PREF_CHINESE_KNOWLEDGE, checked)

    suspend fun setPeople(checked: Boolean) = setBoolean(context, PREF_PEOPLE, checked)

    suspend fun setChineseColor(checked: Boolean) = setBoolean(context, PREF_CHINESE_COLOR, checked)

    suspend fun setCharacter(checked: Boolean) = setBoolean(context, PREF_CHARACTER, checked)

    companion object {
        private val PREF_CLASSIC_POEM = booleanPreferencesKey("key_classic_poem")
        private val PREF_WRITING = booleanPreferencesKey("key_writing")
        private val PREF_POEM_SENTENCE = booleanPreferencesKey("key_poem_sentence")
        private val PREF_IDIOM = booleanPreferencesKey("key_idiom")
        private val PREF_CHINESE_WISECRACK = booleanPreferencesKey("key_chinese_wisecrack")
        private val PREF_TONGUE_TWISTER = booleanPreferencesKey("key_tongue_twister")
        private val PREF_FESTIVAL = booleanPreferencesKey("key_festival")
        private val PREF_SOLAR_TERM = booleanPreferencesKey("key_solar_term")
        private val PREF_CHINESE_KNOWLEDGE = booleanPreferencesKey("key_chinese_knowledge")
        private val PREF_PEOPLE = booleanPreferencesKey("key_chinese_knowledge")
        private val PREF_CHINESE_COLOR = booleanPreferencesKey("key_chinese_color")
        private val PREF_CHARACTER = booleanPreferencesKey("key_character")
    }
}