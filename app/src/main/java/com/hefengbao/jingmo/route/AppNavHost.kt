/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.route

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.hefengbao.jingmo.ui.screen.bookmarks.nav.bookmarkIndexScreen
import com.hefengbao.jingmo.ui.screen.bookmarks.nav.navigateToBookmarkIndexScreen
import com.hefengbao.jingmo.ui.screen.china.worldcultureheritage.nav.chinaWorldCultureHeritageGraph
import com.hefengbao.jingmo.ui.screen.china.worldcultureheritage.nav.chinaWorldCultureHeritageShowScreen
import com.hefengbao.jingmo.ui.screen.china.worldcultureheritage.nav.navigateToChinaWorldCultureHeritageGraph
import com.hefengbao.jingmo.ui.screen.china.worldcultureheritage.nav.navigateToChinaWorldCultureHeritageShowScreen
import com.hefengbao.jingmo.ui.screen.chinese.antitheticalcouplet.nav.chineseAntitheticalCoupletBookmarksScreen
import com.hefengbao.jingmo.ui.screen.chinese.antitheticalcouplet.nav.chineseAntitheticalCoupletCaptureScreen
import com.hefengbao.jingmo.ui.screen.chinese.antitheticalcouplet.nav.chineseAntitheticalCoupletIndexGraph
import com.hefengbao.jingmo.ui.screen.chinese.antitheticalcouplet.nav.chineseAntitheticalCoupletReadScreen
import com.hefengbao.jingmo.ui.screen.chinese.antitheticalcouplet.nav.chineseAntitheticalCoupletSearchScreen
import com.hefengbao.jingmo.ui.screen.chinese.antitheticalcouplet.nav.chineseAntitheticalCoupletShowScreen
import com.hefengbao.jingmo.ui.screen.chinese.antitheticalcouplet.nav.navigateToChineseAntitheticalCoupletBookmarksScreen
import com.hefengbao.jingmo.ui.screen.chinese.antitheticalcouplet.nav.navigateToChineseAntitheticalCoupletCaptureScreen
import com.hefengbao.jingmo.ui.screen.chinese.antitheticalcouplet.nav.navigateToChineseAntitheticalCoupletIndexGraph
import com.hefengbao.jingmo.ui.screen.chinese.antitheticalcouplet.nav.navigateToChineseAntitheticalCoupletReadScreen
import com.hefengbao.jingmo.ui.screen.chinese.antitheticalcouplet.nav.navigateToChineseAntitheticalCoupletSearchScreen
import com.hefengbao.jingmo.ui.screen.chinese.antitheticalcouplet.nav.navigateToChineseAntitheticalCoupletShowScreen
import com.hefengbao.jingmo.ui.screen.chinese.character.nav.chineseCharacterBookmarksScreen
import com.hefengbao.jingmo.ui.screen.chinese.character.nav.chineseCharacterIndexGraph
import com.hefengbao.jingmo.ui.screen.chinese.character.nav.chineseCharacterRadicalScreen
import com.hefengbao.jingmo.ui.screen.chinese.character.nav.chineseCharacterSearchListScreen
import com.hefengbao.jingmo.ui.screen.chinese.character.nav.chineseCharacterShowScreen
import com.hefengbao.jingmo.ui.screen.chinese.character.nav.chineseCharacterStrokeScreen
import com.hefengbao.jingmo.ui.screen.chinese.character.nav.chineseCharacterStrokeShowScreen
import com.hefengbao.jingmo.ui.screen.chinese.character.nav.chineseCharacterSyllableScreen
import com.hefengbao.jingmo.ui.screen.chinese.character.nav.navigateToChineseCharacterBookmarksScreen
import com.hefengbao.jingmo.ui.screen.chinese.character.nav.navigateToChineseCharacterIndexGraph
import com.hefengbao.jingmo.ui.screen.chinese.character.nav.navigateToChineseCharacterRadicalScreen
import com.hefengbao.jingmo.ui.screen.chinese.character.nav.navigateToChineseCharacterSearchListScreen
import com.hefengbao.jingmo.ui.screen.chinese.character.nav.navigateToChineseCharacterShowScreen
import com.hefengbao.jingmo.ui.screen.chinese.character.nav.navigateToChineseCharacterStrokeScreen
import com.hefengbao.jingmo.ui.screen.chinese.character.nav.navigateToChineseCharacterStrokeShowScreen
import com.hefengbao.jingmo.ui.screen.chinese.character.nav.navigateToChineseCharacterSyllableScreen
import com.hefengbao.jingmo.ui.screen.chinese.expression.nav.chineseExpressionBookmarksScreen
import com.hefengbao.jingmo.ui.screen.chinese.expression.nav.chineseExpressionGraph
import com.hefengbao.jingmo.ui.screen.chinese.expression.nav.chineseExpressionSearchScreen
import com.hefengbao.jingmo.ui.screen.chinese.expression.nav.chineseExpressionShowScreen
import com.hefengbao.jingmo.ui.screen.chinese.expression.nav.navigateToChineseExpressionBookmarksScreen
import com.hefengbao.jingmo.ui.screen.chinese.expression.nav.navigateToChineseExpressionGraph
import com.hefengbao.jingmo.ui.screen.chinese.expression.nav.navigateToChineseExpressionSearchScreen
import com.hefengbao.jingmo.ui.screen.chinese.expression.nav.navigateToChineseExpressionShow
import com.hefengbao.jingmo.ui.screen.chinese.idiom.nav.chineseIdiomBookmarksScreen
import com.hefengbao.jingmo.ui.screen.chinese.idiom.nav.chineseIdiomCaptureScreen
import com.hefengbao.jingmo.ui.screen.chinese.idiom.nav.chineseIdiomIndexGraph
import com.hefengbao.jingmo.ui.screen.chinese.idiom.nav.chineseIdiomReadScreen
import com.hefengbao.jingmo.ui.screen.chinese.idiom.nav.chineseIdiomSearchScreen
import com.hefengbao.jingmo.ui.screen.chinese.idiom.nav.chineseIdiomShowScreen
import com.hefengbao.jingmo.ui.screen.chinese.idiom.nav.navigateToChineseIdiomBookmarksScreen
import com.hefengbao.jingmo.ui.screen.chinese.idiom.nav.navigateToChineseIdiomCaptureScreen
import com.hefengbao.jingmo.ui.screen.chinese.idiom.nav.navigateToChineseIdiomIndexGraph
import com.hefengbao.jingmo.ui.screen.chinese.idiom.nav.navigateToChineseIdiomReadScreen
import com.hefengbao.jingmo.ui.screen.chinese.idiom.nav.navigateToChineseIdiomSearchScreen
import com.hefengbao.jingmo.ui.screen.chinese.idiom.nav.navigateToChineseIdiomShowScreen
import com.hefengbao.jingmo.ui.screen.chinese.knowledge.nav.chineseKnowledgeBookmarksScreen
import com.hefengbao.jingmo.ui.screen.chinese.knowledge.nav.chineseKnowledgeIndexGraph
import com.hefengbao.jingmo.ui.screen.chinese.knowledge.nav.chineseKnowledgeReadScreen
import com.hefengbao.jingmo.ui.screen.chinese.knowledge.nav.chineseKnowledgeSearchScreen
import com.hefengbao.jingmo.ui.screen.chinese.knowledge.nav.chineseKnowledgeShowScreen
import com.hefengbao.jingmo.ui.screen.chinese.knowledge.nav.navigateToChineseKnowSearchScreen
import com.hefengbao.jingmo.ui.screen.chinese.knowledge.nav.navigateToChineseKnowledgeBookmarksScreen
import com.hefengbao.jingmo.ui.screen.chinese.knowledge.nav.navigateToChineseKnowledgeIndexGraph
import com.hefengbao.jingmo.ui.screen.chinese.knowledge.nav.navigateToChineseKnowledgeReadScreen
import com.hefengbao.jingmo.ui.screen.chinese.knowledge.nav.navigateToChineseKnowledgeShowScreen
import com.hefengbao.jingmo.ui.screen.chinese.lyric.nav.chineseLyricBookmarksScreen
import com.hefengbao.jingmo.ui.screen.chinese.lyric.nav.chineseLyricCaptureScreen
import com.hefengbao.jingmo.ui.screen.chinese.lyric.nav.chineseLyricIndexGraph
import com.hefengbao.jingmo.ui.screen.chinese.lyric.nav.chineseLyricReadScreen
import com.hefengbao.jingmo.ui.screen.chinese.lyric.nav.chineseLyricSearchScreen
import com.hefengbao.jingmo.ui.screen.chinese.lyric.nav.chineseLyricShowScreen
import com.hefengbao.jingmo.ui.screen.chinese.lyric.nav.navigateToChineseLyricBookmarksScreen
import com.hefengbao.jingmo.ui.screen.chinese.lyric.nav.navigateToChineseLyricCaptureScreen
import com.hefengbao.jingmo.ui.screen.chinese.lyric.nav.navigateToChineseLyricIndexGraph
import com.hefengbao.jingmo.ui.screen.chinese.lyric.nav.navigateToChineseLyricReadScreen
import com.hefengbao.jingmo.ui.screen.chinese.lyric.nav.navigateToChineseLyricSearchScreen
import com.hefengbao.jingmo.ui.screen.chinese.lyric.nav.navigateToChineseLyricShowScreen
import com.hefengbao.jingmo.ui.screen.chinese.poetry.nav.chineseModernPoetryBookmarksScreen
import com.hefengbao.jingmo.ui.screen.chinese.poetry.nav.chineseModernPoetryCaptureScreen
import com.hefengbao.jingmo.ui.screen.chinese.poetry.nav.chineseModernPoetryIndexGraph
import com.hefengbao.jingmo.ui.screen.chinese.poetry.nav.chineseModernPoetryReadScreen
import com.hefengbao.jingmo.ui.screen.chinese.poetry.nav.chineseModernPoetrySearchScreen
import com.hefengbao.jingmo.ui.screen.chinese.poetry.nav.chineseModernPoetryShowScreen
import com.hefengbao.jingmo.ui.screen.chinese.poetry.nav.navigateToChineseModernPoetryBookmarksScreen
import com.hefengbao.jingmo.ui.screen.chinese.poetry.nav.navigateToChineseModernPoetryCaptureScreen
import com.hefengbao.jingmo.ui.screen.chinese.poetry.nav.navigateToChineseModernPoetryIndexGraph
import com.hefengbao.jingmo.ui.screen.chinese.poetry.nav.navigateToChineseModernPoetryReadScreen
import com.hefengbao.jingmo.ui.screen.chinese.poetry.nav.navigateToChineseModernPoetrySearchScreen
import com.hefengbao.jingmo.ui.screen.chinese.poetry.nav.navigateToChineseModernPoetryShowScreen
import com.hefengbao.jingmo.ui.screen.chinese.proverb.nav.chineseProverbBookmarksScreen
import com.hefengbao.jingmo.ui.screen.chinese.proverb.nav.chineseProverbIndexGraph
import com.hefengbao.jingmo.ui.screen.chinese.proverb.nav.chineseProverbReadScreen
import com.hefengbao.jingmo.ui.screen.chinese.proverb.nav.chineseProverbSearchScreen
import com.hefengbao.jingmo.ui.screen.chinese.proverb.nav.chineseProverbShowScreen
import com.hefengbao.jingmo.ui.screen.chinese.proverb.nav.navigateToChineseProverbBookmarksScreen
import com.hefengbao.jingmo.ui.screen.chinese.proverb.nav.navigateToChineseProverbIndexGraph
import com.hefengbao.jingmo.ui.screen.chinese.proverb.nav.navigateToChineseProverbReadScreen
import com.hefengbao.jingmo.ui.screen.chinese.proverb.nav.navigateToChineseProverbSearchScreen
import com.hefengbao.jingmo.ui.screen.chinese.proverb.nav.navigateToChineseProverbShowScreen
import com.hefengbao.jingmo.ui.screen.chinese.quote.nav.chineseQuoteBookmarksScreen
import com.hefengbao.jingmo.ui.screen.chinese.quote.nav.chineseQuoteCaptureScreen
import com.hefengbao.jingmo.ui.screen.chinese.quote.nav.chineseQuoteIndexGraph
import com.hefengbao.jingmo.ui.screen.chinese.quote.nav.chineseQuoteReadScreen
import com.hefengbao.jingmo.ui.screen.chinese.quote.nav.chineseQuoteSearchScreen
import com.hefengbao.jingmo.ui.screen.chinese.quote.nav.chineseQuoteShowScreen
import com.hefengbao.jingmo.ui.screen.chinese.quote.nav.navigateToChineseQuoteBookmarksScreen
import com.hefengbao.jingmo.ui.screen.chinese.quote.nav.navigateToChineseQuoteCaptureScreen
import com.hefengbao.jingmo.ui.screen.chinese.quote.nav.navigateToChineseQuoteIndexGraph
import com.hefengbao.jingmo.ui.screen.chinese.quote.nav.navigateToChineseQuoteReadScreen
import com.hefengbao.jingmo.ui.screen.chinese.quote.nav.navigateToChineseQuoteSearchScreen
import com.hefengbao.jingmo.ui.screen.chinese.quote.nav.navigateToChineseQuoteShowScreen
import com.hefengbao.jingmo.ui.screen.chinese.riddle.nav.chineseRiddleIndexGraph
import com.hefengbao.jingmo.ui.screen.chinese.riddle.nav.chineseRiddleInfoScreen
import com.hefengbao.jingmo.ui.screen.chinese.riddle.nav.chineseRiddleReadScreen
import com.hefengbao.jingmo.ui.screen.chinese.riddle.nav.chineseRiddleSearchScreen
import com.hefengbao.jingmo.ui.screen.chinese.riddle.nav.navigateToChineseRiddleIndexGraph
import com.hefengbao.jingmo.ui.screen.chinese.riddle.nav.navigateToChineseRiddleInfoScreen
import com.hefengbao.jingmo.ui.screen.chinese.riddle.nav.navigateToChineseRiddleReadScreen
import com.hefengbao.jingmo.ui.screen.chinese.riddle.nav.navigateToChineseRiddleSearchScreen
import com.hefengbao.jingmo.ui.screen.chinese.tonguetwister.nav.chineseTongueTwisterIndexGraph
import com.hefengbao.jingmo.ui.screen.chinese.tonguetwister.nav.chineseTongueTwisterShowScreen
import com.hefengbao.jingmo.ui.screen.chinese.tonguetwister.nav.navigateToChineseTongueTwisterIndexGraph
import com.hefengbao.jingmo.ui.screen.chinese.tonguetwister.nav.navigateToChineseTongueTwisterShowScreen
import com.hefengbao.jingmo.ui.screen.chinese.wisecrack.nav.chineseWisecrackBookmarksScreen
import com.hefengbao.jingmo.ui.screen.chinese.wisecrack.nav.chineseWisecrackCaptureScreen
import com.hefengbao.jingmo.ui.screen.chinese.wisecrack.nav.chineseWisecrackIndexGraph
import com.hefengbao.jingmo.ui.screen.chinese.wisecrack.nav.chineseWisecrackReadScreen
import com.hefengbao.jingmo.ui.screen.chinese.wisecrack.nav.chineseWisecrackSearchScreen
import com.hefengbao.jingmo.ui.screen.chinese.wisecrack.nav.chineseWisecrackShowScreen
import com.hefengbao.jingmo.ui.screen.chinese.wisecrack.nav.navigateToChineseWisecrackBookmarksScreen
import com.hefengbao.jingmo.ui.screen.chinese.wisecrack.nav.navigateToChineseWisecrackCaptureScreen
import com.hefengbao.jingmo.ui.screen.chinese.wisecrack.nav.navigateToChineseWisecrackIndexGraph
import com.hefengbao.jingmo.ui.screen.chinese.wisecrack.nav.navigateToChineseWisecrackReadScreen
import com.hefengbao.jingmo.ui.screen.chinese.wisecrack.nav.navigateToChineseWisecrackSearchScreen
import com.hefengbao.jingmo.ui.screen.chinese.wisecrack.nav.navigateToChineseWisecrackShowScreen
import com.hefengbao.jingmo.ui.screen.classicalliterature.classicpoem.nav.classicalLiteratureClassicPoemBookmarksGraph
import com.hefengbao.jingmo.ui.screen.classicalliterature.classicpoem.nav.classicalLiteratureClassicPoemIndexGraph
import com.hefengbao.jingmo.ui.screen.classicalliterature.classicpoem.nav.classicalLiteratureClassicPoemReadScreen
import com.hefengbao.jingmo.ui.screen.classicalliterature.classicpoem.nav.classicalLiteratureClassicPoemSearchScreen
import com.hefengbao.jingmo.ui.screen.classicalliterature.classicpoem.nav.classicalLiteratureClassicPoemShowScreen
import com.hefengbao.jingmo.ui.screen.classicalliterature.classicpoem.nav.navigateToClassicalLiteratureClassicPoemBookmarksGraph
import com.hefengbao.jingmo.ui.screen.classicalliterature.classicpoem.nav.navigateToClassicalLiteratureClassicPoemIndexGraph
import com.hefengbao.jingmo.ui.screen.classicalliterature.classicpoem.nav.navigateToClassicalLiteratureClassicPoemReadScreen
import com.hefengbao.jingmo.ui.screen.classicalliterature.classicpoem.nav.navigateToClassicalLiteratureClassicPoemSearchScreen
import com.hefengbao.jingmo.ui.screen.classicalliterature.classicpoem.nav.navigateToClassicalLiteratureClassicPoemShowScreen
import com.hefengbao.jingmo.ui.screen.classicalliterature.people.nav.classicalLiteraturePeopleGraph
import com.hefengbao.jingmo.ui.screen.classicalliterature.people.nav.classicalLiteraturePeopleSearchGraph
import com.hefengbao.jingmo.ui.screen.classicalliterature.people.nav.classicalLiteraturePeopleShowScreen
import com.hefengbao.jingmo.ui.screen.classicalliterature.people.nav.navigateToClassicalLiteraturePeopleGraph
import com.hefengbao.jingmo.ui.screen.classicalliterature.people.nav.navigateToClassicalLiteraturePeopleSearchGraph
import com.hefengbao.jingmo.ui.screen.classicalliterature.people.nav.navigateToClassicalLiteraturePeopleShowScreen
import com.hefengbao.jingmo.ui.screen.classicalliterature.sentence.nav.classicalLiteratureSentenceBookmarksScreen
import com.hefengbao.jingmo.ui.screen.classicalliterature.sentence.nav.classicalLiteratureSentenceCaptureScreen
import com.hefengbao.jingmo.ui.screen.classicalliterature.sentence.nav.classicalLiteratureSentenceIndexGraph
import com.hefengbao.jingmo.ui.screen.classicalliterature.sentence.nav.classicalLiteratureSentenceReadScreen
import com.hefengbao.jingmo.ui.screen.classicalliterature.sentence.nav.classicalLiteratureSentenceSearchScreen
import com.hefengbao.jingmo.ui.screen.classicalliterature.sentence.nav.classicalLiteratureSentenceShowScreen
import com.hefengbao.jingmo.ui.screen.classicalliterature.sentence.nav.navigateToClassicalLiteratureSentenceBookmarksScreen
import com.hefengbao.jingmo.ui.screen.classicalliterature.sentence.nav.navigateToClassicalLiteratureSentenceCaptureScreen
import com.hefengbao.jingmo.ui.screen.classicalliterature.sentence.nav.navigateToClassicalLiteratureSentenceIndexGraph
import com.hefengbao.jingmo.ui.screen.classicalliterature.sentence.nav.navigateToClassicalLiteratureSentenceReadScreen
import com.hefengbao.jingmo.ui.screen.classicalliterature.sentence.nav.navigateToClassicalLiteratureSentenceSearchScreen
import com.hefengbao.jingmo.ui.screen.classicalliterature.sentence.nav.navigateToClassicalLiteratureSentenceShowScreen
import com.hefengbao.jingmo.ui.screen.classicalliterature.writing.nav.classicalLiteratureWritingBookmarksScreen
import com.hefengbao.jingmo.ui.screen.classicalliterature.writing.nav.classicalLiteratureWritingCaptureScreen
import com.hefengbao.jingmo.ui.screen.classicalliterature.writing.nav.classicalLiteratureWritingIndexGraph
import com.hefengbao.jingmo.ui.screen.classicalliterature.writing.nav.classicalLiteratureWritingReadScreen
import com.hefengbao.jingmo.ui.screen.classicalliterature.writing.nav.classicalLiteratureWritingSearchScreen
import com.hefengbao.jingmo.ui.screen.classicalliterature.writing.nav.classicalLiteratureWritingShowScreen
import com.hefengbao.jingmo.ui.screen.classicalliterature.writing.nav.navigateToClassicalLiteratureWritingBookmarksScreen
import com.hefengbao.jingmo.ui.screen.classicalliterature.writing.nav.navigateToClassicalLiteratureWritingCaptureScreen
import com.hefengbao.jingmo.ui.screen.classicalliterature.writing.nav.navigateToClassicalLiteratureWritingIndexGraph
import com.hefengbao.jingmo.ui.screen.classicalliterature.writing.nav.navigateToClassicalLiteratureWritingReadScreen
import com.hefengbao.jingmo.ui.screen.classicalliterature.writing.nav.navigateToClassicalLiteratureWritingSearchScreen
import com.hefengbao.jingmo.ui.screen.classicalliterature.writing.nav.navigateToClassicalLiteratureWritingShowScreen
import com.hefengbao.jingmo.ui.screen.home.nav.ROUTE_HOME_GRAPH
import com.hefengbao.jingmo.ui.screen.home.nav.homeGraph
import com.hefengbao.jingmo.ui.screen.link.nav.linkIndexScreen
import com.hefengbao.jingmo.ui.screen.link.nav.navigateToLinkIndexScreen
import com.hefengbao.jingmo.ui.screen.settings.nav.navigateToSettingsAboutScreen
import com.hefengbao.jingmo.ui.screen.settings.nav.navigateToSettingsCollectedUserInfoScreen
import com.hefengbao.jingmo.ui.screen.settings.nav.navigateToSettingsGraph
import com.hefengbao.jingmo.ui.screen.settings.nav.navigateToSettingsHomeItemManagerScreen
import com.hefengbao.jingmo.ui.screen.settings.nav.navigateToSettingsImportDataScreen
import com.hefengbao.jingmo.ui.screen.settings.nav.navigateToSettingsPrivacyScreen
import com.hefengbao.jingmo.ui.screen.settings.nav.navigateToSettingsSharedUserInfoScreen
import com.hefengbao.jingmo.ui.screen.settings.nav.navigateToSettingsSyncDataScreen
import com.hefengbao.jingmo.ui.screen.settings.nav.settingsAboutScreen
import com.hefengbao.jingmo.ui.screen.settings.nav.settingsCollectedUserInfoScreen
import com.hefengbao.jingmo.ui.screen.settings.nav.settingsGraph
import com.hefengbao.jingmo.ui.screen.settings.nav.settingsHomeItemManagerScreen
import com.hefengbao.jingmo.ui.screen.settings.nav.settingsImportDataScreen
import com.hefengbao.jingmo.ui.screen.settings.nav.settingsPrivacyScreen
import com.hefengbao.jingmo.ui.screen.settings.nav.settingsSharedUserInfoScreen
import com.hefengbao.jingmo.ui.screen.settings.nav.settingsSyncDataScreen
import com.hefengbao.jingmo.ui.screen.traditionalculture.calendar.nav.navigateToTraditionalCultureCalendarGraph
import com.hefengbao.jingmo.ui.screen.traditionalculture.calendar.nav.traditionalCultureCalendarGraph
import com.hefengbao.jingmo.ui.screen.traditionalculture.color.nav.navigateToTraditionalCultureColorIndexGraph
import com.hefengbao.jingmo.ui.screen.traditionalculture.color.nav.navigateToTraditionalCultureColorShowScreen
import com.hefengbao.jingmo.ui.screen.traditionalculture.color.nav.traditionalCultureColorIndexGraph
import com.hefengbao.jingmo.ui.screen.traditionalculture.color.nav.traditionalCultureColorShowScreen
import com.hefengbao.jingmo.ui.screen.traditionalculture.festival.nav.navigateToTraditionalCultureFestivalIndexGraph
import com.hefengbao.jingmo.ui.screen.traditionalculture.festival.nav.navigateToTraditionalCultureFestivalShowScreen
import com.hefengbao.jingmo.ui.screen.traditionalculture.festival.nav.traditionalCultureFestivalIndexGraph
import com.hefengbao.jingmo.ui.screen.traditionalculture.festival.nav.traditionalCultureFestivalShowScreen
import com.hefengbao.jingmo.ui.screen.traditionalculture.solarterm.nav.navigateToTraditionalCultureSolarTermIndexGraph
import com.hefengbao.jingmo.ui.screen.traditionalculture.solarterm.nav.navigateToTraditionalCultureSolarTermShowScreen
import com.hefengbao.jingmo.ui.screen.traditionalculture.solarterm.nav.traditionalCultureSolarTermIndexGraph
import com.hefengbao.jingmo.ui.screen.traditionalculture.solarterm.nav.traditionalCultureSolarTermShowScreen

@Composable
fun AppNavHost(
    navController: NavHostController
) {
    val animationTime = 500

    NavHost(
        navController = navController,
        startDestination = ROUTE_HOME_GRAPH,
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { it },
                animationSpec = tween(durationMillis = animationTime)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = tween(durationMillis = animationTime)
            )
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { it },
                animationSpec = tween(durationMillis = animationTime)
            )
        }
    ) {
        homeGraph(
            onBookmarkClick = navController::navigateToBookmarkIndexScreen,
            onChinaWorldCultureHeritageClick = navController::navigateToChinaWorldCultureHeritageGraph,
            onChineseAntitheticalCoupletClick = navController::navigateToChineseAntitheticalCoupletIndexGraph,
            onChineseCharacterClick = navController::navigateToChineseCharacterIndexGraph,
            onChineseExpressionClick = navController::navigateToChineseExpressionGraph,
            onChineseIdiomClick = navController::navigateToChineseIdiomIndexGraph,
            onChineseKnowledgeClick = navController::navigateToChineseKnowledgeIndexGraph,
            onChineseLyricClick = navController::navigateToChineseLyricIndexGraph,
            onChineseModernPoetryClick = navController::navigateToChineseModernPoetryIndexGraph,
            onChineseProverbClick = navController::navigateToChineseProverbIndexGraph,
            onChineseQuoteClick = navController::navigateToChineseQuoteIndexGraph,
            onChineseRiddleClick = navController::navigateToChineseRiddleIndexGraph,
            onChineseTongueTwisterClick = navController::navigateToChineseTongueTwisterIndexGraph,
            onChineseWisecrackClick = navController::navigateToChineseWisecrackIndexGraph,
            onClassicalLiteratureClassicPoemClick = navController::navigateToClassicalLiteratureClassicPoemIndexGraph,
            onClassicalLiteraturePeopleClick = navController::navigateToClassicalLiteraturePeopleGraph,
            onClassicalLiteratureSentenceClick = navController::navigateToClassicalLiteratureSentenceIndexGraph,
            onClassicalLiteratureWritingClick = navController::navigateToClassicalLiteratureWritingIndexGraph,
            onTraditionalCultureCalendarClick = navController::navigateToTraditionalCultureCalendarGraph,
            onTraditionalCultureColorClick = navController::navigateToTraditionalCultureColorIndexGraph,
            onTraditionalCultureFestivalClick = navController::navigateToTraditionalCultureFestivalIndexGraph,
            onTraditionalCultureSolarTermsClick = navController::navigateToTraditionalCultureSolarTermIndexGraph,
            onLinksClick = navController::navigateToLinkIndexScreen,
            onSettingsClick = navController::navigateToSettingsGraph,
            nestGraph = {
                bookmarkIndexScreen(
                    onBackClick = navController::navigateUp,
                    onChineseAntitheticalCoupletCardClick = navController::navigateToChineseAntitheticalCoupletShowScreen,
                    onChineseCharacterCardClick = navController::navigateToChineseCharacterShowScreen,
                    onChineseExpressionCardClick = navController::navigateToChineseExpressionShow,
                    onChineseIdiomCardClick = navController::navigateToChineseIdiomShowScreen,
                    onChineseKnowledgeCardClick = navController::navigateToChineseKnowledgeShowScreen,
                    onChineseLyricCardClick = navController::navigateToChineseLyricShowScreen,
                    onChineseModernPoetryCardClick = navController::navigateToChineseModernPoetryShowScreen,
                    onChineseProverbCardClick = navController::navigateToChineseProverbShowScreen,
                    onChineseQuoteCardClick = navController::navigateToChineseQuoteShowScreen,
                    onChineseWisecrackCardClick = navController::navigateToChineseWisecrackShowScreen,
                    onClassicalLiteratureClassicPoemCardClick = navController::navigateToClassicalLiteratureClassicPoemShowScreen,
                    onClassicalLiteratureSentenceCardClick = navController::navigateToClassicalLiteratureSentenceShowScreen,
                    onClassicalLiteratureWritingCardClick = navController::navigateToClassicalLiteratureWritingShowScreen
                )
                chinaWorldCultureHeritageGraph(
                    onBackClick = navController::navigateUp,
                    onItemClick = navController::navigateToChinaWorldCultureHeritageShowScreen
                ) {
                    chinaWorldCultureHeritageShowScreen(
                        onBackClick = navController::navigateUp
                    )
                }
                chineseAntitheticalCoupletIndexGraph(
                    onBackClick = navController::navigateUp,
                    onBookmarksClick = navController::navigateToChineseAntitheticalCoupletBookmarksScreen,
                    onCaptureClick = navController::navigateToChineseAntitheticalCoupletCaptureScreen,
                    onReadMoreClick = navController::navigateToChineseAntitheticalCoupletReadScreen,
                    onSearchClick = navController::navigateToChineseAntitheticalCoupletSearchScreen,
                    nestGraph = {
                        chineseAntitheticalCoupletBookmarksScreen(
                            onBackClick = navController::navigateUp,
                            onItemClick = navController::navigateToChineseAntitheticalCoupletShowScreen
                        )
                        chineseAntitheticalCoupletCaptureScreen(
                            onBackClick = navController::navigateUp
                        )
                        chineseAntitheticalCoupletReadScreen(
                            onBackClick = navController::navigateUp,
                            onCaptureClick = navController::navigateToChineseAntitheticalCoupletCaptureScreen
                        )
                        chineseAntitheticalCoupletShowScreen(
                            onBackClick = navController::navigateUp,
                            onCaptureClick = navController::navigateToChineseAntitheticalCoupletCaptureScreen
                        )
                        chineseAntitheticalCoupletSearchScreen(
                            onBackClick = navController::navigateUp,
                            onItemClick = navController::navigateToChineseAntitheticalCoupletShowScreen
                        )
                    }
                )
                chineseCharacterIndexGraph(
                    onBackClick = navController::navigateUp,
                    onBookmarksClick = navController::navigateToChineseCharacterBookmarksScreen,
                    onPinyinSearchClick = navController::navigateToChineseCharacterSyllableScreen,
                    onRadicalClickSearch = navController::navigateToChineseCharacterRadicalScreen,
                    onStrokeSearchClick = navController::navigateToChineseCharacterStrokeScreen,
                    onStrokeClick = navController::navigateToChineseCharacterStrokeShowScreen,
                    onSearchClick = navController::navigateToChineseCharacterSearchListScreen,
                    nestGraph = {
                        chineseCharacterBookmarksScreen(
                            onBackClick = navController::navigateUp,
                            onItemClick = navController::navigateToChineseCharacterShowScreen,
                        )
                        chineseCharacterSyllableScreen(
                            onBackClick = navController::navigateUp,
                            onItemClick = navController::navigateToChineseCharacterSearchListScreen,
                        )
                        chineseCharacterRadicalScreen(
                            onBackClick = navController::navigateUp,
                            onItemClick = navController::navigateToChineseCharacterSearchListScreen,
                        )
                        chineseCharacterStrokeScreen(
                            onBackClick = navController::navigateUp,
                            onItemClick = navController::navigateToChineseCharacterSearchListScreen,
                        )
                        chineseCharacterSearchListScreen(
                            onBackClick = navController::navigateUp,
                            onItemClick = navController::navigateToChineseCharacterShowScreen
                        )
                        chineseCharacterShowScreen(
                            onBackClick = navController::navigateUp,
                        )
                        chineseCharacterStrokeShowScreen(
                            onBackClick = navController::navigateUp
                        )
                    }
                )

                chineseExpressionGraph(
                    onBackClick = navController::navigateUp,
                    onBookmarksClick = navController::navigateToChineseExpressionBookmarksScreen,
                    onSearchClick = navController::navigateToChineseExpressionSearchScreen,
                    nestGraph = {
                        chineseExpressionBookmarksScreen(
                            onBackClick = navController::navigateUp,
                            onItemClick = navController::navigateToChineseExpressionShow
                        )
                        chineseExpressionSearchScreen(
                            onBackClick = navController::navigateUp,
                            onItemClick = navController::navigateToChineseExpressionShow
                        )
                        chineseExpressionShowScreen(
                            onBackClick = navController::navigateUp
                        )
                    }
                )
                chineseIdiomIndexGraph(
                    onBackClick = navController::navigateUp,
                    onBookmarksClick = navController::navigateToChineseIdiomBookmarksScreen,
                    onCaptureClick = navController::navigateToChineseIdiomCaptureScreen,
                    onReadMoreClick = navController::navigateToChineseIdiomReadScreen,
                    onSearchClick = navController::navigateToChineseIdiomSearchScreen,
                    nestGraph = {
                        chineseIdiomSearchScreen(
                            onBackClick = navController::navigateUp,
                            onItemClick = navController::navigateToChineseIdiomShowScreen,
                        )
                        chineseIdiomShowScreen(
                            onBackClick = navController::navigateUp,
                            onCaptureClick = navController::navigateToChineseIdiomCaptureScreen,
                        )
                        chineseIdiomReadScreen(
                            onBackClick = navController::navigateUp,
                            onCaptureClick = navController::navigateToChineseIdiomCaptureScreen
                        )
                        chineseIdiomCaptureScreen(
                            onBackClick = navController::navigateUp
                        )
                        chineseIdiomBookmarksScreen(
                            onBackClick = navController::navigateUp,
                            onItemClick = navController::navigateToChineseIdiomShowScreen
                        )
                    }
                )
                chineseKnowledgeIndexGraph(
                    onBackClick = navController::navigateUp,
                    onBookmarksClick = navController::navigateToChineseKnowledgeBookmarksScreen,
                    onReadMoreClick = navController::navigateToChineseKnowledgeReadScreen,
                    onSearchClick = navController::navigateToChineseKnowSearchScreen,
                    nestGraph = {
                        chineseKnowledgeBookmarksScreen(
                            onBackClick = navController::navigateUp,
                            onItemClick = navController::navigateToChineseKnowledgeShowScreen
                        )
                        chineseKnowledgeReadScreen(
                            onBackClick = navController::navigateUp,
                        )
                        chineseKnowledgeSearchScreen(
                            onBackClick = navController::navigateUp,
                            onItemClick = navController::navigateToChineseKnowledgeShowScreen
                        )
                        chineseKnowledgeShowScreen(
                            onBackClick = navController::navigateUp
                        )
                    }
                )
                chineseLyricIndexGraph(
                    onBackClick = navController::navigateUp,
                    onBookmarksClick = navController::navigateToChineseLyricBookmarksScreen,
                    onCaptureClick = navController::navigateToChineseLyricCaptureScreen,
                    onReadMoreClick = navController::navigateToChineseLyricReadScreen,
                    onSearchClick = navController::navigateToChineseLyricSearchScreen,
                    nestGraph = {
                        chineseLyricBookmarksScreen(
                            onBackClick = navController::navigateUp,
                            onItemClick = navController::navigateToChineseLyricShowScreen
                        )
                        chineseLyricCaptureScreen(
                            onBackClick = navController::navigateUp
                        )
                        chineseLyricReadScreen(
                            onBackClick = navController::navigateUp,
                            onCaptureClick = navController::navigateToChineseLyricCaptureScreen,
                        )
                        chineseLyricSearchScreen(
                            onBackClick = navController::navigateUp,
                            onItemClick = navController::navigateToChineseLyricShowScreen
                        )
                        chineseLyricShowScreen(
                            onBackClick = navController::navigateUp,
                            onCaptureClick = navController::navigateToChineseLyricCaptureScreen,
                        )
                    }
                )
                chineseModernPoetryIndexGraph(
                    onBackClick = navController::navigateUp,
                    onBookmarksClick = navController::navigateToChineseModernPoetryBookmarksScreen,
                    onCaptureClick = navController::navigateToChineseModernPoetryCaptureScreen,
                    onReadMoreClick = navController::navigateToChineseModernPoetryReadScreen,
                    onSearchClick = navController::navigateToChineseModernPoetrySearchScreen,
                    nestGraph = {
                        chineseModernPoetryBookmarksScreen(
                            onBackClick = navController::navigateUp,
                            onItemClick = navController::navigateToChineseModernPoetryShowScreen
                        )
                        chineseModernPoetryCaptureScreen(
                            onBackClick = navController::navigateUp
                        )
                        chineseModernPoetryReadScreen(
                            onBackClick = navController::navigateUp,
                            onCaptureClick = navController::navigateToChineseModernPoetryCaptureScreen,
                        )
                        chineseModernPoetrySearchScreen(
                            onBackClick = navController::navigateUp,
                            onItemClick = navController::navigateToChineseModernPoetryShowScreen
                        )
                        chineseModernPoetryShowScreen(
                            onBackClick = navController::navigateUp,
                            onCaptureClick = navController::navigateToChineseModernPoetryCaptureScreen,
                        )
                    }
                )
                chineseProverbIndexGraph(
                    onBackClick = navController::navigateUp,
                    onBookmarksClick = navController::navigateToChineseProverbBookmarksScreen,
                    onReadMoreClick = navController::navigateToChineseProverbReadScreen,
                    onSearchClick = navController::navigateToChineseProverbSearchScreen,
                    nestGraph = {
                        chineseProverbBookmarksScreen(
                            onBackClick = navController::navigateUp,
                            onItemClick = navController::navigateToChineseProverbShowScreen
                        )
                        chineseProverbReadScreen(
                            onBackClick = navController::navigateUp
                        )
                        chineseProverbSearchScreen(
                            onBackClick = navController::navigateUp,
                            onItemClick = navController::navigateToChineseProverbShowScreen
                        )
                        chineseProverbShowScreen(
                            onBackClick = navController::navigateUp
                        )
                    }
                )
                chineseQuoteIndexGraph(
                    onBackClick = navController::navigateUp,
                    onBookmarksClick = navController::navigateToChineseQuoteBookmarksScreen,
                    onCaptureClick = navController::navigateToChineseQuoteCaptureScreen,
                    onReadMoreClick = navController::navigateToChineseQuoteReadScreen,
                    onSearchClick = navController::navigateToChineseQuoteSearchScreen,
                    nestGraph = {
                        chineseQuoteBookmarksScreen(
                            onBackClick = navController::navigateUp,
                            onItemClick = navController::navigateToChineseQuoteShowScreen
                        )
                        chineseQuoteCaptureScreen(
                            onBackClick = navController::navigateUp
                        )
                        chineseQuoteReadScreen(
                            onBackClick = navController::navigateUp,
                            onCaptureClick = navController::navigateToChineseQuoteCaptureScreen,
                        )
                        chineseQuoteSearchScreen(
                            onBackClick = navController::navigateUp,
                            onItemClick = navController::navigateToChineseQuoteShowScreen
                        )
                        chineseQuoteShowScreen(
                            onBackClick = navController::navigateUp,
                            onCaptureClick = navController::navigateToChineseQuoteCaptureScreen,
                        )
                    }
                )
                chineseRiddleIndexGraph(
                    onBackClick = navController::navigateUp,
                    onInfoClick = navController::navigateToChineseRiddleInfoScreen,
                    onReadMoreClick = navController::navigateToChineseRiddleReadScreen,
                    onSearchClick = navController::navigateToChineseRiddleSearchScreen,
                    nestGraph = {
                        chineseRiddleInfoScreen(
                            onBackClick = navController::navigateUp
                        )
                        chineseRiddleReadScreen(
                            onBackClick = navController::navigateUp,
                            onInfoClick = navController::navigateToChineseRiddleInfoScreen,
                        )
                        chineseRiddleSearchScreen(
                            onBackClick = navController::navigateUp
                        )
                    }
                )
                chineseTongueTwisterIndexGraph(
                    onBackClick = navController::navigateUp,
                    onItemsClick = navController::navigateToChineseTongueTwisterShowScreen,
                    nestGraph = {
                        chineseTongueTwisterShowScreen(
                            onBackClick = navController::navigateUp
                        )
                    }
                )
                chineseWisecrackIndexGraph(
                    onBackClick = navController::navigateUp,
                    onBookmarksClick = navController::navigateToChineseWisecrackBookmarksScreen,
                    onCaptureClick = navController::navigateToChineseWisecrackCaptureScreen,
                    onReadMoreClick = navController::navigateToChineseWisecrackReadScreen,
                    onSearchClick = navController::navigateToChineseWisecrackSearchScreen,
                    nestGraph = {
                        chineseWisecrackCaptureScreen(
                            onBackClick = navController::navigateUp
                        )
                        chineseWisecrackReadScreen(
                            onBackClick = navController::navigateUp,
                            onCaptureClick = navController::navigateToChineseWisecrackCaptureScreen,
                        )
                        chineseWisecrackSearchScreen(
                            onBackClick = navController::navigateUp,
                            onItemClick = navController::navigateToChineseWisecrackShowScreen
                        )
                        chineseWisecrackShowScreen(
                            onBackClick = navController::navigateUp,
                            onCaptureClick = navController::navigateToChineseWisecrackCaptureScreen,
                        )
                        chineseWisecrackBookmarksScreen(
                            onBackClick = navController::navigateUp,
                            onCardClick = navController::navigateToChineseWisecrackShowScreen
                        )
                    }
                )
                classicalLiteratureClassicPoemIndexGraph(
                    onBackClick = navController::navigateUp,
                    onBookmarksClick = navController::navigateToClassicalLiteratureClassicPoemBookmarksGraph,
                    onReadMoreClick = navController::navigateToClassicalLiteratureClassicPoemReadScreen,
                    onSearchClick = navController::navigateToClassicalLiteratureClassicPoemSearchScreen,
                    nestGraph = {
                        classicalLiteratureClassicPoemBookmarksGraph(
                            onBackClick = navController::navigateUp,
                            onReadClick = navController::navigateToClassicalLiteratureClassicPoemShowScreen,
                            nestGraph = {
                                classicalLiteratureClassicPoemShowScreen(
                                    onBackClick = navController::navigateUp
                                )
                            }
                        )
                        classicalLiteratureClassicPoemReadScreen(
                            onBackClick = navController::navigateUp
                        )
                        classicalLiteratureClassicPoemSearchScreen(
                            onBackClick = navController::navigateUp,
                            onItemClick = navController::navigateToClassicalLiteratureClassicPoemShowScreen,
                        )
                    }
                )
                classicalLiteratureSentenceIndexGraph(
                    onBackClick = navController::navigateUp,
                    onBookmarksClick = navController::navigateToClassicalLiteratureSentenceBookmarksScreen,
                    onCaptureClick = navController::navigateToClassicalLiteratureSentenceCaptureScreen,
                    onReadMoreClick = navController::navigateToClassicalLiteratureSentenceReadScreen,
                    onSearchClick = navController::navigateToClassicalLiteratureSentenceSearchScreen,
                    nestGraph = {
                        classicalLiteratureSentenceBookmarksScreen(
                            onBackClick = navController::navigateUp,
                            onCardClick = navController::navigateToClassicalLiteratureSentenceShowScreen
                        )
                        classicalLiteratureSentenceCaptureScreen(
                            onBackClick = navController::navigateUp
                        )
                        classicalLiteratureSentenceReadScreen(
                            onBackClick = navController::navigateUp,
                            onCaptureClick = navController::navigateToClassicalLiteratureSentenceCaptureScreen
                        )
                        classicalLiteratureSentenceSearchScreen(
                            onBackClick = navController::navigateUp,
                            onCaptureClick = navController::navigateToClassicalLiteratureSentenceCaptureScreen,
                        )
                        classicalLiteratureSentenceShowScreen(
                            onBackClick = navController::navigateUp,
                            onCaptureClick = navController::navigateToClassicalLiteratureSentenceCaptureScreen,
                        )
                    }
                )
                classicalLiteraturePeopleGraph(
                    onBackClick = navController::navigateUp,
                    onSearchClick = navController::navigateToClassicalLiteraturePeopleSearchGraph,
                    nestGraph = {
                        classicalLiteraturePeopleSearchGraph(
                            onBackClick = navController::navigateUp,
                            onItemClick = navController::navigateToClassicalLiteraturePeopleShowScreen,
                            nestGraph = {
                                classicalLiteraturePeopleShowScreen(
                                    onBackClick = navController::navigateUp,
                                )
                            }
                        )
                    }
                )
                classicalLiteratureWritingIndexGraph(
                    onBackClick = navController::navigateUp,
                    onBookmarksClick = navController::navigateToClassicalLiteratureWritingBookmarksScreen,
                    onCaptureClick = navController::navigateToClassicalLiteratureWritingCaptureScreen,
                    onSearchClick = navController::navigateToClassicalLiteratureWritingSearchScreen,
                    onReadMoreClick = navController::navigateToClassicalLiteratureWritingReadScreen,
                    nestGraph = {
                        classicalLiteratureWritingBookmarksScreen(
                            onBackClick = navController::navigateUp,
                            onItemClick = navController::navigateToClassicalLiteratureWritingShowScreen
                        )
                        classicalLiteratureWritingSearchScreen(
                            onBackClick = navController::navigateUp,
                            onItemClick = navController::navigateToClassicalLiteratureWritingShowScreen,
                        )
                        classicalLiteratureWritingShowScreen(
                            onBackClick = navController::navigateUp,
                            onCaptureClick = navController::navigateToClassicalLiteratureWritingCaptureScreen
                        )
                        classicalLiteratureWritingReadScreen(
                            onBackClick = navController::navigateUp,
                            onCaptureClick = navController::navigateToClassicalLiteratureWritingCaptureScreen
                        )
                        classicalLiteratureWritingCaptureScreen(
                            onBackClick = navController::navigateUp
                        )
                    }
                )
                traditionalCultureCalendarGraph(
                    onBackClick = navController::navigateUp,
                    nestGraph = {}
                )
                traditionalCultureColorIndexGraph(
                    onBackClick = navController::navigateUp,
                    onItemClick = navController::navigateToTraditionalCultureColorShowScreen,
                    nestGraph = {
                        traditionalCultureColorShowScreen(
                            onBackClick = navController::navigateUp
                        )
                    }
                )
                traditionalCultureFestivalIndexGraph(
                    onBackClick = navController::navigateUp,
                    onItemClick = navController::navigateToTraditionalCultureFestivalShowScreen,
                    nestGraph = {
                        traditionalCultureFestivalShowScreen(
                            onBackClick = navController::navigateUp
                        )
                    }
                )
                traditionalCultureSolarTermIndexGraph(
                    onBackClick = navController::navigateUp,
                    onItemClick = navController::navigateToTraditionalCultureSolarTermShowScreen,
                    nestGraph = {
                        traditionalCultureSolarTermShowScreen(
                            onBackClick = navController::navigateUp
                        )
                    }
                )
                linkIndexScreen(
                    onBackClick = navController::navigateUp
                )
                settingsGraph(
                    onAboutClick = navController::navigateToSettingsAboutScreen,
                    onBackClick = navController::navigateUp,
                    onCollectedUserInfoClick = navController::navigateToSettingsCollectedUserInfoScreen,
                    onHomeItemManagerClick = navController::navigateToSettingsHomeItemManagerScreen,
                    onImportDataClick = navController::navigateToSettingsImportDataScreen,
                    onPrivacyClick = navController::navigateToSettingsPrivacyScreen,
                    onSharedUserInfoClick = navController::navigateToSettingsSharedUserInfoScreen,
                    onSyncDataClick = navController::navigateToSettingsSyncDataScreen,
                    nestGraph = {
                        settingsAboutScreen(
                            onBackClick = navController::navigateUp
                        )
                        settingsCollectedUserInfoScreen(navController::navigateUp)
                        settingsHomeItemManagerScreen(
                            onBackClick = navController::navigateUp
                        )
                        settingsImportDataScreen(
                            onBackClick = navController::navigateUp
                        )
                        settingsPrivacyScreen(
                            onBackClick = navController::navigateUp
                        )
                        settingsSharedUserInfoScreen(navController::navigateUp)
                        settingsSyncDataScreen(
                            onBackClick = navController::navigateUp
                        )
                    }
                )
            }
        )
    }
}