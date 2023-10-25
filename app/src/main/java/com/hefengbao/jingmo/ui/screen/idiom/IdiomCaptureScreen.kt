package com.hefengbao.jingmo.ui.screen.idiom

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.entity.PoemEntity
import com.hefengbao.jingmo.data.model.ChineseColor
import com.hefengbao.jingmo.ui.component.CaptureScaffold

@Composable
fun IdiomCaptureRoute(
    viewModel: IdiomCaptureViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val poem by viewModel.poem.collectAsState(initial = null)
    val chineseColors by viewModel.chineseColors.collectAsState(initial = emptyList())
    val dataStatus = viewModel.dataStatus

    LaunchedEffect(Unit) {
        viewModel.getColors()
    }
    PoemCaptureScreen(
        onBackClick = onBackClick,
        poem = poem,
        defaultColor = if (dataStatus.captureColor == "white") Color.White else Color.Black,
        onColorChange = { viewModel.setCaptureColor(if (it == Color.White) "white" else "black") },
        defaultBackgroundColor = dataStatus.captureBackgroundColor,
        onBackgroundColorChange = { viewModel.setCaptureBackgroundColor(it) },
        colors = chineseColors
    )
}

@Composable
fun PoemCaptureScreen(
    onBackClick: () -> Unit,
    defaultColor: Color,
    onColorChange: (Color) -> Unit,
    defaultBackgroundColor: String,
    onBackgroundColorChange: (String) -> Unit,
    poem: PoemEntity?,
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
        poem?.let { entity ->
            Text(
                text = entity.title,
                style = MaterialTheme.typography.titleLarge,
                color = color
            )
            Text(
                text = "【${entity.dynasty}】${entity.writerName}",
                style = MaterialTheme.typography.labelMedium,
                color = color
            )
            Text(
                text = entity.content,
                style = MaterialTheme.typography.bodyLarge,
                color = color
            )
        }
    }
}