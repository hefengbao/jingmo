package com.hefengbao.wenqu.di

import android.content.Context
import androidx.room.Room.databaseBuilder
import com.hefengbao.wenqu.data.database.AppDatabase
import com.hefengbao.wenqu.data.database.dao.ChineseWisecrackDao
import com.hefengbao.wenqu.data.database.dao.IdiomDao
import com.hefengbao.wenqu.data.database.dao.PoemDao
import com.hefengbao.wenqu.data.database.dao.PoemSentenceDao
import com.hefengbao.wenqu.data.database.dao.PoemTagDao
import com.hefengbao.wenqu.data.database.dao.TagDao
import com.hefengbao.wenqu.data.database.dao.WriterDao
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
        "wenqu.db",
    ).build()


    @Provides
    fun providesPoemDao(
        database: AppDatabase
    ): PoemDao = database.poemDao()

    @Provides
    fun providesTagDao(
        database: AppDatabase
    ): TagDao = database.tagDao()

    @Provides
    fun providesPoemTagDao(
        database: AppDatabase
    ): PoemTagDao = database.poemTagDao()

    @Provides
    fun providesWriterDao(
        database: AppDatabase
    ): WriterDao = database.writerDao()

    @Provides
    fun providesPoemSentenceDao(
        database: AppDatabase
    ): PoemSentenceDao = database.poemSentenceDao()

    @Provides
    fun providesChineseWisecrackDao(
        database: AppDatabase
    ): ChineseWisecrackDao = database.chineseWisecrackDao()

    @Provides
    fun providesIdiomDao(
        database: AppDatabase
    ): IdiomDao = database.idiomDao()
}