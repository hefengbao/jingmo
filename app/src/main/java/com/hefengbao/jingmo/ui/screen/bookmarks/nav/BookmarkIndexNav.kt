package com.hefengbao.jingmo.ui.screen.bookmarks.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hefengbao.jingmo.ui.screen.bookmarks.BookmarkIndexRoute

private const val ROUTE = "bookmark_index"

fun NavController.navigateToBookmarkIndexScreen() {
    this.navigate(ROUTE)
}

fun NavGraphBuilder.bookmarkIndexScreen(
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
) {
    composable(ROUTE) {
        BookmarkIndexRoute(
            onBackClick = onBackClick,
            onChineseAntitheticalCoupletCardClick = onChineseAntitheticalCoupletCardClick,
            onChineseCharacterCardClick = onChineseCharacterCardClick,
            onChineseExpressionCardClick = onChineseExpressionCardClick,
            onChineseIdiomCardClick = onChineseIdiomCardClick,
            onChineseKnowledgeCardClick = onChineseKnowledgeCardClick,
            onChineseLyricCardClick = onChineseLyricCardClick,
            onChineseModernPoetryCardClick = onChineseModernPoetryCardClick,
            onChineseProverbCardClick = onChineseProverbCardClick,
            onChineseQuoteCardClick = onChineseQuoteCardClick,
            onChineseWisecrackCardClick = onChineseWisecrackCardClick,
            onClassicalLiteratureClassicPoemCardClick = onClassicalLiteratureClassicPoemCardClick,
            onClassicalLiteratureSentenceCardClick = onClassicalLiteratureSentenceCardClick,
            onClassicalLiteratureWritingCardClick = onClassicalLiteratureWritingCardClick
        )
    }
}