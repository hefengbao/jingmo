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
import com.hefengbao.jingmo.ui.screen.chinesecharacter.nav.chineseCharacterIndexGraph
import com.hefengbao.jingmo.ui.screen.chinesecharacter.nav.chineseCharacterPinyinIndexScreen
import com.hefengbao.jingmo.ui.screen.chinesecharacter.nav.chineseCharacterRadicalIndexScreen
import com.hefengbao.jingmo.ui.screen.chinesecharacter.nav.chineseCharacterSearchListScreen
import com.hefengbao.jingmo.ui.screen.chinesecharacter.nav.chineseCharacterShowScreen
import com.hefengbao.jingmo.ui.screen.chinesecharacter.nav.chineseCharacterStrokeIndexScreen
import com.hefengbao.jingmo.ui.screen.chinesecharacter.nav.chineseCharacterStrokeScreen
import com.hefengbao.jingmo.ui.screen.chinesecharacter.nav.navigateToChineseCharacterIndexGraph
import com.hefengbao.jingmo.ui.screen.chinesecharacter.nav.navigateToChineseCharacterPinyinIndexScreen
import com.hefengbao.jingmo.ui.screen.chinesecharacter.nav.navigateToChineseCharacterRadicalIndexScreen
import com.hefengbao.jingmo.ui.screen.chinesecharacter.nav.navigateToChineseCharacterSearchListScreen
import com.hefengbao.jingmo.ui.screen.chinesecharacter.nav.navigateToChineseCharacterShowScreen
import com.hefengbao.jingmo.ui.screen.chinesecharacter.nav.navigateToChineseCharacterStrokeIndexScreen
import com.hefengbao.jingmo.ui.screen.chinesecharacter.nav.navigateToChineseCharacterStrokeScreen
import com.hefengbao.jingmo.ui.screen.chinesecolor.nav.chineseColorIndexGraph
import com.hefengbao.jingmo.ui.screen.chinesecolor.nav.chineseColorShowScreen
import com.hefengbao.jingmo.ui.screen.chinesecolor.nav.navigateToChineseColorIndexGraph
import com.hefengbao.jingmo.ui.screen.chinesecolor.nav.navigateToChineseColorShowScreen
import com.hefengbao.jingmo.ui.screen.chineseexpression.nav.chineseExpressionGraph
import com.hefengbao.jingmo.ui.screen.chineseexpression.nav.chineseExpressionSearchScreen
import com.hefengbao.jingmo.ui.screen.chineseexpression.nav.chineseExpressionShowScreen
import com.hefengbao.jingmo.ui.screen.chineseexpression.nav.navigateToChineseExpressionGraph
import com.hefengbao.jingmo.ui.screen.chineseexpression.nav.navigateToChineseExpressionSearchScreen
import com.hefengbao.jingmo.ui.screen.chineseexpression.nav.navigateToChineseExpressionShow
import com.hefengbao.jingmo.ui.screen.chineseknowledge.nav.chineseKnowledgeIndexGraph
import com.hefengbao.jingmo.ui.screen.chineseknowledge.nav.chineseKnowledgeSearchScreen
import com.hefengbao.jingmo.ui.screen.chineseknowledge.nav.navigateToChineseKnowSearchScreen
import com.hefengbao.jingmo.ui.screen.chineseknowledge.nav.navigateToChineseKnowledgeIndexGraph
import com.hefengbao.jingmo.ui.screen.chinesewisecrack.nav.chineseWisecrackBookmarksScreen
import com.hefengbao.jingmo.ui.screen.chinesewisecrack.nav.chineseWisecrackCaptureScreen
import com.hefengbao.jingmo.ui.screen.chinesewisecrack.nav.chineseWisecrackIndexGraph
import com.hefengbao.jingmo.ui.screen.chinesewisecrack.nav.chineseWisecrackSearchScreen
import com.hefengbao.jingmo.ui.screen.chinesewisecrack.nav.chineseWisecrackShowScreen
import com.hefengbao.jingmo.ui.screen.chinesewisecrack.nav.navigateToChineseWisecrackBookmarksScreen
import com.hefengbao.jingmo.ui.screen.chinesewisecrack.nav.navigateToChineseWisecrackCaptureScreen
import com.hefengbao.jingmo.ui.screen.chinesewisecrack.nav.navigateToChineseWisecrackIndexGraph
import com.hefengbao.jingmo.ui.screen.chinesewisecrack.nav.navigateToChineseWisecrackSearchScreen
import com.hefengbao.jingmo.ui.screen.chinesewisecrack.nav.navigateToChineseWisecrackShowScreen
import com.hefengbao.jingmo.ui.screen.classicpoem.nav.classicPoemBookmarksGraph
import com.hefengbao.jingmo.ui.screen.classicpoem.nav.classicPoemIndexGraph
import com.hefengbao.jingmo.ui.screen.classicpoem.nav.classicPoemReadScreen
import com.hefengbao.jingmo.ui.screen.classicpoem.nav.classicPoemSearchScreen
import com.hefengbao.jingmo.ui.screen.classicpoem.nav.classicPoemShowScreen
import com.hefengbao.jingmo.ui.screen.classicpoem.nav.navigateToClassicPoemBookmarksGraph
import com.hefengbao.jingmo.ui.screen.classicpoem.nav.navigateToClassicPoemIndexGraph
import com.hefengbao.jingmo.ui.screen.classicpoem.nav.navigateToClassicPoemReadScreen
import com.hefengbao.jingmo.ui.screen.classicpoem.nav.navigateToClassicPoemSearchScreen
import com.hefengbao.jingmo.ui.screen.classicpoem.nav.navigateToClassicPoemShowScreen
import com.hefengbao.jingmo.ui.screen.festival.nav.festivalIndexGraph
import com.hefengbao.jingmo.ui.screen.festival.nav.festivalShowScreen
import com.hefengbao.jingmo.ui.screen.festival.nav.navigateToFestivalIndexGraph
import com.hefengbao.jingmo.ui.screen.festival.nav.navigateToFestivalShowScreen
import com.hefengbao.jingmo.ui.screen.home.nav.ROUTE_HOME_GRAPH
import com.hefengbao.jingmo.ui.screen.home.nav.homeGraph
import com.hefengbao.jingmo.ui.screen.idiom.nav.idiomBookmarksScreen
import com.hefengbao.jingmo.ui.screen.idiom.nav.idiomCaptureScreen
import com.hefengbao.jingmo.ui.screen.idiom.nav.idiomIndexGraph
import com.hefengbao.jingmo.ui.screen.idiom.nav.idiomReadScreen
import com.hefengbao.jingmo.ui.screen.idiom.nav.idiomSearchScreen
import com.hefengbao.jingmo.ui.screen.idiom.nav.idiomShowScreen
import com.hefengbao.jingmo.ui.screen.idiom.nav.navigateToIdiomBookmarksScreen
import com.hefengbao.jingmo.ui.screen.idiom.nav.navigateToIdiomCaptureScreen
import com.hefengbao.jingmo.ui.screen.idiom.nav.navigateToIdiomIndexGraph
import com.hefengbao.jingmo.ui.screen.idiom.nav.navigateToIdiomReadScreen
import com.hefengbao.jingmo.ui.screen.idiom.nav.navigateToIdiomSearchScreen
import com.hefengbao.jingmo.ui.screen.idiom.nav.navigateToIdiomShowScreen
import com.hefengbao.jingmo.ui.screen.link.nav.linkIndexScreen
import com.hefengbao.jingmo.ui.screen.link.nav.navigateToLinkIndexScreen
import com.hefengbao.jingmo.ui.screen.people.nav.navigateToPeopleGraph
import com.hefengbao.jingmo.ui.screen.people.nav.navigateToPeopleSearchGraph
import com.hefengbao.jingmo.ui.screen.people.nav.navigateToPeopleShowScreen
import com.hefengbao.jingmo.ui.screen.people.nav.peopleGraph
import com.hefengbao.jingmo.ui.screen.people.nav.peopleSearchGraph
import com.hefengbao.jingmo.ui.screen.people.nav.peopleShowScreen
import com.hefengbao.jingmo.ui.screen.poemsentence.nav.navigateToPoemSentenceBookmarksScreen
import com.hefengbao.jingmo.ui.screen.poemsentence.nav.navigateToPoemSentenceCaptureScreen
import com.hefengbao.jingmo.ui.screen.poemsentence.nav.navigateToPoemSentenceIndexGraph
import com.hefengbao.jingmo.ui.screen.poemsentence.nav.navigateToPoemSentenceSearchScreen
import com.hefengbao.jingmo.ui.screen.poemsentence.nav.poemSentenceBookmarksScreen
import com.hefengbao.jingmo.ui.screen.poemsentence.nav.poemSentenceCaptureScreen
import com.hefengbao.jingmo.ui.screen.poemsentence.nav.poemSentenceIndexGraph
import com.hefengbao.jingmo.ui.screen.poemsentence.nav.poemSentenceSearchScreen
import com.hefengbao.jingmo.ui.screen.riddle.nav.navigateToRiddleIndexGraph
import com.hefengbao.jingmo.ui.screen.riddle.nav.navigateToRiddleInfoScreen
import com.hefengbao.jingmo.ui.screen.riddle.nav.navigateToRiddleSearchScreen
import com.hefengbao.jingmo.ui.screen.riddle.nav.riddleIndexGraph
import com.hefengbao.jingmo.ui.screen.riddle.nav.riddleInfoScreen
import com.hefengbao.jingmo.ui.screen.riddle.nav.riddleSearchScreen
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
import com.hefengbao.jingmo.ui.screen.solarterm.nav.navigateToSolarTermIndexGraph
import com.hefengbao.jingmo.ui.screen.solarterm.nav.navigateToSolarTermShowScreen
import com.hefengbao.jingmo.ui.screen.solarterm.nav.solarTermIndexGraph
import com.hefengbao.jingmo.ui.screen.solarterm.nav.solarTermShowScreen
import com.hefengbao.jingmo.ui.screen.tonguetwister.nav.navigateToTongueTwisterIndexGraph
import com.hefengbao.jingmo.ui.screen.tonguetwister.nav.navigateToTongueTwisterShowScreen
import com.hefengbao.jingmo.ui.screen.tonguetwister.nav.tongueTwisterIndexGraph
import com.hefengbao.jingmo.ui.screen.tonguetwister.nav.tongueTwisterShowScreen
import com.hefengbao.jingmo.ui.screen.writing.nav.navigateToWritingBookmarksScreen
import com.hefengbao.jingmo.ui.screen.writing.nav.navigateToWritingCaptureScreen
import com.hefengbao.jingmo.ui.screen.writing.nav.navigateToWritingIndexGraph
import com.hefengbao.jingmo.ui.screen.writing.nav.navigateToWritingReadScreen
import com.hefengbao.jingmo.ui.screen.writing.nav.navigateToWritingSearchScreen
import com.hefengbao.jingmo.ui.screen.writing.nav.navigateToWritingShowScreen
import com.hefengbao.jingmo.ui.screen.writing.nav.writingBookmarksScreen
import com.hefengbao.jingmo.ui.screen.writing.nav.writingCaptureScreen
import com.hefengbao.jingmo.ui.screen.writing.nav.writingIndexGraph
import com.hefengbao.jingmo.ui.screen.writing.nav.writingReadScreen
import com.hefengbao.jingmo.ui.screen.writing.nav.writingSearchScreen
import com.hefengbao.jingmo.ui.screen.writing.nav.writingShowScreen

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
            onChineseColorClick = { navController.navigateToChineseColorIndexGraph() },
            onChineseExpressionClick = { navController.navigateToChineseExpressionGraph() },
            onChineseKnowledgeClick = { navController.navigateToChineseKnowledgeIndexGraph() },
            onChineseWisecrackClick = { navController.navigateToChineseWisecrackIndexGraph() },
            onClassicPoemClick = { navController.navigateToClassicPoemIndexGraph() },
            onFestivalClick = { navController.navigateToFestivalIndexGraph() },
            onIdiomClick = { navController.navigateToIdiomIndexGraph() },
            onLinksClick = { navController.navigateToLinkIndexScreen() },
            onPeopleClick = { navController.navigateToPeopleGraph() },
            onPoemSentenceClick = { navController.navigateToPoemSentenceIndexGraph() },
            onRiddleClick = { navController.navigateToRiddleIndexGraph() },
            onSettingsClick = { navController.navigateToSettingsGraph() },
            onSolarTermsClick = { navController.navigateToSolarTermIndexGraph() },
            onTongueTwisterClick = { navController.navigateToTongueTwisterIndexGraph() },
            onWritingClick = { navController.navigateToWritingIndexGraph() },
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
                chineseColorIndexGraph(
                    onBackClick = navController::navigateUp,
                    onItemClick = { navController.navigateToChineseColorShowScreen(it) },
                    nestGraph = {
                        chineseColorShowScreen(
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
                chineseKnowledgeIndexGraph(
                    onBackClick = navController::navigateUp,
                    onSearchClick = { navController.navigateToChineseKnowSearchScreen() },
                    nestGraph = {
                        chineseKnowledgeSearchScreen(
                            onBackClick = navController::navigateUp
                        )
                    }
                )
                chineseWisecrackIndexGraph(
                    onBackClick = navController::navigateUp,
                    onCaptureClick = { navController.navigateToChineseWisecrackCaptureScreen(it.toString()) },
                    onSearchClick = { navController.navigateToChineseWisecrackSearchScreen() },
                    onBookmarksClick = { navController.navigateToChineseWisecrackBookmarksScreen() },
                    nestGraph = {
                        chineseWisecrackCaptureScreen(
                            onBackClick = navController::navigateUp
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
                classicPoemIndexGraph(
                    onBackClick = navController::navigateUp,
                    onBookmarksClick = { navController.navigateToClassicPoemBookmarksGraph() },
                    onReadMoreClick = { navController.navigateToClassicPoemReadScreen() },
                    onSearchClick = { navController.navigateToClassicPoemSearchScreen() },
                    nestGraph = {
                        classicPoemBookmarksGraph(
                            onBackClick = navController::navigateUp,
                            onReadClick = {
                                navController.navigateToClassicPoemShowScreen(it)
                            },
                            nestGraph = {
                                classicPoemShowScreen(
                                    onBackClick = navController::navigateUp
                                )
                            }
                        )
                        classicPoemReadScreen(
                            onBackClick = navController::navigateUp
                        )
                        classicPoemSearchScreen(
                            onBackClick = navController::navigateUp,
                            onItemClick = {
                                navController.navigateToClassicPoemShowScreen(it)
                            },
                        )
                    }
                )
                festivalIndexGraph(
                    onBackClick = navController::navigateUp,
                    onItemClick = { navController.navigateToFestivalShowScreen(it.toString()) },
                    nestGraph = {
                        festivalShowScreen(
                            onBackClick = navController::navigateUp
                        )
                    }
                )
                idiomIndexGraph(
                    onBackClick = navController::navigateUp,
                    onReadMoreClick = { navController.navigateToIdiomReadScreen() },
                    onBookmarksClick = { navController.navigateToIdiomBookmarksScreen() },
                    onSearchClick = { navController.navigateToIdiomSearchScreen() },
                    nestGraph = {
                        idiomSearchScreen(
                            onBackClick = navController::navigateUp,
                            onItemClick = { navController.navigateToIdiomShowScreen(it.toString()) },
                        )
                        idiomShowScreen(
                            onBackClick = navController::navigateUp,
                            onCaptureClick = { navController.navigateToIdiomCaptureScreen(it.toString()) },
                        )
                        idiomReadScreen(
                            onBackClick = navController::navigateUp,
                            onCaptureClick = { navController.navigateToIdiomCaptureScreen(it.toString()) }
                        )
                        idiomCaptureScreen(
                            onBackClick = navController::navigateUp
                        )
                        idiomBookmarksScreen(
                            onBackClick = navController::navigateUp,
                            onItemClick = { navController.navigateToIdiomShowScreen(it.toString()) }
                        )
                    }
                )
                linkIndexScreen(
                    onBackClick = navController::navigateUp
                )
                poemSentenceIndexGraph(
                    onBackClick = navController::navigateUp,
                    onCaptureClick = { navController.navigateToPoemSentenceCaptureScreen(it.toString()) },
                    onSearchClick = { navController.navigateToPoemSentenceSearchScreen() },
                    onBookmarksClick = { navController.navigateToPoemSentenceBookmarksScreen() },
                    nestGraph = {
                        poemSentenceCaptureScreen(
                            onBackClick = navController::navigateUp
                        )
                        poemSentenceSearchScreen(
                            onBackClick = navController::navigateUp,
                            onCaptureClick = { navController.navigateToPoemSentenceCaptureScreen(it.toString()) },
                        )
                        poemSentenceBookmarksScreen(
                            onBackClick = navController::navigateUp
                        )
                    }
                )
                peopleGraph(
                    onBackClick = navController::navigateUp,
                    onSearchClick = { navController.navigateToPeopleSearchGraph() },
                    nestGraph = {
                        peopleSearchGraph(
                            onBackClick = navController::navigateUp,
                            onItemClick = { type: String, query: String ->
                                navController.navigateToPeopleShowScreen(
                                    type,
                                    query
                                )
                            },
                            nestGraph = {
                                peopleShowScreen(
                                    onBackClick = navController::navigateUp,
                                )
                            }
                        )
                    }
                )
                riddleIndexGraph(
                    onBackClick = navController::navigateUp,
                    onInfoClick = { navController.navigateToRiddleInfoScreen() },
                    onSearchClick = { navController.navigateToRiddleSearchScreen() },
                    nestGraph = {
                        riddleInfoScreen(
                            onBackClick = navController::navigateUp
                        )
                        riddleSearchScreen(
                            onBackClick = navController::navigateUp
                        )
                    }
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
                solarTermIndexGraph(
                    onBackClick = navController::navigateUp,
                    onItemClick = { navController.navigateToSolarTermShowScreen(it.toString()) },
                    nestGraph = {
                        solarTermShowScreen(
                            onBackClick = navController::navigateUp
                        )
                    }
                )
                tongueTwisterIndexGraph(
                    onBackClick = navController::navigateUp,
                    onItemsClick = { navController.navigateToTongueTwisterShowScreen(it.toString()) },
                    nestGraph = {
                        tongueTwisterShowScreen(
                            onBackClick = navController::navigateUp
                        )
                    }
                )
                writingIndexGraph(
                    onBackClick = navController::navigateUp,
                    onSearchClick = {
                        navController.navigateToWritingSearchScreen()
                    },
                    onBookmarksClick = { navController.navigateToWritingBookmarksScreen() },
                    onReadMoreClick = {
                        navController.navigateToWritingReadScreen()
                    },
                    nestGraph = {
                        writingBookmarksScreen(
                            onBackClick = navController::navigateUp,
                            onItemClick = {
                                navController.navigateToWritingShowScreen(it.toString())
                            }
                        )
                        writingSearchScreen(
                            onBackClick = navController::navigateUp,
                            onItemClick = {
                                navController.navigateToWritingShowScreen(it)
                            },
                        )
                        writingShowScreen(
                            onBackClick = navController::navigateUp,
                            onCaptureClick = { navController.navigateToWritingCaptureScreen(it.toString()) }
                        )
                        writingReadScreen(
                            onBackClick = navController::navigateUp,
                            onCaptureClick = { navController.navigateToWritingCaptureScreen(it.toString()) },
                        )
                        writingCaptureScreen(
                            onBackClick = navController::navigateUp
                        )
                    }
                )
            }
        )
    }
}