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
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleDown
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material.icons.filled.Download
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.R
import com.hefengbao.jingmo.common.network.Result
import com.hefengbao.jingmo.data.enums.Category
import com.hefengbao.jingmo.data.model.Dataset
import com.hefengbao.jingmo.data.model.DatasetVersion
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import com.hefengbao.jingmo.ui.screen.settings.components.SettingsTitle

@Composable
fun SyncDataRoute(
    viewModel: DataViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.getDataset()
    }

    val datasetPref by viewModel.datasetPref.collectAsState(initial = DatasetVersion())
    val datasetResult by viewModel.datasetResult.collectAsState(initial = Result.Loading)
    val chinaWorldCultureHeritageResult by viewModel.chinaWorldCultureHeritageResult.collectAsState(
        initial = SyncStatus.NonStatus
    )
    val chinaWorldCultureHeritageProgress by viewModel.chinaWorldCultureHeritageProgress.collectAsState(
        initial = 0f
    )
    val chineseAntitheticalCoupletResult by viewModel.chineseAntitheticalCoupletResult.collectAsState(
        initial = SyncStatus.NonStatus
    )
    val chineseAntitheticalCoupletProgress by viewModel.chineseAntitheticalCoupletProgress.collectAsState(
        initial = 0f
    )
    val chineseExpressionResult by viewModel.chineseExpressionResult.collectAsState(initial = SyncStatus.NonStatus)
    val chineseExpressionResultProgress by viewModel.chineseExpressionResultProgress.collectAsState(
        initial = 0f
    )
    val chineseKnowledgeResult by viewModel.chineseKnowledgeResult.collectAsState(initial = SyncStatus.NonStatus)
    val chineseKnowledgeResultProgress by viewModel.chineseKnowledgeResultProgress.collectAsState(
        initial = 0f
    )
    val chineseModernPoetryResult by viewModel.chineseModernPoetryResult.collectAsState(SyncStatus.NonStatus)
    val chineseModernPoetryResultProgress by viewModel.chineseModernPoetryResultProgress.collectAsState(
        0f
    )
    val chineseProverbResult by viewModel.chineseProverbResult.collectAsState(initial = SyncStatus.NonStatus)
    val chineseProverbResultProgress by viewModel.chineseProverbResultProgress.collectAsState(
        initial = 0f
    )
    val chineseQuoteResult by viewModel.chineseQuoteResult.collectAsState(initial = SyncStatus.NonStatus)
    val chineseQuoteResultProgress by viewModel.chineseQuoteResultProgress.collectAsState(initial = 0f)
    val chineseWisecrackResult by viewModel.chineseWisecrackResult.collectAsState(initial = SyncStatus.NonStatus)
    val chineseWisecrackResultProgress by viewModel.chineseWisecrackResultProgress.collectAsState(
        initial = 0f
    )
    val chineseCharacterResult by viewModel.chineseCharacterResult.collectAsState(initial = SyncStatus.NonStatus)
    val chineseCharacterResultProgress by viewModel.chineseCharacterResultProgress.collectAsState(
        initial = 0f
    )
    val chineseIdiomResult by viewModel.chineseIdiomResult.collectAsState(initial = SyncStatus.NonStatus)
    val chineseIdiomResultProgress by viewModel.chineseIdiomResultProgress.collectAsState(initial = 0f)
    val chineseLyricResult by viewModel.chineseLyricResult.collectAsState(initial = SyncStatus.NonStatus)
    val chineseLyricResultProgress by viewModel.chineseLyricResultProgress.collectAsState(initial = 0f)
    val chineseRiddleResult by viewModel.chineseRiddleResult.collectAsState(initial = SyncStatus.NonStatus)
    val chineseRiddleResultProgress by viewModel.chineseRiddleResultProgress.collectAsState(initial = 0f)
    val chineseTongueTwisterResult by viewModel.chineseTongueTwisterResult.collectAsState(initial = SyncStatus.NonStatus)
    val chineseTongueTwisterResultProgress by viewModel.chineseTongueTwisterResultProgress.collectAsState(
        initial = 0f
    )
    val classicalLiteratureClassicPoemResult by viewModel.classicalLiteratureClassicPoemResult.collectAsState(
        initial = SyncStatus.NonStatus
    )
    val classicalLiteratureClassicPoemResultProgress by viewModel.classicalLiteratureClassicPoemResultProgress.collectAsState(
        initial = 0f
    )
    val classicalLiteraturePeopleResult by viewModel.classicalLiteraturePeopleResult.collectAsState(
        initial = SyncStatus.NonStatus
    )
    val classicalLiteraturePeopleResultProgress by viewModel.classicalLiteraturePeopleResultProgress.collectAsState(
        initial = 0f
    )
    val classicalLiteratureSentenceResult by viewModel.classicalLiteratureSentenceResult.collectAsState(
        initial = SyncStatus.NonStatus
    )
    val classicalLiteratureSentenceResultProgress by viewModel.classicalLiteratureSentenceResultProgress.collectAsState(
        initial = 0f
    )
    val classicalLiteratureWritingResult by viewModel.classicalLiteratureWritingResult.collectAsState(
        initial = SyncStatus.NonStatus
    )
    val classicalLiteratureWritingResultProgress by viewModel.classicalLiteratureWritingResultProgress.collectAsState(
        initial = 0f
    )

    SyncDataScreen(
        onBackClick = onBackClick,
        datasetPref = datasetPref,
        datasetResult = datasetResult,
        syncChinaWorldCultureHeritage = { total: Int, version: Int ->
            viewModel.syncChinaWorldCultureHeritage(total, version)
        },
        chinaWorldCultureHeritageResult = chinaWorldCultureHeritageResult,
        chinaWorldCultureHeritageProgress = chinaWorldCultureHeritageProgress,
        syncChineseAntitheticalCouplet = { total: Int, version: Int ->
            viewModel.syncChineseAntitheticalCouplet(total, version)
        },
        chineseAntitheticalCoupletResult = chineseAntitheticalCoupletResult,
        chineseAntitheticalCoupletProgress = chineseAntitheticalCoupletProgress,
        syncChineseExpression = { total: Int, version: Int ->
            viewModel.syncChineseExpression(total, version)
        },
        chineseExpressionResult = chineseExpressionResult,
        chineseExpressionResultProgress = chineseExpressionResultProgress,
        syncChineseKnowledge = { total: Int, version: Int ->
            viewModel.syncChineseKnowledge(total, version)
        },
        chineseKnowledgeResult = chineseKnowledgeResult,
        chineseKnowledgeResultProgress = chineseKnowledgeResultProgress,
        syncChineseModernPoetry = viewModel::syncChineseModernPoetry,
        chineseModernPoetryResult = chineseModernPoetryResult,
        chineseModernPoetryResultProgress = chineseModernPoetryResultProgress,
        syncChineseProverb = { total: Int, version: Int ->
            viewModel.syncChineseProverbs(total = total, version = version)
        },
        chineseProverbResult = chineseProverbResult,
        chineseProverbResultProgress = chineseProverbResultProgress,
        syncChineseQuote = viewModel::syncChineseQuote,
        chineseQuoteResult = chineseQuoteResult,
        chineseQuoteResultProgress = chineseQuoteResultProgress,
        syncChineseWisecrack = { total: Int, version: Int ->
            viewModel.syncChineseWisecrack(total, version)
        },
        chineseWisecrackResult = chineseWisecrackResult,
        chineseWisecrackResultProgress = chineseWisecrackResultProgress,
        syncChineseCharacter = { total: Int, version: Int ->
            viewModel.syncChineseCharacter(total, version)
        },
        chineseCharacterResult = chineseCharacterResult,
        chineseCharacterResultProgress = chineseCharacterResultProgress,
        syncChineseIdiom = { total: Int, version: Int ->
            viewModel.syncChineseIdiom(total, version)
        },
        chineseIdiomResult = chineseIdiomResult,
        chineseIdiomResultProgress = chineseIdiomResultProgress,
        syncChineseLyric = { total: Int, version: Int ->
            viewModel.syncChineseLyric(total, version)
        },
        chineseLyricResult = chineseLyricResult,
        chineseLyricResultProgress = chineseLyricResultProgress,
        syncChineseRiddle = { total: Int, version: Int ->
            viewModel.syncChineseRiddle(total, version)
        },
        chineseRiddleResult = chineseRiddleResult,
        chineseRiddleResultProgress = chineseRiddleResultProgress,
        syncChineseTongueTwister = { total: Int, version: Int ->
            viewModel.syncChineseTongueTwister(total, version)
        },
        chineseTongueTwisterResult = chineseTongueTwisterResult,
        chineseTongueTwisterResultProgress = chineseTongueTwisterResultProgress,
        syncClassicalLiteraturePeople = { total: Int, version: Int ->
            viewModel.syncClassicalLiteraturePeople(total, version)
        },
        classicalLiteraturePeopleResult = classicalLiteraturePeopleResult,
        classicalLiteraturePeopleResultProgress = classicalLiteraturePeopleResultProgress,
        syncClassicalLiteratureClassicPoem = { total: Int, version: Int ->
            viewModel.syncClassicalLiteratureClassicPoem(total, version)
        },
        classicalLiteratureClassicPoemResult = classicalLiteratureClassicPoemResult,
        classicalLiteratureClassicPoemResultProgress = classicalLiteratureClassicPoemResultProgress,
        syncClassicalLiteratureSentence = { total: Int, version: Int ->
            viewModel.syncClassicalLiteratureSentence(total, version)
        },
        classicalLiteratureSentenceResult = classicalLiteratureSentenceResult,
        classicalLiteratureSentenceResultProgress = classicalLiteratureSentenceResultProgress,
        syncClassicalLiteratureWriting = { total: Int, version: Int ->
            viewModel.syncClassicalLiteratureWriting(total, version)
        },
        classicalLiteratureWritingResult = classicalLiteratureWritingResult,
        classicalLiteratureWritingResultProgress = classicalLiteratureWritingResultProgress,
        setClassicalLiteratureWritingPreviousPage = {
            viewModel.setClassicalLiteratureWritingPreviousPage(it)
        },
        setClassicalLiteratureWritingPreviousCount = { count: Int, total: Int ->
            viewModel.setClassicalLiteratureWritingPreviousCount(count, total)
        }
    )
}

@Composable
private fun SyncDataScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    datasetPref: DatasetVersion,
    datasetResult: Result<List<Dataset>>,
    syncChinaWorldCultureHeritage: (total: Int, version: Int) -> Unit,
    chinaWorldCultureHeritageResult: SyncStatus<Any>,
    chinaWorldCultureHeritageProgress: Float,
    syncChineseAntitheticalCouplet: (total: Int, version: Int) -> Unit,
    chineseAntitheticalCoupletResult: SyncStatus<Any>,
    chineseAntitheticalCoupletProgress: Float,
    syncChineseExpression: (total: Int, version: Int) -> Unit,
    chineseExpressionResult: SyncStatus<Any>,
    chineseExpressionResultProgress: Float,
    syncChineseKnowledge: (total: Int, version: Int) -> Unit,
    chineseKnowledgeResult: SyncStatus<Any>,
    chineseKnowledgeResultProgress: Float,
    syncChineseWisecrack: (total: Int, version: Int) -> Unit,
    chineseWisecrackResult: SyncStatus<Any>,
    chineseWisecrackResultProgress: Float,
    syncChineseModernPoetry: (total: Int, version: Int) -> Unit,
    chineseModernPoetryResult: SyncStatus<Any>,
    chineseModernPoetryResultProgress: Float,
    syncChineseProverb: (total: Int, version: Int) -> Unit,
    chineseProverbResult: SyncStatus<Any>,
    chineseProverbResultProgress: Float,
    syncChineseQuote: (total: Int, version: Int) -> Unit,
    chineseQuoteResult: SyncStatus<Any>,
    chineseQuoteResultProgress: Float,
    syncClassicalLiteratureClassicPoem: (total: Int, version: Int) -> Unit,
    classicalLiteratureClassicPoemResult: SyncStatus<Any>,
    classicalLiteratureClassicPoemResultProgress: Float,
    syncChineseCharacter: (total: Int, version: Int) -> Unit,
    chineseCharacterResult: SyncStatus<Any>,
    chineseCharacterResultProgress: Float,
    syncChineseIdiom: (total: Int, version: Int) -> Unit,
    chineseIdiomResult: SyncStatus<Any>,
    chineseIdiomResultProgress: Float,
    syncChineseLyric: (total: Int, version: Int) -> Unit,
    chineseLyricResult: SyncStatus<Any>,
    chineseLyricResultProgress: Float,
    syncChineseRiddle: (total: Int, version: Int) -> Unit,
    chineseRiddleResult: SyncStatus<Any>,
    chineseRiddleResultProgress: Float,
    syncChineseTongueTwister: (total: Int, version: Int) -> Unit,
    chineseTongueTwisterResult: SyncStatus<Any>,
    chineseTongueTwisterResultProgress: Float,
    syncClassicalLiteraturePeople: (total: Int, version: Int) -> Unit,
    classicalLiteraturePeopleResult: SyncStatus<Any>,
    classicalLiteraturePeopleResultProgress: Float,
    syncClassicalLiteratureSentence: (total: Int, version: Int) -> Unit,
    classicalLiteratureSentenceResult: SyncStatus<Any>,
    classicalLiteratureSentenceResultProgress: Float,
    syncClassicalLiteratureWriting: (total: Int, version: Int) -> Unit,
    classicalLiteratureWritingResult: SyncStatus<Any>,
    classicalLiteratureWritingResultProgress: Float,
    setClassicalLiteratureWritingPreviousPage: (Int) -> Unit,
    setClassicalLiteratureWritingPreviousCount: (count: Int, total: Int) -> Unit
) {
    SimpleScaffold(
        onBackClick = onBackClick,
        title = "同步数据"
    ) {
        when (datasetResult) {
            is Result.Error -> {
                Toast.makeText(
                    LocalContext.current,
                    "${datasetResult.exception?.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }

            Result.Loading -> {
                LinearProgressIndicator(modifier = modifier.fillMaxWidth())
            }

            is Result.Success -> {
                TipDialog()

                val nameList = datasetResult.data.map { it.name }
                val countList = datasetResult.data.map { it.count }
                val versionList = datasetResult.data.map { it.version }

                val items = listOf(
                    Group(
                        title = stringResource(R.string.classicalliterature),
                        items = listOf(
                            Item(
                                title = stringResource(R.string.classicalliterature_classicpoem),
                                name = Category.ClassicalLiteratureClassicPoem.model,
                                localVersion = datasetPref.classicLiteratureClassicPoemVersion,
                                status = classicalLiteratureClassicPoemResult,
                                progress = classicalLiteratureClassicPoemResultProgress,
                                onClick = { count: Int, version: Int ->
                                    syncClassicalLiteratureClassicPoem(count, version)
                                }
                            ),
                            Item(
                                title = stringResource(R.string.classicalliterature_writing),
                                name = Category.ClassicalLiteratureWriting.model,
                                localVersion = datasetPref.classicLiteratureWritingVersion,
                                status = classicalLiteratureWritingResult,
                                progress = classicalLiteratureWritingResultProgress,
                                onClick = { count: Int, version: Int ->
                                    syncClassicalLiteratureWriting(count, version)
                                }
                            ),
                            Item(
                                title = stringResource(R.string.classicalliterature_sentence),
                                name = Category.ClassicalLiteratureSentence.model,
                                localVersion = datasetPref.classicLiteratureSentenceVersion,
                                status = classicalLiteratureSentenceResult,
                                progress = classicalLiteratureSentenceResultProgress,
                                onClick = { count: Int, version: Int ->
                                    syncClassicalLiteratureSentence(count, version)
                                }
                            ),
                            Item(
                                title = stringResource(R.string.classicalliterature_people),
                                name = Category.ClassicalLiteraturePeople.model,
                                localVersion = datasetPref.classicLiteraturePeopleVersion,
                                status = classicalLiteraturePeopleResult,
                                progress = classicalLiteraturePeopleResultProgress,
                                onClick = { count: Int, version: Int ->
                                    syncClassicalLiteraturePeople(count, version)
                                }
                            )
                        ),
                    ),
                    Group(
                        title = stringResource(R.string.chinese),
                        items = listOf(
                            Item(
                                title = "汉字",
                                name = Category.ChineseCharacter.model,
                                localVersion = datasetPref.chineseCharacterVersion,
                                status = chineseCharacterResult,
                                progress = chineseCharacterResultProgress,
                                onClick = { count: Int, version: Int ->
                                    syncChineseCharacter(count, version)
                                }
                            ),
                            Item(
                                title = stringResource(R.string.chinese_expression),
                                name = Category.ChineseExpression.model,
                                localVersion = datasetPref.chineseExpressionVersion,
                                status = chineseExpressionResult,
                                progress = chineseExpressionResultProgress,
                                onClick = { count: Int, version: Int ->
                                    syncChineseExpression(count, version)
                                }
                            ),
                            Item(
                                title = stringResource(R.string.chinese_idiom),
                                name = Category.ChineseIdiom.model,
                                localVersion = datasetPref.chineseIdiomVersion,
                                status = chineseIdiomResult,
                                progress = chineseIdiomResultProgress,
                                onClick = { count: Int, version: Int ->
                                    syncChineseIdiom(count, version)
                                }
                            ),
                            Item(
                                title = stringResource(R.string.chinese_wisecrack),
                                name = Category.ChineseWisecrack.model,
                                localVersion = datasetPref.chineseWisecrackVersion,
                                status = chineseWisecrackResult,
                                progress = chineseWisecrackResultProgress,
                                onClick = { count: Int, version: Int ->
                                    syncChineseWisecrack(count, version)
                                }
                            ),
                            Item(
                                title = stringResource(R.string.chinese_riddle),
                                name = Category.ChineseRiddle.model,
                                localVersion = datasetPref.chineseRiddleVersion,
                                status = chineseRiddleResult,
                                progress = chineseRiddleResultProgress,
                                onClick = { count: Int, version: Int ->
                                    syncChineseRiddle(count, version)
                                }
                            ),
                            Item(
                                title = stringResource(R.string.chinese_proverb),
                                name = Category.ChineseProverb.model,
                                localVersion = datasetPref.chineseProverbVersion,
                                status = chineseProverbResult,
                                progress = chineseProverbResultProgress,
                                onClick = { count: Int, version: Int ->
                                    syncChineseProverb(count, version)
                                }
                            ),
                            Item(
                                title = stringResource(R.string.chinese_tonguetwister),
                                name = Category.ChineseTongueTwister.model,
                                localVersion = datasetPref.chineseTongueTwisterVersion,
                                status = chineseTongueTwisterResult,
                                progress = chineseTongueTwisterResultProgress,
                                onClick = { count: Int, version: Int ->
                                    syncChineseTongueTwister(count, version)
                                }
                            ),
                            Item(
                                title = stringResource(R.string.chinese_antitheticalcouplet),
                                name = Category.ChineseAntitheticalCouplet.model,
                                localVersion = datasetPref.chineseAntitheticalCoupletVersion,
                                status = chineseAntitheticalCoupletResult,
                                progress = chineseAntitheticalCoupletProgress,
                                onClick = { count: Int, version: Int ->
                                    syncChineseAntitheticalCouplet(count, version)
                                }
                            ),
                            Item(
                                title = stringResource(R.string.chinese_lyric),
                                name = Category.ChineseLyric.model,
                                localVersion = datasetPref.chineseLyricVersion,
                                status = chineseLyricResult,
                                progress = chineseLyricResultProgress,
                                onClick = { count: Int, version: Int ->
                                    syncChineseLyric(count, version)
                                }
                            ),
                            Item(
                                title = stringResource(R.string.chinese_knowledge),
                                name = Category.ChineseKnowledge.model,
                                localVersion = datasetPref.chineseKnowledgeVersion,
                                status = chineseKnowledgeResult,
                                progress = chineseKnowledgeResultProgress,
                                onClick = { count: Int, version: Int ->
                                    syncChineseKnowledge(count, version)
                                }
                            ),
                            Item(
                                title = stringResource(R.string.chinese_quote),
                                name = Category.ChineseQuote.model,
                                localVersion = datasetPref.chineseQuoteVersion,
                                status = chineseQuoteResult,
                                progress = chineseQuoteResultProgress,
                                onClick = { count: Int, version: Int ->
                                    syncChineseQuote(count, version)
                                }
                            ),
                            Item(
                                title = stringResource(R.string.chinese_modernpoetry),
                                name = Category.ChineseModernPoetry.model,
                                localVersion = datasetPref.chineseModernPoetryVersion,
                                status = chineseModernPoetryResult,
                                progress = chineseModernPoetryResultProgress,
                                onClick = { count: Int, version: Int ->
                                    syncChineseModernPoetry(count, version)
                                }
                            ),
                        )
                    ),
                    Group(
                        title = stringResource(R.string.china),
                        items = listOf(
                            Item(
                                title = stringResource(R.string.china_worldcultureheritage),
                                name = Category.ChinaWorldCulturalHeritage.model,
                                localVersion = datasetPref.chinaWorldCulturalHeritageVersion,
                                status = chinaWorldCultureHeritageResult,
                                progress = chinaWorldCultureHeritageProgress,
                                onClick = { count, version ->
                                    syncChinaWorldCultureHeritage(count, version)
                                }
                            )
                        )
                    )
                )

                LaunchedEffect(datasetPref.classicLiteratureWritingVersion) {
                    val writingsIndex =
                        nameList.indexOfFirst { it == Category.ClassicalLiteratureWriting.model }
                    // 诗文续传逻辑,设为上次的进度
                    if (datasetPref.classicLiteratureWritingVersion != versionList[writingsIndex]) {
                        if (datasetPref.classicLiteratureWritingCurrentCount != countList[writingsIndex]) {
                            setClassicalLiteratureWritingPreviousPage(if (datasetPref.classicLiteratureWritingCurrentPage != 0) datasetPref.classicLiteratureWritingCurrentPage else 1)
                            setClassicalLiteratureWritingPreviousCount(
                                datasetPref.classicLiteratureWritingCurrentCount,
                                countList[writingsIndex]
                            )
                        }
                    }
                }

                LazyColumn {
                    items.forEach {
                        item {
                            SettingsTitle(title = it.title)
                        }
                        itemsIndexed(
                            items = it.items
                        ) { _: Int, item: Item ->
                            val key = nameList.indexOf(item.name)

                            if (item.status is SyncStatus.Error) {
                                item.status.exception?.message
                            }

                            Item(
                                title = item.title,
                                subtitle = countList[key].toString(),
                                onClick = { item.onClick(countList[key], versionList[key]) },
                                enabled = item.localVersion != versionList[key] || item.status == SyncStatus.Loading,
                                showProgressIndicator = item.status == SyncStatus.Loading,
                                progress = item.progress,
                                error = if (item.status is SyncStatus.Error) {
                                    item.status.exception?.message
                                } else null
                            )
                        }
                    }
                }
            }
        }
    }
}

private class Group(
    val title: String,
    val items: List<Item>
)

private class Item(
    val title: String,
    val name: String,
    val localVersion: Int,
    val status: SyncStatus<Any>,
    val progress: Float,
    val onClick: (count: Int, version: Int) -> Unit,
)

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
                Text(text = "数据量大（尤其是【诗文】数据，可以考虑最后同步），预计需要数小时，建议在电量充足、流量充足的空闲时间同步数据！建议一个一个同步,不然可能因占用太多内存而被系统杀死进程。")
            }
        )
    }
}

@SuppressLint("DefaultLocale")
@Composable
private fun Item(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    onClick: () -> Unit,
    enabled: Boolean,
    showProgressIndicator: Boolean,
    progress: Float,
    error: String?,
) {
    Column(
        modifier = modifier.padding(16.dp, 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(text = title)
                Text(
                    text = subtitle,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = .5f),
                    style = MaterialTheme.typography.labelMedium
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (progress > 0 && progress != 100f) {
                    Text(text = "${String.format("%.2f", progress * 100)}%")
                }

                if (enabled) {
                    Icon(
                        imageVector = Icons.Default.ArrowCircleDown,
                        contentDescription = null,
                        tint = Color.Red
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.CheckCircleOutline,
                        contentDescription = null,
                        tint = Color.Green
                    )
                }

                IconButton(
                    onClick = onClick,
                ) {
                    if (showProgressIndicator) {
                        CircularProgressIndicator()
                    }
                    Icon(imageVector = Icons.Default.Download, contentDescription = null)
                }
            }
        }

        if (error != null) {
            Text(text = error, color = MaterialTheme.colorScheme.error)
        }
    }
}