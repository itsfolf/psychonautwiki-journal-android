package pw.zotan.psylog.ui.main

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

val ARE_CONDITIONS_ACCEPTED = booleanPreferencesKey("are_conditions_accepted")

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : ViewModel() {

    val isAcceptedFlow: StateFlow<Boolean> = dataStore.data
        .map { preferences ->
            preferences[ARE_CONDITIONS_ACCEPTED] ?: false
        }.stateIn(
            initialValue = true,
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000)
        )

    fun accept() {
        viewModelScope.launch {
            dataStore.edit { settings ->
                settings[ARE_CONDITIONS_ACCEPTED] = true
            }
        }
    }
}