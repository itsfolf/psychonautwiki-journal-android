package pw.zotan.psylog.ui.tabs.journal.experience.timeline

import pw.zotan.psylog.data.room.experiences.entities.AdaptiveColor
import java.time.Instant

data class DataForOneTimedNote(
    val time: Instant,
    val color: AdaptiveColor
)
