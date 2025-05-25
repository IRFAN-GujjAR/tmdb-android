package com.irfangujjar.tmdb.ui.screens.login

import androidx.compose.foundation.Image
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.irfangujjar.tmdb.R
import com.irfangujjar.tmdb.ui.screens.login.components.CustomTextField
import com.irfangujjar.tmdb.ui.theme.TMDbTheme
import com.irfangujjar.tmdb.ui.util.ScreenPadding
import com.irfangujjar.tmdb.viewmodel.LoginViewModel


@Preview
@Composable
fun LoginScreen() {
    val viewModel: LoginViewModel = viewModel()
    val scrollState = rememberScrollState()
    TMDbTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .statusBarsPadding()
                    .padding(
                        top = ScreenPadding.TOP_PADDING + 24.dp,
                        bottom = ScreenPadding.BOTTOM_PADDING + 8.dp,
                        start = ScreenPadding.START_PADDING,
                        end = ScreenPadding.END_PADDING
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
                    isError = viewModel.userNameError
                )
                Spacer(modifier = Modifier.height(20.dp))
                PasswordTextField(
                    password = viewModel.password,
                    onValueChanged = { viewModel.updatePassword(it) },
                    showPassword = viewModel.showPassword,
                    onToggleShowPassword = {
                        viewModel.toggleShowPassword()
                    },
                    isError = viewModel.passwordError
                )
                Spacer(modifier = Modifier.height(24.dp))
                LoginScreenButtons(
                    onSignInClick = { viewModel.signIn() },
                    onSignUpClick = {},
                    onContinueWithoutSignInClick = {}
                )
            }
        }

    }
}

@Composable
private fun LoginScreenButtons(
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit,
    onContinueWithoutSignInClick: () -> Unit
) {
    Button(
        onClick = onSignInClick,
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(vertical = 14.dp)
    ) {
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
        ) {
            Text("SIGN UP")
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
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
    Spacer(modifier = Modifier.height(16.dp))
    Button(
        onClick = onContinueWithoutSignInClick,
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(vertical = 14.dp)
    ) {
        Text("CONTINUE WITHOUT SIGN IN")
    }
}

@Composable
private fun UsernameTextField(
    username: String,
    onValueChanged: (String) -> Unit,
    onClearIconClick: () -> Unit,
    isError: Boolean
) {
    CustomTextField(
        value = username,
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
    isError: Boolean
) {
    CustomTextField(
        value = password,
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