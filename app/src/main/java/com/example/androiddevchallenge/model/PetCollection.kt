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
package com.example.androiddevchallenge.model

import androidx.compose.runtime.Immutable

@Immutable
data class PetCollection(
    val id: Long,
    val name: String,
    val pets: List<Pet>,
    val type: CollectionType = CollectionType.Normal
)

enum class CollectionType { Normal, Highlight }

/**
 * A fake repo
 */
object PetRepo {
    fun getPets(): List<PetCollection> = petsCollections
    fun getPet(petId: Long) = petsList.find { it.id == petId }!!
    fun getFilters() = filters
}

/**
 * Static data
 */

private val cats = PetCollection(
    id = 1L,
    name = "Cat collection",
    type = CollectionType.Normal,
    pets = petsList.filter { it.kind == PetKind.Cat }
)

private val dogs = PetCollection(
    id = 2L,
    name = "Dog collection",
    pets = petsList.filter { it.kind == PetKind.Dog }
)

private val horses = PetCollection(
    id = 3L,
    name = "Horse collection",
    pets = petsList.filter { it.kind == PetKind.Horse }
)

private val others = PetCollection(
    id = 4L,
    name = "Other collection",
    pets = petsList.filter { it.kind == PetKind.Other }
)

private val petsCollections = listOf(
    cats, dogs, horses, others
)
