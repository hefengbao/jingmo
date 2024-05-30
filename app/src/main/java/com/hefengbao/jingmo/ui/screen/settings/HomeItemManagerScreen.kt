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

@Composable
fun HomeItemManagerRoute(
    viewModel: HomeItemManagerViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val homeItem by viewModel.homeItem.collectAsState(initial = HomeItem())

    HomeItemManagerScreen(
        onBackClick = onBackClick,
        homeItem = homeItem,
        setClassicPoem = { viewModel.setClassicPoem(it) },
        setWriting = { viewModel.setWriting(it) },
        setPoemSentence = { viewModel.setPoemSentence(it) },
        setIdiom = { viewModel.setIdiom(it) },
        setChineseWisecrack = { viewModel.setChineseWisecrack(it) },
        setTongueTwister = { viewModel.setTongueTwister(it) },
        setFestival = { viewModel.setFestival(it) },
        setSolarTerm = { viewModel.setSolarTerm(it) },
        setChineseKnowledge = { viewModel.setChineseKnowledge(it) },
        setPeople = { viewModel.setPeople(it) },
        setChineseColor = { viewModel.setChineseColor(it) },
        setChineseCharacter = { viewModel.setChineseCharacter(it) },
        setChineseExpression = { viewModel.setChineseExpression(it) }
    )
}

@Composable
private fun HomeItemManagerScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    homeItem: HomeItem,
    setClassicPoem: (Boolean) -> Unit,
    setWriting: (Boolean) -> Unit,
    setPoemSentence: (Boolean) -> Unit,
    setIdiom: (Boolean) -> Unit,
    setChineseWisecrack: (Boolean) -> Unit,
    setTongueTwister: (Boolean) -> Unit,
    setFestival: (Boolean) -> Unit,
    setSolarTerm: (Boolean) -> Unit,
    setChineseKnowledge: (Boolean) -> Unit,
    setPeople: (Boolean) -> Unit,
    setChineseColor: (Boolean) -> Unit,
    setChineseCharacter: (Boolean) -> Unit,
    setChineseExpression: (Boolean) -> Unit,
) {
    SimpleScaffold(onBackClick = onBackClick, title = "栏目管理") {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            Item(
                title = "经典诗文",
                checked = homeItem.classicPoem,
                onCheckedChange = setClassicPoem
            )
            Item(
                title = "诗文",
                checked = homeItem.writing,
                onCheckedChange = setWriting
            )
            Item(
                title = "诗文名句",
                checked = homeItem.poemSentence,
                onCheckedChange = setPoemSentence
            )
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
                checked = homeItem.idiom,
                onCheckedChange = setIdiom
            )
            Item(
                title = "歇后语",
                checked = homeItem.chineseWisecrack,
                onCheckedChange = setChineseWisecrack
            )
            Item(
                title = "绕口令",
                checked = homeItem.tongueTwister,
                onCheckedChange = setTongueTwister
            )
            Item(
                title = "传统节日",
                checked = homeItem.festival,
                onCheckedChange = setFestival
            )
            Item(
                title = "二十四节气",
                checked = homeItem.solarTerm,
                onCheckedChange = setSolarTerm
            )
            Item(
                title = "知识卡片",
                checked = homeItem.chineseKnowledge,
                onCheckedChange = setChineseKnowledge
            )
            Item(
                title = "人物",
                checked = homeItem.people,
                onCheckedChange = setPeople
            )
            Item(
                title = "传统色",
                checked = homeItem.chineseColor,
                onCheckedChange = setChineseColor
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