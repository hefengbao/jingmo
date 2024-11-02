/*
 *  This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 *  For the full copyright and license information, please view the LICENSE
 *  file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.china.worldcultureheritage

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.entity.china.WorldCulturalHeritageEntity
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import com.hefengbao.jingmo.ui.component.webview.RYWebView
import com.hefengbao.jingmo.ui.screen.ImageData
import com.hefengbao.jingmo.ui.screen.ReaderImageViewer

@Composable
fun WorldCultureHeritageShowRoute(
    viewModel: WorldCultureHeritageShowViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val worldCulturalHeritageEntity by viewModel.worldCulturalHeritageEntity.collectAsState()

    WorldCultureHeritageShowScreen(
        onBackClick = onBackClick,
        worldCulturalHeritageEntity = worldCulturalHeritageEntity,
        onDownloadImage = viewModel::downloadImage
    )
}

@Composable
fun WorldCultureHeritageShowScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    worldCulturalHeritageEntity: WorldCulturalHeritageEntity?,
    onDownloadImage: (String) -> Unit,
) {
    worldCulturalHeritageEntity?.let { entity ->
        var currentImageData by remember { mutableStateOf(ImageData()) }
        var showFullScreenImageViewer by remember { mutableStateOf(false) }

        SimpleScaffold(onBackClick = onBackClick, title = entity.name) {
            SelectionContainer(
                modifier = modifier.padding(16.dp)
            ) {
                var content =
                    "<h3>遗产分布</h3><p>${entity.address}</p><h3>列入标准</h3><p>${entity.level}</p><h3>列入时间</h3><p>${entity.year}</p>"

                entity.year2?.let {
                    content += "<h3>扩展时间</h3><p>${entity.year2}</p>"
                }
                content += "<h3>遗产介绍</h3>${entity.content}"

                RYWebView(
                    content = content,
                    onImageClick = { imgUrl: String, altText: String ->
                        currentImageData = ImageData(imgUrl, altText)
                        showFullScreenImageViewer = true
                    }
                )
            }
        }

        if (showFullScreenImageViewer) {

            ReaderImageViewer(
                imageData = currentImageData,
                onDownloadImage = onDownloadImage,
                onDismissRequest = { showFullScreenImageViewer = false }
            )
        }
    }
}