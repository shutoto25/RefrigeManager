package com.gmail.shu10devapp.refrigemanager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

/**
 * adapter.
 */
class TabContentsPagerAdapter(
    fm: FragmentManager, private val ingredientList: MutableList<Ingredient>
) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val tabTitles = arrayOf<CharSequence>(
        "すべて", "お肉", "野菜", "冷蔵品", "冷凍品", "調味料", "その他")

    override fun getPageTitle(position: Int): CharSequence {
        return tabTitles[position]
    }

    override fun getItem(position: Int): Fragment {
        return TabContentsFragment(ingredientList, getPageTitle(position))
    }

    override fun getCount(): Int {
        return tabTitles.size
    }
}