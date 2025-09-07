package pw.zotan.psylog.ui.main.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import androidx.navigation.toRoute
import pw.zotan.psylog.data.substances.AdministrationRoute
import pw.zotan.psylog.ui.main.navigation.composableWithTransitions
import pw.zotan.psylog.ui.tabs.journal.addingestion.route.CustomSubstanceChooseRouteScreen
import pw.zotan.psylog.ui.tabs.settings.customunits.add.ChooseRouteDuringAddCustomUnitScreen
import pw.zotan.psylog.ui.tabs.settings.customunits.add.AddIngestionSearchScreen
import pw.zotan.psylog.ui.tabs.settings.customunits.add.FinishAddCustomUnitScreen
import kotlinx.serialization.Serializable

fun NavGraphBuilder.addCustomUnitGraph(navController: NavController) {
    navigation<AddCustomUnitsParentRoute>(
        startDestination = AddCustomUnitsChooseSubstanceScreenRoute,
    ) {
        composableWithTransitions<AddCustomUnitsChooseSubstanceScreenRoute> {
            AddIngestionSearchScreen(
                navigateToChooseRoute = { substanceName ->
                    navController.navigate(ChooseRouteOfAddCustomUnitRoute(substanceName))
                },
                navigateToCustomSubstanceChooseRoute = { customSubstanceName ->
                    navController.navigate(CustomSubstanceChooseRouteRoute(customSubstanceName))
                }
            )
        }
        composableWithTransitions<CustomSubstanceChooseRouteRoute> { backStackEntry ->
            val route = backStackEntry.toRoute<CustomSubstanceChooseRouteRoute>()
            CustomSubstanceChooseRouteScreen(
                onRouteTap = { administrationRoute ->
                    navController.navigate(
                        FinishAddCustomUnitRoute(
                            administrationRoute = administrationRoute,
                            substanceName = route.customSubstanceName,
                        )
                    )
                }
            )
        }
        composableWithTransitions<ChooseRouteOfAddCustomUnitRoute> { backStackEntry ->
            val route = backStackEntry.toRoute<ChooseRouteOfAddCustomUnitRoute>()
            ChooseRouteDuringAddCustomUnitScreen(
                onRouteChosen = { administrationRoute ->
                    navController.navigate(
                        FinishAddCustomUnitRoute(
                            administrationRoute = administrationRoute,
                            substanceName = route.substanceName,
                        )
                    )
                }
            )
        }
        composableWithTransitions<FinishAddCustomUnitRoute> {
            FinishAddCustomUnitScreen(
                dismissAddCustomUnit = {
                    navController.popBackStack(
                        route = AddCustomUnitsChooseSubstanceScreenRoute,
                        inclusive = true
                    )
                },
            )
        }
    }
}

@Serializable
object AddCustomUnitsParentRoute

@Serializable
object AddCustomUnitsChooseSubstanceScreenRoute

@Serializable
data class ChooseRouteOfAddCustomUnitRoute(val substanceName: String)

@Serializable
data class FinishAddCustomUnitRoute(
    val administrationRoute: AdministrationRoute,
    val substanceName: String,
)
