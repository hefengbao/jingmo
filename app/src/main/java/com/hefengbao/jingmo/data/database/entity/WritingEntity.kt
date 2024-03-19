package com.hefengbao.jingmo.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey
import com.hefengbao.jingmo.data.model.writing.Allusion
import com.hefengbao.jingmo.data.model.writing.Clause
import com.hefengbao.jingmo.data.model.writing.Quote
import com.hefengbao.jingmo.data.model.writing.Tune

@Fts4
@Entity(tableName = "writings")
data class WritingEntity(
    @PrimaryKey
    @ColumnInfo(name = "rowid")
    val id: Int,
    @ColumnInfo(name = "group_index")
    val groupIndex: Int?,
    val classes: List<String>?,
    val froms: List<String>?,
    val allusions: List<Allusion>?,
    val pictures: List<String>?,
    val dynasty: String,
    val author: String,
    @ColumnInfo("author_id")
    val authorId: Int?,
    @ColumnInfo(name = "author_date")
    val authorDate: String?,
    @ColumnInfo(name = "author_place")
    val authorPlace: String?,
    val type: String,
    @ColumnInfo(name = "type_detail")
    val typeDetail: String,
    val rhyme: String?,
    @Embedded("title_")
    val title: Clause,
    @Embedded("subtitle_")
    val subtitle: Clause?,
    @Embedded(prefix = "tune_")
    val tuneId: Tune?,
    val preface: String?,
    val clauses: List<Clause>,
    val note: String?,
    val comments: List<Quote>?
) {
    var content: String? = clauses.joinToString("") { it.content }
}
