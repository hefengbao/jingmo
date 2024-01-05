package com.hefengbao.jingmo.data.database.util

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class StringListConverter {
    @TypeConverter
    fun listToString(list: List<String>?): String? {
        return if (list == null) {
            null
        } else {
            Json.encodeToString(list)
        }
    }

    @TypeConverter
    fun stringToList(str: String?): List<String>? {
        return if (str == null) {
            null
        } else {
            Json.decodeFromString(str)
        }
    }
}