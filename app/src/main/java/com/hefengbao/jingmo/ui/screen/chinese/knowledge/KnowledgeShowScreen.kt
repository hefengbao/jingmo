/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.knowledge

import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.animateFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.R
import com.hefengbao.jingmo.data.database.entity.BookmarkEntity
import com.hefengbao.jingmo.data.database.entity.chinese.KnowledgeEntity
import com.hefengbao.jingmo.data.enums.Category
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import com.hefengbao.jingmo.ui.screen.chinese.knowledge.components.KnowledgePanel

@Composable
fun KnowledgeShowRoute(
    viewModel: KnowledgeShowViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {
    val knowledgeEntity by viewModel.knowledgeEntity.collectAsState(initial = null)
    val bookmarkEntity by viewModel.bookmarkEntity.collectAsState(initial = null)

    LyricShowScreen(
        onBackClick = onBackClick,
        knowledgeEntity = knowledgeEntity,
        bookmarkEntity = bookmarkEntity,
        addBookmark = { viewModel.addBookmark(it, Category.ChineseKnowledge.model) },
        cancelBookmark = { viewModel.cancelBookmark(it, Category.ChineseKnowledge.model) },
        isBookmarked = { viewModel.isBookmarked(it, Category.ChineseKnowledge.model) }
    )
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun LyricShowScreen(
    onBackClick: () -> Unit,
    knowledgeEntity: KnowledgeEntity?,
    bookmarkEntity: BookmarkEntity?,
    addBookmark: (Int) -> Unit,
    cancelBookmark: (Int) -> Unit,
    isBookmarked: (Int) -> Unit
) {
    val state = rememberScrollState()

    val fabVisible by remember { derivedStateOf { state.value != state.maxValue } }

    knowledgeEntity?.let { entity ->

        LaunchedEffect(entity) { isBookmarked(entity.id) }
        SimpleScaffold(
            onBackClick = onBackClick,
            title = stringResource(R.string.chinese_knowledge),
            floatingActionButton = {
                FloatingActionButton(
                    modifier = Modifier.animateFloatingActionButton(
                        visible = fabVisible,
                        alignment = Alignment.BottomEnd
                    ),
                    onClick = {
                        if (bookmarkEntity == null) {
                            addBookmark(entity.id)
                        } else {
                            cancelBookmark(entity.id)
                        }
                    }
                ) {
                    if (bookmarkEntity == null) {
                        Icon(
                            imageVector = Icons.Default.BookmarkBorder,
                            contentDescription = stringResource(R.string.cancel_bookmark)
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.Bookmark,
                            contentDescription = stringResource(R.string.add_bookmark),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        ) {
            KnowledgePanel(entity = entity, state = state)
        }
    }
}