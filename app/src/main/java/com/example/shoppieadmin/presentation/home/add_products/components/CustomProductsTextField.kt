package com.example.shoppieadmin.presentation.home.add_products.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shoppieadmin.ui.theme.PrimaryColor

@Composable
fun CustomProductsTextField(
    modifier: Modifier = Modifier,
    placeHolderText: String,
    textValue: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Ascii,
    minLines: Int = 1,
    isSingleLine: Boolean = true,
    cursorColor: Color
) {

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .border(0.5.dp, cursorColor, RoundedCornerShape(24.dp)),
        value = textValue,
        onValueChange = onValueChange,
        singleLine = isSingleLine,
        placeholder = { Text(text = placeHolderText, color = cursorColor) },
        shape = RoundedCornerShape(24.dp),
        minLines = minLines,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )
}


