package pw.zotan.psylog.ui.tabs.journal.experience.edit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun EditExperienceScreen(
    navigateBack: () -> Unit,
    viewModel: EditExperienceViewModel = hiltViewModel()
) {
    EditExperienceScreen(
        enteredTitle = viewModel.enteredTitle,
        onChangeOfEnteredTitle = { viewModel.enteredTitle = it },
        isEnteredTitleOk = viewModel.isEnteredTitleOk,
        onDoneTap = {
            viewModel.onDoneTap()
            navigateBack()
        },
        text = viewModel.enteredText,
        onTextChange = { viewModel.enteredText = it },
        location = viewModel.enteredLocation,
        onLocationChange = { viewModel.enteredLocation = it }
    )
}

@Preview
@Composable
fun EditExperienceScreenPreview() {
    EditExperienceScreen(
        enteredTitle = "Day at the Lake",
        onChangeOfEnteredTitle = { },
        isEnteredTitleOk = true,
        onDoneTap = {},
        text = "Here are some sample notes",
        onTextChange = {},
        location = "Zurich",
        onLocationChange = {}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditExperienceScreen(
    enteredTitle: String,
    onChangeOfEnteredTitle: (String) -> Unit,
    isEnteredTitleOk: Boolean,
    onDoneTap: () -> Unit,
    text: String,
    onTextChange: (String) -> Unit,
    location: String,
    onLocationChange: (String) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit experience") },
                actions = {
                    if (isEnteredTitleOk) {
                        IconButton(onClick = onDoneTap) {
                            Icon(
                                Icons.Filled.Done,
                                contentDescription = "Done icon"
                            )
                        }
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(10.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            val focusManager = LocalFocusManager.current
            OutlinedTextField(
                value = enteredTitle,
                onValueChange = onChangeOfEnteredTitle,
                singleLine = true,
                label = { Text(text = "Title") },
                isError = !isEnteredTitleOk,
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    capitalization = KeyboardCapitalization.Sentences
                ),
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = location,
                onValueChange = onLocationChange,
                singleLine = true,
                label = { Text(text = "Location") },
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    capitalization = KeyboardCapitalization.Words
                ),
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = text,
                onValueChange = onTextChange,
                label = { Text(text = "Notes") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Default,
                    capitalization = KeyboardCapitalization.Sentences
                ),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )
        }
    }
}