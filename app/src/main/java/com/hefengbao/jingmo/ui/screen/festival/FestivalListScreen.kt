package com.hefengbao.jingmo.ui.screen.festival

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.model.Festival

@Composable
fun FestivalListRoute(
    viewModel: FestivalListViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onItemClick: (Int) -> Unit,
) {
    LaunchedEffect(Unit) {
        viewModel.getList()
    }

    val festivals by viewModel.festivals.collectAsState(initial = emptyList())

    FestivalListScreen(
        onBackClick = onBackClick,
        onItemClick = onItemClick,
        festivals = festivals
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FestivalListScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onItemClick: (Int) -> Unit,
    festivals: List<Festival>
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "传统节日")
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier.padding(paddingValues),
            content = {
                itemsIndexed(
                    items = festivals,
                    key = { _: Int, item: Festival -> item.id }
                ) { _: Int, item: Festival ->
                    Text(
                        text = item.name,
                        modifier = modifier
                            .fillMaxWidth()
                            .clickable {
                                onItemClick(item.id)
                            }
                            .padding(16.dp)
                    )
                }
            }
        )
    }
}