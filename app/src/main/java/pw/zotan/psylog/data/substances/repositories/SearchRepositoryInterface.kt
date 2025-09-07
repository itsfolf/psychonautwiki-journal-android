package pw.zotan.psylog.data.substances.repositories

import pw.zotan.psylog.data.substances.classes.SubstanceWithCategories

interface SearchRepositoryInterface {
    fun getMatchingSubstances(
    searchText: String,
    filterCategories: List<String>,
    recentlyUsedSubstanceNamesSorted: List<String>,
    ): List<SubstanceWithCategories>
}