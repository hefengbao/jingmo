package com.hefengbao.jingmo.ui.screen.poem

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.model.WritingWithCollection
import com.hefengbao.jingmo.data.model.ChineseColor
import com.hefengbao.jingmo.ui.component.CaptureScaffold

@Composable
fun PoemCaptureRoute(
    viewModel: PoemCaptureViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val poem by viewModel.poem.collectAsState(initial = null)
    val chineseColors by viewModel.chineseColors.collectAsState(initial = emptyList())
    val dataStatus = viewModel.appStatus

    LaunchedEffect(Unit) {
        viewModel.getColors()
    }
    PoemCaptureScreen(
        onBackClick = onBackClick,
        poem = poem,
        defaultColor = if (dataStatus.captureTextColor == "white") Color.White else Color.Black,
        onColorChange = { viewModel.setCaptureColor(if (it == Color.White) "white" else "black") },
        defaultBackgroundColor = dataStatus.captureBackgroundColor,
        onBackgroundColorChange = { viewModel.setCaptureBackgroundColor(it) },
        colors = chineseColors
    )
}

@Composable
private fun PoemCaptureScreen(
    onBackClick: () -> Unit,
    defaultColor: Color,
    onColorChange: (Color) -> Unit,
    defaultBackgroundColor: String,
    onBackgroundColorChange: (String) -> Unit,
    poem: WritingWithCollection?,
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
            val content = buildString {
                entity.clauses.mapIndexed { _, clause ->
                    append(clause.content)

                    if (clause.breakAfter != null) {
                        append("\n")
                    }
                }
            }
            Text(
                text = entity.title.content,
                style = MaterialTheme.typography.titleLarge,
                color = color
            )
            Text(
                text = "${entity.dynasty}.${entity.author}",
                style = MaterialTheme.typography.titleSmall,
                color = color
            )
            Text(
                text = content,
                style = MaterialTheme.typography.bodyLarge,
                color = color
            )
        }
    }
}