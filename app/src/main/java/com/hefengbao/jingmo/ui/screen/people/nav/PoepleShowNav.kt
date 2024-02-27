package com.hefengbao.jingmo.ui.screen.people.nav

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hefengbao.jingmo.ui.screen.people.PeopleShowRoute
import java.net.URLDecoder
import java.net.URLEncoder
import kotlin.text.Charsets.UTF_8

internal const val peopleShowTypeArg = "type"
internal const val peopleShowQueryArg = "id"
private const val base = "people_show"
private const val ROUTE = "$base/{$peopleShowTypeArg}/{$peopleShowQueryArg}"

internal class PeopleShowArgs(val type: String, val query: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(
                URLDecoder.decode(savedStateHandle[peopleShowTypeArg], UTF_8.name()),
                URLDecoder.decode(savedStateHandle[peopleShowQueryArg], UTF_8.name())
            )
}

fun NavController.navigateToPeopleShowScreen(type: String, query: String) {
    val encodeType = URLEncoder.encode(type, UTF_8.name())
    val encodeQuery = URLEncoder.encode(query, UTF_8.name())
    this.navigate("$base/$encodeType/$encodeQuery") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.peopleShowScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = ROUTE,
        arguments = listOf(
            navArgument(peopleShowQueryArg) { NavType.StringType }
        )
    ) {
        PeopleShowRoute(
            onBackClick = onBackClick
        )
    }
}