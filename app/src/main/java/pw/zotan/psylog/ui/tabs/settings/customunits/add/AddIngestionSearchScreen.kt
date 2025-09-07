package pw.zotan.psylog.ui.tabs.settings.customunits.add


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Keyboard
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import pw.zotan.psylog.data.room.experiences.entities.CustomSubstance
import pw.zotan.psylog.ui.tabs.journal.addingestion.search.AddIngestionSearchViewModel
import pw.zotan.psylog.ui.tabs.journal.addingestion.search.SubstanceRowAddIngestion
import pw.zotan.psylog.ui.tabs.search.CategoryModel
import pw.zotan.psylog.ui.tabs.search.SubstanceModel
import pw.zotan.psylog.ui.tabs.search.customColor
import pw.zotan.psylog.ui.tabs.search.substancerow.SubstanceRow

@Composable
fun AddIngestionSearchScreen(
    navigateToChooseRoute: (substanceName: String) -> Unit,
    navigateToCustomSubstanceChooseRoute: (customSubstanceName: String) -> Unit,
    viewModel: AddIngestionSearchViewModel = hiltViewModel()
) {
    AddIngestionSearchScreen(
        navigateToChooseRoute = navigateToChooseRoute,
        navigateToCustomSubstanceChooseRoute = navigateToCustomSubstanceChooseRoute,
        searchText = viewModel.searchTextFlow.collectAsState().value,
        onChangeSearchText = {
            viewModel.updateSearchText(it)
        },
        filteredSubstances = viewModel.filteredSubstancesFlow.collectAsState().value,
        filteredCustomSubstances = viewModel.filteredCustomSubstancesFlow.collectAsState().value
    )
}

@Composable
private fun AddIngestionSearchScreen(
    navigateToChooseRoute: (substanceName: String) -> Unit,
    navigateToCustomSubstanceChooseRoute: (customSubstanceName: String) -> Unit,
    searchText: String,
    onChangeSearchText: (searchText: String) -> Unit,
    filteredSubstances: List<SubstanceModel>,
    filteredCustomSubstances: List<CustomSubstance>,
) {
    val focusRequester = remember { FocusRequester() }
    var isFocused by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    Scaffold(
        floatingActionButton = {
            if (!isFocused) {
                FloatingActionButton(onClick = { focusRequester.requestFocus() }) {
                    Icon(Icons.Default.Keyboard, contentDescription = "Keyboard")
                }
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            LinearProgressIndicator(
                progress = { 0.17f },
                modifier = Modifier
                    .fillMaxWidth()
                    .clearAndSetSemantics { },
            )
            TextField(
                value = searchText,
                onValueChange = { value ->
                    onChangeSearchText(value)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester)
                    .onFocusChanged { focusState ->
                        isFocused = focusState.isFocused
                    }
                    .clearAndSetSemantics { },
                placeholder = { Text(text = "Search substances") },
                leadingIcon = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "Search",
                    )
                },
                trailingIcon = {
                    Row {
                        if (searchText.isNotEmpty()) {
                            IconButton(onClick = {
                                onChangeSearchText("")
                            }) {
                                Icon(
                                    Icons.Default.Close,
                                    contentDescription = "Close",
                                )
                            }
                        }
                    }
                },
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                keyboardOptions = KeyboardOptions.Default.copy(
                    autoCorrectEnabled = false,
                    imeAction = ImeAction.Done,
                    capitalization = KeyboardCapitalization.Words,
                ),
                singleLine = true
            )
            LazyColumn {
                items(filteredSubstances) { substance ->
                    SubstanceRowAddIngestion(substanceModel = substance, onTap = {
                        navigateToChooseRoute(substance.name)
                    })
                    HorizontalDivider()
                }
                items(filteredCustomSubstances) { customSubstance ->
                    SubstanceRow(substanceModel = SubstanceModel(
                        name = customSubstance.name,
                        commonNames = emptyList(),
                        categories = listOf(
                            CategoryModel(
                                name = "custom", color = customColor
                            )
                        ),
                        hasSaferUse = false,
                        hasInteractions = false
                    ), onTap = {
                        navigateToCustomSubstanceChooseRoute(customSubstance.name)
                    })
                    HorizontalDivider()
                }
                item {
                    if (filteredSubstances.isEmpty() && filteredCustomSubstances.isEmpty()) {
                        Text("No matching substance found", modifier = Modifier.padding(10.dp))
                    }
                }
            }
        }
    }
}