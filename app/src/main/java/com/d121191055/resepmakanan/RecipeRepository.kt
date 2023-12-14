package com.d121191055.resepmakanan

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeRepository(private val api: SpoonacularApi) {

    fun getRecipes(): LiveData<List<Recipe>> {
        val recipes = MutableLiveData<List<Recipe>>()

        api.getRecipes().enqueue(object : Callback<List<Recipe>> {
            override fun onResponse(call: Call<List<Recipe>>, response: Response<List<Recipe>>) {
                if (response.isSuccessful) {
                    recipes.value = response.body()
                } else {
                    Log.e("RecipeRepository", "Failed to get recipes. Code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Recipe>>, t: Throwable) {
                Log.e("RecipeRepository", "Failed to get recipes", t)
            }
        })

        return recipes
    }

    fun getRecipeById(recipeId: String): LiveData<Recipe> {
        val recipe = MutableLiveData<Recipe>()

        api.getRecipeById(recipeId).enqueue(object : Callback<Recipe> {
            override fun onResponse(call: Call<Recipe>, response: Response<Recipe>) {
                if (response.isSuccessful) {
                    recipe.value = response.body()
                } else {
                    Log.e("RecipeRepository", "Failed to get recipe by id. Code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Recipe>, t: Throwable) {
                Log.e("RecipeRepository", "Failed to get recipe by id", t)
            }
        })

        return recipe
    }

    fun refreshRecipes() {
        api.getRecipes().enqueue(object : Callback<List<Recipe>> {
            override fun onResponse(call: Call<List<Recipe>>, response: Response<List<Recipe>>) {
                if (response.isSuccessful) {
                    val recipes = response.body()
                    recipes?.forEach { it.isFavorite = false }
                } else {
                    Log.e("RecipeRepository", "Failed to refresh recipes. Code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Recipe>>, t: Throwable) {
                Log.e("RecipeRepository", "Failed to refresh recipes", t)
            }
        })
    }

    fun toggleFavorite(recipeId: String) {
        val recipe = getRecipeById(recipeId).value
        recipe?.isFavorite = !recipe?.isFavorite!! ?: false
        recipe?.let { updateRecipe(recipeId, it) }
    }

    fun updateRecipe(recipeId: String, recipe: Recipe) {
        api.updateRecipe(recipeId, recipe).enqueue(object : Callback<Recipe> {
            override fun onResponse(call: Call<Recipe>, response: Response<Recipe>) {
                if (response.isSuccessful) {
                    Log.d("RecipeRepository", "Recipe updated successfully")
                } else {
                    Log.e("RecipeRepository", "Failed to update recipe. Code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Recipe>, t: Throwable) {
                Log.e("RecipeRepository", "Failed to update recipe", t)
            }
        })
    }
}
