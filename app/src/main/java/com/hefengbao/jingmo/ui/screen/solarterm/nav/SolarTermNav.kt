package com.hefengbao.jingmo.ui.screen.solarterm.nav

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hefengbao.jingmo.ui.screen.solarterm.SolarTermRoute
import java.net.URLDecoder
import java.net.URLEncoder
import kotlin.text.Charsets.UTF_8

internal const val solarTermIdArg = "solarTermId"

internal class SolarTermArgs(val solarTermId: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(URLDecoder.decode(checkNotNull(savedStateHandle[solarTermIdArg]), UTF_8.name()))
}

fun NavController.navigateToSolarTerm(id: String) {
    val encodeId = URLEncoder.encode(id, UTF_8.name())
    this.navigate("solar_term/$encodeId") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.solarTermScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = "solar_term/{$solarTermIdArg}",
        arguments = listOf(
            navArgument(solarTermIdArg) { type = NavType.StringType }
        )
    ) {
        SolarTermRoute(onBackClick = onBackClick)
    }
}