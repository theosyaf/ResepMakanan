package com.d121191055.resepmakanan

data class Recipe(
    val id: String,
    val title: String,
    val description: String,
    val ingredients: List<Ingredient>,
    val steps: List<Step>,
    val image: String,
    var isFavorite: Boolean
)

data class Ingredient(
    val name: String,
    val amount: String,
    val unit: String
)

data class Step(
    val number: Int,
    val title: String,
    val description: String
)
