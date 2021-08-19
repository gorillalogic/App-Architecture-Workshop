package com.gorillalogic.architecture.pets.ui.favorites

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pets.models.Owner
import com.pets.models.Pet
import com.gorillalogic.architecture.pets.R
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun PetRow(pet: Pet, onLikeClick: () -> Unit) {
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
            .padding(all = 16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {
            CoilImage(
                imageModel = pet.petImage,
                contentScale = ContentScale.FillHeight,
                circularRevealedEnabled = false,
                failure = {
                    Image(
                        painterResource(R.drawable.pawprint),
                        contentDescription = "",
                        contentScale = ContentScale.FillHeight,
                        modifier = Modifier.fillMaxSize()
                    )
                },
                loading = {
                    Image(
                        painterResource(R.drawable.pawprint),
                        contentDescription = "",
                        contentScale = ContentScale.FillHeight,
                        modifier = Modifier.fillMaxSize()
                    )
                },
                modifier = Modifier
                    .width(100.dp)
            )
            Text(text = pet.name,
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            color = Color(R.color.purple_200))
            IconButton(onClick = onLikeClick) {
                Icon(Icons.Filled.Favorite,
                    contentDescription = "Unlike",
                    tint = Color(R.color.purple_500)
                )
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun PetRowPreview() {
    val pet = Pet(id = 1,
    name = "Ollie",
    age = 7,
    breed = "Border Collie",
    sex = "Male",
    color = "Brown",
    owner = Owner("Dylan", address = "", email = "", phone = ""),
        petDescription = "Sweet little pretty puppy",
        petImage = "https://learning-challenge.herokuapp.com/images/pet-1.jpg",
        vaccines = ArrayList(),
        weight = 20
    )
    PetRow(pet = pet) {

    }
}