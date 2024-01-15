package com.hefengbao.jingmo.ui.component

import android.Manifest
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.SaveAlt
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.TextFormat
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetValue
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.hefengbao.jingmo.R
import com.hefengbao.jingmo.common.util.FileUtil
import com.hefengbao.jingmo.data.model.ChineseColor
import dev.shreyaspatil.capturable.Capturable
import dev.shreyaspatil.capturable.controller.rememberCaptureController
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun CaptureScaffold(
    modifier: Modifier = Modifier,
    colors: List<ChineseColor>,
    onBackClick: () -> Unit,
    defaultColor: Color,
    onColorChange: (Color) -> Unit,
    defaultBackgroundColor: String,
    onBackgroundColorChange: (String) -> Unit,
    content: @Composable (color: Color, backgroundColor: String) -> Unit
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

    var backgroundColor by remember { mutableStateOf(defaultBackgroundColor) }

    var color by remember { mutableStateOf(defaultColor) }

    val writeStoragePermissionState = rememberPermissionState(
        permission = Manifest.permission.WRITE_EXTERNAL_STORAGE,
    )
    var shouldShowRationale by rememberSaveable { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }

    BackHandler(scaffoldState.bottomSheetState.isVisible) {
        coroutineScope.launch {
            scaffoldState.bottomSheetState.hide()
        }
    }

    BottomSheetScaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "生成图片")
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        // Android 10 以下需要存储权限
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
                            if (writeStoragePermissionState.status.isGranted) {
                                captureController.capture()
                            } else {
                                if (writeStoragePermissionState.status.shouldShowRationale) {
                                    shouldShowRationale = true
                                } else {
                                    writeStoragePermissionState.launchPermissionRequest()
                                }
                            }
                        } else {
                            captureController.capture()
                        }
                    }) {
                        Icon(imageVector = Icons.Default.SaveAlt, contentDescription = null)
                    }
                    IconButton(onClick = {
                        coroutineScope.launch {
                            scaffoldState.bottomSheetState.expand()
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Settings, contentDescription = null)
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
                    Text(text = "文字颜色", style = MaterialTheme.typography.titleMedium)
                }
                item {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Icon(
                            modifier = modifier
                                .background(Color.LightGray)
                                .clickable {
                                    color = Color.White
                                    onColorChange(Color.White)
                                }
                                .padding(16.dp),
                            imageVector = Icons.Default.TextFormat,
                            tint = Color.White,
                            contentDescription = null
                        )
                        Icon(
                            modifier = modifier
                                .background(Color.LightGray)
                                .clickable {
                                    color = Color.Black
                                    onColorChange(Color.Black)
                                }
                                .padding(16.dp),
                            imageVector = Icons.Default.TextFormat,
                            tint = Color.Black,
                            contentDescription = null
                        )
                    }
                }
                item {
                    Text(text = "背景颜色", style = MaterialTheme.typography.titleMedium)
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
                                            backgroundColor = item.hex
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
        },
    ) { paddingValues ->
        Capturable(
            controller = captureController,
            onCaptured = { imageBitmap: ImageBitmap?, throwable: Throwable? ->
                if (imageBitmap != null) {
                    val localDateTime = LocalDateTime.now()
                    val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss")

                    if (FileUtil.saveImageToStorage(
                            context,
                            imageBitmap.asAndroidBitmap(),
                            "jingmo_${dateTimeFormatter.format(localDateTime)}.jpg"
                        )
                    ) {
                        Toast.makeText(context, "保存成功", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "保存失败", Toast.LENGTH_SHORT).show()
                    }
                }
                if (throwable != null) {
                    Log.e("CaptureScaffold", throwable.message.toString())
                    Toast.makeText(context, "保存失败 ${throwable.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            },
            modifier = modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(
                    rememberScrollState()
                )
        ) {
            Column(
                modifier = modifier
                    .background(Color(backgroundColor.toColorInt()))
                    .fillMaxWidth()
                    .padding(32.dp, 64.dp)
            ) {
                Column(
                    modifier = modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    content(color, backgroundColor)
                    Image(
                        modifier = modifier.size(64.dp),
                        painter = painterResource(
                            id = if (color == Color.White) {
                                R.drawable.ic_nowinlife_white
                            } else {
                                R.drawable.ic_nowinlife_black
                            }
                        ),
                        contentDescription = null
                    )
                }
            }
        }
    }

    LaunchedEffect(shouldShowRationale) {
        if (shouldShowRationale) {
            val result = snackbarHostState.showSnackbar(
                message = "保存图片到手机需要授予文件读写权限，请授予权限",
                actionLabel = "开始授权",
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
}