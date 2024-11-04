/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.repository.settings

import com.hefengbao.jingmo.data.datastore.HomePreference
import com.hefengbao.jingmo.data.model.HomeItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeItemRepositoryImpl @Inject constructor(
    private val homePreference: HomePreference
) : HomeItemRepository {
    override suspend fun setChinaWorldCultureHeritage(checked: Boolean) =
        homePreference.setChinaWorldCultureHeritage(checked)

    override suspend fun setChineseAntitheticalCouplet(checked: Boolean) =
        homePreference.setChineseAntitheticalCouplet(checked)

    override suspend fun setChineseCharacter(checked: Boolean) =
        homePreference.setChineseCharacter(checked)

    override suspend fun setChineseExpression(checked: Boolean) =
        homePreference.setChineseExpression(checked)

    override suspend fun setChineseIdiom(checked: Boolean) =
        homePreference.setChineseIdiom(checked)

    override suspend fun setChineseKnowledge(checked: Boolean) =
        homePreference.setChineseKnowledge(checked)

    override suspend fun setChineseLyric(checked: Boolean) =
        homePreference.setChineseLyric(checked)

    override suspend fun setChineseProverb(checked: Boolean) =
        homePreference.setChineseProverb(checked)

    override suspend fun setChineseQuote(checked: Boolean) =
        homePreference.setChineseQuote(checked)

    override suspend fun setChineseRiddle(checked: Boolean) =
        homePreference.setChineseRiddle(checked)

    override suspend fun setChineseTongueTwister(checked: Boolean) =
        homePreference.setChineseTongueTwister(checked)

    override suspend fun setChineseWisecrack(checked: Boolean) =
        homePreference.setChineseWisecrack(checked)

    override suspend fun setClassicalLiteratureClassicPoem(checked: Boolean) =
        homePreference.setClassicalLiteratureClassicPoem(checked)

    override suspend fun setClassicalLiteraturePeople(checked: Boolean) =
        homePreference.setClassicalLiteraturePeople(checked)

    override suspend fun setClassicalLiteratureSentence(checked: Boolean) =
        homePreference.setClassicalLiteratureSentence(checked)

    override suspend fun setClassicalLiteratureWriting(checked: Boolean) =
        homePreference.setClassicalLiteratureWriting(checked)

    override suspend fun setTraditionalCultureColor(checked: Boolean) =
        homePreference.setTraditionalCultureColor(checked)

    override suspend fun setTraditionalCultureFestival(checked: Boolean) =
        homePreference.setTraditionalCultureFestival(checked)

    override suspend fun setTraditionalCultureSolarTerm(checked: Boolean) =
        homePreference.setTraditionalCultureSolarTerm(checked)

    override fun getHomeItem(): Flow<HomeItem> = homePreference.homeItem
}