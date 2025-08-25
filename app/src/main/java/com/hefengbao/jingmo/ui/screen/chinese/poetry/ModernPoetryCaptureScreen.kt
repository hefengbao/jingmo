package com.hefengbao.jingmo.ui.screen.chinese.poetry

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.entity.chinese.ModernPoetryEntity
import com.hefengbao.jingmo.data.model.AppStatus
import com.hefengbao.jingmo.data.model.traditionalculture.ChineseColor
import com.hefengbao.jingmo.ui.component.CaptureScaffold

@Composable
fun ModernPoetryCaptureRoute(
    viewModel: ModernPoetryCaptureViewModel = hiltViewModel<ModernPoetryCaptureViewModel>(),
    onBackClick: () -> Unit,
) {
    val poetryEntity by viewModel.poetryEntity.collectAsState(null)
    val colors by viewModel.colors.collectAsState(emptyList())
    val appStatus by viewModel.appStatus.collectAsState(null)

    ModernPoetryCaptureScreen(
        onBackClick = onBackClick,
        poetryEntity = poetryEntity,
        appStatus = appStatus,
        colors = colors,
        onTextColorChange = viewModel::setCaptureTextColor,
        onBackgroundColorChange = viewModel::setCaptureBackgroundColor
    )
}

@Composable
fun ModernPoetryCaptureScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    poetryEntity: ModernPoetryEntity?,
    appStatus: AppStatus?,
    colors: List<ChineseColor>,
    onTextColorChange: (String) -> Unit,
    onBackgroundColorChange: (String) -> Unit,
) {
    appStatus?.let {
        CaptureScaffold(
            modifier = modifier,
            onBackClick = onBackClick,
            colors = colors,
            onTextColorChange = onTextColorChange,
            onBackgroundColorChange = onBackgroundColorChange,
        ) {
            val tColor = appStatus.captureTextColor

            poetryEntity?.let { entity ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(appStatus.captureBackgroundColor)
                        .padding(horizontal = 16.dp, vertical = 32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = entity.title,
                        color = tColor
                    )
                    Text(
                        text = entity.author,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodySmall,
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