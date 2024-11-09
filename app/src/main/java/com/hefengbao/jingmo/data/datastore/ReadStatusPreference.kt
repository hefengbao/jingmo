/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

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
            chineseAntitheticalCoupletLastReadId = it[PREF_CHINESE_ANTITHETICAL_COUPLET] ?: 1,
            chineseKnowledgeLastReadId = it[PREF_CHINESE_KNOWLEDGE] ?: 1,
            chineseLyricLastReadId = it[PREF_CHINESE_LYRIC] ?: 1,
            chineseModernPoetryLastReadId = it[PREF_CHINESE_MODERN_POETRY] ?: 1,
            chineseProverbLastReadId = it[PREF_CHINESE_PROVERB] ?: 1,
            chineseQuoteLastReadId = it[PREF_CHINESE_QUOTE] ?: 1,
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

    suspend fun setChineseAntitheticalCoupletLastReadId(id: Int) =
        setInt(context, PREF_CHINESE_ANTITHETICAL_COUPLET, id)

    suspend fun setChineseKnowledgeLastReadId(id: Int) =
        setInt(context, PREF_CHINESE_KNOWLEDGE, id)

    suspend fun setChineseLyricLastReadId(id: Int) =
        setInt(context, PREF_CHINESE_LYRIC, id)

    suspend fun setChineseModernPoetryLastReadId(id: Int) =
        setInt(context, PREF_CHINESE_MODERN_POETRY, id)

    suspend fun setChineseProverbLastReadId(id: Int) = setInt(context, PREF_CHINESE_PROVERB, id)

    suspend fun setChineseQuoteLastReadId(id: Int) = setInt(context, PREF_CHINESE_QUOTE, id)

    suspend fun setChineseWisecracksLastReadId(id: Int) =
        setInt(context, PREF_CHINESE_WISECRACKS, id)

    suspend fun setClassicPoemsLastReadId(id: Int) =
        setInt(context, PREF_CLASSIC_POEMS, id)

    suspend fun setIdiomsLastReadId(id: Int) = setInt(context, PREF_IDIOMS, id)

    suspend fun setPeopleLastReadId(id: Int) = setInt(context, PREF_PEOPLE, id)

    suspend fun setPoemSentencesLastReadId(id: Int) =
        setInt(context, PREF_POEM_SENTENCES, id)

    suspend fun setRiddlesLastReadId(id: Int) = setInt(context, PREF_RIDDLES, id)

    suspend fun setTongueTwistersLastReadId(id: Int) =
        setInt(context, PREF_TONGUE_TWISTERS, id)

    suspend fun setWritingsLastReadId(id: Int) = setInt(context, PREF_WRITINGS, id)

    companion object {
        private val PREF_CHINESE_ANTITHETICAL_COUPLET =
            intPreferencesKey("key_chinese_antithetical_couplet")
        private val PREF_CHINESE_KNOWLEDGE = intPreferencesKey("key_chinese_knowledge")
        private val PREF_CHINESE_MODERN_POETRY = intPreferencesKey("key_chinese_modern_poetry")
        private val PREF_CHINESE_LYRIC = intPreferencesKey("key_chinese_lyric")
        private val PREF_CHINESE_PROVERB = intPreferencesKey("key_chinese_proverb")
        private val PREF_CHINESE_QUOTE = intPreferencesKey("key_chinese_quote")
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