package com.hefengbao.jingmo.ui.screen.idiom.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hefengbao.jingmo.data.database.entity.IdiomEntity

@Composable
fun IdiomShowPanel(
    modifier: Modifier = Modifier,
    idiom: IdiomEntity
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
                    text = idiom.word,
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