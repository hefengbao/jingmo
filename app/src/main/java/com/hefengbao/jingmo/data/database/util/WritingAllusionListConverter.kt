package com.hefengbao.jingmo.data.database.util

import androidx.room.TypeConverter
import com.hefengbao.jingmo.data.model.writing.Allusion
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class WritingAllusionListConverter {
    @TypeConverter
    fun listToString(list: List<Allusion>?): String? {
        return if (list == null) {
            null
        } else {
            Json.encodeToString(list)
        }
    }

    @TypeConverter
    fun stringToList(str: String?): List<Allusion>? {
        return if (str == null) {
            null
        } else {
            Json.decodeFromString(str)
        }
    }
}