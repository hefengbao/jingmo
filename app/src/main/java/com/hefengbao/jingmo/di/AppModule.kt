package com.hefengbao.jingmo.di

import android.content.Context
import com.hefengbao.jingmo.App
import com.hefengbao.jingmo.data.datastore.AppPreference
import com.hefengbao.jingmo.data.datastore.DatasetPreference
import com.hefengbao.jingmo.data.datastore.HomePreference
import com.hefengbao.jingmo.data.datastore.ReadStatusPreference
import com.hefengbao.jingmo.data.network.fake.FakeAssetManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApp(): App = App()

    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    @Provides
    @Singleton
    fun providesFakeAssetManager(
        @ApplicationContext context: Context,
    ): FakeAssetManager = FakeAssetManager(context.assets::open)

    @Provides
    @Singleton
    fun providesAppPreference(
        @ApplicationContext context: Context
    ): AppPreference = AppPreference(context)

    @Provides
    @Singleton
    fun providesDatasetPreference(
        @ApplicationContext context: Context
    ): DatasetPreference = DatasetPreference(context)

    @Provides
    @Singleton
    fun providesHomePreference(
        @ApplicationContext context: Context
    ): HomePreference = HomePreference(context)

    @Provides
    @Singleton
    fun providesReadStatusPreference(
        @ApplicationContext context: Context
    ): ReadStatusPreference = ReadStatusPreference(context)
}