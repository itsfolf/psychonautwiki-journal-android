package pw.zotan.psylog.ui.tabs.journal.calendar

import androidx.lifecycle.ViewModel
import pw.zotan.psylog.data.room.experiences.ExperienceRepository
import pw.zotan.psylog.data.room.experiences.relations.ExperienceWithIngestionsCompanionsAndRatings
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ExperienceFetchViewModel @Inject constructor(
    val experienceRepo: ExperienceRepository,
) : ViewModel() {

    suspend fun getExperience(experienceId: Int): ExperienceWithIngestionsCompanionsAndRatings? {
        return experienceRepo.getExperienceWithIngestionsCompanionsAndRatings(experienceId)
    }
}