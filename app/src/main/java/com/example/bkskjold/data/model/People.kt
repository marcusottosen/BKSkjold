package com.example.bkskjold.data.model

data class People(
    val id: Int,
    val name: String,
    val phoneNumber: Int,
    val team: String
)

// TODO data should be fetched from a database
object PeopleSource {
    var peopleSource: List<People> = listOf(
        People(0,"Hans-Peter", 88888888, "U13"),
        People(1,"Thomas", 44448888, "U13"),
        People(2,"Michael", 12345678, "U13"),
        People(3,"Klaus", 98265816, "guest"),
        People(4,"Jan", 11299911, "U13"),
        People(5,"Dennis", 45230875, "U13"),
        People(6,"Hans-Peter", 88888888, "U13"),
        People(7,"Thomas", 44448888, "U13"),
        People(8,"Michael", 12345678, "U13"),
        People(9,"Klaus", 98265816, "guest"),
        People(10,"Jan", 11299911, "U13"),
        People(11,"Dennis", 45230875, "U13"),
        People(12,"Hans-Peter", 88888888, "U13"),
        People(13,"Thomas", 44448888, "U13"),
        People(14,"Michael", 12345678, "U13"),
        People(15,"Klaus", 98265816, "guest"),
        People(16,"Jan", 11299911, "U13"),
        People(17,"Hans-Peter", 88888888, "U13"),
        People(18,"Thomas", 44448888, "U13"),
        People(19,"Michael", 12345678, "U13"),
        People(20,"Klaus", 98265816, "guest"),
        People(21,"Hans-Peter", 88888888, "U13"),
        People(21,"Thomas", 44448888, "U13"),
        People(23,"Michael", 12345678, "U13"),
        People(24,"Klaus", 98265816, "guest")

    )
}