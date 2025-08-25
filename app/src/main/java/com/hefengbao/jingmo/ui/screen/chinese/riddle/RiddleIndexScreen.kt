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
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ReadMore
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
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
fun RiddleIndexRoute(
    viewModel: RiddleIndexViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onInfoClick: () -> Unit,
    onSearchClick: () -> Unit,
    onReadMoreClick: () -> Unit,
) {

    val riddleEntity by viewModel.riddleEntity.collectAsState(null)

    RiddleIndexScreen(
        onBackClick = onBackClick,
        onInfoClick = onInfoClick,
        onSearchClick = onSearchClick,
        onReadMoreClick = onReadMoreClick,
        riddleEntity = riddleEntity,
        onRefresh = { viewModel.getRandom() }
    )
}

@Composable
private fun RiddleIndexScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onInfoClick: () -> Unit,
    onReadMoreClick: () -> Unit,
    onSearchClick: () -> Unit,
    riddleEntity: RiddleEntity?,
    onRefresh: () -> Unit,
) {
    var showAnswer by remember { mutableStateOf(false) }

    SimpleScaffold(
        onBackClick = onBackClick,
        title = stringResource(R.string.chinese_riddle),
        actions = {
            IconButton(onClick = onReadMoreClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ReadMore,
                    contentDescription = stringResource(R.string.read_more)
                )
            }
            IconButton(onClick = onSearchClick) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(R.string.search)
                )
            }
            IconButton(onClick = onInfoClick) {
                Icon(imageVector = Icons.Outlined.Info, contentDescription = "点击查看谜语知识")
            }
        },
        bottomBar = {
            BottomAppBar(
                actions = {
                    Row(
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        IconButton(
                            onClick = { showAnswer = !showAnswer },
                        ) {
                            Icon(
                                imageVector = if (showAnswer) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff,
                                contentDescription = if (showAnswer) "显示谜底" else "隐藏谜底"
                            )
                        }

                    }
                },
                floatingActionButton = {
                    FloatingActionButton(onClick = onRefresh) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = stringResource(R.string.refresh)
                        )
                    }
                }
            )
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
                                onRefresh()
                            } else if (it > 0 && abs(it) > 500f) {
                                onRefresh()
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