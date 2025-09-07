package pw.zotan.psylog.ui.tabs.journal.experience.models

import pw.zotan.psylog.ui.tabs.journal.experience.TimelineDisplayOption
import pw.zotan.psylog.ui.tabs.journal.experience.components.DataForOneEffectLine

data class ConsumerWithIngestions(
    val consumerName: String,
    val ingestionElements: List<IngestionElement>,
    val dataForEffectLines: List<DataForOneEffectLine>,
    val timelineDisplayOption: TimelineDisplayOption
)