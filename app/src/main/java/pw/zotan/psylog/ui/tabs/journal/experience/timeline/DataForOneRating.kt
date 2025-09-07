package pw.zotan.psylog.ui.tabs.journal.experience.timeline

import pw.zotan.psylog.data.room.experiences.entities.ShulginRatingOption
import java.time.Instant

data class DataForOneRating(
    val time: Instant,
    val option: ShulginRatingOption
)

