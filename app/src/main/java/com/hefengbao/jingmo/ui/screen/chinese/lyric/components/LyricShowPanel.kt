/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.lyric.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hefengbao.jingmo.data.database.entity.chinese.LyricEntity

@Composable
fun LyricShowPanel(
    modifier: Modifier = Modifier,
    entity: LyricEntity
) {
    SelectionContainer {
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    modifier = modifier.fillMaxWidth(),
                    text = entity.title,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center
                )
            }
            item {
                var text = ""
                if (entity.writer != null) {
                    text += "填词：${entity.writer}"
                }
                if (entity.singer != null) {
                    if (text.isNotEmpty()) {
                        text += " / "
                    }
                    text += "演唱：${entity.singer}"
                }
                Text(
                    modifier = modifier.fillMaxWidth(),
                    text = text,
                    style = MaterialTheme.typography.labelMedium.copy(brush = null, alpha = .5f),
                    textAlign = TextAlign.Center
                )
            }
            item {
                Text(
                    modifier = modifier.fillMaxWidth(),
                    text = entity.content,
                )
            }
        }
    }
}