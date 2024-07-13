/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.classicalliterature.writing.components

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.hefengbao.jingmo.data.database.entity.classicalliterature.WritingEntity
import com.hefengbao.jingmo.data.model.classicalliterature.writing.CharDict
import com.hefengbao.jingmo.data.model.classicalliterature.writing.WordDict
import com.hefengbao.jingmo.ui.component.BackgroundTitle
import kotlinx.serialization.json.Json

@SuppressLint("RememberReturnType")
@Composable
fun WritingShowPanel(
    modifier: Modifier = Modifier,
    writing: WritingEntity,
    json: Json,
) {
    val tag = "note"

    val charDicts = mutableListOf<CharDict>()
    val wordDicts = mutableListOf<WordDict>()

    val content = buildAnnotatedString {
        writing.clauses.mapIndexed { _, clause ->
            if (clause.comments != null) {
                var splitContent = clause.content

                clause.comments.sortedBy {
                    it.index
                }.map { comment ->

                    when (comment.type) {
                        "CharDictInJson" -> {

                            val char = json.decodeFromString<CharDict>(comment.content)

                            charDicts.add(char)

                            val arr = splitContent.split(char.OriginalChar)

                            append(arr[0])

                            splitContent = if (arr.size == 2) {
                                arr[1]
                            } else {
                                ""
                            }

                            pushStringAnnotation(tag, "char_${charDicts.lastIndex}")

                            withStyle(
                                style = SpanStyle(
                                    textDecoration = TextDecoration.Underline,
                                )
                            ) {
                                append(char.OriginalChar)
                            }

                            pop()
                        }

                        "WordDictInJson" -> {
                            val word = json.decodeFromString<WordDict>(comment.content)

                            wordDicts.add(word)

                            val arr = splitContent.split(word.Text)

                            append(arr[0])

                            splitContent = if (arr.size == 2) {
                                arr[1]
                            } else {
                                ""
                            }

                            pushStringAnnotation(tag, "word_${wordDicts.lastIndex}")

                            withStyle(
                                style = SpanStyle(
                                    textDecoration = TextDecoration.Underline,
                                )
                            ) {
                                append(word.Text)
                            }

                            pop()
                        }

                        else -> {
                            // 没什么可处理
                        }
                    }
                }
                if (splitContent.isNotEmpty()) {
                    append(splitContent)
                }
            } else {
                append(clause.content)
            }

            if (writing.type != "文") {
                append("\n")
            }

            if (clause.breakAfter != null) {
                append("\n")
            }
        }
    }

    var showCharDialog by rememberSaveable { mutableStateOf(false) }
    var showWordDialog by rememberSaveable { mutableStateOf(false) }
    var showChar by rememberSaveable {
        mutableStateOf<CharDict?>(null)
    }
    var showWord by rememberSaveable {
        mutableStateOf<WordDict?>(null)
    }

    val state = rememberLazyListState()

    BackHandler(showCharDialog) {
        showCharDialog = false
        showChar = null
    }

    BackHandler(showWordDialog) {
        showWordDialog = false
        showWord = null
    }

    if (showCharDialog && showChar != null) {
        CharDialog(charDict = showChar as CharDict) {
            showCharDialog = false
            showChar = null
        }
    }

    if (showWordDialog && showWord != null) {
        WordDialog(wordDict = showWord as WordDict) {
            showWordDialog = false
            showWord = null
        }
    }

    LaunchedEffect(writing) {
        state.animateScrollToItem(0)
    }

    SelectionContainer {
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            state = state
        ) {
            item {
                Column(
                    modifier = modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    Text(
                        text = writing.title.content,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = "${writing.dynasty}·${writing.author}",
                        style = MaterialTheme.typography.labelLarge
                    )
                    if (writing.preface != null) {
                        Text(
                            text = writing.preface.replace("<br />", "\n"),
                            style = MaterialTheme.typography.labelLarge,
                            fontStyle = FontStyle.Italic
                        )
                    }
                    //Text(text = content, style = MaterialTheme.typography.bodyLarge)
                    ClickableText(
                        text = content,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = MaterialTheme.colorScheme.onBackground
                        ),
                    ) {
                        content.getStringAnnotations(tag, it, it).map { string ->
                            val arr = string.item.split("_")

                            when (arr[0]) {
                                "word" -> {
                                    showWord = wordDicts[arr[1].toInt()]
                                    showWordDialog = true
                                }

                                "char" -> {
                                    showChar = charDicts[arr[1].toInt()]
                                    showCharDialog = true
                                }

                                else -> {}
                            }
                        }
                    }
                }
            }

            writing.note?.let {
                item {
                    BackgroundTitle(title = "按语、作者自注、跋")
                    Text(text = it, modifier = modifier.padding(top = 16.dp))
                }
            }

            writing.comments?.let {
                item {
                    BackgroundTitle(title = "赏析（评价）")
                    it.map {
                        Column(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            it.book?.let { book ->
                                Text(text = "\uD83D\uDCD6 $book")
                            }

                            it.section?.let { section ->
                                Text(text = section)
                            }

                            it.content?.let { content ->
                                Text(
                                    text = content.replace("<br />", "\n")
                                        .replace("</p>", "\n")
                                        .replace("</div>", "\n")
                                        .replace("<[^>]+>".toRegex(), "")
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CharDialog(
    modifier: Modifier = Modifier,
    charDict: CharDict,
    onDismissRequest: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = { /*TODO*/ },
        title = {
            Text(text = charDict.OriginalChar)
        },
        text = {
            Column(
                modifier = modifier.verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                charDict.Comments.map { comment ->
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(text = "📖 《${comment.Origin}》")
                        comment.Explains.map {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(text = it.Content.replace("<[^>]+>".toRegex(), ""))
                            }
                        }
                    }
                }
            }
        },
    )
}

@Composable
private fun WordDialog(
    modifier: Modifier = Modifier,
    wordDict: WordDict,
    onDismissRequest: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = { /*TODO*/ },
        title = {
            Text(text = wordDict.Text)
        },
        text = {
            Column(
                modifier = modifier.verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (wordDict.Spells != null) {
                    Text(text = "${wordDict.Text}（${wordDict.Spells}）")
                } else {
                    Text(text = wordDict.Text)
                }
                wordDict.Explains.map {
                    Text(text = it.replace("<[^>]+>".toRegex(), ""))
                }
            }
        }
    )
}