package com.example.cs441_final_project

import android.view.View
import android.widget.ListView
import android.widget.TextView

class RecipeController(private val collection_layout: View): ViewController(collection_layout) {

    override fun updateView() {
        //collection_layout.findViewById<TextView>(R.id.message).text = appData.memeCollection.size.toString()

        //val adapter = CharacterAdapter(collection_layout.context, appData.memeCollection, imageLoader)
        val listView = collection_layout.findViewById<ListView>(R.id.collection_list)
        //listView.adapter = adapter
    }

}