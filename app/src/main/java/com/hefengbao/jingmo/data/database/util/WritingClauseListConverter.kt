package com.hefengbao.jingmo.data.database.util

import androidx.room.TypeConverter
import com.hefengbao.jingmo.data.model.writing.Clause
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class WritingClauseListConverter {
    @TypeConverter
    fun listToString(list: List<Clause>?): String? {
        return if (list == null) {
            null
        } else {
            Json.encodeToString(list)
        }
    }

    @TypeConverter
    fun stringToList(str: String?): List<Clause>? {
        return if (str == null) {
            null
        } else {
            Json.decodeFromString(str)
        }
    }
}