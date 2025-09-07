package pw.zotan.psylog.ui.tabs.settings.customunits.archive

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import pw.zotan.psylog.data.room.experiences.ExperienceRepository
import pw.zotan.psylog.data.room.experiences.entities.CustomUnit
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CustomUnitArchiveViewModel @Inject constructor(
    experienceRepository: ExperienceRepository,
) : ViewModel() {

    val customUnitsFlow: StateFlow<List<CustomUnit>> = experienceRepository.getCustomUnitsFlow(true).stateIn(
        initialValue = emptyList(),
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000)
    )
}