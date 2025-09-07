package pw.zotan.psylog.ui.tabs.journal.experience.components.timednote

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import pw.zotan.psylog.data.room.experiences.entities.AdaptiveColor
import pw.zotan.psylog.data.room.experiences.entities.TimedNote
import java.time.Instant

class TimedNotePreviewProvider : PreviewParameterProvider<TimedNote> {
    override val values: Sequence<TimedNote> = sequenceOf(
        TimedNote(
            time = Instant.now(),
            note = "Hello my name is",
            color = AdaptiveColor.PURPLE,
            experienceId = 0,
            isPartOfTimeline = true
        ),
        TimedNote(
            time = Instant.now(),
            note = "Hello my name is",
            color = AdaptiveColor.PURPLE,
            experienceId = 0,
            isPartOfTimeline = false
        )
    )
}