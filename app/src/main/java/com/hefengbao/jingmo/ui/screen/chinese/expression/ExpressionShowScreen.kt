/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.expression

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.R
import com.hefengbao.jingmo.data.database.entity.BookmarkEntity
import com.hefengbao.jingmo.data.database.entity.chinese.ExpressionEntity
import com.hefengbao.jingmo.data.enums.Category
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import com.hefengbao.jingmo.ui.screen.chinese.expression.components.ExpressionPanel

@Composable
fun ChineseExpressionShowRoute(
    onBackClick: () -> Unit,
    viewModel: ExpressionShowViewModel = hiltViewModel()
) {
    val expressionEntity by viewModel.expressionEntity.collectAsState()
    val bookmarkEntity by viewModel.bookmarkEntity.collectAsState(null)

    ChineseExpressionShowScreen(
        onBackClick = onBackClick,
        expressionEntity = expressionEntity,
        bookmarkEntity = bookmarkEntity,
        addBookmark = { viewModel.cancelBookmark(it, Category.ChineseExpression.model) },
        cancelBookmark = { viewModel.addBookmark(it, Category.ChineseExpression.model) },
        isBookmarked = { viewModel.isBookmarked(it, Category.ChineseExpression.model) }
    )
}

@Composable
private fun ChineseExpressionShowScreen(
    onBackClick: () -> Unit,
    expressionEntity: ExpressionEntity?,
    bookmarkEntity: BookmarkEntity?,
    addBookmark: (Int) -> Unit,
    cancelBookmark: (Int) -> Unit,
    isBookmarked: (Int) -> Unit,
) {
    expressionEntity?.let { entity ->
        LaunchedEffect(entity) {
            isBookmarked(entity.id)
        }
        SimpleScaffold(
            onBackClick = onBackClick, title = stringResource(R.string.chinese_expression),
            bottomBar = {
                BottomAppBar(
                    actions = {
                        Row(
                            modifier = Modifier.padding(horizontal = 16.dp)
                        ) {
                            IconButton(
                                onClick = {
                                    if (bookmarkEntity != null) {
                                        addBookmark(entity.id)
                                    } else {
                                        cancelBookmark(entity.id)
                                    }
                                }
                            ) {
                                if (bookmarkEntity != null) {
                                    Icon(
                                        imageVector = Icons.Default.Bookmark,
                                        contentDescription = stringResource(R.string.cancel_bookmark),
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                } else {
                                    Icon(
                                        imageVector = Icons.Default.BookmarkBorder,
                                        contentDescription = stringResource(R.string.add_bookmark)
                                    )
                                }
                            }
                        }
                    }
                )
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ExpressionPanel(entity = entity)
            }
        }
    }
}