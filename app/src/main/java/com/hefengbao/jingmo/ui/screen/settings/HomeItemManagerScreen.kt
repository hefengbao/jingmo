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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
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
        setChineseProverb = { viewModel.setChineseProverb(it) },
        setChineseTongueTwister = { viewModel.setChineseTongueTwister(it) },
        setChineseWisecrack = { viewModel.setChineseWisecrack(it) },
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
    setChineseProverb: (Boolean) -> Unit,
    setChineseTongueTwister: (Boolean) -> Unit,
    setChineseWisecrack: (Boolean) -> Unit,
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
            SettingsTitle(title = "古诗文")
            Item(
                title = "经典诗文",
                checked = homeItem.classicalLiteratureClassicPoem,
                onCheckedChange = setClassicalLiteratureClassicPoem
            )
            Item(
                title = "诗文",
                checked = homeItem.classicalLiteratureWriting,
                onCheckedChange = setClassicalLiteratureWriting
            )
            Item(
                title = "诗文名句",
                checked = homeItem.classicalLiteratureSentence,
                onCheckedChange = setClassicalLiteratureSentence
            )
            Item(
                title = "人物",
                checked = homeItem.classicalLiteraturePeople,
                onCheckedChange = setClassicalLiteraturePeople
            )
            SettingsTitle(title = "现代汉语")
            Item(
                title = "汉字",
                checked = homeItem.chineseCharacter,
                onCheckedChange = setChineseCharacter
            )
            Item(
                title = "词语",
                checked = homeItem.chineseExpression,
                onCheckedChange = setChineseExpression
            )
            Item(
                title = "成语",
                checked = homeItem.chineseIdiom,
                onCheckedChange = setChineseIdiom
            )
            Item(
                title = "歇后语",
                checked = homeItem.chineseWisecrack,
                onCheckedChange = setChineseWisecrack
            )
            Item(
                title = "谚语",
                checked = homeItem.chineseProverb,
                onCheckedChange = setChineseProverb
            )
            Item(
                title = "绕口令",
                checked = homeItem.chineseTongueTwister,
                onCheckedChange = setChineseTongueTwister
            )
            Item(
                title = "对联",
                checked = homeItem.chineseAntitheticalCouplet,
                onCheckedChange = setChineseAntitheticalCouplet
            )
            Item(
                title = "歌词",
                checked = homeItem.chineseLyric,
                onCheckedChange = setChineseLyric
            )
            Item(
                title = "知识卡片",
                checked = homeItem.chineseKnowledge,
                onCheckedChange = setChineseKnowledge
            )
            SettingsTitle(title = "传统文化")
            Item(
                title = "节日",
                checked = homeItem.traditionalCultureFestival,
                onCheckedChange = setTraditionalCultureFestival
            )
            Item(
                title = "节气",
                checked = homeItem.traditionalCultureSolarTerm,
                onCheckedChange = setTraditionalCultureSolarTerm
            )
            Item(
                title = "颜色",
                checked = homeItem.traditionalCultureColor,
                onCheckedChange = setTraditionalCultureColor
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