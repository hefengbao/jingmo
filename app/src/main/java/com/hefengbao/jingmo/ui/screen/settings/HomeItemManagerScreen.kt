/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.R
import com.hefengbao.jingmo.data.model.HomeItem
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import com.hefengbao.jingmo.ui.screen.settings.components.SettingsTitle

@Composable
fun HomeItemManagerRoute(
    viewModel: HomeItemManagerViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val homeItem by viewModel.homeItem.collectAsState(initial = HomeItem())

    HomeItemManagerScreen(
        onBackClick = onBackClick,
        homeItem = homeItem,
        setChinaWorldCultureHeritage = viewModel::setChinaWorldCultureHeritage,
        setClassicalLiteratureClassicPoem = { viewModel.setClassicalLiteratureClassicPoem(it) },
        setClassicalLiteraturePeople = { viewModel.setClassicalLiteraturePeople(it) },
        setClassicalLiteratureSentence = { viewModel.setClassicalLiteratureSentence(it) },
        setClassicalLiteratureWriting = { viewModel.setClassicalLiteratureWriting(it) },
        setChineseAntitheticalCouplet = viewModel::setChineseAntitheticalCouplet,
        setChineseCharacter = { viewModel.setChineseCharacter(it) },
        setChineseExpression = { viewModel.setChineseExpression(it) },
        setChineseIdiom = { viewModel.setChineseIdiom(it) },
        setChineseKnowledge = { viewModel.setChineseKnowledge(it) },
        setChineseLyric = { viewModel.setChineseLyric(it) },
        setChineseModernPoetry = viewModel::setChineseModernPoetry,
        setChineseProverb = { viewModel.setChineseProverb(it) },
        setChineseQuote = viewModel::setChineseQuote,
        setChineseRiddle = { viewModel.setChineseRiddle(it) },
        setChineseTongueTwister = { viewModel.setChineseTongueTwister(it) },
        setChineseWisecrack = { viewModel.setChineseWisecrack(it) },
        setTraditionalCultureCalendar = viewModel::setTraditionalCultureCalendar,
        setTraditionalCultureColor = { viewModel.setTraditionalCultureColor(it) },
        setTraditionalCultureFestival = { viewModel.setTraditionalCultureFestival(it) },
        setTraditionalCultureSolarTerm = { viewModel.setTraditionalCultureSolarTerm(it) },
    )
}

@Composable
private fun HomeItemManagerScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    homeItem: HomeItem,
    setChinaWorldCultureHeritage: (Boolean) -> Unit,
    setClassicalLiteratureClassicPoem: (Boolean) -> Unit,
    setClassicalLiteraturePeople: (Boolean) -> Unit,
    setClassicalLiteratureSentence: (Boolean) -> Unit,
    setClassicalLiteratureWriting: (Boolean) -> Unit,
    setChineseAntitheticalCouplet: (Boolean) -> Unit,
    setChineseCharacter: (Boolean) -> Unit,
    setChineseExpression: (Boolean) -> Unit,
    setChineseIdiom: (Boolean) -> Unit,
    setChineseKnowledge: (Boolean) -> Unit,
    setChineseLyric: (Boolean) -> Unit,
    setChineseModernPoetry: (Boolean) -> Unit,
    setChineseProverb: (Boolean) -> Unit,
    setChineseQuote: (Boolean) -> Unit,
    setChineseRiddle: (Boolean) -> Unit,
    setChineseTongueTwister: (Boolean) -> Unit,
    setChineseWisecrack: (Boolean) -> Unit,
    setTraditionalCultureCalendar: (Boolean) -> Unit,
    setTraditionalCultureColor: (Boolean) -> Unit,
    setTraditionalCultureFestival: (Boolean) -> Unit,
    setTraditionalCultureSolarTerm: (Boolean) -> Unit,
) {
    SimpleScaffold(onBackClick = onBackClick, title = "栏目管理") {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            SettingsTitle(title = stringResource(R.string.classicalliterature))
            Item(
                title = stringResource(R.string.classicalliterature_classicpoem),
                checked = homeItem.classicalLiteratureClassicPoem,
                onCheckedChange = setClassicalLiteratureClassicPoem
            )
            Item(
                title = stringResource(R.string.classicalliterature_writing),
                checked = homeItem.classicalLiteratureWriting,
                onCheckedChange = setClassicalLiteratureWriting
            )
            Item(
                title = stringResource(R.string.classicalliterature_sentence),
                checked = homeItem.classicalLiteratureSentence,
                onCheckedChange = setClassicalLiteratureSentence
            )
            Item(
                title = stringResource(R.string.classicalliterature_people),
                checked = homeItem.classicalLiteraturePeople,
                onCheckedChange = setClassicalLiteraturePeople
            )
            SettingsTitle(title = stringResource(R.string.chinese))
            Item(
                title = stringResource(R.string.chinese_character),
                checked = homeItem.chineseCharacter,
                onCheckedChange = setChineseCharacter
            )
            Item(
                title = stringResource(R.string.chinese_expression),
                checked = homeItem.chineseExpression,
                onCheckedChange = setChineseExpression
            )
            Item(
                title = stringResource(R.string.chinese_idiom),
                checked = homeItem.chineseIdiom,
                onCheckedChange = setChineseIdiom
            )
            Item(
                title = stringResource(R.string.chinese_wisecrack),
                checked = homeItem.chineseWisecrack,
                onCheckedChange = setChineseWisecrack
            )
            Item(
                title = stringResource(R.string.chinese_proverb),
                checked = homeItem.chineseProverb,
                onCheckedChange = setChineseProverb
            )
            Item(
                title = stringResource(R.string.chinese_riddle),
                checked = homeItem.chineseRiddle,
                onCheckedChange = setChineseRiddle
            )
            Item(
                title = stringResource(R.string.chinese_tonguetwister),
                checked = homeItem.chineseTongueTwister,
                onCheckedChange = setChineseTongueTwister
            )
            Item(
                title = stringResource(R.string.chinese_antitheticalcouplet),
                checked = homeItem.chineseAntitheticalCouplet,
                onCheckedChange = setChineseAntitheticalCouplet
            )
            Item(
                title = stringResource(R.string.chinese_lyric),
                checked = homeItem.chineseLyric,
                onCheckedChange = setChineseLyric
            )
            Item(
                title = stringResource(R.string.chinese_knowledge),
                checked = homeItem.chineseKnowledge,
                onCheckedChange = setChineseKnowledge
            )
            Item(
                title = stringResource(R.string.chinese_quote),
                checked = homeItem.chineseQuote,
                onCheckedChange = setChineseQuote
            )
            Item(
                title = stringResource(R.string.chinese_modernpoetry),
                checked = homeItem.chineseModernPoetry,
                onCheckedChange = setChineseModernPoetry
            )
            SettingsTitle(title = stringResource(R.string.traditionalculture))
            Item(
                title = stringResource(R.string.traditionalculture_festival),
                checked = homeItem.traditionalCultureFestival,
                onCheckedChange = setTraditionalCultureFestival
            )
            Item(
                title = stringResource(R.string.traditionalculture_solarterm),
                checked = homeItem.traditionalCultureSolarTerm,
                onCheckedChange = setTraditionalCultureSolarTerm
            )
            Item(
                title = stringResource(R.string.traditionalculture_color),
                checked = homeItem.traditionalCultureColor,
                onCheckedChange = setTraditionalCultureColor
            )
            Item(
                title = stringResource(R.string.traditionalculture_calendar),
                checked = homeItem.traditionalCultureCalendar,
                onCheckedChange = setTraditionalCultureCalendar
            )
            SettingsTitle(title = stringResource(R.string.china))
            Item(
                title = stringResource(R.string.china_worldcultureheritage),
                checked = homeItem.chinaWorldCulturalHeritage,
                onCheckedChange = setChinaWorldCultureHeritage
            )
        }
    }
}

@Composable
private fun Item(
    modifier: Modifier = Modifier,
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp, 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title)
        Switch(checked = checked, onCheckedChange = onCheckedChange)
    }
}