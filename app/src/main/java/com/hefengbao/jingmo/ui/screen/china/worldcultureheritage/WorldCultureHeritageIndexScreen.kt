/*
 *  This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 *  For the full copyright and license information, please view the LICENSE
 *  file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.china.worldcultureheritage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import com.hefengbao.jingmo.data.database.entity.china.WorldCulturalHeritageEntity
import com.hefengbao.jingmo.ui.component.SimpleScaffold

@Composable
fun WorldCultureHeritageIndexRoute(
    viewModel: WorldCultureHeritageIndexViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onItemClick: (Int) -> Unit,
) {
    val list by viewModel.list.collectAsState()

    WorldCultureHeritageIndexScreen(
        onBackClick = onBackClick,
        onItemClick = onItemClick,
        list = list
    )
}

@Composable
private fun WorldCultureHeritageIndexScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onItemClick: (Int) -> Unit,
    list: List<WorldCulturalHeritageEntity>
) {
    SimpleScaffold(onBackClick = onBackClick, title = "中国的世界遗产") {
        LazyColumn(
            modifier = modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(
                items = list
            ) { entity: WorldCulturalHeritageEntity ->
                Card(
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    onClick = { onItemClick(entity.id) })
                {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        AsyncImage(
                            modifier = Modifier.size(128.dp),
                            model = entity.image,
                            contentDescription = "",
                            contentScale = ContentScale.FillBounds,
                            onState = { state ->
                                when (state) {
                                    AsyncImagePainter.State.Empty -> {}
                                    is AsyncImagePainter.State.Error -> {}
                                    is AsyncImagePainter.State.Loading -> {}
                                    is AsyncImagePainter.State.Success -> {}
                                }
                            }
                        )
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                        ) {
                            Text(text = entity.name)
                        }
                    }
                }
            }
        }
    }
}