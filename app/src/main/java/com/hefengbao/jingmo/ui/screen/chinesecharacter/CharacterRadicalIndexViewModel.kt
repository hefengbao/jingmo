/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.chinesecharacter

import androidx.lifecycle.ViewModel
import com.hefengbao.jingmo.data.repository.ChineseCharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterRadicalIndexViewModel @Inject constructor(
    repository: ChineseCharacterRepository
) : ViewModel() {
    val radicals = repository.radicals()
}