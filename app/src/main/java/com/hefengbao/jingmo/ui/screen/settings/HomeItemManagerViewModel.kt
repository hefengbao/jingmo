/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.model.HomeItem
import com.hefengbao.jingmo.data.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeItemManagerViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {

    private val _homeItem: MutableStateFlow<HomeItem> = MutableStateFlow(HomeItem())
    val homeItem: SharedFlow<HomeItem> = _homeItem

    init {
        viewModelScope.launch {
            homeRepository.getHomeItem().collectLatest { _homeItem.value = it }
        }
    }

    fun setClassicalLiteratureClassicPoem(checked: Boolean) {
        viewModelScope.launch {
            homeRepository.setClassicalLiteratureClassicPoem(checked)
        }
    }

    fun setClassicalLiteraturePeople(checked: Boolean) {
        viewModelScope.launch {
            homeRepository.setClassicalLiteraturePeople(checked)
        }
    }

    fun setClassicalLiteratureSentence(checked: Boolean) {
        viewModelScope.launch {
            homeRepository.setClassicalLiteratureSentence(checked)
        }
    }

    fun setClassicalLiteratureWriting(checked: Boolean) {
        viewModelScope.launch {
            homeRepository.setClassicalLiteratureWriting(checked)
        }
    }


    fun setChineseCharacter(checked: Boolean) {
        viewModelScope.launch {
            homeRepository.setChineseCharacter(checked)
        }
    }

    fun setChineseExpression(checked: Boolean) {
        viewModelScope.launch {
            homeRepository.setChineseExpression(checked)
        }
    }

    fun setChineseIdiom(checked: Boolean) {
        viewModelScope.launch {
            homeRepository.setChineseIdiom(checked)
        }
    }

    fun setChineseKnowledge(checked: Boolean) {
        viewModelScope.launch {
            homeRepository.setChineseKnowledge(checked)
        }
    }

    fun setChineseLyric(checked: Boolean) {
        viewModelScope.launch {
            homeRepository.setChineseLyric(checked)
        }
    }

    fun setChineseProverb(checked: Boolean) {
        viewModelScope.launch {
            homeRepository.setChineseProverb(checked)
        }
    }

    fun setChineseTongueTwister(checked: Boolean) {
        viewModelScope.launch {
            homeRepository.setChineseTongueTwister(checked)
        }
    }

    fun setChineseWisecrack(checked: Boolean) {
        viewModelScope.launch {
            homeRepository.setChineseWisecrack(checked)
        }
    }

    fun setTraditionalCultureColor(checked: Boolean) {
        viewModelScope.launch {
            homeRepository.setTraditionalCultureColor(checked)
        }
    }

    fun setTraditionalCultureFestival(checked: Boolean) {
        viewModelScope.launch {
            homeRepository.setTraditionalCultureFestival(checked)
        }
    }

    fun setTraditionalCultureSolarTerm(checked: Boolean) {
        viewModelScope.launch {
            homeRepository.setTraditionalCultureSolarTerm(checked)
        }
    }
}