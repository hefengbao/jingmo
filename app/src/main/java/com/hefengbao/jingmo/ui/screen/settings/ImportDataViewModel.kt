/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.settings

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.entity.chinese.DictionaryPinyinEntity
import com.hefengbao.jingmo.data.datastore.DatasetPreference
import com.hefengbao.jingmo.data.model.chinese.ChineseKnowledge
import com.hefengbao.jingmo.data.model.chinese.ChineseWisecrack
import com.hefengbao.jingmo.data.model.chinese.DictionaryWrapper
import com.hefengbao.jingmo.data.model.chinese.ExpressionWrapper
import com.hefengbao.jingmo.data.model.chinese.IdiomWrapper
import com.hefengbao.jingmo.data.model.chinese.Lyric
import com.hefengbao.jingmo.data.model.chinese.Proverb
import com.hefengbao.jingmo.data.model.chinese.TongueTwister
import com.hefengbao.jingmo.data.model.chinese.asChineseExpressionEntity
import com.hefengbao.jingmo.data.model.chinese.asChineseKnowledgeEntity
import com.hefengbao.jingmo.data.model.chinese.asChineseWisecrackEntity
import com.hefengbao.jingmo.data.model.chinese.asDictionaryEntity
import com.hefengbao.jingmo.data.model.chinese.asIdiomEntity
import com.hefengbao.jingmo.data.model.chinese.asLyricEntity
import com.hefengbao.jingmo.data.model.chinese.asProverbEntity
import com.hefengbao.jingmo.data.model.chinese.asTongueTwisterEntity
import com.hefengbao.jingmo.data.model.classicalliterature.ClassicPoem
import com.hefengbao.jingmo.data.model.classicalliterature.PeopleWrapper
import com.hefengbao.jingmo.data.model.classicalliterature.PoemSentence
import com.hefengbao.jingmo.data.model.classicalliterature.WritingWrapper
import com.hefengbao.jingmo.data.model.classicalliterature.asClassicPoemEntity
import com.hefengbao.jingmo.data.model.classicalliterature.asPeopleEntity
import com.hefengbao.jingmo.data.model.classicalliterature.asPoemSentenceEntity
import com.hefengbao.jingmo.data.model.classicalliterature.asWritingEntity
import com.hefengbao.jingmo.data.repository.settings.ImportRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class ImportViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val json: Json,
    private val repository: ImportRepository,
    private val preference: DatasetPreference,
) : ViewModel() {
    private val chineseExpressionCount = 320349
    private val chineseKnowledgeCount = 464
    private val chineseProverbsCount = 489
    private val chineseWisecracksCount = 14026
    private val classicPoemCount = 955
    private val dictionaryCount = 20552
    private val idiomsCount = 49639
    private val lyricCount = 499
    private val peopleCount = 85776
    private val poemSentencesCount = 10000
    private val riddlesCount = 42446
    private val tongueTwistersCount = 45
    private val writingsCount = 1144422

    val chineseExpressionRatio =
        repository.chineseExpressionTotal().distinctUntilChanged().flatMapLatest {
            MutableStateFlow(it.toFloat() / chineseExpressionCount)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = 0f
        )
    private val _chineseExpressionStatus: MutableStateFlow<ImportStatus<Any>> =
        MutableStateFlow(ImportStatus.Finish)
    val chineseExpressionStatus: SharedFlow<ImportStatus<Any>> = _chineseExpressionStatus
    fun chineseExpression(uris: List<Uri>) {
        viewModelScope.launch {
            _chineseExpressionStatus.value = ImportStatus.Loading
            uris.forEach {
                json.decodeFromString<ExpressionWrapper>(readTextFromUri(it))
                    .data
                    .forEach { chineseExpression ->
                        repository.insertChineseExpression(chineseExpression.asChineseExpressionEntity())
                    }
            }
            _chineseExpressionStatus.value = ImportStatus.Finish
        }
    }

    fun clearChineseExpressions() {
        viewModelScope.launch {
            repository.clearChineseExpressions()
            preference.setChineseExpressionVersion(0)
        }
    }

    val chineseProverbRatio =
        repository.chineseProverbTotal().distinctUntilChanged().flatMapLatest {
            MutableStateFlow(it.toFloat() / chineseProverbsCount)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = 0f
        )
    private val _chineseProversStatus: MutableStateFlow<ImportStatus<Any>> =
        MutableStateFlow(ImportStatus.Finish)
    val chineseProverbStatus: SharedFlow<ImportStatus<Any>> = _chineseProversStatus
    fun chineseProverb(uris: List<Uri>) {
        viewModelScope.launch {
            _chineseProversStatus.value = ImportStatus.Loading
            uris.forEach {
                json.decodeFromString<List<Proverb>>(readTextFromUri(it))
                    .forEach { proverb ->
                        repository.insertChineseProverb(proverb.asProverbEntity())
                    }
            }
            _chineseProversStatus.value = ImportStatus.Finish
        }
    }

    fun clearChineseProverbs() {
        viewModelScope.launch {
            repository.clearChineseProverbs()
            preference.setChineseProverbVersion(0)
        }
    }

    val chineseWisecrackRatio =
        repository.chineseWisecrackTotal().distinctUntilChanged().flatMapLatest {
            MutableStateFlow(it.toFloat() / chineseWisecracksCount)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = 0f
        )
    private val _chineseWisecrackStatus: MutableStateFlow<ImportStatus<Any>> =
        MutableStateFlow(ImportStatus.Finish)
    val chineseWisecrackStatus: SharedFlow<ImportStatus<Any>> = _chineseWisecrackStatus
    fun chineseWisecrack(uris: List<Uri>) {
        viewModelScope.launch {
            _chineseWisecrackStatus.value = ImportStatus.Loading
            uris.forEach {
                json.decodeFromString<List<ChineseWisecrack>>(readTextFromUri(it))
                    .forEach { chineseWisecrack ->
                        repository.insertChineseWisecrack(chineseWisecrack.asChineseWisecrackEntity())
                    }

            }
            _chineseWisecrackStatus.value = ImportStatus.Finish
        }
    }

    fun clearChineseWisecracks() {
        viewModelScope.launch {
            repository.clearChineseWisecracks()
            preference.setChineseWisecrackVersion(0)
        }
    }

    val chineseKnowledgeRatio =
        repository.chineseKnowledgeTotal().distinctUntilChanged().flatMapLatest {
            MutableStateFlow(it.toFloat() / chineseKnowledgeCount)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = 0f
        )
    private val _chineseKnowledgeStatus: MutableStateFlow<ImportStatus<Any>> =
        MutableStateFlow(ImportStatus.Finish)
    val chineseKnowledgeStatus: SharedFlow<ImportStatus<Any>> = _chineseKnowledgeStatus
    fun chineseKnowledge(uris: List<Uri>) {
        viewModelScope.launch {
            _chineseKnowledgeStatus.value = ImportStatus.Loading
            uris.forEach {
                json.decodeFromString<List<ChineseKnowledge>>(readTextFromUri(it))
                    .forEach { chineseKnowledge ->
                        repository.insertChineseKnowledge(chineseKnowledge.asChineseKnowledgeEntity())
                    }
            }
            _chineseKnowledgeStatus.value = ImportStatus.Finish
        }
    }

    fun clearChineseKnowledge() {
        viewModelScope.launch {
            repository.clearChineseKnowledge()
            preference.setChineseKnowledgeVersion(0)
        }
    }

    val classicPoemsRatio =
        repository.classicalLiteratureClassicPoemTotal().distinctUntilChanged().flatMapLatest {
            MutableStateFlow(it.toFloat() / classicPoemCount)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = 0f
        )
    private val _classicPoemsStatus: MutableStateFlow<ImportStatus<Any>> =
        MutableStateFlow(ImportStatus.Finish)
    val classicPoemsStatus: SharedFlow<ImportStatus<Any>> = _classicPoemsStatus
    fun classicPoems(uris: List<Uri>) {
        viewModelScope.launch {
            _classicPoemsStatus.value = ImportStatus.Loading
            uris.forEach {
                json.decodeFromString<List<ClassicPoem>>(readTextFromUri(it)).forEach { poem ->
                    repository.insertClassicalLiteratureClassicPoems(poem.asClassicPoemEntity())
                }
            }
            _classicPoemsStatus.value = ImportStatus.Finish
        }
    }

    fun clearClassicalLiteratureClassicPoems() {
        viewModelScope.launch {
            repository.clearClassicalLiteratureClassicPoems()
            preference.setClassicalLiteratureClassicPoemVersion(0)
        }
    }

    val dictionaryRatio = repository.chineseDictionaryTotal().distinctUntilChanged().flatMapLatest {
        MutableStateFlow(it.toFloat() / dictionaryCount)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = 0f
    )
    private val _dictionaryStatus: MutableStateFlow<ImportStatus<Any>> =
        MutableStateFlow(ImportStatus.Finish)
    val dictionaryStatus: SharedFlow<ImportStatus<Any>> = _dictionaryStatus
    fun dictionary(uris: List<Uri>) {
        viewModelScope.launch {
            _dictionaryStatus.value = ImportStatus.Loading
            uris.forEach {
                json.decodeFromString<DictionaryWrapper>(readTextFromUri(it)).data.forEach { dictionary ->
                    repository.insertChineseDictionary(dictionary.asDictionaryEntity())
                    dictionary.pinyin2?.map { pinyin ->
                        repository.insertChineseDictionaryPinyin(
                            DictionaryPinyinEntity(
                                dictionaryId = dictionary.id,
                                pinyin = pinyin,
                            )
                        )
                    }
                }
            }
            _dictionaryStatus.value = ImportStatus.Finish
        }
    }

    fun clearChineseDictionaries() {
        viewModelScope.launch {
            repository.clearChineseDictionaries()
            preference.setChineseDictionaryVersion(0)
        }
    }

    val idiomsRatio = repository.chineseIdiomTotal().distinctUntilChanged().flatMapLatest {
        MutableStateFlow(it.toFloat() / idiomsCount)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = 0f
    )
    private val _idiomStatus: MutableStateFlow<ImportStatus<Any>> =
        MutableStateFlow(ImportStatus.Finish)
    val idiomStatus: SharedFlow<ImportStatus<Any>> = _idiomStatus
    fun idioms(uris: List<Uri>) {
        viewModelScope.launch {
            _idiomStatus.value = ImportStatus.Loading
            uris.forEach {
                json.decodeFromString<IdiomWrapper>(readTextFromUri(it)).data.forEach { idiom ->
                    repository.insertChineseIdiom(idiom.asIdiomEntity())
                }
            }
            _idiomStatus.value = ImportStatus.Finish
        }
    }

    fun clearChineseIdioms() {
        viewModelScope.launch {
            repository.clearChineseIdioms()
            preference.setChineseIdiomVersion(0)
        }
    }

    val lyricRatio = repository.chineseLyricTotal().distinctUntilChanged().flatMapLatest {
        MutableStateFlow(it.toFloat() / lyricCount)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = 0f
    )
    private val _lyricStatus: MutableStateFlow<ImportStatus<Any>> =
        MutableStateFlow(ImportStatus.Finish)
    val lyricStatus: SharedFlow<ImportStatus<Any>> = _lyricStatus
    fun lyrics(uris: List<Uri>) {
        viewModelScope.launch {
            _lyricStatus.value = ImportStatus.Loading
            uris.forEach {
                json.decodeFromString<List<Lyric>>(readTextFromUri(it)).forEach { lyric ->
                    repository.insertChineseLyric(lyric.asLyricEntity())
                }
            }
            _lyricStatus.value = ImportStatus.Finish
        }
    }

    fun clearChineseLyrics() {
        viewModelScope.launch {
            repository.clearChineseLyrics()
            preference.setChineseLyricVersion(0)
        }
    }

    val peopleRatio =
        repository.classicalLiteraturePeopleTotal().distinctUntilChanged().flatMapLatest {
            MutableStateFlow(it.toFloat() / peopleCount)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = 0f
        )
    private val _peopleStatus: MutableStateFlow<ImportStatus<Any>> =
        MutableStateFlow(ImportStatus.Finish)
    val peopleStatus: SharedFlow<ImportStatus<Any>> = _peopleStatus
    fun people(uris: List<Uri>) {
        viewModelScope.launch {
            _peopleStatus.value = ImportStatus.Loading
            uris.forEach {
                json.decodeFromString<PeopleWrapper>(readTextFromUri(it)).data.forEach { people ->
                    repository.insertClassicalLiteraturePeople(people.asPeopleEntity())
                }
            }
            _peopleStatus.value = ImportStatus.Finish
        }
    }

    fun clearClassicalLiteraturePeople() {
        viewModelScope.launch {
            repository.clearClassicalLiteraturePeople()
            preference.setClassicalLiteraturePeopleVersion(0)
        }
    }

    val poemSentencesRatio =
        repository.classicalLiteratureSentenceTotal().distinctUntilChanged().flatMapLatest {
            MutableStateFlow(it.toFloat() / poemSentencesCount)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = 0f
        )
    private val _poemSentenceStatus: MutableStateFlow<ImportStatus<Any>> =
        MutableStateFlow(ImportStatus.Finish)
    val poemSentenceStatus: SharedFlow<ImportStatus<Any>> = _poemSentenceStatus
    fun poemSentences(uris: List<Uri>) {
        viewModelScope.launch {
            _peopleStatus.value = ImportStatus.Loading
            uris.forEach {
                json.decodeFromString<List<PoemSentence>>(readTextFromUri(it))
                    .forEach { poemSentence ->
                        repository.insertClassicalLiteratureSentence(poemSentence.asPoemSentenceEntity())
                    }
            }
            _peopleStatus.value = ImportStatus.Finish
        }
    }

    fun clearClassicalLiteratureSentence() {
        viewModelScope.launch {
            repository.clearClassicalLiteratureSentence()
            preference.setClassicalLiteratureSentenceVersion(0)
        }
    }

    val tongueTwistersRatio =
        repository.chineseTongueTwistersTotal().distinctUntilChanged().flatMapLatest {
            MutableStateFlow(it.toFloat() / tongueTwistersCount)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = 0f
        )
    private val _tongueTwisterStatus: MutableStateFlow<ImportStatus<Any>> =
        MutableStateFlow(ImportStatus.Finish)
    val tongueTwisterStatus: SharedFlow<ImportStatus<Any>> = _tongueTwisterStatus
    fun tongueTwisters(uris: List<Uri>) {
        viewModelScope.launch {
            _tongueTwisterStatus.value = ImportStatus.Loading
            uris.forEach {
                json.decodeFromString<List<TongueTwister>>(readTextFromUri(it))
                    .forEach { tongueTwister ->
                        repository.insertChineseTongueTwister(tongueTwister.asTongueTwisterEntity())
                    }
            }
            _tongueTwisterStatus.value = ImportStatus.Finish
        }
    }

    fun clearChineseTongueTwisters() {
        viewModelScope.launch {
            repository.clearChineseTongueTwisters()
            preference.setChineseTongueTwisterVersion(0)
        }
    }

    val writingsRatio =
        repository.classicalLiteratureWritingTotal().distinctUntilChanged().flatMapLatest {
            MutableStateFlow(it.toFloat() / writingsCount)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = 0f
        )
    private val _writingStatus: MutableStateFlow<ImportStatus<Any>> =
        MutableStateFlow(ImportStatus.Finish)
    val writingStatus: SharedFlow<ImportStatus<Any>> = _writingStatus
    fun writings(uris: List<Uri>) {
        viewModelScope.launch {
            _writingStatus.value = ImportStatus.Loading
            uris.forEach {
                json.decodeFromString<WritingWrapper>(readTextFromUri(it)).data.forEach { writing ->
                    repository.insertClassicalLiteratureWriting(writing.asWritingEntity())
                }
            }
            _writingStatus.value = ImportStatus.Finish
        }
    }

    fun clearClassicalLiteratureWritings() {
        viewModelScope.launch {
            repository.clearClassicalLiteratureWritings()
            preference.setClassicalLiteratureWritingVersion(0)
            preference.setClassicalLiteratureWritingCurrentCount(0)
            preference.setClassicalLiteratureWritingCurrentPage(0)
        }
    }

    private val contentResolver = context.contentResolver

    @Throws(IOException::class)
    private fun readTextFromUri(uri: Uri): String {
        val stringBuilder = StringBuilder()
        contentResolver.openInputStream(uri)?.use { inputStream ->
            BufferedReader(InputStreamReader(inputStream)).use { reader ->
                var line: String? = reader.readLine()
                while (line != null) {
                    stringBuilder.append(line)
                    line = reader.readLine()
                }
            }
            inputStream.close()
        }
        return stringBuilder.toString()
    }
}

sealed interface ImportStatus<out T> {
    data object Finish : ImportStatus<Nothing>
    data object Loading : ImportStatus<Nothing>
}