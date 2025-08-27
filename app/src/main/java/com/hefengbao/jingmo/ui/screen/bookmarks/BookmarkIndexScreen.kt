package com.hefengbao.jingmo.ui.screen.bookmarks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.hefengbao.jingmo.data.database.entity.BookmarkEntity
import com.hefengbao.jingmo.data.database.entity.chinese.AntitheticalCoupletEntity
import com.hefengbao.jingmo.data.database.entity.chinese.CharacterEntity
import com.hefengbao.jingmo.data.database.entity.chinese.ExpressionEntity
import com.hefengbao.jingmo.data.database.entity.chinese.IdiomEntity
import com.hefengbao.jingmo.data.database.entity.chinese.KnowledgeEntity
import com.hefengbao.jingmo.data.database.entity.chinese.LyricEntity
import com.hefengbao.jingmo.data.database.entity.chinese.ModernPoetryEntity
import com.hefengbao.jingmo.data.database.entity.chinese.ProverbEntity
import com.hefengbao.jingmo.data.database.entity.chinese.QuoteEntity
import com.hefengbao.jingmo.data.database.entity.chinese.WisecrackEntity
import com.hefengbao.jingmo.data.database.entity.classicalliterature.ClassicPoemEntity
import com.hefengbao.jingmo.data.database.entity.classicalliterature.SentenceEntity
import com.hefengbao.jingmo.data.database.entity.classicalliterature.WritingEntity
import com.hefengbao.jingmo.data.enums.Category
import com.hefengbao.jingmo.ui.component.SimpleScaffold

@Composable
fun BookmarkIndexRoute(
    onBackClick: () -> Unit,
    onChineseAntitheticalCoupletCardClick: (Int) -> Unit,
    onChineseCharacterCardClick: (Int) -> Unit,
    onChineseExpressionCardClick: (Int) -> Unit,
    onChineseIdiomCardClick: (Int) -> Unit,
    onChineseKnowledgeCardClick: (Int) -> Unit,
    onChineseLyricCardClick: (Int) -> Unit,
    onChineseModernPoetryCardClick: (Int) -> Unit,
    onChineseProverbCardClick: (Int) -> Unit,
    onChineseQuoteCardClick: (Int) -> Unit,
    onChineseWisecrackCardClick: (Int) -> Unit,
    onClassicalLiteratureClassicPoemCardClick: (Int) -> Unit,
    onClassicalLiteratureSentenceCardClick: (Int) -> Unit,
    onClassicalLiteratureWritingCardClick: (Int) -> Unit,
    viewModel: BookmarkIndexViewModel = hiltViewModel<BookmarkIndexViewModel>()
) {
    val bookmarks = viewModel.bookmarks.collectAsLazyPagingItems()
    val chineseAntitheticalCouplets by viewModel.chineseAntitheticalCouplets.collectAsState(
        emptyList()
    )
    val chineseCharacters by viewModel.chineseCharacters.collectAsState(emptyList())
    val chineseExpressions by viewModel.chineseExpressions.collectAsState(emptyList())
    val chineseIdioms by viewModel.chineseIdioms.collectAsState(emptyList())
    val chineseKnowledge by viewModel.chineseKnowledge.collectAsState(emptyList())
    val chineseLyrics by viewModel.chineseLyrics.collectAsState(emptyList())
    val chineseModernPoetry by viewModel.chineseModernPoetry.collectAsState(emptyList())
    val chineseProverbs by viewModel.chineseProverbs.collectAsState(emptyList())
    val chineseQuotes by viewModel.chineseQuotes.collectAsState(emptyList())
    val chineseWisecracks by viewModel.chineseWisecracks.collectAsState(emptyList())
    val classicalLiteratureClassicPoems by viewModel.classicalLiteratureClassicPoems.collectAsState(
        emptyList()
    )
    val classicalLiteratureSentences by viewModel.classicalLiteratureSentences.collectAsState(
        emptyList()
    )
    val classicalLiteratureWritings by viewModel.classicalLiteratureWritings.collectAsState(
        emptyList()
    )

    LaunchedEffect(bookmarks.itemSnapshotList) {
        bookmarks.itemSnapshotList.map { bookmarkEntity ->
            bookmarkEntity?.let {
                viewModel.set(bookmarkEntity)
            }
        }
    }

    BookmarkIndexScreen(
        onBackClick = onBackClick,
        bookmarks = bookmarks,
        onChineseAntitheticalCoupletCardClick = onChineseAntitheticalCoupletCardClick,
        chineseAntitheticalCouplets = chineseAntitheticalCouplets,
        onChineseCharacterCardClick = onChineseCharacterCardClick,
        chineseCharacters = chineseCharacters,
        onChineseExpressionCardClick = onChineseExpressionCardClick,
        chineseExpressions = chineseExpressions,
        onChineseIdiomCardClick = onChineseIdiomCardClick,
        chineseIdioms = chineseIdioms,
        onChineseKnowledgeCardClick = onChineseKnowledgeCardClick,
        chineseKnowledge = chineseKnowledge,
        onChineseLyricCardClick = onChineseLyricCardClick,
        chineseLyrics = chineseLyrics,
        onChineseModernPoetryCardClick = onChineseModernPoetryCardClick,
        chineseModernPoetry = chineseModernPoetry,
        onChineseProverbCardClick = onChineseProverbCardClick,
        chineseProverbs = chineseProverbs,
        onChineseQuoteCardClick = onChineseQuoteCardClick,
        chineseQuotes = chineseQuotes,
        onChineseWisecrackCardClick = onChineseWisecrackCardClick,
        chineseWisecracks = chineseWisecracks,
        onClassicalLiteratureClassicPoemCardClick = onClassicalLiteratureClassicPoemCardClick,
        classicalLiteratureClassicPoems = classicalLiteratureClassicPoems,
        onClassicalLiteratureSentenceCardClick = onClassicalLiteratureSentenceCardClick,
        classicalLiteratureSentences = classicalLiteratureSentences,
        onClassicalLiteratureWritingCardClick = onClassicalLiteratureWritingCardClick,
        classicalLiteratureWritings = classicalLiteratureWritings,
    )
}

@Composable
private fun BookmarkIndexScreen(
    onBackClick: () -> Unit,
    bookmarks: LazyPagingItems<BookmarkEntity>,
    onChineseAntitheticalCoupletCardClick: (Int) -> Unit,
    chineseAntitheticalCouplets: List<AntitheticalCoupletEntity>?,
    onChineseCharacterCardClick: (Int) -> Unit,
    chineseCharacters: List<CharacterEntity>?,
    onChineseExpressionCardClick: (Int) -> Unit,
    chineseExpressions: List<ExpressionEntity>?,
    onChineseIdiomCardClick: (Int) -> Unit,
    chineseIdioms: List<IdiomEntity>?,
    onChineseKnowledgeCardClick: (Int) -> Unit,
    chineseKnowledge: List<KnowledgeEntity>?,
    onChineseLyricCardClick: (Int) -> Unit,
    chineseLyrics: List<LyricEntity>?,
    onChineseModernPoetryCardClick: (Int) -> Unit,
    chineseModernPoetry: List<ModernPoetryEntity>?,
    onChineseProverbCardClick: (Int) -> Unit,
    chineseProverbs: List<ProverbEntity>?,
    onChineseQuoteCardClick: (Int) -> Unit,
    chineseQuotes: List<QuoteEntity>?,
    onChineseWisecrackCardClick: (Int) -> Unit,
    chineseWisecracks: List<WisecrackEntity>?,
    onClassicalLiteratureClassicPoemCardClick: (Int) -> Unit,
    classicalLiteratureClassicPoems: List<ClassicPoemEntity>?,
    onClassicalLiteratureSentenceCardClick: (Int) -> Unit,
    classicalLiteratureSentences: List<SentenceEntity>?,
    onClassicalLiteratureWritingCardClick: (Int) -> Unit,
    classicalLiteratureWritings: List<WritingEntity>?,
) {
    SimpleScaffold(onBackClick, "收藏夹") {
        LazyColumn {
            items(count = bookmarks.itemCount) {
                bookmarks[it]?.let { entity ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        onClick = {
                            when (Category.from(entity.bookmarkableModel)) {
                                Category.ChinaWorldCulturalHeritage -> {}
                                Category.ChineseAntitheticalCouplet -> onChineseAntitheticalCoupletCardClick(
                                    entity.bookmarkableId
                                )

                                Category.ChineseCharacter -> onChineseCharacterCardClick(entity.bookmarkableId)
                                Category.ChineseExpression -> onChineseExpressionCardClick(entity.bookmarkableId)
                                Category.ChineseIdiom -> onChineseIdiomCardClick(entity.bookmarkableId)
                                Category.ChineseKnowledge -> onChineseKnowledgeCardClick(entity.bookmarkableId)
                                Category.ChineseLyric -> onChineseLyricCardClick(entity.bookmarkableId)
                                Category.ChineseModernPoetry -> onChineseModernPoetryCardClick(
                                    entity.bookmarkableId
                                )

                                Category.ChineseProverb -> onChineseProverbCardClick(entity.bookmarkableId)
                                Category.ChineseQuote -> onChineseQuoteCardClick(entity.bookmarkableId)
                                Category.ChineseRiddle -> {}
                                Category.ChineseTongueTwister -> {}
                                Category.ChineseWisecrack -> onChineseWisecrackCardClick(entity.bookmarkableId)
                                Category.ClassicalLiteratureClassicPoem -> onClassicalLiteratureClassicPoemCardClick(
                                    entity.bookmarkableId
                                )

                                Category.ClassicalLiteraturePeople -> {}
                                Category.ClassicalLiteratureSentence -> onClassicalLiteratureSentenceCardClick(
                                    entity.bookmarkableId
                                )

                                Category.ClassicalLiteratureWriting -> onClassicalLiteratureWritingCardClick(
                                    entity.bookmarkableId
                                )

                                Category.TraditionalCultureCalendar -> {}
                                Category.TraditionalCultureColor -> {}
                                Category.TraditionalCultureFestival -> {}
                                Category.TraditionalCultureSolarTerm -> {}
                                null -> {}
                            }
                        }
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "#${Category.from(entity.bookmarkableModel)?.label}",
                                color = MaterialTheme.colorScheme.primary,
                                style = MaterialTheme.typography.bodySmall
                            )
                            when (Category.from(entity.bookmarkableModel)) {
                                Category.ChinaWorldCulturalHeritage -> {}
                                Category.ChineseAntitheticalCouplet -> {
                                    val antitheticalCouplet =
                                        chineseAntitheticalCouplets?.firstOrNull { antitheticalCouplet -> entity.bookmarkableId == antitheticalCouplet.id }
                                    antitheticalCouplet?.let {
                                        Text(antitheticalCouplet.body)
                                    }
                                }

                                Category.ChineseCharacter -> {
                                    val character =
                                        chineseCharacters?.firstOrNull { character -> entity.bookmarkableId == character.id }
                                    character?.let {
                                        Text(
                                            text = "${character.character}（${character.pinyin}）"
                                        )
                                    }
                                }

                                Category.ChineseExpression -> {
                                    val expression =
                                        chineseExpressions?.firstOrNull { expression -> entity.bookmarkableId == expression.id }
                                    expression?.let {
                                        Text("${expression.word}（${expression.pinyin}）")
                                    }
                                }

                                Category.ChineseIdiom -> {
                                    val idiom =
                                        chineseIdioms?.firstOrNull { idiom -> entity.bookmarkableId == idiom.id }
                                    idiom?.let {
                                        Text("${idiom.word}（${idiom.pinyin}）")
                                    }
                                }

                                Category.ChineseKnowledge -> {
                                    val knowledge =
                                        chineseKnowledge?.firstOrNull { knowledgeEntity -> entity.bookmarkableId == knowledgeEntity.id }
                                    knowledge?.let {
                                        Text(
                                            text = knowledge.content,
                                            maxLines = 2,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }
                                }

                                Category.ChineseLyric -> {
                                    val lyric =
                                        chineseLyrics?.firstOrNull { lyricEntity -> entity.bookmarkableId == lyricEntity.id }
                                    lyric?.let {
                                        Text(
                                            text = "${lyric.title}（词：${lyric.writer ?: ""}）"
                                        )
                                        Text(
                                            text = lyric.content,
                                            maxLines = 2,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }
                                }

                                Category.ChineseModernPoetry -> {
                                    val poetry =
                                        chineseModernPoetry?.firstOrNull { poetryEntity -> entity.bookmarkableId == poetryEntity.id }
                                    poetry?.let {
                                        Text("${poetry.title}(作者：${poetry.author})")
                                        Text(
                                            text = poetry.content,
                                            maxLines = 2,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }
                                }

                                Category.ChineseProverb -> {
                                    val proverb =
                                        chineseProverbs?.firstOrNull { proverbEntity -> entity.bookmarkableId == proverbEntity.id }
                                    proverb?.let {
                                        Text(proverb.content)
                                    }
                                }

                                Category.ChineseQuote -> {
                                    val quote =
                                        chineseQuotes?.firstOrNull { quoteEntity -> entity.bookmarkableId == quoteEntity.id }
                                    quote?.let {
                                        Text(
                                            text = quote.content,
                                            maxLines = 2,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }
                                }

                                Category.ChineseRiddle -> {}
                                Category.ChineseTongueTwister -> {}
                                Category.ChineseWisecrack -> {
                                    val wisecrack =
                                        chineseWisecracks?.firstOrNull { wisecrackEntity -> entity.bookmarkableId == wisecrackEntity.id }
                                    wisecrack?.let {
                                        Text("${wisecrack.riddle}——${wisecrack.answer}")
                                    }
                                }

                                Category.ClassicalLiteratureClassicPoem -> {
                                    val poem =
                                        classicalLiteratureClassicPoems?.firstOrNull { poemEntity -> entity.bookmarkableId == poemEntity.id }
                                    poem?.let { classicPoemEntity ->
                                        Text(
                                            text = "${classicPoemEntity.title}（${classicPoemEntity.writer}）"
                                        )
                                        Text(
                                            text = classicPoemEntity.content,
                                            maxLines = 2,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }
                                }

                                Category.ClassicalLiteraturePeople -> {}
                                Category.ClassicalLiteratureSentence -> {
                                    val sentence =
                                        classicalLiteratureSentences?.firstOrNull { sentenceEntity -> entity.bookmarkableId == sentenceEntity.id }
                                    sentence?.let {
                                        Text(
                                            text = sentence.content
                                        )
                                    }
                                }

                                Category.ClassicalLiteratureWriting -> {
                                    val writing =
                                        classicalLiteratureWritings?.firstOrNull { writingEntity -> entity.bookmarkableId == writingEntity.id }
                                    writing?.let { writingEntity ->
                                        Text("${writingEntity.title.content}（${writingEntity.author}）")
                                        Text(
                                            text = writingEntity.content ?: "",
                                            maxLines = 2,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }
                                }

                                Category.TraditionalCultureCalendar -> {}
                                Category.TraditionalCultureColor -> {}
                                Category.TraditionalCultureFestival -> {}
                                Category.TraditionalCultureSolarTerm -> {}
                                null -> {}
                            }
                        }
                    }
                }
            }
        }
    }
}