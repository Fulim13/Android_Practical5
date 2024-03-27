package com.example.demo.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FriendVM : ViewModel() {

    val friendsLD = MutableLiveData<List<Friend>>()

    // Collection reference
    private val col = Firebase.firestore.collection("friends")
    init {
        // TODO: Add snapshot listener (real-time updates)

    }

    // ---------------------------------------------------------------------------------------------

    fun init() = Unit

    fun get(id: String) = friendsLD.value?.find { it.id == id }

    fun delete(id: String) {
        // TODO: Delete record by id

    }

    fun deleteAll() {
        // TODO: Delete all records

    }

    fun set(f: Friend) {
        // TODO: Set record (insert or update)

    }

    // ---------------------------------------------------------------------------------------------

    private fun idExists(id: String) = friendsLD.value?.any { it.id == id } ?: false

    fun validate(f: Friend, insert: Boolean = true): String {
        val regexId = Regex("""^[0-9A-Z]{4}$""")
        var e = ""

        if (insert) {
            e += if (f.id == "") "- Id is required.\n"
            else if (!f.id.matches(regexId)) "- Id format is invalid (format: X999).\n"
            else if (idExists(f.id)) "- Id is duplicated.\n"
            else ""
        }

        e += if (f.name == "") "- Name is required.\n"
        else if (f.name.length < 3) "- Name is too short (at least 3 letters).\n"
        else if (f.name.length > 100) "- Name is too long (at most 100 letters).\n"
        else ""

        e += if (f.age == 0) "- Age is required.\n"
        else if (f.age < 18) "- Underage (at least 18).\n"
        else if (f.age > 99) "- Overage (at most 99).\n"
        else ""

        // TODO: Validate if photo is empty
        e += if (f.photo.toBytes().isEmpty()) "- Photo is required.\n"
        else ""

        return e
    }

}