/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.settings

import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hefengbao.jingmo.R
import com.hefengbao.jingmo.common.util.ClipboardUtil
import com.hefengbao.jingmo.common.util.SystemUtil
import com.hefengbao.jingmo.ui.component.SimpleScaffold

@Composable
fun AboutRoute(
    onBackClick: () -> Unit,
) {
    val context = LocalContext.current
    val version = SystemUtil.getVersionName(context)

    AboutScreen(
        onBackClick = onBackClick,
        context = context,
        version = version
    )
}

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    context: Context,
    version: String?
) {
    SimpleScaffold(onBackClick = onBackClick, title = "关于") {
        SelectionContainer {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Column(
                        modifier = modifier.align(Alignment.Center),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_logo),
                            contentDescription = null,
                            modifier = modifier
                                .size(108.dp)
                        )
                        Text(
                            text = "当前版本：$version",
                            style = MaterialTheme.typography.labelSmall,
                            textAlign = TextAlign.Center,
                            modifier = modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                }

                Text(text = "❤贺丰宝（hefengbao）设计和编码❤")

                val desc = """
                『京墨』开源的中华文化宝典 APP。献给喜欢中华文字、文学、文化的人。
                
                本应用使用的数据来自网络（主要是 Github 开源的仓库），可在项目仓库查看 README.md 中的说明。
                
                源码仓库：
                
                https://github.com/hefengbao/jingmo
                
                https://gitee.com/hefengbao/jingmo
                
                关注公众号『NowInLife』获取更新消息。
                
                如果在使用过程中，遇到内容错误，欢迎在公众号留言说明，我们共同改进。
                
                如果有人想要♥赞助♥本项目，可在公众号文章中赞赏👍。
            """.trimIndent()

                Text(text = desc)

                val copyText = "NowInLife"

                Row(
                    modifier = modifier.padding(bottom = 48.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "公众号：$copyText")
                    Icon(
                        imageVector = Icons.Default.ContentCopy,
                        contentDescription = null,
                        modifier = modifier
                            .clickable {
                                ClipboardUtil.textCopyThenPost(context, copyText)
                            }
                            .padding(8.dp)
                    )
                }
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AboutScreenPreview() {
    AboutRoute(
        onBackClick = {}
    )
}