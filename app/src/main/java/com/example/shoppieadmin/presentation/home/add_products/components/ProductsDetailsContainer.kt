package com.example.shoppieadmin.presentation.home.add_products.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.shoppieadmin.ui.theme.PrimaryColor

@Composable
fun ProductsDetailsContainer(
    modifier: Modifier = Modifier,
    nameValue: () -> String,
    onNameChanged: (String) -> Unit,
    descriptionValue: () -> String,
    onDescriptionChanged: (String) -> Unit,
    priceValue: () -> String,
    onPriceChanged: (String) -> Unit,
    countValue: () -> String,
    onCountChanged: (String) -> Unit,
    categoryValue: () -> String,
    onCategoryChanged: (String) -> Unit,
    brandValue : () -> String,
    onBrandChanged: (String) -> Unit,
    cursorColor: Color
    ) {
    val brands = listOf("Nike", "Puma", "Converse", "Under Armour", "Adidas")

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)) {

        CustomProductsTextField(
            textValue = nameValue(),
            onValueChange = onNameChanged,
            cursorColor = cursorColor,
            placeHolderText = "Product name"
        )

        CustomProductsDropdown(
            placeHolderText = "Select a brand",
            selectedValue = brandValue(),
            onValueChange = onBrandChanged,
            brands = brands,
            cursorColor = cursorColor
        )

        CustomProductsTextField(
            textValue = descriptionValue(),
            onValueChange = onDescriptionChanged,
            cursorColor = cursorColor,
            placeHolderText = "Product description"
        )

        CustomProductsTextField(
            textValue = priceValue(),
            onValueChange = onPriceChanged,
            cursorColor = cursorColor,
            placeHolderText = "Price",
            keyboardType = KeyboardType.Number
        )

        CustomProductsTextField(
            textValue = countValue(),
            onValueChange = onCountChanged,
            cursorColor = cursorColor,
            placeHolderText = "Product count",
            keyboardType = KeyboardType.Number
        )

        CustomProductsTextField(
            textValue = categoryValue(),
            onValueChange = onCategoryChanged,
            cursorColor = cursorColor,
            placeHolderText = "Product category"
        )
    }

}