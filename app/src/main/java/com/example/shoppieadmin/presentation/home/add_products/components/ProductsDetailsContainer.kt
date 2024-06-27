package com.example.shoppieadmin.presentation.home.add_products.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

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
    cursorColor: Color
    ) {


    Column(
        modifier = modifier.fillMaxWidth().padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)) {

        CustomProductsTextField(
            textValue = nameValue(),
            onValueChange = onNameChanged,
            cursorColor = cursorColor,
            placeHolderText = "Product name"
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