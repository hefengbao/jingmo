package com.hefengbao.jingmo.data.database.util

import androidx.room.TypeConverter
import com.hefengbao.jingmo.data.model.chinese.ExplanationItem
import kotlinx.serialization.json.Json

class CharacterExplanations2Converter {
    @TypeConverter
    fun explanations2ListToString(list: List<ExplanationItem>?): String? {
        return if (list == null) {
            null
        } else {
            Json.encodeToString(list)
        }
    }

    @TypeConverter
    fun stringToExplanations2List(str: String?): List<ExplanationItem>? {
        return if (str == null) {
            null
        } else {
            Json.decodeFromString(str)
        }
    }
}