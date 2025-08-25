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
import com.hefengbao.jingmo.data.enums.Category
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
            chineseIdiomLastReadId = it[PREF_CHINESE_IDIOM] ?: 1,
            chineseKnowledgeLastReadId = it[PREF_CHINESE_KNOWLEDGE] ?: 1,
            chineseLyricLastReadId = it[PREF_CHINESE_LYRIC] ?: 1,
            chineseModernPoetryLastReadId = it[PREF_CHINESE_MODERN_POETRY] ?: 1,
            chineseProverbLastReadId = it[PREF_CHINESE_PROVERB] ?: 1,
            chineseQuoteLastReadId = it[PREF_CHINESE_QUOTE] ?: 1,
            chineseRiddleLastReadId = it[PREF_CHINESE_RIDDLE] ?: 1,
            chineseTongueTwisterLastReadId = it[PREF_CHINESE_TONGUE_TWISTER] ?: 1,
            chineseWisecrackLastReadId = it[PREF_CHINESE_WISECRACK] ?: 1,
            classicLiteratureClassicPoemLastReadId = it[PREF_CLASSICAL_LITERATURE_CLASSIC_POEM]
                ?: 1,
            classicLiteraturePeopleLastReadId = it[PREF_CLASSICAL_LITERATURE_PEOPLE] ?: 1,
            classicLiteratureSentenceLastReadId = it[PREF_CLASSICAL_LITERATURE_SENTENCE] ?: 1,
            classicLiteratureWritingsLastReadId = it[PREF_CLASSICAL_LITERATURE_WRITING] ?: 1,
        )
    }

    suspend fun setChineseAntitheticalCoupletLastReadId(id: Int) =
        setInt(context, PREF_CHINESE_ANTITHETICAL_COUPLET, id)

    suspend fun setChineseIdiomsLastReadId(id: Int) = setInt(context, PREF_CHINESE_IDIOM, id)

    suspend fun setChineseKnowledgeLastReadId(id: Int) =
        setInt(context, PREF_CHINESE_KNOWLEDGE, id)

    suspend fun setChineseLyricLastReadId(id: Int) =
        setInt(context, PREF_CHINESE_LYRIC, id)

    suspend fun setChineseModernPoetryLastReadId(id: Int) =
        setInt(context, PREF_CHINESE_MODERN_POETRY, id)

    suspend fun setChineseProverbLastReadId(id: Int) = setInt(context, PREF_CHINESE_PROVERB, id)

    suspend fun setChineseQuoteLastReadId(id: Int) = setInt(context, PREF_CHINESE_QUOTE, id)

    suspend fun setChineseRiddlesLastReadId(id: Int) = setInt(context, PREF_CHINESE_RIDDLE, id)

    suspend fun setChineseTongueTwistersLastReadId(id: Int) =
        setInt(context, PREF_CHINESE_TONGUE_TWISTER, id)

    suspend fun setChineseWisecracksLastReadId(id: Int) =
        setInt(context, PREF_CHINESE_WISECRACK, id)


    suspend fun setClassicLiteratureClassicPoemsLastReadId(id: Int) =
        setInt(context, PREF_CLASSICAL_LITERATURE_CLASSIC_POEM, id)


    suspend fun setClassicLiteraturePeopleLastReadId(id: Int) =
        setInt(context, PREF_CLASSICAL_LITERATURE_PEOPLE, id)

    suspend fun setClassicLiteratureSentencesLastReadId(id: Int) =
        setInt(context, PREF_CLASSICAL_LITERATURE_SENTENCE, id)

    suspend fun setClassicLiteratureWritingsLastReadId(id: Int) =
        setInt(context, PREF_CLASSICAL_LITERATURE_WRITING, id)

    companion object {
        private val PREF_CHINESE_ANTITHETICAL_COUPLET =
            intPreferencesKey(Category.ChineseAntitheticalCouplet.model)
        private val PREF_CHINESE_IDIOM = intPreferencesKey(Category.ChineseIdiom.model)
        private val PREF_CHINESE_KNOWLEDGE = intPreferencesKey(Category.ChineseKnowledge.model)
        private val PREF_CHINESE_LYRIC = intPreferencesKey(Category.ChineseLyric.model)
        private val PREF_CHINESE_MODERN_POETRY =
            intPreferencesKey(Category.ChineseModernPoetry.model)
        private val PREF_CHINESE_PROVERB = intPreferencesKey(Category.ChineseProverb.model)
        private val PREF_CHINESE_QUOTE = intPreferencesKey(Category.ChineseQuote.model)
        private val PREF_CHINESE_RIDDLE = intPreferencesKey(Category.ChineseRiddle.model)
        private val PREF_CHINESE_TONGUE_TWISTER =
            intPreferencesKey(Category.ChineseTongueTwister.model)
        private val PREF_CHINESE_WISECRACK = intPreferencesKey(Category.ChineseWisecrack.model)

        private val PREF_CLASSICAL_LITERATURE_CLASSIC_POEM =
            intPreferencesKey(Category.ClassicalLiteratureClassicPoem.model)
        private val PREF_CLASSICAL_LITERATURE_PEOPLE =
            intPreferencesKey(Category.ClassicalLiteraturePeople.model)
        private val PREF_CLASSICAL_LITERATURE_SENTENCE =
            intPreferencesKey(Category.ClassicalLiteratureSentence.model)
        private val PREF_CLASSICAL_LITERATURE_WRITING =
            intPreferencesKey(Category.ClassicalLiteratureWriting.model)
    }
}