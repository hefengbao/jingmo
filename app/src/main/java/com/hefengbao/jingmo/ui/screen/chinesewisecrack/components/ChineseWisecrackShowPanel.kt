package com.hefengbao.jingmo.ui.screen.chinesewisecrack.components

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hefengbao.jingmo.data.database.model.ChineseWisecrackWithBookmark
import kotlin.math.abs

@Composable
fun ShowChineseWisecrackPanel(
    modifier: Modifier = Modifier,
    entity: ChineseWisecrackWithBookmark,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp, 16.dp, 16.dp, 96.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        SelectionContainer {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = entity.riddle,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "—— ${entity.answer}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}