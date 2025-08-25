/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.riddle

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.R
import com.hefengbao.jingmo.data.database.entity.chinese.RiddleEntity
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import kotlin.math.abs

@Composable
fun RiddleReadRoute(
    viewModel: RiddleReadViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onInfoClick: () -> Unit,
) {

    val riddleEntity by viewModel.riddleEntity.collectAsState()
    val nextId by viewModel.nextId.collectAsState()
    val prevId by viewModel.prevId.collectAsState()

    RiddleReadScreen(
        onBackClick = onBackClick,
        onInfoClick = onInfoClick,
        riddleEntity = riddleEntity,
        setCurrentId = { viewModel.setCurrentId(it) },
        prevId = prevId,
        nextId = nextId
    )
}

@Composable
private fun RiddleReadScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onInfoClick: () -> Unit,
    riddleEntity: RiddleEntity?,
    setCurrentId: (Int) -> Unit,
    prevId: Int?,
    nextId: Int?
) {
    var showAnswer by remember { mutableStateOf(false) }

    SimpleScaffold(
        onBackClick = onBackClick,
        title = stringResource(R.string.chinese_riddle),
        actions = {
            IconButton(onClick = onInfoClick) {
                Icon(imageVector = Icons.Outlined.Info, contentDescription = "点击查看谜语知识")
            }
        },
        bottomBar = {
            BottomAppBar {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(
                        onClick = { prevId?.let(setCurrentId) },
                        enabled = prevId != null,
                        modifier = modifier.padding(16.dp),
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.previous)
                        )
                    }

                    IconButton(
                        onClick = { showAnswer = !showAnswer },
                        modifier = modifier.padding(16.dp),
                    ) {
                        Icon(
                            imageVector = if (showAnswer) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff,
                            contentDescription = if (showAnswer) "显示谜底" else "隐藏谜底"
                        )
                    }

                    IconButton(
                        onClick = { nextId?.let(setCurrentId) },
                        enabled = nextId != null,
                        modifier = modifier.padding(16.dp),
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = stringResource(R.string.next)
                        )
                    }
                }
            }
        }
    ) {
        riddleEntity?.let { entity ->
            LaunchedEffect(entity) {
                showAnswer = false // 默认隐藏答案
            }

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
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(text = entity.puzzle)
                        if (showAnswer) {
                            Text(text = entity.answer)
                        }
                    }
                }
            }
        }
    }
}