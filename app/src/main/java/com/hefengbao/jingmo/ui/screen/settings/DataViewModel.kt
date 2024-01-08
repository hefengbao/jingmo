package com.hefengbao.jingmo.ui.screen.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hefengbao.jingmo.common.network.Result
import com.hefengbao.jingmo.data.repository.NetworkDatasourceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataViewModel @Inject constructor(
    private val repository: NetworkDatasourceRepository
) : ViewModel() {
    private val _chineseKnowledgeResult: MutableStateFlow<Result<Any>?> = MutableStateFlow(null)
    val chineseKnowledgeResult: SharedFlow<Result<Any>?> = _chineseKnowledgeResult
    fun syncChineseKnowledge(){
        _chineseKnowledgeResult.value = Result.Loading
        viewModelScope.launch {
            _chineseKnowledgeResult.value = repository.syncChineseKnowledge()
        }
    }

    private val _chineseWisecracksResult: MutableStateFlow<Result<Any>?> = MutableStateFlow(null)
    val chineseWisecracksResult: SharedFlow<Result<Any>?> = _chineseWisecracksResult
    fun syncChineseWisecracks(){
        _chineseWisecracksResult.value = Result.Loading
        viewModelScope.launch {
            _chineseWisecracksResult.value = repository.syncChineseWisecracks()
        }
    }

    private val _idioms: MutableStateFlow<Result<Any>?> = MutableStateFlow(null)
    val idioms: SharedFlow<Result<Any>?> = _idioms
    fun syncIdioms(){
        _idioms.value = Result.Loading
        viewModelScope.launch {
            _idioms.value = repository.syncIdioms()
        }
    }

    private val _syncPeopleResult: MutableStateFlow<Result<Any>?> = MutableStateFlow(null)
    val syncPeopleResult: SharedFlow<Result<Any>?> = _syncPeopleResult
    fun syncPeople(){
        _syncPeopleResult.value = Result.Loading
        viewModelScope.launch {
            _syncPeopleResult.value = repository.syncPeople()
        }
    }

    private val _poemSentencesResult: MutableStateFlow<Result<Any>?> = MutableStateFlow(null)
    val poemSentencesResult: SharedFlow<Result<Any>?> = _poemSentencesResult
    fun syncPoemSentences(){
        _syncWritingsResult.value = Result.Loading
        viewModelScope.launch {
            _poemSentencesResult.value = repository.syncPoemSentences()
        }
    }

    private val _syncRiddlesResult: MutableStateFlow<Result<Any>?> = MutableStateFlow(null)
    val syncRiddlesResult: SharedFlow<Result<Any>?> = _syncRiddlesResult
    fun syncRiddles(){
        _syncRiddlesResult.value = Result.Loading
        viewModelScope.launch {
            _syncRiddlesResult.value = repository.syncRiddles()
        }
    }

    private val _tongueTwistersResult: MutableStateFlow<Result<Any>?> = MutableStateFlow(null)
    val tongueTwistersResult: SharedFlow<Result<Any>?> = _tongueTwistersResult
    fun syncTongueTwisters(){
        _tongueTwistersResult.value = Result.Loading
        viewModelScope.launch {
            _tongueTwistersResult.value  = repository.syncTongueTwisters()
        }
    }

    private val _syncWritingsResult: MutableStateFlow<Result<Any>?> = MutableStateFlow(null)
    val syncWritingsResult: SharedFlow<Result<Any>?> = _syncWritingsResult
    fun syncWritings(){
        _syncWritingsResult.value = Result.Loading
        viewModelScope.launch {
            _syncWritingsResult.value = repository.syncWritings()
        }
    }
}