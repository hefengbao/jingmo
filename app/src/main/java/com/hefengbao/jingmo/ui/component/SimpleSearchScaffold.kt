package com.hefengbao.jingmo.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleSearchScaffold(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    value: String,
    onValueChange: (String) -> Unit,
    onSearch: () -> Unit,
    hint: String = "请输入",
    textStyle: TextStyle = TextStyle.Default,
    actions: @Composable RowScope.() -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    content: @Composable () -> Unit = {},
) {
    val focus = LocalFocusManager.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    BasicTextField(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(end = 16.dp),
                        value = value,
                        onValueChange = {
                            onValueChange(it)
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Search,
                        ),
                        keyboardActions = KeyboardActions(
                            onSearch = {
                                onSearch()
                                focus.clearFocus(true)
                            }
                        ),
                        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                        textStyle = textStyle,
                        decorationBox = { innerTextField ->
                            Row(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .background(
                                        color = MaterialTheme.colorScheme.primaryContainer.copy(
                                            alpha = .3f
                                        ),
                                        shape = RoundedCornerShape(16.dp)
                                    )
                                    .padding(16.dp, 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(imageVector = Icons.Default.Search, contentDescription = null)
                                Box(
                                    modifier = modifier.weight(1f)
                                ) {
                                    if (value.isEmpty())
                                        Text(text = hint, color = Color.Gray, style = textStyle)

                                    innerTextField()
                                }
                                if (value.isNotEmpty()) {
                                    Icon(
                                        modifier = modifier
                                            .background(
                                                color = Color.Transparent,
                                                shape = RoundedCornerShape(8.dp)
                                            )
                                            .clickable {
                                                onValueChange("")
                                            },
                                        imageVector = Icons.Default.Clear,
                                        contentDescription = null
                                    )
                                }
                            }
                        }
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "返回")
                    }
                },
                actions = actions
            )
        },
        floatingActionButton = floatingActionButton
    ) { paddingValues: PaddingValues ->
        Surface(
            modifier = Modifier.padding(paddingValues)
        ) {
            content.invoke()
        }
    }
}