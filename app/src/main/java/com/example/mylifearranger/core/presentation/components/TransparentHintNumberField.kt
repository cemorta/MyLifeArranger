package com.example.mylifearranger.core.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun TransparentHintNumberField(
    text: String,
    hint: String,
    onValueChange: (String) -> Unit,
    onFocusChange: (FocusState) -> Unit,
    isHintVisible: Boolean,
    singleLine: Boolean = false,
    textStyle: TextStyle = TextStyle(),
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        BasicTextField(
            value = text,
            onValueChange = {
                if (it.all { char -> char.isDigit() }) {
                    onValueChange(it)
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            singleLine = singleLine,
            textStyle = textStyle,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { onFocusChange(it) })
        if (isHintVisible) {
            Text(text = hint, style = textStyle, color = textStyle.color.copy(alpha = 0.5f))
        }
    }
}