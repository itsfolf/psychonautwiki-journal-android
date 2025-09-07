/*
 * Copyright (c) 2022-2023. Isaak Hanimann.
 * This file is part of PsychonautWiki Journal.
 *
 * PsychonautWiki Journal is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at
 * your option) any later version.
 *
 * PsychonautWiki Journal is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with PsychonautWiki Journal.  If not, see https://www.gnu.org/licenses/gpl-3.0.en.html.
 */

package pw.zotan.psylog.ui.main.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import pw.zotan.psylog.ui.main.navigation.composableWithTransitions
import pw.zotan.psylog.ui.main.navigation.StatsTopLevelRoute
import pw.zotan.psylog.ui.tabs.stats.StatsScreen
import pw.zotan.psylog.ui.tabs.stats.substancecompanion.SubstanceCompanionScreen
import kotlinx.serialization.Serializable

fun NavGraphBuilder.statsGraph(navController: NavHostController) {
    navigation<StatsTopLevelRoute>(
        startDestination = StatsScreenRoute,
    ) {
        composableWithTransitions<StatsScreenRoute> {
            StatsScreen(
                navigateToSubstanceCompanion = { substanceName, consumerName ->
                    navController.navigate(
                        SubstanceCompanionRoute(
                            substanceName = substanceName,
                            consumerName = consumerName
                        )
                    )
                },
            )
        }
        composableWithTransitions<SubstanceCompanionRoute> {
            SubstanceCompanionScreen()
        }
    }
}

@Serializable
object StatsScreenRoute

@Serializable
data class SubstanceCompanionRoute(val substanceName: String, val consumerName: String?)