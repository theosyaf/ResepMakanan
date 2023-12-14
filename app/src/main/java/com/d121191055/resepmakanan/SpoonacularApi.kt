package com.d121191055.resepmakanan

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface SpoonacularApi {
    @GET("recipes")
    fun getRecipes(): Call<List<Recipe>>

    @GET("recipes/{id}")
    fun getRecipeById(@Path("id") recipeId: String): Call<Recipe>

    @PUT("recipes/{id}")
    fun updateRecipe(@Path("id") recipeId: String, @Body recipe: Recipe): Call<Recipe>
}
