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

    fun setClassicPoem(checked: Boolean) {
        viewModelScope.launch {
            homeRepository.setClassicPoem(checked)
        }
    }

    fun setWriting(checked: Boolean) {
        viewModelScope.launch {
            homeRepository.setWriting(checked)
        }
    }

    fun setPoemSentence(checked: Boolean) {
        viewModelScope.launch {
            homeRepository.setPoemSentence(checked)
        }
    }

    fun setIdiom(checked: Boolean) {
        viewModelScope.launch {
            homeRepository.setIdiom(checked)
        }
    }

    fun setChineseWisecrack(checked: Boolean) {
        viewModelScope.launch {
            homeRepository.setChineseWisecrack(checked)
        }
    }

    fun setTongueTwister(checked: Boolean) {
        viewModelScope.launch {
            homeRepository.setTongueTwister(checked)
        }
    }

    fun setFestival(checked: Boolean) {
        viewModelScope.launch {
            homeRepository.setFestival(checked)
        }
    }

    fun setSolarTerm(checked: Boolean) {
        viewModelScope.launch {
            homeRepository.setSolarTerm(checked)
        }
    }

    fun setChineseKnowledge(checked: Boolean) {
        viewModelScope.launch {
            homeRepository.setChineseKnowledge(checked)
        }
    }

    fun setPeople(checked: Boolean) {
        viewModelScope.launch {
            homeRepository.setPeople(checked)
        }
    }

    fun setChineseColor(checked: Boolean) {
        viewModelScope.launch {
            homeRepository.setChineseColor(checked)
        }
    }
}