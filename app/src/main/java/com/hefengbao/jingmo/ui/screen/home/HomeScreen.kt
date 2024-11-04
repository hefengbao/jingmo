/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.R
import com.hefengbao.jingmo.data.model.HomeItem

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
    onChinaWorldCultureHeritageClick: () -> Unit,
    onChineseAntitheticalCoupletClick: () -> Unit,
    onChineseCharacterClick: () -> Unit,
    onChineseExpressionClick: () -> Unit,
    onChineseIdiomClick: () -> Unit,
    onChineseKnowledgeClick: () -> Unit,
    onChineseLyricClick: () -> Unit,
    onChineseProverbClick: () -> Unit,
    onChineseQuoteClick: () -> Unit,
    onChineseRiddleClick: () -> Unit,
    onChineseTongueTwisterClick: () -> Unit,
    onChineseWisecrackClick: () -> Unit,
    onClassicalLiteratureClassicPoemClick: () -> Unit,
    onClassicalLiteraturePeopleClick: () -> Unit,
    onClassicalLiteratureSentenceClick: () -> Unit,
    onClassicalLiteratureWritingClick: () -> Unit,
    onTraditionalCultureColorClick: () -> Unit,
    onTraditionalCultureFestivalClick: () -> Unit,
    onTraditionalCultureSolarTermsClick: () -> Unit,
    onLinksClick: () -> Unit,
    onSettingsClick: () -> Unit,
) {
    val homeItem by viewModel.homeItem.collectAsState(initial = HomeItem())
    val showSyncDataTip by viewModel.showSyncDataTip.collectAsState(initial = false)

    HomeScreen(
        homeItem = homeItem,
        onChinaWorldCultureHeritageClick = onChinaWorldCultureHeritageClick,
        onChineseAntitheticalCoupletClick = onChineseAntitheticalCoupletClick,
        onChineseCharacterClick = onChineseCharacterClick,
        onChineseExpressionClick = onChineseExpressionClick,
        onChineseIdiomClick = onChineseIdiomClick,
        onChineseKnowledgeClick = onChineseKnowledgeClick,
        onChineseLyricClick = onChineseLyricClick,
        onChineseProverbClick = onChineseProverbClick,
        onChineseQuoteClick = onChineseQuoteClick,
        onChineseRiddleClick = onChineseRiddleClick,
        onChineseTongueTwisterClick = onChineseTongueTwisterClick,
        onChineseWisecrackClick = onChineseWisecrackClick,
        onClassicalLiteratureClassicPoemClick = onClassicalLiteratureClassicPoemClick,
        onClassicalLiteraturePeopleClick = onClassicalLiteraturePeopleClick,
        onClassicalLiteratureSentenceClick = onClassicalLiteratureSentenceClick,
        onClassicalLiteratureWritingClick = onClassicalLiteratureWritingClick,
        onTraditionalCultureColorClick = onTraditionalCultureColorClick,
        onTraditionalCultureFestivalClick = onTraditionalCultureFestivalClick,
        onTraditionalCultureSolarTermsClick = onTraditionalCultureSolarTermsClick,
        onLinksClick = onLinksClick,
        onSettingsClick = onSettingsClick,
        showSyncDataTip = showSyncDataTip,
        updateShowSyncDataTip = { viewModel.updateShowSyncDataTip() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(
    modifier: Modifier = Modifier,
    homeItem: HomeItem,
    onChinaWorldCultureHeritageClick: () -> Unit,
    onChineseAntitheticalCoupletClick: () -> Unit,
    onChineseCharacterClick: () -> Unit,
    onChineseExpressionClick: () -> Unit,
    onChineseIdiomClick: () -> Unit,
    onChineseKnowledgeClick: () -> Unit,
    onChineseLyricClick: () -> Unit,
    onChineseProverbClick: () -> Unit,
    onChineseQuoteClick: () -> Unit,
    onChineseRiddleClick: () -> Unit,
    onChineseTongueTwisterClick: () -> Unit,
    onChineseWisecrackClick: () -> Unit,
    onClassicalLiteratureClassicPoemClick: () -> Unit,
    onClassicalLiteraturePeopleClick: () -> Unit,
    onClassicalLiteratureSentenceClick: () -> Unit,
    onClassicalLiteratureWritingClick: () -> Unit,
    onTraditionalCultureColorClick: () -> Unit,
    onTraditionalCultureFestivalClick: () -> Unit,
    onTraditionalCultureSolarTermsClick: () -> Unit,
    onLinksClick: () -> Unit,
    onSettingsClick: () -> Unit,
    showSyncDataTip: Boolean,
    updateShowSyncDataTip: () -> Unit,
) {
    if (showSyncDataTip) {
        AlertDialog(
            onDismissRequest = updateShowSyncDataTip,
            confirmButton = {
                TextButton(onClick = updateShowSyncDataTip) {
                    Text(text = "知道了")
                }
            },
            text = {
                Text(text = "如果首次使用，请点击右上角设置（⚙）同步数据或者导入数据")
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                actions = {
                    IconButton(onClick = onLinksClick) {
                        Icon(imageVector = Icons.Default.Link, contentDescription = "链接")
                    }
                    IconButton(onClick = onSettingsClick) {
                        Icon(imageVector = Icons.Default.Settings, contentDescription = "设置")
                    }
                }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = modifier
                .padding(paddingValues)
                .fillMaxWidth()
        ) {
            LazyVerticalGrid(
                modifier = modifier.padding(16.dp),
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                content = {
                    if (homeItem.classicalLiteratureGroup) {
                        item(
                            span = { GridItemSpan(2) }
                        ) {
                            Title(text = "古诗文")
                        }
                        if (homeItem.classicalLiteratureClassicPoem) {
                            item {
                                Item(
                                    title = "经典诗文",
                                    onClick = onClassicalLiteratureClassicPoemClick
                                )
                            }
                        }
                        if (homeItem.classicalLiteratureWriting) {
                            item {
                                Item(
                                    title = "诗文",
                                    onClick = onClassicalLiteratureWritingClick
                                )
                            }
                        }
                        if (homeItem.classicalLiteratureSentence) {
                            item {
                                Item(
                                    title = "诗文名句",
                                    onClick = onClassicalLiteratureSentenceClick
                                )
                            }
                        }
                    }

                    if (homeItem.classicalLiteraturePeople) {
                        item {
                            Item(
                                title = "人物",
                                onClick = onClassicalLiteraturePeopleClick
                            )
                        }
                    }

                    if (homeItem.chineseGroup) {
                        item(
                            span = { GridItemSpan(2) }
                        ) {
                            Title(text = "现代汉语")
                        }
                        if (homeItem.chineseCharacter) {
                            item {
                                Item(
                                    title = "汉字",
                                    onClick = onChineseCharacterClick
                                )
                            }
                        }
                        if (homeItem.chineseExpression) {
                            item {
                                Item(
                                    title = "词语",
                                    onClick = onChineseExpressionClick
                                )
                            }
                        }
                        if (homeItem.chineseIdiom) {
                            item {
                                Item(
                                    title = "成语",
                                    onClick = onChineseIdiomClick
                                )
                            }
                        }
                        if (homeItem.chineseWisecrack) {
                            item {
                                Item(
                                    title = "歇后语",
                                    onClick = onChineseWisecrackClick
                                )
                            }
                        }
                        if (homeItem.chineseProverb) {
                            item {
                                Item(
                                    title = "谚语",
                                    onClick = onChineseProverbClick
                                )
                            }
                        }
                        if (homeItem.chineseRiddle) {
                            item {
                                Item(
                                    title = "谜语",
                                    onClick = onChineseRiddleClick
                                )
                            }
                        }
                        if (homeItem.chineseTongueTwister) {
                            item {
                                Item(
                                    title = "绕口令",
                                    onClick = onChineseTongueTwisterClick
                                )
                            }
                        }
                        if (homeItem.chineseAntitheticalCouplet) {
                            item {
                                Item(
                                    title = "对联",
                                    onClick = onChineseAntitheticalCoupletClick
                                )
                            }
                        }
                        if (homeItem.chineseLyric) {
                            item {
                                Item(
                                    title = "歌词",
                                    onClick = onChineseLyricClick
                                )
                            }
                        }
                        if (homeItem.chineseKnowledge) {
                            item {
                                Item(
                                    title = "知识卡片",
                                    onClick = onChineseKnowledgeClick
                                )
                            }
                        }
                        if (homeItem.chineseQuote) {
                            item {
                                Item(
                                    title = "句子",
                                    onClick = onChineseQuoteClick
                                )
                            }
                        }
                    }

                    if (homeItem.traditionalCultureGroup) {
                        item(
                            span = { GridItemSpan(2) }
                        ) {
                            Title(text = "传统文化")
                        }
                        if (homeItem.traditionalCultureFestival) {
                            item {
                                Item(
                                    title = "节日",
                                    onClick = onTraditionalCultureFestivalClick
                                )
                            }
                        }
                        if (homeItem.traditionalCultureSolarTerm) {
                            item {
                                Item(
                                    title = "节气",
                                    onClick = onTraditionalCultureSolarTermsClick
                                )
                            }
                        }
                        if (homeItem.traditionalCultureColor) {
                            item {
                                Item(
                                    title = "颜色",
                                    onClick = onTraditionalCultureColorClick
                                )
                            }
                        }
                    }
                    if (homeItem.chinaGroup) {
                        item(
                            span = { GridItemSpan(2) }
                        ) {
                            Title(text = "中国")
                        }
                        if (homeItem.chinaWorldCulturalHeritage) {
                            item {
                                Item(
                                    title = "世界文化遗产",
                                    onClick = onChinaWorldCultureHeritageClick
                                )
                            }
                        }
                    }
                }
            )
        }
    }
}

@Composable
private fun Item(
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
    ) {
        Column(
            modifier = modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun Title(
    text: String
) {
    val color = MaterialTheme.colorScheme.primary
    Row(
        modifier = Modifier.padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            modifier = Modifier
                .height(20.dp)
                .drawBehind {
                    drawLine(
                        color = color,
                        start = Offset(0f, 0f),
                        end = Offset(0f, 80f),
                        strokeWidth = 20f
                    )
                }
        ) {}
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

