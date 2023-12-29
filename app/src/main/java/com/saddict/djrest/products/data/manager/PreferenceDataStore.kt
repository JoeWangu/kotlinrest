package com.saddict.djrest.products.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import java.io.IOException

//data class Token(val token: String)

//private const val USER_PREFERENCES_NAME = "user_preferences"
//private const val LAYOUT_PREFERENCES_NAME = "layout_preferences"
//private
const val TOKEN = "user_token"

// Create a DataStore instance using the preferencesDataStore delegate, with the Context as
// receiver.
val Context.tokenDataStore: DataStore<Preferences> by preferencesDataStore(
    name = TOKEN
)

/**
 * Class that handles saving and retrieving user preferences
 */
class PreferenceDataStore(
//    dataStore: DataStore<Preferences>,
    context: Context
) {
    private val pref = context.tokenDataStore
//    private val pref = dataStore

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

    fun getToken(): String {
        var token: String
        runBlocking {
            token = preferenceFlow.first()
        }
        return token
    }

//    suspend fun getToken(): String?{
//        return pref.data.collect().toString()
//    }

    suspend fun setToken(token: String?){
        pref.edit { preferences ->
            preferences[PreferencesKeys.TOKEN_KEY] = token ?: ""
        }
    }

/*    companion object {
        @Volatile private var INSTANCE: PreferenceDataStore? = null

        fun getInstance(context: Context): PreferenceDataStore =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: PreferenceDataStore(context.dataStore).also { INSTANCE = it }
            }
    }*/
}