package pw.zotan.psylog.ui.tabs.journal.experience.components

import pw.zotan.psylog.data.room.experiences.entities.AdaptiveColor
import pw.zotan.psylog.data.substances.AdministrationRoute
import pw.zotan.psylog.data.substances.classes.roa.RoaDuration
import java.time.Instant

data class DataForOneEffectLine(
    val substanceName: String,
    val route: AdministrationRoute,
    val roaDuration: RoaDuration?,
    val height: Float,
    val horizontalWeight: Float,
    val color: AdaptiveColor,
    val startTime: Instant,
    val endTime: Instant?
)