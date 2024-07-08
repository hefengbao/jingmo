/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.writing

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.entity.WritingEntity
import com.hefengbao.jingmo.data.model.ChineseColor
import com.hefengbao.jingmo.ui.component.CaptureScaffold

@Composable
fun WritingCaptureRoute(
    viewModel: WritingCaptureViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val writing by viewModel.writing.collectAsState()
    val chineseColors by viewModel.chineseColors.collectAsState(initial = emptyList())
    val dataStatus = viewModel.appStatus

    LaunchedEffect(Unit) {
        viewModel.getColors()
    }

    WritingCaptureScreen(
        onBackClick = onBackClick,
        writing = writing,
        defaultColor = if (dataStatus.captureTextColor == "white") Color.White else Color.Black,
        onColorChange = { viewModel.setCaptureColor(if (it == Color.White) "white" else "black") },
        defaultBackgroundColor = dataStatus.captureBackgroundColor,
        onBackgroundColorChange = { viewModel.setCaptureBackgroundColor(it) },
        colors = chineseColors
    )
}

@Composable
private fun WritingCaptureScreen(
    onBackClick: () -> Unit,
    defaultColor: Color,
    onColorChange: (Color) -> Unit,
    defaultBackgroundColor: String,
    onBackgroundColorChange: (String) -> Unit,
    writing: WritingEntity?,
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
        writing?.let { entity ->
            val content = buildString {
                entity.clauses.mapIndexed { _, clause ->
                    if (entity.type != "文") {
                        append(clause.content)
                        append("\n")
                    } else {
                        append(clause.content)
                    }

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
                text = "${entity.dynasty}·${entity.author}",
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