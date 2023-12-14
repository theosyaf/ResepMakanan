package com.d121191055.resepmakanan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide

class RecipeDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: RecipeDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)

        viewModel = ViewModelProvider(this).get(RecipeDetailViewModel::class.java)

        val recipeId : String? = intent.getStringExtra(EXTRA_RECIPE_ID)

        viewModel.setRecipeId(recipeId)

        viewModel.recipe.observe(this, { recipe ->
            findViewById<TextView>(R.id.text_view_title).text = recipe.title
            findViewById<TextView>(R.id.text_view_description).text = recipe.description
            findViewById<TextView>(R.id.text_view_ingredients).text = recipe.ingredients.joinToString("\n")
            findViewById<TextView>(R.id.text_view_steps).text = recipe.steps.joinToString("\n")

            val imageView = findViewById<ImageView>(R.id.image_view)
            Glide.with(this).load(recipe.image).into(imageView)
        })

        findViewById<Button>(R.id.button_favorite).setOnClickListener {
            viewModel.onFavorite()
        }
    }

    companion object {
        const val EXTRA_RECIPE_ID = "extra_recipe_id"
    }
}
