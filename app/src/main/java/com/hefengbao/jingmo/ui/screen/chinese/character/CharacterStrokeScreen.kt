/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.character

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.model.chinese.character.Stroke
import com.hefengbao.jingmo.ui.component.SimpleScaffold

@Composable
fun CharacterStrokeRoute(
    viewModel: CharacterStrokeViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onItemClick: (String, String) -> Unit,
) {
    val strokes = viewModel.strokes

    CharacterStrokeScreen(
        onBackClick = onBackClick,
        onItemClick = onItemClick,
        strokes = strokes
    )
}

@Composable
private fun CharacterStrokeScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onItemClick: (String, String) -> Unit,
    strokes: List<Stroke>
) {
    SimpleScaffold(onBackClick = onBackClick, title = "笔画查询") {
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            itemsIndexed(
                items = strokes
            ) { _: Int, item: Stroke ->
                Text(
                    text = item.label,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onItemClick(item.stroke.toString(), "stroke") }
                        .padding(vertical = 16.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}