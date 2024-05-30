package com.hefengbao.jingmo.ui.screen.chineseexpression

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.entity.ChineseExpressionEntity
import com.hefengbao.jingmo.ui.component.SimpleScaffold

@Composable
fun ChineseExpressionShowRoute(
    onBackClick: () -> Unit,
    viewModel: ChineseExpressionShowViewModel = hiltViewModel()
) {
    val expression by viewModel.expression.collectAsState(initial = null)

    ChineseExpressionShowScreen(onBackClick = onBackClick, expression = expression)
}

@Composable
private fun ChineseExpressionShowScreen(
    onBackClick: () -> Unit,
    expression: ChineseExpressionEntity?
) {
    SimpleScaffold(onBackClick = onBackClick, title = "词语") {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            expression?.let { entity ->
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(text = entity.pinyin, style = MaterialTheme.typography.bodyMedium)
                    Text(text = entity.word)
                }
                entity.explanation?.let {
                    Text(text = it)
                }
            }
        }
    }
}