package com.hefengbao.jingmo.data.database.util

import androidx.room.TypeConverter
import com.hefengbao.jingmo.data.model.writing.Comment
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class WritingCommentListConverter {
    @TypeConverter
    fun listToString(list: List<Comment>?): String? {
        return if (list == null) {
            null
        } else {
            Json.encodeToString(list)
        }
    }

    @TypeConverter
    fun stringToList(str: String?): List<Comment>? {
        return if (str == null) {
            null
        } else {
            Json.decodeFromString(str)
        }
    }
}