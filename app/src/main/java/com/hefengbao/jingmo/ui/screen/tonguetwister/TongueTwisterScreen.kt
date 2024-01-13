package com.hefengbao.jingmo.ui.screen.tonguetwister

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.entity.TongueTwisterEntity
import com.hefengbao.jingmo.ui.component.SimpleScaffold

@Composable
fun TongueTwisterRoute(
    viewModel: TongueTwisterViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.getTongueTwister(viewModel.id)
        viewModel.getPrevId(viewModel.id)
        viewModel.getNextId(viewModel.id)
    }

    val prevId by viewModel.prevId.collectAsState(initial = null)
    val nextId by viewModel.nextId.collectAsState(initial = null)
    val tongueTwister by viewModel.tongueTwister.collectAsState(initial = null)

    TongueTwisterScreen(
        onBackClick = onBackClick,
        tongueTwister = tongueTwister,
        prevId = prevId,
        nextId = nextId,
        onPrevClick = {
            viewModel.getTongueTwister(prevId!!)
            viewModel.getPrevId(prevId!!)
            viewModel.getNextId(prevId!!)
        },
        onNextClick = {
            viewModel.getTongueTwister(nextId!!)
            viewModel.getPrevId(nextId!!)
            viewModel.getNextId(nextId!!)
        },
        setLastReadId = {
            viewModel.setLastReadId(it)
        }
    )
}

@Composable
private fun TongueTwisterScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    tongueTwister: TongueTwisterEntity?,
    onPrevClick: () -> Unit,
    onNextClick: () -> Unit,
    prevId: Int?,
    nextId: Int?,
    setLastReadId: (Int) -> Unit
) {
    tongueTwister?.let { entity ->
        LaunchedEffect(entity) {
            setLastReadId(entity.id)
        }
        SimpleScaffold(
            onBackClick = onBackClick,
            title = entity.title
        ) {
            Box(
                modifier = modifier
                    .fillMaxSize()
            ) {
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(bottom = 56.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        modifier = modifier.padding(16.dp),
                        text = entity.content
                    )
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
                        enabled = prevId != 0
                    ) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }

                    IconButton(
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