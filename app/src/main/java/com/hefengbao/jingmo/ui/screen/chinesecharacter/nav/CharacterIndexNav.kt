package com.hefengbao.jingmo.ui.screen.chinesecharacter.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hefengbao.jingmo.ui.screen.chinesecharacter.ChineseCharacterIndexRoute

private const val ROUTE = "chinese_character_index"
private const val ROUTE_GRAPH = "chinese_character_index_graph"

fun NavController.navigateToChineseCharacterIndexGraph() {
    this.navigate(ROUTE_GRAPH)
}

fun NavGraphBuilder.chineseCharacterIndexGraph(
    onBackClick: () -> Unit,
    onPinyinSearchClick: () -> Unit,
    onRadicalClickSearch: () -> Unit,
    onStrokeSearchClick: () -> Unit,
    onStrokeClick: () -> Unit,
    onSearchClick: (String, String) -> Unit,
    nestGraph: NavGraphBuilder.() -> Unit
) {
    navigation(
        startDestination = ROUTE,
        route = ROUTE_GRAPH
    ) {
        composable(ROUTE) {
            ChineseCharacterIndexRoute(
                onBackClick = onBackClick,
                onPinyinSearchClick = onPinyinSearchClick,
                onRadicalClickSearch = onRadicalClickSearch,
                onStrokeSearchClick = onStrokeSearchClick,
                onStrokeClick = onStrokeClick,
                onSearch = onSearchClick
            )
        }
        nestGraph()
    }
}