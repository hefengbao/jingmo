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
import androidx.compose.material3.BottomAppBar
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
import com.hefengbao.jingmo.ui.screen.chinese.proverb.components.ProverbPanel

@Composable
fun ProverbShowRoute(
    viewModel: ProverbShowViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {
    val proverbEntity by viewModel.proverbEntity.collectAsState(initial = null)
    val proverbCollectionEntity by viewModel.proverbCollectionEntity.collectAsState(initial = null)

    ProverbShowScreen(
        onBackClick = onBackClick,
        proverbEntity = proverbEntity,
        proverbCollectionEntity = proverbCollectionEntity,
        setCollect = { viewModel.collect(it) },
        setUncollect = { viewModel.uncollect(it) }
    )
}

@Composable
private fun ProverbShowScreen(
    onBackClick: () -> Unit,
    proverbEntity: ProverbEntity?,
    proverbCollectionEntity: ProverbCollectionEntity?,
    setCollect: (Int) -> Unit,
    setUncollect: (Int) -> Unit,
) {
    proverbEntity?.let { entity ->
        SimpleScaffold(
            onBackClick = onBackClick,
            title = "谚语",
            bottomBar = {
                BottomAppBar {
                    Row(
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        if (proverbCollectionEntity == null) {
                            IconButton(onClick = { setCollect(entity.id) }) {
                                Icon(
                                    imageVector = Icons.Default.BookmarkBorder,
                                    contentDescription = null
                                )
                            }
                        } else {
                            IconButton(onClick = { setUncollect(entity.id) }) {
                                Icon(
                                    imageVector = Icons.Default.Bookmark,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }
                }
            }
        ) {
            ProverbPanel(entity = entity)
        }
    }
}