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
import androidx.room.RenameTable
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.AutoMigrationSpec
import com.hefengbao.jingmo.data.database.dao.BookmarkDao
import com.hefengbao.jingmo.data.database.dao.ChinaWorldCulturalHeritageDao
import com.hefengbao.jingmo.data.database.dao.ChineseAntitheticalCoupletDao
import com.hefengbao.jingmo.data.database.dao.ChineseCharacterDao
import com.hefengbao.jingmo.data.database.dao.ChineseExpressionDao
import com.hefengbao.jingmo.data.database.dao.ChineseIdiomDao
import com.hefengbao.jingmo.data.database.dao.ChineseKnowledgeDao
import com.hefengbao.jingmo.data.database.dao.ChineseLyricDao
import com.hefengbao.jingmo.data.database.dao.ChineseModernPoetryDao
import com.hefengbao.jingmo.data.database.dao.ChineseProverbDao
import com.hefengbao.jingmo.data.database.dao.ChineseQuoteDao
import com.hefengbao.jingmo.data.database.dao.ChineseRiddleDao
import com.hefengbao.jingmo.data.database.dao.ChineseTongueTwisterDao
import com.hefengbao.jingmo.data.database.dao.ChineseWisecrackDao
import com.hefengbao.jingmo.data.database.dao.ClassicalLiteratureClassicPoemDao
import com.hefengbao.jingmo.data.database.dao.ClassicalLiteraturePeopleDao
import com.hefengbao.jingmo.data.database.dao.ClassicalLiteratureSentenceDao
import com.hefengbao.jingmo.data.database.dao.ClassicalLiteratureWritingDao
import com.hefengbao.jingmo.data.database.entity.BookmarkEntity
import com.hefengbao.jingmo.data.database.util.CharacterExplanations2Converter
import com.hefengbao.jingmo.data.database.util.IntListConverter
import com.hefengbao.jingmo.data.database.util.PeopleAliasListConverter
import com.hefengbao.jingmo.data.database.util.PeopleDetailListConverter
import com.hefengbao.jingmo.data.database.util.PeopleHometownListConverter
import com.hefengbao.jingmo.data.database.util.StringListConverter
import com.hefengbao.jingmo.data.database.util.WritingConverter
import com.hefengbao.jingmo.data.database.entity.china.WorldCulturalHeritageEntity as ChinaWorldCulturalHeritageEntity
import com.hefengbao.jingmo.data.database.entity.chinese.AntitheticalCoupletEntity as ChineseAntitheticalCoupletEntity
import com.hefengbao.jingmo.data.database.entity.chinese.CharacterEntity as ChineseCharacterEntity
import com.hefengbao.jingmo.data.database.entity.chinese.ExpressionEntity as ChineseExpressionEntity
import com.hefengbao.jingmo.data.database.entity.chinese.IdiomEntity as ChineseIdiomEntity
import com.hefengbao.jingmo.data.database.entity.chinese.KnowledgeEntity as ChineseKnowledgeEntity
import com.hefengbao.jingmo.data.database.entity.chinese.KnowledgeFtsEntity as ChineseKnowledgeFtsEntity
import com.hefengbao.jingmo.data.database.entity.chinese.LyricEntity as ChineseLyricEntity
import com.hefengbao.jingmo.data.database.entity.chinese.LyricFtsEntity as ChineseLyricFtsEntity
import com.hefengbao.jingmo.data.database.entity.chinese.ModernPoetryEntity as ChineseModernPoetryEntity
import com.hefengbao.jingmo.data.database.entity.chinese.ModernPoetryFtsEntity as ChineseModernPoetryFtsEntity
import com.hefengbao.jingmo.data.database.entity.chinese.ProverbEntity as ChineseProverbEntity
import com.hefengbao.jingmo.data.database.entity.chinese.ProverbFtsEntity as ChineseProverbFtsEntity
import com.hefengbao.jingmo.data.database.entity.chinese.QuoteEntity as ChineseQuoteEntity
import com.hefengbao.jingmo.data.database.entity.chinese.QuoteFtsEntity as ChineseQuoteFtsEntity
import com.hefengbao.jingmo.data.database.entity.chinese.RiddleEntity as ChineseRiddleEntity
import com.hefengbao.jingmo.data.database.entity.chinese.TongueTwisterEntity as ChineseTongueTwisterEntity
import com.hefengbao.jingmo.data.database.entity.chinese.WisecrackEntity as ChineseWisecrackEntity
import com.hefengbao.jingmo.data.database.entity.classicalliterature.ClassicPoemEntity as ClassicalLiteratureClassicPoemEntity
import com.hefengbao.jingmo.data.database.entity.classicalliterature.ClassicPoemFtsEntity as ClassicalLiteratureClassicPoemFtsEntity
import com.hefengbao.jingmo.data.database.entity.classicalliterature.PeopleEntity as ClassicalLiteraturePeopleEntity
import com.hefengbao.jingmo.data.database.entity.classicalliterature.SentenceEntity as ClassicalLiteratureSentenceEntity
import com.hefengbao.jingmo.data.database.entity.classicalliterature.WritingEntity as ClassicalLiteratureWritingEntity
import com.hefengbao.jingmo.data.database.entity.classicalliterature.WritingFtsEntity as ClassicalLiteratureWritingFtsEntity

/**
 *  entities 数组中添加 data class 或其中的 data class 发生任何变化， 先 version + 1, 然后再 Build。
 *  app/schemas 目录下，保存 exportSchema 数据
 */
@Database(
    entities = [
        BookmarkEntity::class,
        ChinaWorldCulturalHeritageEntity::class,
        ChineseAntitheticalCoupletEntity::class,
        ChineseCharacterEntity::class,
        ChineseExpressionEntity::class,
        ChineseIdiomEntity::class,
        ChineseKnowledgeEntity::class,
        ChineseKnowledgeFtsEntity::class,
        ChineseLyricEntity::class,
        ChineseLyricFtsEntity::class,
        ChineseModernPoetryEntity::class,
        ChineseModernPoetryFtsEntity::class,
        ChineseProverbEntity::class,
        ChineseProverbFtsEntity::class,
        ChineseQuoteEntity::class,
        ChineseQuoteFtsEntity::class,
        ChineseRiddleEntity::class,
        ChineseTongueTwisterEntity::class,
        ChineseWisecrackEntity::class,
        ClassicalLiteratureClassicPoemEntity::class,
        ClassicalLiteratureClassicPoemFtsEntity::class,
        ClassicalLiteraturePeopleEntity::class,
        ClassicalLiteratureSentenceEntity::class,
        ClassicalLiteratureWritingEntity::class,
        ClassicalLiteratureWritingFtsEntity::class,
    ],
    version = 28,
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
        AutoMigration(from = 21, to = 22),
        AutoMigration(from = 22, to = 23),
        AutoMigration(from = 23, to = 24),
        AutoMigration(from = 24, to = 25),
        AutoMigration(from = 25, to = 26, spec = AppDatabase.AutoMigration25To26::class),
        AutoMigration(from = 26, to = 27),
        AutoMigration(from = 27, to = 28, spec = AppDatabase.AutoMigration27To28::class),
    ],
    exportSchema = true
)
@TypeConverters(
    CharacterExplanations2Converter::class,
    IntListConverter::class,
    PeopleAliasListConverter::class,
    PeopleDetailListConverter::class,
    PeopleHometownListConverter::class,
    StringListConverter::class,
    WritingConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookmarkDao(): BookmarkDao
    abstract fun chineseAntitheticalCoupletDao(): ChineseAntitheticalCoupletDao
    abstract fun chineseModernPoetryDao(): ChineseModernPoetryDao
    abstract fun chineseQuoteDao(): ChineseQuoteDao
    abstract fun chineseExpressionDao(): ChineseExpressionDao
    abstract fun chineseKnowledgeDao(): ChineseKnowledgeDao
    abstract fun chineseWisecrackDao(): ChineseWisecrackDao
    abstract fun chineseCharacterDao(): ChineseCharacterDao
    abstract fun chineseIdiomDao(): ChineseIdiomDao
    abstract fun chineseLyricDao(): ChineseLyricDao
    abstract fun chineseProverbDao(): ChineseProverbDao
    abstract fun chineseRiddleDao(): ChineseRiddleDao
    abstract fun chineseTongueTwisterDao(): ChineseTongueTwisterDao
    abstract fun classicalLiteratureClassicPoemDao(): ClassicalLiteratureClassicPoemDao
    abstract fun classicalLiteraturePeopleDao(): ClassicalLiteraturePeopleDao
    abstract fun classicalLiteratureSentenceDao(): ClassicalLiteratureSentenceDao
    abstract fun classicalLiteratureWritingDao(): ClassicalLiteratureWritingDao
    abstract fun chinaWorldCulturalHeritageDao(): ChinaWorldCulturalHeritageDao

    @DeleteTable(tableName = "poems")
    @DeleteTable(tableName = "poem_tag")
    @DeleteTable(tableName = "tags")
    @DeleteTable(tableName = "writers")
    class AutoMigration7To8 : AutoMigrationSpec

    @RenameColumn(
        tableName = "chinese_knowledge",
        fromColumnName = "id",
        toColumnName = "rowid"
    )
    class AutoMigration8To9 : AutoMigrationSpec

    @DeleteTable(tableName = "idioms")
    @DeleteTable(tableName = "idiom_collections")
    class AutoMigration13To14 : AutoMigrationSpec

    @DeleteTable(tableName = "chinese_knowledge")
    class AutoMigration18To19 : AutoMigrationSpec

    @DeleteTable(tableName = "writings")
    class AutoMigration25To26 : AutoMigrationSpec

    @DeleteTable(tableName = "chinese_antithetical_couplet_collections")
    @DeleteTable(tableName = "dictionary_collections")
    @DeleteTable(tableName = "chinese_expression_collections")
    @DeleteTable(tableName = "idiom_collections")
    @DeleteTable(tableName = "chinese_knowledge_collections")
    @DeleteTable(tableName = "lyric_collections")
    @DeleteTable(tableName = "chinese_modern_poetry_collections")
    @DeleteTable(tableName = "proverb_collections")
    @DeleteTable(tableName = "chinese_quote_collections")
    @DeleteTable(tableName = "chinese_wisecrack_collections")
    @DeleteTable(tableName = "classic_poem_collections")
    @DeleteTable(tableName = "poem_sentence_collections")
    @DeleteTable(tableName = "writing_collections")
    @DeleteTable(tableName = "dictionary")
    @DeleteTable(tableName = "dictionary_pinyin")
    @DeleteTable(tableName = "chinese_expressions")
    @RenameTable(
        fromTableName = "china_world_cultural_heritage",
        toTableName = "china_worldcultureheritage"
    )
    @RenameTable(
        fromTableName = "chinese_antithetical_couplets",
        toTableName = "chinese_antitheticalcouplet"
    )
    @RenameTable(fromTableName = "idioms", toTableName = "chinese_idiom")
    @RenameTable(fromTableName = "lyrics", toTableName = "chinese_lyric")
    @RenameTable(fromTableName = "lyrics_fts", toTableName = "chinese_lyric_fts")
    @RenameTable(fromTableName = "chinese_modern_poetry", toTableName = "chinese_modernpoetry")
    @RenameTable(
        fromTableName = "chinese_modern_poetry_fts",
        toTableName = "chinese_modernpoetry_fts"
    )
    @RenameTable(fromTableName = "proverbs", toTableName = "chinese_proverb")
    @RenameTable(fromTableName = "proverbs_fts", toTableName = "chinese_proverb_fts")
    @RenameTable(fromTableName = "chinese_quotes", toTableName = "chinese_quote")
    @RenameTable(fromTableName = "chinese_quotes_fts", toTableName = "chinese_quote_fts")
    @RenameTable(fromTableName = "riddles", toTableName = "chinese_riddle")
    @RenameTable(fromTableName = "tongue_twisters", toTableName = "chinese_tonguetwister")
    @RenameTable(fromTableName = "chinese_wisecracks", toTableName = "chinese_wisecrack")
    @RenameTable(fromTableName = "classic_poems", toTableName = "classicalliterature_classicpoem")
    @RenameTable(
        fromTableName = "classic_poems_fts",
        toTableName = "classicalliterature_classicpoem_fts"
    )
    @RenameTable(fromTableName = "people", toTableName = "classicalliterature_people")
    @RenameTable(fromTableName = "poem_sentences", toTableName = "classicalliterature_sentence")
    @RenameTable(fromTableName = "writings", toTableName = "classicalliterature_writing")
    @RenameTable(fromTableName = "writing_fts", toTableName = "classicalliterature_writing_fts")
    class AutoMigration27To28 : AutoMigrationSpec
}