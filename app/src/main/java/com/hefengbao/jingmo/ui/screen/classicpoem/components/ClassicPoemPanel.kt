package com.hefengbao.jingmo.ui.screen.classicpoem.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hefengbao.jingmo.data.database.entity.ClassicPoemEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClassicPoemPanel(
    modifier: Modifier = Modifier,
    entity: ClassicPoemEntity,
    state: LazyListState,
    showAnnotationBottomSheet: Boolean,
    annotationSheetState: SheetState,
    onAnnotationBottomSheetDismiss: () -> Unit,
    showTranslationBottomSheet: Boolean,
    translationSheetState: SheetState,
    onTranslationBottomSheetDismiss: () -> Unit,
    showPoemBottomSheet: Boolean,
    poemSheetState: SheetState,
    onPoemBottomSheetDismiss: () -> Unit,
) {
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
            onDismissRequest = onAnnotationBottomSheetDismiss,
            sheetState = annotationSheetState
        ) {
            SelectionContainer {
                Column(
                    modifier = modifier
                        .padding(32.dp, 16.dp, 32.dp, 32.dp)
                        .verticalScroll(
                            rememberScrollState()
                        )
                ) {
                    entity.annotation?.let {
                        Text(text = it)
                    }
                }
            }
        }
    }
    if (showTranslationBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = onTranslationBottomSheetDismiss,
            sheetState = translationSheetState
        ) {
            SelectionContainer {
                Column(
                    modifier = modifier
                        .padding(32.dp, 16.dp, 32.dp, 32.dp)
                        .verticalScroll(
                            rememberScrollState()
                        )
                ) {
                    entity.translation?.let {
                        Text(text = it)
                    }
                }
            }
        }
    }
    if (showPoemBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = onPoemBottomSheetDismiss,
            sheetState = poemSheetState
        ) {
            SelectionContainer {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(32.dp, 16.dp, 32.dp, 32.dp)
                        .verticalScroll(
                            rememberScrollState()
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = entity.content,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}