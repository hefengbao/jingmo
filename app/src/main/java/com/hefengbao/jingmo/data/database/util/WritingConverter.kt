package com.hefengbao.jingmo.data.database.util

import androidx.room.TypeConverter
import com.hefengbao.jingmo.data.model.classicalliterature.writing.Allusion
import com.hefengbao.jingmo.data.model.classicalliterature.writing.Clause
import com.hefengbao.jingmo.data.model.classicalliterature.writing.Comment
import com.hefengbao.jingmo.data.model.classicalliterature.writing.Quote
import kotlinx.serialization.json.Json

class WritingConverter {
    @TypeConverter
    fun allusionListToString(list: List<Allusion>?): String? {
        return if (list == null) {
            null
        } else {
            Json.encodeToString(list)
        }
    }

    @TypeConverter
    fun stringToAllusionList(str: String?): List<Allusion>? {
        return if (str == null) {
            null
        } else {
            Json.decodeFromString(str)
        }
    }

    @TypeConverter
    fun clauseListToString(list: List<Clause>?): String? {
        return if (list == null) {
            null
        } else {
            Json.encodeToString(list)
        }
    }

    @TypeConverter
    fun stringToClauseList(str: String?): List<Clause>? {
        return if (str == null) {
            null
        } else {
            Json.decodeFromString(str)
        }
    }

    @TypeConverter
    fun commentListToString(list: List<Comment>?): String? {
        return if (list == null) {
            null
        } else {
            Json.encodeToString(list)
        }
    }

    @TypeConverter
    fun stringToCommentList(str: String?): List<Comment>? {
        return if (str == null) {
            null
        } else {
            Json.decodeFromString(str)
        }
    }

    @TypeConverter
    fun quoteListToString(list: List<Quote>?): String? {
        return if (list == null) {
            null
        } else {
            Json.encodeToString(list)
        }
    }

    @TypeConverter
    fun stringToQuoteList(str: String?): List<Quote>? {
        return if (str == null) {
            null
        } else {
            Json.decodeFromString(str)
        }
    }
}