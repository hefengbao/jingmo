/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.proverb

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.entity.chinese.ProverbCollectionEntity
import com.hefengbao.jingmo.data.database.entity.chinese.ProverbEntity
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import com.hefengbao.jingmo.ui.screen.chinese.proverb.components.ProverbShowPanel

@Composable
fun ProverbIndexRoute(
    viewModel: ProverbIndexViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onBookmarksClick: () -> Unit,
    onSearchClick: () -> Unit,
) {
    val proverbEntity by viewModel.proverbEntity.collectAsState(initial = null)
    val proverbCollectionEntity by viewModel.proverbCollectionEntity.collectAsState(initial = null)

    ProverbIndexScreen(
        onBackClick = onBackClick,
        onBookmarksClick = onBookmarksClick,
        onSearchClick = onSearchClick,
        proverbEntity = proverbEntity,
        proverbCollectionEntity = proverbCollectionEntity,
        setCollect = { viewModel.collect(it) },
        setUncollect = { viewModel.uncollect(it) },
        refresh = { viewModel.random() },
        isCollect = { viewModel.isCollect(it) }
    )
}

@Composable
private fun ProverbIndexScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onBookmarksClick: () -> Unit,
    onSearchClick: () -> Unit,
    proverbEntity: ProverbEntity?,
    proverbCollectionEntity: ProverbCollectionEntity?,
    setCollect: (Int) -> Unit,
    setUncollect: (Int) -> Unit,
    refresh: () -> Unit,
    isCollect: (Int) -> Unit,
) {
    SimpleScaffold(
        onBackClick = onBackClick,
        title = "谚语",
        actions = {
            IconButton(onClick = onBookmarksClick) {
                Icon(imageVector = Icons.Default.Bookmarks, contentDescription = "")
            }
            IconButton(onClick = onSearchClick) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "")
            }
        },
        bottomBar = {
            proverbEntity?.let {

                isCollect(proverbEntity.id)

                BottomAppBar(
                    actions = {
                        Row(
                            modifier = modifier.padding(horizontal = 16.dp)
                        ) {
                            if (proverbCollectionEntity != null) {
                                IconButton(onClick = { setUncollect(proverbEntity.id) }) {
                                    Icon(
                                        imageVector = Icons.Default.Bookmark,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }
                            } else {
                                IconButton(onClick = { setCollect(proverbEntity.id) }) {
                                    Icon(
                                        imageVector = Icons.Default.BookmarkBorder,
                                        contentDescription = null
                                    )
                                }
                            }
                        }
                    },
                    floatingActionButton = {
                        FloatingActionButton(onClick = refresh) {
                            Icon(imageVector = Icons.Default.Refresh, contentDescription = "")
                        }
                    }
                )
            }
        }
    ) {
        proverbEntity?.let { entity -> ProverbShowPanel(entity = entity) }
    }
}