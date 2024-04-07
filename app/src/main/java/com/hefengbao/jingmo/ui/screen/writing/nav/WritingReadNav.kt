package com.hefengbao.jingmo.ui.screen.writing.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hefengbao.jingmo.ui.screen.writing.WritingReadRoute

private const val ROUTE = "writing_read"

fun NavController.navigateToWritingReadScreen() {
    this.navigate(ROUTE) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.writingReadScreen(
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
) {
    composable(ROUTE) {
        WritingReadRoute(
            onBackClick = onBackClick,
            onCaptureClick = onCaptureClick
        )
    }
}