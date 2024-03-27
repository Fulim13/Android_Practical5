package com.example.demo.data

import com.google.firebase.firestore.Blob

// TODO: Specify document id
data class Friend (

    val id: String = "",
    val name: String = "",
    val age: Int = 0,
    val photo: Blob = Blob.fromBytes(ByteArray(0))
)