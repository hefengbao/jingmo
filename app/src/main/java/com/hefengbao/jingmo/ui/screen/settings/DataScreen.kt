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
    val syncPeopleResult by viewModel.syncPeopleResult.collectAsState(initial = null)
    val syncRiddlesResult by viewModel.syncRiddlesResult.collectAsState(initial = null)
    
        
    DataScreen(
        onBackClick = onBackClick,
        syncPeopleResult = syncPeopleResult,
        onSyncPeopleClick = { viewModel.syncPeople() },
        syncRiddlesResult = syncRiddlesResult,
        onSyncRiddlesClick = { viewModel.syncRiddles() }
    )
}

@Composable
private fun DataScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    syncPeopleResult: Result<Any>?,
    onSyncPeopleClick: () -> Unit,
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
            Item(
                title = "人物",
                onClick = onSyncPeopleClick,
                enabled = true,
                showProgressIndicator = syncPeopleResult == Result.Loading
            )
            Item(
                title = "谜语",
                onClick = onSyncRiddlesClick,
                enabled = true,
                showProgressIndicator = syncRiddlesResult == Result.Loading
            )
        }
    }
}

@Composable
private fun Item(
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit,
    enabled: Boolean,
    showProgressIndicator: Boolean
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ){
        Text(text = title, style = MaterialTheme.typography.titleMedium)
        IconButton(
            onClick = onClick,
            enabled = enabled
        ) {
            if (showProgressIndicator){
                CircularProgressIndicator()
            }
            Icon(imageVector = Icons.Default.Download, contentDescription = null)
        }
    }
}