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
            chinaWorldCulturalHeritageVersion = it[PREF_CHINA_WORLD_CULTURE_HERITAGE] ?: 0,
            chineseAntitheticalCoupletVersion = it[PREF_CHINESE_ANTITHETICAL_COUPLET] ?: 0,
            chineseCharacterVersion = it[PREF_CHINESE_CHARACTER] ?: 0,
            chineseExpressionVersion = it[PREF_CHINESE_EXPRESSION] ?: 0,
            chineseIdiomVersion = it[PREF_CHINESE_IDIOM] ?: 0,
            chineseKnowledgeVersion = it[PREF_CHINESE_KNOWLEDGE] ?: 0,
            chineseLyricVersion = it[PREF_CHINESE_LYRIC] ?: 0,
            chineseModernPoetryVersion = it[PREF_CHINESE_MODERN_POETRY] ?: 0,
            chineseProverbVersion = it[PREF_CHINESE_PROVERB] ?: 0,
            chineseQuoteVersion = it[PREF_CHINESE_QUOTE] ?: 0,
            chineseRiddleVersion = it[PREF_CHINESE_RIDDLE] ?: 0,
            chineseTongueTwisterVersion = it[PREF_CHINESE_TONGUE_TWISTER] ?: 0,
            chineseWisecrackVersion = it[PREF_CHINESE_WISECRACK] ?: 0,
            classicLiteratureClassicPoemVersion = it[PREF_CLASSICAL_LITERATURE_CLASSIC_POEM] ?: 0,
            classicLiteraturePeopleVersion = it[PREF_CLASSICAL_LITERATURE_PEOPLE] ?: 0,
            classicLiteratureSentenceVersion = it[PREF_CLASSICAL_LITERATURE_SENTENCE] ?: 0,
            classicLiteratureWritingVersion = it[PREF_CLASSICAL_LITERATURE_WRITING] ?: 0,
            classicLiteratureWritingCurrentPage = it[PREF_WRITING_CURRENT_PAGE] ?: 1,
            classicLiteratureWritingCurrentCount = it[PREF_WRITING_CURRENT_COUNT] ?: 0
        )
    }

    suspend fun setChinsWorldCultureHeritageVersion(version: Int) =
        setInt(context, PREF_CHINA_WORLD_CULTURE_HERITAGE, version)

    suspend fun setChineseAntitheticalCoupletVersion(version: Int) =
        setInt(context, PREF_CHINESE_ANTITHETICAL_COUPLET, version)

    suspend fun setChineseDictionaryVersion(version: Int) =
        setInt(context, PREF_CHINESE_CHARACTER, version)

    suspend fun setChineseExpressionVersion(version: Int) =
        setInt(context, PREF_CHINESE_EXPRESSION, version)

    suspend fun setChineseIdiomVersion(version: Int) =
        setInt(context, PREF_CHINESE_IDIOM, version)

    suspend fun setChineseKnowledgeVersion(version: Int) =
        setInt(context, PREF_CHINESE_KNOWLEDGE, version)

    suspend fun setChineseLyricVersion(version: Int) =
        setInt(context, PREF_CHINESE_LYRIC, version)

    suspend fun setChineseModernPoetryVersion(version: Int) =
        setInt(context, PREF_CHINESE_MODERN_POETRY, version)

    suspend fun setChineseProverbVersion(version: Int) =
        setInt(context, PREF_CHINESE_PROVERB, version)

    suspend fun setChineseQuoteVersion(version: Int) =
        setInt(context, PREF_CHINESE_QUOTE, version)

    suspend fun setChineseRiddleVersion(version: Int) =
        setInt(context, PREF_CHINESE_RIDDLE, version)

    suspend fun setChineseTongueTwisterVersion(version: Int) =
        setInt(context, PREF_CHINESE_TONGUE_TWISTER, version)

    suspend fun setChineseWisecrackVersion(version: Int) =
        setInt(context, PREF_CHINESE_WISECRACK, version)

    suspend fun setClassicalLiteratureClassicPoemVersion(version: Int) =
        setInt(context, PREF_CLASSICAL_LITERATURE_CLASSIC_POEM, version)

    suspend fun setClassicalLiteraturePeopleVersion(version: Int) =
        setInt(context, PREF_CLASSICAL_LITERATURE_PEOPLE, version)

    suspend fun setClassicalLiteratureSentenceVersion(version: Int) =
        setInt(context, PREF_CLASSICAL_LITERATURE_SENTENCE, version)

    suspend fun setClassicalLiteratureWritingVersion(version: Int) =
        setInt(context, PREF_CLASSICAL_LITERATURE_WRITING, version)

    suspend fun setClassicalLiteratureWritingCurrentPage(page: Int) =
        setInt(context, PREF_WRITING_CURRENT_PAGE, page)

    suspend fun setClassicalLiteratureWritingCurrentCount(count: Int) =
        setInt(context, PREF_WRITING_CURRENT_COUNT, count)

    companion object {
        private val PREF_CHINA_WORLD_CULTURE_HERITAGE =
            intPreferencesKey(Category.ChinaWorldCulturalHeritage.model)
        private val PREF_CHINESE_ANTITHETICAL_COUPLET =
            intPreferencesKey(Category.ChineseAntitheticalCouplet.model)
        private val PREF_CHINESE_CHARACTER = intPreferencesKey(Category.ChineseCharacter.model)
        private val PREF_CHINESE_EXPRESSION = intPreferencesKey(Category.ChineseExpression.model)
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

        // 用于实现诗文同步 续传
        private val PREF_WRITING_CURRENT_PAGE =
            intPreferencesKey("${Category.ClassicalLiteratureWriting.model}_current_page")
        private val PREF_WRITING_CURRENT_COUNT =
            intPreferencesKey("${Category.ClassicalLiteratureWriting.model}_current_count")
    }
}