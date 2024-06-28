package com.example.shoppieadmin.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.shoppieadmin.domain.home.models.Product
import com.example.shoppieadmin.utils.shimmerEffect

@Composable
fun CustomItem(
    modifier: Modifier = Modifier,
    product: Product
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(24.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.LightGray)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .clip(RoundedCornerShape(12.dp))
                .shimmerEffect(),
            model = if (product.images.isNotEmpty()) product.images[0]
            else "https://media.geeksforgeeks.org/wp-content/uploads/20230802153215/Error-404.png",
            contentDescription = product.name,
            contentScale = ContentScale.Crop
        )
        Text(
            modifier = Modifier.padding(start = 8.dp, top = 8.dp),
            text = product.name
        )

        Text(
            modifier = Modifier.padding(start = 8.dp, top = 8.dp),
            text = product.description
        )
    }
}