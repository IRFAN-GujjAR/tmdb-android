package com.irfangujjar.tmdb.features.login.presentation.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.irfangujjar.tmdb.R
import com.irfangujjar.tmdb.core.ui.ScreenPadding
import com.irfangujjar.tmdb.core.ui.components.CustomDialogBox
import com.irfangujjar.tmdb.core.ui.components.CustomTopAppBar
import com.irfangujjar.tmdb.features.login.presentation.screens.components.CustomTextField
import com.irfangujjar.tmdb.features.login.presentation.viewmodel.LoginViewModel
import com.irfangujjar.tmdb.features.login.presentation.viewmodel.state.LoginContinueState
import com.irfangujjar.tmdb.features.login.presentation.viewmodel.state.LoginState


@Preview
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel<LoginViewModel>(),
    isAppStartedFirstTime: Boolean = false,
    showBackStack: Boolean = false,
    onBackStackPressed: () -> Unit = {},
    navigateToMainScreen: () -> Unit = {}
) {
    val loginState = viewModel.loginState.collectAsState()
    val continueState = viewModel.continueState.collectAsState()
    val isLoginLoading = loginState.value == LoginState.LoggingIn
    val isContinueLoading = continueState.value == LoginContinueState.Loading
    val isLoading =
        isLoginLoading || isContinueLoading
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    if (showBackStack)
        Scaffold(
            topBar = {
                CustomTopAppBar(
                    showBackStack = true,
                    matchSurfaceColor = true,
                    onBackStackPressed = onBackStackPressed
                )
            }
        ) { innerPadding ->
            LoginScreenBody(
                scrollState = scrollState,
                viewModel = viewModel,
                innerPadding = innerPadding,
                isLoading = isLoading,
                isAppStartedFirstTime = isAppStartedFirstTime,
                navigateToMainScreen = navigateToMainScreen,
                context = context,
                isLoginLoading = isLoginLoading,
                isContinueLoading = isContinueLoading,
                showBackStack = showBackStack
            )
        }
    else
        LoginScreenBody(
            scrollState = scrollState,
            viewModel = viewModel,
            isLoading = isLoading,
            innerPadding = null,
            isAppStartedFirstTime = isAppStartedFirstTime,
            navigateToMainScreen = navigateToMainScreen,
            context = context,
            isLoginLoading = isLoginLoading,
            isContinueLoading = isContinueLoading,
            showBackStack = false
        )
    if (loginState.value is LoginState.Error) {
        val loginStateValue = loginState.value as LoginState.Error
        CustomDialogBox(
            title = "Error",
            message = loginStateValue.error.message,
        ) {
            viewModel.resetLoginState()
        }
    }

}

@Composable
private fun LoginScreenBody(
    scrollState: ScrollState,
    viewModel: LoginViewModel,
    showBackStack: Boolean,
    innerPadding: PaddingValues?,
    isLoading: Boolean,
    isAppStartedFirstTime: Boolean,
    navigateToMainScreen: () -> Unit,
    context: Context,
    isLoginLoading: Boolean,
    isContinueLoading: Boolean
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .statusBarsPadding()
                .padding(
                    ScreenPadding.getPadding(innerPaddingValues = innerPadding)
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.tmdb_logo),
                contentDescription = "TMDb Logo",
                modifier = Modifier.height(100.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            UsernameTextField(
                username = viewModel.username,
                onValueChanged = { viewModel.updateUsername(it) },
                onClearIconClick = { viewModel.updateUsername("") },
                isError = viewModel.userNameError,
                enabled = !isLoading
            )
            Spacer(modifier = Modifier.height(20.dp))
            PasswordTextField(
                password = viewModel.password,
                onValueChanged = { viewModel.updatePassword(it) },
                showPassword = viewModel.showPassword,
                onToggleShowPassword = {
                    viewModel.toggleShowPassword()
                },
                isError = viewModel.passwordError,
                enabled = !isLoading
            )
            Spacer(modifier = Modifier.height(24.dp))
            LoginScreenButtons(
                onSignInClick = {
                    viewModel.signIn(
                        isAppStartedFirstTime = isAppStartedFirstTime,
                        navigateToMainScreen = navigateToMainScreen
                    )
                },
                onSignUpClick = { viewModel.signUp(context) },
                onContinueWithoutSignInClick = {
                    viewModel.continueWithoutSignIn(navigateToMainScreen)
                },
                isLoading = isLoading,
                isLoginLoading = isLoginLoading,
                isContinueLoading = isContinueLoading,
                showBackStack = showBackStack
            )
        }

    }
}

@Composable
private fun LoginScreenButtons(
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit,
    showBackStack: Boolean,
    onContinueWithoutSignInClick: () -> Unit,
    isLoading: Boolean,
    isLoginLoading: Boolean,
    isContinueLoading: Boolean
) {
    Button(
        onClick = onSignInClick,
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(vertical = 14.dp),
        enabled = !isLoading
    ) {
        if (isLoginLoading)
            CircularProgressIndicator()
        else
            Text("SIGN IN")
    }
    Spacer(modifier = Modifier.height(10.dp))
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Don't have an account?",
        )
        TextButton(
            onClick = onSignUpClick,
            enabled = !isLoading
        ) {
            Text("SIGN UP")
        }
    }
    if (!showBackStack)
        Spacer(modifier = Modifier.height(8.dp))
    if (!showBackStack)
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            HorizontalDivider(
                modifier = Modifier.weight(1f)
            )
            Text(
                "OR",
                modifier = Modifier.padding(horizontal = 12.dp)
            )
            HorizontalDivider(
                modifier = Modifier.weight(1f)
            )

        }
    if (!showBackStack)
        Spacer(modifier = Modifier.height(16.dp))
    if (!showBackStack)
        Button(
            onClick = onContinueWithoutSignInClick,
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(vertical = 14.dp),
            enabled = !isLoading
        ) {
            if (isContinueLoading)
                CircularProgressIndicator()
            else
                Text("CONTINUE WITHOUT SIGN IN")
        }
}

@Composable
private fun UsernameTextField(
    username: String,
    onValueChanged: (String) -> Unit,
    onClearIconClick: () -> Unit,
    isError: Boolean,
    enabled: Boolean
) {
    CustomTextField(
        value = username,
        enabled = enabled,
        label = "Username",
        onValueChanged = onValueChanged,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Username Leading Icon"
            )
        },
        trailingIcon = {
            if (username.isNotEmpty()) {
                IconButton(onClick = onClearIconClick) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Clear Username"
                    )
                }
            }
        },
        isError = isError,
        errorMsg = "Username cannot be empty"
    )
}

@Composable
private fun PasswordTextField(
    password: String,
    onValueChanged: (String) -> Unit,
    showPassword: Boolean,
    onToggleShowPassword: () -> Unit,
    isError: Boolean,
    enabled: Boolean
) {
    CustomTextField(
        value = password,
        enabled = enabled,
        label = "Password",
        onValueChanged = onValueChanged,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = "Password Icon"
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Password
        ),
        trailingIcon = {
            if (password.isNotEmpty()) {
                IconButton(onClick = onToggleShowPassword) {
                    Icon(
                        imageVector = if (showPassword)
                            Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = "Visibility Toggle Icon"
                    )
                }
            }
        },
        visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
        isError = isError,
        errorMsg = "Password cannot be empty"
    )
}