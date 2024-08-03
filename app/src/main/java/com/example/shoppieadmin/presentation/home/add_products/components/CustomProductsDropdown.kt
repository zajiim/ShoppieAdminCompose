package com.example.shoppieadmin.presentation.home.add_products.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.shoppieadmin.ui.theme.PrimaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomProductsDropdown(
    placeHolderText: String,
    selectedValue: String,
    onValueChange: (String) -> Unit,
    brands: List<String>,
    cursorColor: Color
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {expanded = !expanded }
    ) {
        OutlinedTextField(
            value = selectedValue,
            onValueChange = {},
            readOnly = true,
            placeholder = { Text(text = placeHolderText, color = cursorColor)},
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
                .height(56.dp)
                .border(0.5.dp, cursorColor, RoundedCornerShape(24.dp))
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(PrimaryColor)) {
            brands.forEach { brand ->
                DropdownMenuItem(
                    text = { Text(text = brand) },
                    onClick = {
                        onValueChange(brand)
                        expanded = false
                    })
            }
        }
    }

}