package com.example.shoppieadmin.presentation.auth.login.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.shoppieadmin.ui.theme.PrimaryDark

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    title: String,
    textValue: String,
    keyboardType: KeyboardType = KeyboardType.Ascii,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    hint: String,
    textColor: Color,
    cursorColor: Color,
    onValueChange: (String) -> Unit,
    trailingIcon: ImageVector? = null,
    onTrailingIconClicked: (() -> Unit)?,
    errorString: String? = null,
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {

        Text(
            text = title,
            color = PrimaryDark,
            style = MaterialTheme.typography.bodyMedium
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth()
                .padding(top = 3.dp)
                .border(0.5.dp, textColor, RoundedCornerShape(24.dp))
                .height(56.dp)
                .shadow(3.dp, RoundedCornerShape(24.dp)),
            value = textValue,
            onValueChange = onValueChange,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                cursorColor = cursorColor
            ),
            singleLine = true,
            shape = RoundedCornerShape(24.dp),
            trailingIcon = {
                if (trailingIcon != null) {
                    Icon(
                        imageVector = trailingIcon,
                        contentDescription = null,
                        tint = cursorColor,
                        modifier = Modifier.clickable {
                            if (onTrailingIconClicked != null) onTrailingIconClicked()
                        }
                    )
                }
            },
            placeholder = {
                Text(text = hint)
            },
            textStyle = MaterialTheme.typography.bodyMedium,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            visualTransformation = visualTransformation
        )

        Text(
            text = errorString ?: "",
            color = Color.Red,
            style = MaterialTheme.typography.bodySmall
            )

    }

}