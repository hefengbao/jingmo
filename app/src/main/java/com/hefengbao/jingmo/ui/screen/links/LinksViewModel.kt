package com.hefengbao.jingmo.ui.screen.links

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.model.Link
import com.hefengbao.jingmo.data.repository.LinksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LinksViewModel @Inject constructor(
    private val repository: LinksRepository
) : ViewModel() {
    private val _links: MutableStateFlow<List<Link>> = MutableStateFlow(emptyList())
    val links: StateFlow<List<Link>> = _links

    fun getList() {
        viewModelScope.launch {
            _links.value = repository.getList()
        }
    }
}