package com.hefengbao.jingmo.ui.screen.chinesecharacter.nav

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hefengbao.jingmo.ui.screen.chinesecharacter.CharacterShowScreenRoute
import java.net.URLDecoder
import java.net.URLEncoder
import kotlin.text.Charsets.UTF_8

internal const val characterShowArgsCharacterArg = "character"
private const val base = "chinese_character_show"
private const val ROUTE = "$base/{$characterShowArgsCharacterArg}"

internal class CharacterShowArgs(val id: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(
                URLDecoder.decode(savedStateHandle[characterShowArgsCharacterArg], UTF_8.name()),
            )
}

fun NavController.navigateToChineseCharacterShowScreen(id: String) {
    val encodeId = URLEncoder.encode(id, UTF_8.name())
    this.navigate("$base/$encodeId") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.chineseCharacterShowScreen(
    onBackClick: () -> Unit,
) {
    composable(
        route = ROUTE,
        arguments = listOf(
            navArgument(characterShowArgsCharacterArg) { type = NavType.StringType },
        )
    ) {
        CharacterShowScreenRoute(onBackClick = onBackClick)
    }
}
