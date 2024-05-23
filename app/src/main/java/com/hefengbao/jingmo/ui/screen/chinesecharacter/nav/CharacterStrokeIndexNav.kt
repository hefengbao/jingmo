package com.hefengbao.jingmo.ui.screen.chinesecharacter.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hefengbao.jingmo.ui.screen.chinesecharacter.CharacterStrokeIndexRoute

private const val ROUTE = "chinese_character_stroke_index"

fun NavController.navigateToChineseCharacterStrokeIndexScreen() {
    this.navigate(ROUTE) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.chineseCharacterStrokeIndexScreen(
    onBackClick: () -> Unit,
    onItemClick: (String, String) -> Unit,
) {
    composable(ROUTE) {
        CharacterStrokeIndexRoute(
            onBackClick = onBackClick,
            onItemClick = onItemClick
        )
    }
}