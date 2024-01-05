package com.hefengbao.jingmo.ui.screen.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.common.network.Result
import com.hefengbao.jingmo.data.repository.NetworkDatasourceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataViewModel @Inject constructor(
    private val repository: NetworkDatasourceRepository
) : ViewModel() {
    private val _syncRiddlesResult: MutableStateFlow<Result<Any>?> = MutableStateFlow(null)
    val syncRiddlesResult: SharedFlow<Result<Any>?> = _syncRiddlesResult
    fun syncRiddles(){
        _syncRiddlesResult.value = Result.Loading
        viewModelScope.launch {
            _syncRiddlesResult.value = repository.syncRiddle()
        }
    }
}