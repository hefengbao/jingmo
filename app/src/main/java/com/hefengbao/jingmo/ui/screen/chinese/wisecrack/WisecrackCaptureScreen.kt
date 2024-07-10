/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.wisecrack

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
import com.hefengbao.jingmo.data.database.model.ChineseWisecrackWithBookmark
import com.hefengbao.jingmo.data.model.ChineseColor
import com.hefengbao.jingmo.ui.component.CaptureScaffold

@Composable
fun ChineseWisecrackCaptureRoute(
    viewModel: WisecrackCaptureViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val chineseWisecrack by viewModel.chineseWisecrack.collectAsState()
    val chineseColors by viewModel.chineseColors.collectAsState(initial = emptyList())
    val dataStatus = viewModel.appStatus

    LaunchedEffect(Unit) {
        viewModel.getColors()
    }

    ChineseWisecrackCaptureScreen(
        onBackClick = onBackClick,
        chineseWisecrack = chineseWisecrack,
        defaultColor = if (dataStatus.captureTextColor == "white") Color.White else Color.Black,
        onColorChange = { viewModel.setCaptureColor(if (it == Color.White) "white" else "black") },
        defaultBackgroundColor = dataStatus.captureBackgroundColor,
        onBackgroundColorChange = { viewModel.setCaptureBackgroundColor(it) },
        colors = chineseColors
    )
}

@Composable
private fun ChineseWisecrackCaptureScreen(
    onBackClick: () -> Unit,
    defaultColor: Color,
    onColorChange: (Color) -> Unit,
    defaultBackgroundColor: String,
    onBackgroundColorChange: (String) -> Unit,
    chineseWisecrack: ChineseWisecrackWithBookmark?,
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
        chineseWisecrack?.let { entity ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 96.dp, horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = entity.riddle,
                    style = MaterialTheme.typography.bodyLarge,
                    color = color
                )
                Text(
                    text = "—— ${entity.answer}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = color
                )
            }
        }
    }
}