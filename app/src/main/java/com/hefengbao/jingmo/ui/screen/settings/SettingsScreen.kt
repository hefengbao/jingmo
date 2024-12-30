/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Badge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hefengbao.jingmo.ui.screen.settings.components.SettingsTitle

@Composable
fun SettingsRoute(
    onAboutClick: () -> Unit,
    onBackClick: () -> Unit,
    onDataClick: () -> Unit,
    onHomeItemManagerClick: () -> Unit,
    onImportClick: () -> Unit,
    onPrivacyClick: () -> Unit,
) {
    SettingsScreen(
        onAboutClick = onAboutClick,
        onBackClick = onBackClick,
        onDataClick = onDataClick,
        onHomeItemManagerClick = onHomeItemManagerClick,
        onImportClick = onImportClick,
        onPrivacyClick = onPrivacyClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    onAboutClick: () -> Unit,
    onBackClick: () -> Unit,
    onDataClick: () -> Unit,
    onHomeItemManagerClick: () -> Unit,
    onImportClick: () -> Unit,
    onPrivacyClick: () -> Unit,
) {
    var showThemeDialog by rememberSaveable { mutableStateOf(false) }

    if (showThemeDialog) {
        ThemeDialog {
            showThemeDialog = false
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "设置")
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        },
    ) { paddingValues ->
        Column(
            modifier = modifier
                .padding(paddingValues)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
        ) {
            SettingsTitle(title = "UI")
            Item(title = "主题设置", onClick = { showThemeDialog = true }, showBadge = false)
            SettingsTitle(title = "功能")
            Item(title = "同步数据", onClick = onDataClick, showBadge = false)
            Item(title = "导入数据", onClick = onImportClick, showBadge = false)
            Item(title = "栏目管理", onClick = onHomeItemManagerClick, showBadge = false)
            SettingsTitle(title = "其他")
            Item(title = "隐私政策", onClick = onPrivacyClick, showBadge = false)
            Item(title = "关于", onClick = onAboutClick, showBadge = false)
        }
    }
}

@Composable
private fun Item(
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit,
    showBadge: Boolean = false,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium
        )

        if (showBadge) {
            Badge(
                modifier = modifier.size(8.dp)
            ) { Text(text = "") }
        }
    }
}