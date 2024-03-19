package com.hefengbao.jingmo.route

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.hefengbao.jingmo.ui.screen.chinesecolor.nav.chineseColorIndexGraph
import com.hefengbao.jingmo.ui.screen.chinesecolor.nav.chineseColorShowScreen
import com.hefengbao.jingmo.ui.screen.chinesecolor.nav.navigateToChineseColorIndexGraph
import com.hefengbao.jingmo.ui.screen.chinesecolor.nav.navigateToChineseColorShowScreen
import com.hefengbao.jingmo.ui.screen.chineseknowledge.nav.chineseKnowledgeIndexGraph
import com.hefengbao.jingmo.ui.screen.chineseknowledge.nav.chineseKnowledgeSearchScreen
import com.hefengbao.jingmo.ui.screen.chineseknowledge.nav.navigateToChineseKnowSearchScreen
import com.hefengbao.jingmo.ui.screen.chineseknowledge.nav.navigateToChineseKnowledgeIndexGraph
import com.hefengbao.jingmo.ui.screen.chinesewisecrack.nav.chineseWisecrackBookmarksScreen
import com.hefengbao.jingmo.ui.screen.chinesewisecrack.nav.chineseWisecrackCaptureScreen
import com.hefengbao.jingmo.ui.screen.chinesewisecrack.nav.chineseWisecrackIndexGraph
import com.hefengbao.jingmo.ui.screen.chinesewisecrack.nav.chineseWisecrackSearchScreen
import com.hefengbao.jingmo.ui.screen.chinesewisecrack.nav.chineseWisecrackSearchShowScreen
import com.hefengbao.jingmo.ui.screen.chinesewisecrack.nav.navigateToChineseWisecrackBookmarksScreen
import com.hefengbao.jingmo.ui.screen.chinesewisecrack.nav.navigateToChineseWisecrackCaptureScreen
import com.hefengbao.jingmo.ui.screen.chinesewisecrack.nav.navigateToChineseWisecrackIndexGraph
import com.hefengbao.jingmo.ui.screen.chinesewisecrack.nav.navigateToChineseWisecrackSearchScreen
import com.hefengbao.jingmo.ui.screen.chinesewisecrack.nav.navigateToChineseWisecrackSearchShowScreen
import com.hefengbao.jingmo.ui.screen.festival.nav.festivalIndexGraph
import com.hefengbao.jingmo.ui.screen.festival.nav.festivalShowScreen
import com.hefengbao.jingmo.ui.screen.festival.nav.navigateToFestivalIndexGraph
import com.hefengbao.jingmo.ui.screen.festival.nav.navigateToFestivalShowScreen
import com.hefengbao.jingmo.ui.screen.home.nav.ROUTE_HOME_GRAPH
import com.hefengbao.jingmo.ui.screen.home.nav.homeGraph
import com.hefengbao.jingmo.ui.screen.idiom.nav.idiomBookmarksReadScreen
import com.hefengbao.jingmo.ui.screen.idiom.nav.idiomBookmarksScreen
import com.hefengbao.jingmo.ui.screen.idiom.nav.idiomCaptureScreen
import com.hefengbao.jingmo.ui.screen.idiom.nav.idiomIndexGraph
import com.hefengbao.jingmo.ui.screen.idiom.nav.idiomReadScreen
import com.hefengbao.jingmo.ui.screen.idiom.nav.idiomShowScreen
import com.hefengbao.jingmo.ui.screen.idiom.nav.navigateToIdiomBookmarksReadScreen
import com.hefengbao.jingmo.ui.screen.idiom.nav.navigateToIdiomBookmarksScreen
import com.hefengbao.jingmo.ui.screen.idiom.nav.navigateToIdiomCaptureScreen
import com.hefengbao.jingmo.ui.screen.idiom.nav.navigateToIdiomIndexGraph
import com.hefengbao.jingmo.ui.screen.idiom.nav.navigateToIdiomReadScreen
import com.hefengbao.jingmo.ui.screen.idiom.nav.navigateToIdiomShowScreen
import com.hefengbao.jingmo.ui.screen.link.nav.linkIndexScreen
import com.hefengbao.jingmo.ui.screen.link.nav.navigateToLinkIndexScreen
import com.hefengbao.jingmo.ui.screen.people.nav.navigateToPeopleGraph
import com.hefengbao.jingmo.ui.screen.people.nav.navigateToPeopleShowScreen
import com.hefengbao.jingmo.ui.screen.people.nav.peopleGraph
import com.hefengbao.jingmo.ui.screen.people.nav.peopleShowScreen
import com.hefengbao.jingmo.ui.screen.poem.nav.navigateToPoemBookmarksReadScreen
import com.hefengbao.jingmo.ui.screen.poem.nav.navigateToPoemBookmarksScreen
import com.hefengbao.jingmo.ui.screen.poem.nav.navigateToPoemCaptureScreen
import com.hefengbao.jingmo.ui.screen.poem.nav.navigateToPoemIndexGraph
import com.hefengbao.jingmo.ui.screen.poem.nav.navigateToPoemReadScreen
import com.hefengbao.jingmo.ui.screen.poem.nav.navigateToPoemSearchScreen
import com.hefengbao.jingmo.ui.screen.poem.nav.navigateToPoemSearchShowScreen
import com.hefengbao.jingmo.ui.screen.poem.nav.poemBookmarksReadScreen
import com.hefengbao.jingmo.ui.screen.poem.nav.poemBookmarksScreen
import com.hefengbao.jingmo.ui.screen.poem.nav.poemCaptureScreen
import com.hefengbao.jingmo.ui.screen.poem.nav.poemIndexGraph
import com.hefengbao.jingmo.ui.screen.poem.nav.poemReadScreen
import com.hefengbao.jingmo.ui.screen.poem.nav.poemSearchScreen
import com.hefengbao.jingmo.ui.screen.poem.nav.poemSearchShowScreen
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
import com.hefengbao.jingmo.ui.screen.settings.nav.importScreen
import com.hefengbao.jingmo.ui.screen.settings.nav.navigateToAboutScreen
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

@Composable
fun AppNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = ROUTE_HOME_GRAPH
    ) {
        homeGraph(
            onLinksClick = { navController.navigateToLinkIndexScreen() },
            onSettingsClick = { navController.navigateToSettingsGraph() },
            onPoemClick = { navController.navigateToPoemIndexGraph() },
            onPoemSentenceClick = { navController.navigateToPoemSentenceIndexGraph() },
            onChineseWisecrackClick = { navController.navigateToChineseWisecrackIndexGraph() },
            onIdiomClick = { navController.navigateToIdiomIndexGraph() },
            onChineseColorClick = { navController.navigateToChineseColorIndexGraph() },
            onFestivalClick = { navController.navigateToFestivalIndexGraph() },
            onSolarTermsClick = { navController.navigateToSolarTermIndexGraph() },
            onTongueTwisterClick = { navController.navigateToTongueTwisterIndexGraph() },
            onChineseKnowledgeClick = { navController.navigateToChineseKnowledgeIndexGraph() },
            onRiddleClick = { navController.navigateToRiddleIndexGraph() },
            onPeopleClick = { navController.navigateToPeopleGraph() },
            nestGraph = {
                poemIndexGraph(
                    onBackClick = navController::navigateUp,
                    onSearchClick = {
                        // search 补位用，不产生任何作用
                        navController.navigateToPoemSearchScreen("search", "search")
                    },
                    onAuthorClick = { navController.navigateToPoemSearchScreen("author", it) },
                    onBookmarksClick = { navController.navigateToPoemBookmarksScreen() },
                    onReadMoreClick = {
                        navController.navigateToPoemReadScreen()
                    },
                    nestGraph = {
                        poemBookmarksScreen(
                            onBackClick = navController::navigateUp,
                            onItemClick = {
                                navController.navigateToPoemBookmarksReadScreen(it.toString())
                            }
                        )
                        poemBookmarksReadScreen(
                            onBackClick = navController::navigateUp,
                            onCaptureClick = { navController.navigateToPoemCaptureScreen(it.toString()) }
                        )
                        poemSearchScreen(
                            onBackClick = navController::navigateUp,
                            onItemClick = { id: String, type: String, query: String ->
                                navController.navigateToPoemSearchShowScreen(id, type, query)
                            },
                        )
                        poemSearchShowScreen(
                            onBackClick = navController::navigateUp,
                            onCaptureClick = { navController.navigateToPoemCaptureScreen(it.toString()) }
                        )
                        poemReadScreen(
                            onBackClick = navController::navigateUp,
                            onCaptureClick = { navController.navigateToPoemCaptureScreen(it.toString()) },
                        )
                        poemCaptureScreen(
                            onBackClick = navController::navigateUp
                        )
                    }
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
                            onItemClick = { id: Int, query: String ->
                                navController.navigateToChineseWisecrackSearchShowScreen(
                                    id.toString(),
                                    query
                                )
                            }
                        )
                        chineseWisecrackSearchShowScreen(
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
                idiomIndexGraph(
                    onBackClick = navController::navigateUp,
                    onItemClick = { navController.navigateToIdiomShowScreen(it.toString()) },
                    onReadMoreClick = { navController.navigateToIdiomReadScreen() },
                    onBookmarksClick = { navController.navigateToIdiomBookmarksScreen() },
                    nestGraph = {
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
                            onItemClick = { navController.navigateToIdiomBookmarksReadScreen(it.toString()) }
                        )
                        idiomBookmarksReadScreen(
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
                festivalIndexGraph(
                    onBackClick = navController::navigateUp,
                    onItemClick = { navController.navigateToFestivalShowScreen(it.toString()) },
                    nestGraph = {
                        festivalShowScreen(
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
                chineseKnowledgeIndexGraph(
                    onBackClick = navController::navigateUp,
                    onSearchClick = { navController.navigateToChineseKnowSearchScreen() },
                    nestGraph = {
                        chineseKnowledgeSearchScreen(
                            onBackClick = navController::navigateUp
                        )
                    }
                )
                linkIndexScreen(
                    onBackClick = navController::navigateUp
                )
                settingsGraph(
                    onBackClick = navController::navigateUp,
                    onAboutClick = { navController.navigateToAboutScreen() },
                    onPrivacyClick = { navController.navigateToPrivacyScreen() },
                    onDataClick = { navController.navigateToSettingsDataScreen() },
                    onImportClick = { navController.navigateToImportScreen() },
                    nestGraph = {
                        aboutScreen(
                            onBackClick = navController::navigateUp
                        )
                        privacyScreen(
                            onBackClick = navController::navigateUp
                        )
                        settingsDataScreen(
                            onBackClick = navController::navigateUp
                        )
                        importScreen(
                            onBackClick = navController::navigateUp
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
                peopleGraph(
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
    }
}