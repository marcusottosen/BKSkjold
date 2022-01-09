package com.example.bkskjold.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bkskjold.data.model.CurrentUser
import com.example.bkskjold.data.model.CurrentUserModel
import com.example.bkskjold.data.model.updateCurrentUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.type.Date
import java.lang.IllegalArgumentException

/**
 * View model for the login view.
 */
class RegisterViewModel : ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth

    private val _firstName = MutableLiveData("")
    val firstName: LiveData<String> = _firstName

    private val _lastName = MutableLiveData("")
    val lastName: LiveData<String> = _lastName

    private val _email = MutableLiveData("")
    val email: LiveData<String> = _email

    private val _password = MutableLiveData("")
    val password: LiveData<String> = _password

    private val _phoneNumber = MutableLiveData("")
    val phoneNumber: LiveData<String> = _phoneNumber

    private val _address = MutableLiveData("")
    val address: LiveData<String> = _address

    private val _team = MutableLiveData("")
    val team: LiveData<String> = _team

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    // Update name
    fun updateFirstName(newFirstName: String) {
        _firstName.value = newFirstName
    }
    fun updateLastName(newLastName: String) {
        _lastName.value = newLastName
    }

    // Update email
    fun updateEmail(newEmail: String) {
        _email.value = newEmail
    }

    // Update password
    fun updatePassword(newPassword: String) {
        _password.value = newPassword
    }

    // Update phone
    fun updatePhone(newPhoneNumber: String){
        _phoneNumber.value = newPhoneNumber
    }
    // Update address
    fun updateAddress(newAddress: String){
        _address.value = newAddress
    }
    // Update team
    fun updateTeam(newTeam: String){
        _team.value = newTeam
    }


    // Register user
    fun registerUser(home: () -> Unit) {
        if (_loading.value == false) {
            val firstName: String = _firstName.value ?: throw IllegalArgumentException("first name expected")
            val lastName: String = _lastName.value ?: throw IllegalArgumentException("last name expected")
            val email: String = _email.value ?: throw IllegalArgumentException("email expected")
            val password: String = _password.value ?: throw IllegalArgumentException("password expected")
            val phoneNumber: String = _phoneNumber.value ?: throw IllegalArgumentException("phonenumber expected")
            val address: String = _address.value ?: throw IllegalArgumentException("address expected")
            val team: String = _team.value ?: throw IllegalArgumentException("team expected")

            _loading.value = true

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Firebase.firestore.collection("users-db").document(FirebaseAuth.getInstance().uid.toString()).set(
                            CurrentUserModel(
                                FirebaseAuth.getInstance().uid.toString(),
                                firstName,
                                lastName,
                                email,
                                address,
                                phoneNumber.toInt(),
                                com.google.firebase.Timestamp.now(),
                                team,
                                1,
                                0,
                                com.google.firebase.Timestamp.now()

                        ))
                        updateCurrentUser()
                        home()
                    }
                    _loading.value = false
                }
        }
    }
}
