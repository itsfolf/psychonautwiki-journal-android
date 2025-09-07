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
import pw.zotan.psylog.ui.main.navigation.SaferUseTopLevelRoute
import pw.zotan.psylog.ui.tabs.safer.DoseExplanationScreen
import pw.zotan.psylog.ui.tabs.safer.DoseGuideScreen
import pw.zotan.psylog.ui.tabs.safer.DrugTestingScreen
import pw.zotan.psylog.ui.tabs.safer.ReagentTestingScreen
import pw.zotan.psylog.ui.tabs.safer.RouteExplanationScreen
import pw.zotan.psylog.ui.tabs.safer.SaferHallucinogensScreen
import pw.zotan.psylog.ui.tabs.safer.SaferUseScreen
import pw.zotan.psylog.ui.tabs.safer.VolumetricDosingScreen
import pw.zotan.psylog.ui.tabs.search.substance.SaferStimulantsScreen
import kotlinx.serialization.Serializable

fun NavGraphBuilder.saferGraph(navController: NavHostController) {
    navigation<SaferUseTopLevelRoute>(
        startDestination = SaferUseScreenRoute
    ) {
        composableWithTransitions<SaferUseScreenRoute> {
            SaferUseScreen(
                navigateToDrugTestingScreen = {
                    navController.navigate(DrugTestingRoute)
                },
                navigateToSaferHallucinogensScreen = {
                    navController.navigate(SaferHallucinogensRoute)
                },
                navigateToVolumetricDosingScreen = {
                    navController.navigate(VolumetricDosingOnSaferTabRoute)
                },
                navigateToDosageGuideScreen = {
                    navController.navigate(DosageGuideRoute)
                },
                navigateToDosageClassificationScreen = {
                    navController.navigate(DosageExplanationRouteOnSaferTab)
                },
                navigateToRouteExplanationScreen = {
                    navController.navigate(AdministrationRouteExplanationRouteOnSaferTab)
                },
                navigateToReagentTestingScreen = {
                    navController.navigate(ReagentTestingRoute)
                }
            )
        }
        composableWithTransitions<SaferHallucinogensRoute> { SaferHallucinogensScreen() }
        composableWithTransitions<SaferStimulantsRoute> { SaferStimulantsScreen() }
        composableWithTransitions<DosageExplanationRouteOnSaferTab> { DoseExplanationScreen() }
        composableWithTransitions<AdministrationRouteExplanationRouteOnSaferTab> {
            RouteExplanationScreen()
        }
        composableWithTransitions<DrugTestingRoute> { DrugTestingScreen() }
        composableWithTransitions<DosageGuideRoute> {
            DoseGuideScreen(
                navigateToDoseClassification = {
                    navController.navigate(DosageExplanationRouteOnSaferTab)
                },
                navigateToVolumetricDosing = {
                    navController.navigate(VolumetricDosingOnSaferTabRoute)
                },
            )
        }
        composableWithTransitions<VolumetricDosingOnSaferTabRoute> {
            VolumetricDosingScreen()
        }
        composableWithTransitions<ReagentTestingRoute> {
            ReagentTestingScreen()
        }
    }
}


@Serializable
object SaferUseScreenRoute

@Serializable
object SaferHallucinogensRoute

@Serializable
object SaferStimulantsRoute

@Serializable
object DosageExplanationRouteOnSaferTab

@Serializable
object ReagentTestingRoute

@Serializable
object DrugTestingRoute

@Serializable
object AdministrationRouteExplanationRouteOnSaferTab

@Serializable
object DosageGuideRoute

@Serializable
object VolumetricDosingOnSaferTabRoute
