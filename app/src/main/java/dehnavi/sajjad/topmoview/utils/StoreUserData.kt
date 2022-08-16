package dehnavi.sajjad.topmoview.utils

import android.content.Context
import android.preference.PreferenceDataStore
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StoreUserData @Inject constructor(@ApplicationContext val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(Constant.USER_INFO_DATA_STORE)
        val userToken = stringPreferencesKey(Constant.USER_TOKEN)
    }

    suspend fun saveUserToken(token: String) {
        context.dataStore.edit {
            it[userToken] = token
        }
    }

    fun getUserToken() = context.dataStore.data.map {
        it[userToken] ?: ""
    }
}