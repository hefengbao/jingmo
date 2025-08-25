/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.di

import android.content.Context
import androidx.room.Room.databaseBuilder
import com.hefengbao.jingmo.common.Constant
import com.hefengbao.jingmo.data.database.AppDatabase
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
    fun providesBookmarkDao(
        database: AppDatabase
    ): BookmarkDao = database.bookmarkDao()

    @Provides
    @Singleton
    fun providesChinaWorldCultureHeritageDao(
        database: AppDatabase
    ): ChinaWorldCulturalHeritageDao = database.chinaWorldCulturalHeritageDao()

    @Provides
    @Singleton
    fun providesChineseAntitheticalCoupletDao(
        database: AppDatabase
    ): ChineseAntitheticalCoupletDao = database.chineseAntitheticalCoupletDao()

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
    fun providesChineseModernPoetryDao(
        database: AppDatabase
    ): ChineseModernPoetryDao = database.chineseModernPoetryDao()

    @Provides
    @Singleton
    fun providesChineseWisecrackDao(
        database: AppDatabase
    ): ChineseWisecrackDao = database.chineseWisecrackDao()

    @Provides
    @Singleton
    fun providesChineseDictionaryDao(
        database: AppDatabase
    ): ChineseCharacterDao = database.chineseCharacterDao()

    @Provides
    @Singleton
    fun providesChineseIdiomDao(
        database: AppDatabase
    ): ChineseIdiomDao = database.chineseIdiomDao()

    @Provides
    @Singleton
    fun providesChineseLyricDao(
        database: AppDatabase
    ): ChineseLyricDao = database.chineseLyricDao()

    @Provides
    @Singleton
    fun providesChineseProverbDao(
        database: AppDatabase
    ): ChineseProverbDao = database.chineseProverbDao()

    @Provides
    @Singleton
    fun providesChineseQuoteDao(
        database: AppDatabase
    ): ChineseQuoteDao = database.chineseQuoteDao()

    @Provides
    @Singleton
    fun providesClassicalLiteratureClassicPoemDao(
        database: AppDatabase
    ): ClassicalLiteratureClassicPoemDao = database.classicalLiteratureClassicPoemDao()

    @Provides
    @Singleton
    fun providesClassicalLiteraturePeopleDao(
        database: AppDatabase
    ): ClassicalLiteraturePeopleDao = database.classicalLiteraturePeopleDao()

    @Provides
    @Singleton
    fun providesClassicalLiteratureSentenceDao(
        database: AppDatabase
    ): ClassicalLiteratureSentenceDao = database.classicalLiteratureSentenceDao()

    @Provides
    @Singleton
    fun providesChineseRiddleDao(
        database: AppDatabase
    ): ChineseRiddleDao = database.chineseRiddleDao()

    @Provides
    @Singleton
    fun providesChineseTongueTwisterDao(
        database: AppDatabase
    ): ChineseTongueTwisterDao = database.chineseTongueTwisterDao()

    @Provides
    @Singleton
    fun providesClassicalLiteratureWritingDao(
        database: AppDatabase
    ): ClassicalLiteratureWritingDao = database.classicalLiteratureWritingDao()
}