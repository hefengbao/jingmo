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
import com.hefengbao.jingmo.data.repository.chinese.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterRadicalViewModel @Inject constructor(
    repository: CharacterRepository
) : ViewModel() {
    val radicals = repository.radicals()
}