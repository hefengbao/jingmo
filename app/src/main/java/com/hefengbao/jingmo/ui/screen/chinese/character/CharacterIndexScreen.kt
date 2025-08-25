/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.character

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Gesture
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import kotlin.math.abs

@Composable
fun ChineseCharacterIndexRoute(
    viewModel: CharacterIndexViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onBookmarksClick: () -> Unit,
    onPinyinSearchClick: () -> Unit,
    onRadicalClickSearch: () -> Unit,
    onStrokeClick: () -> Unit,
    onStrokeSearchClick: () -> Unit,
    onSearch: (String, String) -> Unit,
) {
    val characterEntity by viewModel.characterEntity.collectAsState(null)
    val bookmarkEntity by viewModel.bookmarkEntity.collectAsState(null)

    ChineseCharacterIndexScreen(
        onBackClick = onBackClick,
        onBookmarksClick = onBookmarksClick,
        onPinyinSearchClick = onPinyinSearchClick,
        onRadicalClickSearch = onRadicalClickSearch,
        onStrokeClick = onStrokeClick,
        onStrokeSearchClick = onStrokeSearchClick,
        onSearch = onSearch,
        characterEntity = characterEntity,
        bookmarkEntity = bookmarkEntity,
        isBookmarked = { viewModel.isBookmarked(it, Category.ChineseCharacter.model) },
        addBookmark = { viewModel.addBookmark(it, Category.ChineseCharacter.model) },
        cancelBookmark = { viewModel.cancelBookmark(it, Category.ChineseCharacter.model) },
        onRefresh = viewModel::getRandom
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("SetJavaScriptEnabled")
@Composable
private fun ChineseCharacterIndexScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onBookmarksClick: () -> Unit,
    onPinyinSearchClick: () -> Unit,
    onRadicalClickSearch: () -> Unit,
    onStrokeClick: () -> Unit,
    onStrokeSearchClick: () -> Unit,
    onSearch: (String, String) -> Unit,
    characterEntity: CharacterEntity?,
    bookmarkEntity: BookmarkEntity?,
    isBookmarked: (Int) -> Unit,
    addBookmark: (Int) -> Unit,
    cancelBookmark: (Int) -> Unit,
    onRefresh: () -> Unit,
) {
    val context = LocalContext.current

    SimpleScaffold(
        onBackClick = onBackClick,
        title = stringResource(R.string.chinese_character),
        actions = {
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .clickable(true) { onPinyinSearchClick() }) {
                Text(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Color.Gray,
                            shape = RoundedCornerShape(
                                topStart = 16.dp,
                                topEnd = 16.dp,
                                bottomStart = 16.dp,
                                bottomEnd = 16.dp
                            )
                        )
                        .padding(8.dp),
                    text = "拼音",
                    style = MaterialTheme.typography.labelMedium
                )
            }
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .clickable(true) { onRadicalClickSearch() }) {
                Text(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Color.Gray,
                            shape = RoundedCornerShape(
                                topStart = 16.dp,
                                topEnd = 16.dp,
                                bottomStart = 16.dp,
                                bottomEnd = 16.dp
                            )
                        )
                        .padding(8.dp),
                    text = "部首",
                    style = MaterialTheme.typography.labelMedium
                )
            }
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .clickable(true) { onStrokeSearchClick() }) {
                Text(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Color.Gray,
                            shape = RoundedCornerShape(
                                topStart = 16.dp,
                                topEnd = 16.dp,
                                bottomStart = 16.dp,
                                bottomEnd = 16.dp
                            )
                        )
                        .padding(8.dp),
                    text = "笔画",
                    style = MaterialTheme.typography.labelMedium
                )
            }
            IconButton(onClick = onStrokeClick) {
                Icon(
                    imageVector = Icons.Default.Gesture,
                    contentDescription = stringResource(R.string.character_bihuayanshi)
                )
            }
            IconButton(onClick = onBookmarksClick) {
                Icon(
                    imageVector = Icons.Outlined.Bookmarks,
                    contentDescription = stringResource(R.string.add_bookmark)
                )
            }
        },
        bottomBar = {
            characterEntity?.let {

                LaunchedEffect(characterEntity) {
                    isBookmarked(characterEntity.id)
                }

                BottomAppBar(
                    actions = {
                        Row(
                            modifier = Modifier.padding(horizontal = 16.dp)
                        ) {
                            IconButton(
                                onClick = {
                                    if (bookmarkEntity != null) {
                                        cancelBookmark(characterEntity.id)
                                    } else {
                                        addBookmark(characterEntity.id)
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
        }
    ) {
        var query by remember {
            mutableStateOf("")
        }

        SelectionContainer {
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .padding(16.dp)
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
                    ),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    SearchItem(query, onQueryChange = { query = it }, onSearch = onSearch)
                }
                characterEntity?.let { entity ->
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
                                .fillMaxWidth(),
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchItem(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String, String) -> Unit
) {
    SearchBar(
        inputField = {
            SearchBarDefaults.InputField(
                query = query,
                onQueryChange = {
                    if (it.length <= 1) {
                        onQueryChange(it)
                    }
                },
                onSearch = {
                    if (query.isNotEmpty()) {
                        onSearch(query, "char")
                    }
                },
                expanded = false,
                onExpandedChange = {},
                enabled = true,
                placeholder = {
                    Text(text = "请输入汉字查询")
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(R.string.search)
                    )
                },
                trailingIcon = {
                    if (query.isNotEmpty()) {
                        IconButton(onClick = { onQueryChange("") }) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = stringResource(R.string.clear)
                            )
                        }
                    }
                },
            )
        },
        expanded = false,
        onExpandedChange = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = SearchBarDefaults.inputFieldShape,
        tonalElevation = SearchBarDefaults.TonalElevation,
        shadowElevation = SearchBarDefaults.ShadowElevation,
        windowInsets = SearchBarDefaults.windowInsets,
    ) {}
}