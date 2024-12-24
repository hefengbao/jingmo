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
import com.hefengbao.jingmo.common.network.Result
import com.hefengbao.jingmo.data.database.entity.chinese.DictionaryPinyinEntity
import com.hefengbao.jingmo.data.model.Dataset
import com.hefengbao.jingmo.data.model.china.asWorldCulturalHeritageEntity
import com.hefengbao.jingmo.data.model.chinese.asAntitheticalCoupletEntity
import com.hefengbao.jingmo.data.model.chinese.asChineseExpressionEntity
import com.hefengbao.jingmo.data.model.chinese.asChineseKnowledgeEntity
import com.hefengbao.jingmo.data.model.chinese.asChineseWisecrackEntity
import com.hefengbao.jingmo.data.model.chinese.asDictionaryEntity
import com.hefengbao.jingmo.data.model.chinese.asIdiomEntity
import com.hefengbao.jingmo.data.model.chinese.asLyricEntity
import com.hefengbao.jingmo.data.model.chinese.asModernPoetryEntity
import com.hefengbao.jingmo.data.model.chinese.asProverbEntity
import com.hefengbao.jingmo.data.model.chinese.asQuoteEntity
import com.hefengbao.jingmo.data.model.chinese.asRiddleEntity
import com.hefengbao.jingmo.data.model.chinese.asTongueTwisterEntity
import com.hefengbao.jingmo.data.model.classicalliterature.asClassicPoemEntity
import com.hefengbao.jingmo.data.model.classicalliterature.asPeopleEntity
import com.hefengbao.jingmo.data.model.classicalliterature.asPoemSentenceEntity
import com.hefengbao.jingmo.data.model.classicalliterature.asWritingEntity
import com.hefengbao.jingmo.data.repository.settings.NetworkDatasourceRepository
import com.hefengbao.jingmo.data.repository.settings.PreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataViewModel @Inject constructor(
    private val repository: NetworkDatasourceRepository,
    private val preference: PreferenceRepository,
) : ViewModel() {
    val datasetPref = preference.getDatasetStatus()

    private val _datasetResult: MutableStateFlow<Result<List<Dataset>>> =
        MutableStateFlow(Result.Loading)
    val datasetResult: SharedFlow<Result<List<Dataset>>> = _datasetResult
    fun getDataset() {
        viewModelScope.launch {
            _datasetResult.value = repository.dataset()
        }
    }

    private val _chinaWorldCultureHeritageResult: MutableStateFlow<SyncStatus<Any>> =
        MutableStateFlow(SyncStatus.NonStatus)
    val chinaWorldCultureHeritageResult: SharedFlow<SyncStatus<Any>> =
        _chinaWorldCultureHeritageResult
    private val _chinaWorldCultureHeritageProgress: MutableStateFlow<Float> = MutableStateFlow(0f)
    val chinaWorldCultureHeritageProgress: SharedFlow<Float> = _chinaWorldCultureHeritageProgress
    fun syncChinaWorldCultureHeritage(total: Int, version: Int) {
        viewModelScope.launch {
            var count = 0
            when (val response = repository.syncChinaWorldCultureHeritage(version)) {
                is Result.Error -> {
                    _chinaWorldCultureHeritageResult.value = SyncStatus.Error(response.exception)
                }

                Result.Loading -> {
                    _chinaWorldCultureHeritageResult.value = SyncStatus.Loading
                }

                is Result.Success -> {
                    response.data.map {
                        repository.insertChinaWorldCultureHeritage(it.asWorldCulturalHeritageEntity())
                        count++
                        _chinaWorldCultureHeritageProgress.value = count.toFloat() / total
                    }

                    preference.setChinaWorldCultureHeritageVersion(version)
                    _chinaWorldCultureHeritageResult.value = SyncStatus.Success
                }
            }
        }
    }

    private val _chineseAntitheticalCoupletResult: MutableStateFlow<SyncStatus<Any>> =
        MutableStateFlow(SyncStatus.NonStatus)
    val chineseAntitheticalCoupletResult: SharedFlow<SyncStatus<Any>> =
        _chineseAntitheticalCoupletResult
    private val _chineseAntitheticalCoupletProgress: MutableStateFlow<Float> = MutableStateFlow(0f)
    val chineseAntitheticalCoupletProgress: SharedFlow<Float> = _chineseAntitheticalCoupletProgress
    fun syncChineseAntitheticalCouplet(total: Int, version: Int) {
        _chineseAntitheticalCoupletResult.value = SyncStatus.Loading
        viewModelScope.launch {
            var count = 0
            when (val response = repository.syncChineseAntitheticalCouplets(version)) {
                is Result.Error -> {
                    _chineseAntitheticalCoupletResult.value = SyncStatus.Error(response.exception)
                }

                Result.Loading -> {}
                is Result.Success -> {
                    response.data.map {
                        repository.insertChineseAntitheticalCouplet(it.asAntitheticalCoupletEntity())
                        count++
                        _chineseAntitheticalCoupletProgress.value = count.toFloat() / total
                    }

                    preference.setChineseAntitheticalVersion(version)
                    _chineseAntitheticalCoupletResult.value = SyncStatus.Success
                }
            }
        }
    }

    private val _chineseExpressionResult: MutableStateFlow<SyncStatus<Any>> =
        MutableStateFlow(SyncStatus.NonStatus)
    val chineseExpressionResult: SharedFlow<SyncStatus<Any>> = _chineseExpressionResult
    private val _chineseExpressionResultProgress: MutableStateFlow<Float> = MutableStateFlow(0f)
    val chineseExpressionResultProgress: SharedFlow<Float> = _chineseExpressionResultProgress
    fun syncChineseExpression(total: Int, version: Int) {
        _chineseExpressionResult.value = SyncStatus.Loading
        viewModelScope.launch {
            var page: Int? = 1
            var count = 0
            while (page != null) {
                when (val response = repository.syncChineseExpressions(version, page)) {
                    is Result.Error -> {
                        _chineseExpressionResult.value = SyncStatus.Error(response.exception)
                    }

                    Result.Loading -> {}
                    is Result.Success -> {
                        if (response.data.nextPage != null) {
                            page++
                        } else {
                            page = null
                        }
                        response.data.data.map {
                            repository.insertChineseExpression(it.asChineseExpressionEntity())
                            count++
                            _chineseExpressionResultProgress.value = count.toFloat() / total
                        }
                    }
                }
            }
            // TODO 这里需优化
            preference.setChineseExpressionVersion(version)
            _chineseExpressionResult.value = SyncStatus.Success
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
            when (val response = repository.syncChineseKnowledge(version)) {
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

                    preference.setChineseKnowledgeVersion(version)
                    _chineseKnowledgeResult.value = SyncStatus.Success
                }
            }
        }
    }

    private val _chineseModernPoetryResult: MutableStateFlow<SyncStatus<Any>> =
        MutableStateFlow(SyncStatus.NonStatus)
    val chineseModernPoetryResult: SharedFlow<SyncStatus<Any>> = _chineseModernPoetryResult
    private val _chineseModernPoetryResultProgress: MutableStateFlow<Float> = MutableStateFlow(0f)
    val chineseModernPoetryResultProgress: SharedFlow<Float> = _chineseModernPoetryResultProgress
    fun syncChineseModernPoetry(total: Int, version: Int) {
        _chineseModernPoetryResult.value = SyncStatus.Loading
        viewModelScope.launch {
            when (val response = repository.syncChineseModernPoetry(version)) {
                is Result.Error -> _chineseModernPoetryResult.value =
                    SyncStatus.Error(response.exception)

                Result.Loading -> {}
                is Result.Success -> {
                    var count = 0
                    response.data.map {
                        repository.insertChineseModernPoetry(it.asModernPoetryEntity())
                        count++
                        _chineseModernPoetryResultProgress.value = count.toFloat() / total
                    }
                    preference.setChineseModernPoetryVersion(version)
                    _chineseModernPoetryResult.value = SyncStatus.Success
                }
            }
        }
    }

    private val _chineseProverbResult: MutableStateFlow<SyncStatus<Any>> =
        MutableStateFlow(SyncStatus.NonStatus)
    val chineseProverbResult: SharedFlow<SyncStatus<Any>> = _chineseProverbResult
    private val _chineseProverbResultProgress: MutableStateFlow<Float> = MutableStateFlow(0f)
    val chineseProverbResultProgress: SharedFlow<Float> = _chineseProverbResultProgress
    fun syncChineseProverbs(total: Int, version: Int) {
        _chineseProverbResult.value = SyncStatus.Loading
        viewModelScope.launch {
            when (val response = repository.syncChineseProverbs(version)) {
                is Result.Error -> _chineseProverbResult.value =
                    SyncStatus.Error(response.exception)

                Result.Loading -> {}
                is Result.Success -> {
                    var count = 0
                    response.data.map {
                        repository.insertChineseProverb(it.asProverbEntity())
                        count++
                        _chineseProverbResultProgress.value = count.toFloat() / total
                    }
                    preference.setChineseProverbVersion(version)
                    _chineseProverbResult.value = SyncStatus.Success
                }
            }
        }
    }

    private val _chineseQuotesResult: MutableStateFlow<SyncStatus<Any>> =
        MutableStateFlow(SyncStatus.NonStatus)
    val chineseQuotesResult: SharedFlow<SyncStatus<Any>> = _chineseQuotesResult
    private val _chineseQuotesResultProgress: MutableStateFlow<Float> = MutableStateFlow(0f)
    val chineseQuotesResultProgress: SharedFlow<Float> = _chineseQuotesResultProgress
    fun syncChineseQuotes(total: Int, version: Int) {
        _chineseQuotesResult.value = SyncStatus.Loading
        viewModelScope.launch {
            when (val response = repository.syncChineseQuotes(version)) {
                is Result.Error -> _chineseQuotesResult.value =
                    SyncStatus.Error(response.exception)

                Result.Loading -> {}
                is Result.Success -> {
                    var count = 0
                    response.data.map {
                        repository.insertChineseQuote(it.asQuoteEntity())
                        count++
                        _chineseQuotesResultProgress.value = count.toFloat() / total
                    }
                    preference.setChineseQuoteVersion(version)
                    _chineseQuotesResult.value = SyncStatus.Success
                }
            }
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
            when (val response = repository.syncChineseWisecracks(version)) {
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

                    preference.setChineseWisecracksVersion(version)
                    _chineseWisecracksResult.value = SyncStatus.Success
                }
            }
        }
    }

    private val _dictionaryResult: MutableStateFlow<SyncStatus<Any>> =
        MutableStateFlow(SyncStatus.NonStatus)
    val dictionaryResult: SharedFlow<SyncStatus<Any>> = _dictionaryResult
    private val _dictionaryResultProgress: MutableStateFlow<Float> = MutableStateFlow(0f)
    val dictionaryResultProgress: SharedFlow<Float> = _dictionaryResultProgress
    fun syncChineseDictionary(total: Int, version: Int) {
        viewModelScope.launch {
            repository.clearChineseDictionaryPinyin()
        }
        _dictionaryResult.value = SyncStatus.Loading
        viewModelScope.launch {
            var page: Int? = 1
            var count = 0
            while (page != null) {
                when (val response = repository.syncChineseDictionary(version, page)) {
                    is Result.Error -> _dictionaryResult.value =
                        SyncStatus.Error(response.exception)

                    Result.Loading -> {}
                    is Result.Success -> {
                        if (response.data.nextPage != null) {
                            page++
                        } else {
                            page = null
                        }
                        response.data.data.map {
                            repository.insertChineseDictionary(it.asDictionaryEntity())
                            it.pinyin2?.map { pinyin ->
                                repository.insertChineseDictionaryPinyin(
                                    DictionaryPinyinEntity(
                                        dictionaryId = it.id,
                                        pinyin = pinyin,
                                    )
                                )
                            }
                            count++
                            _dictionaryResultProgress.value = count.toFloat() / total
                        }
                    }
                }
            }
            // TODO 这里需优化
            preference.setChineseDictionaryVersion(version)
            _dictionaryResult.value = SyncStatus.Success
        }
    }

    private val _idiomsResult: MutableStateFlow<SyncStatus<Any>> =
        MutableStateFlow(SyncStatus.NonStatus)
    val idiomsResult: SharedFlow<SyncStatus<Any>> = _idiomsResult
    private val _idiomsResultProgress: MutableStateFlow<Float> = MutableStateFlow(0f)
    val idiomsResultProgress: SharedFlow<Float> = _idiomsResultProgress
    fun syncChineseIdioms(total: Int, version: Int) {
        _idiomsResult.value = SyncStatus.Loading
        viewModelScope.launch {
            var page: Int? = 1
            var count = 0

            while (page != null) {
                when (val response = repository.syncChineseIdioms(version, page)) {
                    is Result.Error -> {
                        _idiomsResult.value = SyncStatus.Error(response.exception)
                    }

                    Result.Loading -> {}
                    is Result.Success -> {
                        if (response.data.nextPage != null) {
                            page++
                        } else {
                            page = null
                        }

                        response.data.data.map {
                            repository.insertChineseIdiom(it.asIdiomEntity())
                            count++
                            _idiomsResultProgress.value = count.toFloat() / total
                        }
                    }
                }
            }

            // TODO 这里需优化
            preference.setChineseIdiomVersion(version)
            _idiomsResult.value = SyncStatus.Success
        }
    }

    private val _lyricResult: MutableStateFlow<SyncStatus<Any>> =
        MutableStateFlow(SyncStatus.NonStatus)
    val lyricResult: SharedFlow<SyncStatus<Any>> = _lyricResult
    private val _lyricResultProgress: MutableStateFlow<Float> = MutableStateFlow(0f)
    val lyricResultProgress: SharedFlow<Float> = _lyricResultProgress
    fun syncChineseLyric(total: Int, version: Int) {
        _lyricResult.value = SyncStatus.Loading
        viewModelScope.launch {
            when (val response = repository.syncChineseLyrics(version)) {
                is Result.Error -> {
                    _lyricResult.value = SyncStatus.Error(response.exception)
                }

                Result.Loading -> {}
                is Result.Success -> {
                    var count = 0

                    response.data.map {
                        repository.insertChineseLyric(it.asLyricEntity())
                        count++
                        _lyricResultProgress.value = count.toFloat() / total
                    }

                    preference.setChineseLyricVersion(version)
                    _lyricResult.value = SyncStatus.Success
                }
            }
        }
    }

    private val _peopleResult: MutableStateFlow<SyncStatus<Any>> =
        MutableStateFlow(SyncStatus.NonStatus)
    val peopleResult: SharedFlow<SyncStatus<Any>> = _peopleResult
    private val _peopleResultProgress: MutableStateFlow<Float> = MutableStateFlow(0f)
    val peopleResultProgress: SharedFlow<Float> = _peopleResultProgress
    fun syncClassicalLiteraturePeople(total: Int, version: Int) {
        _peopleResult.value = SyncStatus.Loading
        viewModelScope.launch {
            var page: Int? = 1
            var count = 0
            while (page != null) {
                when (val response = repository.syncClassicalLiteraturePeople(version, page)) {
                    is Result.Error -> _peopleResult.value = SyncStatus.Error(response.exception)
                    Result.Loading -> {}
                    is Result.Success -> {
                        if (response.data.nextPage != null) {
                            page++
                        } else {
                            page = null
                        }
                        response.data.data.map {
                            repository.insertClassicalLiteraturePeople(it.asPeopleEntity())
                            count++
                            _peopleResultProgress.value = count.toFloat() / total
                        }
                    }
                }
            }
            // TODO 这里需优化
            preference.setClassicalLiteraturePeopleVersion(version)
            _peopleResult.value = SyncStatus.Success
        }
    }

    private val _classicPoemsResult: MutableStateFlow<SyncStatus<Any>> =
        MutableStateFlow(SyncStatus.NonStatus)
    val classicPoemsResult: SharedFlow<SyncStatus<Any>> = _classicPoemsResult
    private val _classicPoemsResultProgress: MutableStateFlow<Float> = MutableStateFlow(0f)
    val classicPoemsResultProgress: SharedFlow<Float> = _classicPoemsResultProgress
    fun syncClassicalLiteratureClassicPoems(total: Int, version: Int) {
        _classicPoemsResult.value = SyncStatus.Loading
        viewModelScope.launch {
            when (val response = repository.syncClassicalLiteratureClassicPoems(version)) {
                is Result.Error -> {
                    _classicPoemsResult.value = SyncStatus.Error(response.exception)
                }

                Result.Loading -> {}
                is Result.Success -> {
                    var count = 0
                    response.data.map {
                        repository.insertClassicalLiteratureClassicPoem(it.asClassicPoemEntity())
                        count++
                        _classicPoemsResultProgress.value = count.toFloat() / total
                    }

                    preference.setClassicalLiteratureClassicPoemsVersion(version)
                    _classicPoemsResult.value = SyncStatus.Success
                }
            }
        }
    }

    private val _poemSentencesResult: MutableStateFlow<SyncStatus<Any>> =
        MutableStateFlow(SyncStatus.NonStatus)
    val poemSentencesResult: SharedFlow<SyncStatus<Any>> = _poemSentencesResult
    private val _poemSentencesResultProgress: MutableStateFlow<Float> = MutableStateFlow(0f)
    val poemSentencesResultProgress: SharedFlow<Float> = _poemSentencesResultProgress
    fun syncClassicalLiteratureSentences(total: Int, version: Int) {
        _poemSentencesResult.value = SyncStatus.Loading
        viewModelScope.launch {
            when (val response = repository.syncClassicalLiteratureSentence(version)) {
                is Result.Error -> {
                    _poemSentencesResult.value = SyncStatus.Error(response.exception)
                }

                Result.Loading -> {}
                is Result.Success -> {
                    var count = 0
                    response.data.map {
                        repository.insertClassicalLiteratureSentence(it.asPoemSentenceEntity())
                        count++
                        _poemSentencesResultProgress.value = count.toFloat() / total
                    }

                    preference.setClassicalLiteratureSentenceVersion(version)
                    _poemSentencesResult.value = SyncStatus.Success
                }
            }
        }
    }

    private val _riddlesResult: MutableStateFlow<SyncStatus<Any>> =
        MutableStateFlow(SyncStatus.NonStatus)
    val riddlesResult: SharedFlow<SyncStatus<Any>> = _riddlesResult
    private val _riddlesResultProgress: MutableStateFlow<Float> = MutableStateFlow(0f)
    val riddlesResultProgress: SharedFlow<Float> = _riddlesResultProgress
    fun syncChineseRiddles(total: Int, version: Int) {
        _riddlesResult.value = SyncStatus.Loading
        viewModelScope.launch {
            when (val response = repository.syncChineseRiddles(version)) {
                is Result.Error -> _riddlesResult.value = SyncStatus.Error(response.exception)
                Result.Loading -> {}
                is Result.Success -> {
                    var count = 0
                    response.data.map {
                        repository.insertChineseRiddle(it.asRiddleEntity())
                        count++
                        _riddlesResultProgress.value = count.toFloat() / total
                    }

                    preference.setChineseRiddleVersion(version)
                    _riddlesResult.value = SyncStatus.Success
                }
            }
        }
    }

    private val _tongueTwistersResult: MutableStateFlow<SyncStatus<Any>> =
        MutableStateFlow(SyncStatus.NonStatus)
    val tongueTwisterResult: SharedFlow<SyncStatus<Any>> = _tongueTwistersResult
    private val _tongueTwistersResultProgress: MutableStateFlow<Float> = MutableStateFlow(0f)
    val tongueTwistersResultProgress: SharedFlow<Float> = _tongueTwistersResultProgress
    fun syncChineseTongueTwisters(total: Int, version: Int) {
        _tongueTwistersResult.value = SyncStatus.Loading
        viewModelScope.launch {
            when (val response = repository.syncChineseTongueTwisters(version)) {
                is Result.Error -> _tongueTwistersResult.value =
                    SyncStatus.Error(response.exception)

                Result.Loading -> {}
                is Result.Success -> {
                    var count = 0
                    response.data.map {
                        repository.insertChineseTongueTwister(it.asTongueTwisterEntity())
                        count++
                        _tongueTwistersResultProgress.value = count.toFloat() / total
                    }

                    preference.setChineseTongueTwisterVersion(version)
                    _tongueTwistersResult.value = SyncStatus.Success
                }
            }
        }
    }

    private val _writingsResult: MutableStateFlow<SyncStatus<Any>> =
        MutableStateFlow(SyncStatus.NonStatus)
    val writingsResult: SharedFlow<SyncStatus<Any>> = _writingsResult
    private val _writingsResultProgress: MutableStateFlow<Float> = MutableStateFlow(0f)
    val writingsResultProgress: SharedFlow<Float> = _writingsResultProgress

    private var writingCurrentPage: MutableStateFlow<Int> = MutableStateFlow(1)
    private var writingCurrentCount: MutableStateFlow<Int> = MutableStateFlow(0)
    fun setWritingsPreviousPage(page: Int) {
        writingCurrentPage.value = page
    }

    fun setWritingsPreviousCount(count: Int, total: Int) {
        writingCurrentCount.value = count
        _writingsResultProgress.value =
            writingCurrentCount.value.toFloat() / total
    }

    fun syncClassicalLiteratureWritings(total: Int, version: Int) {
        _writingsResult.value = SyncStatus.Loading

        viewModelScope.launch {

            while (writingCurrentPage.value != 0) {
                when (
                    val response = repository.syncClassicalLiteratureWritings(
                        version,
                        writingCurrentPage.value
                    )
                ) {
                    is Result.Error -> _writingsResult.value == SyncStatus.Error(response.exception)
                    Result.Loading -> {}
                    is Result.Success -> {
                        repository.insertClassicalLiteratureWriting(response.data.data.map { it.asWritingEntity() })

                        writingCurrentCount.value += response.data.data.size
                        _writingsResultProgress.value = writingCurrentCount.value.toFloat() / total

                        // 记录进度
                        preference.setClassicalLiteratureWritingCurrentPage(writingCurrentPage.value)
                        preference.setClassicalLiteratureWritingCurrentCount(writingCurrentCount.value)

                        if (response.data.nextPage != null) {
                            writingCurrentPage.value = response.data.nextPage
                        } else {
                            writingCurrentPage.value = 0
                        }

                    }
                }
            }
            preference.setClassicalLiteratureWritingVersion(version)
            preference.setClassicalLiteratureWritingCurrentPage(1)
            preference.setClassicalLiteratureWritingCurrentCount(0)
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