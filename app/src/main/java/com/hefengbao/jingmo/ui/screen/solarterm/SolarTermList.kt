package com.hefengbao.jingmo.ui.screen.solarterm

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.model.SolarTerm
import com.hefengbao.jingmo.ui.component.SimpleScaffold

@Composable
fun SolarTermListRoute(
    viewModel: SolarTermListViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onItemClick: (Int) -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.getList()
    }

    val solarTerms by viewModel.solarTerms.collectAsState(initial = emptyList())

    SolarTermListScreen(
        solarTerms = solarTerms,
        onBackClick = onBackClick,
        onItemClick = onItemClick
    )
}

@Composable
private fun SolarTermListScreen(
    modifier: Modifier = Modifier,
    solarTerms: List<SolarTerm>,
    onBackClick: () -> Unit,
    onItemClick: (Int) -> Unit
) {
    SimpleScaffold(
        onBackClick = onBackClick,
        title = "二十四节气",
    ) {
        LazyColumn(
            content = {
                itemsIndexed(
                    items = solarTerms,
                    key = { _: Int, item: SolarTerm -> item.id }
                ) { _: Int, item: SolarTerm ->
                    Text(
                        text = item.name,
                        modifier = modifier
                            .fillMaxWidth()
                            .clickable {
                                onItemClick(item.id)
                            }
                            .padding(horizontal = 16.dp, vertical = 16.dp)
                    )
                }
            }
        )
    }
}