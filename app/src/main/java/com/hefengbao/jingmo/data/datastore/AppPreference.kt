/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.hefengbao.jingmo.common.Constant
import com.hefengbao.jingmo.data.model.AppStatus
import com.hefengbao.jingmo.data.model.theme.DarkThemeConfig
import com.hefengbao.jingmo.data.model.theme.ThemeBrand
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.app: DataStore<Preferences> by preferencesDataStore(name = Constant.DATASTORE_NAME)

private fun getLong(context: Context, key: Preferences.Key<Long>, default: Long = 0L): Flow<Long> =
    context.app.data.map { it[key] ?: default }

private suspend fun setLong(context: Context, key: Preferences.Key<Long>, value: Long) {
    context.app.edit { it[key] = value }
}

private fun getInt(context: Context, key: Preferences.Key<Int>, default: Int = 0): Flow<Int> =
    context.app.data.map { it[key] ?: default }

private suspend fun setInt(context: Context, key: Preferences.Key<Int>, value: Int) {
    context.app.edit { it[key] = value }
}

private suspend fun setString(context: Context, key: Preferences.Key<String>, value: String) {
    context.app.edit { it[key] = value }
}

private suspend fun setBoolean(context: Context, key: Preferences.Key<Boolean>, value: Boolean) {
    context.app.edit { it[key] = value }
}

class AppPreference(
    private val context: Context
) {
    val appStatus: Flow<AppStatus> = context.app.data.map {
        AppStatus(
            captureTextColorString = it[PREF_CAPTURE_TEXT_COLOR] ?: "#FFFFFF",
            captureBackgroundColorString = it[PREF_CAPTURE_BACKGROUND_COLOR] ?: "#065279",
            themeBrand = ThemeBrand.from(it[PREF_THEME_BRAND] ?: ThemeBrand.DEFAULT.name),
            darkThemeConfig = DarkThemeConfig.from(
                it[PREF_DARK_THEME_CONFIG] ?: DarkThemeConfig.FOLLOW_SYSTEM.name
            ),
            useDynamicColor = it[PREF_USE_DYNAMIC_COLOR] ?: false,
            showSyncDataTip = it[PREF_SHOW_SYNC_DATA_TIP] ?: true,
            userAgreementVersion = it[PREF_USER_AGREEMENT_VERSION] ?: 0,
        )
    }

    suspend fun setCaptureTextColor(color: String) =
        setString(context, PREF_CAPTURE_TEXT_COLOR, color)

    suspend fun setCaptureBackgroundColor(color: String) =
        setString(context, PREF_CAPTURE_BACKGROUND_COLOR, color)

    suspend fun setThemeBrand(brand: ThemeBrand) =
        setString(context, PREF_THEME_BRAND, brand.name)

    suspend fun setDarkThemeConfig(config: DarkThemeConfig) =
        setString(context, PREF_DARK_THEME_CONFIG, config.name)

    suspend fun setUseDynamicColor(useDynamicColor: Boolean) =
        setBoolean(context, PREF_USE_DYNAMIC_COLOR, useDynamicColor)

    suspend fun setShowSyncDataTip(show: Boolean) =
        setBoolean(context, PREF_SHOW_SYNC_DATA_TIP, show)

    suspend fun setUserAgreementVersion(version: Int) =
        setInt(context, PREF_USER_AGREEMENT_VERSION, version)

    companion object {
        private val PREF_CAPTURE_TEXT_COLOR = stringPreferencesKey("key_capture_text_color")
        private val PREF_CAPTURE_BACKGROUND_COLOR =
            stringPreferencesKey("key_capture_background_color")
        private val PREF_THEME_BRAND = stringPreferencesKey("key_theme_brand")
        private val PREF_DARK_THEME_CONFIG = stringPreferencesKey("key_dark_theme_config")
        private val PREF_USE_DYNAMIC_COLOR = booleanPreferencesKey("key_use_dynamic_color")
        private val PREF_SHOW_SYNC_DATA_TIP = booleanPreferencesKey("key_show_sync_data_tip")
        private val PREF_USER_AGREEMENT_VERSION = intPreferencesKey("key_user_agreement_version")
    }
}