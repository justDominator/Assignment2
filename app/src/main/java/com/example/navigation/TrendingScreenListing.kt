package com.example.navigation
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.navigation.data.Property
import com.example.navigation.data.PropertyViewModel
import me.onebone.toolbar.*


@Composable
fun TrendingScreenListing(navController: NavController, propertyViewModel: PropertyViewModel) {
    var projects: List<Property> by remember {
        mutableStateOf(emptyList())
    }

    LaunchedEffect(key1 = Unit) {
        propertyViewModel.readAllData.collect {
            projects = it
        }
    }

    val state = rememberCollapsingToolbarScaffoldState()

    CollapsingToolbarScaffold(
        modifier = Modifier,
        state = state,
        scrollStrategy = ScrollStrategy.EnterAlways,
        toolbar = {
            val textSize = (24 + (30 - 12) * state.toolbarState.progress).sp
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .pin()
                    .background(color = Color.White)
            )
            Text(
                    text = "Trending Projects",
                    style = TextStyle(color = Color.Blue),
                    modifier = Modifier.padding(8.dp,16.dp),
                    fontSize = textSize
            )
        }) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(projects) { property ->
                PropertyVerticalViewCard(property, propertyViewModel)
            }
        }
    }
}

@Composable
fun PropertyVerticalViewCard(property: Property, propertyViewModel: PropertyViewModel) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.img),
                contentDescription = "propertyImage",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.4f)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 4.dp),
                text = property.price,
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis, maxLines = 1,
            )
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = property.houseName,
                    fontSize = 16.sp,
                )

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
                text = property.location,
                color = Color.Gray,
                overflow = TextOverflow.Ellipsis, maxLines = 1,
            )

            Divider(
                modifier = Modifier
                    .padding(0.dp, 20.dp, 0.dp, 20.dp)
                    .fillMaxWidth(), color = Color.Gray, thickness = 1.dp
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 4.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                Column() {
                    Row(horizontalArrangement = Arrangement.SpaceBetween) {
                        Image(
                            painter = painterResource(id = R.drawable.seller),
                            modifier = Modifier
                                .width(20.dp)
                                .height(20.dp)
                                .padding(2.dp)
                                .clip(CircleShape),
                            contentDescription = "",
                        )
                        Text(
                            text = "${property.seller}",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal,
                            overflow = TextOverflow.Ellipsis, maxLines = 1,
                            modifier = Modifier.fillMaxWidth(.3f),
                        )
                    }

                    Text(
                        text = "seller",
                        fontWeight = FontWeight.ExtraLight,
                        modifier = Modifier.padding(start = 20.dp)
                    )
                }


                Button(onClick = {}) {
                    Text(
                        "View Phone", modifier = Modifier
                            .width(85.dp)
                            .height(25.dp)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {}) {
                    Image(
                        painter = painterResource(id = R.drawable.whatsapp),
                        contentDescription = "whatsappIcon",
                        modifier = Modifier
                            .width(20.dp)
                            .height(25.dp)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {}) {
                    Image(
                        painter = painterResource(id = R.drawable.phone),
                        contentDescription = "phoneIcon",
                        modifier = Modifier
                            .width(20.dp)
                            .height(25.dp),
                        colorFilter = ColorFilter.tint(color = Color.White)
                    )
                }
            }
        }

    }
}

