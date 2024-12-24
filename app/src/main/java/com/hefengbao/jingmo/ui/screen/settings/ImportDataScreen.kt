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
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import com.hefengbao.jingmo.ui.screen.settings.components.SettingsTitle

@Composable
fun ImportRoute(
    viewModel: ImportViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val chinaWorldCultureHeritageRatio by viewModel.chinaWorldCultureHeritageRatio.collectAsState()
    val chineseAntitheticalCoupletRatio by viewModel.chineseAntitheticalCoupletRatio.collectAsState()
    val chineseExpressionRatio by viewModel.chineseExpressionRatio.collectAsState()
    val chineseWisecrackRatio by viewModel.chineseWisecrackRatio.collectAsState()
    val chineseKnowledgeRatio by viewModel.chineseKnowledgeRatio.collectAsState()
    val chineseModernPoetryRatio by viewModel.chineseModernPoetryRatio.collectAsState()
    val chineseProverbRatio by viewModel.chineseProverbRatio.collectAsState()
    val chineseQuotesRatio by viewModel.chineseQuotesRatio.collectAsState()
    val chineseRiddleRatio by viewModel.chineseRiddleRatio.collectAsState()
    val chineseDictionaryRatio by viewModel.chineseDictionaryRatio.collectAsState()
    val chineseIdiomsRatio by viewModel.chineseIdiomsRatio.collectAsState()
    val chineseLyricRatio by viewModel.chineseLyricRatio.collectAsState()
    val chineseTongueTwistersRatio by viewModel.chineseTongueTwistersRatio.collectAsState()

    val classicalLiteraturePeopleRatio by viewModel.peopleRatio.collectAsState()
    val classicalLiteratureClassicPoemsRatio by viewModel.classicPoemsRatio.collectAsState()
    val classicalLiteratureSentenceRatio by viewModel.poemSentencesRatio.collectAsState()
    val classicalLiteratureWritingsRatio by viewModel.writingsRatio.collectAsState()

    val chinaWorldCultureHeritageStatus by viewModel.chinaWorldCultureHeritageStatus.collectAsState(
        initial = ImportStatus.Loading
    )
    val chineseAntitheticalCoupletStatus by viewModel.chineseAntitheticalCoupletStatus.collectAsState(
        initial = ImportStatus.Loading
    )
    val chineseExpressionStatus by viewModel.chineseExpressionStatus.collectAsState(initial = ImportStatus.Loading)
    val chineseKnowledgeStatus by viewModel.chineseKnowledgeStatus.collectAsState(initial = ImportStatus.Loading)
    val chineseWisecracksStatus by viewModel.chineseWisecrackStatus.collectAsState(initial = ImportStatus.Loading)
    val chineseModernPoetryStatus by viewModel.chineseModernPoetryStatus.collectAsState(ImportStatus.Loading)
    val chineseProverbStatus by viewModel.chineseProverbStatus.collectAsState(initial = ImportStatus.Loading)
    val chineseQuotesStatus by viewModel.chineseQuotesStatus.collectAsState(initial = ImportStatus.Loading)
    val chineseRiddleStatus by viewModel.chineseRiddleStatus.collectAsState(initial = ImportStatus.Loading)
    val chineseDictionaryStatus by viewModel.chineseDictionaryStatus.collectAsState(initial = ImportStatus.Loading)
    val chineseIdiomsStatus by viewModel.chineseIdiomStatus.collectAsState(initial = ImportStatus.Loading)
    val chineseLyricStatus by viewModel.chineseLyricStatus.collectAsState(initial = ImportStatus.Loading)
    val chineseTongueTwistersStatus by viewModel.chineseTongueTwisterStatus.collectAsState(initial = ImportStatus.Loading)
    val classicalLiteratureClassicPoemsStatus by viewModel.classicPoemsStatus.collectAsState(initial = ImportStatus.Loading)
    val classicalLiteratureSentencesStatus by viewModel.poemSentenceStatus.collectAsState(initial = ImportStatus.Loading)
    val classicalLiteraturePeopleStatus by viewModel.peopleStatus.collectAsState(initial = ImportStatus.Loading)
    val classicalLiteratureWritingsStatus by viewModel.writingStatus.collectAsState(initial = ImportStatus.Loading)

    ImportScreen(
        onBackClick = onBackClick,
        chinaWorldCultureHeritageRatio = chinaWorldCultureHeritageRatio,
        chinaWorldCultureHeritageStatus = chinaWorldCultureHeritageStatus,
        chinaWorldCultureHeritageUris = viewModel::chinaWorldCultureHeritage,
        clearChinaWorldCultureHeritage = viewModel::clearChinaWorldCultureHeritage,
        chineseAntitheticalCoupletRatio = chineseAntitheticalCoupletRatio,
        chineseAntitheticalCoupletStatus = chineseAntitheticalCoupletStatus,
        chineseAntitheticalCoupletUris = viewModel::chineseAntitheticalCouplet,
        clearChineseAntitheticalCouplet = viewModel::clearChineseAntitheticalCouplet,
        chineseExpressionRatio = chineseExpressionRatio,
        chineseExpressionStatus = chineseExpressionStatus,
        chineseExpressionUris = { viewModel.chineseExpression(it) },
        clearChineseExpressions = viewModel::clearChineseExpressions,
        chineseKnowledgeRatio = chineseKnowledgeRatio,
        chineseKnowledgeStatus = chineseKnowledgeStatus,
        chineseKnowledgeUris = { viewModel.chineseKnowledge(it) },
        clearChineseKnowledge = viewModel::clearChineseKnowledge,
        chineseModernPoetryRatio = chineseModernPoetryRatio,
        chineseModernPoetryStatus = chineseModernPoetryStatus,
        chineseModernPoetryUris = viewModel::chineseModernPoetry,
        clearChineseModernPoetry = viewModel::clearChineseModernPoetry,
        chineseProverbRatio = chineseProverbRatio,
        chineseProverbStatus = chineseProverbStatus,
        chineseProverbUris = { viewModel.chineseProverb(it) },
        clearChineseProverbs = viewModel::clearChineseProverbs,
        chineseQuotesRatio = chineseQuotesRatio,
        chineseQuotesStatus = chineseQuotesStatus,
        chineseQuotesUris = viewModel::chineseQuotes,
        clearChineseQuotes = viewModel::clearChineseQuotes,
        chineseWisecracksRatio = chineseWisecrackRatio,
        chineseWisecracksStatus = chineseWisecracksStatus,
        chineseWisecracksUris = { viewModel.chineseWisecrack(it) },
        clearChineseWisecracks = viewModel::clearChineseWisecracks,
        chineseRiddleRadio = chineseRiddleRatio,
        chineseRiddleStatus = chineseRiddleStatus,
        chineseRiddleUris = { viewModel.chineseRiddle(it) },
        clearChineseRiddles = viewModel::clearChineseRiddle,
        chineseDictionaryRatio = chineseDictionaryRatio,
        chineseDictionaryStatus = chineseDictionaryStatus,
        chineseDictionaryUris = { viewModel.dictionary(it) },
        clearChineseDictionaries = viewModel::clearChineseDictionaries,
        chineseIdiomsRatio = chineseIdiomsRatio,
        chineseIdiomsStatus = chineseIdiomsStatus,
        chineseIdiomsUris = { viewModel.idioms(it) },
        clearChineseIdioms = viewModel::clearChineseIdioms,
        chineseLyricRatio = chineseLyricRatio,
        chineseLyricStatus = chineseLyricStatus,
        chineseLyricUris = { viewModel.lyrics(it) },
        clearChineseLyrics = viewModel::clearChineseLyrics,
        chineseTongueTwistersRatio = chineseTongueTwistersRatio,
        chineseTongueTwistersStatus = chineseTongueTwistersStatus,
        chineseTongueTwistersUris = { viewModel.tongueTwisters(it) },
        clearChineseTongueTwisters = viewModel::clearChineseTongueTwisters,
        classicalLiteratureClassicPoemsRatio = classicalLiteratureClassicPoemsRatio,
        classicalLiteratureClassicPoemsStatus = classicalLiteratureClassicPoemsStatus,
        classicalLiteratureClassicPoemsUris = { viewModel.classicPoems(it) },
        clearClassicalLiteratureClassicPoems = viewModel::clearClassicalLiteratureClassicPoems,
        classicalLiteraturePeopleRatio = classicalLiteraturePeopleRatio,
        classicalLiteraturePeopleStatus = classicalLiteraturePeopleStatus,
        classicalLiteraturePeopleUris = { viewModel.people(it) },
        clearClassicalLiteraturePeople = viewModel::clearClassicalLiteraturePeople,
        classicalLiteratureSentencesRatio = classicalLiteratureSentenceRatio,
        classicalLiteratureSentencesStatus = classicalLiteratureSentencesStatus,
        classicalLiteratureSentencesUris = { viewModel.poemSentences(it) },
        clearClassicalLiteratureSentence = viewModel::clearClassicalLiteratureSentence,
        classicalLiteratureWritingsRatio = classicalLiteratureWritingsRatio,
        classicalLiteratureWritingsStatus = classicalLiteratureWritingsStatus,
        classicalLiteratureWritingsUris = { viewModel.writings(it) },
        clearClassicalLiteratureWritings = viewModel::clearClassicalLiteratureWritings
    )
}

@Composable
private fun ImportScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    chinaWorldCultureHeritageRatio: Float,
    chinaWorldCultureHeritageStatus: ImportStatus<Any>,
    chinaWorldCultureHeritageUris: (List<Uri>) -> Unit,
    clearChinaWorldCultureHeritage: () -> Unit,
    chineseAntitheticalCoupletRatio: Float,
    chineseAntitheticalCoupletStatus: ImportStatus<Any>,
    chineseAntitheticalCoupletUris: (List<Uri>) -> Unit,
    clearChineseAntitheticalCouplet: () -> Unit,
    chineseExpressionRatio: Float,
    chineseExpressionStatus: ImportStatus<Any>,
    chineseExpressionUris: (List<Uri>) -> Unit,
    clearChineseExpressions: () -> Unit,
    chineseKnowledgeRatio: Float,
    chineseKnowledgeStatus: ImportStatus<Any>,
    chineseKnowledgeUris: (List<Uri>) -> Unit,
    clearChineseKnowledge: () -> Unit,
    chineseModernPoetryRatio: Float,
    chineseModernPoetryStatus: ImportStatus<Any>,
    chineseModernPoetryUris: (List<Uri>) -> Unit,
    clearChineseModernPoetry: () -> Unit,
    chineseProverbRatio: Float,
    chineseProverbStatus: ImportStatus<Any>,
    chineseProverbUris: (List<Uri>) -> Unit,
    clearChineseProverbs: () -> Unit,
    chineseQuotesRatio: Float,
    chineseQuotesStatus: ImportStatus<Any>,
    chineseQuotesUris: (List<Uri>) -> Unit,
    clearChineseQuotes: () -> Unit,
    chineseWisecracksRatio: Float,
    chineseWisecracksStatus: ImportStatus<Any>,
    chineseWisecracksUris: (List<Uri>) -> Unit,
    clearChineseWisecracks: () -> Unit,
    chineseRiddleRadio: Float,
    chineseRiddleStatus: ImportStatus<Any>,
    chineseRiddleUris: (List<Uri>) -> Unit,
    clearChineseRiddles: () -> Unit,
    chineseDictionaryRatio: Float,
    chineseDictionaryStatus: ImportStatus<Any>,
    chineseDictionaryUris: (List<Uri>) -> Unit,
    clearChineseDictionaries: () -> Unit,
    chineseIdiomsRatio: Float,
    chineseIdiomsStatus: ImportStatus<Any>,
    chineseIdiomsUris: (List<Uri>) -> Unit,
    clearChineseIdioms: () -> Unit,
    chineseLyricRatio: Float,
    chineseLyricStatus: ImportStatus<Any>,
    chineseLyricUris: (List<Uri>) -> Unit,
    clearChineseLyrics: () -> Unit,
    chineseTongueTwistersRatio: Float,
    chineseTongueTwistersStatus: ImportStatus<Any>,
    chineseTongueTwistersUris: (List<Uri>) -> Unit,
    clearChineseTongueTwisters: () -> Unit,
    classicalLiteratureClassicPoemsRatio: Float,
    classicalLiteratureClassicPoemsStatus: ImportStatus<Any>,
    classicalLiteratureClassicPoemsUris: (List<Uri>) -> Unit,
    clearClassicalLiteratureClassicPoems: () -> Unit,
    classicalLiteraturePeopleRatio: Float,
    classicalLiteraturePeopleStatus: ImportStatus<Any>,
    classicalLiteraturePeopleUris: (List<Uri>) -> Unit,
    clearClassicalLiteraturePeople: () -> Unit,
    classicalLiteratureSentencesRatio: Float,
    classicalLiteratureSentencesStatus: ImportStatus<Any>,
    classicalLiteratureSentencesUris: (List<Uri>) -> Unit,
    clearClassicalLiteratureSentence: () -> Unit,
    classicalLiteratureWritingsRatio: Float,
    classicalLiteratureWritingsStatus: ImportStatus<Any>,
    classicalLiteratureWritingsUris: (List<Uri>) -> Unit,
    clearClassicalLiteratureWritings: () -> Unit,
) {
    val uriHandler = LocalUriHandler.current
    val chinaWorldCultureHeritageLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.OpenMultipleDocuments()) {
            chinaWorldCultureHeritageUris(it)
        }
    val chineseAntitheticalCoupletLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.OpenMultipleDocuments()) {
            chineseAntitheticalCoupletUris(it)
        }

    val chineseExpressionLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.OpenMultipleDocuments()) {
            chineseExpressionUris(it)
        }
    val chineseKnowledgeLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.OpenMultipleDocuments()) {
            chineseKnowledgeUris(it)
        }
    val chineseModernPoetryLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.OpenMultipleDocuments()) {
            chineseModernPoetryUris(it)
        }
    val chineseProverbLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.OpenMultipleDocuments()) {
            chineseProverbUris(it)
        }
    val chineseQuotesLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.OpenMultipleDocuments()) {
            chineseQuotesUris(it)
        }
    val chineseWisecracksLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.OpenMultipleDocuments()) {
            chineseWisecracksUris(it)
        }
    val chineseRiddleLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.OpenMultipleDocuments()) {
            chineseRiddleUris(it)
        }
    val classicalLiteratureClassicPoemsLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.OpenMultipleDocuments()) {
            classicalLiteratureClassicPoemsUris(it)
        }
    val chineseDictionaryLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.OpenMultipleDocuments()) {
            chineseDictionaryUris(it)
        }
    val chineseIdiomsLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.OpenMultipleDocuments()) {
            chineseIdiomsUris(it)
        }
    val chineseLyricLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.OpenMultipleDocuments()) {
            chineseLyricUris(it)
        }
    val classicalLiteraturePeopleLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.OpenMultipleDocuments()) {
            classicalLiteraturePeopleUris(it)
        }
    val classicalLiteratureSentencesLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.OpenMultipleDocuments()) {
            classicalLiteratureSentencesUris(it)
        }
    val chineseTongueTwistersLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.OpenMultipleDocuments()) {
            chineseTongueTwistersUris(it)
        }
    val classicalLiteratureWritingsLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.OpenMultipleDocuments()) {
            classicalLiteratureWritingsUris(it)
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
            SettingsTitle(title = "古诗文")
            MenuItem(
                title = "经典诗文",
                ratio = classicalLiteratureClassicPoemsRatio,
                launcher = classicalLiteratureClassicPoemsLauncher,
                status = classicalLiteratureClassicPoemsStatus,
                onDeleteClick = clearClassicalLiteratureClassicPoems
            )
            MenuItem(
                title = "诗文",
                ratio = classicalLiteratureWritingsRatio,
                launcher = classicalLiteratureWritingsLauncher,
                status = classicalLiteratureWritingsStatus,
                onDeleteClick = clearClassicalLiteratureWritings
            )
            MenuItem(
                title = "诗文名句",
                ratio = classicalLiteratureSentencesRatio,
                launcher = classicalLiteratureSentencesLauncher,
                status = classicalLiteratureSentencesStatus,
                onDeleteClick = clearClassicalLiteratureSentence
            )
            MenuItem(
                title = "人物",
                ratio = classicalLiteraturePeopleRatio,
                launcher = classicalLiteraturePeopleLauncher,
                status = classicalLiteraturePeopleStatus,
                onDeleteClick = clearClassicalLiteraturePeople
            )
            SettingsTitle(title = "现代汉语")
            MenuItem(
                title = "汉字",
                ratio = chineseDictionaryRatio,
                launcher = chineseDictionaryLauncher,
                status = chineseDictionaryStatus,
                onDeleteClick = clearChineseDictionaries
            )
            MenuItem(
                title = "成语",
                ratio = chineseIdiomsRatio,
                launcher = chineseIdiomsLauncher,
                status = chineseIdiomsStatus,
                onDeleteClick = clearChineseIdioms
            )
            MenuItem(
                title = "词语",
                ratio = chineseExpressionRatio,
                launcher = chineseExpressionLauncher,
                status = chineseExpressionStatus,
                onDeleteClick = clearChineseExpressions
            )
            MenuItem(
                title = "歇后语",
                ratio = chineseWisecracksRatio,
                launcher = chineseWisecracksLauncher,
                status = chineseWisecracksStatus,
                onDeleteClick = clearChineseWisecracks
            )
            MenuItem(
                title = "谚语",
                ratio = chineseProverbRatio,
                launcher = chineseProverbLauncher,
                status = chineseProverbStatus,
                onDeleteClick = clearChineseProverbs
            )
            MenuItem(
                title = "谜语",
                ratio = chineseRiddleRadio,
                launcher = chineseRiddleLauncher,
                status = chineseRiddleStatus,
                onDeleteClick = clearChineseRiddles
            )
            MenuItem(
                title = "绕口令",
                ratio = chineseTongueTwistersRatio,
                launcher = chineseTongueTwistersLauncher,
                status = chineseTongueTwistersStatus,
                onDeleteClick = clearChineseTongueTwisters
            )
            MenuItem(
                title = "对联",
                ratio = chineseAntitheticalCoupletRatio,
                launcher = chineseAntitheticalCoupletLauncher,
                status = chineseAntitheticalCoupletStatus,
                onDeleteClick = clearChineseAntitheticalCouplet
            )
            MenuItem(
                title = "歌词",
                ratio = chineseLyricRatio,
                launcher = chineseLyricLauncher,
                status = chineseLyricStatus,
                onDeleteClick = clearChineseLyrics
            )
            MenuItem(
                title = "知识卡片",
                ratio = chineseKnowledgeRatio,
                launcher = chineseKnowledgeLauncher,
                status = chineseKnowledgeStatus,
                onDeleteClick = clearChineseKnowledge
            )
            MenuItem(
                title = "句子",
                ratio = chineseQuotesRatio,
                launcher = chineseQuotesLauncher,
                status = chineseQuotesStatus,
                onDeleteClick = clearChineseQuotes
            )
            MenuItem(
                title = "诗歌",
                ratio = chineseModernPoetryRatio,
                launcher = chineseModernPoetryLauncher,
                status = chineseModernPoetryStatus,
                onDeleteClick = clearChineseModernPoetry
            )
            SettingsTitle(title = "中国")
            MenuItem(
                title = "世界文化遗产",
                ratio = chinaWorldCultureHeritageRatio,
                launcher = chinaWorldCultureHeritageLauncher,
                status = chinaWorldCultureHeritageStatus,
                onDeleteClick = clearChinaWorldCultureHeritage
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
    status: ImportStatus<Any>,
    onDeleteClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(text = title)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (ratio > 0) {
                Text(text = "${String.format("%.2f", ratio * 100)}%")
            }
            IconButton(onClick = { launcher.launch(arrayOf("application/json")) }) {
                if (status is ImportStatus.Loading) {
                    CircularProgressIndicator()
                }
                Icon(imageVector = Icons.Default.FileOpen, contentDescription = "")
            }
            IconButton(onClick = onDeleteClick) {
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = "删除数据",
                    tint = Color.Red
                )
            }
        }
    }
}