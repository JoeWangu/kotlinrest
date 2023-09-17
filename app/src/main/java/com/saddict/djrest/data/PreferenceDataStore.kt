package com.saddict.djrest.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

//data class Token(val token: String)

//private const val USER_PREFERENCES_NAME = "user_preferences"
//private const val LAYOUT_PREFERENCES_NAME = "layout_preferences"
private const val TOKEN = "user_token"

// Create a DataStore instance using the preferencesDataStore delegate, with the Context as
// receiver.
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = TOKEN
)

/**
 * Class that handles saving and retrieving user preferences
 */
class PreferenceDataStore(
//    dataStore: DataStore<Preferences>,
    context: Context
) {
    private val pref = context.dataStore

    private object PreferencesKeys {
        val TOKEN_KEY = stringPreferencesKey("token_key")
    }

    val preferenceFlow: Flow<String> = pref.data
        .catch {
            if (it is IOException) {
                it.printStackTrace()
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            preferences[PreferencesKeys.TOKEN_KEY] ?: ""
        }

//    fun getToken() = pref.data.map {
//        Token(token = it[PreferencesKeys.TOKEN_KEY] ?: "")
//    }

    suspend fun setToken(token: String?){
        pref.edit { preferences ->
            preferences[PreferencesKeys.TOKEN_KEY] = token ?: ""
        }
    }
}

//class SettingsDataStore(context: Context) {
//    private val IS_LINEAR_LAYOUT_MANAGER = booleanPreferencesKey("is_linear_layout_manager")
//    suspend fun saveLayoutToPreferencesStore(isLinearLayoutManager: Boolean, context: Context) {
//        context.dataStore.edit { preferences ->
//            preferences[IS_LINEAR_LAYOUT_MANAGER] = isLinearLayoutManager
//        }
//    }
//    val preferenceFlow: Flow<Boolean> = context.dataStore.data
//        .catch {
//            if (it is IOException) {
//                it.printStackTrace()
//                emit(emptyPreferences())
//            } else {
//                throw it
//            }
//        }
//        .map { preferences ->
//            // On the first run of the app, we will use LinearLayoutManager by default
//            preferences[IS_LINEAR_LAYOUT_MANAGER] ?: true
//        }
//
//}