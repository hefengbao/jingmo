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
import com.hefengbao.jingmo.data.database.entity.chinese.DictionaryCollectionEntity
import com.hefengbao.jingmo.data.database.entity.chinese.DictionaryEntity
import com.hefengbao.jingmo.data.model.chinese.character.Pinyin
import com.hefengbao.jingmo.data.model.chinese.character.Radical
import com.hefengbao.jingmo.data.model.chinese.character.Stroke
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun pinyins(): List<Pinyin>
    fun radicals(): List<Radical>
    fun strokes(): List<Stroke>
    fun get(id: Int): Flow<DictionaryEntity>
    fun search(character: String): Flow<List<DictionaryEntity>>
    fun searchByPinyin(pinyin: String): Flow<List<DictionaryEntity>>
    fun searchByRadical(radical: String): Flow<List<DictionaryEntity>>
    fun searchByStroke(stroke: Int): Flow<List<DictionaryEntity>>
    fun collections(): Flow<PagingData<DictionaryEntity>>
    suspend fun collect(entity: DictionaryCollectionEntity)
    suspend fun uncollect(id: Int)
    fun isCollect(id: Int): Flow<DictionaryCollectionEntity?>
}