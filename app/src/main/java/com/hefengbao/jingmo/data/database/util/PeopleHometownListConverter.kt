package com.hefengbao.jingmo.data.database.util

import androidx.room.TypeConverter
import com.hefengbao.jingmo.data.model.PeopleHometown
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class PeopleHometownListConverter {
    @TypeConverter
    fun listToString(list: List<PeopleHometown>?): String? {
        return if (list == null) {
            null
        } else {
            Json.encodeToString(list)
        }
    }

    @TypeConverter
    fun stringToList(str: String?): List<PeopleHometown>? {
        return if (str == null) {
            null
        } else {
            Json.decodeFromString(str)
        }
    }
}