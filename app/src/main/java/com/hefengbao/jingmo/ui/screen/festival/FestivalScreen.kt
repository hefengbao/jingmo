package com.hefengbao.jingmo.ui.screen.festival

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Launch
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.model.Festival
import com.hefengbao.jingmo.ui.component.SimpleScaffold

@Composable
fun FestivalRoute(
    viewModel: FestivalViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {
    val festival by viewModel.festival.collectAsState(initial = null)

    FestivalScreen(onBackClick = onBackClick, festival = festival)
}

@Composable
private fun FestivalScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    festival: Festival?
) {
    val uriHandler = LocalUriHandler.current
    festival?.let { _ ->
        SimpleScaffold(
            onBackClick = onBackClick,
            title = festival.name,
            actions = {
                IconButton(
                    onClick = { uriHandler.openUri(festival.url) }
                ) {
                    Icon(
                        imageVector = Icons.Default.Launch,
                        contentDescription = "跳转到百度百科了解更多"
                    )
                }
            }
        ) {
            Column(
                modifier = modifier
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                TextItem(title = "名称", content = festival.name)
                TextItem(title = "别名", content = festival.alias)
                TextItem(title = "简介", content = festival.desc)
            }
        }
    }
}

@Composable
private fun TextItem(
    title: String,
    content: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = title, style = MaterialTheme.typography.headlineSmall)
        Text(text = content)
    }
}