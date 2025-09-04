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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.R
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import com.hefengbao.jingmo.ui.screen.settings.components.SettingsTitle

@Composable
fun ImportDataRoute(
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
    val chineseQuotesRatio by viewModel.chineseQuoteRatio.collectAsState()
    val chineseRiddleRatio by viewModel.chineseRiddleRatio.collectAsState()
    val chineseDictionaryRatio by viewModel.chineseCharacterRatio.collectAsState()
    val chineseIdiomsRatio by viewModel.chineseIdiomRatio.collectAsState()
    val chineseLyricRatio by viewModel.chineseLyricRatio.collectAsState()
    val chineseTongueTwistersRatio by viewModel.chineseTongueTwisterRatio.collectAsState()

    val classicalLiteraturePeopleRatio by viewModel.classicalLiteraturePeopleRatio.collectAsState()
    val classicalLiteratureClassicPoemsRatio by viewModel.classicalLiteratureClassicPoemRatio.collectAsState()
    val classicalLiteratureSentenceRatio by viewModel.classicalLiteratureSentenceRatio.collectAsState()
    val classicalLiteratureWritingsRatio by viewModel.classicalLiteratureWritingRatio.collectAsState()

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
    val chineseQuotesStatus by viewModel.chineseQuoteStatus.collectAsState(initial = ImportStatus.Loading)
    val chineseRiddleStatus by viewModel.chineseRiddleStatus.collectAsState(initial = ImportStatus.Loading)
    val chineseDictionaryStatus by viewModel.chineseDictionaryStatus.collectAsState(initial = ImportStatus.Loading)
    val chineseIdiomsStatus by viewModel.chineseIdiomStatus.collectAsState(initial = ImportStatus.Loading)
    val chineseLyricStatus by viewModel.chineseLyricStatus.collectAsState(initial = ImportStatus.Loading)
    val chineseTongueTwistersStatus by viewModel.chineseTongueTwisterStatus.collectAsState(initial = ImportStatus.Loading)
    val classicalLiteratureClassicPoemsStatus by viewModel.classicalLiteratureClassicPoemStatus.collectAsState(
        initial = ImportStatus.Loading
    )
    val classicalLiteratureSentencesStatus by viewModel.classicalLiteratureSentenceStatus.collectAsState(
        initial = ImportStatus.Loading
    )
    val classicalLiteraturePeopleStatus by viewModel.classicalLiteraturePeopleStatus.collectAsState(
        initial = ImportStatus.Loading
    )
    val classicalLiteratureWritingsStatus by viewModel.classicalLiteratureWritingStatus.collectAsState(
        initial = ImportStatus.Loading
    )

    ImportDataScreen(
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
        chineseDictionaryUris = { viewModel.chineseCharacters(it) },
        clearChineseDictionaries = viewModel::clearChineseCharacters,
        chineseIdiomsRatio = chineseIdiomsRatio,
        chineseIdiomsStatus = chineseIdiomsStatus,
        chineseIdiomsUris = { viewModel.chineseIdioms(it) },
        clearChineseIdioms = viewModel::clearChineseIdioms,
        chineseLyricRatio = chineseLyricRatio,
        chineseLyricStatus = chineseLyricStatus,
        chineseLyricUris = { viewModel.chineseLyrics(it) },
        clearChineseLyrics = viewModel::clearChineseLyrics,
        chineseTongueTwistersRatio = chineseTongueTwistersRatio,
        chineseTongueTwistersStatus = chineseTongueTwistersStatus,
        chineseTongueTwistersUris = { viewModel.chineseTongueTwisters(it) },
        clearChineseTongueTwisters = viewModel::clearChineseTongueTwisters,
        classicalLiteratureClassicPoemsRatio = classicalLiteratureClassicPoemsRatio,
        classicalLiteratureClassicPoemsStatus = classicalLiteratureClassicPoemsStatus,
        classicalLiteratureClassicPoemsUris = { viewModel.classicalLiteratureClassicPoems(it) },
        clearClassicalLiteratureClassicPoems = viewModel::clearClassicalLiteratureClassicPoems,
        classicalLiteraturePeopleRatio = classicalLiteraturePeopleRatio,
        classicalLiteraturePeopleStatus = classicalLiteraturePeopleStatus,
        classicalLiteraturePeopleUris = { viewModel.classicalLiteraturePeople(it) },
        clearClassicalLiteraturePeople = viewModel::clearClassicalLiteraturePeople,
        classicalLiteratureSentencesRatio = classicalLiteratureSentenceRatio,
        classicalLiteratureSentencesStatus = classicalLiteratureSentencesStatus,
        classicalLiteratureSentencesUris = { viewModel.classicalLiteratureSentences(it) },
        clearClassicalLiteratureSentence = viewModel::clearClassicalLiteratureSentences,
        classicalLiteratureWritingsRatio = classicalLiteratureWritingsRatio,
        classicalLiteratureWritingsStatus = classicalLiteratureWritingsStatus,
        classicalLiteratureWritingsUris = { viewModel.classicalLiteratureWritings(it) },
        clearClassicalLiteratureWritings = viewModel::clearClassicalLiteratureWritings
    )
}

@Composable
private fun ImportDataScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    chinaWorldCultureHeritageRatio: Int,
    chinaWorldCultureHeritageStatus: ImportStatus<Any>,
    chinaWorldCultureHeritageUris: (List<Uri>) -> Unit,
    clearChinaWorldCultureHeritage: () -> Unit,
    chineseAntitheticalCoupletRatio: Int,
    chineseAntitheticalCoupletStatus: ImportStatus<Any>,
    chineseAntitheticalCoupletUris: (List<Uri>) -> Unit,
    clearChineseAntitheticalCouplet: () -> Unit,
    chineseExpressionRatio: Int,
    chineseExpressionStatus: ImportStatus<Any>,
    chineseExpressionUris: (List<Uri>) -> Unit,
    clearChineseExpressions: () -> Unit,
    chineseKnowledgeRatio: Int,
    chineseKnowledgeStatus: ImportStatus<Any>,
    chineseKnowledgeUris: (List<Uri>) -> Unit,
    clearChineseKnowledge: () -> Unit,
    chineseModernPoetryRatio: Int,
    chineseModernPoetryStatus: ImportStatus<Any>,
    chineseModernPoetryUris: (List<Uri>) -> Unit,
    clearChineseModernPoetry: () -> Unit,
    chineseProverbRatio: Int,
    chineseProverbStatus: ImportStatus<Any>,
    chineseProverbUris: (List<Uri>) -> Unit,
    clearChineseProverbs: () -> Unit,
    chineseQuotesRatio: Int,
    chineseQuotesStatus: ImportStatus<Any>,
    chineseQuotesUris: (List<Uri>) -> Unit,
    clearChineseQuotes: () -> Unit,
    chineseWisecracksRatio: Int,
    chineseWisecracksStatus: ImportStatus<Any>,
    chineseWisecracksUris: (List<Uri>) -> Unit,
    clearChineseWisecracks: () -> Unit,
    chineseRiddleRadio: Int,
    chineseRiddleStatus: ImportStatus<Any>,
    chineseRiddleUris: (List<Uri>) -> Unit,
    clearChineseRiddles: () -> Unit,
    chineseDictionaryRatio: Int,
    chineseDictionaryStatus: ImportStatus<Any>,
    chineseDictionaryUris: (List<Uri>) -> Unit,
    clearChineseDictionaries: () -> Unit,
    chineseIdiomsRatio: Int,
    chineseIdiomsStatus: ImportStatus<Any>,
    chineseIdiomsUris: (List<Uri>) -> Unit,
    clearChineseIdioms: () -> Unit,
    chineseLyricRatio: Int,
    chineseLyricStatus: ImportStatus<Any>,
    chineseLyricUris: (List<Uri>) -> Unit,
    clearChineseLyrics: () -> Unit,
    chineseTongueTwistersRatio: Int,
    chineseTongueTwistersStatus: ImportStatus<Any>,
    chineseTongueTwistersUris: (List<Uri>) -> Unit,
    clearChineseTongueTwisters: () -> Unit,
    classicalLiteratureClassicPoemsRatio: Int,
    classicalLiteratureClassicPoemsStatus: ImportStatus<Any>,
    classicalLiteratureClassicPoemsUris: (List<Uri>) -> Unit,
    clearClassicalLiteratureClassicPoems: () -> Unit,
    classicalLiteraturePeopleRatio: Int,
    classicalLiteraturePeopleStatus: ImportStatus<Any>,
    classicalLiteraturePeopleUris: (List<Uri>) -> Unit,
    clearClassicalLiteraturePeople: () -> Unit,
    classicalLiteratureSentencesRatio: Int,
    classicalLiteratureSentencesStatus: ImportStatus<Any>,
    classicalLiteratureSentencesUris: (List<Uri>) -> Unit,
    clearClassicalLiteratureSentence: () -> Unit,
    classicalLiteratureWritingsRatio: Int,
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
            IconButton(onClick = { uriHandler.openUri("https://www.bilibili.com/video/BV1zqh9z1EZj") }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.HelpOutline,
                    contentDescription = "帮助"
                )
            }
        }
    ) {
        TipDialog()

        Column(
            modifier = modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            SettingsTitle(title = stringResource(R.string.classicalliterature))
            MenuItem(
                title = stringResource(R.string.classicalliterature_classicpoem),
                ratio = classicalLiteratureClassicPoemsRatio,
                launcher = classicalLiteratureClassicPoemsLauncher,
                status = classicalLiteratureClassicPoemsStatus,
                onDeleteClick = clearClassicalLiteratureClassicPoems
            )
            MenuItem(
                title = stringResource(R.string.classicalliterature_writing),
                ratio = classicalLiteratureWritingsRatio,
                launcher = classicalLiteratureWritingsLauncher,
                status = classicalLiteratureWritingsStatus,
                onDeleteClick = clearClassicalLiteratureWritings
            )
            MenuItem(
                title = stringResource(R.string.classicalliterature_sentence),
                ratio = classicalLiteratureSentencesRatio,
                launcher = classicalLiteratureSentencesLauncher,
                status = classicalLiteratureSentencesStatus,
                onDeleteClick = clearClassicalLiteratureSentence
            )
            MenuItem(
                title = stringResource(R.string.classicalliterature_people),
                ratio = classicalLiteraturePeopleRatio,
                launcher = classicalLiteraturePeopleLauncher,
                status = classicalLiteraturePeopleStatus,
                onDeleteClick = clearClassicalLiteraturePeople
            )
            SettingsTitle(title = stringResource(R.string.chinese))
            MenuItem(
                title = stringResource(R.string.chinese_character),
                ratio = chineseDictionaryRatio,
                launcher = chineseDictionaryLauncher,
                status = chineseDictionaryStatus,
                onDeleteClick = clearChineseDictionaries
            )
            MenuItem(
                title = stringResource(R.string.chinese_idiom),
                ratio = chineseIdiomsRatio,
                launcher = chineseIdiomsLauncher,
                status = chineseIdiomsStatus,
                onDeleteClick = clearChineseIdioms
            )
            MenuItem(
                title = stringResource(R.string.chinese_expression),
                ratio = chineseExpressionRatio,
                launcher = chineseExpressionLauncher,
                status = chineseExpressionStatus,
                onDeleteClick = clearChineseExpressions
            )
            MenuItem(
                title = stringResource(R.string.chinese_wisecrack),
                ratio = chineseWisecracksRatio,
                launcher = chineseWisecracksLauncher,
                status = chineseWisecracksStatus,
                onDeleteClick = clearChineseWisecracks
            )
            MenuItem(
                title = stringResource(R.string.chinese_proverb),
                ratio = chineseProverbRatio,
                launcher = chineseProverbLauncher,
                status = chineseProverbStatus,
                onDeleteClick = clearChineseProverbs
            )
            MenuItem(
                title = stringResource(R.string.chinese_riddle),
                ratio = chineseRiddleRadio,
                launcher = chineseRiddleLauncher,
                status = chineseRiddleStatus,
                onDeleteClick = clearChineseRiddles
            )
            MenuItem(
                title = stringResource(R.string.chinese_tonguetwister),
                ratio = chineseTongueTwistersRatio,
                launcher = chineseTongueTwistersLauncher,
                status = chineseTongueTwistersStatus,
                onDeleteClick = clearChineseTongueTwisters
            )
            MenuItem(
                title = stringResource(R.string.chinese_antitheticalcouplet),
                ratio = chineseAntitheticalCoupletRatio,
                launcher = chineseAntitheticalCoupletLauncher,
                status = chineseAntitheticalCoupletStatus,
                onDeleteClick = clearChineseAntitheticalCouplet
            )
            MenuItem(
                title = stringResource(R.string.chinese_lyric),
                ratio = chineseLyricRatio,
                launcher = chineseLyricLauncher,
                status = chineseLyricStatus,
                onDeleteClick = clearChineseLyrics
            )
            MenuItem(
                title = stringResource(R.string.chinese_quote),
                ratio = chineseQuotesRatio,
                launcher = chineseQuotesLauncher,
                status = chineseQuotesStatus,
                onDeleteClick = clearChineseQuotes
            )
            MenuItem(
                title = stringResource(R.string.chinese_modernpoetry),
                ratio = chineseModernPoetryRatio,
                launcher = chineseModernPoetryLauncher,
                status = chineseModernPoetryStatus,
                onDeleteClick = clearChineseModernPoetry
            )
            MenuItem(
                title = stringResource(R.string.chinese_knowledge),
                ratio = chineseKnowledgeRatio,
                launcher = chineseKnowledgeLauncher,
                status = chineseKnowledgeStatus,
                onDeleteClick = clearChineseKnowledge
            )
            SettingsTitle(title = stringResource(R.string.china))
            MenuItem(
                title = stringResource(R.string.china_worldcultureheritage),
                ratio = chinaWorldCultureHeritageRatio,
                launcher = chinaWorldCultureHeritageLauncher,
                status = chinaWorldCultureHeritageStatus,
                onDeleteClick = clearChinaWorldCultureHeritage
            )
        }
    }
}

@Composable
private fun TipDialog() {
    var showDialog by rememberSaveable { mutableStateOf(true) }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                Button(onClick = { showDialog = false }) {
                    Text(text = "知道了")
                }
            },
            title = {
                Text(text = "提示")
            },
            text = {
                Text(text = "下载最新版本 app，在网盘下载最新数据，可以避免一些不必要的错误。根据条目选择对应文件夹下的文件，不然会导致 app 奔溃，如果文件夹下的文件比较多，那就找到全选的按钮（菜单）点击全选。导入数据时会占用大量内存，可以提前清理一下内存占用。演示视频可点击右上角帮助按钮查看。")
            }
        )
    }
}

@SuppressLint("DefaultLocale")
@Composable
private fun MenuItem(
    modifier: Modifier = Modifier,
    title: String,
    ratio: Int,
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
                Text(text = "$ratio 条")
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