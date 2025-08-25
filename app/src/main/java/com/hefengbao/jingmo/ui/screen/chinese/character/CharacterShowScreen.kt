/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.character

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.R
import com.hefengbao.jingmo.common.util.ClipboardUtil
import com.hefengbao.jingmo.data.database.entity.BookmarkEntity
import com.hefengbao.jingmo.data.database.entity.chinese.CharacterEntity
import com.hefengbao.jingmo.data.enums.Category
import com.hefengbao.jingmo.ui.component.EmphasizedTitle
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import com.hefengbao.jingmo.ui.screen.chinese.character.components.StyledText

@Composable
fun CharacterShowRoute(
    viewModel: CharacterShowViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {
    val characterEntity by viewModel.characterEntity.collectAsState()
    val bookmarkEntity by viewModel.bookmarkEntity.collectAsState(null)
    val context = LocalContext.current

    CharacterShowScreen(
        context = context,
        onBackClick = onBackClick,
        characterEntity = characterEntity,
        bookmarkEntity = bookmarkEntity,
        cancelBookmark = { viewModel.cancelBookmark(it, Category.ChineseCharacter.model) },
        addBookmark = { viewModel.addBookmark(it, Category.ChineseCharacter.model) },
        isBookmarked = { viewModel.isBookmarked(it, Category.ChineseCharacter.model) },
    )
}

@Composable
private fun CharacterShowScreen(
    modifier: Modifier = Modifier,
    context: Context,
    onBackClick: () -> Unit,
    characterEntity: CharacterEntity?,
    bookmarkEntity: BookmarkEntity?,
    cancelBookmark: (Int) -> Unit,
    addBookmark: (Int) -> Unit,
    isBookmarked: (Int) -> Unit,
) {
    characterEntity?.let { entity ->
        LaunchedEffect(entity) { isBookmarked(entity.id) }
        SimpleScaffold(
            onBackClick = onBackClick,
            title = entity.character,
            bottomBar = {
                BottomAppBar {
                    Row(
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        IconButton(
                            onClick = {
                                if (bookmarkEntity != null) {
                                    cancelBookmark(entity.id)
                                } else {
                                    addBookmark(entity.id)
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
            }
        ) {
            SelectionContainer {
                LazyColumn(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = entity.character,
                                style = MaterialTheme.typography.displayLarge
                            )
                            IconButton(
                                onClick = {
                                    ClipboardUtil.textCopyThenPost(context, entity.character)
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ContentCopy,
                                    contentDescription = stringResource(R.string.copy)
                                )
                            }
                        }
                    }
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            entity.pinyin?.let {
                                Text(text = "拼音：$it")
                            }
                            entity.stroke?.let {
                                Text(text = "笔画：$it")
                            }
                            entity.radical?.let {
                                Text(text = "部首：$it")
                            }
                            entity.wubi?.let {
                                Text(text = "五笔：$it")
                            }
                        }
                    }
                    entity.explanations?.let {
                        if (entity.explanations.isNotEmpty()) {
                            item {
                                EmphasizedTitle(title = "简要释义")
                            }
                            items(entity.explanations) {
                                Text(it)
                            }
                        }
                    }
                    entity.explanations2?.let {
                        if (entity.explanations2.isNotEmpty()) {
                            item {
                                EmphasizedTitle(title = "详细释义")
                            }
                            itemsIndexed(entity.explanations2) { index, item ->
                                var text = "（${index + 1}）"
                                item.speech?.let {
                                    text += "【$it】"
                                }
                                item.content?.let {
                                    text += it
                                }
                                Text(text)
                                Column(
                                    modifier = Modifier.padding(vertical = 8.dp),
                                    verticalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    item.refer?.let {
                                        StyledText("参考", it)
                                    }
                                    item.detail?.let { details ->
                                        details.forEach { detail ->
                                            StyledText("引", "${detail.text} ——${detail.book}")
                                        }
                                    }
                                    item.words?.let { words ->
                                        words.forEach { word ->
                                            StyledText("词", "${word.word}：${word.text}")
                                        }
                                    }
                                    item.same?.let {
                                        StyledText("同", it)
                                    }
                                    item.example?.let {
                                        StyledText("例", it)
                                    }
                                    item.simplified?.let {
                                        StyledText("简体", it)
                                    }
                                    item.variant?.let {
                                        StyledText("异体", it)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}