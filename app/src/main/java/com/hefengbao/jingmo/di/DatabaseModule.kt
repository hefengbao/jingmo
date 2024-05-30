package com.hefengbao.jingmo.di

import android.content.Context
import androidx.room.Room.databaseBuilder
import com.hefengbao.jingmo.common.Constant
import com.hefengbao.jingmo.data.database.AppDatabase
import com.hefengbao.jingmo.data.database.dao.ChineseExpressionDao
import com.hefengbao.jingmo.data.database.dao.ChineseKnowledgeDao
import com.hefengbao.jingmo.data.database.dao.ChineseWisecrackDao
import com.hefengbao.jingmo.data.database.dao.ClassicPoemDao
import com.hefengbao.jingmo.data.database.dao.DictionaryDao
import com.hefengbao.jingmo.data.database.dao.IdiomDao
import com.hefengbao.jingmo.data.database.dao.PeopleDao
import com.hefengbao.jingmo.data.database.dao.PoemSentenceDao
import com.hefengbao.jingmo.data.database.dao.RiddleDao
import com.hefengbao.jingmo.data.database.dao.TongueTwisterDao
import com.hefengbao.jingmo.data.database.dao.WritingDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesAppDatabase(
        @ApplicationContext context: Context,
    ): AppDatabase = databaseBuilder(
        context,
        AppDatabase::class.java,
        Constant.DB_NAME,
    ).build()

    @Provides
    @Singleton
    fun providesChineseExpressionDao(
        database: AppDatabase
    ): ChineseExpressionDao = database.chineseExpressionDao()

    @Provides
    @Singleton
    fun providesChineseKnowledgeDao(
        database: AppDatabase
    ): ChineseKnowledgeDao = database.chineseKnowledgeDao()

    @Provides
    @Singleton
    fun providesChineseWisecrackDao(
        database: AppDatabase
    ): ChineseWisecrackDao = database.chineseWisecrackDao()

    @Provides
    @Singleton
    fun providesDictionaryDao(
        database: AppDatabase
    ): DictionaryDao = database.dictionaryDao()

    @Provides
    @Singleton
    fun providesPoemDao(
        database: AppDatabase
    ): ClassicPoemDao = database.classicPoemDao()

    @Provides
    @Singleton
    fun providesIdiomDao(
        database: AppDatabase
    ): IdiomDao = database.idiomDao()

    @Provides
    @Singleton
    fun providesPeopleDao(
        database: AppDatabase
    ): PeopleDao = database.peopleDao()

    @Provides
    @Singleton
    fun providesPoemSentenceDao(
        database: AppDatabase
    ): PoemSentenceDao = database.poemSentenceDao()

    @Provides
    @Singleton
    fun providesRiddleDao(
        database: AppDatabase
    ): RiddleDao = database.riddleDao()

    @Provides
    @Singleton
    fun providesTongueTwisterDao(
        database: AppDatabase
    ): TongueTwisterDao = database.tongueTwisterDao()

    @Provides
    @Singleton
    fun providesWritingDao(
        database: AppDatabase
    ): WritingDao = database.writingDao()
}