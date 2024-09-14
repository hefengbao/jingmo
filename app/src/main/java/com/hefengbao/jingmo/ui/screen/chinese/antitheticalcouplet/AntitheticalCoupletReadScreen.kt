/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.antitheticalcouplet

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.entity.chinese.AntitheticalCoupletCollectionEntity
import com.hefengbao.jingmo.data.database.entity.chinese.AntitheticalCoupletEntity
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import com.hefengbao.jingmo.ui.screen.chinese.antitheticalcouplet.components.AntitheticalCoupletPanel
import kotlin.math.abs

@Composable
fun AntitheticalCoupletReadRoute(
    viewModel: AntitheticalCoupletReadViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit
) {
    val antitheticalCouplet by viewModel.antitheticalCouplet.collectAsState()
    val antitheticalCoupletCollection by viewModel.antitheticalCoupletCollection.collectAsState()
    val prevId by viewModel.prevId.collectAsState()
    val nextId by viewModel.nextId.collectAsState()

    AntitheticalCoupletReadScreen(
        onBackClick = onBackClick,
        onCaptureClick = onCaptureClick,
        antitheticalCouplet = antitheticalCouplet,
        antitheticalCoupletCollection = antitheticalCoupletCollection,
        prevId = prevId,
        nextId = nextId,
        setCurrentId = { viewModel.setCurrentId(it) },
        setCollect = { viewModel.setCollect(it) },
        setUncollect = { viewModel.setUncollect(it) },
    )
}

@Composable
private fun AntitheticalCoupletReadScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
    antitheticalCouplet: AntitheticalCoupletEntity?,
    antitheticalCoupletCollection: AntitheticalCoupletCollectionEntity?,
    prevId: Int?,
    nextId: Int?,
    setCurrentId: (Int) -> Unit,
    setCollect: (Int) -> Unit,
    setUncollect: (Int) -> Unit,
) {
    antitheticalCouplet?.let { entity ->
        SimpleScaffold(
            onBackClick = onBackClick,
            title = "对联",
            actions = {
                IconButton(onClick = { onCaptureClick(entity.id) }) {
                    Icon(imageVector = Icons.Default.Photo, contentDescription = null)
                }
            },
            bottomBar = {
                BottomAppBar {
                    androidx.compose.foundation.layout.Row(
                        modifier = modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        IconButton(
                            onClick = { setCurrentId(prevId!!) },
                            enabled = prevId != null
                        ) {
                            Icon(
                                modifier = modifier.padding(8.dp),
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = null
                            )
                        }
                        IconButton(
                            onClick = {
                                if (antitheticalCoupletCollection != null) {
                                    setUncollect(entity.id)
                                } else {
                                    setCollect(entity.id)
                                }
                            }
                        ) {
                            if (antitheticalCoupletCollection != null) {
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
                        IconButton(
                            modifier = modifier.padding(8.dp),
                            onClick = { setCurrentId(nextId!!) },
                            enabled = nextId != null
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = null
                            )
                        }
                    }
                }
            }
        ) {
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
                AntitheticalCoupletPanel(antitheticalCouplet = antitheticalCouplet)
            }
        }
    }
}