/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.component

import android.Manifest
import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.TextFormat
import androidx.compose.material.icons.outlined.SaveAlt
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetValue
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.hefengbao.jingmo.R
import com.hefengbao.jingmo.common.util.FileUtil
import com.hefengbao.jingmo.data.model.traditionalculture.ChineseColor
import dev.shreyaspatil.capturable.capturable
import dev.shreyaspatil.capturable.controller.CaptureController
import dev.shreyaspatil.capturable.controller.rememberCaptureController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalPermissionsApi::class,
    ExperimentalComposeUiApi::class,
    ExperimentalComposeApi::class,
)
@Composable
fun CaptureScaffold(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    colors: List<ChineseColor>,
    onTextColorChange: (String) -> Unit,
    onBackgroundColorChange: (String) -> Unit,
    content: @Composable () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    val captureController = rememberCaptureController()

    val context = LocalContext.current

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

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        TopBar(onBackClick)
        Column(
            modifier = Modifier.capturable(captureController)
        ) {
            content.invoke()
        }
        BottomBar(
            context = context,
            captureController = captureController,
            coroutineScope = coroutineScope,
            colors = colors,
            onTextColorChange = onTextColorChange,
            onBackgroundColorChange = onBackgroundColorChange
        )
    }

    /*BottomSheetScaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.capture))
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            val bitmapAsync = captureController.captureAsync()

                            try {
                                coroutineScope.launch {
                                    bitmap = bitmapAsync.await()
                                }
                            } catch (error: Throwable) {
                                Toast.makeText(
                                    context, "${error.message}", Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.SaveAlt,
                            contentDescription = stringResource(R.string.save)
                        )
                    }
                    IconButton(
                        onClick = {
                            coroutineScope.launch {
                                scaffoldState.bottomSheetState.expand()
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Settings,
                            contentDescription = stringResource(R.string.settings)
                        )
                    }
                }
            )
        },
        sheetPeekHeight = 0.dp,
        scaffoldState = scaffoldState,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        sheetContent = {
            LazyColumn(
                modifier = modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Text(
                        text = stringResource(R.string.text_color),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                item {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Icon(
                            modifier = modifier
                                .background(Color.LightGray)
                                .clickable {
                                    onTextColorChange("white")
                                }
                                .padding(16.dp),
                            imageVector = Icons.Default.TextFormat,
                            tint = Color.White,
                            contentDescription = stringResource(R.string.white)
                        )
                        Icon(
                            modifier = modifier
                                .background(Color.LightGray)
                                .clickable {
                                    onTextColorChange("black")
                                }
                                .padding(16.dp),
                            imageVector = Icons.Default.TextFormat,
                            tint = Color.Black,
                            contentDescription = stringResource(R.string.black)
                        )
                    }
                }
                item {
                    Text(
                        text = stringResource(R.string.background_color),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                item {
                    LazyHorizontalGrid(
                        modifier = modifier.height(400.dp),
                        rows = GridCells.Fixed(8),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        content = {
                            itemsIndexed(
                                items = colors,
                                key = { _, item -> item.id },
                            ) { _, item ->
                                Box(
                                    modifier = modifier
                                        .background(Color(item.hex.toColorInt()))
                                        .width(100.dp)
                                        .fillMaxHeight()
                                        .clickable {
                                            onBackgroundColorChange(item.hex)
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = item.name,
                                    )
                                }
                            }
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = modifier
                .capturable(captureController)
                .fillMaxWidth()
                .height(10000.dp)
                .padding(paddingValues)
                .background(Color(backgroundColor.toColorInt())),
        ) {
            content.invoke()
        }
    }
    */
}

@Composable
fun TopBar(
    onBackClick: () -> Unit,
) {
    Row(
        modifier = Modifier.padding(16.dp, 32.dp, 16.dp, 16.dp)
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(R.string.back)
            )
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun BottomBar(
    context: Context,
    captureController: CaptureController,
    coroutineScope: CoroutineScope,
    colors: List<ChineseColor>,
    onTextColorChange: (String) -> Unit,
    onBackgroundColorChange: (String) -> Unit
) {
    var bitmap: ImageBitmap? by remember { mutableStateOf(null) }
    val writeStoragePermissionState = rememberPermissionState(
        permission = Manifest.permission.WRITE_EXTERNAL_STORAGE,
    )
    var shouldShowRationale by rememberSaveable { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }

    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )

    Row(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        IconButton(onClick = { showBottomSheet = true }) {
            Icon(
                imageVector = Icons.Outlined.Settings,
                contentDescription = stringResource(R.string.settings)
            )
        }

        IconButton(
            onClick = {
                val bitmapAsync = captureController.captureAsync()

                try {
                    coroutineScope.launch {
                        bitmap = bitmapAsync.await()
                    }
                } catch (error: Throwable) {
                    Toast.makeText(
                        context, "${error.message}", Toast.LENGTH_LONG
                    ).show()
                }
            }
        ) {
            Icon(
                imageVector = Icons.Outlined.SaveAlt,
                contentDescription = stringResource(R.string.save)
            )
        }
    }

    LaunchedEffect(shouldShowRationale) {
        if (shouldShowRationale) {
            val result = snackbarHostState.showSnackbar(
                message = context.getString(R.string.permission_image),
                actionLabel = context.getString(R.string.grant_permissions),
                duration = SnackbarDuration.Indefinite,
                withDismissAction = true
            )

            shouldShowRationale = when (result) {
                SnackbarResult.Dismissed -> {
                    false
                }

                SnackbarResult.ActionPerformed -> {
                    writeStoragePermissionState.launchPermissionRequest()
                    false
                }
            }
        }
    }

    bitmap?.let { image ->
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            if (writeStoragePermissionState.status.isGranted) {
                saveImage(context, image)
            } else {
                if (writeStoragePermissionState.status.shouldShowRationale) {
                    shouldShowRationale = true
                } else {
                    writeStoragePermissionState.launchPermissionRequest()
                }
            }
        } else {
            saveImage(context, image)
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            modifier = Modifier.fillMaxHeight(),
            sheetState = sheetState,
            onDismissRequest = { showBottomSheet = false }
        ) {
            LazyColumn(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Text(
                        text = stringResource(R.string.text_color),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                item {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Icon(
                            modifier = Modifier
                                .background(Color.LightGray)
                                .clickable {
                                    onTextColorChange("#FFFFFF")
                                }
                                .padding(16.dp),
                            imageVector = Icons.Default.TextFormat,
                            tint = Color.White,
                            contentDescription = stringResource(R.string.white)
                        )
                        Icon(
                            modifier = Modifier
                                .background(Color.LightGray)
                                .clickable {
                                    onTextColorChange("#000000")
                                }
                                .padding(16.dp),
                            imageVector = Icons.Default.TextFormat,
                            tint = Color.Black,
                            contentDescription = stringResource(R.string.black)
                        )
                    }
                }
                item {
                    Text(
                        text = stringResource(R.string.background_color),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                item {
                    LazyHorizontalGrid(
                        modifier = Modifier.height(400.dp),
                        rows = GridCells.Fixed(8),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        content = {
                            itemsIndexed(
                                items = colors,
                                key = { _, item -> item.id },
                            ) { _, item ->
                                Box(
                                    modifier = Modifier
                                        .background(Color(item.hex.toColorInt()))
                                        .width(100.dp)
                                        .fillMaxHeight()
                                        .clickable {
                                            onBackgroundColorChange(item.hex)
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = item.name,
                                    )
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}

private fun saveImage(context: Context, bitmap: ImageBitmap) {
    val localDateTime = LocalDateTime.now()
    val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss")
    if (FileUtil.saveImageToStorage(
            context,
            bitmap.asAndroidBitmap(),
            "jingmo_${dateTimeFormatter.format(localDateTime)}.jpg"
        )
    ) {
        Toast.makeText(context, R.string.save_success, Toast.LENGTH_SHORT).show()
    } else {
        Toast.makeText(context, R.string.save_fail, Toast.LENGTH_SHORT).show()
    }
}