package pw.zotan.psylog.ui.tabs.search.substancerow

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import pw.zotan.psylog.ui.tabs.search.CategoryModel
import pw.zotan.psylog.ui.tabs.search.SubstanceModel

class SubstanceModelPreviewProvider : PreviewParameterProvider<SubstanceModel> {
    override val values: Sequence<SubstanceModel> = sequenceOf(
        SubstanceModel(
            name = "Example Substance",
            commonNames = listOf("Hat", "Boot", "Hoodie", "Shirt", "Blouse"),
            categories = listOf(
                CategoryModel(
                    name = "common",
                    color = Color.Blue
                ),
                CategoryModel(
                    name = "psychedelic",
                    color = Color.Magenta
                )
            ),
            hasSaferUse = false,
            hasInteractions = false
        )
    )
}