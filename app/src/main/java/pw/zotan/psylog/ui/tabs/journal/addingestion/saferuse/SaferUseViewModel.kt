package pw.zotan.psylog.ui.tabs.journal.addingestion.saferuse

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import pw.zotan.psylog.data.substances.repositories.SubstanceRepository
import pw.zotan.psylog.ui.main.navigation.graphs.CheckSaferUseRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SaferUseViewModel @Inject constructor(
    substanceRepo: SubstanceRepository,
    state: SavedStateHandle,
) : ViewModel() {
    val substanceName = state.toRoute<CheckSaferUseRoute>().substanceName
    val substance = substanceRepo.getSubstance(substanceName)!!
}