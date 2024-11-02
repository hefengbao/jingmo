package com.hefengbao.jingmo.ui.component.webview

import android.util.Log
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.hefengbao.jingmo.common.ext.openURL
import com.hefengbao.jingmo.common.ext.surfaceColorAtElevation
import com.hefengbao.yuzhu.ui.component.webview.WebViewLayout

@Composable
fun RYWebView(
    modifier: Modifier = Modifier,
    content: String,
    refererDomain: String? = null,
    onImageClick: ((imgUrl: String, altText: String) -> Unit)? = null,
) {
    val context = LocalContext.current
    val maxWidth = LocalConfiguration.current.screenWidthDp.dp.value
    val backgroundColor = MaterialTheme.colorScheme
        .surfaceColorAtElevation(0.dp).toArgb()
    val selectionTextColor = Color.Black.toArgb()
    val selectionBgColor = MaterialTheme.colorScheme.tertiaryContainer.toArgb()
    val textColor: Int = MaterialTheme.colorScheme.onSurfaceVariant.toArgb()
    val textBold: Boolean = false
    val textAlign: String = "start"
    val textMargin: Int = 0
    val boldTextColor: Int = MaterialTheme.colorScheme.onSurface.toArgb()
    val linkTextColor: Int = MaterialTheme.colorScheme.primary.toArgb()
    val subheadBold: Boolean = false
    val subheadUpperCase: Boolean = false
    val fontSize: Int = 17
    val letterSpacing: Float = 0.5F
    val lineHeight: Float = 1.5F
    val imgMargin: Int = 24
    val imgBorderRadius: Int = 0
    val codeTextColor: Int = MaterialTheme.colorScheme.tertiary.toArgb()
    val codeBgColor: Int = MaterialTheme.colorScheme
        .surfaceColorAtElevation(6.dp).toArgb()
    val bionicReading = false

    val webView by remember(backgroundColor) {
        mutableStateOf(
            WebViewLayout.get(
                context = context,
                webViewClient = WebViewClient(
                    context = context,
                    refererDomain = refererDomain,
                    onOpenLink = { url ->
                        context.openURL(url)
                    }
                ),
                onImageClick = onImageClick
            )
        )
    }

    AndroidView(
        modifier = modifier,
        factory = { webView },
        update = {
            it.apply {
                Log.i("RLog", "maxWidth: ${maxWidth}")
                Log.i("RLog", "readingFont: ${context.filesDir.absolutePath}")
                Log.i("RLog", "CustomWebView: ${content}")
                settings.defaultFontSize = fontSize
                loadDataWithBaseURL(
                    null,
                    WebViewHtml.HTML.format(
                        WebViewStyle.get(
                            fontSize = fontSize,
                            lineHeight = lineHeight,
                            letterSpacing = letterSpacing,
                            textMargin = textMargin,
                            textColor = textColor,
                            textBold = textBold,
                            textAlign = textAlign,
                            boldTextColor = boldTextColor,
                            subheadBold = subheadBold,
                            subheadUpperCase = subheadUpperCase,
                            imgMargin = imgMargin,
                            imgBorderRadius = imgBorderRadius,
                            linkTextColor = linkTextColor,
                            codeTextColor = codeTextColor,
                            codeBgColor = codeBgColor,
                            tableMargin = textMargin,
                            selectionTextColor = selectionTextColor,
                            selectionBgColor = selectionBgColor,
                        ),
                        url,
                        content,
                        WebViewScript.get(bionicReading),
                    ),
                    "text/HTML",
                    "UTF-8", null
                )
            }
        },
    )
}
