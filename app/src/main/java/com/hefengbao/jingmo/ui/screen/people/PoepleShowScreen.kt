package com.hefengbao.jingmo.ui.screen.people

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.entity.PeopleEntity
import com.hefengbao.jingmo.ui.component.SimpleScaffold

@Composable
fun PeopleShowRoute(
    viewModel: PeopleShowViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {
    val people by viewModel.people.collectAsState(initial = null)

    PeopleShowScreen(
        onBackClick = onBackClick,
        people = people
    )
}

@Composable
private fun PeopleShowScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    people: PeopleEntity?
) {
    people?.let { entity ->
        SimpleScaffold(
            onBackClick = onBackClick,
            title = entity.name
        ) {
            LazyColumn(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                val birthAndDeath = buildAnnotatedString {
                    if (entity.birthYear != null) {
                        if (entity.birthYear.toInt() < 0) {
                            append("公元前${entity.birthYear.toInt()}年")
                        } else {
                            append("公元${entity.birthYear}年")
                        }

                        if (entity.birthDay != null) {
                            if (entity.birthDay.contains("月")) {
                                append(entity.birthDay)
                            } else {
                                val arr = entity.birthDay.split("/")
                                if (arr.size == 2) {
                                    append("${arr[0]}月${arr[1]}日")
                                }
                            }
                        }
                    } else {
                        append("?")
                    }

                    append(" - ")

                    if (entity.deathYear != null) {
                        if (entity.deathYear.toInt() < 0) {
                            append("公元前${entity.deathYear.toInt()}年")
                        } else {
                            append("公元${entity.deathYear}年")
                        }

                        if (entity.deathDay != null) {
                            if (entity.deathDay.contains("月")) {
                                append(entity.deathDay)
                            } else {
                                val arr = entity.deathDay.split("/")
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
                        modifier = modifier.padding(16.dp, 8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "\uD83D\uDD3B 生卒年月",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(text = birthAndDeath)
                    }
                }

                item {
                    Column(
                        modifier = modifier.padding(16.dp, 8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "\uD83D\uDD3B 主要生活朝代",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(text = entity.dynasty)
                    }
                }

                if (!entity.aliases.isNullOrEmpty()) {
                    val aliases = entity.aliases.map {
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
                            modifier = modifier.padding(16.dp, 8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "\uD83D\uDD3B 别称",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(text = aliases)
                        }
                    }
                }

                if (!entity.titles.isNullOrEmpty()) {
                    item {
                        Column(
                            modifier = modifier.padding(16.dp, 8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "\uD83D\uDD3B 标签",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(text = entity.titles.joinToString("、"))
                        }
                    }
                }

                if (!entity.hometown.isNullOrEmpty()) {
                    item {
                        Column(
                            modifier = modifier.padding(16.dp, 8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "\uD83D\uDD3B 家乡",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(text = entity.hometown.map { return@map it.name }
                                .joinToString("、"))
                        }
                    }
                }

                if (!entity.details.isNullOrEmpty()) {
                    item {
                        Text(
                            text = "\uD83D\uDD3B 人物资料",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = modifier.padding(16.dp, 8.dp)
                        )
                    }

                    entity.details.forEach {
                        item {
                            Column(
                                modifier = modifier.padding(16.dp, 8.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(text = "📖 ${it.book}")

                                it.content?.let { content ->
                                    Text(
                                        text = content.replace("<br />", "\n")
                                            .replace("</p>", "\n")
                                            .replace("<[^>]+>".toRegex(), "")
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}