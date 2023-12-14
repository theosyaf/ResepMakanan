package com.d121191055.resepmakanan

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class RecipeListViewModel(private val repository: RecipeRepository) : ViewModel() {

    val recipes: LiveData<List<Recipe>> = repository.getRecipes()

    fun onRefresh() {
        repository.refreshRecipes()
    }
}
