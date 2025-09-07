package pw.zotan.psylog.data.substances.classes.roa

import pw.zotan.psylog.data.substances.AdministrationRoute

data class Roa(
    val route: AdministrationRoute,
    val roaDose: RoaDose?,
    val roaDuration: RoaDuration?,
    val bioavailability: Bioavailability?
)