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
import com.hefengbao.jingmo.data.database.dao.ChineseAntitheticalCoupletDao
import com.hefengbao.jingmo.data.database.dao.ChineseDictionaryDao
import com.hefengbao.jingmo.data.database.dao.ChineseExpressionDao
import com.hefengbao.jingmo.data.database.dao.ChineseIdiomDao
import com.hefengbao.jingmo.data.database.dao.ChineseKnowledgeDao
import com.hefengbao.jingmo.data.database.dao.ChineseLyricDao
import com.hefengbao.jingmo.data.database.dao.ChineseProverbDao
import com.hefengbao.jingmo.data.database.dao.ChineseRiddleDao
import com.hefengbao.jingmo.data.database.dao.ChineseTongueTwisterDao
import com.hefengbao.jingmo.data.database.dao.ChineseWisecrackDao
import com.hefengbao.jingmo.data.database.dao.ClassicalLiteratureClassicPoemDao
import com.hefengbao.jingmo.data.database.dao.ClassicalLiteraturePeopleDao
import com.hefengbao.jingmo.data.database.dao.ClassicalLiteratureSentenceDao
import com.hefengbao.jingmo.data.database.dao.ClassicalLiteratureWritingDao
import com.hefengbao.jingmo.data.database.entity.chinese.AntitheticalCoupletCollectionEntity
import com.hefengbao.jingmo.data.database.entity.chinese.AntitheticalCoupletEntity
import com.hefengbao.jingmo.data.database.entity.chinese.DictionaryEntity
import com.hefengbao.jingmo.data.database.entity.chinese.DictionaryPinyinEntity
import com.hefengbao.jingmo.data.database.entity.chinese.ExpressionEntity
import com.hefengbao.jingmo.data.database.entity.chinese.IdiomCollectionEntity
import com.hefengbao.jingmo.data.database.entity.chinese.IdiomEntity
import com.hefengbao.jingmo.data.database.entity.chinese.KnowledgeCollectionEntity
import com.hefengbao.jingmo.data.database.entity.chinese.KnowledgeEntity
import com.hefengbao.jingmo.data.database.entity.chinese.KnowledgeFtsEntity
import com.hefengbao.jingmo.data.database.entity.chinese.LyricCollectionEntity
import com.hefengbao.jingmo.data.database.entity.chinese.LyricEntity
import com.hefengbao.jingmo.data.database.entity.chinese.LyricFtsEntity
import com.hefengbao.jingmo.data.database.entity.chinese.ProverbCollectionEntity
import com.hefengbao.jingmo.data.database.entity.chinese.ProverbEntity
import com.hefengbao.jingmo.data.database.entity.chinese.ProverbFtsEntity
import com.hefengbao.jingmo.data.database.entity.chinese.RiddleEntity
import com.hefengbao.jingmo.data.database.entity.chinese.TongueTwisterEntity
import com.hefengbao.jingmo.data.database.entity.chinese.WisecrackCollectionEntity
import com.hefengbao.jingmo.data.database.entity.chinese.WisecrackEntity
import com.hefengbao.jingmo.data.database.entity.classicalliterature.ClassicPoemCollectionEntity
import com.hefengbao.jingmo.data.database.entity.classicalliterature.ClassicPoemEntity
import com.hefengbao.jingmo.data.database.entity.classicalliterature.ClassicPoemFtsEntity
import com.hefengbao.jingmo.data.database.entity.classicalliterature.PeopleEntity
import com.hefengbao.jingmo.data.database.entity.classicalliterature.SentenceCollectionEntity
import com.hefengbao.jingmo.data.database.entity.classicalliterature.SentenceEntity
import com.hefengbao.jingmo.data.database.entity.classicalliterature.WritingCollectionEntity
import com.hefengbao.jingmo.data.database.entity.classicalliterature.WritingEntity
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
        AntitheticalCoupletEntity::class,
        AntitheticalCoupletCollectionEntity::class,
        ClassicPoemCollectionEntity::class,
        ClassicPoemEntity::class,
        ClassicPoemFtsEntity::class,
        PeopleEntity::class,
        WritingCollectionEntity::class,
        WritingEntity::class,
        SentenceCollectionEntity::class,
        SentenceEntity::class,
        ExpressionEntity::class,
        WisecrackCollectionEntity::class,
        WisecrackEntity::class,
        DictionaryEntity::class,
        DictionaryPinyinEntity::class,
        IdiomCollectionEntity::class,
        IdiomEntity::class,
        KnowledgeCollectionEntity::class,
        KnowledgeEntity::class,
        KnowledgeFtsEntity::class,
        LyricCollectionEntity::class,
        LyricEntity::class,
        LyricFtsEntity::class,
        ProverbEntity::class,
        ProverbCollectionEntity::class,
        ProverbFtsEntity::class,
        RiddleEntity::class,
        TongueTwisterEntity::class,
    ],
    version = 21,
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
        AutoMigration(from = 17, to = 18),
        AutoMigration(from = 18, to = 19, spec = AppDatabase.AutoMigration18To19::class),
        AutoMigration(from = 19, to = 20),
        AutoMigration(from = 20, to = 21),
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
    abstract fun antitheticalCoupletDao(): ChineseAntitheticalCoupletDao
    abstract fun expressionDao(): ChineseExpressionDao
    abstract fun knowledgeDao(): ChineseKnowledgeDao
    abstract fun wisecrackDao(): ChineseWisecrackDao
    abstract fun dictionaryDao(): ChineseDictionaryDao
    abstract fun idiomDao(): ChineseIdiomDao
    abstract fun lyricDao(): ChineseLyricDao
    abstract fun proverbDao(): ChineseProverbDao
    abstract fun riddleDao(): ChineseRiddleDao
    abstract fun tongueTwisterDao(): ChineseTongueTwisterDao
    abstract fun classicPoemDao(): ClassicalLiteratureClassicPoemDao
    abstract fun peopleDao(): ClassicalLiteraturePeopleDao
    abstract fun sentenceDao(): ClassicalLiteratureSentenceDao
    abstract fun writingDao(): ClassicalLiteratureWritingDao

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

    @DeleteTable(
        tableName = "chinese_knowledge"
    )
    class AutoMigration18To19 : AutoMigrationSpec
}