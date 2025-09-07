package pw.zotan.psylog.ui.tabs.stats.substancecompanion

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import pw.zotan.psylog.data.room.experiences.ExperienceRepository
import pw.zotan.psylog.data.room.experiences.entities.CustomUnit
import pw.zotan.psylog.data.room.experiences.entities.Experience
import pw.zotan.psylog.data.room.experiences.entities.Ingestion
import pw.zotan.psylog.data.room.experiences.entities.SubstanceCompanion
import pw.zotan.psylog.data.substances.repositories.SubstanceRepository
import pw.zotan.psylog.ui.main.navigation.graphs.SubstanceCompanionRoute
import pw.zotan.psylog.ui.tabs.journal.addingestion.search.suggestion.models.CustomUnitDose
import pw.zotan.psylog.ui.tabs.search.substance.roa.toReadableString
import pw.zotan.psylog.ui.utils.getTimeDifferenceText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.time.Instant
import javax.inject.Inject

@HiltViewModel
class SubstanceCompanionViewModel @Inject constructor(
    experienceRepo: ExperienceRepository,
    substanceRepo: SubstanceRepository,
    state: SavedStateHandle
) : ViewModel() {

    private val currentTimeFlow: Flow<Instant> = flow {
        while (true) {
            emit(Instant.now())
            delay(timeMillis = 1000 * 10)
        }
    }

    private val substanceCompanionRoute = state.toRoute<SubstanceCompanionRoute>()
    private val substanceName = substanceCompanionRoute.substanceName
    val consumerName = substanceCompanionRoute.consumerName

    private val substance = substanceRepo.getSubstance(substanceName)
    val tolerance = substance?.tolerance
    val crossTolerances = substance?.crossTolerances ?: emptyList()

    val thisCompanionFlow: StateFlow<SubstanceCompanion?> =
        experienceRepo.getSubstanceCompanionFlow(substanceName).stateIn(
            initialValue = null,
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000)
        )

    val ingestionBurstsFlow: StateFlow<List<IngestionsBurst>> =
        experienceRepo.getSortedIngestionsWithExperienceAndCustomUnitFlow(substanceName)
            .map { list -> list.filter { it.ingestion.consumerName == consumerName } }
            .combine(currentTimeFlow) { sortedIngestionsWithExperiences, currentTime ->
                val experiencesWithIngestions =
                    sortedIngestionsWithExperiences.groupBy { it.ingestion.experienceId }
                var lastDate = currentTime
                val allIngestionBursts: MutableList<IngestionsBurst> = mutableListOf()
                for (oneExperience in experiencesWithIngestions) {
                    val experience = oneExperience.value.firstOrNull()?.experience ?: continue
                    val ingestionsSorted = oneExperience.value.map { IngestionsBurst.IngestionAndCustomUnit(
                        ingestion = it.ingestion,
                        customUnit = it.customUnit
                    ) }.sortedBy { it.ingestion.time }
                    val experienceStart = ingestionsSorted.first().ingestion.time
                    val experienceEnd = ingestionsSorted.last().ingestion.time
                    val diffText = getTimeDifferenceText(
                        fromInstant = experienceEnd,
                        toInstant = lastDate
                    )
                    allIngestionBursts.add(
                        IngestionsBurst(
                            timeUntil = diffText,
                            experience = experience,
                            ingestions = ingestionsSorted
                        )
                    )
                    lastDate = experienceStart
                }
                return@combine allIngestionBursts
            }.stateIn(
                initialValue = emptyList(),
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000)
            )
}

data class IngestionsBurst(
    val timeUntil: String,
    val experience: Experience,
    val ingestions: List<IngestionAndCustomUnit>
) {
    data class IngestionAndCustomUnit(
        val ingestion: Ingestion,
        val customUnit: CustomUnit?
    ) {
        val customUnitDose: CustomUnitDose?
            get() = ingestion.dose?.let { doseUnwrapped ->
                customUnit?.let { customUnitUnwrapped ->
                    CustomUnitDose(
                        dose = doseUnwrapped,
                        isEstimate = ingestion.isDoseAnEstimate,
                        estimatedDoseStandardDeviation = ingestion.estimatedDoseStandardDeviation,
                        customUnit = customUnitUnwrapped
                    )
                }
            }
        val doseDescription: String
            get() = customUnitDose?.doseDescription ?: ingestionDoseDescription

        private val ingestionDoseDescription get() = ingestion.dose?.let { dose ->
            ingestion.estimatedDoseStandardDeviation?.let { estimatedDoseStandardDeviation ->
                "${dose.toReadableString()}±${estimatedDoseStandardDeviation.toReadableString()} ${ingestion.units}"
            } ?: run {
                val description = "${dose.toReadableString()} ${ingestion.units}"
                if (ingestion.isDoseAnEstimate) {
                    "~$description"
                } else {
                    description
                }
            }
        } ?: "Unknown dose"
    }
}