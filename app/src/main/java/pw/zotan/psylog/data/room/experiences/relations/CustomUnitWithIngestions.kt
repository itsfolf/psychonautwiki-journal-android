package pw.zotan.psylog.data.room.experiences.relations

import androidx.room.Embedded
import androidx.room.Relation
import pw.zotan.psylog.data.room.experiences.entities.CustomUnit
import pw.zotan.psylog.data.room.experiences.entities.Ingestion

data class CustomUnitWithIngestions(
    @Embedded val customUnit: CustomUnit,
    @Relation(
        entity = Ingestion::class,
        parentColumn = "id",
        entityColumn = "customUnitId"
    ) val ingestions: List<Ingestion>
)