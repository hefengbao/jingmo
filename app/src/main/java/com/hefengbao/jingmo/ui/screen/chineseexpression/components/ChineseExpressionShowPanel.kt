package com.hefengbao.jingmo.ui.screen.chineseexpression.components

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