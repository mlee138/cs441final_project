package com.example.cs441_final_project

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.design.widget.BottomNavigationView
import android.util.LruCache
import android.view.animation.AnimationUtils
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.kitchen_content.*
import kotlinx.android.synthetic.main.recipe_content.*
import kotlinx.android.synthetic.main.shoppinglist_content.*



class MainActivity : AppCompatActivity() {

    private lateinit var kitchenController: KitchenController
    private lateinit var recipeController: RecipeController
    private lateinit var shoppingListController: ShoppingListController

    private lateinit var prefs: SharedPreferences

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        // 0 = kitchen, 1 = recipes, 2 = shopping list
        val displayedView = content_flipper.displayedChild

        when (item.itemId) {
            R.id.navigation_kitchen -> {
                if(displayedView != 0){
                    content_flipper.inAnimation = AnimationUtils.loadAnimation(applicationContext, R.anim.in_from_left)
                    content_flipper.outAnimation = AnimationUtils.loadAnimation(applicationContext, R.anim.out_from_right)
                    kitchenController.updateView()
                }
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_recipes -> {
                if(displayedView == 0){
                    content_flipper.inAnimation = AnimationUtils.loadAnimation(applicationContext, R.anim.in_from_right)
                    content_flipper.outAnimation = AnimationUtils.loadAnimation(applicationContext, R.anim.out_from_left)
                    content_flipper.displayedChild = 1
                    recipeController.updateView()
                } else if (displayedView == 2){
                    content_flipper.inAnimation = AnimationUtils.loadAnimation(applicationContext, R.anim.in_from_left)
                    content_flipper.outAnimation = AnimationUtils.loadAnimation(applicationContext, R.anim.out_from_right)
                    content_flipper.displayedChild = 1
                    recipeController.updateView()
                }

                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_shoppingList -> {
                if(displayedView != 2){
                    content_flipper.inAnimation = AnimationUtils.loadAnimation(applicationContext, R.anim.in_from_right)
                    content_flipper.outAnimation = AnimationUtils.loadAnimation(applicationContext, R.anim.out_from_left)
                    content_flipper.displayedChild = 2
                    shoppingListController.updateView()
                }
                //????
                //val collection = Klaxon().toJsonString((application as ApplicationData).memeCollection)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*prefs = this.getSharedPreferences("com.example.cs441_final_project", Context.MODE_PRIVATE)
        (application as ApplicationData).cashMoney = prefs.getInt("com.example.cs441_final_project", 0)
        findViewById<TextView>(R.id.orb_counter).text = (application as ApplicationData).cashMoney.toString()

        //???
        kitchenController = KitchenController(kitchen_container)
        recipeController = RecipeController(recipe_container)
        shoppingListController = ShoppingListController(shoppingList_container)*/
    }

    override fun onPause() {
        prefs.edit().putInt("com.example.cs441_final_project", (application as ApplicationData).cashMoney).apply()

        //val collection = Klaxon().toJsonString((application as ApplicationData).memeCollection)
        //prefs.edit().putString("com.example.cs441_final_project", collection).apply()

        super.onPause()
    }

    override fun onResume() {
        (application as ApplicationData).cashMoney = prefs.getInt("com.example.cs441_final_project",0)
        val collection = prefs.getString("com.example.cs441_final_project","[]")
        //(application as ApplicationData).memeCollection = Klaxon().parseArray<RedditData>(collection)?.toMutableList() ?: mutableListOf<>()

        //findViewById<TextView>(R.id.orb_counter).text = (application as ApplicationData).cashMoney.toString()

        super.onResume()
    }
}
