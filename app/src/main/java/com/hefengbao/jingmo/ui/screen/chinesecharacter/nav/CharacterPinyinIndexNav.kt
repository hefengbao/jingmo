package com.hefengbao.jingmo.ui.screen.chinesecharacter.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hefengbao.jingmo.ui.screen.chinesecharacter.CharacterPinyinIndexRoute


private const val ROUTE = "chinese_character_pinyin_index"

fun NavController.navigateToChineseCharacterPinyinIndexScreen() {
    this.navigate(ROUTE) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.chineseCharacterPinyinIndexScreen(
    onBackClick: () -> Unit,
    onItemClick: (String, String) -> Unit,
) {
    composable(ROUTE) {
        CharacterPinyinIndexRoute(
            onBackClick = onBackClick,
            onItemClick = onItemClick
        )
    }
}