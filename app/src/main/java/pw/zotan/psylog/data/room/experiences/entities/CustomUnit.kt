package pw.zotan.psylog.data.room.experiences.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import pw.zotan.psylog.data.substances.AdministrationRoute
import pw.zotan.psylog.ui.tabs.search.substance.roa.toReadableString
import java.time.Instant

@Entity
data class CustomUnit(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val substanceName: String,
    var name: String,
    val creationDate: Instant = Instant.now(),
    val administrationRoute: AdministrationRoute,
    var dose: Double?,
    var estimatedDoseStandardDeviation: Double?,
    var isEstimate: Boolean,
    var isArchived: Boolean,
    var unit: String,
    var unitPlural: String? = null,
    var originalUnit: String,
    var note: String
) {

    fun getDoseOfOneUnitDescription(): String {
        return this.dose?.let { unwrappedDose ->
            if (this.isEstimate) {
                this.estimatedDoseStandardDeviation?.let { estimatedDoseStandardDeviationUnwrapped ->
                    "${unwrappedDose.toReadableString()}±${estimatedDoseStandardDeviationUnwrapped.toReadableString()} ${this.originalUnit}"
                } ?: "~${unwrappedDose.toReadableString()} ${this.originalUnit}"
            } else {
                "${unwrappedDose.toReadableString()} ${this.originalUnit}"
            }
        } ?: "Unknown dose"
    }

    fun getPluralizableUnit(): PluralizableUnit {
        val plural = unitPlural
        if (plural == null) {
            val calculatedPlural =
                if (unit != "mg" && unit != "g" && unit.lowercase() != "ml" && unit.lastOrNull() != 's') {
                    unit + "s"
                } else {
                    unit
                }
            return PluralizableUnit(singular = unit, plural = calculatedPlural)
        } else {
            return PluralizableUnit(singular = unit, plural = plural)
        }
    }

    companion object {
        var mdmaSample = CustomUnit(
            substanceName = "MDMA",
            name = "Capsule",
            administrationRoute = AdministrationRoute.ORAL,
            dose = 40.0,
            estimatedDoseStandardDeviation = null,
            isEstimate = false,
            isArchived = false,
            unit = "capsule",
            unitPlural = "capsules",
            originalUnit = "mg",
            note = "this is a note"
        )

        var twoCBSample = CustomUnit(
            substanceName = "2C-B",
            name = "Pink rocket",
            administrationRoute = AdministrationRoute.ORAL,
            dose = 14.0,
            estimatedDoseStandardDeviation = 4.0,
            isEstimate = true,
            isArchived = false,
            unit = "pill",
            unitPlural = "pills",
            originalUnit = "mg",
            note = "this is a note"
        )
    }
}

data class PluralizableUnit(
    val singular: String,
    val plural: String
)
