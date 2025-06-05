import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.irfangujjar.tmdb.core.ui.ScreenPadding
import com.irfangujjar.tmdb.core.ui.components.CustomTopAppBar
import com.irfangujjar.tmdb.core.ui.theme.TMDbTheme
import com.irfangujjar.tmdb.core.ui.theme.UserTheme
import com.irfangujjar.tmdb.features.theme.presentation.viewmodel.UserThemeViewModel

@Composable
fun ThemeScreen(
    userTheme: UserTheme,
    outerPadding: PaddingValues,
    onBackStackPressed: () -> Unit,
    viewModel: UserThemeViewModel = hiltViewModel()
) {
    val state = viewModel.userTheme.collectAsState()
    val currentTheme = state.value ?: userTheme
    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Theme",
                showBackStack = true, onBackStackPressed = onBackStackPressed
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            UserThemeSelection(
                modifier = Modifier.padding(
                    ScreenPadding.getPadding(
                        outerPaddingValues = outerPadding,
                        innerPaddingValues = innerPadding
                    )
                ),
                currentTheme = currentTheme,
                onThemeSelected = {
                    viewModel.saveUserTheme(it)
                }
            )
        }
    }
}

@Composable
private fun UserThemeSelection(
    modifier: Modifier = Modifier,
    currentTheme: UserTheme,
    onThemeSelected: (UserTheme) -> Unit
) {
    // Note that Modifier.selectableGroup() is essential to ensure correct accessibility behavior
    Column(modifier.selectableGroup()) {
        UserTheme.entries.forEach { userTheme ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .selectable(
                        selected = (userTheme == currentTheme),
                        onClick = { onThemeSelected(userTheme) },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (userTheme == currentTheme),
                    onClick = null // null recommended for accessibility with screen readers
                )
                Text(
                    text = userTheme.name,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
    
}


@Preview
@Composable
private fun AppearancesScreenPreview() {
    TMDbTheme {
        Surface {
            ThemeScreen(
                userTheme = UserTheme.SYSTEM,
                outerPadding = PaddingValues(),
                onBackStackPressed = {}
            )
        }
    }
}