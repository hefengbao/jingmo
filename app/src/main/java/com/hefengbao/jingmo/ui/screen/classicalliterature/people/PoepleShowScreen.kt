/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.classicalliterature.people

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.entity.classicalliterature.PeopleEntity
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import com.hefengbao.jingmo.ui.screen.classicalliterature.people.components.PeoplePanel

@Composable
fun PeopleShowRoute(
    viewModel: PeopleShowViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {
    val peopleEntity by viewModel.peopleEntity.collectAsState(initial = null)

    PeopleShowScreen(
        onBackClick = onBackClick,
        peopleEntity = peopleEntity
    )
}

@Composable
private fun PeopleShowScreen(
    onBackClick: () -> Unit,
    peopleEntity: PeopleEntity?
) {
    SimpleScaffold(
        onBackClick = onBackClick,
        title = "人物资料"
    ) {
        peopleEntity?.let {
            PeoplePanel(people = it)
        }
    }
}