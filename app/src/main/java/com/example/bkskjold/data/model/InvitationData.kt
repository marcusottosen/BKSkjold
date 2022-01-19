package com.example.bkskjold.data.model

class InvitationData {
    private var invitation1 = listOf("Ekkart Kindler DTU Compute", "d. 13/04/2022", "Bane A")
    private var invitation2 = listOf("Ekkart Kindler DTU Compute", "d. 05/10/2022", "Bane B")
    private var invitation3 = listOf("Ekkart Kindler DTU Compute", "d. 01/01/2022", "Bane C")

    private val invitations = listOf(invitation1, invitation2, invitation3)

    fun getInvitations(): List<List<String>> {
        return invitations
    }

}