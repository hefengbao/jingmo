package com.hefengbao.jingmo.ui.screen.classicpoem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.entity.ClassicPoemEntity
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import kotlinx.coroutines.launch

@Composable
fun ClassicPoemIndexRoute(
    viewModel: ClassicPoemIndexViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {
    val poem by viewModel.poem.collectAsState(initial = null)

    LaunchedEffect(Unit) {
        viewModel.getRandom()
    }

    ClassicPoemIndexScreen(
        onBackClick = onBackClick,
        classicPoemEntity = poem,
        onFabClick = {
            viewModel.getRandom()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ClassicPoemIndexScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    classicPoemEntity: ClassicPoemEntity?,
    onFabClick: () -> Unit
) {
    val annotationSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showAnnotationBottomSheet by remember { mutableStateOf(false) }
    val translationSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showTranslationBottomSheet by remember { mutableStateOf(false) }
    var state = rememberLazyListState()
    var scope = rememberCoroutineScope()

    SimpleScaffold(
        onBackClick = onBackClick,
        title = "经典诗文",
        bottomBar = {
            classicPoemEntity?.let {
                BottomAppBar(
                    actions = {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            OutlinedButton(
                                onClick = {
                                    showAnnotationBottomSheet = true
                                },
                                enabled = classicPoemEntity.annotation != null
                            ) {
                                Text(text = "注")
                            }
                            OutlinedButton(
                                onClick = {
                                    showTranslationBottomSheet = true
                                },
                                enabled = classicPoemEntity.translation != null
                            ) {
                                Text(text = "译")
                            }
                        }
                    },
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = {
                                onFabClick()
                                scope.launch { state.animateScrollToItem(0) }
                            }
                        ) {
                            Icon(imageVector = Icons.Default.Refresh, contentDescription = "刷新")
                        }
                    }
                )
            }
        },
    ) {
        classicPoemEntity?.let { entity ->
            SelectionContainer {
                LazyColumn(
                    modifier = modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    state = state
                ) {
                    item {
                        Row {
                            Text(
                                text = "《${entity.collection}》",
                                color = MaterialTheme.colorScheme.primary,
                                style = MaterialTheme.typography.labelLarge
                            )
                            entity.category?.let {
                                Text(
                                    text = "#${entity.category}",
                                    color = MaterialTheme.colorScheme.primary,
                                    style = MaterialTheme.typography.labelLarge
                                )
                            }
                        }
                    }
                    item {
                        Column(
                            modifier = modifier
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                        ) {
                            Text(
                                text = entity.title,
                                style = MaterialTheme.typography.titleLarge
                            )
                            Text(
                                text = "【${entity.dynasty}】${entity.writer}",
                                style = MaterialTheme.typography.labelLarge
                            )
                            entity.preface?.let {
                                Text(
                                    text = it,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontStyle = FontStyle.Italic
                                )
                            }
                            Text(
                                text = entity.content
                            )
                        }
                    }
                    entity.creativeBackground?.let {
                        item {
                            Column(
                                modifier = modifier
                                    .fillMaxWidth(),
                                verticalArrangement = Arrangement.spacedBy(8.dp),
                            ) {
                                Text(
                                    text = "💡创作背景",
                                    style = MaterialTheme.typography.titleLarge
                                )
                                Text(text = it.replace("\n\n", "\n").replace("\n", "\n\n"))
                            }
                        }
                    }
                    entity.explain?.let {
                        item {
                            Column(
                                modifier = modifier
                                    .fillMaxWidth(),
                                verticalArrangement = Arrangement.spacedBy(8.dp),
                            ) {
                                Text(
                                    text = "📖文学赏析",
                                    style = MaterialTheme.typography.titleLarge
                                )
                                Text(text = it.replace("\n\n", "\n").replace("\n", "\n\n"))
                            }
                        }
                    }
                    entity.comment?.let {
                        item {
                            Column(
                                modifier = modifier
                                    .fillMaxWidth(),
                                verticalArrangement = Arrangement.spacedBy(8.dp),
                            ) {
                                Text(
                                    text = "🔖 名家点评",
                                    style = MaterialTheme.typography.titleLarge
                                )
                                Text(text = it.replace("\n\n", "\n").replace("\n", "\n\n"))
                            }
                        }
                    }
                    entity.writerIntroduction?.let {
                        item {
                            Column(
                                modifier = modifier
                                    .fillMaxWidth(),
                                verticalArrangement = Arrangement.spacedBy(8.dp),
                            ) {
                                Text(
                                    text = "📃 作者简介",
                                    style = MaterialTheme.typography.titleLarge
                                )
                                Text(text = it.replace("\n\n", "\n").replace("\n", "\n\n"))
                            }
                        }
                    }
                }
            }

            if (showAnnotationBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = { showAnnotationBottomSheet = false },
                    sheetState = annotationSheetState
                ) {
                    SelectionContainer {
                        LazyColumn(
                            state = rememberLazyListState(),
                            modifier = modifier.padding(32.dp, 16.dp, 32.dp, 32.dp)
                        ) {
                            entity.annotation?.let {
                                item {
                                    Text(text = it)
                                }
                            }
                        }
                    }
                }
            }
            if (showTranslationBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = { showTranslationBottomSheet = false },
                    sheetState = translationSheetState
                ) {
                    SelectionContainer {
                        LazyColumn(
                            state = rememberLazyListState(),
                            modifier = modifier.padding(32.dp, 16.dp, 32.dp, 32.dp)
                        ) {
                            entity.translation?.let {
                                item {
                                    Text(text = it)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}