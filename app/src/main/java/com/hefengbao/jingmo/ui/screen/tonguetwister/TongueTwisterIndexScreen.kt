package com.hefengbao.jingmo.ui.screen.tonguetwister

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.model.SimpleTongueTwister
import com.hefengbao.jingmo.ui.component.SimpleScaffold

@Composable
fun TongueTwisterIndexRoute(
    viewModel: TongueTwisterIndexViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onItemClick: (Int) -> Unit
) {
    val tongueTwisters by viewModel.tongueTwisters.collectAsState(initial = emptyList())

    TongueTwisterIndexScreen(
        onBackClick = onBackClick,
        tongueTwisters = tongueTwisters,
        onItemClick = onItemClick
    )
}

@Composable
private fun TongueTwisterIndexScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    tongueTwisters: List<SimpleTongueTwister>,
    onItemClick: (Int) -> Unit
) {
    //var firstLoading by rememberSaveable { mutableStateOf(true) }

    SimpleScaffold(
        onBackClick = onBackClick,
        title = "绕口令"
    ) {
        LazyColumn(
            modifier = modifier.fillMaxWidth()
        ) {
            itemsIndexed(
                items = tongueTwisters,
                key = { _: Int, item: SimpleTongueTwister -> item.id }
            ) { _: Int, item: SimpleTongueTwister ->
                Text(
                    text = item.title,
                    modifier = modifier
                        .fillMaxWidth()
                        .clickable {
                            //firstLoading = false
                            onItemClick(item.id)
                        }
                        .padding(16.dp)
                )
            }
        }
    }
}