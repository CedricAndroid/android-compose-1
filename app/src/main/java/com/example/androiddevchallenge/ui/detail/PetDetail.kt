/*
 * Copyright 2020 The Android Open Source Project
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
package com.example.androiddevchallenge.ui.detail

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.components.PetDivider
import com.example.androiddevchallenge.model.Pet
import com.example.androiddevchallenge.model.PetImage
import com.example.androiddevchallenge.model.PetRepo
import com.example.androiddevchallenge.ui.theme.teal200
import com.example.androiddevchallenge.util.formatPrice
import dev.chrisbanes.accompanist.insets.navigationBarsPadding
import dev.chrisbanes.accompanist.insets.statusBarsPadding
import kotlin.math.max
import kotlin.math.min

private val BottomBarHeight = 56.dp
private val TitleHeight = 128.dp
private val GradientScroll = 180.dp
private val ImageOverlap = 115.dp
private val MinTitleOffset = 56.dp
private val MinImageOffset = 12.dp
private val MaxTitleOffset = ImageOverlap + MinTitleOffset + GradientScroll
private val ExpandedImageSize = 300.dp
private val CollapsedImageSize = 150.dp
private val HzPadding = Modifier.padding(horizontal = 24.dp)

@Composable
fun PetDetail(
    petId: Long,
    upPress: () -> Unit
) {
    val pet = remember(petId) { PetRepo.getPet(petId) }
    // val related = remember(petId) { PetRepo.getRelated(petId) }

    Box(Modifier.fillMaxSize()) {
        val scroll = rememberScrollState(0)
        Header()
        Body(pet, scroll)
        Title(pet, scroll.value)
        Image(pet.img, scroll.value)
        Up(upPress)
        // CartBottomBar(modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
private fun Header() {
    Spacer(
        modifier = Modifier
            .height(250.dp)
            .fillMaxWidth()
            .background(
                Brush.horizontalGradient(
                    colors = listOf(
                        Color.White,
                        Color.Gray,
                        Color.White,
                        Color.Gray,
                        Color.White,
                        Color.Gray,
                        Color.White
                    )
                )
            )
    )
}

@Composable
private fun Title(pet: Pet, scroll: Int) {
    val maxOffset = with(LocalDensity.current) { MaxTitleOffset.toPx() }
    val minOffset = with(LocalDensity.current) { MinTitleOffset.toPx() }
    val offset = (maxOffset - scroll).coerceAtLeast(minOffset)
    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .heightIn(min = TitleHeight)
            .statusBarsPadding()
            .graphicsLayer { translationY = offset }
            .background(color = Color.White)
    ) {
        Spacer(Modifier.height(16.dp))
        Text(
            text = pet.name,
            style = MaterialTheme.typography.h4,
            color = Color.Black,
            modifier = HzPadding
        )
        Text(
            text = pet.ageText,
            style = MaterialTheme.typography.subtitle2,
            fontSize = 20.sp,
            color = Color.Blue,
            modifier = HzPadding
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = formatPrice(pet.price),
            style = MaterialTheme.typography.h6,
            color = Color.DarkGray,
            modifier = HzPadding
        )

        Spacer(Modifier.height(8.dp))
        PetDivider()
    }
}

@Composable
private fun Body(
    pet: Pet,
    scroll: ScrollState
) {
    Column {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .height(MinTitleOffset)
        )
        Column(
            modifier = Modifier.verticalScroll(scroll)
        ) {
            Spacer(Modifier.height(GradientScroll))
            Surface(Modifier.fillMaxWidth()) {
                Column {
                    Spacer(Modifier.height(ImageOverlap))
                    Spacer(Modifier.height(TitleHeight))

                    Spacer(Modifier.height(16.dp))
                    Text(
                        text = "Description",
                        style = MaterialTheme.typography.overline,
                        color = Color.Black,
                        modifier = HzPadding
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = stringResource(R.string.lorum_ipsum),
                        style = MaterialTheme.typography.body1,
                        color = Color.Black,
                        modifier = HzPadding
                    )
                    Spacer(Modifier.height(40.dp))
                    Text(
                        text = "Owner",
                        style = MaterialTheme.typography.overline,
                        color = Color.Black,
                        modifier = HzPadding
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = pet.owner,
                        style = MaterialTheme.typography.body1,
                        color = Color.Black,
                        modifier = HzPadding
                    )

                    Spacer(Modifier.height(40.dp))
                    Text(
                        text = "Type",
                        style = MaterialTheme.typography.overline,
                        color = Color.Black,
                        modifier = HzPadding
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = pet.type,
                        style = MaterialTheme.typography.body1,
                        color = Color.Black,
                        modifier = HzPadding
                    )

                    Spacer(Modifier.height(16.dp))
                    PetDivider()

                    Spacer(
                        modifier = Modifier
                            .padding(bottom = BottomBarHeight)
                            .navigationBarsPadding(left = false, right = false)
                            .height(8.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun Up(upPress: () -> Unit) {
    IconButton(
        onClick = upPress,
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .size(36.dp)
            .background(
                color = teal200,
                shape = CircleShape
            )
    ) {
        Icon(
            imageVector = Icons.Outlined.ArrowBack,
            contentDescription = "back"
        )
    }
}

@Composable
private fun Image(
    imageUrl: String,
    scroll: Int
) {
    val collapseRange = with(LocalDensity.current) { (MaxTitleOffset - MinTitleOffset).toPx() }
    val collapseFraction = (scroll / collapseRange).coerceIn(0f, 1f)

    CollapsingImageLayout(
        collapseFraction = collapseFraction,
        modifier = HzPadding.then(Modifier.statusBarsPadding())
    ) {
        PetImage(
            imageUrl = imageUrl,
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun CollapsingImageLayout(
    collapseFraction: Float,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        check(measurables.size == 1)

        val imageMaxSize = min(ExpandedImageSize.roundToPx(), constraints.maxWidth)
        val imageMinSize = max(CollapsedImageSize.roundToPx(), constraints.minWidth)
        val imageWidth = lerp(imageMaxSize, imageMinSize, collapseFraction)
        val imagePlaceable = measurables[0].measure(Constraints.fixed(imageWidth, imageWidth))

        val imageY = lerp(MinTitleOffset, MinImageOffset, collapseFraction).roundToPx()
        val imageX = lerp(
            (constraints.maxWidth - imageWidth) / 2, // centered when expanded
            constraints.maxWidth - imageWidth, // right aligned when collapsed
            collapseFraction
        )
        layout(
            width = constraints.maxWidth,
            height = imageY + imageWidth
        ) {
            imagePlaceable.place(imageX, imageY)
        }
    }
}
