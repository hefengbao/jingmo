/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.settings

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withLink
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.hefengbao.jingmo.BuildConfig
import com.hefengbao.jingmo.ui.component.SimpleScaffold

@Composable
fun PrivacyRoute(
    onBackClick: () -> Unit
) {
    PrivacyScreen(onBackClick = onBackClick)
}

@Composable
private fun PrivacyScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    SimpleScaffold(
        onBackClick = onBackClick,
        title = "隐私政策"
    ){
        Column(
            modifier = modifier
                .fillMaxWidth()
        ) {
            AndroidView(
                factory = { context ->
                    WebView(context).apply {
                        settings.javaScriptEnabled = true
                        settings.domStorageEnabled = true  // 启用 DOM 存储
                        settings.loadWithOverviewMode = true  // 适应屏幕大小
                        settings.useWideViewPort = true  // 启用广泛视图模式

                        // 设置 WebViewClient 以防止外部浏览器打开链接
                        webViewClient = object : WebViewClient(){
                            override fun onPageFinished(view: WebView?, url: String?) {
                                super.onPageFinished(view, url)
                            }
                        }

                        loadUrl(BuildConfig.USER_AGREEMENT_URL)
                    }
                }
            )
        }
    }
}