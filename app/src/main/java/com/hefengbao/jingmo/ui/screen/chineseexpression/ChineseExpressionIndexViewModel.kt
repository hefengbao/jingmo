package com.hefengbao.jingmo.ui.screen.chineseexpression

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.entity.ChineseExpressionEntity
import com.hefengbao.jingmo.data.repository.ChineseExpressionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChineseExpressionIndexViewModel @Inject constructor(
    private val repository: ChineseExpressionRepository
) : ViewModel() {
    private val _expression: MutableStateFlow<ChineseExpressionEntity?> = MutableStateFlow(null)
    val expression: SharedFlow<ChineseExpressionEntity?> = _expression

    fun getRandom() {
        viewModelScope.launch {
            repository.random().collectLatest {
                _expression.value = it
            }
        }
    }
}