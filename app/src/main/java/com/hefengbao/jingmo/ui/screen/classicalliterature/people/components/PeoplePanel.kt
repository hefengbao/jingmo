/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.classicalliterature.people.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import com.hefengbao.jingmo.data.database.entity.classicalliterature.PeopleEntity
import com.hefengbao.jingmo.ui.component.EmphasizedTitle

private val HTML_TAG_REGEX = "<[^>]+>".toRegex()

@Composable
fun PeoplePanel(
    modifier: Modifier = Modifier,
    people: PeopleEntity
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        val birthAndDeath = buildAnnotatedString {
            if (people.birthYear != null) {
                if (people.birthYear.toInt() < 0) {
                    append("公元前${people.birthYear.toInt()}年")
                } else {
                    append("公元${people.birthYear}年")
                }

                if (people.birthDay != null) {
                    if (people.birthDay.contains("月")) {
                        append(people.birthDay)
                    } else {
                        val arr = people.birthDay.split("/")
                        if (arr.size == 2) {
                            append("${arr[0]}月${arr[1]}日")
                        }
                    }
                }
            } else {
                append("?")
            }

            append(" - ")

            if (people.deathYear != null) {
                if (people.deathYear.toInt() < 0) {
                    append("公元前${people.deathYear.toInt()}年")
                } else {
                    append("公元${people.deathYear}年")
                }

                if (people.deathDay != null) {
                    if (people.deathDay.contains("月")) {
                        append(people.deathDay)
                    } else {
                        val arr = people.deathDay.split("/")
                        if (arr.size == 2) {
                            append("${arr[0]}月${arr[1]}日")
                        }
                    }
                }
            } else {
                append("?")
            }
        }

        item {
            Column(
                modifier = Modifier.padding(16.dp, 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = people.name, style = MaterialTheme.typography.titleMedium)
                EmphasizedTitle(title = "生卒年月")
                Text(text = birthAndDeath)
            }
        }

        item {
            Column(
                modifier = Modifier.padding(16.dp, 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                EmphasizedTitle(title = "主要生活朝代")
                Text(text = people.dynasty)
            }
        }

        if (!people.aliases.isNullOrEmpty()) {
            val aliases = people.aliases.map {
                val type = when (it.type) {
                    "BieCheng" -> "别称"
                    "Xing" -> "姓"
                    "Zi" -> "字"
                    "Hao" -> "号"
                    "Ming" -> "名"
                    "ShiHao" -> "谥号"
                    "FengJue" -> "封号"
                    "FamousName" -> "世称"
                    "PreUsedName" -> "曾用名"
                    "XingMing" -> "姓名"
                    else -> "其他"
                }

                return@map "$type:${it.name}\n"
            }.joinToString("")
            item {
                Column(
                    modifier = Modifier.padding(16.dp, 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    EmphasizedTitle(title = "别称")
                    Text(text = aliases)
                }
            }
        }

        if (!people.titles.isNullOrEmpty()) {
            item {
                Column(
                    modifier = Modifier.padding(16.dp, 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    EmphasizedTitle(title = "标签")
                    Text(text = people.titles.joinToString("、"))
                }
            }
        }

        if (!people.hometown.isNullOrEmpty()) {
            item {
                Column(
                    modifier = Modifier.padding(16.dp, 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    EmphasizedTitle(title = "家乡")
                    Text(text = people.hometown.map { return@map it.name }
                        .joinToString("、"))
                }
            }
        }

        if (!people.details.isNullOrEmpty()) {
            item {
                Row(modifier = Modifier.padding(horizontal = 16.dp)) {
                    EmphasizedTitle(title = "人物资料")
                }
            }

            people.details.forEach {
                item {
                    Column(
                        modifier = Modifier.padding(16.dp, 8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(text = "📖 ${it.book}")

                        it.content?.let { content ->
                            Text(
                                text = content.replace("<br />", "\n")
                                    .replace("<br/>", "\n")
                                    .replace("<br>", "\n")
                                    .replace("</p>", "\n")
                                    .replace(HTML_TAG_REGEX, "")
                            )
                        }
                    }
                }
            }
        }
    }
}