/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.model

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.Shadow5
import com.example.androiddevchallenge.ui.theme.purple500
import dev.chrisbanes.accompanist.coil.CoilImage

class Pet(
    val id: Long,
    val name: String,
    age: Int,
    val kind: PetKind,
    val img: String,
    val price: Long = 5000L,
    val type: String = "DefaultType",
    val owner: String = "ILOVEPETS31"
) {
    val ageText = "$age years old"
}

//region URL Images
val defaultImageDog =
    "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/dog-puppy-on-garden-royalty-free-image-1586966191.jpg?crop=1.00xw:0.669xh;0,0.190xh&resize=980:*"
val ImageDog1 =
    "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/single-minded-royalty-free-image-997141470-1558379890.jpg?crop=0.872xw:0.863xh;0,0.0540xh&resize=980:*"
val ImageDog2 =
    "https://hips.hearstapps.com/ghk.h-cdn.co/assets/17/29/bichon-frise.jpg?crop=1xw:0.9998552821997105xh;center,top&resize=980:*"

val cat1 =
    "https://www.arthipo.com/image/cache/catalog/poster/animals/panimal156-ellie-being-cute.-photo--resim-poster-kanvas-photo-1000x1000.jpg"
val cat2 =
    "https://images.pexels.com/photos/617278/pexels-photo-617278.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"
val cat3 =
    "https://i1.wp.com/vandaagindegeschiedenis.nl/wp-content/uploads-pvandag1/2013/06/garfield-560.jpg?fit=560%2C366&ssl=1"
val cat4 = "https://static.ah.nl/static/recepten/img_RAM_PRD130114_890x594_JPG.jpg"

val horse1 =
    "https://mk0nationaltodayijln.kinstacdn.com/wp-content/uploads/2019/12/national-horse-day-640x514.jpg"

val other1 = "https://i.pinimg.com/originals/52/b7/ae/52b7ae36d349463933b839a4f865578a.jpg"
//endregion

val petsList = listOf<Pet>(

    // Cats
    Pet(1L, "Pistache", 4, PetKind.Cat, cat1, type = "Normal cat"),
    Pet(2L, "Caty", 3, PetKind.Cat, cat2, type = "Normal cat"),
    Pet(3L, "Garfield", 5, PetKind.Cat, cat3, type = "Normal cat"),
    Pet(4L, "Lasagna", 2, PetKind.Cat, cat4, type = "Normal cat"),

    // Dogs
    Pet(5L, "Noor", 2, PetKind.Dog, defaultImageDog, type = "Irse setter"),
    Pet(6L, "Sloefie", 1, PetKind.Dog, ImageDog1, type = "Chiwawa"),
    Pet(7L, "Scoobie", 7, PetKind.Dog, ImageDog2, type = "BullDoser"),

    // Horses
    Pet(8L, "Spirit", 3, PetKind.Horse, horse1, type = "Horse Type"),

    // Other
    Pet(9L, "Piccachu", 3, PetKind.Other, other1, type = "Pokemon Type")
)

enum class PetKind {
    Dog,
    Cat,
    Horse,
    Other
}

@Composable
fun PetCollection(
    petCollection: PetCollection,
    onPetClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .heightIn(min = 56.dp)
                .padding(start = 24.dp)
        ) {
            Text(
                text = petCollection.name,
                style = MaterialTheme.typography.h6,
                color = Shadow5,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.Start)
            )
            val ctx = LocalContext.current
            IconButton(
                onClick = { Toast.makeText(ctx, "To implement", Toast.LENGTH_SHORT).show() },
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Icon(
                    imageVector = Icons.Outlined.ArrowForward,
                    tint = Shadow5,
                    contentDescription = null
                )
            }
        }
        Pets(petCollection.pets, onPetClick)
    }
}

@Composable
fun Pets(
    pets: List<Pet>,
    onPetClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(start = 12.dp, end = 12.dp)
    ) {
        items(pets) { pet ->
            PetItem(pet, onPetClick)
        }
    }
}

@Composable
fun PetItem(
    pet: Pet,
    onPetClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .clickable(onClick = { onPetClick(pet.id) })
            .padding(
                start = 4.dp,
                end = 4.dp,
                bottom = 8.dp
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {
            PetImage(
                imageUrl = pet.img,
                elevation = 4.dp,
                contentDescription = null,
                modifier = Modifier.size(120.dp)
            )
            Text(
                text = pet.name,
                style = MaterialTheme.typography.subtitle1,
                color = purple500,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun PetImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    elevation: Dp = 0.dp
) {
    Surface(
        color = Color.LightGray,
        elevation = elevation,
        shape = RoundedCornerShape(10),
        modifier = modifier
    ) {
        CoilImage(
            data = imageUrl,
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}
