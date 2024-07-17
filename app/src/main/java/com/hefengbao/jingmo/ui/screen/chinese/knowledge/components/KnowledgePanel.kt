package com.hefengbao.jingmo.ui.screen.chinese.knowledge.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Link
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import com.hefengbao.jingmo.data.database.entity.chinese.KnowledgeEntity

@Composable
fun KnowledgePanel(
    modifier: Modifier = Modifier,
    entity: KnowledgeEntity
) {
    val uriHandler = LocalUriHandler.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                SelectionContainer {
                    Text(
                        text = entity.content,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                Row(
                    modifier = modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = if (entity.url != null) Arrangement.SpaceBetween else Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "#${entity.label}",
                        color = MaterialTheme.colorScheme.primary,
                    )
                    entity.url?.let { url ->
                        IconButton(onClick = {
                            uriHandler.openUri(url)
                        }) {
                            Icon(
                                imageVector = Icons.Default.Link,
                                contentDescription = null
                            )
                        }
                    }
                }
            }
        }
    }
}