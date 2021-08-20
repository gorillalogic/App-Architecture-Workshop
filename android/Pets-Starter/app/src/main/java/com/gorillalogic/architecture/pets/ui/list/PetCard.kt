package com.gorillalogic.architecture.pets.ui.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gorillalogic.architecture.pets.R
import com.pets.models.Pet
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.glide.GlideImage


@Composable
fun PetCard(pet: Pet, isFavorite: Boolean, onLikeClick: () -> Unit) {
    Card(
        modifier = Modifier
            .height(250.dp)
            .fillMaxWidth()
            .padding(15.dp)
            .clickable { },
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            CoilImage(
                imageModel = pet.petImage,
                contentScale = ContentScale.FillBounds,
                failure = {
                    Image(
                        painterResource(R.drawable.pawprint),
                        contentDescription = "",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier.fillMaxSize()
                    )
                },
                loading = {
                    Image(
                        painterResource(R.drawable.pawprint),
                        contentDescription = "",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier.fillMaxSize()
                    )
                },
                modifier = Modifier
                    .height(150.dp)
            )

            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly) {
                Text(text = pet.name,
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(R.color.purple_200))
                IconButton(onClick = onLikeClick) {
                    if (isFavorite)
                        Icon(
                            painterResource(id = R.drawable.heart_fill),
                            contentDescription = "Unlike",
                            tint = Color(R.color.purple_500),
                            modifier = Modifier.width(25.dp)
                        )
                    else
                        Icon(
                            painterResource(id = R.drawable.heart),
                            contentDescription = "Unlike",
                            tint = Color(R.color.purple_500),
                            modifier = Modifier.width(25.dp)
                        )
                }
            }
        }
    }
}
