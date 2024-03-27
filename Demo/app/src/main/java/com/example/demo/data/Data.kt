package com.example.demo.data

import com.google.firebase.firestore.Blob
import com.google.firebase.firestore.DocumentId

// TODO: Specify document id
data class Friend (
    @DocumentId
    val id: String = "",
    val name: String = "",
    val age: Int = 0,
    val photo: Blob = Blob.fromBytes(ByteArray(0))
)