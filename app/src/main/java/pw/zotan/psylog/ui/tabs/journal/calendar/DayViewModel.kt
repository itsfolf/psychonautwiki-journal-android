package pw.zotan.psylog.ui.tabs.journal.calendar

import androidx.lifecycle.ViewModel
import pw.zotan.psylog.data.room.experiences.ExperienceRepository
import pw.zotan.psylog.data.room.experiences.entities.AdaptiveColor
import pw.zotan.psylog.ui.utils.getInstant
import com.kizitonwose.calendar.core.CalendarDay
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class DayViewModel @Inject constructor(
    val experienceRepo: ExperienceRepository,
) : ViewModel() {

    suspend fun getExperienceInfo(day: CalendarDay): ExperienceInfo {
        val startOfDay = day.date.atStartOfDay().getInstant()
        val endOfDay = startOfDay.plusMillis(24 * 60 * 60 * 1000)
        val ingestions = experienceRepo.getIngestionsWithCompanions(
            fromInstant = startOfDay,
            toInstant = endOfDay
        )
        return ExperienceInfo(
            experienceIds = ingestions.map { it.ingestion.experienceId }.toSet().toList(),
            colors = ingestions.mapNotNull { it.substanceCompanion?.color }
        )
    }
}

data class ExperienceInfo(
    val experienceIds: List<Int>,
    val colors: List<AdaptiveColor>
)