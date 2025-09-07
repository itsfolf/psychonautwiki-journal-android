package pw.zotan.psylog.data.room.experiences.relations

import androidx.room.Embedded
import androidx.room.Relation
import pw.zotan.psylog.data.room.experiences.entities.Experience
import pw.zotan.psylog.data.room.experiences.entities.Ingestion
import java.time.Instant

data class ExperienceWithIngestionsAndCompanions(
    @Embedded val experience: Experience,
    @Relation(
        entity = Ingestion::class,
        parentColumn = "id",
        entityColumn = "experienceId"
    ) val ingestionsWithCompanionAndCustomUnit: List<IngestionWithCompanionAndCustomUnit>
) {
    val sortInstant: Instant
        get() = ingestionsWithCompanionAndCustomUnit.firstOrNull()?.ingestion?.time ?: experience.sortDate
}
