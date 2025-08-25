package com.hefengbao.jingmo.ui.screen.chinese.lyric

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
import com.hefengbao.jingmo.data.database.entity.chinese.LyricEntity
import com.hefengbao.jingmo.data.model.AppStatus
import com.hefengbao.jingmo.data.model.traditionalculture.ChineseColor
import com.hefengbao.jingmo.ui.component.CaptureScaffold

@Composable
fun LyricCaptureRoute(
    viewModel: LyricCaptureViewModel = hiltViewModel<LyricCaptureViewModel>(),
    onBackClick: () -> Unit,
) {
    val lyricEntity by viewModel.lyricEntity.collectAsState(initial = null)
    val colors by viewModel.colors.collectAsState(emptyList())
    val appStatus by viewModel.appStatus.collectAsState(null)

    LyricCaptureScreen(
        onBackClick = onBackClick,
        lyricEntity = lyricEntity,
        colors = colors,
        appStatus = appStatus,
        onTextColorChange = viewModel::setCaptureTextColor,
        onBackgroundColorChange = viewModel::setCaptureBackgroundColor
    )
}

@Composable
private fun LyricCaptureScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    lyricEntity: LyricEntity?,
    colors: List<ChineseColor>,
    appStatus: AppStatus?,
    onTextColorChange: (String) -> Unit,
    onBackgroundColorChange: (String) -> Unit,
) {
    appStatus?.let {
        CaptureScaffold(
            modifier = modifier,
            colors = colors,
            onBackClick = onBackClick,
            onTextColorChange = onTextColorChange,
            onBackgroundColorChange = onBackgroundColorChange
        ) {
            val tColor = appStatus.captureTextColor

            lyricEntity?.let { entity ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(appStatus.captureBackgroundColor)
                        .padding(
                            vertical = 64.dp,
                            horizontal = 32.dp
                        ),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = entity.title,
                        style = MaterialTheme.typography.titleMedium,
                        color = tColor
                    )

                    var text = ""
                    if (entity.writer != null) {
                        text += "填词：${entity.writer}"
                    }
                    if (entity.singer != null) {
                        if (text.isNotEmpty()) {
                            text += " / "
                        }
                        text += "演唱：${entity.singer}"
                    }
                    Text(
                        text = text,
                        style = MaterialTheme.typography.labelMedium.copy(
                            brush = null,
                            alpha = .5f
                        ),
                        color = tColor
                    )
                    Text(
                        text = entity.content,
                        color = tColor
                    )
                }
            }
        }
    }

}