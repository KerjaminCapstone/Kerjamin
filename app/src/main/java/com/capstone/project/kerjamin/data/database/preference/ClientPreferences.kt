package com.capstone.project.kerjamin.data.database.preference

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.capstone.project.kerjamin.data.database.response.ResponseLogin
import com.capstone.project.kerjamin.data.helper.setClientIdlingResource
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "tokenClient")
class ClientPreferences @Inject constructor(@ApplicationContext val context: Context) {

    private val dataStore = context.dataStore

    suspend fun saveToken(login: ResponseLogin){
        setClientIdlingResource {
            dataStore.edit { preferences ->
                preferences[MESSAGE_ACCOUNT_KEY] = login.message
                preferences[TOKEN_ACCOUNT_KEY] = login.token
            }
        }
    }

    fun getToken(): Flow<ResponseLogin> {
        return dataStore.data.map { preferences ->
            ResponseLogin(
                preferences[MESSAGE_ACCOUNT_KEY]?:"",
                preferences[TOKEN_ACCOUNT_KEY]?:""
            )
        }
    }

    suspend fun logOut(){
        dataStore.edit {
            it.clear()
        }
    }

    companion object {
        private val MESSAGE_ACCOUNT_KEY = stringPreferencesKey("token")
        private val TOKEN_ACCOUNT_KEY = stringPreferencesKey("token")
    }

}