package pw.zotan.psylog.ui.main.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import pw.zotan.psylog.ui.main.navigation.composableWithTransitions
import pw.zotan.psylog.ui.main.navigation.SettingsTopLevelRoute
import pw.zotan.psylog.ui.tabs.settings.DonateScreen
import pw.zotan.psylog.ui.tabs.settings.FAQScreen
import pw.zotan.psylog.ui.tabs.settings.SettingsScreen
import pw.zotan.psylog.ui.tabs.settings.colors.SubstanceColorsScreen
import pw.zotan.psylog.ui.tabs.settings.combinations.CombinationSettingsScreen
import pw.zotan.psylog.ui.tabs.settings.customunits.CustomUnitsScreen
import pw.zotan.psylog.ui.tabs.settings.customunits.archive.CustomUnitArchiveScreen
import pw.zotan.psylog.ui.tabs.settings.customunits.edit.EditCustomUnitScreen
import kotlinx.serialization.Serializable

fun NavGraphBuilder.settingsGraph(navController: NavHostController) {
    navigation<SettingsTopLevelRoute>(
        startDestination = SettingsScreenRoute,
    ) {
        composableWithTransitions<SettingsScreenRoute> {
            SettingsScreen(
                navigateToFAQ = {
                    navController.navigate(FAQRoute)
                },
                navigateToComboSettings = {
                    navController.navigate(CombinationSettingsRoute)
                },
                navigateToSubstanceColors = {
                    navController.navigate(SubstanceColorsRoute)
                },
                navigateToCustomUnits = {
                    navController.navigate(CustomUnitsRoute)
                },
                navigateToDonate = {
                    navController.navigate(DonateRoute)
                },
            )
        }
        composableWithTransitions<FAQRoute> { FAQScreen() }
        composableWithTransitions<DonateRoute> { DonateScreen() }
        composableWithTransitions<CombinationSettingsRoute> { CombinationSettingsScreen() }
        composableWithTransitions<SubstanceColorsRoute> { SubstanceColorsScreen() }
        composableWithTransitions<CustomUnitArchiveRoute> {
            CustomUnitArchiveScreen(navigateToEditCustomUnit = { customUnitId ->
                navController.navigate(EditCustomUnitRoute(customUnitId))
            })
        }
        addCustomUnitGraph(navController)
        composableWithTransitions<CustomUnitsRoute> {
            CustomUnitsScreen(
                navigateToAddCustomUnit = {
                    navController.navigate(AddCustomUnitsParentRoute)
                },
                navigateToEditCustomUnit = { customUnitId ->
                    navController.navigate(EditCustomUnitRoute(customUnitId))
                },
                navigateToCustomUnitArchive = {
                    navController.navigate(CustomUnitArchiveRoute)
                }
            )
        }
        composableWithTransitions<EditCustomUnitRoute> {
            EditCustomUnitScreen(navigateBack = navController::popBackStack)
        }
    }
}

@Serializable
object SettingsScreenRoute

@Serializable
object FAQRoute

@Serializable
object DonateRoute

@Serializable
object CombinationSettingsRoute

@Serializable
object SubstanceColorsRoute

@Serializable
object CustomUnitArchiveRoute

@Serializable
object CustomUnitsRoute

@Serializable
data class EditCustomUnitRoute(val customUnitId: Int)
