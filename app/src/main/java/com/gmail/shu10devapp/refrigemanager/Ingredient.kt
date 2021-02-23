package com.gmail.shu10devapp.refrigemanager

import java.io.Serializable

/**
 * 冷蔵庫アイテム.
 */
data class Ingredient(
    var name: String, var genre: Array<Genre?>) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Ingredient

        if (name != other.name) return false
        if (!genre.contentEquals(other.genre)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + genre.contentHashCode()
        return result
    }
}


