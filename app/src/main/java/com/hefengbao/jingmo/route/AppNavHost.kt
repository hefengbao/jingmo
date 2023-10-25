package com.hefengbao.jingmo.route

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.hefengbao.jingmo.ui.screen.chinesecolor.nav.chineseColorListGraph
import com.hefengbao.jingmo.ui.screen.chinesecolor.nav.chineseColorScreen
import com.hefengbao.jingmo.ui.screen.chinesecolor.nav.navigateToChineseColorListGraph
import com.hefengbao.jingmo.ui.screen.chinesecolor.nav.navigateToChineseColorScreen
import com.hefengbao.jingmo.ui.screen.chinesewisecrack.nav.chineseWisecrackCaptureScreen
import com.hefengbao.jingmo.ui.screen.chinesewisecrack.nav.chineseWisecrackGraph
import com.hefengbao.jingmo.ui.screen.chinesewisecrack.nav.navigateToChineseWisecrackCaptureScreen
import com.hefengbao.jingmo.ui.screen.chinesewisecrack.nav.navigateToChineseWisecrackGraph
import com.hefengbao.jingmo.ui.screen.home.nav.ROUTE_HOME_GRAPH
import com.hefengbao.jingmo.ui.screen.home.nav.homeGraph
import com.hefengbao.jingmo.ui.screen.idiom.nav.idiomCaptureScreen
import com.hefengbao.jingmo.ui.screen.idiom.nav.idiomGraph
import com.hefengbao.jingmo.ui.screen.idiom.nav.idiomListGraph
import com.hefengbao.jingmo.ui.screen.idiom.nav.navigateToIdiomCaptureScreen
import com.hefengbao.jingmo.ui.screen.idiom.nav.navigateToIdiomGraph
import com.hefengbao.jingmo.ui.screen.idiom.nav.navigateToIdiomListGraph
import com.hefengbao.jingmo.ui.screen.poem.nav.navigateToPoemCaptureScreen
import com.hefengbao.jingmo.ui.screen.poem.nav.navigateToPoemGraph
import com.hefengbao.jingmo.ui.screen.poem.nav.navigateToPoemListGraph
import com.hefengbao.jingmo.ui.screen.poem.nav.poemCaptureScreen
import com.hefengbao.jingmo.ui.screen.poem.nav.poemGraph
import com.hefengbao.jingmo.ui.screen.poem.nav.poemListGraph
import com.hefengbao.jingmo.ui.screen.poemsentence.nav.navigateToPoemSentenceCaptureScreen
import com.hefengbao.jingmo.ui.screen.poemsentence.nav.navigateToPoemSentenceGraph
import com.hefengbao.jingmo.ui.screen.poemsentence.nav.poemSentenceCaptureScreen
import com.hefengbao.jingmo.ui.screen.poemsentence.nav.poemSentenceGraph
import com.hefengbao.jingmo.ui.screen.settings.nav.aboutScreen
import com.hefengbao.jingmo.ui.screen.settings.nav.navigateToAboutScreen
import com.hefengbao.jingmo.ui.screen.settings.nav.navigateToPrivacyScreen
import com.hefengbao.jingmo.ui.screen.settings.nav.navigateToSettingsGraph
import com.hefengbao.jingmo.ui.screen.settings.nav.privacyScreen
import com.hefengbao.jingmo.ui.screen.settings.nav.settingsGraph

@Composable
fun AppNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = ROUTE_HOME_GRAPH
    ) {
        homeGraph(
            onSettingsClick = { navController.navigateToSettingsGraph() },
            onPoemClick = { navController.navigateToPoemListGraph() },
            onPoemSentenceClick = { navController.navigateToPoemSentenceGraph() },
            onChineseWisecrackClick = { navController.navigateToChineseWisecrackGraph() },
            onIdiomClick = { navController.navigateToIdiomListGraph() },
            onChineseColorClick = { navController.navigateToChineseColorListGraph() },
            nestGraph = {
                poemListGraph(
                    onBackClick = navController::navigateUp,
                    onItemClick = { navController.navigateToPoemGraph(it.toString()) },
                    nestGraph = {
                        poemGraph(
                            onBackClick = navController::navigateUp,
                            onCaptureClick = { navController.navigateToPoemCaptureScreen(it.toString()) },
                            nestGraph = {
                                poemCaptureScreen(
                                    onBackClick = navController::navigateUp
                                )
                            }
                        )
                    }
                )
                poemSentenceGraph(
                    onBackClick = navController::navigateUp,
                    onCaptureClick = { navController.navigateToPoemSentenceCaptureScreen(it.toString()) },
                    nestGraph = {
                        poemSentenceCaptureScreen(
                            onBackClick = navController::navigateUp
                        )
                    }
                )
                chineseWisecrackGraph(
                    onBackClick = navController::navigateUp,
                    onCaptureClick = { navController.navigateToChineseWisecrackCaptureScreen(it.toString()) },
                    nestGraph = {
                        chineseWisecrackCaptureScreen(
                            onBackClick = navController::navigateUp
                        )
                    }
                )
                idiomListGraph(
                    onBackClick = navController::navigateUp,
                    onItemClick = { navController.navigateToIdiomGraph(it.toString()) },
                    nestGraph = {
                        idiomGraph(
                            onBackClick = navController::navigateUp,
                            onCaptureClick = { navController.navigateToIdiomCaptureScreen(it.toString()) },
                            nestGraph = {
                                idiomCaptureScreen(
                                    onBackClick = navController::navigateUp
                                )
                            }
                        )
                    }
                )
                chineseColorListGraph(
                    onBackClick = navController::navigateUp,
                    onItemClick = { navController.navigateToChineseColorScreen(it) },
                    nestGraph = {
                        chineseColorScreen(
                            onBackClick = navController::navigateUp
                        )
                    }
                )
                settingsGraph(
                    onBackClick = navController::navigateUp,
                    onAboutClick = { navController.navigateToAboutScreen() },
                    onPrivacyClick = { navController.navigateToPrivacyScreen() },
                    nestGraph = {
                        aboutScreen(
                            onBackClick = navController::navigateUp
                        )
                        privacyScreen(
                            onBackClick = navController::navigateUp
                        )
                    }
                )
            }
        )
    }
}