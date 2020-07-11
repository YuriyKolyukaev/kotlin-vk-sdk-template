package ru.gladkov.kotlin_vk_sdk_template

import android.app.Activity
import android.util.Log
import android.view.View
import android.widget.Toast
import com.arellomobile.mvp.MvpPresenter

fun Any.log(text: Any?) {
    Log.i("kolyukaevYS", text.toString())
}

fun Activity.toast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}