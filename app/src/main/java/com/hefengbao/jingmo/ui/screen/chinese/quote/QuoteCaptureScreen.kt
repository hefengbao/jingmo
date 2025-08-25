package com.hefengbao.jingmo.ui.screen.chinese.quote

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.entity.chinese.QuoteEntity
import com.hefengbao.jingmo.data.model.AppStatus
import com.hefengbao.jingmo.data.model.traditionalculture.ChineseColor
import com.hefengbao.jingmo.ui.component.CaptureScaffold

@Composable
fun QuoteCaptureRoute(
    viewModel: QuoteCaptureViewModel = hiltViewModel<QuoteCaptureViewModel>(),
    onBackClick: () -> Unit,
) {
    val colors by viewModel.colors.collectAsState(emptyList())
    val appStatus by viewModel.appStatus.collectAsState(null)
    val quoteEntity by viewModel.quoteEntity.collectAsState(null)

    QuoteCaptureScreen(
        onBackClick = onBackClick,
        appStatus = appStatus,
        colors = colors,
        quoteEntity = quoteEntity,
        onTextColorChange = viewModel::setCaptureTextColor,
        onBackgroundColorChange = viewModel::setCaptureBackgroundColor
    )
}

@Composable
private fun QuoteCaptureScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    appStatus: AppStatus?,
    colors: List<ChineseColor>,
    quoteEntity: QuoteEntity?,
    onTextColorChange: (String) -> Unit,
    onBackgroundColorChange: (String) -> Unit,
) {
    appStatus?.let {
        CaptureScaffold(
            modifier = modifier,
            onBackClick = onBackClick,
            colors = colors,
            onTextColorChange = onTextColorChange,
            onBackgroundColorChange = onBackgroundColorChange,
        ) {
            val tColor = appStatus.captureTextColor

            quoteEntity?.let { entity ->
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .background(appStatus.captureBackgroundColor)
                        .padding(16.dp, 32.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        modifier = modifier.fillMaxWidth(),
                        text = entity.content,
                        color = tColor
                    )
                    Text(
                        text = "${entity.author}·${entity.from}",
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        color = tColor
                    )
                }
            }
        }
    }
}