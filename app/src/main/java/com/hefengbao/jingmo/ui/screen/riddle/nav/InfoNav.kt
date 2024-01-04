package com.hefengbao.jingmo.ui.screen.riddle.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hefengbao.jingmo.ui.screen.riddle.InfoRoute

private const val ROUTE_INFO = "riddle_info"

fun NavController.navigateToRiddleInfoScreen(){
    this.navigate(ROUTE_INFO)
}

fun NavGraphBuilder.riddleInfoScreen(
    onBackClick: () -> Unit,
){
    composable(ROUTE_INFO){
        InfoRoute(
            onBackClick = onBackClick
        )
    }
}