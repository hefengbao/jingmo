/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.idiom

import androidx.compose.foundation.border
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.entity.chinese.IdiomEntity
import com.hefengbao.jingmo.ui.component.CaptureScaffold
import com.hefengbao.jingmo.data.model.traditionalculture.Color as ChineseColor

@Composable
fun IdiomCaptureRoute(
    viewModel: IdiomCaptureViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val idiom by viewModel.idiom.collectAsState(initial = null)
    val chineseColors by viewModel.colors.collectAsState(initial = emptyList())
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
    idiom: IdiomEntity?,
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
        idiom?.let {
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
                        text = idiom.word,
                        style = MaterialTheme.typography.bodyLarge,
                        color = color
                    )
                }
                idiom.explanation?.let {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Title(
                            text = "释义",
                            color = color,
                        )
                        Text(
                            text = it,
                            color = color
                        )
                    }
                }

                idiom.source?.let { source ->
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Title(text = "出处", color = color)

                        val text = buildAnnotatedString {
                            source.text?.let { append(it) }
                            source.book?.let {
                                withStyle(style = SpanStyle(fontStyle = FontStyle.Italic)) {
                                    append("《${it}》")
                                }
                            }
                        }

                        Text(text = text, color = color)
                    }
                }

                idiom.quote?.let { quote ->
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Title(text = "名著用例", color = color)

                        val text = buildAnnotatedString {
                            quote.text?.let { append(it) }
                            quote.book?.let {
                                withStyle(style = SpanStyle(fontStyle = FontStyle.Italic)) {
                                    append("《${it}》")
                                }
                            }
                        }

                        Text(text = text, color = color)
                    }
                }

                idiom.usage?.let {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Title(text = "用法介绍", color = color)
                        Text(text = it, color = color)
                    }
                }

                idiom.example?.let { example ->
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Title(text = "示例", color = color)

                        val text = buildAnnotatedString {
                            example.text?.let { append(it) }
                            example.book?.let {
                                withStyle(style = SpanStyle(fontStyle = FontStyle.Italic)) {
                                    append("《${it}》")
                                }
                            }
                        }

                        Text(text = text, color = color)
                    }
                }

                idiom.similar?.let {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Title(text = "同义成语", color = color)
                        Text(text = it.joinToString("、"), color = color)
                    }
                }

                idiom.opposite?.let {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Title(text = "反义成语", color = color)
                        Text(text = it.joinToString("、"), color = color)
                    }
                }

                idiom.story?.let { list ->
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Title(text = "成语故事", color = color)
                        list.forEach {
                            Text(text = it, color = color)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun Title(text: String, color: Color) {
    Text(
        text = text,
        modifier = Modifier
            .border(width = 1.dp, color = color)
            .padding(16.dp, 8.dp),
        color = color,
        style = MaterialTheme.typography.titleSmall,
    )
}