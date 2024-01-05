package com.hefengbao.jingmo.ui.screen.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.common.network.Result
import com.hefengbao.jingmo.ui.component.SimpleScaffold

@Composable
fun DataRoute(
    viewModel: DataViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    
    val syncRiddlesResult by viewModel.syncRiddlesResult.collectAsState(initial = null)
        
    DataScreen(
        onBackClick = onBackClick,
        syncRiddlesResult = syncRiddlesResult,
        onSyncRiddlesClick = {
            viewModel.syncRiddles()
        }
    )
}

@Composable
private fun DataScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    syncRiddlesResult: Result<Any>?,
    onSyncRiddlesClick: () -> Unit,
) {
    SimpleScaffold(
        onBackClick = onBackClick,
        title = "同步数据"
    ){
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ){
                Text(text = "谜语", style = MaterialTheme.typography.titleMedium)
                IconButton(
                    onClick = onSyncRiddlesClick,
                    enabled = true
                ) {
                    if (syncRiddlesResult == Result.Loading){
                        CircularProgressIndicator()
                    }
                    Icon(imageVector = Icons.Default.Download, contentDescription = null)
                }
            }
        }
    }
}