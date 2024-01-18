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
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
fun ChineseWisecrackSearchShowRoute(
    viewModel: ChineseWisecrackSearchShowViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
) {

    LaunchedEffect(Unit) {
        viewModel.getChineseWisecrack(viewModel.id)
        viewModel.getPrevId(viewModel.id, viewModel.query)
        viewModel.getNextId(viewModel.id, viewModel.query)
    }

    val prevId by viewModel.prevId.collectAsState(initial = null)

    val nextId by viewModel.nextId.collectAsState(initial = null)

    val chineseWisecrack by viewModel.chineseCrack.collectAsState(initial = null)

    ChineseWisecrackSearchShowScreen(
        onBackClick = onBackClick,
        onCaptureClick = onCaptureClick,
        chineseCrack = chineseWisecrack,
        prevId = prevId,
        nextId = nextId,
        onPrevClick = {
            viewModel.getChineseWisecrack(prevId!!)
            viewModel.getPrevId(prevId!!, viewModel.query)
            viewModel.getNextId(prevId!!, viewModel.query)
        },
        onNextClick = {
            viewModel.getChineseWisecrack(nextId!!)
            viewModel.getPrevId(nextId!!, viewModel.query)
            viewModel.getNextId(nextId!!, viewModel.query)
        },
        query = viewModel.query
    )
}

@Composable
private fun ChineseWisecrackSearchShowScreen(
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
    chineseCrack: ChineseWisecrackEntity?,
    prevId: Int?,
    nextId: Int?,
    onPrevClick: () -> Unit,
    onNextClick: () -> Unit,
    query: String
) {

    chineseCrack?.let { entity ->
        SimpleScaffold(
            onBackClick = onBackClick,
            title = "搜索：$query",
            actions = {
                IconButton(onClick = { onCaptureClick(entity.id) }) {
                    Icon(imageVector = Icons.Default.Photo, contentDescription = null)
                }
            }
        ){
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