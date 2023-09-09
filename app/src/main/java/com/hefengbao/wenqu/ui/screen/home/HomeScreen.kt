package com.hefengbao.wenqu.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.wenqu.R
import com.hefengbao.wenqu.data.model.DataStatus

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
    onSettingsClick: () -> Unit,
    onPoemClick: () -> Unit,
    onPoemSentenceClick: () -> Unit,
    onChineseWisecrackClick: () -> Unit,
    onIdiomClick: () -> Unit
) {
    HomeScreen(
        onSettingsClick = onSettingsClick,
        onPoemClick = onPoemClick,
        onPoemSentenceClick = onPoemSentenceClick,
        onChineseWisecrackClick = onChineseWisecrackClick,
        onIdiomClick = onIdiomClick,
        dataStatus = viewModel.dataStatus
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(
    modifier: Modifier = Modifier,
    onSettingsClick: () -> Unit,
    onPoemClick: () -> Unit,
    onPoemSentenceClick: () -> Unit,
    onChineseWisecrackClick: () -> Unit,
    onIdiomClick: () -> Unit,
    dataStatus: DataStatus
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                actions = {
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
                            subtitle = "${dataStatus.poemCount} 首（阙/篇）",
                            onClick = onPoemClick
                        )
                    }
                    item {
                        Item(
                            title = "古诗词文名句",
                            subtitle = "${dataStatus.poemSentenceCount} 句",
                            onClick = onPoemSentenceClick
                        )
                    }
                    item {
                        Item(
                            title = "歇后语",
                            subtitle = "${dataStatus.chineseWisecrackCount} 条",
                            onClick = onChineseWisecrackClick
                        )
                    }
                    item {
                        Item(
                            title = "成语",
                            subtitle = "${dataStatus.idiomCount} 条",
                            onClick = onIdiomClick
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

