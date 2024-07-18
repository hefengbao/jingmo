package com.hefengbao.jingmo.data.repository.settings

import com.hefengbao.jingmo.data.model.AppStatus
import com.hefengbao.jingmo.data.model.theme.DarkThemeConfig
import com.hefengbao.jingmo.data.model.theme.ThemeBrand
import kotlinx.coroutines.flow.Flow

interface ThemeRepository {
    val appStatus: Flow<AppStatus>

    /**
     * Sets the desired theme brand.
     */
    suspend fun setThemeBrand(themeBrand: ThemeBrand)

    /**
     * Sets the desired dark theme config.
     */
    suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig)

    /**
     * Sets the preferred dynamic color config.
     */
    suspend fun setDynamicColorPreference(useDynamicColor: Boolean)
}