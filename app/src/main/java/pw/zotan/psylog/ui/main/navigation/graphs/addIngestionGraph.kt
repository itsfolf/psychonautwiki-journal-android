package pw.zotan.psylog.ui.main.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import androidx.navigation.toRoute
import pw.zotan.psylog.data.substances.AdministrationRoute
import pw.zotan.psylog.ui.main.navigation.composableWithTransitions
import pw.zotan.psylog.ui.tabs.journal.addingestion.dose.ChooseDoseScreen
import pw.zotan.psylog.ui.tabs.journal.addingestion.dose.customsubstance.CustomSubstanceChooseDoseScreen
import pw.zotan.psylog.ui.tabs.journal.addingestion.dose.customunit.ChooseDoseCustomUnitScreen
import pw.zotan.psylog.ui.tabs.journal.addingestion.interactions.CheckInteractionsScreen
import pw.zotan.psylog.ui.tabs.journal.addingestion.route.ChooseRouteScreen
import pw.zotan.psylog.ui.tabs.journal.addingestion.route.CustomSubstanceChooseRouteScreen
import pw.zotan.psylog.ui.tabs.journal.addingestion.saferuse.CheckSaferUseScreen
import pw.zotan.psylog.ui.tabs.journal.addingestion.search.AddIngestionSearchScreen
import pw.zotan.psylog.ui.tabs.journal.addingestion.time.FinishIngestionScreen
import pw.zotan.psylog.ui.tabs.safer.RouteExplanationScreen
import pw.zotan.psylog.ui.tabs.search.custom.AddCustomSubstanceAndContinueScreen
import pw.zotan.psylog.ui.tabs.settings.customunits.add.FinishAddCustomUnitScreen
import kotlinx.serialization.Serializable

fun NavGraphBuilder.addIngestionGraph(navController: NavController) {
    navigation<AddIngestionRoute>(
        startDestination = AddIngestionSearchRoute,
    ) {
        composableWithTransitions<AddIngestionSearchRoute> {
            AddIngestionSearchScreen(
                navigateToCheckInteractions = { substanceName ->
                    navController.navigate(CheckInteractionsRoute(substanceName))
                },
                navigateToCheckSaferUse = { substanceName ->
                    navController.navigate(CheckSaferUseRoute(substanceName))
                },
                navigateToCustomSubstanceChooseRoute = { customSubstanceName ->
                    navController.navigate(CustomSubstanceChooseRouteRoute(customSubstanceName))
                },
                navigateToChooseTime = { substanceName, administrationRoute, dose, units, isEstimate, estimatedDoseStandardDeviation, customUnitId ->
                    navController.navigate(
                        FinishIngestionRoute(
                            administrationRoute = administrationRoute,
                            units = units,
                            isEstimate = isEstimate,
                            dose = dose,
                            estimatedDoseStandardDeviation = estimatedDoseStandardDeviation,
                            substanceName = substanceName,
                            customUnitId = customUnitId,
                        )
                    )
                },
                navigateToChooseCustomSubstanceDose = { customSubstanceName, administrationRoute ->
                    navController.navigate(
                        ChooseCustomSubstanceDoseRoute(
                            customSubstanceName = customSubstanceName,
                            administrationRoute = administrationRoute

                        )
                    )
                },
                navigateToDose = { substanceName, administrationRoute ->
                    navController.navigate(
                        ChooseDoseRoute(
                            substanceName = substanceName,
                            administrationRoute = administrationRoute
                        )
                    )
                },
                navigateToChooseRoute = { substanceName ->
                    navController.navigate(ChooseRouteOfAddIngestionRoute(substanceName = substanceName))
                },
                navigateToAddCustomSubstanceScreen = { searchText ->
                    navController.navigate(AddCustomSubstanceRouteOnAddIngestionGraph(searchText = searchText))
                },
                navigateToCustomUnitChooseDose = { customUnitId ->
                    navController.navigate(ChooseDoseCustomUnitRoute(customUnitId = customUnitId))
                }
            )
        }
        composableWithTransitions<AddCustomSubstanceRouteOnAddIngestionGraph> { backStackEntry ->
            val route = backStackEntry.toRoute<AddCustomSubstanceRouteOnAddIngestionGraph>()
            AddCustomSubstanceAndContinueScreen(
                navigateToChooseRoa = { customSubstanceName ->
                    navController.navigate(CustomSubstanceChooseRouteRoute(customSubstanceName)) {
                        popUpTo(AddIngestionSearchRoute)
                    }
                },
                initialName = route.searchText
            )
        }
        composableWithTransitions<CheckInteractionsRoute> { backStackEntry ->
            val route = backStackEntry.toRoute<CheckInteractionsRoute>()
            CheckInteractionsScreen(
                navigateToNext = {
                    navController.navigate(ChooseRouteOfAddIngestionRoute(substanceName = route.substanceName))
                },
            )
        }
        composableWithTransitions<CheckSaferUseRoute> { backStackEntry ->
            val route = backStackEntry.toRoute<CheckSaferUseRoute>()
            CheckSaferUseScreen(
                navigateToNext = {
                    navController.navigate(CheckInteractionsRoute(substanceName = route.substanceName))
                },
            )
        }
        composableWithTransitions<ChooseDoseCustomUnitRoute> {
            ChooseDoseCustomUnitScreen(
                navigateToChooseTimeAndMaybeColor = { administrationRoute: AdministrationRoute,
                                                      units: String?,
                                                      isEstimate: Boolean,
                                                      dose: Double?,
                                                      estimatedDoseStandardDeviation: Double?,
                                                      substanceName: String,
                                                      customUnitId: Int? ->
                    navController.navigate(
                        FinishIngestionRoute(
                            administrationRoute = administrationRoute,
                            isEstimate = isEstimate,
                            units = units,
                            dose = dose,
                            estimatedDoseStandardDeviation = estimatedDoseStandardDeviation,
                            substanceName = substanceName,
                            customUnitId = customUnitId,
                        )
                    )
                },
                navigateToCreateCustomUnit = { administrationRoute, substanceName ->
                    navController.navigate(
                        FinishAddCustomUnitRoute(
                            administrationRoute = administrationRoute,
                            substanceName = substanceName
                        )
                    )
                }
            )
        }
        composableWithTransitions<ChooseRouteOfAddIngestionRoute> { backStackEntry ->
            val route = backStackEntry.toRoute<ChooseRouteOfAddIngestionRoute>()
            ChooseRouteScreen(
                navigateToChooseDose = { administrationRoute ->
                    navController.navigate(
                        ChooseDoseRoute(
                            substanceName = route.substanceName,
                            administrationRoute = administrationRoute
                        )
                    )
                },
                navigateToRouteExplanationScreen = {
                    navController.navigate(AdministrationRouteExplanationRouteOnJournalTab)
                }
            )
        }
        composableWithTransitions<CustomSubstanceChooseRouteRoute> { backStackEntry ->
            val route = backStackEntry.toRoute<CustomSubstanceChooseRouteRoute>()
            CustomSubstanceChooseRouteScreen(
                onRouteTap = { administrationRoute ->
                    navController.navigate(
                        ChooseCustomSubstanceDoseRoute(
                            customSubstanceName = route.customSubstanceName,
                            administrationRoute = administrationRoute
                        )
                    )
                }
            )
        }
        composableWithTransitions<ChooseCustomSubstanceDoseRoute> { backStackEntry ->
            val route = backStackEntry.toRoute<ChooseCustomSubstanceDoseRoute>()
            CustomSubstanceChooseDoseScreen(
                navigateToChooseTimeAndMaybeColor = { units, isEstimate, dose, estimatedDoseStandardDeviation ->
                    navController.navigate(
                        FinishIngestionRoute(
                            administrationRoute = route.administrationRoute,
                            units = units,
                            isEstimate = isEstimate,
                            dose = dose,
                            estimatedDoseStandardDeviation = estimatedDoseStandardDeviation,
                            substanceName = route.customSubstanceName,
                            customUnitId = null
                        )
                    )
                },
                navigateToCreateCustomUnit = {
                    navController.navigate(
                        FinishAddCustomUnitRoute(
                            substanceName = route.customSubstanceName,
                            administrationRoute = route.administrationRoute
                        )
                    )
                },
                navigateToSaferSniffingScreen = {
                    navController.navigate(SaferSniffingRouteOnJournalTab)
                },
            )
        }
        composableWithTransitions<ChooseDoseRoute> { backStackEntry ->
            val route = backStackEntry.toRoute<ChooseDoseRoute>()
            ChooseDoseScreen(
                navigateToChooseTimeAndMaybeColor = { units, isEstimate, dose, estimatedDoseStandardDeviation ->
                    navController.navigate(
                        FinishIngestionRoute(
                            administrationRoute = route.administrationRoute,
                            units = units,
                            isEstimate = isEstimate,
                            dose = dose,
                            estimatedDoseStandardDeviation = estimatedDoseStandardDeviation,
                            substanceName = route.substanceName,
                            customUnitId = null,
                        )
                    )
                },
                navigateToVolumetricDosingScreenOnJournalTab = {
                    navController.navigate(VolumetricDosingOnJournalTabRoute)
                },
                navigateToSaferSniffingScreen = {
                    navController.navigate(SaferSniffingRouteOnJournalTab)
                },
                navigateToCreateCustomUnit = {
                    navController.navigate(
                        FinishAddCustomUnitRoute(
                            substanceName = route.substanceName,
                            administrationRoute = route.administrationRoute
                        )
                    )
                }
            )
        }
        composableWithTransitions<FinishIngestionRoute> {
            FinishIngestionScreen(
                dismissAddIngestionScreens = {
                    navController.popBackStack(route = AddIngestionRoute, inclusive = true)
                },
            )
        }
        composableWithTransitions<AdministrationRouteExplanationRouteOnJournalTab> {
            RouteExplanationScreen()
        }
        composableWithTransitions<FinishAddCustomUnitRoute> {
            FinishAddCustomUnitScreen(
                dismissAddCustomUnit = { customUnitId ->
                    navController.navigate(ChooseDoseCustomUnitRoute(customUnitId = customUnitId)) {
                        popUpTo(AddIngestionSearchRoute)
                    }
                },
            )
        }
    }
}

@Serializable
object AddIngestionRoute

@Serializable
object AddIngestionSearchRoute

@Serializable
data class CheckInteractionsRoute(val substanceName: String)

@Serializable
data class CheckSaferUseRoute(val substanceName: String)

@Serializable
data class ChooseDoseCustomUnitRoute(val customUnitId: Int)

@Serializable
data class ChooseRouteOfAddIngestionRoute(val substanceName: String)

@Serializable
data class CustomSubstanceChooseRouteRoute(val customSubstanceName: String)

@Serializable
data class ChooseCustomSubstanceDoseRoute(
    val customSubstanceName: String,
    val administrationRoute: AdministrationRoute,
)

@Serializable
data class ChooseDoseRoute(
    val substanceName: String,
    val administrationRoute: AdministrationRoute
)

@Serializable
data class FinishIngestionRoute(
    val administrationRoute: AdministrationRoute,
    val isEstimate: Boolean,
    val units: String?,
    val dose: Double?,
    val estimatedDoseStandardDeviation: Double?,
    val substanceName: String, // can be name of pw substance or custom substance
    val customUnitId: Int?,
)

@Serializable
object AdministrationRouteExplanationRouteOnJournalTab

@Serializable
data class AddCustomSubstanceRouteOnAddIngestionGraph(val searchText: String)