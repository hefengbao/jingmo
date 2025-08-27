package com.hefengbao.jingmo.ui.screen.bookmarks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.hefengbao.jingmo.data.database.entity.BookmarkEntity
import com.hefengbao.jingmo.data.enums.Category
import com.hefengbao.jingmo.data.repository.BookmarkRepository
import com.hefengbao.jingmo.data.repository.chinese.AntitheticalCoupletRepository
import com.hefengbao.jingmo.data.repository.chinese.CharacterRepository
import com.hefengbao.jingmo.data.repository.chinese.ExpressionRepository
import com.hefengbao.jingmo.data.repository.chinese.IdiomRepository
import com.hefengbao.jingmo.data.repository.chinese.KnowledgeRepository
import com.hefengbao.jingmo.data.repository.chinese.LyricRepository
import com.hefengbao.jingmo.data.repository.chinese.ModernPoetryRepository
import com.hefengbao.jingmo.data.repository.chinese.ProverbRepository
import com.hefengbao.jingmo.data.repository.chinese.QuoteRepository
import com.hefengbao.jingmo.data.repository.chinese.WisecrackRepository
import com.hefengbao.jingmo.data.repository.classicalliterature.ClassicPoemRepository
import com.hefengbao.jingmo.data.repository.classicalliterature.SentenceRepository
import com.hefengbao.jingmo.data.repository.classicalliterature.WritingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class BookmarkIndexViewModel @Inject constructor(
    bookmarkRepository: BookmarkRepository,
    private val antitheticalCoupletRepository: AntitheticalCoupletRepository,
    private val characterRepository: CharacterRepository,
    private val expressionRepository: ExpressionRepository,
    private val idiomRepository: IdiomRepository,
    private val knowledgeRepository: KnowledgeRepository,
    private val lyricRepository: LyricRepository,
    private val modernPoetryRepository: ModernPoetryRepository,
    private val proverbRepository: ProverbRepository,
    private val quoteRepository: QuoteRepository,
    private val wisecrackRepository: WisecrackRepository,
    private val classisPoemRepository: ClassicPoemRepository,
    private val sentenceRepository: SentenceRepository,
    private val writingRepository: WritingRepository
) : ViewModel() {
    val bookmarks = bookmarkRepository.bookmarks().cachedIn(viewModelScope)

    private val chineseAntitheticalCoupletIds: MutableStateFlow<List<Int>> =
        MutableStateFlow(emptyList())
    private val chineseCharacterIds: MutableStateFlow<List<Int>> = MutableStateFlow(emptyList())
    private val chineseExpressionIds: MutableStateFlow<List<Int>> = MutableStateFlow(emptyList())
    private val chineseIdiomIds: MutableStateFlow<List<Int>> = MutableStateFlow(emptyList())
    private val chineseKnowledgeIds: MutableStateFlow<List<Int>> = MutableStateFlow(emptyList())
    private val chineseLyricIds: MutableStateFlow<List<Int>> = MutableStateFlow(emptyList())
    private val chineseModernPoetryIds: MutableStateFlow<List<Int>> = MutableStateFlow(emptyList())
    private val chineseProverbIds: MutableStateFlow<List<Int>> = MutableStateFlow(emptyList())
    private val chineseQuoteIds: MutableStateFlow<List<Int>> = MutableStateFlow(emptyList())
    private val chineseRiddleIds: MutableStateFlow<List<Int>> = MutableStateFlow(emptyList())
    private val chineseWisecrackIds: MutableStateFlow<List<Int>> = MutableStateFlow(emptyList())
    private val classicalLiteratureClassicPoemIds: MutableStateFlow<List<Int>> =
        MutableStateFlow(emptyList())
    private val classicalLiteratureSentenceIds: MutableStateFlow<List<Int>> =
        MutableStateFlow(emptyList())
    private val classicalLiteratureWritingIds: MutableStateFlow<List<Int>> =
        MutableStateFlow(emptyList())

    fun set(entity: BookmarkEntity) {
        when (Category.from(entity.bookmarkableModel)) {
            Category.ChinaWorldCulturalHeritage -> {}
            Category.ChineseAntitheticalCouplet -> {
                if (!chineseAntitheticalCoupletIds.value.contains(entity.bookmarkableId)) {
                    chineseAntitheticalCoupletIds.value += entity.bookmarkableId
                }
            }

            Category.ChineseCharacter -> {
                if (!chineseCharacterIds.value.contains(entity.bookmarkableId)) {
                    chineseCharacterIds.value += entity.bookmarkableId
                }
            }

            Category.ChineseExpression -> {
                if (!chineseExpressionIds.value.contains(entity.bookmarkableId)) {
                    chineseExpressionIds.value += entity.bookmarkableId
                }
            }

            Category.ChineseIdiom -> {
                if (!chineseIdiomIds.value.contains(entity.bookmarkableId)) {
                    chineseIdiomIds.value += entity.bookmarkableId
                }
            }

            Category.ChineseKnowledge -> {
                if (!chineseKnowledgeIds.value.contains(entity.bookmarkableId)) {
                    chineseKnowledgeIds.value += entity.bookmarkableId
                }
            }

            Category.ChineseLyric -> {
                if (!chineseLyricIds.value.contains(entity.bookmarkableId)) {
                    chineseLyricIds.value += entity.bookmarkableId
                }
            }

            Category.ChineseModernPoetry -> {
                if (!chineseModernPoetryIds.value.contains(entity.bookmarkableId)) {
                    chineseModernPoetryIds.value += entity.bookmarkableId
                }
            }

            Category.ChineseProverb -> {
                if (!chineseProverbIds.value.contains(entity.bookmarkableId)) {
                    chineseProverbIds.value += entity.bookmarkableId
                }
            }

            Category.ChineseQuote -> {
                if (!chineseQuoteIds.value.contains(entity.bookmarkableId)) {
                    chineseQuoteIds.value += entity.bookmarkableId
                }
            }

            Category.ChineseRiddle -> {
                if (!chineseRiddleIds.value.contains(entity.bookmarkableId)) {
                    chineseRiddleIds.value += entity.bookmarkableId
                }
            }

            Category.ChineseTongueTwister -> {}
            Category.ChineseWisecrack -> {
                if (!chineseWisecrackIds.value.contains(entity.bookmarkableId)) {
                    chineseWisecrackIds.value += entity.bookmarkableId
                }
            }

            Category.ClassicalLiteratureClassicPoem -> {
                if (!classicalLiteratureClassicPoemIds.value.contains(entity.bookmarkableId)) {
                    classicalLiteratureClassicPoemIds.value += entity.bookmarkableId
                }
            }

            Category.ClassicalLiteraturePeople -> {}
            Category.ClassicalLiteratureSentence -> {
                if (!classicalLiteratureSentenceIds.value.contains(entity.bookmarkableId)) {
                    classicalLiteratureSentenceIds.value += entity.bookmarkableId
                }
            }

            Category.ClassicalLiteratureWriting -> {
                if (!classicalLiteratureWritingIds.value.contains(entity.bookmarkableId)) {
                    classicalLiteratureWritingIds.value += entity.bookmarkableId
                }
            }

            Category.TraditionalCultureCalendar -> {}
            Category.TraditionalCultureColor -> {}
            Category.TraditionalCultureFestival -> {}
            Category.TraditionalCultureSolarTerm -> {}
            null -> {}
        }
    }

    val chineseAntitheticalCouplets = chineseAntitheticalCoupletIds
        .flatMapLatest { antitheticalCoupletRepository.get(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )

    val chineseCharacters = chineseCharacterIds
        .flatMapLatest { characterRepository.get(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )

    val chineseExpressions = chineseExpressionIds
        .flatMapLatest { expressionRepository.get(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )

    val chineseIdioms = chineseIdiomIds
        .flatMapLatest { idiomRepository.get(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )

    val chineseKnowledge = chineseKnowledgeIds
        .flatMapLatest { knowledgeRepository.get(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )

    val chineseLyrics = chineseLyricIds
        .flatMapLatest { lyricRepository.get(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )

    val chineseModernPoetry = chineseModernPoetryIds
        .flatMapLatest { modernPoetryRepository.get(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )

    val chineseProverbs = chineseProverbIds
        .flatMapLatest { proverbRepository.get(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )

    val chineseQuotes = chineseQuoteIds
        .flatMapLatest { quoteRepository.get(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )

    val chineseWisecracks = chineseWisecrackIds
        .flatMapLatest { wisecrackRepository.get(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )

    val classicalLiteratureClassicPoems = classicalLiteratureClassicPoemIds
        .flatMapLatest { classisPoemRepository.get(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )

    val classicalLiteratureSentences = classicalLiteratureSentenceIds
        .flatMapLatest { sentenceRepository.get(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )

    val classicalLiteratureWritings = classicalLiteratureWritingIds
        .flatMapLatest { writingRepository.get(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )
}