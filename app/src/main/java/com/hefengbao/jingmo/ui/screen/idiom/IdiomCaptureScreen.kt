package com.hefengbao.jingmo.ui.screen.idiom

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.model.IdiomWithBookmark
import com.hefengbao.jingmo.data.model.ChineseColor
import com.hefengbao.jingmo.ui.component.CaptureScaffold

@Composable
fun IdiomCaptureRoute(
    viewModel: IdiomCaptureViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val idiom by viewModel.idiom.collectAsState(initial = null)
    val chineseColors by viewModel.chineseColors.collectAsState(initial = emptyList())
    val dataStatus = viewModel.appStatus

    LaunchedEffect(Unit) {
        viewModel.getColors()
    }

    IdiomCaptureScreen(
        onBackClick = onBackClick,
        idiom = idiom,
        defaultColor = if (dataStatus.captureTextColor == "white") Color.White else Color.Black,
        onColorChange = { viewModel.setCaptureColor(if (it == Color.White) "white" else "black") },
        defaultBackgroundColor = dataStatus.captureBackgroundColor,
        onBackgroundColorChange = { viewModel.setCaptureBackgroundColor(it) },
        colors = chineseColors
    )
}

@Composable
private fun IdiomCaptureScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    defaultColor: Color,
    onColorChange: (Color) -> Unit,
    defaultBackgroundColor: String,
    onBackgroundColorChange: (String) -> Unit,
    idiom: IdiomWithBookmark?,
    colors: List<ChineseColor>
) {
    CaptureScaffold(
        colors = colors,
        onBackClick = onBackClick,
        defaultColor = defaultColor,
        onColorChange = onColorChange,
        defaultBackgroundColor = defaultBackgroundColor,
        onBackgroundColorChange = onBackgroundColorChange
    ) { color, _ ->
        idiom?.let { entity ->
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(24.dp, 48.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = idiom.pinyin,
                        style = MaterialTheme.typography.bodyMedium,
                        color = color
                    )
                    Text(
                        text = entity.word,
                        style = MaterialTheme.typography.bodyLarge,
                        color = color
                    )
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "释义",
                        style = MaterialTheme.typography.titleMedium,
                        color = color
                    )
                    Text(
                        text = idiom.explanation,
                        style = MaterialTheme.typography.bodyLarge,
                        color = color
                    )
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "示例",
                        style = MaterialTheme.typography.titleMedium,
                        color = color
                    )
                    Text(
                        text = idiom.example,
                        style = MaterialTheme.typography.bodyLarge,
                        color = color
                    )
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "出处",
                        style = MaterialTheme.typography.titleMedium,
                        color = color
                    )
                    Text(
                        text = idiom.derivation,
                        style = MaterialTheme.typography.bodyLarge,
                        color = color
                    )
                }
            }
        }
    }
}