/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.DeleteTable
import androidx.room.RenameColumn
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.AutoMigrationSpec
import com.hefengbao.jingmo.data.database.dao.ChineseExpressionDao
import com.hefengbao.jingmo.data.database.dao.ChineseKnowledgeDao
import com.hefengbao.jingmo.data.database.dao.ChineseWisecrackDao
import com.hefengbao.jingmo.data.database.dao.ClassicPoemDao
import com.hefengbao.jingmo.data.database.dao.DictionaryDao
import com.hefengbao.jingmo.data.database.dao.IdiomDao
import com.hefengbao.jingmo.data.database.dao.LyricDao
import com.hefengbao.jingmo.data.database.dao.PeopleDao
import com.hefengbao.jingmo.data.database.dao.PoemSentenceDao
import com.hefengbao.jingmo.data.database.dao.RiddleDao
import com.hefengbao.jingmo.data.database.dao.TongueTwisterDao
import com.hefengbao.jingmo.data.database.dao.WritingDao
import com.hefengbao.jingmo.data.database.entity.ChineseExpressionEntity
import com.hefengbao.jingmo.data.database.entity.ChineseKnowledgeEntity
import com.hefengbao.jingmo.data.database.entity.ChineseWisecrackCollectionEntity
import com.hefengbao.jingmo.data.database.entity.ChineseWisecrackEntity
import com.hefengbao.jingmo.data.database.entity.ClassicPoemCollectionEntity
import com.hefengbao.jingmo.data.database.entity.ClassicPoemEntity
import com.hefengbao.jingmo.data.database.entity.ClassicPoemFtsEntity
import com.hefengbao.jingmo.data.database.entity.DictionaryEntity
import com.hefengbao.jingmo.data.database.entity.DictionaryPinyinEntity
import com.hefengbao.jingmo.data.database.entity.IdiomCollectionEntity
import com.hefengbao.jingmo.data.database.entity.IdiomEntity
import com.hefengbao.jingmo.data.database.entity.LyricCollectionEntity
import com.hefengbao.jingmo.data.database.entity.LyricEntity
import com.hefengbao.jingmo.data.database.entity.LyricFtsEntity
import com.hefengbao.jingmo.data.database.entity.PeopleEntity
import com.hefengbao.jingmo.data.database.entity.PoemSentenceCollectionEntity
import com.hefengbao.jingmo.data.database.entity.PoemSentenceEntity
import com.hefengbao.jingmo.data.database.entity.RiddleEntity
import com.hefengbao.jingmo.data.database.entity.TongueTwisterEntity
import com.hefengbao.jingmo.data.database.entity.WritingCollectionEntity
import com.hefengbao.jingmo.data.database.entity.WritingEntity
import com.hefengbao.jingmo.data.database.util.IntListConverter
import com.hefengbao.jingmo.data.database.util.PeopleAliasListConverter
import com.hefengbao.jingmo.data.database.util.PeopleDetailListConverter
import com.hefengbao.jingmo.data.database.util.PeopleHometownListConverter
import com.hefengbao.jingmo.data.database.util.StringListConverter
import com.hefengbao.jingmo.data.database.util.WritingAllusionListConverter
import com.hefengbao.jingmo.data.database.util.WritingClauseListConverter
import com.hefengbao.jingmo.data.database.util.WritingCommentListConverter
import com.hefengbao.jingmo.data.database.util.WritingQuoteListConverter

/**
 *  entities 数组中添加 data class 或其中的 data class 发生任何变化， 先 version + 1, 然后再 Build。
 *  app/schemas 目录下，保存 exportSchema 数据
 */
@Database(
    entities = [
        ChineseExpressionEntity::class,
        ChineseKnowledgeEntity::class,
        ChineseWisecrackCollectionEntity::class,
        ChineseWisecrackEntity::class,
        ClassicPoemCollectionEntity::class,
        ClassicPoemEntity::class,
        ClassicPoemFtsEntity::class,
        DictionaryEntity::class,
        DictionaryPinyinEntity::class,
        IdiomCollectionEntity::class,
        IdiomEntity::class,
        LyricCollectionEntity::class,
        LyricEntity::class,
        LyricFtsEntity::class,
        PeopleEntity::class,
        PoemSentenceCollectionEntity::class,
        PoemSentenceEntity::class,
        RiddleEntity::class,
        TongueTwisterEntity::class,
        WritingCollectionEntity::class,
        WritingEntity::class,
    ],
    version = 17,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
        AutoMigration(from = 2, to = 3),
        AutoMigration(from = 3, to = 4),
        AutoMigration(from = 4, to = 5),
        AutoMigration(from = 5, to = 6),
        AutoMigration(from = 6, to = 7),
        AutoMigration(from = 7, to = 8, spec = AppDatabase.AutoMigration7To8::class),
        AutoMigration(from = 8, to = 9, spec = AppDatabase.AutoMigration8To9::class),
        AutoMigration(from = 9, to = 10),
        AutoMigration(from = 10, to = 11),
        AutoMigration(from = 11, to = 12),
        AutoMigration(from = 12, to = 13),
        AutoMigration(from = 13, to = 14, spec = AppDatabase.AutoMigration13To14::class),
        AutoMigration(from = 14, to = 15),
        AutoMigration(from = 15, to = 16),
        AutoMigration(from = 16, to = 17),
    ],
    exportSchema = true
)
@TypeConverters(
    IntListConverter::class,
    PeopleAliasListConverter::class,
    PeopleDetailListConverter::class,
    PeopleHometownListConverter::class,
    StringListConverter::class,
    WritingAllusionListConverter::class,
    WritingClauseListConverter::class,
    WritingCommentListConverter::class,
    WritingQuoteListConverter::class,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun chineseExpressionDao(): ChineseExpressionDao
    abstract fun chineseKnowledgeDao(): ChineseKnowledgeDao
    abstract fun chineseWisecrackDao(): ChineseWisecrackDao
    abstract fun classicPoemDao(): ClassicPoemDao
    abstract fun dictionaryDao(): DictionaryDao
    abstract fun idiomDao(): IdiomDao
    abstract fun lyricDao(): LyricDao
    abstract fun peopleDao(): PeopleDao
    abstract fun riddleDao(): RiddleDao
    abstract fun tongueTwisterDao(): TongueTwisterDao
    abstract fun poemSentenceDao(): PoemSentenceDao
    abstract fun writingDao(): WritingDao

    @DeleteTable(
        tableName = "poems"
    )
    @DeleteTable(
        tableName = "poem_tag"
    )
    @DeleteTable(
        tableName = "tags"
    )
    @DeleteTable(
        tableName = "writers"
    )
    class AutoMigration7To8 : AutoMigrationSpec

    @RenameColumn(
        tableName = "chinese_knowledge",
        fromColumnName = "id",
        toColumnName = "rowid"
    )
    class AutoMigration8To9 : AutoMigrationSpec

    @DeleteTable(
        tableName = "idioms"
    )
    @DeleteTable(
        tableName = "idiom_collections"
    )
    class AutoMigration13To14 : AutoMigrationSpec
}