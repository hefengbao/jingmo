package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.data.database.entity.DictionaryEntity
import com.hefengbao.jingmo.data.model.CharacterPinyin
import com.hefengbao.jingmo.data.model.CharacterRadical
import com.hefengbao.jingmo.data.model.CharacterStroke
import kotlinx.coroutines.flow.Flow

interface ChineseCharacterRepository {
    fun pinyins(): List<CharacterPinyin>
    fun radicals(): List<CharacterRadical>
    fun strokes(): List<CharacterStroke>
    fun get(id: Int): Flow<DictionaryEntity>
    fun search(character: String): Flow<List<DictionaryEntity>>
    fun searchByPinyin(pinyin: String): Flow<List<DictionaryEntity>>
    fun searchByRadical(radical: String): Flow<List<DictionaryEntity>>
    fun searchByStroke(stroke: Int): Flow<List<DictionaryEntity>>
}