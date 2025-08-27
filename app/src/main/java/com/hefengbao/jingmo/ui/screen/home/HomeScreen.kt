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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Bookmarks
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.R
import com.hefengbao.jingmo.data.model.HomeItem

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
    onBookmarkClick: () -> Unit,
    onChinaWorldCultureHeritageClick: () -> Unit,
    onChineseAntitheticalCoupletClick: () -> Unit,
    onChineseCharacterClick: () -> Unit,
    onChineseExpressionClick: () -> Unit,
    onChineseIdiomClick: () -> Unit,
    onChineseKnowledgeClick: () -> Unit,
    onChineseLyricClick: () -> Unit,
    onChineseModernPoetryClick: () -> Unit,
    onChineseProverbClick: () -> Unit,
    onChineseQuoteClick: () -> Unit,
    onChineseRiddleClick: () -> Unit,
    onChineseTongueTwisterClick: () -> Unit,
    onChineseWisecrackClick: () -> Unit,
    onClassicalLiteratureClassicPoemClick: () -> Unit,
    onClassicalLiteraturePeopleClick: () -> Unit,
    onClassicalLiteratureSentenceClick: () -> Unit,
    onClassicalLiteratureWritingClick: () -> Unit,
    onTraditionalCultureCalendarClick: () -> Unit,
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
        onBookmarkClick = onBookmarkClick,
        onChinaWorldCultureHeritageClick = onChinaWorldCultureHeritageClick,
        onChineseAntitheticalCoupletClick = onChineseAntitheticalCoupletClick,
        onChineseCharacterClick = onChineseCharacterClick,
        onChineseExpressionClick = onChineseExpressionClick,
        onChineseIdiomClick = onChineseIdiomClick,
        onChineseKnowledgeClick = onChineseKnowledgeClick,
        onChineseLyricClick = onChineseLyricClick,
        onChineseModernPoetryClick = onChineseModernPoetryClick,
        onChineseProverbClick = onChineseProverbClick,
        onChineseQuoteClick = onChineseQuoteClick,
        onChineseRiddleClick = onChineseRiddleClick,
        onChineseTongueTwisterClick = onChineseTongueTwisterClick,
        onChineseWisecrackClick = onChineseWisecrackClick,
        onClassicalLiteratureClassicPoemClick = onClassicalLiteratureClassicPoemClick,
        onClassicalLiteraturePeopleClick = onClassicalLiteraturePeopleClick,
        onClassicalLiteratureSentenceClick = onClassicalLiteratureSentenceClick,
        onClassicalLiteratureWritingClick = onClassicalLiteratureWritingClick,
        onTraditionalCultureCalendarClick = onTraditionalCultureCalendarClick,
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
    onBookmarkClick: () -> Unit,
    onChinaWorldCultureHeritageClick: () -> Unit,
    onChineseAntitheticalCoupletClick: () -> Unit,
    onChineseCharacterClick: () -> Unit,
    onChineseExpressionClick: () -> Unit,
    onChineseIdiomClick: () -> Unit,
    onChineseKnowledgeClick: () -> Unit,
    onChineseLyricClick: () -> Unit,
    onChineseModernPoetryClick: () -> Unit,
    onChineseProverbClick: () -> Unit,
    onChineseQuoteClick: () -> Unit,
    onChineseRiddleClick: () -> Unit,
    onChineseTongueTwisterClick: () -> Unit,
    onChineseWisecrackClick: () -> Unit,
    onClassicalLiteratureClassicPoemClick: () -> Unit,
    onClassicalLiteraturePeopleClick: () -> Unit,
    onClassicalLiteratureSentenceClick: () -> Unit,
    onClassicalLiteratureWritingClick: () -> Unit,
    onTraditionalCultureCalendarClick: () -> Unit,
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
                    IconButton(onBookmarkClick) {
                        Icon(imageVector = Icons.Outlined.Bookmarks, contentDescription = "收藏夹")
                    }
                    IconButton(onClick = onLinksClick) {
                        Icon(imageVector = Icons.Default.Link, contentDescription = "链接")
                    }
                    IconButton(onClick = onSettingsClick) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = stringResource(R.string.settings)
                        )
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
                            Title(title = stringResource(R.string.classicalliterature))
                        }
                        if (homeItem.classicalLiteratureClassicPoem) {
                            item {
                                Item(
                                    title = stringResource(R.string.classicalliterature_classicpoem),
                                    onClick = onClassicalLiteratureClassicPoemClick
                                )
                            }
                        }
                        if (homeItem.classicalLiteratureWriting) {
                            item {
                                Item(
                                    title = stringResource(R.string.classicalliterature_writing),
                                    onClick = onClassicalLiteratureWritingClick
                                )
                            }
                        }
                        if (homeItem.classicalLiteratureSentence) {
                            item {
                                Item(
                                    title = stringResource(R.string.classicalliterature_sentence),
                                    onClick = onClassicalLiteratureSentenceClick
                                )
                            }
                        }
                    }

                    if (homeItem.classicalLiteraturePeople) {
                        item {
                            Item(
                                title = stringResource(R.string.classicalliterature_people),
                                onClick = onClassicalLiteraturePeopleClick
                            )
                        }
                    }

                    if (homeItem.chineseGroup) {
                        item(
                            span = { GridItemSpan(2) }
                        ) {
                            Title(title = stringResource(R.string.chinese))
                        }
                        if (homeItem.chineseCharacter) {
                            item {
                                Item(
                                    title = stringResource(R.string.chinese_character),
                                    onClick = onChineseCharacterClick
                                )
                            }
                        }
                        if (homeItem.chineseExpression) {
                            item {
                                Item(
                                    title = stringResource(R.string.chinese_expression),
                                    onClick = onChineseExpressionClick
                                )
                            }
                        }
                        if (homeItem.chineseIdiom) {
                            item {
                                Item(
                                    title = stringResource(R.string.chinese_idiom),
                                    onClick = onChineseIdiomClick
                                )
                            }
                        }
                        if (homeItem.chineseWisecrack) {
                            item {
                                Item(
                                    title = stringResource(R.string.chinese_wisecrack),
                                    onClick = onChineseWisecrackClick
                                )
                            }
                        }
                        if (homeItem.chineseProverb) {
                            item {
                                Item(
                                    title = stringResource(R.string.chinese_proverb),
                                    onClick = onChineseProverbClick
                                )
                            }
                        }
                        if (homeItem.chineseRiddle) {
                            item {
                                Item(
                                    title = stringResource(R.string.chinese_riddle),
                                    onClick = onChineseRiddleClick
                                )
                            }
                        }
                        if (homeItem.chineseTongueTwister) {
                            item {
                                Item(
                                    title = stringResource(R.string.chinese_tonguetwister),
                                    onClick = onChineseTongueTwisterClick
                                )
                            }
                        }
                        if (homeItem.chineseAntitheticalCouplet) {
                            item {
                                Item(
                                    title = stringResource(R.string.chinese_antitheticalcouplet),
                                    onClick = onChineseAntitheticalCoupletClick
                                )
                            }
                        }
                        if (homeItem.chineseLyric) {
                            item {
                                Item(
                                    title = stringResource(R.string.chinese_lyric),
                                    onClick = onChineseLyricClick
                                )
                            }
                        }
                        if (homeItem.chineseQuote) {
                            item {
                                Item(
                                    title = stringResource(R.string.chinese_quote),
                                    onClick = onChineseQuoteClick
                                )
                            }
                        }
                        if (homeItem.chineseModernPoetry) {
                            item {
                                Item(
                                    title = stringResource(R.string.chinese_modernpoetry),
                                    onClick = onChineseModernPoetryClick
                                )
                            }
                        }
                        if (homeItem.chineseKnowledge) {
                            item {
                                Item(
                                    title = stringResource(R.string.chinese_knowledge),
                                    onClick = onChineseKnowledgeClick
                                )
                            }
                        }
                    }

                    if (homeItem.traditionalCultureGroup) {
                        item(
                            span = { GridItemSpan(2) }
                        ) {
                            Title(title = stringResource(R.string.traditionalculture))
                        }
                        if (homeItem.traditionalCultureFestival) {
                            item {
                                Item(
                                    title = stringResource(R.string.traditionalculture_festival),
                                    onClick = onTraditionalCultureFestivalClick
                                )
                            }
                        }
                        if (homeItem.traditionalCultureSolarTerm) {
                            item {
                                Item(
                                    title = stringResource(R.string.traditionalculture_solarterm),
                                    onClick = onTraditionalCultureSolarTermsClick
                                )
                            }
                        }
                        if (homeItem.traditionalCultureColor) {
                            item {
                                Item(
                                    title = stringResource(R.string.traditionalculture_color),
                                    onClick = onTraditionalCultureColorClick
                                )
                            }
                        }
                        item {
                            Item(
                                title = stringResource(R.string.traditionalculture_calendar),
                                onClick = onTraditionalCultureCalendarClick
                            )
                        }
                    }
                    if (homeItem.chinaGroup) {
                        item(
                            span = { GridItemSpan(2) }
                        ) {
                            Title(title = stringResource(R.string.china))
                        }
                        if (homeItem.chinaWorldCulturalHeritage) {
                            item {
                                Item(
                                    title = stringResource(R.string.china_worldcultureheritage),
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
    title: String
) {
    /*val color = MaterialTheme.colorScheme.primary
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
    }*/
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 8.dp),
        text = title,
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.onBackground.copy(alpha = .5f),
    )
}

