package pw.zotan.psylog.data.room.experiences.relations

import androidx.room.Embedded
import androidx.room.Relation
import pw.zotan.psylog.data.room.experiences.entities.CustomUnit
import pw.zotan.psylog.data.room.experiences.entities.Experience
import pw.zotan.psylog.data.room.experiences.entities.Ingestion

data class IngestionWithExperienceAndCustomUnit(
    @Embedded
    var ingestion: Ingestion,

    @Relation(
        parentColumn = "experienceId",
        entityColumn = "id"
    )
    var experience: Experience,

    @Relation(
        parentColumn = "customUnitId",
        entityColumn = "id"
    )
    var customUnit: CustomUnit?
)