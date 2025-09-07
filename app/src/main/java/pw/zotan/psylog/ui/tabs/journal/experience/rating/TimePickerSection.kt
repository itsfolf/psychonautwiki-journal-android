package pw.zotan.psylog.ui.tabs.journal.experience.rating

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import pw.zotan.psylog.ui.tabs.journal.addingestion.time.DatePickerButton
import pw.zotan.psylog.ui.tabs.journal.addingestion.time.TimePickerButton
import pw.zotan.psylog.ui.tabs.journal.experience.components.CardWithTitle
import pw.zotan.psylog.ui.utils.getDateWithWeekdayText
import pw.zotan.psylog.ui.utils.getShortTimeText
import java.time.LocalDateTime

@Preview
@Composable
fun TimePickerSectionPreview() {
    TimePickerSection(selectedTime = LocalDateTime.now(), onTimeChange = {})
}

@Composable
fun TimePickerSection(
    selectedTime: LocalDateTime,
    onTimeChange: (LocalDateTime) -> Unit,
) {
    CardWithTitle(title = "Time") {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            DatePickerButton(
                localDateTime = selectedTime,
                onChange = onTimeChange,
                dateString = selectedTime.getDateWithWeekdayText(),
                modifier = Modifier.fillMaxWidth()
            )
            TimePickerButton(
                localDateTime = selectedTime,
                onChange = onTimeChange,
                timeString = selectedTime.getShortTimeText(),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}