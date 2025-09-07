package pw.zotan.psylog.ui.tabs.stats

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import pw.zotan.psylog.data.room.experiences.entities.AdaptiveColor
import pw.zotan.psylog.data.substances.AdministrationRoute

class StatsPreviewProvider : PreviewParameterProvider<StatsModel> {
    override val values: Sequence<StatsModel> = sequenceOf(
        StatsModel(
            selectedOption = TimePickerOption.DAYS_7,
            areThereAnyIngestions = true,
            startDateText = "22. June 2022",
            consumerName = null,
            statItems = listOf(
                StatItem(
                    substanceName = "LSD",
                    color = AdaptiveColor.BLUE,
                    experienceCount = 3,
                    ingestionCount = 3,
                    routeCounts = listOf(
                        RouteCount(
                            administrationRoute = AdministrationRoute.SUBLINGUAL,
                            count = 3
                        )
                    ),
                    totalDose = TotalDose(
                        dose = 500.0,
                        units = "ug",
                        isEstimate = false,
                        estimatedDoseStandardDeviation = null
                    )
                ),
                StatItem(
                    substanceName = "MDMA",
                    color = AdaptiveColor.PINK,
                    ingestionCount = 8,
                    experienceCount = 2,
                    routeCounts = listOf(
                        RouteCount(
                            administrationRoute = AdministrationRoute.ORAL,
                            count = 6
                        ),
                        RouteCount(
                            administrationRoute = AdministrationRoute.INSUFFLATED,
                            count = 2
                        )
                    ),
                    totalDose = TotalDose(
                        dose = 950.0,
                        units = "mg",
                        isEstimate = true,
                        estimatedDoseStandardDeviation = 50.0
                    )
                ),
                StatItem(
                    substanceName = "Cocaine",
                    color = AdaptiveColor.ORANGE,
                    ingestionCount = 11,
                    experienceCount = 1,
                    routeCounts = listOf(
                        RouteCount(
                            administrationRoute = AdministrationRoute.INSUFFLATED,
                            count = 11
                        )
                    ),
                    totalDose = null
                ),
                StatItem(
                    substanceName = "Ketamine",
                    color = AdaptiveColor.PURPLE,
                    experienceCount = 1,
                    ingestionCount = 1,
                    routeCounts = listOf(
                        RouteCount(
                            administrationRoute = AdministrationRoute.INSUFFLATED,
                            count = 1
                        )
                    ),
                    totalDose = TotalDose(
                        dose = 30.0,
                        units = "mg",
                        isEstimate = true,
                        estimatedDoseStandardDeviation = 5.0
                    )
                )
            ),
            chartBuckets = listOf(
                listOf(
                    ColorCount(
                        color = AdaptiveColor.PINK,
                        count = 2
                    ),
                    ColorCount(
                        color = AdaptiveColor.BLUE,
                        count = 3
                    ),
                ),
                listOf(),
                listOf(),
                listOf(),
                listOf(),
                listOf(
                    ColorCount(
                        color = AdaptiveColor.ORANGE,
                        count = 1
                    ),
                ),
                listOf(
                    ColorCount(
                        color = AdaptiveColor.PURPLE,
                        count = 1
                    )
                )
            ),
        )
    )
}