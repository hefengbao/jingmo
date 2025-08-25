/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.classicalliterature.people

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.entity.classicalliterature.PeopleEntity
import com.hefengbao.jingmo.data.repository.classicalliterature.PeopleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleIndexViewModel @Inject constructor(
    private val peopleRepository: PeopleRepository
) : ViewModel() {

    init {
        getRandom()
    }

    private val _peopleEntity: MutableStateFlow<PeopleEntity?> = MutableStateFlow(null)
    val peopleEntity: SharedFlow<PeopleEntity?> = _peopleEntity

    fun getRandom() {
        viewModelScope.launch {
            peopleRepository.getRandom().collectLatest {
                _peopleEntity.value = it
            }
        }
    }
}