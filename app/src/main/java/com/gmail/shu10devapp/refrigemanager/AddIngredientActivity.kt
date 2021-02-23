package com.gmail.shu10devapp.refrigemanager

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_ingredient.*

/**
 * 食材追加画面.
 */
class AddIngredientActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_ingredient)

        if (intent.getIntExtra("POSITION", -1) >= 0) {
            val receiveIngredient = intent.getSerializableExtra("EDIT_ITEM") as Ingredient
            name.setText(receiveIngredient.name)
            val genreList = receiveIngredient.genre
            for (genre in genreList) {
                when (genre) {
                    Genre.MEET -> meet.isChecked = true
                    Genre.VEGETABLE -> vegetable.isChecked = true
                    Genre.REFRIGERATE -> refrigerate.isChecked = true
                    Genre.FROZEN -> frozen.isChecked = true
                    Genre.SEASONING -> seasoning.isChecked = true
                    Genre.OTHER -> others.isChecked = true
                    else -> continue
                }
            }
        }
        add_button.setOnClickListener { _ ->
            val resultIntent = Intent()
            val checkedGenreList = arrayOf(
                if (meet.isChecked) Genre.MEET else null,
                if (vegetable.isChecked) Genre.VEGETABLE else null,
                if (refrigerate.isChecked) Genre.REFRIGERATE else null,
                if (frozen.isChecked) Genre.FROZEN else null,
                if (seasoning.isChecked) Genre.SEASONING else null,
                if (others.isChecked) Genre.OTHER else null,
            )
            val ingredient = Ingredient(name.text.toString(), checkedGenreList)
            resultIntent.putExtra("ingredient", ingredient)
            resultIntent.putExtra("POSITION", intent.getIntExtra("POSITION", -1))
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}