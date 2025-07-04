package com.irfangujjar.tmdb.features.login.presentation.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.ContentType
import androidx.compose.ui.semantics.contentType
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun CustomTextField(
    value: String,
    label: String,
    onValueChanged: (String) -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isError: Boolean = false,
    errorMsg: String,
    enabled: Boolean,
    maxLines: Int = 1,
    contentType: ContentType
) {
    Column {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .semantics {
                    this.contentType = contentType
                },
            value = value,
            label = { Text(label) },
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            visualTransformation = visualTransformation,
            onValueChange = onValueChanged,
            keyboardOptions = keyboardOptions,
            isError = isError,
            enabled = enabled,
            maxLines = maxLines
        )
        if (isError) {
            Text(
                text = errorMsg,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }

}