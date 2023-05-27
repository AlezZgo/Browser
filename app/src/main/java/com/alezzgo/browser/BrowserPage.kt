package com.alezzgo.browser


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.SemanticsProperties.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun BrowserScreen(viewModel: BrowserViewModel) {
    Column {
        // Search bar
        SearchBar(hint = "Search", modifier = Modifier.padding(16.dp))

//        // Lazy list of items
//        LazyColumn(
//            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
//        ) {
//            items(viewModel.itemsList) { item ->
//                ListItem(item = item)
//            }
//        }
    }
}

@Composable
fun SearchBar(
    hint: String,
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit = {},
    onClear: () -> Unit = {},
) {
    val textState = remember { mutableStateOf("") }

        TextField(
            value = textState.value,
            onValueChange = {
                textState.value = it
                onSearch(it)
            },
            modifier = modifier
                .fillMaxWidth()
                .background(Color.DarkGray, RoundedCornerShape(12.dp))
                .height(56.dp)
                .padding(start = 12.dp, end = 12.dp),
            textStyle = MaterialTheme.typography.bodyLarge,
            colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent),
            placeholder = { Text(hint) },
            singleLine = true,
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                imeAction = androidx.compose.ui.text.input.ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearch(textState.value)
                }
            ),
            leadingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(Color.Gray),
                    modifier = Modifier
                        .size(24.dp)
                )
            },
            trailingIcon = {
                if (textState.value.isNotEmpty()) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_clear),
                        contentDescription = "Clear search",
                        colorFilter = ColorFilter.tint(Color.Gray),
                        modifier = Modifier
                            .clickable {
                                textState.value = ""
                                onClear()
                            }
                            .size(24.dp)

                    )
                }
            },
        )


}

@Composable
fun ListItem(item: Item) {
    val interactionSource = remember { MutableInteractionSource() }
    Row(
        modifier = Modifier
            .clickable(
                interactionSource = interactionSource,
                indication = rememberRipple(bounded = true),
                onClick = { /* Handle item click */ }
            )
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(item.imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = item.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.clip(CircleShape)
        )
        Column(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Text(text = item.title, style = MaterialTheme.typography.headlineLarge)
            Text(text = item.subtitle, style = MaterialTheme.typography.labelSmall)
        }
    }
}