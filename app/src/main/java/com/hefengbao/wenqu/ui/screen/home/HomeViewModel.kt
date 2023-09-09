package com.hefengbao.wenqu.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.wenqu.data.model.DataStatus
import com.hefengbao.wenqu.data.repository.PreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val preferenceRepository: PreferenceRepository
) : ViewModel() {
    lateinit var dataStatus: DataStatus

    init {
        viewModelScope.launch {
            dataStatus = preferenceRepository.getDataStatus().first()
        }
    }
}