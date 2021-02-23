package com.gmail.shu10devapp.refrigemanager

/**
 * ジャンルクラス.
 */
enum class Genre(private val genreName: String) {
    ALL("すべて"),
    MEET("お肉"),
    VEGETABLE("野菜"),
    REFRIGERATE("冷蔵品"),
    FROZEN("冷凍品"),
    SEASONING("調味料"),
    OTHER("その他");

    companion object {
        fun getName(genre: Genre): String {
            return genre.genreName
        }

        fun getGenreLength(): Int {
            return values().size
        }

        fun getAllItem(): Array<Genre> {
            return arrayOf(ALL, MEET, VEGETABLE, REFRIGERATE, FROZEN, SEASONING, OTHER)
        }
    }
}