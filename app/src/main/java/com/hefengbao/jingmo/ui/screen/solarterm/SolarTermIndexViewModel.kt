package com.hefengbao.jingmo.ui.screen.solarterm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.model.SolarTerm
import com.hefengbao.jingmo.data.repository.SolarTermsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SolarTermIndexViewModel @Inject constructor(
    private val repository: SolarTermsRepository
) : ViewModel() {
    private val _solarTerms: MutableStateFlow<List<SolarTerm>> = MutableStateFlow(emptyList())
    val solarTerms: SharedFlow<List<SolarTerm>> = _solarTerms
    fun getList() {
        viewModelScope.launch {
            _solarTerms.value = repository.getList()
        }
    }
}