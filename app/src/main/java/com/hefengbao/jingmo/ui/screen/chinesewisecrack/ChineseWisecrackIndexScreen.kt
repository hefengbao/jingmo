package com.hefengbao.jingmo.ui.screen.chinesewisecrack

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.model.ChineseWisecrackWithBookmark
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import com.hefengbao.jingmo.ui.screen.chinesewisecrack.components.ShowChineseWisecrackPanel

@Composable
fun ChineseWisecrackIndexRoute(
    viewModel: ChineseWisecrackIndexViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
    onSearchClick: () -> Unit,
    onBookmarksClick: () -> Unit,
) {

    val prevId by viewModel.prevId.collectAsState(initial = null)

    val nextId by viewModel.nextId.collectAsState(initial = null)

    val chineseWisecrack by viewModel.chineseCrack.collectAsState(initial = null)

    ChineseWisecrackIndexScreen(
        onBackClick = onBackClick,
        onBookmarksClick = onBookmarksClick,
        onCaptureClick = onCaptureClick,
        onSearchClick = onSearchClick,
        chineseCrack = chineseWisecrack,
        prevId = prevId,
        nextId = nextId,
        setCurrentId = { viewModel.setCurrentId(it) },
        setCollect = { viewModel.setCollect(it) },
        setUncollect = { viewModel.setUncollect(it) },
        setLastReadId = {
            viewModel.setLastReadId(it)
        },
    )
}

@Composable
private fun ChineseWisecrackIndexScreen(
    onBackClick: () -> Unit,
    onBookmarksClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
    onSearchClick: () -> Unit,
    chineseCrack: ChineseWisecrackWithBookmark?,
    prevId: Int?,
    nextId: Int?,
    setCurrentId: (Int) -> Unit,
    setCollect: (Int) -> Unit,
    setUncollect: (Int) -> Unit,
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
                IconButton(onClick = onBookmarksClick) {
                    Icon(imageVector = Icons.Outlined.Bookmarks, contentDescription = "收藏")
                }
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
                nextId = nextId,
                setUncollect = setUncollect,
                setCollect = setCollect,
                setCurrentId = setCurrentId
            )
        }
    }
}