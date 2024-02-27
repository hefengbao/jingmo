package com.hefengbao.jingmo.ui.screen.idiom

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.model.IdiomWithBookmark
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import com.hefengbao.jingmo.ui.screen.idiom.components.IdiomShowPanel

@Composable
fun IdiomRoute(
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
    viewModel: IdiomShowViewModel = hiltViewModel()
) {
    val idiom by viewModel.idiom.collectAsState(initial = null)

    IdiomScreen(
        onBackClick = onBackClick,
        onCaptureClick = onCaptureClick,
        idiom = idiom,
        setUncollect = { viewModel.setUncollect(it) },
        setCollect = { viewModel.setCollect(it) }
    )
}

@Composable
private fun IdiomScreen(
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
    idiom: IdiomWithBookmark?,
    setUncollect: (Int) -> Unit,
    setCollect: (Int) -> Unit
) {

    idiom?.let { entity ->
        var isCollect = entity.collectedAt != null
        SimpleScaffold(
            onBackClick = onBackClick,
            title = entity.word,
            actions = {
                IconButton(
                    onClick = {
                        if (isCollect) {
                            setUncollect(entity.id)
                        } else {
                            setCollect(entity.id)
                        }
                        isCollect = !isCollect
                    }
                ) {
                    if (isCollect) {
                        Icon(
                            imageVector = Icons.Default.Bookmark,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.BookmarkBorder,
                            contentDescription = null
                        )
                    }
                }
                IconButton(onClick = { onCaptureClick(entity.id) }) {
                    Icon(imageVector = Icons.Default.Photo, contentDescription = null)
                }
            }
        ) {
            IdiomShowPanel(idiom = entity)
        }
    }
}