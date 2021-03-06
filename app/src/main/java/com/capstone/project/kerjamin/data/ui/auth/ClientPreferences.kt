package com.capstone.project.kerjamin.data.ui.auth

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.capstone.project.kerjamin.data.ui.auth.response.ResponseLogin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ClientPreferences  private constructor(private val dataStore: DataStore<Preferences>) {

    fun getToken(): Flow<ResponseLogin>{
        return dataStore.data.map { preferences ->
            ResponseLogin(
                preferences[ACCOUNT_ERROR_KEY]?:false,
                preferences[ACCOUNT_TOKEN_KEY]?:"",
                preferences[ACCOUNT_STATE_KEY]?:false
            )
        }
    }

    suspend fun saveToken(login: ResponseLogin){
        dataStore.edit { preferences ->
            preferences[ACCOUNT_ERROR_KEY]= login.error
            preferences[ACCOUNT_TOKEN_KEY]= login.token
            preferences[ACCOUNT_STATE_KEY]= true
        }
    }

    suspend fun logOut(){
        dataStore.edit { preferences ->
            preferences[ACCOUNT_STATE_KEY] = false
        }
    }

    companion object{
        @Volatile
        private var INSTANCE_CLIENT : ClientPreferences? = null

        private val ACCOUNT_ERROR_KEY = booleanPreferencesKey("error")
        private val ACCOUNT_TOKEN_KEY = stringPreferencesKey("token")
        private val ACCOUNT_STATE_KEY = booleanPreferencesKey("isLogin")

        fun getInstanceClient(dataStore: DataStore<Preferences>): ClientPreferences {
            return INSTANCE_CLIENT ?: synchronized(this){
                val instanceClient = ClientPreferences(dataStore)
                INSTANCE_CLIENT = instanceClient
                instanceClient
            }
        }
    }
}