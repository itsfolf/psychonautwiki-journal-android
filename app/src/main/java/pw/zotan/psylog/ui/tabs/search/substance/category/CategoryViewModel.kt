package pw.zotan.psylog.ui.tabs.search.substance.category

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import pw.zotan.psylog.data.substances.repositories.SubstanceRepository
import pw.zotan.psylog.ui.main.navigation.graphs.CategoryRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    substanceRepo: SubstanceRepository,
    state: SavedStateHandle
) : ViewModel() {
    private val categoryName = state.toRoute<CategoryRoute>().categoryName
    val category = substanceRepo.getCategory(categoryName)
}