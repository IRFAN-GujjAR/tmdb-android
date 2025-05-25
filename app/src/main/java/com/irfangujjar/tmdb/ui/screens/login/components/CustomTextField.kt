package com.irfangujjar.tmdb.ui.screens.login.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun CustomTextField(
    value: String,
    label: String,
    onValueChanged: (String) -> Unit,
    leadingIcon:@Composable (()->Unit)?=null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    trailingIcon:@Composable (()->Unit)?=null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isError: Boolean=false,
    errorMsg: String
){
    Column {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            label = { Text(label) },
            leadingIcon =leadingIcon,
            trailingIcon = trailingIcon,
            visualTransformation = visualTransformation,
            onValueChange = onValueChanged,
            keyboardOptions=keyboardOptions,
            isError = isError
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