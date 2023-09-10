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
            val poemSynced by viewModel.poemSynced.collectAsState(initial = false)
            val tagSynced by viewModel.tagSynced.collectAsState(initial = false)
            val poemTagSynced by viewModel.poemTagSynced.collectAsState(initial = false)
            val writerSynced by viewModel.writerSynced.collectAsState(initial = false)
            val poemSentenceSynced by viewModel.poemSentenceSynced.collectAsState(initial = false)
            val idiomSynced by viewModel.idiomSynced.collectAsState(initial = false)
            val chineseWisecrackSynced by viewModel.chineseWisecrackSynced.collectAsState(initial = false)

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
                        !poemSynced ||
                        !tagSynced ||
                        !poemTagSynced ||
                        !writerSynced ||
                        !poemSentenceSynced ||
                        !idiomSynced ||
                        !chineseWisecrackSynced
                    ) {
                        SyncScreen(
                            onSyncClick = { viewModel.sync() },
                            poemSynced = poemSynced,
                            tagSynced = tagSynced,
                            poemTagSynced = poemTagSynced,
                            writerSynced = writerSynced,
                            poemSentenceSynced = poemSentenceSynced,
                            idiomSynced = idiomSynced,
                            chineseWisecrackSynced = chineseWisecrackSynced,
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
    poemSynced: Boolean,
    tagSynced: Boolean,
    poemTagSynced: Boolean,
    writerSynced: Boolean,
    poemSentenceSynced: Boolean,
    idiomSynced: Boolean,
    chineseWisecrackSynced: Boolean,
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
        Item(title = "古诗词文", progress = if (poemSynced) 1f else poemProgress)
        Item(title = "标签", progress = if (tagSynced) 1f else tagProgress)
        Item(
            title = "古诗词文和标签对应关系",
            progress = if (poemTagSynced) 1f else poemTagProgress
        )
        Item(title = "诗人", progress = if (writerSynced) 1f else writerProgress)
        Item(
            title = "古诗词文名句",
            progress = if (poemSentenceSynced) 1f else poemSentenceProgress
        )
        Item(title = "成语", progress = if (idiomSynced) 1f else idiomProgress)
        Item(
            title = "歇后语",
            progress = if (chineseWisecrackSynced) 1f else chineseWisecrackProgress
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