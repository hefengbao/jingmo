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
import com.hefengbao.jingmo.data.repository.settings.HomeItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeItemManagerViewModel @Inject constructor(
    private val repository: HomeItemRepository
) : ViewModel() {

    private val _homeItem: MutableStateFlow<HomeItem> = MutableStateFlow(HomeItem())
    val homeItem: SharedFlow<HomeItem> = _homeItem

    init {
        viewModelScope.launch {
            repository.getHomeItem().collectLatest { _homeItem.value = it }
        }
    }

    fun setChinaWorldCultureHeritage(checked: Boolean) {
        viewModelScope.launch {
            repository.setChinaWorldCultureHeritage(checked)
        }
    }

    fun setClassicalLiteratureClassicPoem(checked: Boolean) {
        viewModelScope.launch {
            repository.setClassicalLiteratureClassicPoem(checked)
        }
    }

    fun setClassicalLiteraturePeople(checked: Boolean) {
        viewModelScope.launch {
            repository.setClassicalLiteraturePeople(checked)
        }
    }

    fun setClassicalLiteratureSentence(checked: Boolean) {
        viewModelScope.launch {
            repository.setClassicalLiteratureSentence(checked)
        }
    }

    fun setClassicalLiteratureWriting(checked: Boolean) {
        viewModelScope.launch {
            repository.setClassicalLiteratureWriting(checked)
        }
    }

    fun setChineseAntitheticalCouplet(checked: Boolean) {
        viewModelScope.launch {
            repository.setChineseAntitheticalCouplet(checked)
        }
    }

    fun setChineseCharacter(checked: Boolean) {
        viewModelScope.launch {
            repository.setChineseCharacter(checked)
        }
    }

    fun setChineseExpression(checked: Boolean) {
        viewModelScope.launch {
            repository.setChineseExpression(checked)
        }
    }

    fun setChineseIdiom(checked: Boolean) {
        viewModelScope.launch {
            repository.setChineseIdiom(checked)
        }
    }

    fun setChineseKnowledge(checked: Boolean) {
        viewModelScope.launch {
            repository.setChineseKnowledge(checked)
        }
    }

    fun setChineseLyric(checked: Boolean) {
        viewModelScope.launch {
            repository.setChineseLyric(checked)
        }
    }

    fun setChineseModernPoetry(checked: Boolean) {
        viewModelScope.launch {
            repository.setChineseModernPoetry(checked)
        }
    }

    fun setChineseProverb(checked: Boolean) {
        viewModelScope.launch {
            repository.setChineseProverb(checked)
        }
    }

    fun setChineseQuote(checked: Boolean) {
        viewModelScope.launch {
            repository.setChineseQuote(checked)
        }
    }

    fun setChineseRiddle(checked: Boolean) {
        viewModelScope.launch {
            repository.setChineseRiddle(checked)
        }
    }

    fun setChineseTongueTwister(checked: Boolean) {
        viewModelScope.launch {
            repository.setChineseTongueTwister(checked)
        }
    }

    fun setChineseWisecrack(checked: Boolean) {
        viewModelScope.launch {
            repository.setChineseWisecrack(checked)
        }
    }

    fun setTraditionalCultureCalendar(checked: Boolean) {
        viewModelScope.launch {
            repository.setTraditionalCultureCalendar(checked)
        }
    }

    fun setTraditionalCultureColor(checked: Boolean) {
        viewModelScope.launch {
            repository.setTraditionalCultureColor(checked)
        }
    }

    fun setTraditionalCultureFestival(checked: Boolean) {
        viewModelScope.launch {
            repository.setTraditionalCultureFestival(checked)
        }
    }

    fun setTraditionalCultureSolarTerm(checked: Boolean) {
        viewModelScope.launch {
            repository.setTraditionalCultureSolarTerm(checked)
        }
    }
}