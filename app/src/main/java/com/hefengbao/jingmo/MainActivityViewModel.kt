package com.hefengbao.jingmo

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.repository.PreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    preferenceRepository: PreferenceRepository
) : ViewModel() {
    init {
        // 只是为了把 preference 数据加载到内存
        viewModelScope.launch {
            preferenceRepository.getAppStatus().collectLatest {  }
            preferenceRepository.getDatasetStatus().collectLatest {  }
            preferenceRepository.getReadStatus().collectLatest {  }
        }
    }

    private val _showLanding: MutableStateFlow<Boolean> = MutableStateFlow(true)
    var showLanding: SharedFlow<Boolean> = _showLanding

    fun closeLanding() {
        viewModelScope.launch {
            delay(1500)
            _showLanding.value = false
        }
    }
}