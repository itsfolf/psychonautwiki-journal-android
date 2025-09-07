package pw.zotan.psylog.ui.tabs.journal.experience.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import pw.zotan.psylog.data.substances.AdministrationRoute
import pw.zotan.psylog.ui.tabs.journal.experience.models.CumulativeDose
import pw.zotan.psylog.ui.tabs.journal.experience.models.CumulativeRouteAndDose

@Composable
fun CumulativeDoseRow(
    cumulativeDose: CumulativeDose,
    areDosageDotsHidden: Boolean,
    modifier: Modifier
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = cumulativeDose.substanceName,
            style = MaterialTheme.typography.titleMedium
        )
        cumulativeDose.cumulativeRouteAndDose.forEach { cumulativeRouteAndDose ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val text = buildAnnotatedString {
                    append(cumulativeRouteAndDose.doseDescription)
                    withStyle(style = SpanStyle(color = Color.Gray)) {
                        append(" " + cumulativeRouteAndDose.route.displayText.lowercase())
                    }
                }
                Text(text = text, style = MaterialTheme.typography.titleSmall)
                val numDots = cumulativeRouteAndDose.numDots
                if (numDots != null && !areDosageDotsHidden) {
                    DotRows(numDots = numDots)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CumulativeDoseRowPreview() {
    CumulativeDoseRow(
        cumulativeDose = CumulativeDose(
            substanceName = "Amphetamine",
            cumulativeRouteAndDose = listOf(
                CumulativeRouteAndDose(
                    cumulativeDose = 30.0,
                    units = "mg",
                    isEstimate = false,
                    cumulativeDoseStandardDeviation = 12.0,
                    numDots = 6,
                    route = AdministrationRoute.INSUFFLATED,
                    hasMoreThanOneIngestion = true
                ),
                CumulativeRouteAndDose(
                    cumulativeDose = 25.0,
                    units = "mg",
                    isEstimate = false,
                    cumulativeDoseStandardDeviation = 12.0,
                    numDots = 5,
                    route = AdministrationRoute.ORAL,
                    hasMoreThanOneIngestion = true
                )
            )
        ),
        areDosageDotsHidden = false,
        modifier = Modifier.fillMaxWidth()
    )
}