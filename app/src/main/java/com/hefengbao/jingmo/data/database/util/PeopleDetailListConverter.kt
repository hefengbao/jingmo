package com.hefengbao.jingmo.data.database.util

import androidx.room.TypeConverter
import com.hefengbao.jingmo.data.model.IntroItem
import com.hefengbao.jingmo.data.model.PeopleAlias
import com.hefengbao.jingmo.data.model.PeopleDetail
import com.hefengbao.jingmo.data.model.PeopleHometown
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class PeopleDetailListConverter {
    @TypeConverter
    fun listToString(list: List<PeopleDetail>?): String? {
        return if (list == null) {
            null
        } else {
            Json.encodeToString(list)
        }
    }

    @TypeConverter
    fun stringToList(str: String?): List<PeopleDetail>? {
        return if (str == null) {
            null
        } else {
            Json.decodeFromString(str)
        }
    }
}