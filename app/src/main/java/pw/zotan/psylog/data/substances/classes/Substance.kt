package pw.zotan.psylog.data.substances.classes

import pw.zotan.psylog.data.substances.AdministrationRoute
import pw.zotan.psylog.data.substances.classes.roa.Roa
import pw.zotan.psylog.ui.utils.getInteractionExplanationURLForSubstance

data class Substance(
    val name: String,
    val commonNames: List<String>,
    val url: String,
    val isApproved: Boolean,
    val tolerance: Tolerance?,
    val crossTolerances: List<String>,
    val addictionPotential: String?,
    val toxicities: List<String>,
    val categories: List<String>,
    val summary: String?,
    val effectsSummary: String?,
    val dosageRemark: String?,
    val generalRisks: String?,
    val longtermRisks: String?,
    val saferUse: List<String>,
    val interactions: Interactions?,
    val roas: List<Roa>,
) {
    fun getRoa(route: AdministrationRoute): Roa? {
        return roas.firstOrNull { it.route == route }
    }

    val hasInteractions: Boolean get() {
        return if (interactions == null) {
            false
        } else {
            interactions.uncertain.isNotEmpty() || interactions.unsafe.isNotEmpty() || interactions.dangerous.isNotEmpty()
        }
    }

    val isHallucinogen
        get() = categories.any {
            val hallucinogens = setOf(
                "hallucinogen",
                "psychedelic",
                "dissociative",
                "deliriant",
            )
            hallucinogens.contains(it.lowercase())
        }
    val isStimulant
        get() = categories.any {
            val stimulants = setOf(
                "stimulant",
            )
            stimulants.contains(it.lowercase())
        }

    val interactionExplanationURL get() = getInteractionExplanationURLForSubstance(url)
}