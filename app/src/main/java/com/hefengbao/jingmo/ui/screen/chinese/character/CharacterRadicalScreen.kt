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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.model.chinese.character.Radical
import com.hefengbao.jingmo.ui.component.SimpleScaffold

@Composable
fun CharacterRadicalIndexRoute(
    viewModel: CharacterRadicalViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onItemClick: (String, String) -> Unit,
) {
    val radicals = viewModel.radicals

    CharacterRadicalIndexScreen(
        onBackClick = onBackClick,
        onItemClick = onItemClick,
        radicals = radicals
    )
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalLayoutApi::class)
@Composable
private fun CharacterRadicalIndexScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onItemClick: (String, String) -> Unit,
    radicals: List<Radical>
) {
    val configuration = LocalConfiguration.current

    val screenWidth = configuration.screenWidthDp.dp

    SimpleScaffold(onBackClick = onBackClick, title = "部首查询") {
        LazyColumn(
            modifier = modifier.fillMaxWidth()
        ) {
            radicals.forEachIndexed { _, characterRadical ->
                stickyHeader {
                    Text(
                        text = characterRadical.stroke,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.primary)
                            .padding(16.dp),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
                item {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        FlowRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            maxItemsInEachRow = 4,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            for (item in characterRadical.radicals) {
                                Column(
                                    modifier = Modifier
                                        .clickable {
                                            onItemClick(item, "radical")
                                        }
                                        .padding(vertical = 16.dp)
                                        .width((screenWidth - 32.dp) / 3),
                                ) {
                                    Text(
                                        text = item,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 16.dp),
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}