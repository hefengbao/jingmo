package com.hefengbao.jingmo.ui.screen.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.common.network.Result
import com.hefengbao.jingmo.data.datastore.DatasetPreference
import com.hefengbao.jingmo.data.model.Dataset
import com.hefengbao.jingmo.data.model.asChineseKnowledgeEntity
import com.hefengbao.jingmo.data.model.asChineseWisecrackEntity
import com.hefengbao.jingmo.data.model.asIdiomEntity
import com.hefengbao.jingmo.data.model.asPeopleEntity
import com.hefengbao.jingmo.data.model.asPoemSentenceEntity
import com.hefengbao.jingmo.data.model.asRiddleEntity
import com.hefengbao.jingmo.data.model.asTongueTwisterEntity
import com.hefengbao.jingmo.data.model.asWritingEntity
import com.hefengbao.jingmo.data.repository.NetworkDatasourceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataViewModel @Inject constructor(
    private val repository: NetworkDatasourceRepository,
    private val preference: DatasetPreference,
) : ViewModel() {

    val datasetPref = preference.datasetVersion

    private val _datasetResult: MutableStateFlow<Result<List<Dataset>>> =
        MutableStateFlow(Result.Loading)
    val datasetResult: SharedFlow<Result<List<Dataset>>> = _datasetResult
    fun getDataset() {
        viewModelScope.launch {
            _datasetResult.value = repository.dataset()
        }
    }

    private val _chineseKnowledgeResult: MutableStateFlow<SyncStatus<Any>> =
        MutableStateFlow(SyncStatus.NonStatus)
    val chineseKnowledgeResult: SharedFlow<SyncStatus<Any>> = _chineseKnowledgeResult
    private val _chineseKnowledgeResultProgress: MutableStateFlow<Float> = MutableStateFlow(0f)
    val chineseKnowledgeResultProgress: SharedFlow<Float> = _chineseKnowledgeResultProgress
    fun syncChineseKnowledge(total: Int, version: Int) {
        _chineseKnowledgeResult.value = SyncStatus.Loading
        viewModelScope.launch {
            when (val response = repository.syncChineseKnowledge()) {
                is Result.Error -> _chineseKnowledgeResult.value =
                    SyncStatus.Error(response.exception)

                Result.Loading -> {}
                is Result.Success -> {
                    var count = 0
                    response.data.map {
                        repository.insertChinesKnowledge(it.asChineseKnowledgeEntity())
                        count++
                        _chineseKnowledgeResultProgress.value = count.toFloat() / total
                    }
                }
            }
            preference.setChineseKnowledgeVersion(version)
            _chineseKnowledgeResult.value = SyncStatus.Success
        }
    }


    private val _chineseWisecracksResult: MutableStateFlow<SyncStatus<Any>> =
        MutableStateFlow(SyncStatus.NonStatus)
    val chineseWisecracksResult: SharedFlow<SyncStatus<Any>> = _chineseWisecracksResult
    private val _chineseWisecracksResultProgress: MutableStateFlow<Float> = MutableStateFlow(0f)
    val chineseWisecracksResultProgress: SharedFlow<Float> = _chineseWisecracksResultProgress
    fun syncChineseWisecracks(total: Int, version: Int) {
        _chineseWisecracksResult.value = SyncStatus.Loading
        viewModelScope.launch {
            when (val response = repository.syncChineseWisecracks()) {
                is Result.Error -> _chineseWisecracksResult.value =
                    SyncStatus.Error(response.exception)

                Result.Loading -> {}
                is Result.Success -> {
                    var count = 0
                    response.data.map {
                        repository.insertChineseWisecrack(it.asChineseWisecrackEntity())
                        count++
                        _chineseWisecracksResultProgress.value = count.toFloat() / total
                    }
                }
            }
            preference.setChineseWisecracksVersion(version)
            _chineseWisecracksResult.value = SyncStatus.Success
        }
    }

    private val _idiomsResult: MutableStateFlow<SyncStatus<Any>> =
        MutableStateFlow(SyncStatus.NonStatus)
    val idiomsResult: SharedFlow<SyncStatus<Any>> = _idiomsResult
    private val _idiomsResultProgress: MutableStateFlow<Float> = MutableStateFlow(0f)
    val idiomsResultProgress: SharedFlow<Float> = _idiomsResultProgress
    fun syncIdioms(total: Int, version: Int) {
        _idiomsResult.value = SyncStatus.Loading
        viewModelScope.launch {
            when (val response = repository.syncIdioms()) {
                is Result.Error -> {
                    Log.e("DataViewModel", "${response.exception?.message}")
                    _idiomsResult.value = SyncStatus.Error(response.exception)
                }

                Result.Loading -> {}
                is Result.Success -> {
                    var count = 0
                    response.data.map {
                        repository.insertIdiom(it.asIdiomEntity())
                        count++
                        _idiomsResultProgress.value = count.toFloat() / total
                    }
                }
            }
            preference.setIdiomsVersion(version)
            _idiomsResult.value = SyncStatus.Success
        }
    }

    private val _peopleResult: MutableStateFlow<SyncStatus<Any>> =
        MutableStateFlow(SyncStatus.NonStatus)
    val peopleResult: SharedFlow<SyncStatus<Any>> = _peopleResult
    private val _peopleResultProgress: MutableStateFlow<Float> = MutableStateFlow(0f)
    val peopleResultProgress: SharedFlow<Float> = _peopleResultProgress
    fun syncPeople(total: Int, version: Int) {
        _peopleResult.value = SyncStatus.Loading
        viewModelScope.launch {
            when (val response = repository.syncPeople()) {
                is Result.Error -> _peopleResult.value = SyncStatus.Error(response.exception)
                Result.Loading -> {}
                is Result.Success -> {
                    var count = 0
                    response.data.map {
                        repository.insertPeople(it.asPeopleEntity())
                        count++
                        _peopleResultProgress.value = count.toFloat() / total
                    }
                }
            }
            preference.setPeopleVersion(version)
            _peopleResult.value = SyncStatus.Success
        }
    }

    private val _poemSentencesResult: MutableStateFlow<SyncStatus<Any>> =
        MutableStateFlow(SyncStatus.NonStatus)
    val poemSentencesResult: SharedFlow<SyncStatus<Any>> = _poemSentencesResult
    private val _poemSentencesResultProgress: MutableStateFlow<Float> = MutableStateFlow(0f)
    val poemSentencesResultProgress: SharedFlow<Float> = _poemSentencesResultProgress
    fun syncPoemSentences(total: Int, version: Int) {
        _poemSentencesResult.value = SyncStatus.Loading
        viewModelScope.launch {
            when (val response = repository.syncPoemSentences()) {
                is Result.Error -> {
                    Log.e("DataViewModel", "${response.exception?.message}")
                    _poemSentencesResult.value = SyncStatus.Error(response.exception)
                }

                Result.Loading -> {}
                is Result.Success -> {
                    var count = 0
                    response.data.map {
                        repository.insertPoemSentence(it.asPoemSentenceEntity())
                        count++
                        _poemSentencesResultProgress.value = count.toFloat() / total
                    }
                }
            }
            preference.setPoemSentencesVersion(version)
            _poemSentencesResult.value = SyncStatus.Success
        }
    }

    private val _riddlesResult: MutableStateFlow<SyncStatus<Any>> =
        MutableStateFlow(SyncStatus.NonStatus)
    val riddlesResult: SharedFlow<SyncStatus<Any>> = _riddlesResult
    private val _riddlesResultProgress: MutableStateFlow<Float> = MutableStateFlow(0f)
    val riddlesResultProgress: SharedFlow<Float> = _riddlesResultProgress
    fun syncRiddles(total: Int, version: Int) {
        _riddlesResult.value = SyncStatus.Loading
        viewModelScope.launch {
            when (val response = repository.syncRiddles()) {
                is Result.Error -> _riddlesResult.value = SyncStatus.Error(response.exception)
                Result.Loading -> {}
                is Result.Success -> {
                    var count = 0
                    response.data.map {
                        repository.insertRiddle(it.asRiddleEntity())
                        count++
                        _riddlesResultProgress.value = count.toFloat() / total
                    }
                }
            }
            preference.setRiddlesVersion(version)
            _riddlesResult.value = SyncStatus.Success
        }
    }

    private val _tongueTwistersResult: MutableStateFlow<SyncStatus<Any>> =
        MutableStateFlow(SyncStatus.NonStatus)
    val tongueTwisterResult: SharedFlow<SyncStatus<Any>> = _tongueTwistersResult
    private val _tongueTwistersResultProgress: MutableStateFlow<Float> = MutableStateFlow(0f)
    val tongueTwistersResultProgress: SharedFlow<Float> = _tongueTwistersResultProgress
    fun syncTongueTwisters(total: Int, version: Int) {
        _tongueTwistersResult.value = SyncStatus.Loading
        viewModelScope.launch {
            when (val response = repository.syncTongueTwisters()) {
                is Result.Error -> _tongueTwistersResult.value =
                    SyncStatus.Error(response.exception)

                Result.Loading -> {}
                is Result.Success -> {
                    var count = 0
                    response.data.map {
                        repository.insertTongueTwister(it.asTongueTwisterEntity())
                        count++
                        _tongueTwistersResultProgress.value = count.toFloat() / total
                    }
                }
            }
            preference.setTongueTwistersVersion(version)
            _tongueTwistersResult.value = SyncStatus.Success
        }
    }

    private val _writingsResult: MutableStateFlow<SyncStatus<Any>> =
        MutableStateFlow(SyncStatus.NonStatus)
    val writingsResult: SharedFlow<SyncStatus<Any>> = _writingsResult
    private val _writingsResultProgress: MutableStateFlow<Float> = MutableStateFlow(0f)
    val writingsResultProgress: SharedFlow<Float> = _writingsResultProgress
    fun syncWritings(total: Int, version: Int) {
        _writingsResult.value = SyncStatus.Loading

        viewModelScope.launch {
            var page: Int? = 1
            var count = 0
            while (page != null) {
                when (val response = repository.syncWritings(page)) {
                    is Result.Error -> _writingsResult.value == response.exception
                    Result.Loading -> {}
                    is Result.Success -> {
                        if (response.data.nextPage != null) {
                            page++
                        } else {
                            page = null
                        }

                        response.data.data.map {
                            repository.insertWriting(it.asWritingEntity())
                            count++
                            _writingsResultProgress.value = count.toFloat() / total
                        }
                    }
                }
            }
            preference.setWritingsVersion(version)
            _writingsResult.value = SyncStatus.Success
        }
    }
}

sealed interface SyncStatus<out T> {
    data class Error(val exception: Throwable? = null) : SyncStatus<Nothing>
    data object Success : SyncStatus<Nothing>
    data object Loading : SyncStatus<Nothing>
    data object NonStatus : SyncStatus<Nothing>
}