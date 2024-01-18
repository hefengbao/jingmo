package com.hefengbao.jingmo.ui.screen.chinesewisecrack

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.entity.ChineseWisecrackEntity
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import com.hefengbao.jingmo.ui.screen.chinesewisecrack.components.ShowChineseWisecrackPanel

@Composable
fun ChineseWisecrackRoute(
    viewModel: ChineseWisecrackIndexViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
    onSearchClick: () -> Unit
) {

    LaunchedEffect(Unit) {
        viewModel.getChineseWisecrack(viewModel.id)
        viewModel.getPrevId(viewModel.id)
        viewModel.getNextId(viewModel.id)
    }

    val prevId by viewModel.prevId.collectAsState(initial = null)

    val nextId by viewModel.nextId.collectAsState(initial = null)

    val chineseWisecrack by viewModel.chineseCrack.collectAsState(initial = null)

    ChineseWisecrackScreen(
        onBackClick = onBackClick,
        onCaptureClick = onCaptureClick,
        onSearchClick = onSearchClick,
        chineseCrack = chineseWisecrack,
        prevId = prevId,
        nextId = nextId,
        onPrevClick = {
            viewModel.getChineseWisecrack(prevId!!)
            viewModel.getPrevId(prevId!!)
            viewModel.getNextId(prevId!!)
        },
        onNextClick = {
            viewModel.getChineseWisecrack(nextId!!)
            viewModel.getPrevId(nextId!!)
            viewModel.getNextId(nextId!!)
        },
        setLastReadId = {
            viewModel.setLastReadId(it)
        }
    )
}

@Composable
private fun ChineseWisecrackScreen(
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
    onSearchClick: () -> Unit,
    chineseCrack: ChineseWisecrackEntity?,
    prevId: Int?,
    nextId: Int?,
    onPrevClick: () -> Unit,
    onNextClick: () -> Unit,
    setLastReadId: (Int) -> Unit,
) {
    chineseCrack?.let { entity ->
        LaunchedEffect(entity) {
            setLastReadId(entity.id)
        }

        SimpleScaffold(
            onBackClick = onBackClick,
            title = "歇后语",
            actions = {
                IconButton(onClick = { onCaptureClick(entity.id) }) {
                    Icon(imageVector = Icons.Default.Photo, contentDescription = null)
                }
                IconButton(onClick = onSearchClick) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                }
            }
        ) {
            ShowChineseWisecrackPanel(
                entity = entity,
                prevId = prevId,
                onPrevClick = onPrevClick,
                nextId = nextId,
                onNextClick = onNextClick
            )
        }
    }
}