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
            chinaWorldCulturalHeritage = it[PREF_CHINA_WORLD_CULTURE_HERITAGE] ?: true,
            classicalLiteratureClassicPoem = it[PREF_CLASSICAL_LITERATURE_CLASSIC_POEM] ?: true,
            classicalLiteraturePeople = it[PREF_CLASSICAL_LITERATURE_PEOPLE] ?: true,
            classicalLiteratureSentence = it[PREF_CLASSICAL_LITERATURE_SENTENCE] ?: true,
            classicalLiteratureWriting = it[PREF_CLASSICAL_LITERATURE_WRITING] ?: true,
            chineseCharacter = it[PREF_CHINESE_CHARACTER] ?: true,
            chineseExpression = it[PREF_CHINESE_EXPRESSION] ?: true,
            chineseAntitheticalCouplet = it[PREF_CHINESE_ANTITHETICAL_COUPLET] ?: true,
            chineseIdiom = it[PREF_CHINESE_IDIOM] ?: true,
            chineseKnowledge = it[PREF_CHINESE_KNOWLEDGE] ?: true,
            chineseLyric = it[PREF_CHINESE_LYRIC] ?: true,
            chineseProverb = it[PREF_CHINESE_PROVERB] ?: true,
            chineseRiddle = it[PREF_CHINESE_RIDDLE] ?: true,
            chineseTongueTwister = it[PREF_CHINESE_TONGUE_TWISTER] ?: true,
            chineseWisecrack = it[PREF_CHINESE_WISECRACK] ?: true,
            traditionalCultureColor = it[PREF_TRADITIONAL_CULTURE_COLOR] ?: true,
            traditionalCultureFestival = it[PREF_TRADITIONAL_CULTURE_FESTIVAL] ?: true,
            traditionalCultureSolarTerm = it[PREF_TRADITIONAL_CULTURE_SOLAR_TERM] ?: true,
        )
    }

    suspend fun setChinaWorldCultureHeritage(checked: Boolean) =
        setBoolean(context, PREF_CHINA_WORLD_CULTURE_HERITAGE, checked)

    suspend fun setClassicalLiteratureClassicPoem(checked: Boolean) =
        setBoolean(context, PREF_CLASSICAL_LITERATURE_CLASSIC_POEM, checked)

    suspend fun setClassicalLiteraturePeople(checked: Boolean) =
        setBoolean(context, PREF_CLASSICAL_LITERATURE_PEOPLE, checked)

    suspend fun setClassicalLiteratureSentence(checked: Boolean) =
        setBoolean(context, PREF_CLASSICAL_LITERATURE_SENTENCE, checked)

    suspend fun setClassicalLiteratureWriting(checked: Boolean) =
        setBoolean(context, PREF_CLASSICAL_LITERATURE_WRITING, checked)


    suspend fun setTraditionalCultureColor(checked: Boolean) =
        setBoolean(context, PREF_TRADITIONAL_CULTURE_COLOR, checked)

    suspend fun setTraditionalCultureFestival(checked: Boolean) =
        setBoolean(context, PREF_TRADITIONAL_CULTURE_FESTIVAL, checked)

    suspend fun setTraditionalCultureSolarTerm(checked: Boolean) =
        setBoolean(context, PREF_TRADITIONAL_CULTURE_SOLAR_TERM, checked)

    suspend fun setChineseAntitheticalCouplet(checked: Boolean) =
        setBoolean(context, PREF_CHINESE_ANTITHETICAL_COUPLET, checked)

    suspend fun setChineseCharacter(checked: Boolean) =
        setBoolean(context, PREF_CHINESE_CHARACTER, checked)

    suspend fun setChineseExpression(checked: Boolean) =
        setBoolean(context, PREF_CHINESE_EXPRESSION, checked)

    suspend fun setChineseIdiom(checked: Boolean) =
        setBoolean(context, PREF_CHINESE_IDIOM, checked)

    suspend fun setChineseKnowledge(checked: Boolean) =
        setBoolean(context, PREF_CHINESE_KNOWLEDGE, checked)

    suspend fun setChineseLyric(checked: Boolean) =
        setBoolean(context, PREF_CHINESE_LYRIC, checked)

    suspend fun setChineseProverb(checked: Boolean) =
        setBoolean(context, PREF_CHINESE_PROVERB, checked)

    suspend fun setChineseRiddle(checked: Boolean) =
        setBoolean(context, PREF_CHINESE_RIDDLE, checked)

    suspend fun setChineseTongueTwister(checked: Boolean) =
        setBoolean(context, PREF_CHINESE_TONGUE_TWISTER, checked)

    suspend fun setChineseWisecrack(checked: Boolean) =
        setBoolean(context, PREF_CHINESE_WISECRACK, checked)

    companion object {
        //古诗文 classical_literature CLASSICAL_LITERATURE
        private val PREF_CLASSICAL_LITERATURE_CLASSIC_POEM =
            booleanPreferencesKey("key_classical_literature_classic_poem")
        private val PREF_CLASSICAL_LITERATURE_PEOPLE =
            booleanPreferencesKey("key_classical_literature_people")
        private val PREF_CLASSICAL_LITERATURE_SENTENCE =
            booleanPreferencesKey("key_classical_literature_sentence")
        private val PREF_CLASSICAL_LITERATURE_WRITING =
            booleanPreferencesKey("key_classical_literature_writing")

        //传统文化 traditional_culture TRADITIONAL_CULTURE
        private val PREF_TRADITIONAL_CULTURE_COLOR =
            booleanPreferencesKey("key_traditional_culture_color")
        private val PREF_TRADITIONAL_CULTURE_FESTIVAL =
            booleanPreferencesKey("key_traditional_culture_festival")
        private val PREF_TRADITIONAL_CULTURE_SOLAR_TERM =
            booleanPreferencesKey("key_traditional_culture_solar_term")

        // 中国
        private val PREF_CHINA_WORLD_CULTURE_HERITAGE =
            booleanPreferencesKey("key_china_world_culture_heritage")

        // 汉语
        private val PREF_CHINESE_ANTITHETICAL_COUPLET =
            booleanPreferencesKey("key_chinese_antithetical_couplet")
        private val PREF_CHINESE_CHARACTER = booleanPreferencesKey("key_chinese_character")
        private val PREF_CHINESE_EXPRESSION = booleanPreferencesKey("key_chinese_expression")
        private val PREF_CHINESE_IDIOM = booleanPreferencesKey("key_chinese_idiom")
        private val PREF_CHINESE_KNOWLEDGE = booleanPreferencesKey("key_chinese_knowledge")
        private val PREF_CHINESE_LYRIC = booleanPreferencesKey("key_chinese_lyric")
        private val PREF_CHINESE_PROVERB = booleanPreferencesKey("key_chinese_proverb")
        private val PREF_CHINESE_RIDDLE = booleanPreferencesKey("key_chinese_riddle")
        private val PREF_CHINESE_TONGUE_TWISTER =
            booleanPreferencesKey("key_chinese_tongue_twister")
        private val PREF_CHINESE_WISECRACK = booleanPreferencesKey("key_chinese_wisecrack")
    }
}