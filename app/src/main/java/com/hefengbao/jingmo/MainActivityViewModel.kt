package com.hefengbao.jingmo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(

) : ViewModel() {
    private val _showLanding: MutableStateFlow<Boolean> = MutableStateFlow(true)
    var showLanding: SharedFlow<Boolean> = _showLanding

    fun closeLanding() {
        viewModelScope.launch {
            delay(1500)
            _showLanding.value = false
        }
    }
}