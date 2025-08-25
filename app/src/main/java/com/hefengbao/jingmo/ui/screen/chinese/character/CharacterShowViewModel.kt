/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.character

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.base.BaseViewModel
import com.hefengbao.jingmo.data.repository.BookmarkRepository
import com.hefengbao.jingmo.data.repository.chinese.CharacterRepository
import com.hefengbao.jingmo.ui.screen.chinese.character.nav.CharacterShowArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CharacterShowViewModel @Inject constructor(
    private val repository: CharacterRepository,
    private val bookmarkRepository: BookmarkRepository,
    savedStateHandle: SavedStateHandle
) : BaseViewModel(bookmarkRepository) {
    private val args = CharacterShowArgs(savedStateHandle)

    @OptIn(ExperimentalCoroutinesApi::class)
    val characterEntity = MutableStateFlow(args.id).flatMapLatest {
        repository.get(it.toInt())
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )
}