/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.hefengbao.jingmo.ui.component.SimpleScaffold

@Composable
fun SharedUserInfoRoute(
    onBackClick: () -> Unit
) {
    SharedUserInfoScreen(onBackClick = onBackClick)
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun SharedUserInfoScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {

    val list = listOf(
        Info(
            name = "腾讯Bugly",
            company = "腾讯公司",
            info = "设备信息（包括设备型号、操作系统版本号）",
            useCase = "APP 发生奔溃等场景",
            purpose = "帮助开发者快速发现并解决异常",
            type = "本地采集",
            url = "https://privacy.qq.com/document/preview/fc748b3d96224fdb825ea79e132c1a56"
        )
    )
    SimpleScaffold(
        onBackClick = onBackClick,
        title = "第三方信息共享清单"
    ) {

        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
        ) {
            item {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = """
                        为保障 APP 稳定运行或实现相关功能，我们还可能会接入由第三方提供的软件开发包（SDK）实现前述目的。我们对接入的相关第三方 SDK 同时在以下清单中列明。你可以通过目录中提供的链接或者路径查看第三方的数据使用和保护规则。
                    """.trimIndent()
                )
            }
            items(
                items = list
            ) { info ->
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = info.name,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(fontWeight = FontWeight.Bold)
                            ) {
                                append("第三方主体：")
                            }
                            append(info.company)
                        }
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(fontWeight = FontWeight.Bold)
                            ) {
                                append("共享的信息：")
                            }
                            append(info.info)
                        }
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(fontWeight = FontWeight.Bold)
                            ) {
                                append("使用场景：")
                            }
                            append(info.useCase)
                        }
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(fontWeight = FontWeight.Bold)
                            ) {
                                append("使用目的：")
                            }
                            append(info.purpose)
                        }
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(fontWeight = FontWeight.Bold)
                            ) {
                                append("共享方式：")
                            }
                            append(info.type)
                        }
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(fontWeight = FontWeight.Bold)
                            ) {
                                append("第三方个人信息处理规则：")
                            }
                            withLink(
                                link = LinkAnnotation.Url(info.url)
                            ) {
                                append("[链接]")
                            }
                        }
                    )
                }
            }
        }
    }
}

private data class Info(
    val name: String,
    val company: String,
    val info: String,
    val useCase: String,
    val purpose: String,
    val type: String,
    val url: String
)