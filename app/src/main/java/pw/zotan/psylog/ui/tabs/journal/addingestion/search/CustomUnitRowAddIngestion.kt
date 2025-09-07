package pw.zotan.psylog.ui.tabs.journal.addingestion.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pw.zotan.psylog.data.room.experiences.entities.CustomUnit
import pw.zotan.psylog.ui.theme.horizontalPadding

@Composable
fun CustomUnitRowAddIngestion(
    customUnit: CustomUnit,
    navigateToCustomUnitChooseDose: (customUnitId: Int) -> Unit
) {
    Column(
        modifier = Modifier
            .clickable {
                navigateToCustomUnitChooseDose(customUnit.id)
            }
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = horizontalPadding),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "${customUnit.substanceName} ${customUnit.administrationRoute.displayText.lowercase()}, ${customUnit.name}",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "${customUnit.getDoseOfOneUnitDescription()} per ${customUnit.unit}",
            style = MaterialTheme.typography.titleSmall
        )
    }
}