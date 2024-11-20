/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.link

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.model.Link
import com.hefengbao.jingmo.ui.component.SimpleScaffold

@Composable
fun LinkIndexRoute(
    viewModel: LinkIndexViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.getList()
    }

    val links by viewModel.links.collectAsState()

    LinkIndexScreen(
        onBackClick = onBackClick,
        links = links
    )
}

@Composable
private fun LinkIndexScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    links: List<Link>
) {
    val uriHandler = LocalUriHandler.current

    SimpleScaffold(onBackClick = onBackClick, title = "链接") {
        LazyVerticalGrid(
            modifier = modifier.fillMaxWidth(),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            content = {
                itemsIndexed(
                    items = links,
                    key = { _: Int, item: Link -> item.url },
                ) { _: Int, item: Link ->
                    Item(link = item, uriHandler = uriHandler)
                }
            }
        )
    }
}

@Composable
private fun Item(
    modifier: Modifier = Modifier,
    link: Link,
    uriHandler: UriHandler
) {
    Card(
        onClick = { uriHandler.openUri(link.url) },
    ) {
        Column(
            modifier = modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = link.title)
            Text(
                text = link.desc,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}