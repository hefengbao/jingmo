package com.hefengbao.jingmo.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.R
import com.hefengbao.jingmo.data.model.HomeItem

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
    onChineseCharacterClick: () -> Unit,
    onChineseColorClick: () -> Unit,
    onChineseKnowledgeClick: () -> Unit,
    onChineseWisecrackClick: () -> Unit,
    onClassicPoemClick: () -> Unit,
    onFestivalClick: () -> Unit,
    onIdiomClick: () -> Unit,
    onLinksClick: () -> Unit,
    onPeopleClick: () -> Unit,
    onPoemSentenceClick: () -> Unit,
    onRiddleClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onSolarTermsClick: () -> Unit,
    onTongueTwisterClick: () -> Unit,
    onWritingClick: () -> Unit
) {
    val homeItem by viewModel.homeItem.collectAsState(initial = HomeItem())

    HomeScreen(
        homeItem = homeItem,
        onChineseCharacterClick = onChineseCharacterClick,
        onChineseColorClick = onChineseColorClick,
        onChineseKnowledgeClick = onChineseKnowledgeClick,
        onChineseWisecrackClick = onChineseWisecrackClick,
        onClassicPoemClick = onClassicPoemClick,
        onFestivalClick = onFestivalClick,
        onIdiomClick = onIdiomClick,
        onLinksClick = onLinksClick,
        onPeopleClick = onPeopleClick,
        onPoemSentenceClick = onPoemSentenceClick,
        onRiddleClick = onRiddleClick,
        onSettingsClick = onSettingsClick,
        onSolarTermsClick = onSolarTermsClick,
        onTongueTwisterClick = onTongueTwisterClick,
        onWritingClick = onWritingClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(
    modifier: Modifier = Modifier,
    homeItem: HomeItem,
    onChineseCharacterClick: () -> Unit,
    onChineseColorClick: () -> Unit,
    onChineseKnowledgeClick: () -> Unit,
    onChineseWisecrackClick: () -> Unit,
    onClassicPoemClick: () -> Unit,
    onFestivalClick: () -> Unit,
    onIdiomClick: () -> Unit,
    onLinksClick: () -> Unit,
    onPeopleClick: () -> Unit,
    onPoemSentenceClick: () -> Unit,
    onRiddleClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onSolarTermsClick: () -> Unit,
    onTongueTwisterClick: () -> Unit,
    onWritingClick: () -> Unit
) {
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
                    if (homeItem.classicPoem) {
                        item {
                            Item(
                                title = "经典诗文",
                                onClick = onClassicPoemClick
                            )
                        }
                    }
                    if (homeItem.writing) {
                        item {
                            Item(
                                title = "诗文",
                                onClick = onWritingClick
                            )
                        }
                    }
                    if (homeItem.poemSentence) {
                        item {
                            Item(
                                title = "诗文名句",
                                onClick = onPoemSentenceClick
                            )
                        }
                    }
                    if (homeItem.idiom) {
                        item {
                            Item(
                                title = "成语",
                                onClick = onIdiomClick
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
                    /*item {
                        Item(
                            title = "谜语",
                            onClick = onRiddleClick
                        )
                    }*/
                    if (homeItem.tongueTwister) {
                        item {
                            Item(
                                title = "绕口令",
                                onClick = onTongueTwisterClick
                            )
                        }
                    }
                    if (homeItem.festival) {
                        item {
                            Item(
                                title = "传统节日",
                                onClick = onFestivalClick
                            )
                        }
                    }
                    if (homeItem.solarTerm) {
                        item {
                            Item(
                                title = "二十四节气",
                                onClick = onSolarTermsClick
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
                    if (homeItem.people) {
                        item {
                            Item(
                                title = "人物",
                                onClick = onPeopleClick
                            )
                        }
                    }
                    if (homeItem.chineseColor) {
                        item {
                            Item(
                                title = "传统色",
                                onClick = onChineseColorClick
                            )
                        }
                    }
                    item {
                        Item(
                            title = "汉字",
                            onClick = onChineseCharacterClick
                        )
                    }
                }
            )
        }
    }
}

@Composable
fun Item(
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
            Text(text = title, style = MaterialTheme.typography.titleLarge)
        }
    }
}

