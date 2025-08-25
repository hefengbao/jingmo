/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.classicalliterature.sentence

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.entity.classicalliterature.SentenceEntity
import com.hefengbao.jingmo.data.model.AppStatus
import com.hefengbao.jingmo.data.model.traditionalculture.ChineseColor
import com.hefengbao.jingmo.ui.component.CaptureScaffold

@Composable
fun SentenceCaptureRoute(
    viewModel: SentenceCaptureViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val sentenceEntity by viewModel.sentenceEntity.collectAsState(initial = null)
    val chineseColors by viewModel.colors.collectAsState(initial = emptyList())
    val appStatus by viewModel.appStatus.collectAsState(null)

    SentenceCaptureScreen(
        onBackClick = onBackClick,
        sentenceEntity = sentenceEntity,
        onTextColorChange = { viewModel.setCaptureTextColor(it) },
        onBackgroundColorChange = { viewModel.setCaptureBackgroundColor(it) },
        appStatus = appStatus,
        colors = chineseColors
    )
}

@Composable
private fun SentenceCaptureScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onTextColorChange: (String) -> Unit,
    onBackgroundColorChange: (String) -> Unit,
    appStatus: AppStatus?,
    sentenceEntity: SentenceEntity?,
    colors: List<ChineseColor>
) {
    appStatus?.let { status ->
        CaptureScaffold(
            colors = colors,
            onBackClick = onBackClick,
            onTextColorChange = onTextColorChange,
            onBackgroundColorChange = onBackgroundColorChange
        ) {
            val tColor = appStatus.captureTextColor

            sentenceEntity?.let { entity ->
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .background(appStatus.captureBackgroundColor)
                        .padding(32.dp, 64.dp),
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
                                        color = tColor
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
                                Text(text = it.toString(), color = tColor)
                            }
                    }
                }
            }
        }
    }
}