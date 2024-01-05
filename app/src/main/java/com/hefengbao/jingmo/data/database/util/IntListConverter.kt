package com.hefengbao.jingmo.data.database.util

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class IntListConverter {
    @TypeConverter
    fun listToString(list: List<Int>?): String? {
        return if (list == null) {
            null
        } else {
            Json.encodeToString(list)
        }
    }

    @TypeConverter
    fun stringToList(str: String?): List<Int>? {
        return if (str == null) {
            null
        } else {
            Json.decodeFromString(str)
        }
    }
}