package com.hefengbao.jingmo.ui.screen.chinesewisecrack.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hefengbao.jingmo.data.database.entity.ChineseWisecrackEntity

@Composable
fun ShowChineseWisecrackPanel(
    modifier: Modifier = Modifier,
    entity: ChineseWisecrackEntity,
    prevId: Int?,
    onPrevClick: () -> Unit,
    nextId: Int?,
    onNextClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
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
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(80.dp)
                .align(
                    Alignment.BottomCenter
                ),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = onPrevClick,
                enabled = prevId != 0,
                modifier = modifier.padding(16.dp)
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }

            IconButton(
                onClick = onNextClick,
                enabled = nextId != 0,
                modifier = modifier.padding(16.dp)
            ) {
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)
            }
        }
    }
}