package com.hefengbao.jingmo.ui.screen.chinesewisecrack.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hefengbao.jingmo.ui.screen.chinesewisecrack.ChineseWisecrackIndexRoute

private const val ROUTE_CHINESE_WISECRACK_INDEX = "chinese_wisecrack_index"
private const val ROUTE_CHINESE_WISECRACK_INDEX_GRAPH = "chinese_wisecrack_index_graph"

fun NavController.navigateToChineseWisecrackIndexGraph() {
    this.navigate(ROUTE_CHINESE_WISECRACK_INDEX)
}

fun NavGraphBuilder.chineseWisecrackIndexGraph(
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
    onSearchClick: () -> Unit,
    onBookmarksClick: () -> Unit,
    nestGraph: NavGraphBuilder.() -> Unit
) {
    navigation(
        startDestination = ROUTE_CHINESE_WISECRACK_INDEX,
        route = ROUTE_CHINESE_WISECRACK_INDEX_GRAPH
    ) {
        composable(ROUTE_CHINESE_WISECRACK_INDEX) {
            ChineseWisecrackIndexRoute(
                onBackClick = onBackClick,
                onCaptureClick = onCaptureClick,
                onSearchClick = onSearchClick,
                onBookmarksClick = onBookmarksClick
            )
        }
    }

    nestGraph()
}