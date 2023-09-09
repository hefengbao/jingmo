package com.hefengbao.wenqu.data.database.util

import androidx.room.TypeConverter
import com.hefengbao.wenqu.data.model.IntroItem
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class DetailInfoListConverter {
    @TypeConverter
    fun listToString(list: List<IntroItem>?): String? {
        return if (list == null) {
            null
        } else {
            Json.encodeToString(list)
        }
    }

    @TypeConverter
    fun stringToList(str: String?): List<IntroItem>? {
        return if (str == null) {
            null
        } else {
            Json.decodeFromString(str)
        }
    }
}