package com.hefengbao.jingmo.ui.screen.idiom.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hefengbao.jingmo.ui.screen.idiom.IdiomReadRoute

private const val ROUTE = "idiom_read"

fun NavController.navigateToIdiomReadScreen(){
    this.navigate(ROUTE)
}

fun NavGraphBuilder.idiomReadScreen(
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit
){
    composable(ROUTE){
        IdiomReadRoute(
            onBackClick = onBackClick,
            onCaptureClick = onCaptureClick
        )
    }
}