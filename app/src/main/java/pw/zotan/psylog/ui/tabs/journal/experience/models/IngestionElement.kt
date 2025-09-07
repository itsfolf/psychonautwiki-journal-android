package pw.zotan.psylog.ui.tabs.journal.experience.models

import pw.zotan.psylog.data.room.experiences.relations.IngestionWithCompanionAndCustomUnit
import pw.zotan.psylog.data.substances.classes.roa.RoaDuration

data class IngestionElement(
    val ingestionWithCompanionAndCustomUnit: IngestionWithCompanionAndCustomUnit,
    val roaDuration: RoaDuration?,
    val numDots: Int?
)