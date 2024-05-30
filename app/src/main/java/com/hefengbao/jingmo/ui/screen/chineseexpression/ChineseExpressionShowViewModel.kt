package com.hefengbao.jingmo.ui.screen.chineseexpression

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.entity.ChineseExpressionEntity
import com.hefengbao.jingmo.data.repository.ChineseExpressionRepository
import com.hefengbao.jingmo.ui.screen.chineseexpression.nav.ChineseExpressionShowArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChineseExpressionShowViewModel @Inject constructor(
    private val repository: ChineseExpressionRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val args = ChineseExpressionShowArgs(savedStateHandle)
    private val _expression: MutableStateFlow<ChineseExpressionEntity?> = MutableStateFlow(null)
    val expression: SharedFlow<ChineseExpressionEntity?> = _expression

    init {
        viewModelScope.launch {
            repository.get(args.id.toInt()).collectLatest {
                _expression.value = it
            }
        }
    }
}