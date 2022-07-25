package com.example.navigation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.navigation.data.Property
import com.example.navigation.data.PropertyViewModel

@Composable
fun TrendingScreen(
    navController: NavController, propertyViewModel: PropertyViewModel
) {

    var projects: List<Property> by remember {
        mutableStateOf(emptyList())
    }

    LaunchedEffect(key1 = Unit) {
        propertyViewModel.getShuffledProperties.collect {
            projects = it
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {

            Column(modifier = Modifier.padding(8.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Trending Projects",
                        color = Color.Blue,
                        fontSize = 25.sp
                    )

                    TextButton(onClick = { navController.navigate(route = Screen.TrendingScreenListing.route) }) {
                        Text(text = "See All")
                    }
                }
                Text(
                    text = "Most seen projects by buyer in Gurgaon"
                )
            }

            LazyRow() {
                items(projects) { property ->
                    PropertyHorizontalViewCard(property, propertyViewModel)
                }
            }


        }

    }
}

@Composable
fun PropertyHorizontalViewCard(property: Property, propertyViewModel: PropertyViewModel) {

    Card(
        elevation = 4.dp,
        modifier = Modifier
            .padding(12.dp)
            .width(300.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.img),
                contentDescription = "propertyImage",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.5f)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,

                )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = property.houseName,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.fillMaxWidth(.8f),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
                Spacer(modifier = Modifier.width(8.dp))

                IconButton(onClick = {
                    property.isFavorite != property.isFavorite
                    propertyViewModel.updateProperty(property)
                }) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_baseline_favorite_24),
                        contentDescription = "",
                        colorFilter = if (property.isFavorite) ColorFilter.tint(color = Color.Blue) else ColorFilter.tint(
                            color = Color.Gray
                        ),
                    )
                }

            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 4.dp),
                text = "mktd by ${property.seller}",
                overflow = TextOverflow.Ellipsis, maxLines = 1,
            )

            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = property.size,
                    color = Color.Gray,
                    overflow = TextOverflow.Ellipsis, maxLines = 1,
                )
                Spacer(Modifier.width(20.dp))
                Canvas(
                    modifier = Modifier
                        .padding(6.dp)
                        .size(6.dp)
                ) {
                    drawCircle(Color.Black)
                }
                Text(
                    text = property.location,
                    color = Color.Gray,
                    overflow = TextOverflow.Ellipsis, maxLines = 1,
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 4.dp),
                text = property.price,
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis, maxLines = 1,
            )
        }
    }
}

