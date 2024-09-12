/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.route

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.hefengbao.jingmo.ui.screen.chinese.character.nav.chineseCharacterIndexGraph
import com.hefengbao.jingmo.ui.screen.chinese.character.nav.chineseCharacterPinyinIndexScreen
import com.hefengbao.jingmo.ui.screen.chinese.character.nav.chineseCharacterRadicalIndexScreen
import com.hefengbao.jingmo.ui.screen.chinese.character.nav.chineseCharacterSearchListScreen
import com.hefengbao.jingmo.ui.screen.chinese.character.nav.chineseCharacterShowScreen
import com.hefengbao.jingmo.ui.screen.chinese.character.nav.chineseCharacterStrokeIndexScreen
import com.hefengbao.jingmo.ui.screen.chinese.character.nav.chineseCharacterStrokeScreen
import com.hefengbao.jingmo.ui.screen.chinese.character.nav.navigateToChineseCharacterIndexGraph
import com.hefengbao.jingmo.ui.screen.chinese.character.nav.navigateToChineseCharacterPinyinIndexScreen
import com.hefengbao.jingmo.ui.screen.chinese.character.nav.navigateToChineseCharacterRadicalIndexScreen
import com.hefengbao.jingmo.ui.screen.chinese.character.nav.navigateToChineseCharacterSearchListScreen
import com.hefengbao.jingmo.ui.screen.chinese.character.nav.navigateToChineseCharacterShowScreen
import com.hefengbao.jingmo.ui.screen.chinese.character.nav.navigateToChineseCharacterStrokeIndexScreen
import com.hefengbao.jingmo.ui.screen.chinese.character.nav.navigateToChineseCharacterStrokeScreen
import com.hefengbao.jingmo.ui.screen.chinese.expression.nav.chineseExpressionGraph
import com.hefengbao.jingmo.ui.screen.chinese.expression.nav.chineseExpressionSearchScreen
import com.hefengbao.jingmo.ui.screen.chinese.expression.nav.chineseExpressionShowScreen
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
import com.hefengbao.jingmo.ui.screen.chinese.lyric.nav.chineseLyricIndexGraph
import com.hefengbao.jingmo.ui.screen.chinese.lyric.nav.chineseLyricReadScreen
import com.hefengbao.jingmo.ui.screen.chinese.lyric.nav.chineseLyricSearchScreen
import com.hefengbao.jingmo.ui.screen.chinese.lyric.nav.chineseLyricShowScreen
import com.hefengbao.jingmo.ui.screen.chinese.lyric.nav.navigateToChineseLyricBookmarksScreen
import com.hefengbao.jingmo.ui.screen.chinese.lyric.nav.navigateToChineseLyricIndexGraph
import com.hefengbao.jingmo.ui.screen.chinese.lyric.nav.navigateToChineseLyricReadScreen
import com.hefengbao.jingmo.ui.screen.chinese.lyric.nav.navigateToChineseLyricSearchScreen
import com.hefengbao.jingmo.ui.screen.chinese.lyric.nav.navigateToChineseLyricShowScreen
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
import com.hefengbao.jingmo.ui.screen.chinese.riddle.nav.chineseRiddleIndexGraph
import com.hefengbao.jingmo.ui.screen.chinese.riddle.nav.chineseRiddleInfoScreen
import com.hefengbao.jingmo.ui.screen.chinese.riddle.nav.chineseRiddleSearchScreen
import com.hefengbao.jingmo.ui.screen.chinese.riddle.nav.navigateToChineseRiddleIndexGraph
import com.hefengbao.jingmo.ui.screen.chinese.riddle.nav.navigateToChineseRiddleInfoScreen
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
import com.hefengbao.jingmo.ui.screen.classicalliterature.sentence.nav.navigateToClassicalLiteratureSentenceBookmarksScreen
import com.hefengbao.jingmo.ui.screen.classicalliterature.sentence.nav.navigateToClassicalLiteratureSentenceCaptureScreen
import com.hefengbao.jingmo.ui.screen.classicalliterature.sentence.nav.navigateToClassicalLiteratureSentenceIndexGraph
import com.hefengbao.jingmo.ui.screen.classicalliterature.sentence.nav.navigateToClassicalLiteratureSentenceReadScreen
import com.hefengbao.jingmo.ui.screen.classicalliterature.sentence.nav.navigateToClassicalLiteratureSentenceSearchScreen
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
import com.hefengbao.jingmo.ui.screen.settings.nav.aboutScreen
import com.hefengbao.jingmo.ui.screen.settings.nav.homeItemManagerScreen
import com.hefengbao.jingmo.ui.screen.settings.nav.importScreen
import com.hefengbao.jingmo.ui.screen.settings.nav.navigateToAboutScreen
import com.hefengbao.jingmo.ui.screen.settings.nav.navigateToHomeItemManagerScreen
import com.hefengbao.jingmo.ui.screen.settings.nav.navigateToImportScreen
import com.hefengbao.jingmo.ui.screen.settings.nav.navigateToPrivacyScreen
import com.hefengbao.jingmo.ui.screen.settings.nav.navigateToSettingsDataScreen
import com.hefengbao.jingmo.ui.screen.settings.nav.navigateToSettingsGraph
import com.hefengbao.jingmo.ui.screen.settings.nav.privacyScreen
import com.hefengbao.jingmo.ui.screen.settings.nav.settingsDataScreen
import com.hefengbao.jingmo.ui.screen.settings.nav.settingsGraph
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
    NavHost(
        navController = navController,
        startDestination = ROUTE_HOME_GRAPH
    ) {
        homeGraph(
            onChineseCharacterClick = { navController.navigateToChineseCharacterIndexGraph() },
            onChineseExpressionClick = { navController.navigateToChineseExpressionGraph() },
            onChineseIdiomClick = { navController.navigateToChineseIdiomIndexGraph() },
            onChineseKnowledgeClick = { navController.navigateToChineseKnowledgeIndexGraph() },
            onChineseLyricClick = { navController.navigateToChineseLyricIndexGraph() },
            onChineseProverbClick = { navController.navigateToChineseProverbIndexGraph() },
            onChineseRiddleClick = { navController.navigateToChineseRiddleIndexGraph() },
            onChineseTongueTwisterClick = { navController.navigateToChineseTongueTwisterIndexGraph() },
            onChineseWisecrackClick = { navController.navigateToChineseWisecrackIndexGraph() },
            onClassicalLiteratureClassicPoemClick = { navController.navigateToClassicalLiteratureClassicPoemIndexGraph() },
            onClassicalLiteraturePeopleClick = { navController.navigateToClassicalLiteraturePeopleGraph() },
            onClassicalLiteratureSentenceClick = { navController.navigateToClassicalLiteratureSentenceIndexGraph() },
            onClassicalLiteratureWritingClick = { navController.navigateToClassicalLiteratureWritingIndexGraph() },
            onTraditionalCultureColorClick = { navController.navigateToTraditionalCultureColorIndexGraph() },
            onTraditionalCultureFestivalClick = { navController.navigateToTraditionalCultureFestivalIndexGraph() },
            onTraditionalCultureSolarTermsClick = { navController.navigateToTraditionalCultureSolarTermIndexGraph() },
            onLinksClick = { navController.navigateToLinkIndexScreen() },
            onSettingsClick = { navController.navigateToSettingsGraph() },
            nestGraph = {
                chineseCharacterIndexGraph(
                    onBackClick = navController::navigateUp,
                    onPinyinSearchClick = { navController.navigateToChineseCharacterPinyinIndexScreen() },
                    onRadicalClickSearch = { navController.navigateToChineseCharacterRadicalIndexScreen() },
                    onStrokeSearchClick = { navController.navigateToChineseCharacterStrokeIndexScreen() },
                    onStrokeClick = { navController.navigateToChineseCharacterStrokeScreen() },
                    onSearchClick = { char, type ->
                        navController.navigateToChineseCharacterSearchListScreen(
                            char,
                            type
                        )
                    },
                    nestGraph = {
                        chineseCharacterPinyinIndexScreen(
                            onBackClick = navController::navigateUp,
                            onItemClick = { pinyin, type ->
                                navController.navigateToChineseCharacterSearchListScreen(
                                    pinyin,
                                    type
                                )
                            },
                        )
                        chineseCharacterRadicalIndexScreen(
                            onBackClick = navController::navigateUp,
                            onItemClick = { radical, type ->
                                navController.navigateToChineseCharacterSearchListScreen(
                                    radical,
                                    type
                                )
                            },
                        )
                        chineseCharacterStrokeIndexScreen(
                            onBackClick = navController::navigateUp,
                            onItemClick = { stroke, type ->
                                navController.navigateToChineseCharacterSearchListScreen(
                                    stroke,
                                    type
                                )
                            },
                        )
                        chineseCharacterSearchListScreen(
                            onBackClick = navController::navigateUp,
                            onItemClick = { navController.navigateToChineseCharacterShowScreen(it) }
                        )
                        chineseCharacterShowScreen(
                            onBackClick = navController::navigateUp,
                        )
                        chineseCharacterStrokeScreen(
                            onBackClick = navController::navigateUp
                        )
                    }
                )

                chineseExpressionGraph(
                    onBackClick = navController::navigateUp,
                    onSearchClick = { navController.navigateToChineseExpressionSearchScreen() },
                    nestGraph = {
                        chineseExpressionSearchScreen(
                            onBackClick = navController::navigateUp,
                            onItemClick = { navController.navigateToChineseExpressionShow(it) }
                        )
                        chineseExpressionShowScreen(
                            onBackClick = navController::navigateUp
                        )
                    }
                )
                chineseIdiomIndexGraph(
                    onBackClick = navController::navigateUp,
                    onReadMoreClick = { navController.navigateToChineseIdiomReadScreen() },
                    onBookmarksClick = { navController.navigateToChineseIdiomBookmarksScreen() },
                    onSearchClick = { navController.navigateToChineseIdiomSearchScreen() },
                    nestGraph = {
                        chineseIdiomSearchScreen(
                            onBackClick = navController::navigateUp,
                            onItemClick = { navController.navigateToChineseIdiomShowScreen(it.toString()) },
                        )
                        chineseIdiomShowScreen(
                            onBackClick = navController::navigateUp,
                            onCaptureClick = { navController.navigateToChineseIdiomCaptureScreen(it.toString()) },
                        )
                        chineseIdiomReadScreen(
                            onBackClick = navController::navigateUp,
                            onCaptureClick = { navController.navigateToChineseIdiomCaptureScreen(it.toString()) }
                        )
                        chineseIdiomCaptureScreen(
                            onBackClick = navController::navigateUp
                        )
                        chineseIdiomBookmarksScreen(
                            onBackClick = navController::navigateUp,
                            onItemClick = { navController.navigateToChineseIdiomShowScreen(it.toString()) }
                        )
                    }
                )
                chineseKnowledgeIndexGraph(
                    onBackClick = navController::navigateUp,
                    onBookmarksClick = { navController.navigateToChineseKnowledgeBookmarksScreen() },
                    onReadMoreClick = { navController.navigateToChineseKnowledgeReadScreen() },
                    onSearchClick = { navController.navigateToChineseKnowSearchScreen() },
                    nestGraph = {
                        chineseKnowledgeBookmarksScreen(
                            onBackClick = navController::navigateUp,
                            onItemClick = { navController.navigateToChineseKnowledgeShowScreen(it.toString()) }
                        )
                        chineseKnowledgeReadScreen(
                            onBackClick = navController::navigateUp,
                        )
                        chineseKnowledgeSearchScreen(
                            onBackClick = navController::navigateUp,
                            onItemClick = { navController.navigateToChineseKnowledgeShowScreen(it.toString()) }
                        )
                        chineseKnowledgeShowScreen(
                            onBackClick = navController::navigateUp
                        )
                    }
                )
                chineseLyricIndexGraph(
                    onBackClick = navController::navigateUp,
                    onBookmarksClick = { navController.navigateToChineseLyricBookmarksScreen() },
                    onReadMoreClick = { navController.navigateToChineseLyricReadScreen() },
                    onSearchClick = { navController.navigateToChineseLyricSearchScreen() },
                    nestGraph = {
                        chineseLyricBookmarksScreen(
                            onBackClick = navController::navigateUp,
                            onItemClick = { navController.navigateToChineseLyricShowScreen(it.toString()) }
                        )
                        chineseLyricReadScreen(
                            onBackClick = navController::navigateUp,
                        )
                        chineseLyricSearchScreen(
                            onBackClick = navController::navigateUp,
                            onItemClick = { navController.navigateToChineseLyricShowScreen(it.toString()) }
                        )
                        chineseLyricShowScreen(
                            onBackClick = navController::navigateUp
                        )
                    }
                )
                chineseProverbIndexGraph(
                    onBackClick = navController::navigateUp,
                    onBookmarksClick = { navController.navigateToChineseProverbBookmarksScreen() },
                    onReadMoreClick = { navController.navigateToChineseProverbReadScreen() },
                    onSearchClick = { navController.navigateToChineseProverbSearchScreen() },
                    nestGraph = {
                        chineseProverbBookmarksScreen(
                            onBackClick = navController::navigateUp,
                            onItemClick = { navController.navigateToChineseProverbShowScreen(it.toString()) }
                        )
                        chineseProverbReadScreen(
                            onBackClick = navController::navigateUp
                        )
                        chineseProverbSearchScreen(
                            onBackClick = navController::navigateUp,
                            onItemClick = { navController.navigateToChineseProverbShowScreen(it.toString()) }
                        )
                        chineseProverbShowScreen(
                            onBackClick = navController::navigateUp
                        )
                    }
                )
                chineseRiddleIndexGraph(
                    onBackClick = navController::navigateUp,
                    onInfoClick = { navController.navigateToChineseRiddleInfoScreen() },
                    onSearchClick = { navController.navigateToChineseRiddleSearchScreen() },
                    nestGraph = {
                        chineseRiddleInfoScreen(
                            onBackClick = navController::navigateUp
                        )
                        chineseRiddleSearchScreen(
                            onBackClick = navController::navigateUp
                        )
                    }
                )
                chineseTongueTwisterIndexGraph(
                    onBackClick = navController::navigateUp,
                    onItemsClick = { navController.navigateToChineseTongueTwisterShowScreen(it.toString()) },
                    nestGraph = {
                        chineseTongueTwisterShowScreen(
                            onBackClick = navController::navigateUp
                        )
                    }
                )
                chineseWisecrackIndexGraph(
                    onBackClick = navController::navigateUp,
                    onBookmarksClick = { navController.navigateToChineseWisecrackBookmarksScreen() },
                    onCaptureClick = { navController.navigateToChineseWisecrackCaptureScreen(it.toString()) },
                    onReadMoreClick = { navController.navigateToChineseWisecrackReadScreen() },
                    onSearchClick = { navController.navigateToChineseWisecrackSearchScreen() },
                    nestGraph = {
                        chineseWisecrackCaptureScreen(
                            onBackClick = navController::navigateUp
                        )
                        chineseWisecrackReadScreen(
                            onBackClick = navController::navigateUp,
                            onCaptureClick = {
                                navController.navigateToChineseWisecrackCaptureScreen(
                                    it.toString()
                                )
                            },
                        )
                        chineseWisecrackSearchScreen(
                            onBackClick = navController::navigateUp,
                            onItemClick = {
                                navController.navigateToChineseWisecrackShowScreen(it.toString())
                            }
                        )
                        chineseWisecrackShowScreen(
                            onBackClick = navController::navigateUp,
                            onCaptureClick = {
                                navController.navigateToChineseWisecrackCaptureScreen(
                                    it.toString()
                                )
                            },
                        )
                        chineseWisecrackBookmarksScreen(
                            onBackClick = navController::navigateUp
                        )
                    }
                )
                classicalLiteratureClassicPoemIndexGraph(
                    onBackClick = navController::navigateUp,
                    onBookmarksClick = { navController.navigateToClassicalLiteratureClassicPoemBookmarksGraph() },
                    onReadMoreClick = { navController.navigateToClassicalLiteratureClassicPoemReadScreen() },
                    onSearchClick = { navController.navigateToClassicalLiteratureClassicPoemSearchScreen() },
                    nestGraph = {
                        classicalLiteratureClassicPoemBookmarksGraph(
                            onBackClick = navController::navigateUp,
                            onReadClick = {
                                navController.navigateToClassicalLiteratureClassicPoemShowScreen(it)
                            },
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
                            onItemClick = {
                                navController.navigateToClassicalLiteratureClassicPoemShowScreen(it)
                            },
                        )
                    }
                )
                classicalLiteratureSentenceIndexGraph(
                    onBackClick = navController::navigateUp,
                    onBookmarksClick = { navController.navigateToClassicalLiteratureSentenceBookmarksScreen() },
                    onCaptureClick = {
                        navController.navigateToClassicalLiteratureSentenceCaptureScreen(
                            it.toString()
                        )
                    },
                    onReadMoreClick = { navController.navigateToClassicalLiteratureSentenceReadScreen() },
                    onSearchClick = { navController.navigateToClassicalLiteratureSentenceSearchScreen() },
                    nestGraph = {
                        classicalLiteratureSentenceBookmarksScreen(
                            onBackClick = navController::navigateUp
                        )
                        classicalLiteratureSentenceCaptureScreen(
                            onBackClick = navController::navigateUp
                        )
                        classicalLiteratureSentenceReadScreen(
                            onBackClick = navController::navigateUp,
                            onCaptureClick = {
                                navController.navigateToClassicalLiteratureSentenceCaptureScreen(
                                    it.toString()
                                )
                            }
                        )
                        classicalLiteratureSentenceSearchScreen(
                            onBackClick = navController::navigateUp,
                            onCaptureClick = {
                                navController.navigateToClassicalLiteratureSentenceCaptureScreen(
                                    it.toString()
                                )
                            },
                        )
                    }
                )
                classicalLiteraturePeopleGraph(
                    onBackClick = navController::navigateUp,
                    onSearchClick = { navController.navigateToClassicalLiteraturePeopleSearchGraph() },
                    nestGraph = {
                        classicalLiteraturePeopleSearchGraph(
                            onBackClick = navController::navigateUp,
                            onItemClick = { type: String, query: String ->
                                navController.navigateToClassicalLiteraturePeopleShowScreen(
                                    type,
                                    query
                                )
                            },
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
                    onSearchClick = {
                        navController.navigateToClassicalLiteratureWritingSearchScreen()
                    },
                    onBookmarksClick = { navController.navigateToClassicalLiteratureWritingBookmarksScreen() },
                    onReadMoreClick = {
                        navController.navigateToClassicalLiteratureWritingReadScreen()
                    },
                    nestGraph = {
                        classicalLiteratureWritingBookmarksScreen(
                            onBackClick = navController::navigateUp,
                            onItemClick = {
                                navController.navigateToClassicalLiteratureWritingShowScreen(it.toString())
                            }
                        )
                        classicalLiteratureWritingSearchScreen(
                            onBackClick = navController::navigateUp,
                            onItemClick = {
                                navController.navigateToClassicalLiteratureWritingShowScreen(it)
                            },
                        )
                        classicalLiteratureWritingShowScreen(
                            onBackClick = navController::navigateUp,
                            onCaptureClick = {
                                navController.navigateToClassicalLiteratureWritingCaptureScreen(
                                    it.toString()
                                )
                            }
                        )
                        classicalLiteratureWritingReadScreen(
                            onBackClick = navController::navigateUp,
                            onCaptureClick = {
                                navController.navigateToClassicalLiteratureWritingCaptureScreen(
                                    it.toString()
                                )
                            },
                        )
                        classicalLiteratureWritingCaptureScreen(
                            onBackClick = navController::navigateUp
                        )
                    }
                )
                traditionalCultureColorIndexGraph(
                    onBackClick = navController::navigateUp,
                    onItemClick = { navController.navigateToTraditionalCultureColorShowScreen(it) },
                    nestGraph = {
                        traditionalCultureColorShowScreen(
                            onBackClick = navController::navigateUp
                        )
                    }
                )
                traditionalCultureFestivalIndexGraph(
                    onBackClick = navController::navigateUp,
                    onItemClick = { navController.navigateToTraditionalCultureFestivalShowScreen(it.toString()) },
                    nestGraph = {
                        traditionalCultureFestivalShowScreen(
                            onBackClick = navController::navigateUp
                        )
                    }
                )
                traditionalCultureSolarTermIndexGraph(
                    onBackClick = navController::navigateUp,
                    onItemClick = { navController.navigateToTraditionalCultureSolarTermShowScreen(it.toString()) },
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
                    onAboutClick = { navController.navigateToAboutScreen() },
                    onBackClick = navController::navigateUp,
                    onDataClick = { navController.navigateToSettingsDataScreen() },
                    onHomeItemManagerClick = { navController.navigateToHomeItemManagerScreen() },
                    onImportClick = { navController.navigateToImportScreen() },
                    onPrivacyClick = { navController.navigateToPrivacyScreen() },
                    nestGraph = {
                        aboutScreen(
                            onBackClick = navController::navigateUp
                        )
                        homeItemManagerScreen(
                            onBackClick = navController::navigateUp
                        )
                        importScreen(
                            onBackClick = navController::navigateUp
                        )
                        privacyScreen(
                            onBackClick = navController::navigateUp
                        )
                        settingsDataScreen(
                            onBackClick = navController::navigateUp
                        )
                    }
                )
            }
        )
    }
}