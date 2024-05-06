package com.hefengbao.jingmo.ui.screen.poemsentence

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.entity.PoemSentenceEntity
import com.hefengbao.jingmo.data.model.ChineseColor
import com.hefengbao.jingmo.ui.component.CaptureScaffold

@Composable
fun PoemSentenceCaptureRoute(
    viewModel: PoemSentenceCaptureViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val poemSentence by viewModel.poemSentence.collectAsState(initial = null)
    val chineseColors by viewModel.chineseColors.collectAsState(initial = emptyList())
    val dataStatus = viewModel.appStatus

    LaunchedEffect(Unit) {
        viewModel.getColors()
    }
    PoemSentenceCaptureScreen(
        onBackClick = onBackClick,
        poemSentence = poemSentence,
        defaultColor = if (dataStatus.captureTextColor == "white") Color.White else Color.Black,
        onColorChange = { viewModel.setCaptureColor(if (it == Color.White) "white" else "black") },
        defaultBackgroundColor = dataStatus.captureBackgroundColor,
        onBackgroundColorChange = { viewModel.setCaptureBackgroundColor(it) },
        colors = chineseColors
    )
}

@Composable
private fun PoemSentenceCaptureScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    defaultColor: Color,
    onColorChange: (Color) -> Unit,
    defaultBackgroundColor: String,
    onBackgroundColorChange: (String) -> Unit,
    poemSentence: PoemSentenceEntity?,
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
        poemSentence?.let { entity ->
            Row(
                modifier = modifier
                    .fillMaxSize()
                    .padding(vertical = 64.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    entity.content.split("，", "。", "？", "！").map {

                        Column {
                            it.toCharArray().map { char ->
                                Text(
                                    text = char.toString(),
                                    style = TextStyle.Default.copy(fontSize = 24.sp),
                                    color = color
                                )
                            }
                        }
                    }
                }
                Column {
                    entity.from.replace("《", "﹁")
                        .replace("》", "﹂")
                        .toCharArray()
                        .map {
                            Text(text = it.toString(), color = color)
                        }
                }
            }
        }
    }
}