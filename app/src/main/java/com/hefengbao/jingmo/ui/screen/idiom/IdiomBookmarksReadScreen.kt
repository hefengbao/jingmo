package com.hefengbao.jingmo.ui.screen.idiom

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.model.IdiomWithBookmark
import com.hefengbao.jingmo.ui.component.BottomActionBar
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import com.hefengbao.jingmo.ui.screen.idiom.components.IdiomShowPanel
import kotlin.math.abs

@Composable
fun IdiomBookmarksReadRoute(
    viewModel: IdiomBookmarksReadViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {
    val idiom by viewModel.idiom.collectAsState(initial = null)
    val prevId by viewModel.prevId.collectAsState(initial = null)
    val nextId by viewModel.nextId.collectAsState(initial = null)

    IdiomBookmarksReadScreen(
        onBackClick = onBackClick,
        idiom = idiom,
        prevId = prevId,
        nextId = nextId,
        getPrevId = { viewModel.getPrevId(it) },
        getNextId = { viewModel.getNextId(it) },
        setCurrentId = { viewModel.setCurrentId(it) },
        setCollect = { viewModel.setCollect(it) },
        setUncollect = { viewModel.setUncollect(it) }
    )
}

@Composable
private fun IdiomBookmarksReadScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    idiom: IdiomWithBookmark?,
    prevId: Int?,
    nextId: Int?,
    getPrevId: (Long) -> Unit,
    getNextId: (Long) -> Unit,
    setCurrentId: (Int) -> Unit,
    setCollect: (Int) -> Unit,
    setUncollect: (Int) -> Unit,
) {

    idiom?.let {

        LaunchedEffect(idiom) {
            getPrevId(idiom.collectedAt!!)
            getNextId(idiom.collectedAt)
        }

        SimpleScaffold(onBackClick = onBackClick, title = "成语") {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .draggable(
                        state = rememberDraggableState {},
                        orientation = Orientation.Horizontal,
                        onDragStarted = {},
                        onDragStopped = {
                            if (it < 0 && abs(it) > 500f) {
                                nextId?.let {
                                    setCurrentId(nextId)
                                }
                            } else if (it > 0 && abs(it) > 500f) {
                                prevId?.let {
                                    setCurrentId(prevId)
                                }
                            }
                        }
                    )
            ) {
                IdiomShowPanel(idiom = idiom)
                BottomActionBar(
                    modifier = modifier.align(Alignment.BottomCenter),
                    id = idiom.id,
                    prevId = prevId,
                    nextId = nextId,
                    setCurrentId = setCurrentId,
                    setUncollect = setUncollect,
                    setCollect = setCollect,
                    collectedAt = it.collectedAt
                )
            }
        }
    }

}