package com.hefengbao.jingmo.ui.screen.idiom

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.entity.IdiomEntity
import com.hefengbao.jingmo.ui.component.SimpleScaffold

@Composable
fun IdiomRoute(
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
    viewModel: IdiomViewModel = hiltViewModel()
) {
    val idiom by viewModel.idiom.collectAsState(initial = null)

    IdiomScreen(
        onBackClick = onBackClick,
        onCaptureClick = onCaptureClick,
        idiom = idiom,
    )
}

@Composable
private fun IdiomScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
    idiom: IdiomEntity?,
) {

    idiom?.let { entity ->
        SimpleScaffold(
            onBackClick = onBackClick,
            title = entity.word,
            actions = {
                IconButton(onClick = { onCaptureClick(entity.id) }) {
                    Icon(imageVector = Icons.Default.Photo, contentDescription = null)
                }
            }
        ) {
            SelectionContainer {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = idiom.pinyin,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = entity.word,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "\uD83D\uDD3B 释义",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = idiom.explanation,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }

                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "\uD83D\uDD3B 示例",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = idiom.example,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }

                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "📖 出处",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = idiom.derivation,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
}