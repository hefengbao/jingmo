/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.database.entity.classicalliterature

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hefengbao.jingmo.data.model.classicalliterature.writing.Allusion
import com.hefengbao.jingmo.data.model.classicalliterature.writing.Clause
import com.hefengbao.jingmo.data.model.classicalliterature.writing.Quote
import com.hefengbao.jingmo.data.model.classicalliterature.writing.Tune

@Entity(tableName = "classicalliterature_writing")
data class WritingEntity(
    @PrimaryKey
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
    @ColumnInfo("author_years")
    val authorYears: String?,
    @ColumnInfo(name = "author_place")
    val authorPlace: String?,
    val type: String,
    @ColumnInfo(name = "type_detail")
    val typeDetail: String?,
    val rhyme: String?,
    @ColumnInfo(name = "first_clause_rhyme")
    val firstClauseRhyme: String?,
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
    var title2: String? = title.content
    var content: String? = clauses.map { item: Clause -> item.content }.joinToString("")
}
