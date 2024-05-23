package com.hefengbao.jingmo.ui.screen.chinesecharacter

import android.annotation.SuppressLint
import android.util.Log
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.hefengbao.jingmo.ui.component.SimpleScaffold


@Composable
fun ChineseCharacterStrokeRoute(
    onBackClick: () -> Unit
) {
    ChineseCharacterStrokeScreen(
        onBackClick = onBackClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("SetJavaScriptEnabled")
@Composable
private fun ChineseCharacterStrokeScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    SimpleScaffold(
        onBackClick = onBackClick,
        title = "汉字笔画"
    ) {
        var query by remember {
            mutableStateOf("墨")
        }

        Column(
            modifier = modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SearchBar(
                query = query,
                onQueryChange = {
                    if (it.length <= 1) {
                        query = it
                    }
                },
                onSearch = {},
                active = false,
                onActiveChange = {},
            ) {

            }
            AndroidView(
                factory = { context ->
                    WebView(context).apply {
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                        )
                        webViewClient = WebViewClient().apply {
                            settings.javaScriptEnabled = true
                        }
                        loadUrl("file:///android_asset/hanzi/index.html")
                    }
                },
                update = {
                    it.evaluateJavascript("javascript:callJS('$query')") { value ->
                        Log.e("ChineseCharacter", "onReceiveValue value = $value")
                    }
                }
            )
        }
    }
}