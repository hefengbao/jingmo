/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.link

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
class LinkIndexViewModel @Inject constructor(
    private val repository: LinksRepository
) : ViewModel() {
    private val _links: MutableStateFlow<List<Link>> = MutableStateFlow(emptyList())
    val links: StateFlow<List<Link>> = _links

    fun getList() {
        viewModelScope.launch {
            _links.value = repository.list()
        }
    }
}