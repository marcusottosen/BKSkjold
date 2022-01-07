package com.example.bkskjold.data.model

class NewsModel {
    /*
    TODO data should be fetched from a database.
    */

    val news1 = listOf("03/01/2022","Ny Bane!", "Se den nye bane ved sine af bane C. Den nye bane kommer til at hedde bane Q")
    val news2 = listOf("03/01/2022","U14 vinder mesterskab", "U14 holdet vindet guld efter kamp mod HB Køge")
    val news3 = listOf("03/01/2022","Something something", "Something mod something vinder something efter something!")

    private val news = listOf(news1, news2, news3)

    fun getNews(): List<List<String>> {
        return news
    }
}