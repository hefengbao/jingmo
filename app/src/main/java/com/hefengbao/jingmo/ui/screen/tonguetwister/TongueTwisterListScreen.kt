package com.hefengbao.jingmo.ui.screen.tonguetwister

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.model.SimpleTongueTwister
import com.hefengbao.jingmo.ui.component.SimpleScaffold

@Composable
fun TongueTwisterListRoute(
    viewModel: TongueTwisterListViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onItemClick: (Int) -> Unit
) {
    val lastReadId = viewModel.lastReadId
    val tongueTwisters by viewModel.tongueTwisters.collectAsState(initial = emptyList())

    TongueTwisterListScreen(
        onBackClick = onBackClick,
        tongueTwisters = tongueTwisters,
        lastReadId = lastReadId,
        onItemClick = onItemClick
    )
}

@Composable
private fun TongueTwisterListScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    tongueTwisters: List<SimpleTongueTwister>,
    lastReadId: Int,
    onItemClick: (Int) -> Unit
) {
    var firstLoading by rememberSaveable { mutableStateOf(true) }

    SimpleScaffold(
        onBackClick = onBackClick,
        title = "绕口令"
    ) {
        if (tongueTwisters.isNotEmpty()){
            val state = rememberLazyListState()

            if (firstLoading) {
                val index = tongueTwisters.indexOfFirst { it.id == lastReadId }
                LaunchedEffect(Unit) {
                    state.animateScrollToItem(index)
                }
            }

            LazyColumn(
                state = state,
                content = {
                    itemsIndexed(
                        items = tongueTwisters,
                        key = { _: Int, item: SimpleTongueTwister -> item.id }
                    ) { _: Int, item: SimpleTongueTwister ->
                        Text(
                            text = item.title,
                            modifier = modifier
                                .fillMaxWidth()
                                .clickable {
                                    firstLoading = false
                                    onItemClick(item.id)
                                }
                                .padding(16.dp)
                        )
                    }
                }
            )
        }
    }
}