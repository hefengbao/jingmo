package com.hefengbao.jingmo.ui.screen.chinesecharacter.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hefengbao.jingmo.ui.screen.chinesecharacter.CharacterRadicalIndexRoute

private const val ROUTE = "chinese_character_radical_index"

fun NavController.navigateToChineseCharacterRadicalIndexScreen() {
    this.navigate(ROUTE) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.chineseCharacterRadicalIndexScreen(
    onBackClick: () -> Unit,
    onItemClick: (String, String) -> Unit,
) {
    composable(ROUTE) {
        CharacterRadicalIndexRoute(
            onBackClick = onBackClick,
            onItemClick = onItemClick
        )
    }
}