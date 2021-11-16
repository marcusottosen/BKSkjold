package com.example.bkskjold.data.model

class ProfileInfo {
    /*
    TODO data should be fetched from a database.
    */
    val testProfile = listOf("Standard medlem: 99kr./md." , "Træninger gennemført: 15" , "Medlem siden: 16/11/2021" , "Hold: Senior, U21" , "Fornavn" , "Efternavn" , "31")
   private val profile = testProfile

    fun getProfile(): List<String> {
        return profile
    }}