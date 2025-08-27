/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.classicalliterature.sentence

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.outlined.Photo
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.R
import com.hefengbao.jingmo.data.database.entity.BookmarkEntity
import com.hefengbao.jingmo.data.database.entity.classicalliterature.SentenceEntity
import com.hefengbao.jingmo.data.enums.Category
import com.hefengbao.jingmo.ui.component.SimpleScaffold

@Composable
fun SentenceShowRoute(
    viewModel: SentenceShowViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
) {
    val sentenceEntity by viewModel.sentenceEntity.collectAsState(null)
    val bookmarkEntity by viewModel.bookmarkEntity.collectAsState(null)

    sentenceEntity?.let { entity ->
        LaunchedEffect(entity) {
            viewModel.isBookmarked(entity.id, Category.ClassicalLiteratureSentence.model)
        }
    }

    SentenceShowScreen(
        onBackClick = onBackClick,
        onCaptureClick = onCaptureClick,
        sentenceEntity = sentenceEntity,
        bookmarkEntity = bookmarkEntity,
        cancelBookmark = {
            viewModel.cancelBookmark(
                it,
                Category.ClassicalLiteratureSentence.model
            )
        },
        addBookmark = { viewModel.addBookmark(it, Category.ClassicalLiteratureSentence.model) },
    )
}

@Composable
private fun SentenceShowScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
    sentenceEntity: SentenceEntity?,
    bookmarkEntity: BookmarkEntity?,
    cancelBookmark: (Int) -> Unit,
    addBookmark: (Int) -> Unit,
) {
    SimpleScaffold(
        onBackClick = onBackClick,
        title = stringResource(R.string.classicalliterature_sentence),
        actions = {
            sentenceEntity?.let {
                IconButton(onClick = { onCaptureClick(sentenceEntity.id) }) {
                    Icon(
                        imageVector = Icons.Outlined.Photo,
                        contentDescription = stringResource(R.string.capture)
                    )
                }
            }
        },
        bottomBar = {
            BottomAppBar(
                actions = {
                    Row(
                        modifier = modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = {
                                sentenceEntity?.let {
                                    if (bookmarkEntity != null) {
                                        cancelBookmark(it.id)
                                    } else {
                                        addBookmark(it.id)
                                    }
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
                },
            )
        }
    ) {
        sentenceEntity?.let {
            Row(
                modifier = modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    sentenceEntity.content.split("，", "。", "？", "！", "、").map {

                        Column {
                            it.toCharArray().map { char ->
                                Text(
                                    text = char.toString(),
                                    style = TextStyle.Default.copy(fontSize = 24.sp)
                                )
                            }
                        }
                    }
                }
                Column {
                    sentenceEntity.from.replace("《", "﹁")
                        .replace("》", "﹂")
                        .toCharArray()
                        .map { text ->
                            Text(text = text.toString())
                        }
                }
            }
        }
    }
}