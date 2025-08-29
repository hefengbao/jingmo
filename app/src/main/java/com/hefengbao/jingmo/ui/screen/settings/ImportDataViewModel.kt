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
import com.hefengbao.jingmo.data.database.entity.classicalliterature.WritingEntity
import com.hefengbao.jingmo.data.datastore.DatasetPreference
import com.hefengbao.jingmo.data.model.DataWrapper
import com.hefengbao.jingmo.data.model.china.WorldCulturalHeritage
import com.hefengbao.jingmo.data.model.china.asWorldCulturalHeritageEntity
import com.hefengbao.jingmo.data.model.chinese.AntitheticalCouplet
import com.hefengbao.jingmo.data.model.chinese.Character
import com.hefengbao.jingmo.data.model.chinese.ChineseKnowledge
import com.hefengbao.jingmo.data.model.chinese.Expression
import com.hefengbao.jingmo.data.model.chinese.Idiom
import com.hefengbao.jingmo.data.model.chinese.Lyric
import com.hefengbao.jingmo.data.model.chinese.ModernPoetry
import com.hefengbao.jingmo.data.model.chinese.Proverb
import com.hefengbao.jingmo.data.model.chinese.Quote
import com.hefengbao.jingmo.data.model.chinese.Riddle
import com.hefengbao.jingmo.data.model.chinese.TongueTwister
import com.hefengbao.jingmo.data.model.chinese.Wisecrack
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
import com.hefengbao.jingmo.data.model.classicalliterature.ClassicPoem
import com.hefengbao.jingmo.data.model.classicalliterature.People
import com.hefengbao.jingmo.data.model.classicalliterature.Sentence
import com.hefengbao.jingmo.data.model.classicalliterature.Writing
import com.hefengbao.jingmo.data.model.classicalliterature.asClassicPoemEntity
import com.hefengbao.jingmo.data.model.classicalliterature.asPeopleEntity
import com.hefengbao.jingmo.data.model.classicalliterature.asSentenceEntity
import com.hefengbao.jingmo.data.model.classicalliterature.asWritingEntity
import com.hefengbao.jingmo.data.repository.settings.ImportRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
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

    var chinaWorldCultureHeritageRatio = MutableStateFlow(0)
    var chineseAntitheticalCoupletRatio = MutableStateFlow(0)
    var chineseCharacterRatio = MutableStateFlow(0)
    var chineseExpressionRatio = MutableStateFlow(0)
    var chineseIdiomRatio = MutableStateFlow(0)
    var chineseKnowledgeRatio = MutableStateFlow(0)
    var chineseLyricRatio = MutableStateFlow(0)
    var chineseModernPoetryRatio = MutableStateFlow(0)
    var chineseProverbRatio = MutableStateFlow(0)
    var chineseQuoteRatio = MutableStateFlow(0)
    var chineseRiddleRatio = MutableStateFlow(0)
    var chineseTongueTwisterRatio = MutableStateFlow(0)
    var chineseWisecrackRatio = MutableStateFlow(0)
    var classicalLiteratureClassicPoemRatio = MutableStateFlow(0)
    var classicalLiteraturePeopleRatio = MutableStateFlow(0)
    var classicalLiteratureSentenceRatio = MutableStateFlow(0)
    var classicalLiteratureWritingRatio = MutableStateFlow(0)

    init {
        viewModelScope.launch {
            repository.chinaChinaWorldCultureHeritageTotal().collectLatest {
                chinaWorldCultureHeritageRatio.value = it
            }
        }
        viewModelScope.launch {
            repository.chineseAntitheticalCoupletTotal().collectLatest {
                chineseAntitheticalCoupletRatio.value = it
            }
        }
        viewModelScope.launch {
            repository.chineseCharacterTotal().collectLatest {
                chineseCharacterRatio.value = it
            }
        }
        viewModelScope.launch {
            repository.chineseExpressionTotal().collectLatest {
                chineseExpressionRatio.value = it
            }
        }
        viewModelScope.launch {
            repository.chineseIdiomTotal().collectLatest {
                chineseIdiomRatio.value = it
            }
        }
        viewModelScope.launch {
            repository.chineseKnowledgeTotal().collectLatest {
                chineseKnowledgeRatio.value = it
            }
        }
        viewModelScope.launch {
            repository.chineseLyricTotal().collectLatest {
                chineseLyricRatio.value = it
            }
        }
        viewModelScope.launch {
            repository.chineseModernPoetryTotal().collectLatest {
                chineseModernPoetryRatio.value = it
            }
        }
        viewModelScope.launch {
            repository.chineseProverbTotal().collectLatest {
                chineseProverbRatio.value = it
            }
        }
        viewModelScope.launch {
            repository.chineseQuoteTotal().collectLatest {
                chineseQuoteRatio.value = it
            }
        }
        viewModelScope.launch {
            repository.chineseRiddleTotal().collectLatest {
                chineseRiddleRatio.value = it
            }
        }
        viewModelScope.launch {
            repository.chineseTongueTwisterTotal().collectLatest {
                chineseTongueTwisterRatio.value = it
            }
        }
        viewModelScope.launch {
            repository.chineseWisecrackTotal().collectLatest {
                chineseWisecrackRatio.value = it
            }
        }
        viewModelScope.launch {
            repository.classicalLiteratureClassicPoemTotal().collectLatest {
                classicalLiteratureClassicPoemRatio.value = it
            }
        }
        viewModelScope.launch {
            repository.classicalLiteraturePeopleTotal().collectLatest {
                classicalLiteraturePeopleRatio.value = it
            }
        }
        viewModelScope.launch {
            repository.classicalLiteratureSentenceTotal().collectLatest {
                classicalLiteratureSentenceRatio.value = it
            }
        }
        viewModelScope.launch {
            repository.classicalLiteratureWritingTotal().collectLatest {
                classicalLiteratureWritingRatio.value = it
            }
        }
    }


    private val _chinaWorldCultureHeritageStatus: MutableStateFlow<ImportStatus<Any>> =
        MutableStateFlow(ImportStatus.Finish)
    val chinaWorldCultureHeritageStatus: SharedFlow<ImportStatus<Any>> =
        _chinaWorldCultureHeritageStatus

    fun chinaWorldCultureHeritage(uris: List<Uri>) {
        viewModelScope.launch {
            _chinaWorldCultureHeritageStatus.value = ImportStatus.Loading
            uris.forEach {
                json.decodeFromString<DataWrapper<WorldCulturalHeritage>>(readTextFromUri(it))
                    .data
                    .forEach { worldCulturalHeritage ->
                        repository.insertChinaWorldCultureHeritage(worldCulturalHeritage.asWorldCulturalHeritageEntity())
                    }
            }
            _chinaWorldCultureHeritageStatus.value = ImportStatus.Finish
        }
    }

    fun clearChinaWorldCultureHeritage() {
        viewModelScope.launch {
            repository.clearChinaWorldCultureHeritage()
        }
    }

    private val _chineseAntitheticalCoupletStatus: MutableStateFlow<ImportStatus<Any>> =
        MutableStateFlow(ImportStatus.Finish)
    val chineseAntitheticalCoupletStatus: SharedFlow<ImportStatus<Any>> =
        _chineseAntitheticalCoupletStatus

    fun chineseAntitheticalCouplet(uris: List<Uri>) {
        viewModelScope.launch {
            _chineseAntitheticalCoupletStatus.value = ImportStatus.Loading
            uris.forEach {
                json.decodeFromString<DataWrapper<AntitheticalCouplet>>(readTextFromUri(it))
                    .data
                    .forEach { antitheticalCouplet ->
                        repository.insertChineseAntitheticalCouplet(antitheticalCouplet.asAntitheticalCoupletEntity())
                    }
            }
            _chineseAntitheticalCoupletStatus.value = ImportStatus.Finish
        }
    }

    fun clearChineseAntitheticalCouplet() {
        viewModelScope.launch {
            repository.clearChineseAntitheticalCouplet()
        }
    }

    private val _chineseExpressionStatus: MutableStateFlow<ImportStatus<Any>> =
        MutableStateFlow(ImportStatus.Finish)
    val chineseExpressionStatus: SharedFlow<ImportStatus<Any>> = _chineseExpressionStatus
    fun chineseExpression(uris: List<Uri>) {
        viewModelScope.launch {
            _chineseExpressionStatus.value = ImportStatus.Loading
            uris.forEach {
                json.decodeFromString<DataWrapper<Expression>>(readTextFromUri(it))
                    .data
                    .forEach { chineseExpression ->
                        repository.insertChineseExpression(chineseExpression.asExpressionEntity())
                    }
            }
            _chineseExpressionStatus.value = ImportStatus.Finish
        }
    }

    fun clearChineseExpressions() {
        viewModelScope.launch {
            repository.clearChineseExpression()
            preference.setChineseExpressionVersion(0)
        }
    }

    private val _chineseModernPoetryStatus: MutableStateFlow<ImportStatus<Any>> =
        MutableStateFlow(ImportStatus.Finish)
    val chineseModernPoetryStatus: SharedFlow<ImportStatus<Any>> = _chineseModernPoetryStatus
    fun chineseModernPoetry(uris: List<Uri>) {
        viewModelScope.launch {
            _chineseModernPoetryStatus.value = ImportStatus.Loading
            uris.forEach {
                json.decodeFromString<DataWrapper<ModernPoetry>>(readTextFromUri(it))
                    .data
                    .forEach { modernPoetry ->
                        repository.insertChineseModernPoetry(modernPoetry.asModernPoetryEntity())
                    }
            }
            _chineseModernPoetryStatus.value = ImportStatus.Finish
        }
    }

    fun clearChineseModernPoetry() {
        viewModelScope.launch {
            repository.clearChineseModernPoetry()
            preference.setChineseModernPoetryVersion(0)
        }
    }

    private val _chineseProversStatus: MutableStateFlow<ImportStatus<Any>> =
        MutableStateFlow(ImportStatus.Finish)
    val chineseProverbStatus: SharedFlow<ImportStatus<Any>> = _chineseProversStatus
    fun chineseProverb(uris: List<Uri>) {
        viewModelScope.launch {
            _chineseProversStatus.value = ImportStatus.Loading
            uris.forEach {
                json.decodeFromString<DataWrapper<Proverb>>(readTextFromUri(it))
                    .data
                    .forEach { proverb ->
                        repository.insertChineseProverb(proverb.asProverbEntity())
                    }
            }
            _chineseProversStatus.value = ImportStatus.Finish
        }
    }

    fun clearChineseProverbs() {
        viewModelScope.launch {
            repository.clearChineseProverb()
            preference.setChineseProverbVersion(0)
        }
    }

    private val _chineseQuoteStatus: MutableStateFlow<ImportStatus<Any>> =
        MutableStateFlow(ImportStatus.Finish)
    val chineseQuoteStatus: SharedFlow<ImportStatus<Any>> = _chineseQuoteStatus
    fun chineseQuotes(uris: List<Uri>) {
        viewModelScope.launch {
            _chineseQuoteStatus.value = ImportStatus.Loading
            uris.forEach {
                json.decodeFromString<DataWrapper<Quote>>(readTextFromUri(it))
                    .data
                    .forEach { quote ->
                        repository.insertChineseQuote(quote.asQuoteEntity())
                    }
            }
            _chineseQuoteStatus.value = ImportStatus.Finish
        }
    }

    fun clearChineseQuotes() {
        viewModelScope.launch {
            repository.clearChineseQuote()
            preference.setChineseQuoteVersion(0)
        }
    }

    private val _chineseWisecrackStatus: MutableStateFlow<ImportStatus<Any>> =
        MutableStateFlow(ImportStatus.Finish)
    val chineseWisecrackStatus: SharedFlow<ImportStatus<Any>> = _chineseWisecrackStatus
    fun chineseWisecrack(uris: List<Uri>) {
        viewModelScope.launch {
            _chineseWisecrackStatus.value = ImportStatus.Loading
            uris.forEach {
                json.decodeFromString<DataWrapper<Wisecrack>>(readTextFromUri(it))
                    .data
                    .forEach { chineseWisecrack ->
                        repository.insertChineseWisecrack(chineseWisecrack.asChineseWisecrackEntity())
                    }

            }
            _chineseWisecrackStatus.value = ImportStatus.Finish
        }
    }

    fun clearChineseWisecracks() {
        viewModelScope.launch {
            repository.clearChineseWisecrack()
            preference.setChineseWisecrackVersion(0)
        }
    }

    private val _chineseKnowledgeStatus: MutableStateFlow<ImportStatus<Any>> =
        MutableStateFlow(ImportStatus.Finish)
    val chineseKnowledgeStatus: SharedFlow<ImportStatus<Any>> = _chineseKnowledgeStatus
    fun chineseKnowledge(uris: List<Uri>) {
        viewModelScope.launch {
            _chineseKnowledgeStatus.value = ImportStatus.Loading
            uris.forEach {
                json.decodeFromString<DataWrapper<ChineseKnowledge>>(readTextFromUri(it))
                    .data
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

    private val _chineseRiddleStatus: MutableStateFlow<ImportStatus<Any>> =
        MutableStateFlow(ImportStatus.Finish)
    val chineseRiddleStatus: SharedFlow<ImportStatus<Any>> = _chineseRiddleStatus
    fun chineseRiddle(uris: List<Uri>) {
        viewModelScope.launch {
            _chineseRiddleStatus.value = ImportStatus.Loading
            uris.forEach {
                json.decodeFromString<DataWrapper<Riddle>>(readTextFromUri(it))
                    .data
                    .forEach { riddle ->
                        repository.insertChineseRiddle(riddle.asRiddleEntity())
                    }
            }
            _chineseRiddleStatus.value = ImportStatus.Finish
        }
    }

    fun clearChineseRiddle() {
        viewModelScope.launch {
            repository.clearChineseRiddle()
            preference.setChineseRiddleVersion(0)
        }
    }

    private val _classicalLiteratureClassicPoemStatus: MutableStateFlow<ImportStatus<Any>> =
        MutableStateFlow(ImportStatus.Finish)
    val classicalLiteratureClassicPoemStatus: SharedFlow<ImportStatus<Any>> =
        _classicalLiteratureClassicPoemStatus

    fun classicalLiteratureClassicPoems(uris: List<Uri>) {
        viewModelScope.launch {
            _classicalLiteratureClassicPoemStatus.value = ImportStatus.Loading
            uris.forEach {
                json.decodeFromString<DataWrapper<ClassicPoem>>(readTextFromUri(it))
                    .data
                    .forEach { poem ->
                        repository.insertClassicalLiteratureClassicPoems(poem.asClassicPoemEntity())
                    }
            }
            _classicalLiteratureClassicPoemStatus.value = ImportStatus.Finish
        }
    }

    fun clearClassicalLiteratureClassicPoems() {
        viewModelScope.launch {
            repository.clearClassicalLiteratureClassicPoem()
            preference.setClassicalLiteratureClassicPoemVersion(0)
        }
    }

    private val _chineseCharacterStatus: MutableStateFlow<ImportStatus<Any>> =
        MutableStateFlow(ImportStatus.Finish)
    val chineseDictionaryStatus: SharedFlow<ImportStatus<Any>> = _chineseCharacterStatus
    fun chineseCharacters(uris: List<Uri>) {
        viewModelScope.launch {
            _chineseCharacterStatus.value = ImportStatus.Loading
            uris.forEach {
                json.decodeFromString<DataWrapper<Character>>(readTextFromUri(it))
                    .data
                    .forEach { character ->
                        repository.insertChineseCharacter(character.asCharacterEntity())
                    }
            }
            _chineseCharacterStatus.value = ImportStatus.Finish
        }
    }

    fun clearChineseCharacters() {
        viewModelScope.launch {
            repository.clearChineseCharacter()
            preference.setChineseDictionaryVersion(0)
        }
    }

    private val _chineseIdiomStatus: MutableStateFlow<ImportStatus<Any>> =
        MutableStateFlow(ImportStatus.Finish)
    val chineseIdiomStatus: SharedFlow<ImportStatus<Any>> = _chineseIdiomStatus
    fun chineseIdioms(uris: List<Uri>) {
        viewModelScope.launch {
            _chineseIdiomStatus.value = ImportStatus.Loading
            uris.forEach {
                json.decodeFromString<DataWrapper<Idiom>>(readTextFromUri(it))
                    .data
                    .forEach { idiom ->
                        repository.insertChineseIdiom(idiom.asIdiomEntity())
                    }
            }
            _chineseIdiomStatus.value = ImportStatus.Finish
        }
    }

    fun clearChineseIdioms() {
        viewModelScope.launch {
            repository.clearChineseIdioms()
            preference.setChineseIdiomVersion(0)
        }
    }

    private val _chineseLyricStatus: MutableStateFlow<ImportStatus<Any>> =
        MutableStateFlow(ImportStatus.Finish)
    val chineseLyricStatus: SharedFlow<ImportStatus<Any>> = _chineseLyricStatus
    fun chineseLyrics(uris: List<Uri>) {
        viewModelScope.launch {
            _chineseLyricStatus.value = ImportStatus.Loading
            uris.forEach {
                json.decodeFromString<DataWrapper<Lyric>>(readTextFromUri(it))
                    .data
                    .forEach { lyric ->
                        repository.insertChineseLyric(lyric.asLyricEntity())
                    }
            }
            _chineseLyricStatus.value = ImportStatus.Finish
        }
    }

    fun clearChineseLyrics() {
        viewModelScope.launch {
            repository.clearChineseLyric()
            preference.setChineseLyricVersion(0)
        }
    }

    private val _classicalLiteraturePeopleStatus: MutableStateFlow<ImportStatus<Any>> =
        MutableStateFlow(ImportStatus.Finish)
    val classicalLiteraturePeopleStatus: SharedFlow<ImportStatus<Any>> =
        _classicalLiteraturePeopleStatus

    fun classicalLiteraturePeople(uris: List<Uri>) {
        viewModelScope.launch {
            _classicalLiteraturePeopleStatus.value = ImportStatus.Loading
            uris.forEach {
                json.decodeFromString<DataWrapper<People>>(readTextFromUri(it))
                    .data
                    .forEach { people ->
                        repository.insertClassicalLiteraturePeople(people.asPeopleEntity())
                    }
            }
            _classicalLiteraturePeopleStatus.value = ImportStatus.Finish
        }
    }

    fun clearClassicalLiteraturePeople() {
        viewModelScope.launch {
            repository.clearClassicalLiteraturePeople()
            preference.setClassicalLiteraturePeopleVersion(0)
        }
    }

    private val _classicalLiteratureSentenceStatus: MutableStateFlow<ImportStatus<Any>> =
        MutableStateFlow(ImportStatus.Finish)
    val classicalLiteratureSentenceStatus: SharedFlow<ImportStatus<Any>> =
        _classicalLiteratureSentenceStatus

    fun classicalLiteratureSentences(uris: List<Uri>) {
        viewModelScope.launch {
            _classicalLiteraturePeopleStatus.value = ImportStatus.Loading
            uris.forEach {
                json.decodeFromString<DataWrapper<Sentence>>(readTextFromUri(it))
                    .data
                    .forEach { poemSentence ->
                        repository.insertClassicalLiteratureSentence(poemSentence.asSentenceEntity())
                    }
            }
            _classicalLiteraturePeopleStatus.value = ImportStatus.Finish
        }
    }

    fun clearClassicalLiteratureSentences() {
        viewModelScope.launch {
            repository.clearClassicalLiteratureSentence()
            preference.setClassicalLiteratureSentenceVersion(0)
        }
    }

    private val _chineseTongueTwisterStatus: MutableStateFlow<ImportStatus<Any>> =
        MutableStateFlow(ImportStatus.Finish)
    val chineseTongueTwisterStatus: SharedFlow<ImportStatus<Any>> = _chineseTongueTwisterStatus
    fun chineseTongueTwisters(uris: List<Uri>) {
        viewModelScope.launch {
            _chineseTongueTwisterStatus.value = ImportStatus.Loading
            uris.forEach {
                json.decodeFromString<DataWrapper<TongueTwister>>(readTextFromUri(it))
                    .data
                    .forEach { tongueTwister ->
                        repository.insertChineseTongueTwister(tongueTwister.asTongueTwisterEntity())
                    }
            }
            _chineseTongueTwisterStatus.value = ImportStatus.Finish
        }
    }

    fun clearChineseTongueTwisters() {
        viewModelScope.launch {
            repository.clearChineseTongueTwister()
            preference.setChineseTongueTwisterVersion(0)
        }
    }

    private val _classicalLiteratureWritingStatus: MutableStateFlow<ImportStatus<Any>> =
        MutableStateFlow(ImportStatus.Finish)
    val classicalLiteratureWritingStatus: SharedFlow<ImportStatus<Any>> =
        _classicalLiteratureWritingStatus

    fun classicalLiteratureWritings(uris: List<Uri>) {
        viewModelScope.launch {
            _classicalLiteratureWritingStatus.value = ImportStatus.Loading
            uris.forEach { uri ->
                val list = mutableListOf<WritingEntity>()
                json.decodeFromString<DataWrapper<Writing>>(readTextFromUri(uri))
                    .data
                    .forEach { writing ->
                        //repository.insertClassicalLiteratureWriting(writing.asWritingEntity())
                        list.add(writing.asWritingEntity())
                    }
                repository.insertClassicalLiteratureWriting(list)
            }
            _classicalLiteratureWritingStatus.value = ImportStatus.Finish
        }
    }

    fun clearClassicalLiteratureWritings() {
        viewModelScope.launch {
            repository.clearClassicalLiteratureWriting()
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