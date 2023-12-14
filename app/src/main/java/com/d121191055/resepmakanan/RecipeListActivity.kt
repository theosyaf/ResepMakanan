package com.d121191055.resepmakanan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class RecipeListActivity : AppCompatActivity() {

    private lateinit var viewModel: RecipeListViewModel
    private lateinit var adapter: RecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)

        viewModel = ViewModelProvider(this).get(RecipeListViewModel::class.java)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        adapter = RecipeAdapter(ArrayList())  // Sertakan data yang sesuai jika sudah ada
        recyclerView.adapter = adapter

        viewModel.recipes.observe(this, { recipes ->
            adapter.setRecipes(recipes)
        })

        val swipeRefreshLayout = findViewById<SwipeRefreshLayout>(R.id.swipe_refresh_layout)
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.onRefresh()
            swipeRefreshLayout.isRefreshing = false
        }
    }
}

