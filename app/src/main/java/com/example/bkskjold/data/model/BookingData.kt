package com.example.bkskjold.data.model

class BookingData {
    var training1 = listOf("21:00", "I dag", "14/11/2021", "8/12", "Bane C", "Senior", "Træner: Ekkart", "Deltag")
    var training2 = listOf("17:00", "Om 1 dag", "15/11/2021", "6/12", "Bane C", "U20", "Træner: Ekkart", "Afmeld Deltagelse")
    var training3 = listOf("17:00", "Om 2 dage", "16/11/2021", "11/12", "Bane C", "U20", "Træner: Ekkart", "Deltag")
    var training4 = listOf("17:00", "Om 3 dage", "17/11/2021", "12/12", "Bane C", "U20", "Træner: Ekkart", "Deltag")
    var training5 = listOf("17:00", "Om 4 dage", "18/11/2021", "4/12", "Bane C", "U20", "Træner: Ekkart", "Afmeld Deltagelse")
    var training6 = listOf("17:00", "Om 5 dage", "19/11/2021", "8/12", "Bane C", "U20", "Træner: Ekkart", "Deltag")
    var training7 = listOf("17:00", "Om 6 dage", "20/11/2021", "8/12", "Bane C", "U20", "Træner: Ekkart", "Deltag")
    var training8 = listOf("17:00", "Om 7 dage", "21/11/2021", "8/12", "Bane C", "U20", "Træner: Ekkart", "Deltag")
    var training9 = listOf("17:00", "Om 8 dage", "22/11/2021", "16/12", "Bane C", "U20", "Træner: Ekkart", "Deltag")
    var training10 = listOf("17:00", "Om 9 dage", "23/11/2021", "8/12", "Bane C", "U20", "Træner: Ekkart", "Afmeld Deltagelse")
    var training11 = listOf("17:00", "Om 10 dage", "24/11/2021", "8/12", "Bane C", "U20", "Træner: Ekkart", "Deltag")
    var training12 = listOf("17:00", "Om 11 dage", "25/11/2021", "8/12", "Bane C", "U20", "Træner: Ekkart", "Deltag")

    fun getAllTraining(): List<List<String>> {
        /* TODO This should eventually fetch the data from a database.
            This should be all available training, including the ones
             already signed up for. This should take a parameter,
             to find the specific users data and trainings.*/
        var allTrainings = listOf(training1, training2, training3, training4, training5, training6, training7, training8, training9, training10, training11, training12)

        return allTrainings
    }

    fun getSignedUpTrainings(): List<List<String>> { //
    /* TODO This should eventually fetch the data from a database.
            Data should include all the current trainings a user has signed up for.
            This method should probably take a parameter, to find the correct user and data */
        var allTrainings = getAllTraining()
        var signedUpTrainings: List<List<String>> = emptyList()

        for (i in allTrainings.indices){
            var current = allTrainings[i]

            if(current[7] == "Afmeld Deltagelse"){
                signedUpTrainings = listOf(signedUpTrainings + current) as List<List<String>>
            }
        }
        return signedUpTrainings

    }
}

