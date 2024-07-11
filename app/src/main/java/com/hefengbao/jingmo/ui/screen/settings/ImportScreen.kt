/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.settings

import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.filled.FileOpen
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.ui.component.SimpleScaffold

@Composable
fun ImportRoute(
    viewModel: ImportViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val chineseExpressionRatio by viewModel.chineseExpressionRatio.collectAsState()
    val chineseWisecrackRatio by viewModel.chineseWisecrackRatio.collectAsState(initial = 0f)
    val chineseKnowledgeRatio by viewModel.chineseKnowledgeRatio.collectAsState(initial = 0f)
    val classicPoemsRatio by viewModel.classicPoemsRatio.collectAsState(initial = 0f)
    val dictionaryRatio by viewModel.dictionaryRatio.collectAsState()
    val idiomsRatio by viewModel.idiomsRatio.collectAsState(initial = 0f)
    val lyricRatio by viewModel.lyricRatio.collectAsState(initial = 0f)
    val peopleRatio by viewModel.peopleRatio.collectAsState(initial = 0f)
    val poemSentenceRatio by viewModel.poemSentencesRatio.collectAsState(initial = 0f)
    val tongueTwistersRatio by viewModel.tongueTwistersRatio.collectAsState(initial = 0f)
    val writingsRatio by viewModel.writingsRatio.collectAsState(initial = 0f)

    val chineseExpressionStatus by viewModel.chineseExpressionStatus.collectAsState(initial = ImportStatus.Loading)
    val chineseKnowledgeStatus by viewModel.chineseKnowledgeStatus.collectAsState(initial = ImportStatus.Loading)
    val chineseWisecracksStatus by viewModel.chineseWisecrackStatus.collectAsState(initial = ImportStatus.Loading)
    val classicPoemsStatus by viewModel.classicPoemsStatus.collectAsState(initial = ImportStatus.Loading)
    val dictionaryStatus by viewModel.dictionaryStatus.collectAsState(initial = ImportStatus.Loading)
    val idiomsStatus by viewModel.idiomStatus.collectAsState(initial = ImportStatus.Loading)
    val lyricStatus by viewModel.lyricStatus.collectAsState(initial = ImportStatus.Loading)
    val poemSentencesStatus by viewModel.poemSentenceStatus.collectAsState(initial = ImportStatus.Loading)
    val peopleStatus by viewModel.peopleStatus.collectAsState(initial = ImportStatus.Loading)
    val tongueTwistersStatus by viewModel.tongueTwisterStatus.collectAsState(initial = ImportStatus.Loading)
    val writingsStatus by viewModel.writingStatus.collectAsState(initial = ImportStatus.Loading)

    ImportScreen(
        onBackClick = onBackClick,
        chineseExpressionRatio = chineseExpressionRatio,
        chineseExpressionStatus = chineseExpressionStatus,
        chineseExpressionUris = { viewModel.chineseExpression(it) },
        chineseKnowledgeRatio = chineseKnowledgeRatio,
        chineseKnowledgeStatus = chineseKnowledgeStatus,
        chineseKnowledgeUris = { viewModel.chineseKnowledge(it) },
        chineseWisecracksRatio = chineseWisecrackRatio,
        chineseWisecracksStatus = chineseWisecracksStatus,
        chineseWisecracksUris = { viewModel.chineseWisecrack(it) },
        classicPoemsRatio = classicPoemsRatio,
        classicPoemsStatus = classicPoemsStatus,
        classicPoemsUris = { viewModel.classicPoems(it) },
        dictionaryRatio = dictionaryRatio,
        dictionaryStatus = dictionaryStatus,
        dictionaryUris = { viewModel.dictionary(it) },
        idiomsRatio = idiomsRatio,
        idiomsStatus = idiomsStatus,
        idiomsUris = { viewModel.idioms(it) },
        lyricRatio = lyricRatio,
        lyricStatus = lyricStatus,
        lyricUris = { viewModel.lyrics(it) },
        peopleRatio = peopleRatio,
        peopleStatus = peopleStatus,
        peopleUris = { viewModel.people(it) },
        poemSentencesRatio = poemSentenceRatio,
        poemSentencesStatus = poemSentencesStatus,
        poemSentencesUris = { viewModel.poemSentences(it) },
        tongueTwistersRatio = tongueTwistersRatio,
        tongueTwistersStatus = tongueTwistersStatus,
        tongueTwistersUris = { viewModel.tongueTwisters(it) },
        writingsRatio = writingsRatio,
        writingsStatus = writingsStatus,
        writingsUris = { viewModel.writings(it) }
    )
}

@Composable
private fun ImportScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    chineseExpressionRatio: Float,
    chineseExpressionStatus: ImportStatus<Any>,
    chineseExpressionUris: (List<Uri>) -> Unit,
    chineseKnowledgeRatio: Float,
    chineseKnowledgeStatus: ImportStatus<Any>,
    chineseKnowledgeUris: (List<Uri>) -> Unit,
    chineseWisecracksRatio: Float,
    chineseWisecracksStatus: ImportStatus<Any>,
    chineseWisecracksUris: (List<Uri>) -> Unit,
    classicPoemsRatio: Float,
    classicPoemsStatus: ImportStatus<Any>,
    classicPoemsUris: (List<Uri>) -> Unit,
    dictionaryRatio: Float,
    dictionaryStatus: ImportStatus<Any>,
    dictionaryUris: (List<Uri>) -> Unit,
    idiomsRatio: Float,
    idiomsStatus: ImportStatus<Any>,
    idiomsUris: (List<Uri>) -> Unit,
    lyricRatio: Float,
    lyricStatus: ImportStatus<Any>,
    lyricUris: (List<Uri>) -> Unit,
    peopleRatio: Float,
    peopleStatus: ImportStatus<Any>,
    peopleUris: (List<Uri>) -> Unit,
    poemSentencesRatio: Float,
    poemSentencesStatus: ImportStatus<Any>,
    poemSentencesUris: (List<Uri>) -> Unit,
    tongueTwistersRatio: Float,
    tongueTwistersStatus: ImportStatus<Any>,
    tongueTwistersUris: (List<Uri>) -> Unit,
    writingsRatio: Float,
    writingsStatus: ImportStatus<Any>,
    writingsUris: (List<Uri>) -> Unit
) {
    val uriHandler = LocalUriHandler.current
    val chineseExpressionLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.OpenMultipleDocuments()) {
            chineseExpressionUris(it)
        }
    val chineseKnowledgeLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.OpenMultipleDocuments()) {
            chineseKnowledgeUris(it)
        }
    val chineseWisecracksLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.OpenMultipleDocuments()) {
            chineseWisecracksUris(it)
        }
    val classicPoemsLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.OpenMultipleDocuments()) {
            classicPoemsUris(it)
        }
    val dictionaryLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.OpenMultipleDocuments()) {
            dictionaryUris(it)
        }
    val idiomsLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.OpenMultipleDocuments()) {
            idiomsUris(it)
        }
    val lyricLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.OpenMultipleDocuments()) {
            lyricUris(it)
        }
    val peopleLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.OpenMultipleDocuments()) {
            peopleUris(it)
        }
    val poemSentencesLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.OpenMultipleDocuments()) {
            poemSentencesUris(it)
        }
    val tongueTwistersLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.OpenMultipleDocuments()) {
            tongueTwistersUris(it)
        }
    val writingsLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.OpenMultipleDocuments()) {
            writingsUris(it)
        }

    SimpleScaffold(
        onBackClick = onBackClick,
        title = "导入数据",
        actions = {
            IconButton(onClick = { uriHandler.openUri("https://mp.weixin.qq.com/s/VT4zx-2vnrwuVZ2Eq5yGCg") }) {
                Icon(imageVector = Icons.AutoMirrored.Filled.HelpOutline, contentDescription = "")
            }
        }
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            MenuItem(
                title = "经典诗文",
                ratio = classicPoemsRatio,
                launcher = classicPoemsLauncher,
                status = classicPoemsStatus
            )
            MenuItem(
                title = "诗文",
                ratio = writingsRatio,
                launcher = writingsLauncher,
                status = writingsStatus
            )
            MenuItem(
                title = "诗文名句",
                ratio = poemSentencesRatio,
                launcher = poemSentencesLauncher,
                status = poemSentencesStatus
            )
            MenuItem(
                title = "汉字",
                ratio = dictionaryRatio,
                launcher = dictionaryLauncher,
                status = dictionaryStatus
            )
            MenuItem(
                title = "成语",
                ratio = idiomsRatio,
                launcher = idiomsLauncher,
                status = idiomsStatus
            )
            MenuItem(
                title = "词语",
                ratio = chineseExpressionRatio,
                launcher = chineseExpressionLauncher,
                status = chineseExpressionStatus
            )
            MenuItem(
                title = "歇后语",
                ratio = chineseWisecracksRatio,
                launcher = chineseWisecracksLauncher,
                status = chineseWisecracksStatus
            )
            MenuItem(
                title = "知识卡片",
                ratio = chineseKnowledgeRatio,
                launcher = chineseKnowledgeLauncher,
                status = chineseKnowledgeStatus
            )
            MenuItem(
                title = "人物",
                ratio = peopleRatio,
                launcher = peopleLauncher,
                status = peopleStatus
            )
            MenuItem(
                title = "绕口令",
                ratio = tongueTwistersRatio,
                launcher = tongueTwistersLauncher,
                status = tongueTwistersStatus
            )
            MenuItem(
                title = "歌词",
                ratio = lyricRatio,
                launcher = lyricLauncher,
                status = lyricStatus
            )
        }
    }
}

@SuppressLint("DefaultLocale")
@Composable
private fun MenuItem(
    modifier: Modifier = Modifier,
    title: String,
    ratio: Float,
    launcher: ManagedActivityResultLauncher<Array<String>, List<@JvmSuppressWildcards Uri>>,
    status: ImportStatus<Any>
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = title)
            if (ratio > 0) {
                Text(text = "${String.format("%.2f", ratio * 100)}%")
            }
        }
        IconButton(onClick = { launcher.launch(arrayOf("application/json")) }) {
            if (status is ImportStatus.Loading) {
                CircularProgressIndicator()
            }
            Icon(imageVector = Icons.Default.FileOpen, contentDescription = "")
        }
    }
    Divider()
}