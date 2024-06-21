package com.hefengbao.jingmo.ui.screen.chinesecharacter

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.common.util.ClipboardUtil
import com.hefengbao.jingmo.data.database.entity.DictionaryEntity
import com.hefengbao.jingmo.ui.component.BackgroundTitle
import com.hefengbao.jingmo.ui.component.SimpleScaffold

@Composable
fun CharacterShowScreenRoute(
    viewModel: CharacterShowViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {
    val character by viewModel.character.collectAsState()
    val context = LocalContext.current

    CharacterShowScreen(
        context = context,
        onBackClick = onBackClick,
        character = character,
    )
}

@Composable
private fun CharacterShowScreen(
    modifier: Modifier = Modifier,
    context: Context,
    onBackClick: () -> Unit,
    character: DictionaryEntity?,
) {
    character?.let { entity ->
        SimpleScaffold(onBackClick = onBackClick, title = entity.char) {
            LazyColumn(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = entity.char, style = MaterialTheme.typography.displayLarge)
                        IconButton(
                            onClick = {
                                ClipboardUtil.textCopyThenPost(context, entity.char)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ContentCopy,
                                contentDescription = "复制"
                            )
                        }
                    }
                }
                item {
                    SelectionContainer {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            entity.pinyin?.let {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                                ) {
                                    BackgroundTitle(title = "拼音")
                                    Text(text = it)
                                }
                            }
                            entity.wubi?.let {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                                ) {
                                    BackgroundTitle(title = "五笔")
                                    Text(text = it)
                                }
                            }
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                BackgroundTitle(title = "总笔画")
                                Text(text = "${entity.stroke}画")
                            }
                        }
                    }
                }
                entity.simpleExplanation?.let {
                    item {
                        SelectionContainer {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                BackgroundTitle(title = "简要释义")
                                Text(
                                    text = it.replace("<br><br><br>", "")
                                        .replace("<br><br>", "<br>")
                                        .replace("<br>", "\n\n")
                                )
                                Text(
                                    text = "💡 1 - 横，2 - 竖，3 - 撇，4 -捺，5 - 折，提为横，点为捺，竖勾为竖，横折为折，竖提为折。",
                                    style = MaterialTheme.typography.labelMedium
                                )
                            }
                        }
                    }
                }
                entity.explanation?.let {
                    item {
                        SelectionContainer {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                BackgroundTitle(title = "详细释义")
                                Text(
                                    text = it.replace("<br><br><br>", "")
                                        .replace("<br><br>", "<br>")
                                        .replace("<br>", "\n\n")
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}