package pw.zotan.psylog.ui.tabs.journal.addingestion.search.suggestion

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import pw.zotan.psylog.data.room.experiences.entities.AdaptiveColor
import pw.zotan.psylog.data.room.experiences.entities.CustomUnit
import pw.zotan.psylog.data.substances.AdministrationRoute
import pw.zotan.psylog.ui.tabs.journal.addingestion.search.suggestion.models.CustomUnitDoseSuggestion
import pw.zotan.psylog.ui.tabs.journal.addingestion.search.suggestion.models.DoseAndUnit
import pw.zotan.psylog.ui.tabs.journal.addingestion.search.suggestion.models.Suggestion
import pw.zotan.psylog.ui.utils.getInstant

class SubstanceSuggestionProvider : PreviewParameterProvider<Suggestion> {
    override val values: Sequence<Suggestion> = sequenceOf(
        Suggestion.PureSubstanceSuggestion(
            adaptiveColor = AdaptiveColor.PINK,
            administrationRoute = AdministrationRoute.ORAL,
            substanceName = "MDMA",
            dosesAndUnit = listOf(
                DoseAndUnit(
                    dose = 50.0,
                    unit = "mg",
                    isEstimate = false,
                    estimatedDoseStandardDeviation = null
                ),
                DoseAndUnit(
                    dose = 100.0,
                    unit = "mg",
                    isEstimate = false,
                    estimatedDoseStandardDeviation = null
                ),
                DoseAndUnit(
                    dose = null,
                    unit = "mg",
                    isEstimate = false,
                    estimatedDoseStandardDeviation = null
                ),
            ),
            sortInstant = getInstant(year = 2023, month = 4, day = 10, hourOfDay = 5, minute = 20)!!,
        ),
        Suggestion.CustomUnitSuggestion(
            customUnit = CustomUnit.mdmaSample,
            adaptiveColor = AdaptiveColor.PINK,
            dosesAndUnit = listOf(
                CustomUnitDoseSuggestion(
                    dose = 2.0,
                    isEstimate = false,
                    estimatedDoseStandardDeviation = null
                ),
                CustomUnitDoseSuggestion(
                    dose = 3.0,
                    isEstimate = false,
                    estimatedDoseStandardDeviation = null
                )
            ),
            sortInstant = getInstant(year = 2023, month = 4, day = 10, hourOfDay = 5, minute = 20)!!
        )
    )
}