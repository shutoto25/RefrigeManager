package com.gmail.shu10devapp.refrigemanager

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.*

/**
 * fragment.
 */
class TabContentsFragment(
    private val ingredientList: MutableList<Ingredient>,
    private val pageTitle: CharSequence
) : Fragment(), RecyclerItemClickListener.OnRecyclerClickListener {

    private val title = pageTitle

    /**
     *
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_tab_contents, container, false)

        // タイトル設定.
        activity?.title = "食材一覧"

        // recyclerViewを取得.
        val ingredientListRecyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        // layoutManagerを取得.
        val layoutManager = LinearLayoutManager(view.context)
        val adapter = IngredientListRecyclerViewAdapter(select(ingredientList, pageTitle))

        ingredientListRecyclerView.layoutManager = layoutManager
        ingredientListRecyclerView.adapter = adapter

        ingredientListRecyclerView.addItemDecoration(
            DividerItemDecoration(view.context, layoutManager.orientation)
        )
        // リストアイテムクリックリスナ.
        ingredientListRecyclerView.addOnItemTouchListener(
            RecyclerItemClickListener(
                view.context,
                ingredientListRecyclerView,
                this
            )
        )
        return view
    }

    override fun onStart() {
        super.onStart()

        Log.d("IngredientManagerApp", "TabContentsFragment title : $title")

    }


    /**
     * リストシングルタップ時イベント.
     */
    override fun onItemClick(view: View, position: Int) {
        val ingredient = select(ingredientList, pageTitle)[position]

        val intent = Intent(context, AddIngredientActivity::class.java)
        intent.putExtra("EDIT_ITEM", ingredient)
        intent.putExtra("POSITION", position)
        startActivityForResult(intent, 1000)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode != 1000) {
            return
        }

        if (resultCode == Activity.RESULT_OK && data != null) {

            val ingredient =
                select(ingredientList, pageTitle)[data.getIntExtra("POSITION", ingredientList.size)]
            val result = data.getSerializableExtra("ingredient") as Ingredient
            ingredient.name = result.name
            ingredient.genre = result.genre

        } else {
            // failed.
        }
    }

    /**
     * リスト長押し時イベント.
     */
    override fun onItemLongClick(view: View, position: Int) {
        TODO("Not yet implemented")
    }

    /**
     * タブタイトルに一致したアイテムを選択.
     */
    private fun select(ingredientList: MutableList<Ingredient>, pageTitle: CharSequence)
            : MutableList<Ingredient> {

        // 「すべて」の場合はそのままreturn.
        if (pageTitle.toString() == Genre.getName(Genre.ALL)) {
            return ingredientList
        }

        val selectedList: MutableList<Ingredient> = ArrayList()
        // 一致するジャンルを持っているか検索.
        for (ingredient in ingredientList) {
            for (genre in ingredient.genre) {
                if (genre != null && pageTitle.toString() == Genre.getName(genre)) {
                    selectedList.add(ingredient)
                }
            }
        }
        return selectedList
    }

}