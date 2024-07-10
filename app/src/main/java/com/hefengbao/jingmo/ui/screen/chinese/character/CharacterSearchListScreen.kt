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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.entity.DictionaryEntity
import com.hefengbao.jingmo.ui.component.SimpleScaffold

@Composable
fun CharacterSearchListRoute(
    viewModel: CharacterSearchListViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onItemClick: (String) -> Unit,
) {
    LaunchedEffect(Unit) {
        viewModel.getCharacters()
    }

    val characters by viewModel.characters.collectAsState(initial = emptyList())

    CharacterSearchListScreen(
        onBackClick = onBackClick,
        onItemClick = onItemClick,
        characters = characters,
        query = viewModel.query,
        type = viewModel.type
    )
}

@Composable
private fun CharacterSearchListScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onItemClick: (String) -> Unit,
    characters: List<DictionaryEntity>,
    query: String,
    type: String
) {
    SimpleScaffold(
        onBackClick = onBackClick,
        title = if (type == "stroke") "$query 画" else query
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            itemsIndexed(
                items = characters
            ) { _: Int, item: DictionaryEntity ->
                Text(
                    text = item.char,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onItemClick(item.id.toString()) }
                        .padding(vertical = 16.dp),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}