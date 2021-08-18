package com.gorillalogic.architecture.pets.ui.favorites

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.pets.models.Pet
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun PetRow(pet: Pet, onLikeClick: () -> Unit) {
    Row(modifier = Modifier.clickable { onLikeClick() }) {
        GlideImage(
            imageModel = pet.petImage,
            // Crop, Fit, Inside, FillHeight, FillWidth, None
            contentScale = ContentScale.FillBounds,
            // shows an image with a circular revealed animation.
            circularRevealedEnabled = true
        )
        Spacer(modifier = Modifier.width(30.dp))
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.W900, color = Color(0xFF4552B8))
                ) {
                    append(pet.name)
                }
            }
        )

        Button(onClick = onLikeClick) {
            Text(text = "DisLike")
        }
    }
}