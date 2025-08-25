/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.poetry.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hefengbao.jingmo.data.database.entity.chinese.ModernPoetryEntity
import com.hefengbao.jingmo.ui.component.EmphasizedTitle

@Composable
internal fun ModernPoetryPanel(
    modifier: Modifier = Modifier,
    entity: ModernPoetryEntity,
    state: ScrollState
) {
    SelectionContainer {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .verticalScroll(state)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = entity.title,
                )
                Text(
                    text = entity.author,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall,
                )
                Text(
                    text = entity.content,
                )
            }

            entity.zhu?.let {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    EmphasizedTitle("注释")
                    Text(
                        text = it
                    )
                }
            }

            entity.yi?.let {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    EmphasizedTitle("翻译")
                    Text(
                        text = it
                    )
                }
            }

            entity.shang?.let {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    EmphasizedTitle("赏析")
                    Text(
                        text = it
                    )
                }
            }

            entity.authorInfo?.let {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    EmphasizedTitle("作者简介")
                    Text(
                        text = it
                    )
                }
            }
        }
    }
}