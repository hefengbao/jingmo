/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.antitheticalcouplet.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hefengbao.jingmo.data.database.entity.chinese.AntitheticalCoupletEntity
import com.hefengbao.jingmo.ui.component.BackgroundTitle

@Composable
fun AntitheticalCoupletPanel(
    modifier: Modifier = Modifier,
    antitheticalCouplet: AntitheticalCoupletEntity,
) {
    SelectionContainer {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = antitheticalCouplet.body,
            )
            antitheticalCouplet.description?.let {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    BackgroundTitle(title = "说明")
                    Text(
                        text = it,
                    )
                }
            }
        }
    }
}