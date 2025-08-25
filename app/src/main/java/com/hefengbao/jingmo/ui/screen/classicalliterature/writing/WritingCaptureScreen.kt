/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.classicalliterature.writing

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.entity.classicalliterature.WritingEntity
import com.hefengbao.jingmo.data.model.AppStatus
import com.hefengbao.jingmo.data.model.traditionalculture.ChineseColor
import com.hefengbao.jingmo.ui.component.CaptureScaffold

@Composable
fun WritingCaptureRoute(
    viewModel: WritingCaptureViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val writingEntity by viewModel.writingEntity.collectAsState()
    val chineseColors by viewModel.colors.collectAsState(initial = emptyList())
    val appStatus by viewModel.appStatus.collectAsState(null)

    WritingCaptureScreen(
        onBackClick = onBackClick,
        writingEntity = writingEntity,
        onTextColorChange = { viewModel.setCaptureTextColor(it) },
        onBackgroundColorChange = { viewModel.setCaptureBackgroundColor(it) },
        colors = chineseColors,
        appStatus = appStatus
    )
}

@Composable
private fun WritingCaptureScreen(
    onBackClick: () -> Unit,
    onTextColorChange: (String) -> Unit,
    onBackgroundColorChange: (String) -> Unit,
    writingEntity: WritingEntity?,
    colors: List<ChineseColor>,
    appStatus: AppStatus?,
) {
    appStatus?.let {
        CaptureScaffold(
            colors = colors,
            onBackClick = onBackClick,
            onTextColorChange = onTextColorChange,
            onBackgroundColorChange = onBackgroundColorChange
        ) {
            val tColor = appStatus.captureTextColor

            writingEntity?.let { entity ->
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
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(appStatus.captureBackgroundColor)
                        .padding(16.dp, 32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = entity.title.content,
                        style = MaterialTheme.typography.titleLarge,
                        color = tColor
                    )
                    Text(
                        text = "${entity.dynasty}·${entity.author}",
                        style = MaterialTheme.typography.titleSmall,
                        color = tColor
                    )
                    Text(
                        text = content,
                        style = MaterialTheme.typography.bodyLarge,
                        color = tColor
                    )
                }
            }
        }
    }
}