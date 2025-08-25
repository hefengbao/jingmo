package com.hefengbao.jingmo.ui.screen.chinese.character.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

@Composable
fun StyledText(label: String, text: String) {
    Text(
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = MaterialTheme.colorScheme.primary
                )
            ) {
                append("<$label>")
            }
            append(" $text")
        },
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(start = 16.dp)
    )
}