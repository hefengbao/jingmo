package com.hefengbao.jingmo.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.hefengbao.jingmo.common.Constant
import com.hefengbao.jingmo.data.model.AppStatus
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

class AppPreference(
    private val context: Context
) {
    val dataStats: Flow<AppStatus> = context.app.data.map {
        AppStatus(
            captureTextColor = it[PREF_CAPTURE_TEXT_COLOR] ?: "white",
            captureBackgroundColor = it[PREF_CAPTURE_BACKGROUND_COLOR] ?: "#065279",
        )
    }

    suspend fun setCaptureTextColor(color: String) = setString(context, PREF_CAPTURE_TEXT_COLOR, color)
    suspend fun setCaptureBackgroundColor(color: String) = setString(context, PREF_CAPTURE_BACKGROUND_COLOR, color)

    companion object {
        private val PREF_CAPTURE_TEXT_COLOR = stringPreferencesKey("key_capture_text_color")
        private val PREF_CAPTURE_BACKGROUND_COLOR =
            stringPreferencesKey("key_capture_background_color")
    }
}