package com.gmail.shu10devapp.refrigemanager

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activiity_tab_contents.*


/**
 *
 */
class TabContentsActivity : AppCompatActivity() {

    /** 冷蔵庫アイテムリスト */
    private val mIngredientList: MutableList<Ingredient> = ArrayList()

    /** pageAdapter */
    private lateinit var mAdapter: TabContentsPagerAdapter

    /** currentPosition */
    private var mPosition : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activiity_tab_contents)

        mAdapter = TabContentsPagerAdapter(supportFragmentManager, getIngredientList())
        viewPager.offscreenPageLimit = 2
        viewPager.adapter = mAdapter
        tabLayout.setupWithViewPager(viewPager)

        // fabのリスナ設定.
        fab.setOnClickListener { _ ->
            val intent = Intent(this, AddIngredientActivity::class.java)
            startActivityForResult(intent, 1000)
        }
    }

    override fun onResume() {
        super.onResume()

        mAdapter.notifyDataSetChanged()
        viewPager.adapter = mAdapter
        viewPager.currentItem = mPosition
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onPause() {
        super.onPause()

        // 表示中のタプを保持する.
        mPosition = viewPager.currentItem
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode != 1000) {
            return
        }

        if (resultCode == Activity.RESULT_OK && data != null) {
            setIngredientList(data.getSerializableExtra("ingredient") as Ingredient)
        } else {
            // failed.
        }
    }


    /**
     * リストセッター.
     */
    private fun setIngredientList(ingredient: Ingredient) {
        mIngredientList.add(ingredient)

    }

    /**
     * リストゲッター.
     */
    private fun getIngredientList(): MutableList<Ingredient> {
        return mIngredientList
    }
}