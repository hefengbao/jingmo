package com.hefengbao.jingmo.ui.screen.riddle

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.entity.RiddleEntity

@Composable
fun RiddleSearchRoute(
    viewModel: RiddleSearchViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val list by viewModel.list.collectAsState(initial = emptyList())

    RiddleSearchScreen(
        onBackClick = onBackClick,
        onSearchClick = { viewModel.search(it) },
        list = list
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RiddleSearchScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onSearchClick: (String) -> Unit,
    list: List<RiddleEntity>
) {
    var query by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    SearchBar(
                        query = query,
                        onQueryChange = { query = it},
                        onSearch = onSearchClick,
                        active = false,
                        onActiveChange = {}
                    ) {
                        
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = modifier.padding(paddingValues)
        ){
            LazyColumn {
                itemsIndexed(
                    items = list
                ){index: Int, item: RiddleEntity ->  
                    Card(
                        modifier = modifier.fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Column(
                            modifier = modifier.fillMaxWidth().padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Text(text = item.puzzle)
                            Text(text = item.answer)
                        }
                    }
                }
            }
        }
    }
}