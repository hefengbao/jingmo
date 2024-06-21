package com.hefengbao.jingmo.ui.screen.idiom.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.hefengbao.jingmo.data.database.entity.IdiomEntity
import com.hefengbao.jingmo.ui.component.BackgroundTitle

@Composable
fun IdiomShowPanel(
    modifier: Modifier = Modifier,
    idiom: IdiomEntity,
) {
    SelectionContainer {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = idiom.pinyin,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = idiom.word,
                    style = MaterialTheme.typography.displaySmall
                )
            }
            idiom.explanation?.let {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    BackgroundTitle(title = "释义")
                    Text(
                        text = it,
                    )
                }
            }

            idiom.source?.let { source ->
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    BackgroundTitle(title = "出处")

                    val text = buildAnnotatedString {
                        source.text?.let { append(it) }
                        source.book?.let {
                            withStyle(style = SpanStyle(fontStyle = FontStyle.Italic)) {
                                append(it)
                            }
                        }
                    }

                    Text(text = text)
                }
            }

            idiom.quote?.let { quote ->
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    BackgroundTitle(title = "名著用例")

                    val text = buildAnnotatedString {
                        quote.text?.let { append(it) }
                        quote.book?.let {
                            withStyle(style = SpanStyle(fontStyle = FontStyle.Italic)) {
                                append(it)
                            }
                        }
                    }

                    Text(text = text)
                }
            }

            idiom.usage?.let {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    BackgroundTitle(title = "用法介绍")
                    Text(text = it)
                }
            }

            idiom.example?.let { example ->
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    BackgroundTitle(title = "示例")

                    val text = buildAnnotatedString {
                        example.text?.let { append(it) }
                        example.book?.let {
                            withStyle(style = SpanStyle(fontStyle = FontStyle.Italic)) {
                                append(it)
                            }
                        }
                    }

                    Text(text = text)
                }
            }

            idiom.similar?.let {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    BackgroundTitle(title = "同义成语")
                    Text(text = it.joinToString("、"))
                }
            }

            idiom.opposite?.let {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    BackgroundTitle(title = "反义成语")
                    Text(text = it.joinToString("、"))
                }
            }

            idiom.story?.let { list ->
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    BackgroundTitle(title = "成语故事")
                    list.forEach {
                        Text(text = it)
                    }
                }
            }
        }
    }
}