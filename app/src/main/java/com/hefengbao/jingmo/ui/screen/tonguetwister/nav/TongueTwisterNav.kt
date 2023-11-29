package com.hefengbao.jingmo.ui.screen.tonguetwister.nav

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hefengbao.jingmo.ui.screen.tonguetwister.TongueTwisterRoute
import java.net.URLDecoder
import java.net.URLEncoder
import kotlin.text.Charsets.UTF_8

internal const val tongueTwisterIdArg = "tongueTwisterId"

internal class TongueTwisterArgs(val tongueTwisterId: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(
                URLDecoder.decode(
                    checkNotNull(savedStateHandle[tongueTwisterIdArg]),
                    UTF_8.name()
                )
            )
}

fun NavController.navigateToTongueTwisterScreen(id: String) {
    val encodeId = URLEncoder.encode(id, UTF_8.name())
    this.navigate("tongue_twister/$encodeId") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.tongueTwisterScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = "tongue_twister/{$tongueTwisterIdArg}",
        arguments = listOf(
            navArgument(tongueTwisterIdArg) { type = NavType.StringType }
        )
    ) {
        TongueTwisterRoute(
            onBackClick = onBackClick,
        )
    }
}