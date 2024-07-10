/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.expression.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hefengbao.jingmo.data.database.entity.ChineseExpressionEntity
import com.hefengbao.jingmo.ui.component.BackgroundTitle

@Composable
fun ChineseExpressionShowPanel(
    modifier: Modifier = Modifier,
    entity: ChineseExpressionEntity
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = entity.pinyin, style = MaterialTheme.typography.bodyMedium)
        Text(text = entity.word, style = MaterialTheme.typography.displaySmall)
    }
    entity.explanation?.let {
        BackgroundTitle(title = "释义")
        Text(text = it)
    }
}