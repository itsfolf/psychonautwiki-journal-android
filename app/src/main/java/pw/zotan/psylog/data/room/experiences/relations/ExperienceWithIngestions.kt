package pw.zotan.psylog.data.room.experiences.relations

import androidx.room.Embedded
import androidx.room.Relation
import pw.zotan.psylog.data.room.experiences.entities.Experience
import pw.zotan.psylog.data.room.experiences.entities.Ingestion

data class ExperienceWithIngestions(
    @Embedded val experience: Experience,
    @Relation(
        entity = Ingestion::class,
        parentColumn = "id",
        entityColumn = "experienceId"
    ) val ingestions: List<Ingestion>
)