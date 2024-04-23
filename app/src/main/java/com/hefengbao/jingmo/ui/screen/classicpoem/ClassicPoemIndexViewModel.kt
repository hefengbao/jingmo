package com.hefengbao.jingmo.ui.screen.classicpoem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.entity.ClassicPoemEntity
import com.hefengbao.jingmo.data.repository.ClassicPoemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClassicPoemIndexViewModel @Inject constructor(
    private val repository: ClassicPoemRepository
) : ViewModel() {
    private val _poem: MutableStateFlow<ClassicPoemEntity?> = MutableStateFlow(null)
    val poem: SharedFlow<ClassicPoemEntity?> = _poem

    fun getRandom() {
        viewModelScope.launch {
            repository.random().collectLatest { _poem.value = it }
        }
    }
}