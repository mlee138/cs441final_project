package com.example.cs441_final_project

import android.view.View

abstract class ViewController(view: View) {
    val appData = view.context.applicationContext as ApplicationData

    abstract fun updateView()

}