package com.hefengbao.jingmo.ui.screen.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hefengbao.jingmo.ui.component.SimpleScaffold

@Composable
fun CollectedUserInfoRoute(
    onBackClick: () -> Unit
) {
    CollectedUserInfoScreen(onBackClick = onBackClick)
}

@Composable
private fun CollectedUserInfoScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    SimpleScaffold(
        onBackClick = onBackClick,
        title = "个人信息收集清单"
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("不收集、保存任何用户信息。")
        }
    }
}