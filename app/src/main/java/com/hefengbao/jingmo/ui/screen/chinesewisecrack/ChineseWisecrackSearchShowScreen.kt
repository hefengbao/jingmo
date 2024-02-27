package com.hefengbao.jingmo.ui.screen.chinesewisecrack

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.model.ChineseWisecrackWithBookmark
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import com.hefengbao.jingmo.ui.screen.chinesewisecrack.components.ShowChineseWisecrackPanel

@Composable
fun ChineseWisecrackSearchShowRoute(
    viewModel: ChineseWisecrackSearchShowViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
) {

    val prevId by viewModel.prevId.collectAsState(initial = null)

    val nextId by viewModel.nextId.collectAsState(initial = null)

    val chineseWisecrack by viewModel.wisecrack.collectAsState(initial = null)

    ChineseWisecrackSearchShowScreen(
        onBackClick = onBackClick,
        onCaptureClick = onCaptureClick,
        chineseCrack = chineseWisecrack,
        prevId = prevId,
        nextId = nextId,
        setCurrentId = { viewModel.setCurrentId(it) },
        setUncollect = { viewModel.setUncollect(it) },
        setCollect = { viewModel.setCurrentId(it) },
        query = viewModel.query
    )
}

@Composable
private fun ChineseWisecrackSearchShowScreen(
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
    chineseCrack: ChineseWisecrackWithBookmark?,
    prevId: Int?,
    nextId: Int?,
    setCurrentId: (Int) -> Unit,
    setUncollect: (Int) -> Unit,
    setCollect: (Int) -> Unit,
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
        ) {
            ShowChineseWisecrackPanel(
                entity = entity,
                prevId = prevId,
                nextId = nextId,
                setCurrentId = setCurrentId,
                setUncollect = setUncollect,
                setCollect = setCollect
            )
        }
    }
}