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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hefengbao.jingmo.R

@Composable
fun HomeRoute(
    onLinksClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onPoemClick: () -> Unit,
    onPoemSentenceClick: () -> Unit,
    onChineseWisecrackClick: () -> Unit,
    onIdiomClick: () -> Unit,
    onChineseColorClick: () -> Unit,
    onFestivalClick: () -> Unit,
    onSolarTermsClick: () -> Unit,
    onTongueTwisterClick: () -> Unit,
    onChineseKnowledgeClick: () -> Unit,
    onRiddleClick: () -> Unit,
) {
    HomeScreen(
        onLinksClick = onLinksClick,
        onSettingsClick = onSettingsClick,
        onPoemClick = onPoemClick,
        onPoemSentenceClick = onPoemSentenceClick,
        onChineseWisecrackClick = onChineseWisecrackClick,
        onIdiomClick = onIdiomClick,
        onChineseColorClick = onChineseColorClick,
        onFestivalClick = onFestivalClick,
        onSolarTermsClick = onSolarTermsClick,
        onTongueTwisterClick = onTongueTwisterClick,
        onChineseKnowledgeClick = onChineseKnowledgeClick,
        onRiddleClick = onRiddleClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(
    modifier: Modifier = Modifier,
    onLinksClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onPoemClick: () -> Unit,
    onPoemSentenceClick: () -> Unit,
    onChineseWisecrackClick: () -> Unit,
    onIdiomClick: () -> Unit,
    onChineseColorClick: () -> Unit,
    onFestivalClick: () -> Unit,
    onSolarTermsClick: () -> Unit,
    onTongueTwisterClick: () -> Unit,
    onChineseKnowledgeClick: () -> Unit,
    onRiddleClick: () -> Unit,
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
                    item {
                        Item(
                            title = "古诗词文",
                            subtitle = "10000 首（阙/篇）",
                            onClick = onPoemClick
                        )
                    }
                    item {
                        Item(
                            title = "古诗词文名句",
                            subtitle = "10000 句",
                            onClick = onPoemSentenceClick
                        )
                    }
                    item {
                        Item(
                            title = "歇后语",
                            subtitle = "14026 条",
                            onClick = onChineseWisecrackClick
                        )
                    }
                    item {
                        Item(
                            title = "成语",
                            subtitle = "30895 条",
                            onClick = onIdiomClick
                        )
                    }
                    item {
                        Item(
                            title = "谜语",
                            subtitle = "42446 组",
                            onClick = onRiddleClick
                        )
                    }
                    item {
                        Item(
                            title = "绕口令",
                            subtitle = "45 段",
                            onClick = onTongueTwisterClick
                        )
                    }
                    item {
                        Item(
                            title = "中国传统节日",
                            subtitle = "16 个",
                            onClick = onFestivalClick
                        )
                    }
                    item {
                        Item(
                            title = "二十四节气",
                            subtitle = "24 个",
                            onClick = onSolarTermsClick
                        )
                    }
                    item {
                        Item(
                            title = "知识卡片",
                            subtitle = "464 组",
                            onClick = onChineseKnowledgeClick
                        )
                    }
                    item {
                        Item(
                            title = "中国传统色",
                            subtitle = "161 种",
                            onClick = onChineseColorClick
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
    subtitle: String,
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
            Text(text = subtitle, style = MaterialTheme.typography.labelMedium)
        }
    }
}

