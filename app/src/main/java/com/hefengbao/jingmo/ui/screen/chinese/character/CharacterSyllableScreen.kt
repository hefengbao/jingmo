/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.character

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.model.chinese.character.Syllable
import com.hefengbao.jingmo.ui.component.SimpleScaffold

@Composable
fun CharacterSyllableRoute(
    viewModel: CharacterSyllableViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onItemClick: (String, String) -> Unit,
) {
    val syllables by viewModel.syllables.collectAsState(emptyList())

    CharacterSyllableScreen(
        onBackClick = onBackClick,
        onItemClick = onItemClick,
        syllables = syllables
    )
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalLayoutApi::class)
@Composable
private fun CharacterSyllableScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onItemClick: (String, String) -> Unit,
    syllables: List<Syllable>
) {
    var showDialog by rememberSaveable { mutableStateOf(false) }
    var tones by rememberSaveable { mutableStateOf(emptyList<String>()) }

    if (showDialog) {
        Dialog(
            onDismissRequest = { showDialog = false }
        ) {
            Card {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2)
                ) {
                    for (item in tones) {
                        item {
                            Column(
                                modifier = Modifier
                                    .clickable {
                                        onItemClick(item, "pinyin")
                                        showDialog = false
                                    }
                                    .padding(vertical = 16.dp)
                            ) {
                                Text(
                                    text = item,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp),
                                    textAlign = TextAlign.Center,
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    SimpleScaffold(onBackClick = onBackClick, title = "拼音查询") {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3)
        ) {
            syllables.forEachIndexed { _, syllable ->
                stickyHeader {
                    Text(
                        text = syllable.alphabet,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.primary)
                            .padding(16.dp),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
                for (item in syllable.pronunciations) {
                    item {
                        Column(
                            modifier = Modifier
                                .clickable {
                                    showDialog = true
                                    tones = item.tones
                                }
                                .padding(vertical = 16.dp)
                        ) {
                            Text(
                                text = item.pinyin,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp),
                                textAlign = TextAlign.Center,
                            )
                        }
                    }
                }
            }
        }
    }
}