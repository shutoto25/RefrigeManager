package com.gmail.shu10devapp.refrigemanager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 *
 */
class IngredientListRecyclerViewAdapter(
    private val ingredientListData: MutableList<Ingredient>
) : RecyclerView.Adapter<IngredientListRecyclerViewAdapter.IngredientListRecyclerViewHolder>() {

    /**
     * 画面部品を保持する自作クラスであるIngredientListRecyclerViewHolderオブジェクトを生成.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : IngredientListRecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_ingredient_item, parent, false)
        return IngredientListRecyclerViewHolder(view)
    }

    /**
     * データ件数を取得.
     */
    override fun getItemCount(): Int {
        return ingredientListData.size
    }

    /**
     * IngredientListRecyclerViewHolder内の各画面部品に表示したいデータを割り当てる.
     */
    override fun onBindViewHolder(holder: IngredientListRecyclerViewHolder, position: Int) {
        val ingredient = ingredientListData[position]

        // IngredientListRecyclerViewHolderより取得したレイアウト要素に食材情報を格納.
        holder.textView.text = ingredient.name
    }


    /**
     * 画面部品要素を構成するクラスを定義
     */
    class IngredientListRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView = itemView.findViewById(R.id.listItemText)
    }
}