package pw.zotan.psylog.ui.tabs.search

data class SubstanceModel(
    val name: String,
    val commonNames: List<String>,
    val categories: List<CategoryModel>,
    val hasSaferUse: Boolean,
    val hasInteractions: Boolean
)