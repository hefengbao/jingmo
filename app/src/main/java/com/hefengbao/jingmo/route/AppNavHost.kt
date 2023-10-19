package com.hefengbao.jingmo.route

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.hefengbao.jingmo.ui.screen.chinesecolor.nav.chineseColorListGraph
import com.hefengbao.jingmo.ui.screen.chinesecolor.nav.chineseColorScreen
import com.hefengbao.jingmo.ui.screen.chinesecolor.nav.navigateToChineseColorListGraph
import com.hefengbao.jingmo.ui.screen.chinesecolor.nav.navigateToChineseColorScreen
import com.hefengbao.jingmo.ui.screen.chinesewisecrack.nav.chineseWisecrackGraph
import com.hefengbao.jingmo.ui.screen.chinesewisecrack.nav.navigateToChineseWisecrackGraph
import com.hefengbao.jingmo.ui.screen.home.nav.ROUTE_HOME_GRAPH
import com.hefengbao.jingmo.ui.screen.home.nav.homeGraph
import com.hefengbao.jingmo.ui.screen.idiom.nav.idiomListGraph
import com.hefengbao.jingmo.ui.screen.idiom.nav.idiomScreen
import com.hefengbao.jingmo.ui.screen.idiom.nav.navigateToIdiomListGraph
import com.hefengbao.jingmo.ui.screen.idiom.nav.navigateToIdiomScreen
import com.hefengbao.jingmo.ui.screen.poem.nav.navigateToPoemListGraph
import com.hefengbao.jingmo.ui.screen.poem.nav.navigateToPoemScreen
import com.hefengbao.jingmo.ui.screen.poem.nav.poemListGraph
import com.hefengbao.jingmo.ui.screen.poem.nav.poemScreen
import com.hefengbao.jingmo.ui.screen.poemsentence.nav.navigateToPoemSentenceGraph
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
                    onItemClick = { navController.navigateToPoemScreen(it.toString()) },
                    nestGraph = {
                        poemScreen(
                            onBackClick = navController::navigateUp
                        )
                    }
                )
                poemSentenceGraph(
                    onBackClick = navController::navigateUp,
                    nestGraph = {}
                )
                chineseWisecrackGraph(
                    onBackClick = navController::navigateUp,
                    nestGraph = {}
                )
                idiomListGraph(
                    onBackClick = navController::navigateUp,
                    onItemClick = { navController.navigateToIdiomScreen(it.toString()) },
                    nestGraph = {
                        idiomScreen(
                            onBackClick = navController::navigateUp,
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