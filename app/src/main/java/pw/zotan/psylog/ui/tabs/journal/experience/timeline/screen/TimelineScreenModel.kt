package pw.zotan.psylog.ui.tabs.journal.experience.timeline.screen

import pw.zotan.psylog.data.room.experiences.entities.ShulginRating
import pw.zotan.psylog.data.room.experiences.entities.TimedNote
import pw.zotan.psylog.ui.tabs.journal.experience.components.DataForOneEffectLine
import pw.zotan.psylog.ui.tabs.journal.experience.components.TimeDisplayOption

data class TimelineScreenModel(
    val title: String,
    val dataForEffectLines: List<DataForOneEffectLine>,
    val ratings: List<ShulginRating>,
    val timedNotes: List<TimedNote>,
    val timeDisplayOption: TimeDisplayOption,
    val areSubstanceHeightsIndependent: Boolean,
)