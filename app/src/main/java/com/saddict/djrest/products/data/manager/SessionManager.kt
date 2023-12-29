package com.saddict.djrest.products.data.manager

/*import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.saddict.djrest.R
import kotlinx.coroutines.flow.first*/

//val Context.dataStore by preferencesDataStore("user_preferences")
//
//class SessionManager(private val context: Context) {
//    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
//    companion object{
//        const val USER_TOKEN = "user_token"
//    }
//    /**
//     * Function to save auth token
//     */
//    fun saveAuthToken(token: String) {
//        val editor = prefs.edit()
//        editor.putString(USER_TOKEN, token)
//        editor.apply()
//    }
//
//    /**
//     * Function to fetch auth token
//     */
//    fun fetchAuthToken(): String? {
//        return prefs.getString(USER_TOKEN, null)
//    }
//
///*    suspend fun saveAuthToken(token: String) {
//        context.dataStore.edit { preferences ->
//            preferences[KEY_AUTH_TOKEN] = token
//        }
//    }
//
//    suspend fun getAuthToken(): String? {
//        val preferences = context.dataStore.data.first()
//        return preferences[KEY_AUTH_TOKEN]
//    }
//
//    companion object {
//        private val KEY_AUTH_TOKEN = stringPreferencesKey("auth_token")
//    }*/
//}