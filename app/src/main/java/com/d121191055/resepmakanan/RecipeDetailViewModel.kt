package com.d121191055.resepmakanan

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope


class RecipeDetailViewModel(
    private val recipeId: String?,
    private val repository: RecipeRepository
) : ViewModel() {

    init {
        recipeId?.let {
            repository.getRecipeById(it)
        }
    }

    val recipe: LiveData<Recipe> = repository.getRecipeById(recipeId ?: "")

    fun onFavorite() {
        if (recipeId != null) {
            repository.toggleFavorite(recipeId)
        }
    }

    fun setRecipeId(recipeId: String?) {

    }
}
