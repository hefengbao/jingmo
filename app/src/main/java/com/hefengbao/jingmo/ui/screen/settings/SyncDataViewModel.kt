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
import com.hefengbao.jingmo.data.model.Dataset
import com.hefengbao.jingmo.data.model.china.asWorldCulturalHeritageEntity
import com.hefengbao.jingmo.data.model.chinese.asAntitheticalCoupletEntity
import com.hefengbao.jingmo.data.model.chinese.asCharacterEntity
import com.hefengbao.jingmo.data.model.chinese.asChineseKnowledgeEntity
import com.hefengbao.jingmo.data.model.chinese.asChineseWisecrackEntity
import com.hefengbao.jingmo.data.model.chinese.asExpressionEntity
import com.hefengbao.jingmo.data.model.chinese.asIdiomEntity
import com.hefengbao.jingmo.data.model.chinese.asLyricEntity
import com.hefengbao.jingmo.data.model.chinese.asModernPoetryEntity
import com.hefengbao.jingmo.data.model.chinese.asProverbEntity
import com.hefengbao.jingmo.data.model.chinese.asQuoteEntity
import com.hefengbao.jingmo.data.model.chinese.asRiddleEntity
import com.hefengbao.jingmo.data.model.chinese.asTongueTwisterEntity
import com.hefengbao.jingmo.data.model.classicalliterature.asClassicPoemEntity
import com.hefengbao.jingmo.data.model.classicalliterature.asPeopleEntity
import com.hefengbao.jingmo.data.model.classicalliterature.asSentenceEntity
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
        _chinaWorldCultureHeritageResult.value = SyncStatus.Loading
        viewModelScope.launch {
            var page: Int? = 1
            var count = 0
            while (page != null) {
                when (val response = repository.syncChinaWorldCultureHeritage(version, page)) {
                    is Result.Error -> {
                        _chinaWorldCultureHeritageResult.value =
                            SyncStatus.Error(response.exception)
                        return@launch
                    }

                    Result.Loading -> {}
                    is Result.Success -> {
                        val entities = response.data.data.map { it.asWorldCulturalHeritageEntity() }
                        repository.insertChinaWorldCultureHeritageAll(entities)
                        count += entities.size
                        _chinaWorldCultureHeritageProgress.value = count.toFloat() / total
                        page = response.data.nextPage
                    }
                }
            }
            if (count == total) {
                preference.setChinaWorldCultureHeritageVersion(version)
            }
            _chinaWorldCultureHeritageResult.value = SyncStatus.Success
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
            var page: Int? = 1
            var count = 0
            while (page != null) {
                when (val response = repository.syncChineseAntitheticalCouplet(version, page)) {
                    is Result.Error -> {
                        _chineseAntitheticalCoupletResult.value =
                            SyncStatus.Error(response.exception)
                        return@launch
                    }

                    Result.Loading -> {}
                    is Result.Success -> {
                        val entities = response.data.data.map { it.asAntitheticalCoupletEntity() }
                        repository.insertChineseAntitheticalCoupletAll(entities)
                        count += entities.size
                        _chineseAntitheticalCoupletProgress.value = count.toFloat() / total
                        page = response.data.nextPage
                    }
                }
            }
            if (count == total) {
                preference.setChineseAntitheticalVersion(version)
            }
            _chineseAntitheticalCoupletResult.value = SyncStatus.Success
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
                when (val response = repository.syncChineseExpression(version, page)) {
                    is Result.Error -> {
                        _chineseExpressionResult.value = SyncStatus.Error(response.exception)
                        return@launch
                    }

                    Result.Loading -> {}
                    is Result.Success -> {
                        val entities = response.data.data.map { it.asExpressionEntity() }
                        repository.insertChineseExpressionAll(entities)
                        count += entities.size
                        _chineseExpressionResultProgress.value = count.toFloat() / total
                        page = response.data.nextPage
                    }
                }
            }
            if (count == total) {
                preference.setChineseExpressionVersion(version)
            }
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
            var page: Int? = 1
            var count = 0
            while (page != null) {
                when (val response = repository.syncChineseKnowledge(version, page)) {
                    is Result.Error -> {
                        _chineseKnowledgeResult.value =
                            SyncStatus.Error(response.exception)
                        return@launch
                    }

                    Result.Loading -> {}
                    is Result.Success -> {
                        val entities = response.data.data.map { it.asChineseKnowledgeEntity() }
                        repository.insertChinesKnowledgeAll(entities)
                        count += entities.size
                        _chineseKnowledgeResultProgress.value = count.toFloat() / total
                        page = response.data.nextPage
                    }
                }
            }
            if (count == total) {
                preference.setChineseKnowledgeVersion(version)
            }
            _chineseKnowledgeResult.value = SyncStatus.Success
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
            var page: Int? = 1
            var count = 0
            while (page != null) {
                when (val response = repository.syncChineseModernPoetry(version, page)) {
                    is Result.Error -> {
                        _chineseModernPoetryResult.value =
                            SyncStatus.Error(response.exception)
                        return@launch
                    }

                    Result.Loading -> {}
                    is Result.Success -> {
                        val entities = response.data.data.map { it.asModernPoetryEntity() }
                        repository.insertChineseModernPoetryAll(entities)
                        count += entities.size
                        _chineseModernPoetryResultProgress.value = count.toFloat() / total
                        page = response.data.nextPage
                    }
                }
            }
            if (count == total) {
                preference.setChineseModernPoetryVersion(version)
            }
            _chineseModernPoetryResult.value = SyncStatus.Success
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
            var page: Int? = 1
            var count = 0
            while (page != null) {
                when (val response = repository.syncChineseProverb(version, page)) {
                    is Result.Error -> {
                        _chineseProverbResult.value =
                            SyncStatus.Error(response.exception)
                        return@launch
                    }

                    Result.Loading -> {}
                    is Result.Success -> {
                        val entities = response.data.data.map { it.asProverbEntity() }
                        repository.insertChineseProverbAll(entities)
                        count += entities.size
                        _chineseProverbResultProgress.value = count.toFloat() / total
                        page = response.data.nextPage
                    }
                }
            }
            if (count == total) {
                preference.setChineseProverbVersion(version)
            }
            _chineseProverbResult.value = SyncStatus.Success
        }
    }

    private val _chineseQuoteResult: MutableStateFlow<SyncStatus<Any>> =
        MutableStateFlow(SyncStatus.NonStatus)
    val chineseQuoteResult: SharedFlow<SyncStatus<Any>> = _chineseQuoteResult
    private val _chineseQuoteResultProgress: MutableStateFlow<Float> = MutableStateFlow(0f)
    val chineseQuoteResultProgress: SharedFlow<Float> = _chineseQuoteResultProgress
    fun syncChineseQuote(total: Int, version: Int) {
        _chineseQuoteResult.value = SyncStatus.Loading
        viewModelScope.launch {
            var page: Int? = 1
            var count = 0
            while (page != null) {
                when (val response = repository.syncChineseQuote(version, page)) {
                    is Result.Error -> {
                        _chineseQuoteResult.value =
                            SyncStatus.Error(response.exception)
                        return@launch
                    }

                    Result.Loading -> {}
                    is Result.Success -> {
                        val entities = response.data.data.map { it.asQuoteEntity() }
                        repository.insertChineseQuoteAll(entities)
                        count += entities.size
                        _chineseQuoteResultProgress.value = count.toFloat() / total
                        page = response.data.nextPage
                    }
                }
            }
            if (count == total) {
                preference.setChineseQuoteVersion(version)
            }
            _chineseQuoteResult.value = SyncStatus.Success
        }
    }

    private val _chineseWisecrackResult: MutableStateFlow<SyncStatus<Any>> =
        MutableStateFlow(SyncStatus.NonStatus)
    val chineseWisecrackResult: SharedFlow<SyncStatus<Any>> = _chineseWisecrackResult
    private val _chineseWisecracksResultProgress: MutableStateFlow<Float> = MutableStateFlow(0f)
    val chineseWisecrackResultProgress: SharedFlow<Float> = _chineseWisecracksResultProgress
    fun syncChineseWisecrack(total: Int, version: Int) {
        _chineseWisecrackResult.value = SyncStatus.Loading
        viewModelScope.launch {
            var page: Int? = 1
            var count = 0
            while (page != null) {
                when (val response = repository.syncChineseWisecrack(version, page)) {
                    is Result.Error -> {
                        _chineseWisecrackResult.value =
                            SyncStatus.Error(response.exception)
                        return@launch
                    }

                    Result.Loading -> {}
                    is Result.Success -> {
                        val entities = response.data.data.map { it.asChineseWisecrackEntity() }
                        repository.insertChineseWisecrackAll(entities)
                        count += entities.size
                        _chineseWisecracksResultProgress.value = count.toFloat() / total
                        page = response.data.nextPage
                    }
                }
            }
            if (count == total) {
                preference.setChineseWisecracksVersion(version)
            }
            _chineseWisecrackResult.value = SyncStatus.Success
        }
    }

    private val _chineseCharacterResult: MutableStateFlow<SyncStatus<Any>> =
        MutableStateFlow(SyncStatus.NonStatus)
    val chineseCharacterResult: SharedFlow<SyncStatus<Any>> = _chineseCharacterResult
    private val _chineseCharacterResultProgress: MutableStateFlow<Float> = MutableStateFlow(0f)
    val chineseCharacterResultProgress: SharedFlow<Float> = _chineseCharacterResultProgress
    fun syncChineseCharacter(total: Int, version: Int) {
        _chineseCharacterResult.value = SyncStatus.Loading
        viewModelScope.launch {
            var page: Int? = 1
            var count = 0
            while (page != null) {
                when (val response = repository.syncChineseCharacter(version, page)) {
                    is Result.Error -> {
                        _chineseCharacterResult.value =
                            SyncStatus.Error(response.exception)
                        return@launch
                    }

                    Result.Loading -> {}
                    is Result.Success -> {
                        val entities = response.data.data.map { it.asCharacterEntity() }
                        repository.insertChineseCharacterAll(entities)
                        count += entities.size
                        _chineseCharacterResultProgress.value = count.toFloat() / total
                        page = response.data.nextPage
                    }
                }
            }
            if (count == total) {
                preference.setChineseCharacterVersion(version)
            }
            _chineseCharacterResult.value = SyncStatus.Success
        }
    }

    private val _chineseIdiomResult: MutableStateFlow<SyncStatus<Any>> =
        MutableStateFlow(SyncStatus.NonStatus)
    val chineseIdiomResult: SharedFlow<SyncStatus<Any>> = _chineseIdiomResult
    private val _chineseIdiomResultProgress: MutableStateFlow<Float> = MutableStateFlow(0f)
    val chineseIdiomResultProgress: SharedFlow<Float> = _chineseIdiomResultProgress
    fun syncChineseIdiom(total: Int, version: Int) {
        _chineseIdiomResult.value = SyncStatus.Loading
        viewModelScope.launch {
            var page: Int? = 1
            var count = 0
            while (page != null) {
                when (val response = repository.syncChineseIdiom(version, page)) {
                    is Result.Error -> {
                        _chineseIdiomResult.value = SyncStatus.Error(response.exception)
                        return@launch
                    }

                    Result.Loading -> {}
                    is Result.Success -> {
                        val entities = response.data.data.map { it.asIdiomEntity() }
                        repository.insertChineseIdiomAll(entities)
                        count += entities.size
                        _chineseIdiomResultProgress.value = count.toFloat() / total
                        page = response.data.nextPage
                    }
                }
            }
            if (count == total) {
                preference.setChineseIdiomVersion(version)
            }
            _chineseIdiomResult.value = SyncStatus.Success
        }
    }

    private val _chineseLyricResult: MutableStateFlow<SyncStatus<Any>> =
        MutableStateFlow(SyncStatus.NonStatus)
    val chineseLyricResult: SharedFlow<SyncStatus<Any>> = _chineseLyricResult
    private val _chineseLyricResultProgress: MutableStateFlow<Float> = MutableStateFlow(0f)
    val chineseLyricResultProgress: SharedFlow<Float> = _chineseLyricResultProgress
    fun syncChineseLyric(total: Int, version: Int) {
        _chineseLyricResult.value = SyncStatus.Loading
        viewModelScope.launch {
            var page: Int? = 1
            var count = 0
            while (page != null) {
                when (val response = repository.syncChineseLyric(version, page)) {
                    is Result.Error -> {
                        _chineseLyricResult.value = SyncStatus.Error(response.exception)
                        return@launch
                    }

                    Result.Loading -> {}
                    is Result.Success -> {
                        val entities = response.data.data.map { it.asLyricEntity() }
                        repository.insertChineseLyricAll(entities)
                        count += entities.size
                        _chineseLyricResultProgress.value = count.toFloat() / total
                        page = response.data.nextPage
                    }
                }
            }
            if (count == total) {
                preference.setChineseLyricVersion(version)
            }
            _chineseLyricResult.value = SyncStatus.Success
        }
    }

    private val _classicalLiteraturePeopleResult: MutableStateFlow<SyncStatus<Any>> =
        MutableStateFlow(SyncStatus.NonStatus)
    val classicalLiteraturePeopleResult: SharedFlow<SyncStatus<Any>> =
        _classicalLiteraturePeopleResult
    private val _classicalLiteraturePeopleResultProgress: MutableStateFlow<Float> =
        MutableStateFlow(0f)
    val classicalLiteraturePeopleResultProgress: SharedFlow<Float> =
        _classicalLiteraturePeopleResultProgress

    fun syncClassicalLiteraturePeople(total: Int, version: Int) {
        _classicalLiteraturePeopleResult.value = SyncStatus.Loading
        viewModelScope.launch {
            var page: Int? = 1
            var count = 0
            while (page != null) {
                when (val response = repository.syncClassicalLiteraturePeople(version, page)) {
                    is Result.Error -> {
                        _classicalLiteraturePeopleResult.value =
                            SyncStatus.Error(response.exception)
                        return@launch
                    }

                    Result.Loading -> {}
                    is Result.Success -> {
                        val entities = response.data.data.map { it.asPeopleEntity() }
                        repository.insertClassicalLiteraturePeopleAll(entities)
                        count += entities.size
                        _classicalLiteraturePeopleResultProgress.value = count.toFloat() / total
                        page = response.data.nextPage
                    }
                }
            }
            if (count == total) {
                preference.setClassicalLiteraturePeopleVersion(version)
            }
            _classicalLiteraturePeopleResult.value = SyncStatus.Success
        }
    }

    private val _classicalLiteratureClassicPoemResult: MutableStateFlow<SyncStatus<Any>> =
        MutableStateFlow(SyncStatus.NonStatus)
    val classicalLiteratureClassicPoemResult: SharedFlow<SyncStatus<Any>> =
        _classicalLiteratureClassicPoemResult
    private val _classicalLiteratureClassicPoemResultProgress: MutableStateFlow<Float> =
        MutableStateFlow(0f)
    val classicalLiteratureClassicPoemResultProgress: SharedFlow<Float> =
        _classicalLiteratureClassicPoemResultProgress

    fun syncClassicalLiteratureClassicPoem(total: Int, version: Int) {
        _classicalLiteratureClassicPoemResult.value = SyncStatus.Loading
        viewModelScope.launch {
            var page: Int? = 1
            var count = 0
            while (page != null) {
                when (val response = repository.syncClassicalLiteratureClassicPoem(version, page)) {
                    is Result.Error -> {
                        _classicalLiteratureClassicPoemResult.value =
                            SyncStatus.Error(response.exception)
                        return@launch
                    }

                    Result.Loading -> {}
                    is Result.Success -> {
                        val entities = response.data.data.map { it.asClassicPoemEntity() }
                        repository.insertClassicalLiteratureClassicPoemAll(entities)
                        count += entities.size
                        _classicalLiteratureClassicPoemResultProgress.value =
                            count.toFloat() / total
                        page = response.data.nextPage
                    }
                }
            }
            if (count == total) {
                preference.setClassicalLiteratureClassicPoemsVersion(version)
            }
            _classicalLiteratureClassicPoemResult.value = SyncStatus.Success
        }
    }

    private val _classicalLiteratureSentenceResult: MutableStateFlow<SyncStatus<Any>> =
        MutableStateFlow(SyncStatus.NonStatus)
    val classicalLiteratureSentenceResult: SharedFlow<SyncStatus<Any>> =
        _classicalLiteratureSentenceResult
    private val _classicalLiteratureSentenceResultProgress: MutableStateFlow<Float> =
        MutableStateFlow(0f)
    val classicalLiteratureSentenceResultProgress: SharedFlow<Float> =
        _classicalLiteratureSentenceResultProgress

    fun syncClassicalLiteratureSentence(total: Int, version: Int) {
        _classicalLiteratureSentenceResult.value = SyncStatus.Loading
        viewModelScope.launch {
            var page: Int? = 1
            var count = 0
            while (page != null) {
                when (val response = repository.syncClassicalLiteratureSentence(version, page)) {
                    is Result.Error -> {
                        _classicalLiteratureSentenceResult.value =
                            SyncStatus.Error(response.exception)
                        return@launch
                    }

                    Result.Loading -> {}
                    is Result.Success -> {
                        val entities = response.data.data.map { it.asSentenceEntity() }
                        repository.insertClassicalLiteratureSentenceAll(entities)
                        count += entities.size
                        _classicalLiteratureSentenceResultProgress.value =
                            count.toFloat() / total
                        page = response.data.nextPage
                    }
                }
            }
            if (count == total) {
                preference.setClassicalLiteratureSentenceVersion(version)
            }
            _classicalLiteratureSentenceResult.value = SyncStatus.Success
        }
    }

    private val _chineseRiddleResult: MutableStateFlow<SyncStatus<Any>> =
        MutableStateFlow(SyncStatus.NonStatus)
    val chineseRiddleResult: SharedFlow<SyncStatus<Any>> = _chineseRiddleResult
    private val _chineseRiddleResultProgress: MutableStateFlow<Float> = MutableStateFlow(0f)
    val chineseRiddleResultProgress: SharedFlow<Float> = _chineseRiddleResultProgress
    fun syncChineseRiddle(total: Int, version: Int) {
        _chineseRiddleResult.value = SyncStatus.Loading
        viewModelScope.launch {
            var page: Int? = 1
            var count = 0
            while (page != null) {
                when (val response = repository.syncChineseRiddle(version, page)) {
                    is Result.Error -> {
                        _chineseRiddleResult.value =
                            SyncStatus.Error(response.exception)
                        return@launch
                    }

                    Result.Loading -> {}
                    is Result.Success -> {
                        val entities = response.data.data.map { it.asRiddleEntity() }
                        repository.insertChineseRiddleAll(entities)
                        count += entities.size
                        _chineseRiddleResultProgress.value = count.toFloat() / total
                        page = response.data.nextPage
                    }
                }
            }
            if (count == total) {
                preference.setChineseRiddleVersion(version)
            }
            _chineseRiddleResult.value = SyncStatus.Success
        }
    }

    private val _chineseTongueTwisterResult: MutableStateFlow<SyncStatus<Any>> =
        MutableStateFlow(SyncStatus.NonStatus)
    val chineseTongueTwisterResult: SharedFlow<SyncStatus<Any>> = _chineseTongueTwisterResult
    private val _chineseTongueTwisterResultProgress: MutableStateFlow<Float> = MutableStateFlow(0f)
    val chineseTongueTwisterResultProgress: SharedFlow<Float> = _chineseTongueTwisterResultProgress
    fun syncChineseTongueTwister(total: Int, version: Int) {
        _chineseTongueTwisterResult.value = SyncStatus.Loading
        viewModelScope.launch {
            var page: Int? = 1
            var count = 0
            while (page != null) {
                when (val response = repository.syncChineseTongueTwister(version, page)) {
                    is Result.Error -> {
                        _chineseTongueTwisterResult.value =
                            SyncStatus.Error(response.exception)
                        return@launch
                    }

                    Result.Loading -> {}
                    is Result.Success -> {
                        val entities = response.data.data.map { it.asTongueTwisterEntity() }
                        repository.insertChineseTongueTwisterAll(entities)
                        count += entities.size
                        _chineseTongueTwisterResultProgress.value = count.toFloat() / total
                        page = response.data.nextPage
                    }
                }
            }
            if (count == total) {
                preference.setChineseTongueTwisterVersion(version)
            }
            _chineseTongueTwisterResult.value = SyncStatus.Success
        }
    }

    private val _classicalLiteratureWritingResult: MutableStateFlow<SyncStatus<Any>> =
        MutableStateFlow(SyncStatus.NonStatus)
    val classicalLiteratureWritingResult: SharedFlow<SyncStatus<Any>> =
        _classicalLiteratureWritingResult
    private val _classicalLiteratureWritingResultProgress: MutableStateFlow<Float> =
        MutableStateFlow(0f)
    val classicalLiteratureWritingResultProgress: SharedFlow<Float> =
        _classicalLiteratureWritingResultProgress

    private var writingCurrentPage: MutableStateFlow<Int> = MutableStateFlow(1)
    private var writingCurrentCount: MutableStateFlow<Int> = MutableStateFlow(0)
    fun setClassicalLiteratureWritingPreviousPage(page: Int) {
        writingCurrentPage.value = page
    }

    fun setClassicalLiteratureWritingPreviousCount(count: Int, total: Int) {
        writingCurrentCount.value = count

        _classicalLiteratureWritingResultProgress.value =
            (if (writingCurrentCount.value.toFloat() > total) total.toFloat() else writingCurrentCount.value.toFloat()) / total
    }

    fun syncClassicalLiteratureWriting(total: Int, version: Int) {
        _classicalLiteratureWritingResult.value = SyncStatus.Loading

        viewModelScope.launch {
            while (writingCurrentPage.value != 0) {
                when (
                    val response = repository.syncClassicalLiteratureWriting(
                        version,
                        writingCurrentPage.value
                    )
                ) {
                    is Result.Error -> {
                        _classicalLiteratureWritingResult.value = SyncStatus.Error(
                            response.exception
                        )
                        return@launch
                    }

                    Result.Loading -> {}
                    is Result.Success -> {
                        val entities = response.data.data.map { it.asWritingEntity() }
                        repository.insertClassicalLiteratureWriting(entities)

                        writingCurrentCount.value += entities.size
                        _classicalLiteratureWritingResultProgress.value =
                            (if (writingCurrentCount.value.toFloat() > total) total.toFloat() else writingCurrentCount.value.toFloat()) / total

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
            if (writingCurrentCount.value >= total) {
                preference.setClassicalLiteratureWritingVersion(version)
                preference.setClassicalLiteratureWritingCurrentPage(1)
                preference.setClassicalLiteratureWritingCurrentCount(0)
            }
            _classicalLiteratureWritingResult.value = SyncStatus.Success
        }
    }
}

sealed interface SyncStatus<out T> {
    data class Error(val exception: Throwable? = null) : SyncStatus<Nothing>
    data object Success : SyncStatus<Nothing>
    data object Loading : SyncStatus<Nothing>
    data object NonStatus : SyncStatus<Nothing>
}