package pw.zotan.psylog.ui.tabs.search.custom

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.imePadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditCustomSubstanceScreen(
    navigateBack: () -> Unit,
    viewModel: EditCustomSubstanceViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Edit custom substance") }, actions = {
                var isShowingDeleteDialog by remember { mutableStateOf(false) }
                IconButton(
                    onClick = { isShowingDeleteDialog = true },
                ) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "Delete substance",
                    )
                }
                AnimatedVisibility(visible = isShowingDeleteDialog) {
                    AlertDialog(
                        onDismissRequest = { isShowingDeleteDialog = false },
                        title = {
                            Text(text = "Delete substance?")
                        },
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    isShowingDeleteDialog = false
                                    viewModel.deleteCustomSubstance()
                                    navigateBack()
                                }
                            ) {
                                Text("Delete")
                            }
                        },
                        dismissButton = {
                            TextButton(
                                onClick = { isShowingDeleteDialog = false }
                            ) {
                                Text("Cancel")
                            }
                        }
                    )
                }
            })
        },
        floatingActionButton = {
            if (viewModel.isValid) {
                ExtendedFloatingActionButton(
                    modifier = Modifier.imePadding(),
                    onClick = {
                        viewModel.onDoneTap()
                        navigateBack()
                    },
                    icon = {
                        Icon(
                            Icons.Filled.Done,
                            contentDescription = "Done"
                        )
                    },
                    text = { Text("Done") },
                )
            }
        }
    ) { padding ->
        AddOrEditCustomSubstanceContent(
            padding = padding,
            name = viewModel.name,
            units = viewModel.units,
            description = viewModel.description,
            onNameChange = { viewModel.name = it },
            onUnitsChange = { viewModel.units = it },
            onDescriptionChange = { viewModel.description = it }
        )
    }
}