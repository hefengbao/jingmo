/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.antitheticalcouplet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.entity.chinese.AntitheticalCoupletEntity
import com.hefengbao.jingmo.data.model.AppStatus
import com.hefengbao.jingmo.data.model.traditionalculture.ChineseColor
import com.hefengbao.jingmo.ui.component.CaptureScaffold

@Composable
fun AntitheticalCoupletCaptureRoute(
    viewModel: AntitheticalCoupletCaptureViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val antitheticalCoupletEntity by viewModel.antitheticalCoupletEntity.collectAsState(initial = null)
    val chineseColors by viewModel.colors.collectAsState(initial = emptyList())
    val appStatus by viewModel.appStatus.collectAsState(null)

    AntitheticalCoupletCaptureScreen(
        onBackClick = onBackClick,
        antitheticalCoupletEntity = antitheticalCoupletEntity,
        appStatus = appStatus,
        onTextColorChange = { viewModel.setCaptureTextColor(it) },
        onBackgroundColorChange = { viewModel.setCaptureBackgroundColor(it) },
        colors = chineseColors
    )
}

@Composable
private fun AntitheticalCoupletCaptureScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    appStatus: AppStatus?,
    onTextColorChange: (String) -> Unit,
    onBackgroundColorChange: (String) -> Unit,
    antitheticalCoupletEntity: AntitheticalCoupletEntity?,
    colors: List<ChineseColor>
) {
    appStatus?.let {
        CaptureScaffold(
            colors = colors,
            onBackClick = onBackClick,
            onTextColorChange = onTextColorChange,
            onBackgroundColorChange = onBackgroundColorChange
        ) {
            antitheticalCoupletEntity?.let { entity ->
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
                            text = entity.body,
                            color = appStatus.captureTextColor
                        )
                    }
                }
            }
        }
    }
}