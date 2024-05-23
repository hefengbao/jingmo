package com.hefengbao.jingmo.ui.screen.chinesecharacter

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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.model.CharacterPinyin
import com.hefengbao.jingmo.ui.component.SimpleScaffold

@Composable
fun CharacterPinyinIndexRoute(
    viewModel: CharacterPinyinIndexViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onItemClick: (String, String) -> Unit,
) {
    val pinyins = viewModel.pinyins

    CharacterPinyinIndexScreen(
        onBackClick = onBackClick,
        onItemClick = onItemClick,
        pinyins = pinyins
    )
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalLayoutApi::class)
@Composable
private fun CharacterPinyinIndexScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onItemClick: (String, String) -> Unit,
    pinyins: List<CharacterPinyin>
) {
    val configuration = LocalConfiguration.current

    val screenWidth = configuration.screenWidthDp.dp

    SimpleScaffold(onBackClick = onBackClick, title = "拼音查询") {
        LazyColumn(
            modifier = modifier.fillMaxWidth(),
        ) {
            pinyins.forEachIndexed { _, characterPinyin ->
                stickyHeader {
                    Text(
                        text = characterPinyin.alphabet,
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
                            for (item in characterPinyin.pinyin) {
                                Column(
                                    modifier = Modifier
                                        .clickable {
                                            onItemClick(item, "pinyin")
                                        }
                                        .padding(vertical = 16.dp)
                                        .width((screenWidth - 32.dp) / 3),
                                ) {
                                    Text(
                                        text = item,
                                        modifier = Modifier.padding(horizontal = 16.dp)
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