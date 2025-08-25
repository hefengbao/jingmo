/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinese.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.model.chinese.character.Syllable
import com.hefengbao.jingmo.data.repository.chinese.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterSyllableViewModel @Inject constructor(
    repository: CharacterRepository
) : ViewModel() {
    private val _syllables: MutableStateFlow<List<Syllable>> = MutableStateFlow(emptyList())
    val syllables: SharedFlow<List<Syllable>> = _syllables

    init {
        viewModelScope.launch {
            _syllables.value = repository.syllables()
        }
    }
}