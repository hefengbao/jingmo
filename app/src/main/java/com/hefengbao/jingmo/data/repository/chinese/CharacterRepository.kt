/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.repository.chinese

import androidx.paging.PagingData
import com.hefengbao.jingmo.data.database.entity.chinese.CharacterEntity
import com.hefengbao.jingmo.data.model.chinese.character.Radical
import com.hefengbao.jingmo.data.model.chinese.character.Stroke
import com.hefengbao.jingmo.data.model.chinese.character.Syllable
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun syllables(): List<Syllable>
    fun radicals(): List<Radical>
    fun strokes(): List<Stroke>
    fun getRandom(): Flow<CharacterEntity?>
    fun get(id: Int): Flow<CharacterEntity?>
    fun search(character: String): Flow<List<CharacterEntity>>
    fun searchByPinyin(pinyin: String): Flow<List<CharacterEntity>>
    fun searchByRadical(radical: String): Flow<List<CharacterEntity>>
    fun searchByStroke(stroke: Int): Flow<List<CharacterEntity>>
    fun bookmarks(): Flow<PagingData<CharacterEntity>>
}