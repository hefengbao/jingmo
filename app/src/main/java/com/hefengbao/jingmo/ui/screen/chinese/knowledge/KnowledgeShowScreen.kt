/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.knowledge

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.entity.chinese.KnowledgeCollectionEntity
import com.hefengbao.jingmo.data.database.entity.chinese.KnowledgeEntity
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import com.hefengbao.jingmo.ui.screen.chinese.knowledge.components.KnowledgeShowPanel

@Composable
fun KnowledgeShowRoute(
    viewModel: KnowledgeShowViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {
    val knowledgeEntity by viewModel.knowledgeEntity.collectAsState(initial = null)
    val knowledgeCollectionEntity by viewModel.knowledgeCollectionEntity.collectAsState(initial = null)

    LyricShowScreen(
        onBackClick = onBackClick,
        knowledgeEntity = knowledgeEntity,
        knowledgeCollectionEntity = knowledgeCollectionEntity,
        setCollect = { viewModel.collect(it) },
        setUncollect = { viewModel.uncollect(it) }
    )
}

@Composable
private fun LyricShowScreen(
    onBackClick: () -> Unit,
    knowledgeEntity: KnowledgeEntity?,
    knowledgeCollectionEntity: KnowledgeCollectionEntity?,
    setCollect: (Int) -> Unit,
    setUncollect: (Int) -> Unit,
) {
    knowledgeEntity?.let { entity ->
        SimpleScaffold(
            onBackClick = onBackClick,
            title = "知识卡片",
            floatingActionButton = {
                if (knowledgeCollectionEntity == null) {
                    FloatingActionButton(onClick = { setCollect(entity.id) }) {
                        Icon(
                            imageVector = Icons.Default.BookmarkBorder,
                            contentDescription = null
                        )
                    }
                } else {
                    FloatingActionButton(onClick = { setUncollect(entity.id) }) {
                        Icon(
                            imageVector = Icons.Default.Bookmark,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        ) {
            KnowledgeShowPanel(entity = entity)
        }
    }
}