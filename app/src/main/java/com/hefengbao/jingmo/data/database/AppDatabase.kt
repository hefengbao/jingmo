package com.hefengbao.jingmo.data.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hefengbao.jingmo.data.database.dao.ChineseKnowledgeDao
import com.hefengbao.jingmo.data.database.dao.ChineseWisecrackDao
import com.hefengbao.jingmo.data.database.dao.IdiomDao
import com.hefengbao.jingmo.data.database.dao.PoemDao
import com.hefengbao.jingmo.data.database.dao.PoemSentenceDao
import com.hefengbao.jingmo.data.database.dao.PoemTagDao
import com.hefengbao.jingmo.data.database.dao.RiddleDao
import com.hefengbao.jingmo.data.database.dao.TagDao
import com.hefengbao.jingmo.data.database.dao.TongueTwisterDao
import com.hefengbao.jingmo.data.database.dao.WriterDao
import com.hefengbao.jingmo.data.database.entity.ChineseKnowledgeEntity
import com.hefengbao.jingmo.data.database.entity.ChineseWisecrackEntity
import com.hefengbao.jingmo.data.database.entity.IdiomEntity
import com.hefengbao.jingmo.data.database.entity.PoemEntity
import com.hefengbao.jingmo.data.database.entity.PoemSentenceEntity
import com.hefengbao.jingmo.data.database.entity.PoemTagCrossRef
import com.hefengbao.jingmo.data.database.entity.RiddleEntity
import com.hefengbao.jingmo.data.database.entity.TagEntity
import com.hefengbao.jingmo.data.database.entity.TongueTwisterEntity
import com.hefengbao.jingmo.data.database.entity.WriterEntity
import com.hefengbao.jingmo.data.database.util.DetailInfoListConverter

@Database(
    entities = [
        ChineseKnowledgeEntity::class,
        ChineseWisecrackEntity::class,
        IdiomEntity::class,
        PoemEntity::class,
        PoemSentenceEntity::class,
        PoemTagCrossRef::class,
        RiddleEntity::class,
        TagEntity::class,
        TongueTwisterEntity::class,
        WriterEntity::class,
    ],
    version = 6,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
        AutoMigration(from = 2, to = 3),
        AutoMigration(from = 3, to = 4),
        AutoMigration(from = 4, to = 5),
        AutoMigration(from = 5, to = 6),
    ],
    exportSchema = true
)
@TypeConverters(
    DetailInfoListConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun chineseKnowledgeDao(): ChineseKnowledgeDao
    abstract fun chineseWisecrackDao(): ChineseWisecrackDao
    abstract fun idiomDao(): IdiomDao
    abstract fun poemDao(): PoemDao
    abstract fun poemTagDao(): PoemTagDao
    abstract fun riddleDao(): RiddleDao
    abstract fun tagDao(): TagDao
    abstract fun tongueTwisterDao(): TongueTwisterDao
    abstract fun poemSentenceDao(): PoemSentenceDao
    abstract fun writerDao(): WriterDao
}