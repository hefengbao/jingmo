package com.hefengbao.jingmo.ui.screen.poem

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.entity.PoemWithWriterAndTags
import com.hefengbao.jingmo.data.database.entity.WritingEntity
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import kotlinx.coroutines.launch

@Composable
fun PoemRoute(
    viewModel: PoemViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.getWriting(viewModel.id)
        when (viewModel.type) {
            "author" -> {
                viewModel.getNextId(viewModel.id, viewModel.query)
                viewModel.getPrevId(viewModel.id, viewModel.query)
            }
            "search" -> {
                viewModel.getSearchNextId(viewModel.id, viewModel.query)
                viewModel.getSearchPrevId(viewModel.id, viewModel.query)
            }
            else -> {
                viewModel.getNextId(viewModel.id)
                viewModel.getPrevId(viewModel.id)
            }
        }
    }

    val writing by viewModel.writing.collectAsState(initial = null)
    val prevId by viewModel.prevId.collectAsState(initial = null)
    val nextId by viewModel.nextId.collectAsState(initial = null)

    Screen(
        onBackClick = onBackClick,
        onCaptureClick = onCaptureClick,
        writing = writing,
        prevId = prevId,
        nextId = nextId,
        onPrevClick = {
            viewModel.getWriting(prevId!!)
            when (viewModel.type) {
                "author" -> {
                    viewModel.getNextId(prevId!!, viewModel.query)
                    viewModel.getPrevId(prevId!!, viewModel.query)
                }
                "search" -> {
                    viewModel.getSearchNextId(prevId!!, viewModel.query)
                    viewModel.getSearchPrevId(prevId!!, viewModel.query)
                }
                else -> {
                    viewModel.getNextId(prevId!!)
                    viewModel.getPrevId(prevId!!)
                }
            }
        },
        onNextClick = {
            viewModel.getWriting(nextId!!)
            when (viewModel.type) {
                "author" -> {
                    viewModel.getNextId(nextId!!, viewModel.query)
                    viewModel.getPrevId(nextId!!, viewModel.query)
                }
                "search" -> {
                    viewModel.getSearchNextId(nextId!!, viewModel.query)
                    viewModel.getSearchPrevId(nextId!!, viewModel.query)
                }
                else -> {
                    viewModel.getNextId(nextId!!)
                    viewModel.getPrevId(nextId!!)
                }
            }
        },
        setLastReadId = {
            if (viewModel.type == "read"){
                viewModel.setLastReadId(it)
            }
        }
    )
}

@Composable
private fun Screen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onCaptureClick: (Int) -> Unit,
    writing: WritingEntity?,
    prevId: Int?,
    nextId: Int?,
    onPrevClick: () -> Unit,
    onNextClick: () -> Unit,
    setLastReadId: (Int) -> Unit
) {
    writing?.let { entity ->
        setLastReadId(entity.id)

        SimpleScaffold(
            onBackClick = onBackClick,
            title = "诗文",
            actions = {
                IconButton(onClick = { onCaptureClick(entity.id) }) {
                    Icon(imageVector = Icons.Default.Photo, contentDescription = "生成图片")
                }
            }
        ) {
            val content = buildString {
                entity.clauses.mapIndexed { index, clause ->
                    /*if (clause.comments != null){
                        clause.comments.map {comment ->
                            if (comment.type == CommentType.Text.name){
                                val length = clause.content.length

                            }
                        }
                    }else{
                        append(clause.content)
                    }*/
                    append(clause.content)

                    if (clause.breakAfter != null) {
                        append("\n")
                    }
                }
            }
            Box(
                modifier =  modifier.fillMaxSize()
            ){
                SelectionContainer {
                    Column(
                        modifier = modifier
                            .fillMaxWidth()
                            .verticalScroll(
                                rememberScrollState()
                            )
                            .padding(start = 32.dp, end = 32.dp, top = 16.dp, bottom = 80.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        Column(
                            modifier = modifier
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            Text(
                                text = entity.title.content,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(text = "${entity.dynasty}.${entity.author}", style = MaterialTheme.typography.titleSmall)
                            if (entity.preface != null) {
                                Text(
                                    text = entity.preface.replace("<br />","\n"),
                                    style = MaterialTheme.typography.bodySmall,
                                    fontStyle = FontStyle.Italic
                                )
                            }
                            Text(text = content, style = MaterialTheme.typography.bodyLarge)
                        }
                        /*Column(
                            modifier = modifier
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(text = entity.type)
                            if (entity.note != null) {
                                Text(text = entity.note)
                            }
                        }
                        if (entity.comments != null) {
                            SelectionContainer {
                                Column(
                                    verticalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    entity.comments.map {
                                        it.content?.let { content ->
                                            Text(text = content)
                                        }
                                    }
                                }
                            }
                        }*/
                    }
                }
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .height(64.dp)
                        .align(
                            Alignment.BottomCenter
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(
                        onClick = onPrevClick,
                        enabled = prevId != 0
                    ) {
                        Icon(
                            modifier = modifier.padding(8.dp),
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null
                        )
                    }

                    IconButton(
                        modifier = modifier.padding(8.dp),
                        onClick = onNextClick,
                        enabled = nextId != 0
                    ) {
                        Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PoemScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onCaptureClick: (Long) -> Unit,
    poem: PoemWithWriterAndTags?,
    prevId: Long?,
    nextId: Long?,
    onPrevClick: () -> Unit,
    onNextClick: () -> Unit,
    setLastReadId: (Long) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Hidden,
            skipHiddenState = false
        )
    )

    BackHandler(scaffoldState.bottomSheetState.isVisible) {
        coroutineScope.launch {
            scaffoldState.bottomSheetState.hide()
        }
    }

    var openBottomSheetRemark by remember { mutableStateOf(false) }
    var openBottomSheetAppreciation by remember { mutableStateOf(false) }
    var openBottomSheetAuthor by remember { mutableStateOf(false) }

    poem?.let { entity ->
        LaunchedEffect(entity) {
            setLastReadId(entity.poemEntity.id)
        }
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "古诗词文")
                    },
                    navigationIcon = {
                        IconButton(onClick = onBackClick) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                        }
                    },
                    actions = {
                        IconButton(onClick = { onCaptureClick(entity.poemEntity.id) }) {
                            Icon(imageVector = Icons.Default.Photo, contentDescription = null)
                        }
                    }
                )
            },
        ) { paddingValues ->
            Box(
                modifier = modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(bottom = 56.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Card(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                    ) {
                        Column(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            SelectionContainer {
                                Text(
                                    text = entity.poemEntity.title,
                                    style = MaterialTheme.typography.titleLarge
                                )
                            }

                            SelectionContainer {
                                Text(
                                    text = "【${entity.poemEntity.dynasty}】${entity.poemEntity.writerName}",
                                    style = MaterialTheme.typography.labelMedium
                                )
                            }

                            SelectionContainer {
                                Text(
                                    text = entity.poemEntity.content,
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                        }
                    }
                }
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .height(48.dp)
                        .align(
                            Alignment.BottomCenter
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(
                        onClick = onPrevClick,
                        enabled = prevId != 0L
                    ) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }

                    OutlinedButton(onClick = {
                        coroutineScope.launch {
                            openBottomSheetRemark = true
                        }
                    }) {
                        Text(text = "注释")
                    }
                    OutlinedButton(onClick = {
                        coroutineScope.launch {
                            openBottomSheetAppreciation = true
                        }
                    }) {
                        Text(text = "赏析")
                    }
                    OutlinedButton(onClick = {
                        coroutineScope.launch {
                            openBottomSheetAuthor = true
                        }
                    }) {
                        Text(text = "作者")
                    }

                    IconButton(
                        onClick = onNextClick,
                        enabled = nextId != 0L
                    ) {
                        Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)
                    }
                }
            }
        }

        if (openBottomSheetRemark) {
            ModalBottomSheet(
                onDismissRequest = { openBottomSheetRemark = false },
                sheetState = rememberModalBottomSheetState(true)
            ) {
                Column(
                    modifier = modifier
                        .padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(text = "${entity.poemEntity.remark}")
                }
            }
        }

        if (openBottomSheetAppreciation) {
            ModalBottomSheet(
                onDismissRequest = { openBottomSheetAppreciation = false },
                sheetState = rememberModalBottomSheetState(true),
            ) {
                Column(
                    modifier = modifier
                        .padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(text = "${entity.poemEntity.shangxi}")
                }
            }
        }

        if (openBottomSheetAuthor) {
            ModalBottomSheet(
                onDismissRequest = { openBottomSheetAuthor = false },
                sheetState = rememberModalBottomSheetState(true)
            ) {
                Column(
                    modifier = modifier
                        .padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    entity.writerEntity?.let { writerEntity ->
                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(text = writerEntity.name)
                            writerEntity.simpleIntro?.let {
                                Text(text = it)
                            }
                            writerEntity.detailIntro?.let {
                                it.map { introItem ->
                                    Text(
                                        text = introItem.title,
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                    Text(text = "${introItem.content}")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}