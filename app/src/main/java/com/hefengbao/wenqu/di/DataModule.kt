package com.hefengbao.wenqu.di

import com.hefengbao.wenqu.data.repository.ChineseCrackRepositoryImpl
import com.hefengbao.wenqu.data.repository.ChineseWisecrackRepository
import com.hefengbao.wenqu.data.repository.IdiomRepository
import com.hefengbao.wenqu.data.repository.IdiomRepositoryImpl
import com.hefengbao.wenqu.data.repository.PoemRepository
import com.hefengbao.wenqu.data.repository.PoemRepositoryImpl
import com.hefengbao.wenqu.data.repository.PoemSentenceRepository
import com.hefengbao.wenqu.data.repository.PoemSentenceRepositoryImpl
import com.hefengbao.wenqu.data.repository.PreferenceRepository
import com.hefengbao.wenqu.data.repository.PreferenceRepositoryImpl
import com.hefengbao.wenqu.data.repository.SyncRepository
import com.hefengbao.wenqu.data.repository.SyncRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindsSyncRepository(
        syncRepository: SyncRepositoryImpl
    ): SyncRepository

    @Binds
    fun bindsPreferenceRepository(
        preferenceRepository: PreferenceRepositoryImpl
    ): PreferenceRepository

    @Binds
    fun bindsPoemRepository(
        poemRepository: PoemRepositoryImpl
    ): PoemRepository

    @Binds
    fun bindsPoemSentenceRepository(
        poemSentenceRepository: PoemSentenceRepositoryImpl
    ): PoemSentenceRepository

    @Binds
    fun bindsChineseWisecrackRepository(
        chineseCrackRepository: ChineseCrackRepositoryImpl
    ): ChineseWisecrackRepository

    @Binds
    fun bindsIdiomRepository(
        idiomRepository: IdiomRepositoryImpl
    ): IdiomRepository
}