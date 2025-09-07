package pw.zotan.psylog.ui.tabs.search.substance.roa.duration

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import pw.zotan.psylog.data.substances.classes.roa.DurationRange
import pw.zotan.psylog.data.substances.classes.roa.DurationUnits
import pw.zotan.psylog.data.substances.classes.roa.RoaDuration

class RoaDurationPreviewProvider : PreviewParameterProvider<RoaDuration> {
    override val values: Sequence<RoaDuration> = sequenceOf(
        RoaDuration(
            onset = DurationRange(
                min = 20f,
                max = 60f,
                units = DurationUnits.MINUTES
            ),
            comeup = DurationRange(
                min = 45f,
                max = 120f,
                units = DurationUnits.MINUTES
            ),
            peak = DurationRange(
                min = 3f,
                max = 5f,
                units = DurationUnits.HOURS
            ),
            offset = DurationRange(
                min = 3f,
                max = 5f,
                units = DurationUnits.HOURS
            ),
            total = DurationRange(
                min = 8f,
                max = 12f,
                units = DurationUnits.HOURS
            ),
            afterglow = DurationRange(
                min = 6f,
                max = 24f,
                units = DurationUnits.HOURS
            )
        ),
        RoaDuration(
            onset = null,
            comeup = DurationRange(
                min = 45f,
                max = 120f,
                units = DurationUnits.MINUTES
            ),
            peak = DurationRange(
                min = 3f,
                max = 5f,
                units = DurationUnits.HOURS
            ),
            offset = DurationRange(
                min = 3f,
                max = 5f,
                units = DurationUnits.HOURS
            ),
            total = DurationRange(
                min = 8f,
                max = 12f,
                units = DurationUnits.HOURS
            ),
            afterglow = DurationRange(
                min = 6f,
                max = 24f,
                units = DurationUnits.HOURS
            )
        ),
        RoaDuration(
            onset = DurationRange(
                min = 20f,
                max = 60f,
                units = DurationUnits.MINUTES
            ),
            comeup = null,
            peak = DurationRange(
                min = 3f,
                max = 5f,
                units = DurationUnits.HOURS
            ),
            offset = DurationRange(
                min = 3f,
                max = 5f,
                units = DurationUnits.HOURS
            ),
            total = DurationRange(
                min = 8f,
                max = 12f,
                units = DurationUnits.HOURS
            ),
            afterglow = DurationRange(
                min = 6f,
                max = 24f,
                units = DurationUnits.HOURS
            )
        ),
        RoaDuration(
            onset = DurationRange(
                min = 20f,
                max = 60f,
                units = DurationUnits.MINUTES
            ),
            comeup = DurationRange(
                min = 45f,
                max = 120f,
                units = DurationUnits.MINUTES
            ),
            peak = null,
            offset = DurationRange(
                min = 3f,
                max = 5f,
                units = DurationUnits.HOURS
            ),
            total = DurationRange(
                min = 8f,
                max = 12f,
                units = DurationUnits.HOURS
            ),
            afterglow = DurationRange(
                min = 6f,
                max = 24f,
                units = DurationUnits.HOURS
            )
        ),
        RoaDuration(
            onset = DurationRange(
                min = 20f,
                max = 60f,
                units = DurationUnits.MINUTES
            ),
            comeup = DurationRange(
                min = 45f,
                max = 120f,
                units = DurationUnits.MINUTES
            ),
            peak = DurationRange(
                min = 3f,
                max = 5f,
                units = DurationUnits.HOURS
            ),
            offset = null,
            total = DurationRange(
                min = 8f,
                max = 12f,
                units = DurationUnits.HOURS
            ),
            afterglow = DurationRange(
                min = 6f,
                max = 24f,
                units = DurationUnits.HOURS
            )
        ),
        RoaDuration(
            onset = null,
            comeup = null,
            peak = DurationRange(
                min = 3f,
                max = 5f,
                units = DurationUnits.HOURS
            ),
            offset = DurationRange(
                min = 3f,
                max = 5f,
                units = DurationUnits.HOURS
            ),
            total = DurationRange(
                min = 8f,
                max = 12f,
                units = DurationUnits.HOURS
            ),
            afterglow = DurationRange(
                min = 6f,
                max = 24f,
                units = DurationUnits.HOURS
            )
        ),
        RoaDuration(
            onset = null,
            comeup = DurationRange(
                min = 45f,
                max = 120f,
                units = DurationUnits.MINUTES
            ),
            peak = null,
            offset = DurationRange(
                min = 3f,
                max = 5f,
                units = DurationUnits.HOURS
            ),
            total = DurationRange(
                min = 8f,
                max = 12f,
                units = DurationUnits.HOURS
            ),
            afterglow = DurationRange(
                min = 6f,
                max = 24f,
                units = DurationUnits.HOURS
            )
        ),
        RoaDuration(
            onset = null,
            comeup = DurationRange(
                min = 45f,
                max = 120f,
                units = DurationUnits.MINUTES
            ),
            peak = DurationRange(
                min = 3f,
                max = 5f,
                units = DurationUnits.HOURS
            ),
            offset = null,
            total = DurationRange(
                min = 8f,
                max = 12f,
                units = DurationUnits.HOURS
            ),
            afterglow = DurationRange(
                min = 6f,
                max = 24f,
                units = DurationUnits.HOURS
            )
        ),
        RoaDuration(
            onset = DurationRange(
                min = 20f,
                max = 60f,
                units = DurationUnits.MINUTES
            ),
            comeup = null,
            peak = null,
            offset = DurationRange(
                min = 3f,
                max = 5f,
                units = DurationUnits.HOURS
            ),
            total = DurationRange(
                min = 8f,
                max = 12f,
                units = DurationUnits.HOURS
            ),
            afterglow = DurationRange(
                min = 6f,
                max = 24f,
                units = DurationUnits.HOURS
            )
        ),
        RoaDuration(
            onset = DurationRange(
                min = 20f,
                max = 60f,
                units = DurationUnits.MINUTES
            ),
            comeup = null,
            peak = DurationRange(
                min = 3f,
                max = 5f,
                units = DurationUnits.HOURS
            ),
            offset = null,
            total = DurationRange(
                min = 8f,
                max = 12f,
                units = DurationUnits.HOURS
            ),
            afterglow = DurationRange(
                min = 6f,
                max = 24f,
                units = DurationUnits.HOURS
            )
        ),
        RoaDuration(
            onset = null,
            comeup = null,
            peak = null,
            offset = DurationRange(
                min = 3f,
                max = 5f,
                units = DurationUnits.HOURS
            ),
            total = DurationRange(
                min = 8f,
                max = 12f,
                units = DurationUnits.HOURS
            ),
            afterglow = DurationRange(
                min = 6f,
                max = 24f,
                units = DurationUnits.HOURS
            )
        ),
        RoaDuration(
            onset = DurationRange(
                min = 20f,
                max = 60f,
                units = DurationUnits.MINUTES
            ),
            comeup = null,
            peak = null,
            offset = null,
            total = DurationRange(
                min = 8f,
                max = 12f,
                units = DurationUnits.HOURS
            ),
            afterglow = DurationRange(
                min = 6f,
                max = 24f,
                units = DurationUnits.HOURS
            )
        ),
        RoaDuration(
            onset = null,
            comeup = DurationRange(
                min = 45f,
                max = 120f,
                units = DurationUnits.MINUTES
            ),
            peak = null,
            offset = null,
            total = DurationRange(
                min = 8f,
                max = 12f,
                units = DurationUnits.HOURS
            ),
            afterglow = DurationRange(
                min = 6f,
                max = 24f,
                units = DurationUnits.HOURS
            )
        ),
        RoaDuration(
            onset = null,
            comeup = null,
            peak = DurationRange(
                min = 3f,
                max = 5f,
                units = DurationUnits.HOURS
            ),
            offset = null,
            total = DurationRange(
                min = 8f,
                max = 12f,
                units = DurationUnits.HOURS
            ),
            afterglow = DurationRange(
                min = 6f,
                max = 24f,
                units = DurationUnits.HOURS
            )
        ),
    )
}