package com.hefengbao.jingmo.ui.screen.festival.nav

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hefengbao.jingmo.ui.screen.festival.FestivalRoute
import java.net.URLDecoder
import java.net.URLEncoder
import kotlin.text.Charsets.UTF_8

internal const val festivalShowIdArg = "festivalId"
private const val base = "festival_show"
private const val ROUTE = "$base/{$festivalShowIdArg}"

internal class FestivalShowArgs(val festivalId: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(URLDecoder.decode(checkNotNull(savedStateHandle[festivalShowIdArg]), UTF_8.name()))
}

fun NavController.navigateToFestivalShowScreen(id: String) {
    val encodedId = URLEncoder.encode(id, UTF_8.name())
    this.navigate("$base/$encodedId") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.festivalShowScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = ROUTE,
        arguments = listOf(
            navArgument(name = festivalShowIdArg) {
                type = NavType.StringType
            }
        )
    ) {
        FestivalRoute(
            onBackClick = onBackClick
        )
    }
}