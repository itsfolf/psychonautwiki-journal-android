package pw.zotan.psylog.ui.tabs.settings.colors

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import pw.zotan.psylog.data.room.experiences.entities.AdaptiveColor
import pw.zotan.psylog.data.room.experiences.entities.SubstanceCompanion
import pw.zotan.psylog.ui.tabs.journal.addingestion.time.ColorPicker
import pw.zotan.psylog.ui.theme.horizontalPadding

@Composable
fun SubstanceColorsScreen(
    viewModel: SubstanceColorsViewModel = hiltViewModel(),
) {
    LaunchedEffect(Unit) {
        viewModel.deleteUnusedSubstanceCompanions()
    }
    SubstanceColorsScreenContent(
        substanceCompanions = viewModel.substanceCompanionsFlow.collectAsState().value,
        updateColor = viewModel::updateColor,
        alreadyUsedColors = viewModel.alreadyUsedColorsFlow.collectAsState().value,
        otherColors = viewModel.otherColorsFlow.collectAsState().value
    )
}

@Preview
@Composable
fun SubstanceColorsScreenPreview() {
    val alreadyUsedColors = listOf(AdaptiveColor.OLIVE, AdaptiveColor.AUBURN)
    val otherColors = AdaptiveColor.entries.filter { !alreadyUsedColors.contains(it) }
    SubstanceColorsScreenContent(
        substanceCompanions = listOf(
            SubstanceCompanion(substanceName = "Substance 1", color = AdaptiveColor.AUBURN),
            SubstanceCompanion(substanceName = "Substance 2", color = AdaptiveColor.BLUE),
            SubstanceCompanion(
                substanceName = "Substance with longer 3",
                color = AdaptiveColor.YELLOW
            ),
            SubstanceCompanion(substanceName = "Substance 4", color = AdaptiveColor.OLIVE),
        ),
        updateColor = { _, _ -> },
        alreadyUsedColors = alreadyUsedColors,
        otherColors = otherColors

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubstanceColorsScreenContent(
    substanceCompanions: List<SubstanceCompanion>,
    updateColor: (color: AdaptiveColor, substanceName: String) -> Unit,
    alreadyUsedColors: List<AdaptiveColor>,
    otherColors: List<AdaptiveColor>
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Substance colors") })
        },
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = horizontalPadding)
                .fillMaxSize()
        ) {
            items(substanceCompanions) { substanceCompanion ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = substanceCompanion.substanceName,
                        style = MaterialTheme.typography.titleMedium
                    )
                    ColorPicker(
                        selectedColor = substanceCompanion.color,
                        onChangeOfColor = {
                            updateColor(it, substanceCompanion.substanceName)
                        },
                        alreadyUsedColors = alreadyUsedColors,
                        otherColors = otherColors
                    )
                }
                HorizontalDivider()
            }
        }
    }
}