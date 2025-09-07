package pw.zotan.psylog.ui.tabs.search.substance.roa.dose

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import pw.zotan.psylog.data.substances.classes.roa.RoaDose

class RoaDosePreviewProvider : PreviewParameterProvider<RoaDose> {
    override val values: Sequence<RoaDose> = sequenceOf(
        RoaDose(
            "mg",
            lightMin = 20.0,
            commonMin = 40.0,
            strongMin = 90.0,
            heavyMin = 140.0,
        )
    )
}