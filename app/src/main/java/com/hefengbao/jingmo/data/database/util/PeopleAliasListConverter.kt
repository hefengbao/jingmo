package com.hefengbao.jingmo.data.database.util

import androidx.room.TypeConverter
import com.hefengbao.jingmo.data.model.PeopleAlias
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class PeopleAliasListConverter {
    @TypeConverter
    fun listToString(list: List<PeopleAlias>?): String? {
        return if (list == null) {
            null
        } else {
            Json.encodeToString(list)
        }
    }

    @TypeConverter
    fun stringToList(str: String?): List<PeopleAlias>? {
        return if (str == null) {
            null
        } else {
            Json.decodeFromString(str)
        }
    }
}