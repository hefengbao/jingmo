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
import com.hefengbao.jingmo.data.database.entity.DictionaryPinyinEntity
import com.hefengbao.jingmo.data.model.ChineseExpressionWrapper
import com.hefengbao.jingmo.data.model.ChineseKnowledge
import com.hefengbao.jingmo.data.model.ChineseWisecrack
import com.hefengbao.jingmo.data.model.ClassicPoem
import com.hefengbao.jingmo.data.model.DictionaryWrapper
import com.hefengbao.jingmo.data.model.IdiomWrapper
import com.hefengbao.jingmo.data.model.Lyric
import com.hefengbao.jingmo.data.model.PeopleWrapper
import com.hefengbao.jingmo.data.model.PoemSentence
import com.hefengbao.jingmo.data.model.TongueTwister
import com.hefengbao.jingmo.data.model.WritingWrapper
import com.hefengbao.jingmo.data.model.asChineseExpressionEntity
import com.hefengbao.jingmo.data.model.asChineseKnowledgeEntity
import com.hefengbao.jingmo.data.model.asChineseWisecrackEntity
import com.hefengbao.jingmo.data.model.asClassicPoemEntity
import com.hefengbao.jingmo.data.model.asDictionaryEntity
import com.hefengbao.jingmo.data.model.asIdiomEntity
import com.hefengbao.jingmo.data.model.asLyricEntity
import com.hefengbao.jingmo.data.model.asPeopleEntity
import com.hefengbao.jingmo.data.model.asPoemSentenceEntity
import com.hefengbao.jingmo.data.model.asTongueTwisterEntity
import com.hefengbao.jingmo.data.model.asWritingEntity
import com.hefengbao.jingmo.data.repository.ImportRepository
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
    private val repository: ImportRepository
) : ViewModel() {
    private val chineseExpressionCount = 320349
    private val chineseKnowledgeCount = 464
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
                json.decodeFromString<ChineseExpressionWrapper>(readTextFromUri(it))
                    .data
                    .forEach { chineseExpression ->
                        repository.insertChineseExpression(chineseExpression.asChineseExpressionEntity())
                    }
            }
            _chineseExpressionStatus.value = ImportStatus.Finish
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

    val classicPoemsRatio = repository.classicPoemTotal().distinctUntilChanged().flatMapLatest {
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
                    repository.insertClassicPoem(poem.asClassicPoemEntity())
                }
            }
            _classicPoemsStatus.value = ImportStatus.Finish
        }
    }

    val dictionaryRatio = repository.dictionaryTotal().distinctUntilChanged().flatMapLatest {
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
                    repository.insertDictionary(dictionary.asDictionaryEntity())
                    dictionary.pinyin2?.map { pinyin ->
                        repository.insertDictionaryPinyin(
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

    val idiomsRatio = repository.idiomsTotal().distinctUntilChanged().flatMapLatest {
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
                    repository.insertIdiom(idiom.asIdiomEntity())
                }
            }
            _idiomStatus.value = ImportStatus.Finish
        }
    }

    val lyricRatio = repository.lyricTotal().distinctUntilChanged().flatMapLatest {
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
                    repository.insertLyric(lyric.asLyricEntity())
                }
            }
            _lyricStatus.value = ImportStatus.Finish
        }
    }

    val peopleRatio = repository.peopleTotal().distinctUntilChanged().flatMapLatest {
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
                    repository.insertPeople(people.asPeopleEntity())
                }
            }
            _peopleStatus.value = ImportStatus.Finish
        }
    }

    val poemSentencesRatio = repository.poemSentencesTotal().distinctUntilChanged().flatMapLatest {
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
                        repository.insertPoemSentence(poemSentence.asPoemSentenceEntity())
                    }
            }
            _peopleStatus.value = ImportStatus.Finish
        }
    }

    val tongueTwistersRatio =
        repository.tongueTwistersTotal().distinctUntilChanged().flatMapLatest {
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
                        repository.insertTongueTwister(tongueTwister.asTongueTwisterEntity())
                    }
            }
            _tongueTwisterStatus.value = ImportStatus.Finish
        }
    }

    val writingsRatio = repository.writingsTotal().distinctUntilChanged().flatMapLatest {
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
                    repository.insertWriting(writing.asWritingEntity())
                }
            }
            _writingStatus.value = ImportStatus.Finish
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