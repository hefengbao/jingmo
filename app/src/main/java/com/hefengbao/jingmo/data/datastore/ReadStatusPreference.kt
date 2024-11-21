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
            chineseIdiomsLastReadId = it[PREF_CHINESE_IDIOMS] ?: 1,
            chineseKnowledgeLastReadId = it[PREF_CHINESE_KNOWLEDGE] ?: 1,
            chineseLyricLastReadId = it[PREF_CHINESE_LYRIC] ?: 1,
            chineseModernPoetryLastReadId = it[PREF_CHINESE_MODERN_POETRY] ?: 1,
            chineseProverbLastReadId = it[PREF_CHINESE_PROVERB] ?: 1,
            chineseQuoteLastReadId = it[PREF_CHINESE_QUOTE] ?: 1,
            chineseRiddlesLastReadId = it[PREF_CHINESE_RIDDLES] ?: 1,
            chineseTongueTwistersLastReadId = it[PREF_CHINESE_TONGUE_TWISTERS] ?: 1,
            chineseWisecracksLastReadId = it[PREF_CHINESE_WISECRACKS] ?: 1,
            classicLiteratureClassicPoemsLastReadId = it[PREF_CLASSICAL_LITERATURE_CLASSIC_POEMS]
                ?: 1,
            classicLiteraturePeopleLastReadId = it[PREF_CLASSICAL_LITERATURE_PEOPLE] ?: 1,
            classicLiteratureSentencesLastReadId = it[PREF_CLASSICAL_LITERATURE_SENTENCES] ?: 1,
            classicLiteratureWritingsLastReadId = it[PREF_CLASSICAL_LITERATURE_WRITINGS] ?: 1,
        )
    }

    suspend fun setChineseAntitheticalCoupletLastReadId(id: Int) =
        setInt(context, PREF_CHINESE_ANTITHETICAL_COUPLET, id)

    suspend fun setChineseIdiomsLastReadId(id: Int) = setInt(context, PREF_CHINESE_IDIOMS, id)

    suspend fun setChineseKnowledgeLastReadId(id: Int) =
        setInt(context, PREF_CHINESE_KNOWLEDGE, id)

    suspend fun setChineseLyricLastReadId(id: Int) =
        setInt(context, PREF_CHINESE_LYRIC, id)

    suspend fun setChineseModernPoetryLastReadId(id: Int) =
        setInt(context, PREF_CHINESE_MODERN_POETRY, id)

    suspend fun setChineseProverbLastReadId(id: Int) = setInt(context, PREF_CHINESE_PROVERB, id)

    suspend fun setChineseQuoteLastReadId(id: Int) = setInt(context, PREF_CHINESE_QUOTE, id)

    suspend fun setChineseRiddlesLastReadId(id: Int) = setInt(context, PREF_CHINESE_RIDDLES, id)

    suspend fun setChineseTongueTwistersLastReadId(id: Int) =
        setInt(context, PREF_CHINESE_TONGUE_TWISTERS, id)

    suspend fun setChineseWisecracksLastReadId(id: Int) =
        setInt(context, PREF_CHINESE_WISECRACKS, id)


    suspend fun setClassicLiteratureClassicPoemsLastReadId(id: Int) =
        setInt(context, PREF_CLASSICAL_LITERATURE_CLASSIC_POEMS, id)


    suspend fun setClassicLiteraturePeopleLastReadId(id: Int) =
        setInt(context, PREF_CLASSICAL_LITERATURE_PEOPLE, id)

    suspend fun setClassicLiteratureSentencesLastReadId(id: Int) =
        setInt(context, PREF_CLASSICAL_LITERATURE_SENTENCES, id)

    suspend fun setClassicLiteratureWritingsLastReadId(id: Int) =
        setInt(context, PREF_CLASSICAL_LITERATURE_WRITINGS, id)

    companion object {
        private val PREF_CHINESE_ANTITHETICAL_COUPLET =
            intPreferencesKey("key_chinese_antithetical_couplet")
        private val PREF_CHINESE_IDIOMS = intPreferencesKey("key_idioms")
        private val PREF_CHINESE_KNOWLEDGE = intPreferencesKey("key_chinese_knowledge")
        private val PREF_CHINESE_LYRIC = intPreferencesKey("key_chinese_lyric")
        private val PREF_CHINESE_MODERN_POETRY = intPreferencesKey("key_chinese_modern_poetry")
        private val PREF_CHINESE_PROVERB = intPreferencesKey("key_chinese_proverb")
        private val PREF_CHINESE_QUOTE = intPreferencesKey("key_chinese_quote")
        private val PREF_CHINESE_RIDDLES = intPreferencesKey("key_riddles")
        private val PREF_CHINESE_TONGUE_TWISTERS = intPreferencesKey("key_tongue_twisters")
        private val PREF_CHINESE_WISECRACKS = intPreferencesKey("key_chinese_wisecracks")

        private val PREF_CLASSICAL_LITERATURE_CLASSIC_POEMS = intPreferencesKey("key_classic_poems")
        private val PREF_CLASSICAL_LITERATURE_PEOPLE = intPreferencesKey("key_people")
        private val PREF_CLASSICAL_LITERATURE_SENTENCES = intPreferencesKey("key_poem_sentences")
        private val PREF_CLASSICAL_LITERATURE_WRITINGS = intPreferencesKey("key_writings")
    }
}