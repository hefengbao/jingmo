package com.hefengbao.jingmo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.hefengbao.jingmo.data.DataSetVersion
import com.hefengbao.jingmo.route.AppNavHost
import com.hefengbao.jingmo.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        val viewModel by viewModels<MainActivityViewModel>()

        setContent {
            val appNavController = rememberNavController()

            val showLanding by viewModel.showLanding.collectAsState(initial = true)

            val synced by viewModel.synced.collectAsState(initial = false)

            val poemVersion by viewModel.poemVersion.collectAsState(initial = 0)
            val tagVersion by viewModel.tagVersion.collectAsState(initial = 0)
            val poemTagVersion by viewModel.poemTagVersion.collectAsState(initial = 0)
            val writerVersion by viewModel.writerVersion.collectAsState(initial = 0)
            val poemSentenceVersion by viewModel.poemSentenceVersion.collectAsState(initial = 0)
            val idiomVersion by viewModel.idiomVersion.collectAsState(initial = 0)
            val chineseWiseCrackVersion by viewModel.chineseWiseCrackVersion.collectAsState(initial = 0)

            val poemProgress by viewModel.poemProgress.collectAsState(initial = 0f)
            val tagProgress by viewModel.tagProgress.collectAsState(initial = 0f)
            val poemTagProgress by viewModel.poemTagProgress.collectAsState(initial = 0f)
            val writerProgress by viewModel.writerProgress.collectAsState(initial = 0f)
            val poemSentenceProgress by viewModel.poemSentenceProgress.collectAsState(initial = 0f)
            val idiomProgress by viewModel.idiomProgress.collectAsState(initial = 0f)
            val chineseWisecrackProgress by viewModel.chineseWisecrackProgress.collectAsState(
                initial = 0f
            )

            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (showLanding) {
                        LandingScreen()
                        viewModel.closeLanding()
                    } else if (
                        !synced
                    ) {
                        SyncScreen(
                            onSyncClick = { viewModel.sync() },
                            poemVersion = poemVersion,
                            tagVersion = tagVersion,
                            poemTagVersion = poemTagVersion,
                            writerVersion = writerVersion,
                            poemSentenceVersion = poemSentenceVersion,
                            idiomVersion = idiomVersion,
                            chineseWiseCrackVersion = chineseWiseCrackVersion,
                            poemProgress = poemProgress,
                            tagProgress = tagProgress,
                            poemTagProgress = poemTagProgress,
                            writerProgress = writerProgress,
                            poemSentenceProgress = poemSentenceProgress,
                            idiomProgress = idiomProgress,
                            chineseWisecrackProgress = chineseWisecrackProgress,
                        )
                    } else {
                        AppNavHost(navController = appNavController)
                    }
                }
            }
        }
    }
}

@Composable
private fun LandingScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = "献给\n\n喜欢中华文字、文学、文化的人",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            modifier = modifier
                .fillMaxWidth()
                .align(Alignment.Center),
        )
    }
}

@Composable
private fun SyncScreen(
    modifier: Modifier = Modifier,
    onSyncClick: () -> Unit,
    poemVersion: Int,
    tagVersion: Int,
    poemTagVersion: Int,
    writerVersion: Int,
    poemSentenceVersion: Int,
    idiomVersion: Int,
    chineseWiseCrackVersion: Int,
    poemProgress: Float,
    tagProgress: Float,
    poemTagProgress: Float,
    writerProgress: Float,
    poemSentenceProgress: Float,
    idiomProgress: Float,
    chineseWisecrackProgress: Float
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Button(onClick = onSyncClick) {
            Text(text = "同步数据")
        }
        Item(
            title = "古诗词文",
            progress = if (poemVersion == DataSetVersion.poem) 1f else poemProgress
        )
        Item(title = "标签", progress = if (tagVersion == DataSetVersion.tag) 1f else tagProgress)
        Item(
            title = "古诗词文和标签对应关系",
            progress = if (poemTagVersion == DataSetVersion.poemTag) 1f else poemTagProgress
        )
        Item(
            title = "诗人",
            progress = if (writerVersion == DataSetVersion.writer) 1f else writerProgress
        )
        Item(
            title = "古诗词文名句",
            progress = if (poemSentenceVersion == DataSetVersion.poemSentence) 1f else poemSentenceProgress
        )
        Item(
            title = "成语",
            progress = if (idiomVersion == DataSetVersion.idiom) 1f else idiomProgress
        )
        Item(
            title = "歇后语",
            progress = if (chineseWiseCrackVersion == DataSetVersion.chineseWisecrack) 1f else chineseWisecrackProgress
        )
    }
}

@Composable
private fun Item(
    title: String,
    progress: Float
) {
    Text(text = title, style = MaterialTheme.typography.titleMedium)
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        LinearProgressIndicator(
            progress = progress, modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        Text(
            text = "${String.format("%.2f", (progress * 100))}%",
            modifier = Modifier.width(100.dp),
            textAlign = TextAlign.End
        )
    }
}